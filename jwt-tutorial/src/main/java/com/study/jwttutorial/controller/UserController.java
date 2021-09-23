package com.study.jwttutorial.controller;

import com.study.jwttutorial.dto.UserDto;
import com.study.jwttutorial.entity.User;
import com.study.jwttutorial.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@Valid @RequestBody UserDto userDto){ //검사1) @Valid : RequestBody 값에 대한 validation 처리
        //검사2) 기존에 존재하는 유저인지 확인
        return ResponseEntity.ok(userService.signup(userDto));
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')") //ROLE_USER, ROLE_ADMIN를 인식하나..?
    public ResponseEntity<User> getMyUserInfo(){
        return ResponseEntity.ok(userService.getUserWithAuthorities().get());
    }

    @GetMapping("/user/{name}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<User> getUserInfo(@PathVariable String name){
        return ResponseEntity.ok(userService.getUserWithAuthorities(name).get());
    }
}
