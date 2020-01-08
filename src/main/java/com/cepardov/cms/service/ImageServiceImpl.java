package com.cepardov.cms.service;

import com.cepardov.cms.entity.Image;
import com.cepardov.cms.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository repository;

    @Override
    public List<Image> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<Image> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Image findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Image save(Image image) {
        return repository.save(image);
    }

    @Override
    public Image update(Image image) {
        return repository.save(image);
    }

    @Override
    public void delete(Image image) {
        repository.delete(image);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
