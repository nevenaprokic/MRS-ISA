package com.booking.ISAbackend.service.impl;

import com.booking.ISAbackend.exceptions.AutomaticallyChangesCategoryIntervalException;
import com.booking.ISAbackend.exceptions.ExistingCategoryNameException;
import com.booking.ISAbackend.exceptions.OverlappingCategoryBoundaryException;
import com.booking.ISAbackend.model.ClientCategory;
import com.booking.ISAbackend.repository.ClientCategoryRepository;
import com.booking.ISAbackend.service.ClientCategoryService;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

@Service
public class ClientCategoryServiceImpl implements ClientCategoryService {

    @Autowired
    ClientCategoryRepository clientCategoryRepository;

    @Override
    public List<ClientCategory> findAll() {
        return clientCategoryRepository.findAll(Sort.by(Sort.Direction.ASC, "lowLimitPoints"));
    }

    @Override
    public void updateClientCategory(ClientCategory clientCategoryData) throws OverlappingCategoryBoundaryException, ExistingCategoryNameException, AutomaticallyChangesCategoryIntervalException {
        ClientCategory category = clientCategoryRepository.findById(clientCategoryData.getId());
        if(checkUniqueCategoryName(clientCategoryData)){
            if(checkIntervalOverlaping(clientCategoryData)){
                clientCategoryRepository.save(clientCategoryData);
                checkDistanceBetweenCategoryIntervals(category);
            }
        }


    }

    @Override
    public List<ClientCategory> findCategoryByReservationPoints(Integer points) {
        return clientCategoryRepository.findByMatchingInterval(points);
    }

    public boolean checkUniqueCategoryName(ClientCategory category) throws ExistingCategoryNameException {
        List<ClientCategory> categories = clientCategoryRepository.findByName(category.getName());
        if (categories.size() >1 ) throw new ExistingCategoryNameException();
        else if (categories.size() == 1 && categories.get(0).getId() != category.getId()) throw new ExistingCategoryNameException();
        return true;
    }

    private boolean checkIntervalOverlaping(ClientCategory category) throws OverlappingCategoryBoundaryException {
        int start = category.getLowLimitPoints();
        int end = category.getHeighLimitPoints();
        String name = category.getName();
        List<ClientCategory> overlapingIntervals = clientCategoryRepository.findByInterval(start, end);
        if (overlapingIntervals.size() > 1)
        {
            throw new OverlappingCategoryBoundaryException();
        }
        if (!overlapingIntervals.get(0).getId().equals(category.getId()))
        {
            throw new OverlappingCategoryBoundaryException();
        }
        return true;

    }

    private void checkDistanceBetweenCategoryIntervals(ClientCategory changedCategory) throws AutomaticallyChangesCategoryIntervalException {
        List<ClientCategory> categories = clientCategoryRepository.findAll(Sort.by(Sort.Direction.ASC, "lowLimitPoints"));
        boolean changedHappend = false;
        for(int i=1; i< categories.size(); i++) {
            ClientCategory current = categories.get(i);
            System.out.println("current " + current.getName()+ " " + current.getLowLimitPoints() + " -" + current.getHeighLimitPoints());

            ClientCategory previous = categories.get(i-1);
            System.out.println("previous " + previous.getName()+ " " + previous.getLowLimitPoints() + " -" + previous.getHeighLimitPoints());
            if (current.getLowLimitPoints() - previous.getHeighLimitPoints() > 1) {
                System.out.println("TUUUUU");
                changedHappend = true;
                //URADITI ISTO OVO ZA KATEGORIJE VLASNIKA
                //SLATI PORUKU O AUTOMATKSOJ PROMENI VREDNOSTI KADA SE NADJE RAZMAK IZMEDJU INTERVALA
                //CHILD TO PARENT DODATI
                if(current.getId().equals(changedCategory.getId())){
                    previous.setHeighLimitPoints(current.getLowLimitPoints() - 1);
                    clientCategoryRepository.save(previous);
                }
                else{
                    current.setLowLimitPoints(previous.getHeighLimitPoints() + 1);
                    clientCategoryRepository.save(current);
                }


            }

            if(changedHappend){
                throw new AutomaticallyChangesCategoryIntervalException();
            }
        }
    }





}
