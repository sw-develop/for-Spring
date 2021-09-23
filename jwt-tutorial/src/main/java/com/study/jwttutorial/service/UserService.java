package com.study.jwttutorial.service;

import com.study.jwttutorial.dto.UserDto;
import com.study.jwttutorial.entity.Authority;
import com.study.jwttutorial.entity.User;
import com.study.jwttutorial.repository.UserRepository;
import com.study.jwttutorial.util.SecurityUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collections;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /*
    회원가입 로직 수행 메서드
     */
    @Transactional
    public User signup(UserDto userDto){
        //name으로 사용자 식별함
        //A.이미 가입한 유저인 경우
        if(userRepository.findOneWithAuthoritiesByName(userDto.getName()).orElse(null) != null){
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        //B.가입하지 않은 유저인 경우
        //빌더 패턴의 장점
        Authority authority = Authority.builder()
                .authorityName("ROLE_USER") //회원가입하는 유저는 ROLE_USER 권한을 가짐
                .build();

        User user = User.builder()
                .name(userDto.getName())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .nickname(userDto.getNickname())
                .authorities(Collections.singleton(authority)) //싱글톤 패턴 적용..?
                .activated(true)
                .build();

        return userRepository.save(user); //User 객체 저장
    }

    /*
    유저 & 권한정보 가져오는 메소드
     */
    //메소드 오버로딩
    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities(String name){
        return userRepository.findOneWithAuthoritiesByName(name);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities(){
        return SecurityUtil.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByName);
    }

}
