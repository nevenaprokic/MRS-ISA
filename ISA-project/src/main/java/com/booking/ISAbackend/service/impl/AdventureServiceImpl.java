package com.booking.ISAbackend.service.impl;

import com.booking.ISAbackend.client.Client;
import com.booking.ISAbackend.dto.AdditionalServiceDTO;
import com.booking.ISAbackend.dto.AdventureDTO;
import com.booking.ISAbackend.dto.AdventureDetailsDTO;
import com.booking.ISAbackend.exceptions.*;
import com.booking.ISAbackend.model.*;
import com.booking.ISAbackend.repository.AdditionalServiceRepository;
import com.booking.ISAbackend.repository.AddressRepository;
import com.booking.ISAbackend.repository.AdventureReporitory;
import com.booking.ISAbackend.repository.PhotoRepository;
import com.booking.ISAbackend.service.AdventureService;
import com.booking.ISAbackend.service.UserService;
import com.booking.ISAbackend.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private PhotoRepository photoRepository;


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
    @Transactional
    public List<AdventureDetailsDTO> getInstructorAdventures(String email) { //ubaciti ocenu
        List<Adventure> adventures = adventureReporitory.findCottageByInstructorEmail(email);
        List<AdventureDetailsDTO>  adventureDTOList = new ArrayList<AdventureDetailsDTO>();
        if(adventures != null){
            for (Adventure a: adventures
                 ) {
                //String ownerEmail, String offerName, String description, String price, List<String> pictures, String peopleNum, String rulesOfConduct, List<AdditionalServiceDTO> additionalServices, String cancelationConditions, String street, String city, String state, String additionalEquipment
                AdventureDetailsDTO dto = new AdventureDetailsDTO(email,
                        a.getName(),
                        a.getDescription(),
                        String.valueOf(a.getPrice()),
                        getPhoto(a),
                        String.valueOf(a.getNumberOfPerson()),
                        a.getRulesOfConduct(),
                        getAdditionalServices(a),
                        a.getCancellationConditions(),
                        a.getAddress().getStreet(),
                        a.getAddress().getCity(),
                        a.getAddress().getState(),
                        a.getAdditionalEquipment(), a.getId());

                adventureDTOList.add(dto);

            }

        }
        return adventureDTOList;
    }

    @Override
    @Transactional
    public AdventureDetailsDTO findAdventureById(int id) throws AdventureNotFoundException {
        Optional<Adventure> adventure = adventureReporitory.findById(id);
        if(adventure.isPresent()){
            Adventure a = adventure.get();
            AdventureDetailsDTO dto = new AdventureDetailsDTO(
                    a.getInstructor().getEmail(),
                    a.getName(),
                    a.getDescription(),
                    String.valueOf(a.getPrice()),
                    getPhoto(a),
                    String.valueOf(a.getNumberOfPerson()),
                    a.getRulesOfConduct(),
                    getAdditionalServices(a),
                    a.getCancellationConditions(),
                    a.getAddress().getStreet(),
                    a.getAddress().getCity(),
                    a.getAddress().getState(),
                    a.getAdditionalEquipment(),a.getId());

            return dto;
        }
        else{
            throw new AdventureNotFoundException("Adventure not found");
        }
    }

    private List<AdditionalServiceDTO> getAdditionalServices(Adventure a) {
        List<AdditionalServiceDTO> additionalServiceDTOList = new ArrayList<AdditionalServiceDTO>();
        for (AdditionalService service: a.getAdditionalServices()
             ) {
            AdditionalServiceDTO dto = new AdditionalServiceDTO(service.getName(), String.valueOf(service.getPrice()));
            additionalServiceDTOList.add(dto);
        }
        return  additionalServiceDTOList;
    }

    private List<String> getPhoto(Adventure a){
        List<String> photos = new ArrayList<>();
        for(Photo p: a.getPhotos()){
            photos.add(p.getPath());
        }
        return photos;
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
                convertPhotosFromDTO(adventure.getPhotos(), instructor.getEmail()),
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

    private List<Photo> convertPhotosFromDTO(List<String> photos, String email){
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
