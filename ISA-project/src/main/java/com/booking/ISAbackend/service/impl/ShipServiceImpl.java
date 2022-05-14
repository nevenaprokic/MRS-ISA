package com.booking.ISAbackend.service.impl;

import com.booking.ISAbackend.dto.CottageDTO;
import com.booking.ISAbackend.dto.OfferSearchParamsDTO;
import com.booking.ISAbackend.model.Client;
import com.booking.ISAbackend.dto.NewShipDTO;
import com.booking.ISAbackend.dto.ShipDTO;
import com.booking.ISAbackend.exceptions.*;
import com.booking.ISAbackend.model.*;
import com.booking.ISAbackend.repository.AddressRepository;
import com.booking.ISAbackend.repository.ReservationRepository;
import com.booking.ISAbackend.repository.ShipRepository;
import com.booking.ISAbackend.service.*;
import com.booking.ISAbackend.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @Autowired
    private MarkService markService;
    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    @Transactional
    public List<ShipDTO> findAll() throws IOException {
        List<Ship> ships = shipRepository.findAll();
        List<ShipDTO> dto = new ArrayList<>();
        for(Ship s: ships){
            ShipDTO shipDTO = new ShipDTO(s);
            shipDTO.setMark(markService.getMark(s.getId()));
            dto.add(shipDTO);
        }
        return dto;
    }

    @Override
    @Transactional
    public List<ShipDTO> findShipByShipOwnerEmail(String email) throws IOException {
        List<Ship> ships = shipRepository.findShipByShipOwnerEmail(email);
        List<ShipDTO> dto = new ArrayList<>();
        for (Ship ship : ships) {
            ShipDTO shipDTO = new ShipDTO(ship);
            shipDTO.setMark(markService.getMark(ship.getId()));
            dto.add(shipDTO);
        }
        return dto;
    }

    @Override
    @Transactional
    public ShipDTO findShipById(Integer id) throws IOException {
        Ship ship = shipRepository.findShipById(id);
        ShipDTO shipDTO = new ShipDTO(ship);
        shipDTO.setMark(markService.getMark(ship.getId()));
        return shipDTO;
    }

    @Override
    public Address findAddressByShipId(Integer id) {
        Ship ship = shipRepository.findShipById(id);
        Address address = ship.getAddress();
        return address;
    }

    @Override
    @Transactional
    public List<ShipDTO> searchShipsClient(OfferSearchParamsDTO params) throws IOException {
        List<Ship> ships = shipRepository.searchShipsClient(params.getName(), params.getDescription(), params.getDescription());
        List<Ship> nonAvailableShips = reservationRepository.nonAvailableShips(params.getDateFrom(), params.getDateTo());

        List<Ship> availableShips = ships.stream()
                .filter(element -> !nonAvailableShips.contains(element))
                .collect(Collectors.toList());

        List<ShipDTO> dto = new ArrayList<>();
        for(Ship c: availableShips){
            ShipDTO shipDTO = new ShipDTO(c);
            shipDTO.setMark(markService.getMark(c.getId()));
            dto.add(shipDTO);
        }
        return dto;
    }

    @Override
    @Transactional
    public List<ShipDTO> searchShips(String name, Integer maxPeople, String address, Double price) throws IOException {
        List<Ship> ships = shipRepository.searchShips(name, maxPeople, address, price);
        List<ShipDTO> dto = new ArrayList<>();
        for(Ship s: ships){
            ShipDTO shipDTO = new ShipDTO(s);
            shipDTO.setMark(markService.getMark(s.getId()));
            dto.add(shipDTO);
        }
        return dto;
    }

    @Override
    @Transactional
    public List<ShipDTO> searchShipByShipOwner(String name, Integer maxPeople, String address, Double price, String email) throws IOException {
        List<Ship> ships = shipRepository.searchShipsByShipOwnerEmail(name, maxPeople, address, price, email);
        List<ShipDTO> dto = new ArrayList<>();
        for(Ship s: ships){
            ShipDTO shipDTO = new ShipDTO(s);
            shipDTO.setMark(markService.getMark(s.getId()));
            dto.add(shipDTO);
        }
        return dto;
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
