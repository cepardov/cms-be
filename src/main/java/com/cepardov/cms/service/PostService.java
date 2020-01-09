package com.cepardov.cms.service;

import com.cepardov.cms.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {

    List<Post> findAll();
    Page<Post> findAll(Pageable pageable);
    Post findById(Long id);
    Post save(Post post);
    Post update(Post post);
    void delete(Post post);
    void deleteById(Long id);

}
