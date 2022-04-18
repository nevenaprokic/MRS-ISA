package com.booking.ISAbackend.service.impl;

import com.booking.ISAbackend.client.Client;
import com.booking.ISAbackend.dto.AdditionalServiceDTO;
import com.booking.ISAbackend.dto.AdventureDTO;
import com.booking.ISAbackend.exceptions.*;
import com.booking.ISAbackend.model.*;
import com.booking.ISAbackend.repository.AdditionalServiceRepository;
import com.booking.ISAbackend.repository.AddressRepository;
import com.booking.ISAbackend.repository.AdventureReporitory;
import com.booking.ISAbackend.repository.PhotoRepository;
import com.booking.ISAbackend.service.AdventureService;
import com.booking.ISAbackend.service.UserService;
import com.booking.ISAbackend.validation.Validator;
import org.hibernate.boot.cfgxml.internal.JaxbCfgProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AdventureServiceImpl implements AdventureService {
    @Autowired
    private UserService userService;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AdventureReporitory adventureReporitory;

    @Autowired
    private PhotoRepository photoRepositorys;

    @Autowired
    private AdditionalServiceRepository additionalServiceRepository;


    @Override
    @Transactional
    public void addAdventure(AdventureDTO adventure) throws AdventureAlreadyExistsException, InvalidPriceException, InvalidPeopleNumberException, RequiredFiledException, InvalidAddressException {
        Instructor instructor = userService.findInstructorByEmail(adventure.getOwnerEmail());
        if(!isAdventureAlreadyExists(adventure.getOfferName(), instructor.getAdventures())){
            if(validateAdventure(adventure)){
                saveAdventure(adventure, instructor);
            }
        }
        else{
            throw new AdventureAlreadyExistsException("You already have adventure with same name. Name has to be unique!");
        }

    }

    @Override
    public List<AdventureDTO> getInstructorAdventures(String email) {
        return null;
    }

    private boolean validateAdventure(AdventureDTO adventure) throws InvalidPriceException, InvalidAddressException, RequiredFiledException, InvalidPeopleNumberException {
        boolean validationResult = Validator.isValidPrice(adventure.getPrice()) &&
                                    Validator.isValidAdress(adventure.getStreet(), adventure.getCity(), adventure.getState()) &&
                                    Validator.isValidAdditionalServices(adventure.getAdditionalServices()) &&
                                    Validator. isValidPeopleNumber(adventure.getPeopleNum()) &&
                                    (!adventure.getCancelationConditions().isEmpty()) &&
                                    (!adventure.getDescription().isEmpty());
        return validationResult;
    }

    private void saveAdventure(AdventureDTO adventure, Instructor instructor) {
        //name, description, price, photos, numberOfPerson, rulesOfConduct, additionalServices, cancellationConditions, deleted, address, quickReservations, reservations, subscribedClients, equipment, intructor

        List<QuickReservation> quickReservations = new ArrayList<QuickReservation>();
        List<Reservation> reservations = new ArrayList<Reservation>();
        List<Client> subscribedClients = new ArrayList<Client>();
        String additionalEquipment = adventure.getAdditionalEquipment();
        Boolean deleted = false;
        Address address = new Address(adventure.getStreet(), adventure.getCity(), adventure.getState());

        addressRepository.save(address);

        Adventure newAdventure = new Adventure(adventure.getOfferName(),
                adventure.getDescription(),
                Double.valueOf(adventure.getPrice()),
                convertPhotosFromDTO(adventure.getPictures()),
                Integer.valueOf(adventure.getPeopleNum()),
                        adventure.getRulesOfConduct(),
                        convertServicesFromDTO(adventure.getAdditionalServices()),
                        adventure.getCancelationConditions(),
                        deleted,
                        address,
                        quickReservations,
                        reservations,
                        subscribedClients,
                        additionalEquipment,
                        instructor);

        adventureReporitory.save(newAdventure);

    }

    private boolean isAdventureAlreadyExists(String adventureName, List<Adventure> existingAdventures){
        for (Adventure adventure: existingAdventures
             ) {
            if(adventure.getName().equals(adventureName)){
                return true;
            }
        }
        return  false;
    }



    private List<AdditionalService> convertServicesFromDTO(List<AdditionalServiceDTO> servicesDTO){
        List<AdditionalService> additionalServices = new ArrayList<AdditionalService>();
        for (AdditionalServiceDTO serviceDTO: servicesDTO
             ) {
            AdditionalService additionalService = new AdditionalService(serviceDTO.getServiceName(), Double.valueOf(serviceDTO.getServicePrice()));
            additionalServices.add(additionalService);
            additionalServiceRepository.save(additionalService);
        }
        return additionalServices;
    }

    private List<Photo> convertPhotosFromDTO(List<String> photos){
        List<Photo> adventurePhotos = new ArrayList<Photo>();
        for (String url: photos
        ) {
           Photo p = new Photo(url);
           adventurePhotos.add(p);
            photoRepositorys.save(p);

        }
        return adventurePhotos;
    }





}
