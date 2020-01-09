package com.cepardov.cms.repository;

import com.cepardov.cms.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Post, Long> {
}
