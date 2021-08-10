package com.example.usersevice.vo;

import lombok.Data;

import java.util.List;

@Data
public class ResponseUser {
    private String email;
    private String name;
    private String pwd;

    private List<ResponseOrder> orders;
}
