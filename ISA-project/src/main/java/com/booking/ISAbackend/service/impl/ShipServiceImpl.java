package com.booking.ISAbackend.service.impl;

import com.booking.ISAbackend.client.Client;
import com.booking.ISAbackend.dto.NewCottageDTO;
import com.booking.ISAbackend.dto.NewShipDTO;
import com.booking.ISAbackend.exceptions.*;
import com.booking.ISAbackend.model.*;
import com.booking.ISAbackend.repository.AddressRepository;
import com.booking.ISAbackend.repository.ShipRepository;
import com.booking.ISAbackend.service.ShipService;
import com.booking.ISAbackend.service.UserService;
import com.booking.ISAbackend.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShipServiceImpl implements ShipService {
    @Autowired
    private ShipRepository shipRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private AddressRepository addressRepository;

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
    public List<Ship> searchShipByShipOwner(String name, Integer maxPeople, String address, Double price, String email) {
        return shipRepository.searchShipsByShipOwnerEmail(name, maxPeople, address, price, email);
    }

    @Override
    @Transactional
    public void addShip(NewShipDTO shipDTO) throws InvalidMotorNumberException, InvalidPriceException, InvalidMaxSpeedException, InvalidSizeException, InvalidMotorPowerException, InvalidPeopleNumberException, InvalidAddressException, ShipAlreadyExistsException {
        ShipOwner shipOwner = userService.findShipOwnerByEmail(shipDTO.getOwnerEmail());
        if(!isShipAlreadyExists(shipDTO.getOfferName(), shipOwner.getShips())){
            if(validateShip(shipDTO)){
                saveShip(shipDTO, shipOwner);
            }
        }
        else{
            throw new ShipAlreadyExistsException("You already have ship with same name. Name has to be unique!");
        }

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
    private void saveShip(NewShipDTO ship, ShipOwner shipOwner) {
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
                photos,
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

        shipRepository.save(newShip);
    }
}
