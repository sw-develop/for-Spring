package com.example.demo.domain.user;

import jdk.jfr.Enabled;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String email;


    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role){
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture){
        this.name = name;
        this.picture = picture;

        return this;
    }



}
