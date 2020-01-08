package com.cepardov.cms.service;

import com.cepardov.cms.entity.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ImageService {

    List<Image> findAll();
    Page<Image> findAll(Pageable pageable);
    Image findById(Long id);
    Image save(Image image);
    Image update(Image image);
    void delete(Image image);
    void deleteById(Long id);
}
