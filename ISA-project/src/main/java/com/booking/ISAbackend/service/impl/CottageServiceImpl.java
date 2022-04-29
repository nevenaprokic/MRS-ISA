package com.booking.ISAbackend.service.impl;

import com.booking.ISAbackend.client.Client;
import com.booking.ISAbackend.dto.AdditionalServiceDTO;
import com.booking.ISAbackend.dto.AdventureDTO;
import com.booking.ISAbackend.dto.CottageDTO;
import com.booking.ISAbackend.dto.NewCottageDTO;
import com.booking.ISAbackend.exceptions.*;
import com.booking.ISAbackend.model.*;
import com.booking.ISAbackend.repository.AdditionalServiceRepository;
import com.booking.ISAbackend.repository.AddressRepository;
import com.booking.ISAbackend.repository.CottageRepository;
import com.booking.ISAbackend.service.AdditionalServiceService;
import com.booking.ISAbackend.service.CottageService;
import com.booking.ISAbackend.service.UserService;
import com.booking.ISAbackend.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CottageServiceImpl implements CottageService {
    @Autowired
    private CottageRepository cottageRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AdditionalServiceService additionalServiceService;


    @Override
    public List<Cottage> findAll(){
        return cottageRepository.findAll();
    }

    @Override
    public List<Cottage> findCottageByCottageOwnerEmail(String email) {
        return cottageRepository.findCottageByCottageOwnerEmail(email);
    }

    @Override
    public Cottage findCottageById(Integer id) {
        return  cottageRepository.findCottageById(id);
    }
    @Override
    public Address findAddressByCottageId(Integer id){
        Cottage cottage = cottageRepository.findCottageById(id);
        Address address = cottage.getAddress();
        return address;
    }

    @Override
    public List<Cottage> searchCottages(String name, Integer maxPeople, String address, Double price) {
        return cottageRepository.searchCottages(name, maxPeople, address, price);
    }

    @Override
    public List<Cottage> searchCottagesByCottageOwner(String name, Integer maxPeople, String address, Double price, String email) {
        return cottageRepository.searchCottagesByCottageOwnerEmail(name, maxPeople, address, price, email);
    }

    @Override
    @Transactional
    public void addCottage(NewCottageDTO cottageDTO) throws CottageAlreadyExistsException, InvalidPriceException, InvalidPeopleNumberException, RequiredFiledException, InvalidAddressException, InvalidBedNumberException, InvalidRoomNumberException {
        CottageOwner cottageOwner = userService.findCottageOwnerByEmail(cottageDTO.getOwnerEmail());
        if(!isCottageAlreadyExists(cottageDTO.getOfferName(), cottageOwner.getCottages())){
            if(validateCottage(cottageDTO)){
                saveCottage(cottageDTO, cottageOwner);
            }
        }
        else{
            throw new CottageAlreadyExistsException("You already have adventure with same name. Name has to be unique!");
        }

    }
    private boolean isCottageAlreadyExists(String cottageName, List<Cottage> existingCottages){
        for (Cottage cottage: existingCottages) {
            if(cottage.getName().equals(cottageName)){
                return true;
            }
        }
        return  false;
    }
    private boolean validateCottage(NewCottageDTO cottage) throws InvalidPriceException, InvalidAddressException, RequiredFiledException, InvalidPeopleNumberException, InvalidRoomNumberException, InvalidBedNumberException {
        boolean validationResult = Validator.isValidPrice(cottage.getPrice()) &&
                Validator.isValidAdress(cottage.getStreet(), cottage.getCity(), cottage.getState()) &&
                Validator.isValidAdditionalServices(cottage.getAdditionalServices()) &&
                Validator.isValidPeopleNumber(cottage.getPeopleNum()) &&
                Validator.isValidRoomNumber(cottage.getRoomNumber()) &&
                Validator.isValidBedNumber(cottage.getBedNumber()) &&
                (!cottage.getCancelationConditions().isEmpty()) &&
                (!cottage.getDescription().isEmpty());
        return validationResult;
    }
    private void saveCottage(NewCottageDTO cottage, CottageOwner cottageOwner) {
        List<QuickReservation> quickReservations = new ArrayList<QuickReservation>();
        List<Reservation> reservations = new ArrayList<Reservation>();
        List<Client> subscribedClients = new ArrayList<Client>();
        List<Photo> photos = new ArrayList<Photo>();
        Boolean deleted = false;
        Address address = new Address(cottage.getStreet(), cottage.getCity(), cottage.getState());

        addressRepository.save(address);

        Cottage newCottage = new Cottage(cottage.getOfferName(),
                cottage.getDescription(),
                Double.valueOf(cottage.getPrice()),
                photos,
                Integer.valueOf(cottage.getPeopleNum()),
                cottage.getRulesOfConduct(),
                additionalServiceService.convertServicesFromDTO(cottage.getAdditionalServices()),
                cottage.getCancelationConditions(),
                deleted,
                address,
                quickReservations,
                reservations,
                subscribedClients,
                Integer.parseInt(cottage.getRoomNumber()),
                Integer.parseInt(cottage.getBedNumber()),
                cottageOwner);

        cottageRepository.save(newCottage);
    }

}
