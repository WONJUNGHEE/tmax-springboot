package com.example.usersevice.controller;

import com.example.usersevice.dto.UserDto;
import com.example.usersevice.entity.UserEntity;
import com.example.usersevice.service.UserService;
import com.example.usersevice.vo.RequestUser;
import com.example.usersevice.vo.ResponseUser;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/")
public class UserController {
    Environment env;
    UserService userService;
    @Autowired
    public UserController(Environment env, UserService userService) {
        this.env = env;
        this.userService = userService;

    }

    @GetMapping("/health_check")
    public String status(HttpServletRequest request){
        return String.format("It's Working in User %s",request.getServerPort());
    }
    @GetMapping("/welcome")
    public String welcome(){
        return env.getProperty("greeting.message");
    }

    @PostMapping("/users")
    public ResponseEntity createUser(@RequestBody @Valid RequestUser user){
        ModelMapper mapper =new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto userDto = mapper.map(user, UserDto.class);
        userService.createUser(userDto);

        RequestUser requestUser = mapper.map(userDto,RequestUser.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(requestUser);
    }

    /*전체 사용자 목록*/
    @GetMapping("/users")
    public List<ResponseUser> getUsers(){
        Iterable<UserEntity> userList = userService.getUserByAll();
        List<ResponseUser> result = new ArrayList<>();

        userList.forEach( v ->result.add(new ModelMapper().map(v,ResponseUser.class)) );
        return result;
    }
    /*사용자 상세 정보*/
    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseUser> getUser(@PathVariable("userId") String userid){
        UserDto userDto = userService.getUserByUserId(userid);
        ResponseUser returnValue = new ModelMapper().map(userDto,ResponseUser.class);
        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }

}
