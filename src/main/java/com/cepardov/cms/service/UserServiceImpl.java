package com.cepardov.cms.service;

import com.cepardov.cms.entity.Notice;
import com.cepardov.cms.entity.User;
import com.cepardov.cms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private NoticeService noticeService;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<User> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User update(User user) {
        return repository.save(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(User user) {
        repository.delete(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Notice findNoticeById(Long id) {
        return noticeService.findById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Notice saveNotice(Notice notice) {
        return noticeService.save(notice);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteNoticeById(Long id) {
        noticeService.deleteById(id);
    }
}
