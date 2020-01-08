package com.cepardov.cms.service;

import com.cepardov.cms.entity.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VideoService {

    List<Video> findAll();
    Page<Video> findAll(Pageable pageable);
    Video findById(Long id);
    Video save(Video video);
    Video update(Video video);
    void delete(Video video);
    void deeleteById(Long id);
}
