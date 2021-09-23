package com.study.jwttutorial.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, unique = true)
    private String name;

    @JsonIgnore //json 형태의 response에서 해당 필드는 제외
    @Column( length = 100)
    private String password;

    @Column(length = 50)
    private String nickname;

    @JsonIgnore
    @Column
    private boolean activated;

    //Authority Entity와 다대다 관계
    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authorityName")})
    private Set<Authority> authorities;
}
