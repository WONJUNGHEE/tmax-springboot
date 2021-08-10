package com.example.usersevice.jpa;

import com.example.usersevice.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity,Long> {
    UserEntity findByUserId(String userId);
}
