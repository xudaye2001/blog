package com.example.demo.blog.service;

import com.example.demo.blog.domain.Authority;

import java.util.Optional;

public interface AuthorityService {
    /**
     * 根据Id查询authority
     * @return
     */
    Optional<Authority> getAuthorityById(Long id);
}
