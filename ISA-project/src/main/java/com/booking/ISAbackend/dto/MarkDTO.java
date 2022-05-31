package com.booking.ISAbackend.dto;

import com.booking.ISAbackend.model.Mark;

public class MarkDTO {

    private Integer mark;
    private String comment;
    private Boolean approved;
    private ReservationDTO reservationDTO;

    public MarkDTO(Integer mark, String comment, Boolean approved, ReservationDTO reservationDTO) {
        this.mark = mark;
        this.comment = comment;
        this.approved = approved;
        this.reservationDTO = reservationDTO;
    }

    public MarkDTO(Mark mark, ReservationDTO reservationDTO) {
        this.mark = mark.getMark();
        this.comment = mark.getComment();
        this.approved = mark.getApproved();
        this.reservationDTO = reservationDTO;
    }

    public Integer getMark() {
        return mark;
    }

    public String getComment() {
        return comment;
    }

    public Boolean getApproved() {
        return approved;
    }

    public ReservationDTO getReservationDTO() {
        return reservationDTO;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public void setReservationDTO(ReservationDTO reservationDTO) {
        this.reservationDTO = reservationDTO;
    }
}

