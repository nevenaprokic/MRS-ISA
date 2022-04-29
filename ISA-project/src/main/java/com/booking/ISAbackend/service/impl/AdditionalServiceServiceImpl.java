package com.booking.ISAbackend.service.impl;

import com.booking.ISAbackend.dto.AdditionalServiceDTO;
import com.booking.ISAbackend.model.AdditionalService;
import com.booking.ISAbackend.repository.AdditionalServiceRepository;
import com.booking.ISAbackend.service.AdditionalServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdditionalServiceServiceImpl implements AdditionalServiceService {
    @Autowired
    private AdditionalServiceRepository additionalServiceRepository;

    @Override
    public List<AdditionalService> convertServicesFromDTO(List<AdditionalServiceDTO> servicesDTO){
        List<AdditionalService> additionalServices = new ArrayList<AdditionalService>();
        for (AdditionalServiceDTO serviceDTO: servicesDTO
        ) {
            AdditionalService additionalService = new AdditionalService(serviceDTO.getServiceName(), Double.valueOf(serviceDTO.getServicePrice()));
            additionalServices.add(additionalService);
            additionalServiceRepository.save(additionalService);
        }
        return additionalServices;
    }
}
