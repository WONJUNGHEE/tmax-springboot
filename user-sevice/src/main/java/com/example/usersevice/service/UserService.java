package com.example.usersevice.service;

import com.example.usersevice.dto.UserDto;
import com.example.usersevice.entity.UserEntity;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto getUserByUserId(String userId);
    UserDto getUserDetailsByEmail(String email);

    /* 전체 사용자 목록 반환 */
    Iterable<UserEntity> getUserByAll();
}
