package com.booking.ISAbackend.service;


import com.booking.ISAbackend.exceptions.AutomaticallyChangesCategoryIntervalException;
import com.booking.ISAbackend.exceptions.ExistingCategoryNameException;
import com.booking.ISAbackend.exceptions.OverlappingCategoryBoundaryException;
import com.booking.ISAbackend.model.ClientCategory;
import jdk.jfr.Category;

import java.util.List;

public interface ClientCategoryService {
    List<ClientCategory> findAll();

    void updateClientCategory(ClientCategory clientCategoryData) throws OverlappingCategoryBoundaryException, ExistingCategoryNameException, AutomaticallyChangesCategoryIntervalException;

    List<ClientCategory> findCategoryByReservationPoints(Integer points);
}
