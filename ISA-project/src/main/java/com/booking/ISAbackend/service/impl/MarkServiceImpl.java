package com.booking.ISAbackend.service.impl;

import com.booking.ISAbackend.model.Mark;
import com.booking.ISAbackend.model.Reservation;
import com.booking.ISAbackend.repository.MarkRepository;
import com.booking.ISAbackend.repository.ReservationRepository;
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
}
