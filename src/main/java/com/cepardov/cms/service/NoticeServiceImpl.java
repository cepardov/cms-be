package com.cepardov.cms.service;

import com.cepardov.cms.entity.Notice;
import com.cepardov.cms.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeRepository repository;

    @Override
    public List<Notice> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<Notice> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Notice findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Notice save(Notice notice) {
        return repository.save(notice);
    }

    @Override
    public Notice update(Notice notice) {
        return repository.save(notice);
    }

    @Override
    public void delete(Notice notice) {
        repository.delete(notice);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
