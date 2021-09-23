package com.study.jwttutorial.repository;

import com.study.jwttutorial.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    /*
    User Entity의 name을 기준으로 User 정보를 가져올 때 권한 정보도 같이 가져오는 메서드
     */
    @EntityGraph(attributePaths = "authorities")
    Optional<User> findOneWithAuthoritiesByName(String name);
}
