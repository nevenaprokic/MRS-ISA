package com.booking.ISAbackend.service;

import com.booking.ISAbackend.dto.MarkDTO;
import com.booking.ISAbackend.model.Mark;

import java.io.IOException;
import java.util.List;

public interface MarkService {
    Double getMark(Integer idOffer);
    Double getMarkByCottageOwnerEmail(String email);
    Double getMarkByShipOwnerEmail(String email);
    Double getMarkByInstructorEmail(String email);
    List<MarkDTO> getAllUncheckesMarks() throws IOException;
}
