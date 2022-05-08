package com.booking.ISAbackend.service.impl;

import com.booking.ISAbackend.client.Client;
import com.booking.ISAbackend.dto.NewCottageDTO;
import com.booking.ISAbackend.exceptions.*;
import com.booking.ISAbackend.model.*;
import com.booking.ISAbackend.repository.AddressRepository;
import com.booking.ISAbackend.repository.CottageRepository;
import com.booking.ISAbackend.service.AdditionalServiceService;
import com.booking.ISAbackend.service.CottageService;
import com.booking.ISAbackend.service.PhotoService;
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
public class CottageServiceImpl implements CottageService {
    @Autowired
    private CottageRepository cottageRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AdditionalServiceService additionalServiceService;
    @Autowired
    private PhotoService photoService;


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
    public int addCottage(NewCottageDTO cottageDTO) throws CottageAlreadyExistsException, InvalidPriceException, InvalidPeopleNumberException, RequiredFiledException, InvalidAddressException, InvalidBedNumberException, InvalidRoomNumberException, IOException {
        CottageOwner cottageOwner = userService.findCottageOwnerByEmail(cottageDTO.getOwnerEmail());
        if(!isCottageAlreadyExists(cottageDTO.getOfferName(), cottageOwner.getCottages())){
            if(validateCottage(cottageDTO)){
                return saveCottage(cottageDTO, cottageOwner).getId();
            }
        }
        else{
            throw new CottageAlreadyExistsException("You already have cottage with same name. Name has to be unique!");
        }
        return -1;

    }
    private boolean isCottageAlreadyExists(String cottageName, List<Cottage> existingCottages){
        for (Cottage cottage: existingCottages) {
            if(cottage.getName().equals(cottageName)){
                return true;
            }
        }
        return  false;
    }
    private boolean validateCottage(NewCottageDTO cottage) throws InvalidPriceException, InvalidAddressException, InvalidPeopleNumberException, InvalidRoomNumberException, InvalidBedNumberException {
        boolean validationResult = Validator.isValidPrice(cottage.getPrice()) &&
                Validator.isValidAdress(cottage.getStreet(), cottage.getCity(), cottage.getState()) &&
                Validator.isValidPeopleNumber(cottage.getPeopleNum()) &&
                Validator.isValidRoomNumber(cottage.getRoomNumber()) &&
                Validator.isValidBedNumber(cottage.getBedNumber()) &&
                (!cottage.getCancelationConditions().isEmpty()) &&
                (!cottage.getDescription().isEmpty());
        return validationResult;
    }
    private Cottage saveCottage(NewCottageDTO cottage, CottageOwner cottageOwner) throws IOException {
        List<QuickReservation> quickReservations = new ArrayList<QuickReservation>();
        List<Reservation> reservations = new ArrayList<Reservation>();
        List<Client> subscribedClients = new ArrayList<Client>();
        List<Photo> photos = new ArrayList<Photo>();
        Boolean deleted = false;
        List<AdditionalService> additionalServices = new ArrayList<AdditionalService>();
        Address address = new Address(cottage.getStreet(), cottage.getCity(), cottage.getState());

        addressRepository.save(address);

        Cottage newCottage = new Cottage(cottage.getOfferName(),
                cottage.getDescription(),
                Double.valueOf(cottage.getPrice()),
                photoService.convertPhotosFromDTO(cottage.getPhotos(), cottageOwner.getEmail()),
                Integer.valueOf(cottage.getPeopleNum()),
                cottage.getRulesOfConduct(),
                additionalServices,
                cottage.getCancelationConditions(),
                deleted,
                address,
                quickReservations,
                reservations,
                subscribedClients,
                Integer.parseInt(cottage.getRoomNumber()),
                Integer.parseInt(cottage.getBedNumber()),
                cottageOwner);

        return cottageRepository.save(newCottage);
    }
    @Override
    public void addAdditionalServices(List<HashMap<String, String>> additionalServiceDTOs, int offerId) throws InvalidPriceException, RequiredFiledException {
        Optional<Cottage> cottage = cottageRepository.findById(offerId);
        if(cottage.isPresent() && Validator.isValidAdditionalServices(additionalServiceDTOs)){
            Cottage c = cottage.get();
            List<AdditionalService> additionalServices = additionalServiceService.convertServicesFromDTO(additionalServiceDTOs);
            c.setAdditionalServices(additionalServices);
            cottageRepository.save(c);
        }

    }

}
