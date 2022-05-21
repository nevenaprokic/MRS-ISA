package com.booking.ISAbackend.service.impl;

import com.booking.ISAbackend.exceptions.AutomaticallyChangesCategoryIntervalException;
import com.booking.ISAbackend.exceptions.ExistingCategoryNameException;
import com.booking.ISAbackend.exceptions.OverlappingCategoryBoundaryException;
import com.booking.ISAbackend.model.ClientCategory;
import com.booking.ISAbackend.model.OwnerCategory;
import com.booking.ISAbackend.repository.OwnerCategoryRepository;
import com.booking.ISAbackend.service.OwnerCategoryService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerCategoryServiceImpl implements OwnerCategoryService {
    @Autowired
    OwnerCategoryRepository ownerCategoryRepository;

    @Override
    public List<OwnerCategory> findAll() {
        return ownerCategoryRepository.findAll();
    }

    @Override
    public List<OwnerCategory> findByReservationpoints(int points) {
        return ownerCategoryRepository.findByMatchingInterval(points);
    }

    @Override
    public void updateOwnerCategory(OwnerCategory ownerCategoryData) throws OverlappingCategoryBoundaryException, ExistingCategoryNameException, AutomaticallyChangesCategoryIntervalException {
        OwnerCategory category = ownerCategoryRepository.findById(ownerCategoryData.getId());
        if(checkUniqueCategoryName(ownerCategoryData)){
            if(checkIntervalOverlaping(ownerCategoryData)){
                ownerCategoryRepository.save(ownerCategoryData);
                checkDistanceBetweenCategoryIntervals(category);
            }
        }
    }

    public boolean checkUniqueCategoryName(OwnerCategory category) throws ExistingCategoryNameException {
        List<OwnerCategory> categories = ownerCategoryRepository.findByName(category.getName());
        if (categories.size() >1 ) throw new ExistingCategoryNameException();
        else if (categories.size() == 1 && categories.get(0).getId() != category.getId()) throw new ExistingCategoryNameException();
        return true;
    }

    private boolean checkIntervalOverlaping(OwnerCategory category) throws OverlappingCategoryBoundaryException {
        int start = category.getLowLimitPoints();
        int end = category.getHeighLimitPoints();
        String name = category.getName();
        List<OwnerCategory> overlapingIntervals = ownerCategoryRepository.findByInterval(start, end);
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

    private void checkDistanceBetweenCategoryIntervals(OwnerCategory changedCategory) throws AutomaticallyChangesCategoryIntervalException {
        List<OwnerCategory> categories = ownerCategoryRepository.findAll(Sort.by(Sort.Direction.ASC, "lowLimitPoints"));
        boolean changedHappend = false;
        for(int i=1; i< categories.size(); i++) {
            OwnerCategory current = categories.get(i);
            System.out.println("current " + current.getName()+ " " + current.getLowLimitPoints() + " -" + current.getHeighLimitPoints());

            OwnerCategory previous = categories.get(i-1);
            System.out.println("previous " + previous.getName()+ " " + previous.getLowLimitPoints() + " -" + previous.getHeighLimitPoints());
            if (current.getLowLimitPoints() - previous.getHeighLimitPoints() > 1) {
                System.out.println("TUUUUU");
                changedHappend = true;
                //CHILD TO PARENT DODATI
                if(current.getId().equals(changedCategory.getId())){
                    previous.setHeighLimitPoints(current.getLowLimitPoints() - 1);
                    ownerCategoryRepository.save(previous);
                }
                else{
                    current.setLowLimitPoints(previous.getHeighLimitPoints() + 1);
                    ownerCategoryRepository.save(current);
                }


            }

            if(changedHappend){
                throw new AutomaticallyChangesCategoryIntervalException();
            }
        }
    }
}
