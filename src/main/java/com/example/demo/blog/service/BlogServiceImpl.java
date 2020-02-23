package com.example.demo.blog.service;


import com.example.demo.blog.domain.Blog;
import com.example.demo.blog.domain.User;
import com.example.demo.blog.repository.BlogRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Null;
import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRespository blogRespository;

    @Transactional
    @Override
    public Blog savaBlog(Blog blog) {
        Blog returnBlog = (Blog) blogRespository.save(blog);
        return returnBlog;
    }

    @Transactional
    @Override
    public Void removeBlog(Long id) {
        blogRespository.deleteById(id);
    }

    @Override
    public Optional getBlogById(Long id) {
        return blogRespository.findById(id);
    }

    @Override
    public Page<Blog> listBlogsByTitleVote(User user, String title, Pageable pageable) {
        title = "%"+title+"%";
        String tags = title;
        Page<Blog> blogs = blogRespository.findByTitleLikeAndUserOrTagsLikeAndUserOrderByCreateTimeDesc(
                user, tags, user, pageable);
    }

    @Override
    public Page<Blog> listBlogsByTitleVoteAndSort(User user, String tilte, Pageable pageable) {
        tilte = "%" + tilte + "%";
        Page<Blog> blogs = blogRespository.findByUserAndTitleLike(user, tilte, pageable);
        return blogs;
    }

    @Override
    public void readingIncrease(Long id) {
        Optional<Blog> blog = blogRespository.findById(id);
        Blog blogNew = null;

        if (blog.isPresent()) {
            blogNew = blog.get();
            blogNew.setReadSize(blogNew.getReadSize()+1);
            this.savaBlog(blogNew);
//            blogRespository.save(blogNew.getId());
        }
    }
}
