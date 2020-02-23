package com.example.demo.blog.service;

import com.example.demo.blog.domain.Blog;
import com.example.demo.blog.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BlogService  {

    /**
     * 保存blog
     * @param blog
     * @return
     */
    Blog savaBlog(Blog blog);

    /**
     * 删除博客
     * @param id
     * @return
     */
    Void removeBlog(Long id);

    /**
     * 根据id获取Blog
     * @param id
     * @return
     */
    Optional<Blog> getBlogById(Long id);

    /**
     * 根据用户进行博客名称分页模糊查询(最新)
     * @param user
     * @param title
     * @param pageable
     * @return
     */
    Page<Blog> listBlogsByTitleVote(User user, String title, Pageable pageable);

    /**
     * 根据用户进行博客名称分页模糊查询(最热)
     * @param user
     * @param tilte
     * @param pageable
     * @return
     */
    Page<Blog> listBlogsByTitleVoteAndSort(User user, String tilte, Pageable pageable);


    void readingIncrease(Long id);


}
