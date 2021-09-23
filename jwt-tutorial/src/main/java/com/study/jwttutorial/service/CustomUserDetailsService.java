package com.study.jwttutorial.service;

import com.study.jwttutorial.entity.User;
import com.study.jwttutorial.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String username){
        return userRepository.findOneWithAuthoritiesByName(username) //로그인 시 DB에서 유저정보와 권한정보를 가져옴
                .map(user -> createUser(username, user))
                .orElseThrow(() -> new UsernameNotFoundException(username + " -> 데이터베이스에서 찾을 수 없습니다."));
    }

    private org.springframework.security.core.userdetails.User createUser(String username, User user){
        if(!user.isActivated()){
            throw new RuntimeException(username + " -> 활성화되어있지 않습니다.");
        }
        List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                .collect(Collectors.toList()); //해당 user의 권한정보
        return new org.springframework.security.core.userdetails.User(user.getName(),
                user.getPassword(),
                grantedAuthorities); //user의 이름, 패스워드, 권한정보를 가지고 User 객체 생성함
    }
}
