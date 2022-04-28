package com.booking.ISAbackend.service;

import com.booking.ISAbackend.model.Photo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;


public interface PhotoService {

    Photo getPhoto(int id);
    Stream<Photo> getAllPhotos();

}
