package com.study.jwttutorial.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Authority { //Role Enum이랑 동일한 역할의 클래스인 듯
    @Id
    @Column(length = 50)
    private String authorityName;
}
