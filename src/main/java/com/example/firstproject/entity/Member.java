package com.example.firstproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity // 엔티티 선언
@AllArgsConstructor // Article() 생성자를 대체하는 어노테이션 추가
@NoArgsConstructor // 기본 생성자 추가 어노테이션
@ToString
@Getter
public class Member {
    @Id
    @GeneratedValue
    Long id;

    @Column
    String email;

    @Column
    String password;
}
