package com.example.usersevice.vo;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RequestUser {

    @NotNull(message = "Email cannot be null")
    @Size(min = 2,message = "Email not be less than two charactors")
    @Email
    private String email;
    @NotNull(message = "name cannot be null")
    @Size(min = 2,message = "Email not be less than two charactors")
    private String name;
    @NotNull(message = "pds cannot be null")
    @Size(min = 8,message = "Email not be less than two charactors")
    private String pwd;
}
