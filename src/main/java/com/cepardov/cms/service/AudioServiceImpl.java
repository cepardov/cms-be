package com.cepardov.cms.service;

import com.cepardov.cms.entity.Audio;
import com.cepardov.cms.repository.AudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AudioServiceImpl implements AudioService {

    @Autowired
    private AudioRepository repository;

    @Override
    public List<Audio> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<Audio> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Audio findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Audio save(Audio audio) {
        return repository.save(audio);
    }

    @Override
    public Audio update(Audio audio) {
        return repository.save(audio);
    }

    @Override
    public void delete(Audio audio) {
        repository.delete(audio);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
