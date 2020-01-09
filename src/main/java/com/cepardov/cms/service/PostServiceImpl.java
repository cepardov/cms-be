package com.cepardov.cms.service;

import com.cepardov.cms.entity.Post;
import com.cepardov.cms.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private NoticeRepository repository;

    @Override
    public List<Post> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Post findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Post save(Post post) {
        return repository.save(post);
    }

    @Override
    public Post update(Post post) {
        return repository.save(post);
    }

    @Override
    public void delete(Post post) {
        repository.delete(post);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
