package com.cepardov.cms.service;

import com.cepardov.cms.entity.Notice;
import com.cepardov.cms.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    List<User> findAll();
    Page<User> findAll(Pageable pageable);
    User findById(Long id);
    User save(User user);
    User update(User user);
    void delete(User user);
    void deleteById(Long id);
    Notice findNoticeById(Long id);
    Notice saveNotice(Notice notice);
    void deleteNoticeById(Long id);
}
