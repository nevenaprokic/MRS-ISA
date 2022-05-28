package com.booking.ISAbackend.service;

public interface MarkService {
    Double getMark(Integer idOffer);
    Double getMarkByCottageOwnerEmail(String email);
    Double getMarkByShipOwnerEmail(String email);
    Double getMarkByInstructorEmail(String email);
}
