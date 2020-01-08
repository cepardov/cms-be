package com.cepardov.cms.service;

import com.cepardov.cms.entity.Audio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AudioService {

    List<Audio> findAll();
    Page<Audio> findAll(Pageable pageable);
    Audio findById(Long id);
    Audio save(Audio audio);
    Audio update(Audio audio);
    void delete(Audio audio);
    void deleteById(Long id);
}
