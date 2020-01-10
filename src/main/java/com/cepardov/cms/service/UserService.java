package com.cepardov.cms.service;

import com.cepardov.cms.entity.Post;
import com.cepardov.cms.entity.Role;
import com.cepardov.cms.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    List<User> findAll();
    Page<User> findAll(Pageable pageable);
    User findById(Long id);
    User findByUsername(String username);
    User save(User user);
    User update(User user);
    void delete(User user);
    void deleteById(Long id);

    void addRole(User user, Role role);
}
