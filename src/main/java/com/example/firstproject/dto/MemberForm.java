package com.example.firstproject.dto;

import com.example.firstproject.entity.Member;
import lombok.AllArgsConstructor; // AllArgsConstructor 패키지 자동 임포트
import lombok.ToString; // ToString 패키지 자동 임포트

@AllArgsConstructor
@ToString // 새 이노테이션 추가
public class MemberForm {
    private Long id;
    private String email;
    private String password;

    //public MemberForm(String email, String password){
     //   this.email = email;
     //   this.password = password;
   // }

  //  @Override
    //public String toString() {
     //   return "MemberForm{" +
      //          "email='" + email + '\'' +
     //           ", password='" + password + '\'' +
     //           '}';
  //  }

    public Member toEntity(){
        return new Member(id, email, password);
    }
}
