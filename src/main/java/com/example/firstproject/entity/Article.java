package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor // Article() 생성자를 대체하는 어노테이션 추가
@NoArgsConstructor // 기본 생성자 추가 어노테이션
@ToString // toString() 메서드를 대체하는 어노테이션 추가
@Entity // 엔티티 선언
public class Article {
    @Id // 엔티티의 대푯값 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동 생성 기능 추가(숫자가 자동으로 매겨짐) // DB가 아이디 자동 생성
    private Long id;
    @Column // title 필드 선언, DB 테이블의 title 열과 연결됨
    private String title;
    @Column
    private  String content;

    public Long getId(){ // 데이터 타입을 String -> Long 변경해야 함
        return id;
    }
   // Article(){ @NoArgsConstructor을 사용하면 작성하지 않아도 됌
   //
   // }
   // public Article(Long id, String title, String content) {
//        this.title = title;
     //   this.content = content;
   // }

   // @Override
   // public String toString() {
     //   return "Article{" +
         //       "id=" + id +
        //        ", title='" + title + '\'' +
         //       ", content='" + content + '\'' +
         //       '}';
  //  }
}