package com.yw.springbootdemo.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private static final long serialVersionUID = -2097162842363438971L;
    private Long id;
    private String name;
    private String password;
    private Integer age;
    private String email;
}
