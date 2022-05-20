package com.booking.ISAbackend.service.impl;

import com.booking.ISAbackend.model.ClientCategory;
import com.booking.ISAbackend.repository.ClientCategoryRepository;
import com.booking.ISAbackend.service.ClientCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientCategoryServiceImpl implements ClientCategoryService {

    @Autowired
    ClientCategoryRepository clientCategoryRepository;

    @Override
    public List<ClientCategory> findAll() {
        return clientCategoryRepository.findAll();
    }
}
