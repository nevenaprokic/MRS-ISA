package com.booking.ISAbackend.service;

import com.booking.ISAbackend.dto.AdditionalServiceDTO;
import com.booking.ISAbackend.model.AdditionalService;

import java.util.List;

public interface AdditionalServiceService {
    List<AdditionalService> convertServicesFromDTO(List<AdditionalServiceDTO> servicesDTO);
}
