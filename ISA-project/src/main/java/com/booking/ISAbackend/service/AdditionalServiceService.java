package com.booking.ISAbackend.service;

import com.booking.ISAbackend.dto.AdditionalServiceDTO;
import com.booking.ISAbackend.model.AdditionalService;
import com.booking.ISAbackend.model.Adventure;
import com.booking.ISAbackend.model.Offer;

import java.util.HashMap;
import java.util.List;

public interface AdditionalServiceService {
    List<AdditionalService> convertServicesFromDTO(List<HashMap<String, String>> servicesDTO);
    boolean isAdditionalServiceExists(List<AdditionalService> currentAdditionalServices, String newServcename);
    AdditionalService findAdditionalService(List<AdditionalService> currentAdditionalServices, String serviceName);
    void removeAdventureServices(List<AdditionalService> oldServices, List<HashMap<String, String>> newServices);
    List<AdditionalServiceDTO> getAdditionalServices(Offer offer);
}
