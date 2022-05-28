package com.booking.ISAbackend.service.impl;

import com.booking.ISAbackend.model.*;
import com.booking.ISAbackend.repository.*;
import com.booking.ISAbackend.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarkServiceImpl implements MarkService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private MarkRepository markRepository;
    @Autowired
    private CottageRepository cottageRepository;
    @Autowired
    private ShipRepository shipRepository;
    @Autowired
    private AdventureReporitory adventureReporitory;

    @Override
    public Double getMark(Integer idOffer){
        List<Mark> marks = markRepository.findAllMarkByOfferId(idOffer);
        Double mark = 0.0;
        for(Mark m: marks) {
            mark += m.getMark();
        }
        if(marks.size() > 0)
            mark /= marks.size();
        return mark;
    }

    @Override
    public Double getMarkByCottageOwnerEmail(String email){
        List<Cottage> cottages = cottageRepository.findCottageByCottageOwnerEmail(email);
        Double mark = 0.0;
        for(Cottage c: cottages){
            mark += getMark(c.getId());
        }
        if(cottages.size() > 0 && mark > 0.0)
            mark /= cottages.size();
        return mark;
    }

    @Override
    public Double getMarkByShipOwnerEmail(String email) {
        List<Ship> ships = shipRepository.findShipByShipOwnerEmail(email);
        Double mark = 0.0;
        for(Ship s: ships){
            mark += getMark(s.getId());
        }
        if(ships.size() > 0 && mark > 0.0)
            mark /= ships.size();
        return mark;
    }

    @Override
    public Double getMarkByInstructorEmail(String email) {
        List<Adventure> adventures = adventureReporitory.findAdventureByInstructorEmail(email);
        Double mark = 0.0;
        for(Adventure a: adventures){
            mark += getMark(a.getId());
        }
        if(adventures.size() > 0 && mark > 0.0)
            mark /= adventures.size();
        return mark;
    }
}
