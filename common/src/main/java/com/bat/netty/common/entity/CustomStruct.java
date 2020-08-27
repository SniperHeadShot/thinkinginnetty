package com.bat.netty.common.entity;

/**
 * 测试用结构体
 *
 * @author ZhengYu
 * @version 1.0 2020/8/27 13:54
 **/
public class CustomStruct {

    private Long id;

    private String username;

    private Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
