package com.yw.springbootdemo.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class User implements Serializable {
    private static final long serialVersionUID = -2097162842363438971L;
    private Long id;
    private String name;
    private String password;
    private Integer age;
    private String email;
    private String abbreviate;
    private String path;
    @ExcelIgnore
    private LocalDateTime lastModifiedDate;
}
