package com.booking.ISAbackend.service.impl;

import com.booking.ISAbackend.model.OwnerCategory;
import com.booking.ISAbackend.repository.OwnerCategoryRepository;
import com.booking.ISAbackend.service.OwnerCategoryService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
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
}
