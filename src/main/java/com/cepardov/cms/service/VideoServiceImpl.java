package com.cepardov.cms.service;

import com.cepardov.cms.entity.Video;
import com.cepardov.cms.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoRepository repository;

    @Override
    public List<Video> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<Video> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Video findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Video save(Video video) {
        return repository.save(video);
    }

    @Override
    public Video update(Video video) {
        return repository.save(video);
    }

    @Override
    public void delete(Video video) {
        repository.delete(video);
    }

    @Override
    public void deeleteById(Long id) {
        repository.deleteById(id);
    }
}
