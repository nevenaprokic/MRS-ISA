package com.booking.ISAbackend.service;

import com.booking.ISAbackend.exceptions.AutomaticallyChangesCategoryIntervalException;
import com.booking.ISAbackend.exceptions.ExistingCategoryNameException;
import com.booking.ISAbackend.exceptions.OverlappingCategoryBoundaryException;
import com.booking.ISAbackend.model.ClientCategory;
import com.booking.ISAbackend.model.OwnerCategory;

import java.util.List;

public interface OwnerCategoryService {
    List<OwnerCategory> findAll();
    List<OwnerCategory> findByReservationpoints(int points);
    void updateOwnerCategory(OwnerCategory ownerCategoryData) throws OverlappingCategoryBoundaryException, ExistingCategoryNameException, AutomaticallyChangesCategoryIntervalException;

}
