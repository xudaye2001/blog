package com.example.demo.blog.domain;

import lombok.Data;
//import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * User 实体.
 *
 * @since 1.0.0 2017年7月9日
 * @author <a href="https://waylau.com">Way Lau</a>
 */
@Entity // 实体
@Data
public class User {

	@Id // 主键
	@GeneratedValue(strategy=GenerationType.IDENTITY) // 自增长策略
	private Long id; // 实体一个唯一标识

	@NotEmpty
	@Size(min = 2, max = 20)
	@Column(nullable = false, length = 20)
	private String name;

	@NotEmpty(message = "邮箱地址不能为空")
	@Size(max = 50)
	@Column(nullable = false, length = 50, unique = true)
	private String email;

	@NotEmpty(message = "用户名不能为空")
	@Size(min = 4, max = 20)
	@Column(length = 20, nullable = false, unique = true)
	private String username;

	@NotEmpty(message = "密码不能为空")
	@Size(min = 4, max = 20)
	@Column(nullable = false, length = 20)
	private String password;

	@Column(length = 200)
	private String avatar;
}
