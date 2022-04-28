package com.booking.ISAbackend.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.booking.ISAbackend.model.Photo;
import com.booking.ISAbackend.repository.PhotoRepository;
import com.booking.ISAbackend.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public Photo getPhoto(int id) {
        return photoRepository.findById(id).get();
    }

    @Override
    public Stream<Photo> getAllPhotos() {
        return photoRepository.findAll().stream();
    }


}
