package com.cepardov.cms.service;

import com.cepardov.cms.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoRepository repository;
}
