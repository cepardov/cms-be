package com.cepardov.cms.service;

import com.cepardov.cms.repository.AudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AudioServiceImpl implements AudioService {

    @Autowired
    private AudioRepository repository;
}
