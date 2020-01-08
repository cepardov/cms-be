package com.cepardov.cms.service;

import com.cepardov.cms.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NoticeService {

    List<Notice> findAll();
    Page<Notice> findAll(Pageable pageable);
    Notice findById(Long id);
    Notice save(Notice notice);
    Notice update(Notice notice);
    void delete(Notice notice);
    void deleteById(Long id);

}
