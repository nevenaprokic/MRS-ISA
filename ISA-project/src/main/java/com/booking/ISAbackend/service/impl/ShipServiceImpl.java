package com.booking.ISAbackend.service.impl;

import com.booking.ISAbackend.client.Client;
import com.booking.ISAbackend.dto.NewShipDTO;
import com.booking.ISAbackend.exceptions.*;
import com.booking.ISAbackend.model.*;
import com.booking.ISAbackend.repository.AddressRepository;
import com.booking.ISAbackend.repository.ShipRepository;
import com.booking.ISAbackend.service.AdditionalServiceService;
import com.booking.ISAbackend.service.PhotoService;
import com.booking.ISAbackend.service.ShipService;
import com.booking.ISAbackend.service.UserService;
import com.booking.ISAbackend.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ShipServiceImpl implements ShipService {
    @Autowired
    private ShipRepository shipRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private PhotoService photoService;
    @Autowired
    private AdditionalServiceService additionalServiceService;

    @Override
    public List<Ship> findAll() {
        return  shipRepository.findAll();
    }

    @Override
    public List<Ship> findShipByShipOwnerEmail(String email) {
        return shipRepository.findShipByShipOwnerEmail(email);
    }

    @Override
    public Ship findShipById(Integer id) {
        return shipRepository.findShipById(id);
    }

    @Override
    public Address findAddressByShipId(Integer id) {
        Ship ship = shipRepository.findShipById(id);
        Address address = ship.getAddress();
        return address;
    }

    @Override
    public List<Ship> searchShips(String name, Integer maxPeople, String address, Double price) {
        return shipRepository.searchShips(name, maxPeople, address, price);
    }

    @Override
    public List<Ship> searchShipByShipOwner(String name, Integer maxPeople, String address, Double price, String email) {
        return shipRepository.searchShipsByShipOwnerEmail(name, maxPeople, address, price, email);
    }

    @Override
    @Transactional
    public int addShip(NewShipDTO shipDTO) throws InvalidMotorNumberException, InvalidPriceException, InvalidMaxSpeedException, InvalidSizeException, InvalidMotorPowerException, InvalidPeopleNumberException, InvalidAddressException, ShipAlreadyExistsException, IOException {
        ShipOwner shipOwner = userService.findShipOwnerByEmail(shipDTO.getOwnerEmail());
        if(!isShipAlreadyExists(shipDTO.getOfferName(), shipOwner.getShips())){
            if(validateShip(shipDTO)){
                return saveShip(shipDTO, shipOwner).getId();
            }
        }
        else{
            throw new ShipAlreadyExistsException("You already have ship with same name. Name has to be unique!");
        }
        return -1;
    }
    private boolean isShipAlreadyExists(String shipName, List<Ship> existingShips){
        for (Ship ship: existingShips) {
            if(ship.getName().equals(shipName)){
                return true;
            }
        }
        return  false;
    }
    private boolean validateShip(NewShipDTO ship) throws  InvalidPriceException, InvalidAddressException, InvalidPeopleNumberException, InvalidSizeException, InvalidMotorNumberException, InvalidMotorPowerException, InvalidMaxSpeedException {
        boolean validationResult = Validator.isValidPrice(ship.getPrice()) &&
                Validator.isValidAdress(ship.getStreet(), ship.getCity(), ship.getState()) &&
                Validator.isValidPeopleNumber(ship.getPeopleNum()) &&
                Validator.isValidSize(ship.getSize()) &&
                Validator.isValidMotorNumber(ship.getMotorNumber()) &&
                Validator.isValidMotorPower(ship.getMotorPower()) &&
                Validator.isValidMaxSpeed(ship.getMaxSpeed()) &&
                (!ship.getType().isEmpty()) &&
                (!ship.getCancelationConditions().isEmpty()) &&
                (!ship.getDescription().isEmpty());
        return validationResult;
    }
    private Ship saveShip(NewShipDTO ship, ShipOwner shipOwner) throws IOException {
        List<QuickReservation> quickReservations = new ArrayList<QuickReservation>();
        List<Reservation> reservations = new ArrayList<Reservation>();
        List<Client> subscribedClients = new ArrayList<Client>();
        List<Photo> photos = new ArrayList<Photo>();
        Boolean deleted = false;
        List<AdditionalService> additionalServices = new ArrayList<AdditionalService>();
        Address address = new Address(ship.getStreet(), ship.getCity(), ship.getState());

        addressRepository.save(address);

        Ship newShip = new Ship(ship.getOfferName(),
                ship.getDescription(),
                Double.valueOf(ship.getPrice()),
                photoService.convertPhotosFromDTO(ship.getPhotos(), shipOwner.getEmail()),
                Integer.valueOf(ship.getPeopleNum()),
                ship.getRulesOfConduct(),
                additionalServices,
                ship.getCancelationConditions(),
                deleted,
                address,
                quickReservations,
                reservations,
                subscribedClients,
                ship.getType(),
                ship.getSize(),
                Integer.parseInt(ship.getMotorNumber()),
                Integer.parseInt(ship.getMotorPower()),
                Integer.parseInt(ship.getMaxSpeed()),
                ship.getNavigationEquipment(),
                ship.getAdditionalEquipment(),
                shipOwner);

        return shipRepository.save(newShip);
    }
    @Override
    public void addAdditionalServices(List<HashMap<String, String>> additionalServiceDTOs, int offerId) throws InvalidPriceException, RequiredFiledException {
        Optional<Ship> ship = shipRepository.findById(offerId);
        if(ship.isPresent() && Validator.isValidAdditionalServices(additionalServiceDTOs)){
            Ship c = ship.get();
            List<AdditionalService> additionalServices = additionalServiceService.convertServicesFromDTO(additionalServiceDTOs);
            c.setAdditionalServices(additionalServices);
            shipRepository.save(c);
        }

    }
}
