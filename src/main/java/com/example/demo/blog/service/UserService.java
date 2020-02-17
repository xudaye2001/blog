package com.example.demo.blog.service;


import com.example.demo.blog.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    /**
     * 新增/编辑/保存用户
     * @param user
     */
    User saveOrUpdateUser(User user);

    /**
     * 注册用户
     * @param user
     */
    User registerUser(User user);

    /**
     * 删除用户
     * @param id
     */
    void removeUser(Long id);

    /**
     * 根据id获取用户
     * @param id
     * @return
     */
    User getUserById(Long id);

    /**
     *
     * @param name
     * @param pageable
     * @return
     */
    Page<User> listByNameLike(String name, Pageable pageable);
}
