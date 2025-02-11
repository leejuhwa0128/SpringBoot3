package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor; // AllArgsConstructor 패키지 자동 임포트
import lombok.ToString; // ToString 패키지 자동 임포트

@AllArgsConstructor
@ToString // 새 이노테이션 추가
public class ArticleForm {
    private Long id; // id 필드 추가
    private String title; // 제목을 받을 필드
    private String content; // 내용을 받을 필드

    // 전송받은 제목과 내용을 필드에 저장하는 생성자 추가( 롬복 이용 위해 생성자 삭제)
    //public ArticleForm(String title, String content) {
    //    this.title = title;
    //    this.content = content;
   // }

    // 데이터를 잘 받았는지 확인할 toString() 메서드 추가( 롬복 이용 위해 생성자 삭제)
    //@Override
    //public String toString() {
     //   return "ArticleForm{" +
       //         "title='" + title + '\'' +
         //       ", content='" + content + '\'' +
         //       '}';
   // }

    public Article toEntity() {
        return new Article(id, title, content);
    }
}
