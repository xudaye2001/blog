package com.example.demo.blog.vo;

import lombok.Data;

@Data
public class Menu {
    private String name;  //菜单名称
    private String url;   //菜单url

    public Menu(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
