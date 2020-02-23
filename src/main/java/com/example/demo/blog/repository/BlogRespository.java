package com.example.demo.blog.repository;

import com.example.demo.blog.domain.Blog;
import com.example.demo.blog.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRespository extends JpaRepository {

    /**
     * 根据用户名/博客标题分页查询博客列表
     * @param user
     * @param title
     * @param pageable
     * @return
     */
    Page<Blog> findByUserAndTitleLike(User user, String title, Pageable pageable);

    /**
     * 根据用户名/博客查询博客列表(时间逆序)
     * @param user
     * @param tags
     * @param user1
     * @param pageable
     * @return
     */
    Page<Blog> findByTitleLikeAndUserOrTagsLikeAndUserOrderByCreateTimeDesc(
            User user, String tags, User user1, Pageable pageable);
}