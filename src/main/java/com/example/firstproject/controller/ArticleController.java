package com.example.firstproject.controller;

import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.pulsar.PulsarAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.firstproject.dto.ArticleForm; // AtricleForm 패키지 자동 임포트
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Slf4j // 로깅 기능을 위한 어노테이션 추가
@Controller // 컨트롤러 선언
public class ArticleController {
    @Autowired // 스프링 부트가 미리 생성해 놓은 리파지터리 객체 주입(DI)
    private ArticleRepository articleRepository; // articleRepository 객체 선언


    @GetMapping("/articles/new") //URL 요청 접수
    public String newArticleForm(){
        return "articles/new";
    }
    @GetMapping("/articles/{id}") // 데이터 조회 요청 검수
    public String show(@PathVariable Long id, Model model){
        log.info("id = " + id);
        // 1. id를 조회해 DB에서 해당 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // 2. 가져온 데이터를 모델에 등록하기
        // name이라는 이름으로 value 객체 추가
        model.addAttribute("article", articleEntity);
        // 3. 조회한 데이터를 사용자에게 보여 주기 위한 뷰 페이지 만들고 반환하기
        return "articles/show"; // 목록으로 돌아가기 링크를 넣을 뷰 파일 확인
    }

    @GetMapping("/articles")
    public String index(Model model){
        // 1. DB에서 모든 Article 데이터 가져오기
        List<Article> articleEntityList = articleRepository.findAll();
        // 2. 가져온 Article 묶음을 모델에 등록하기
        model.addAttribute("articleList",articleEntityList); // articleEntityList 등록
        // 3. 사용자에게 보여 줄 뷰 페이지 설정하기
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit") // URL 요청 접수
    public String edit(@PathVariable Long id, Model model){
        // 수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // 모델에 데이터 등록하기
        model.addAttribute("article", articleEntity); // articleEntity를 article로 등록
        // 뷰 페이지 설정하기
        return "articles/edit";
    }
    @PostMapping("/articles/update")
    public String update(ArticleForm form){ // 매개변수로 DTO 받아오기
        log.info(form.toString());
        // 1. DTO를 엔티티로 변환하기
        Article articleEntity = form.toEntity(); // DTO(form)를 엔티티로 변환하기
        log.info(articleEntity.toString()); // 엔티티로 잘 변환됐는지 로그 찍기
        // 2. 엔티티를 DB에 저장하기
        // 2-1. DB에서 기존 데이터 가져오기
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        // 2-2. 기존 데이터 값을 갱신하기
        if (target != null){
            articleRepository.save(articleEntity); // 엔티티를 DB에 저장(갱신)
        }
        // 3. 수정 결과 페이지로 리다이렉트하기
        return "redirect:/articles/" + articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제 요청이 들어왔습니다!!");
        // 1. 삭제할 대상 가져오기
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());
        // 2. 대상 엔티티 삭제하기
        if (target != null) {
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제됐습니다!");
        }
        // 3. 결과 페이지로 리다이렉트하기
        return "redirect:/articles";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){ // 폼 데이터를 DTO로 받기
        // 1. DTO를 엔티티로 변환
        //System.out.println(form.toString()); // DTO에 폼 데이터가 잘 담겼는지 확인
        log.info(form.toString()); // 로깅 코드 추가
        Article article = form.toEntity();
        //System.out.println(article.toString()); // DTO가 엔티티로 잘 변환되는지 확인 출력
        log.info(form.toString()); // 로깅 코드 추가

        // 2. 리파지터리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);
        //System.out.println(saved.toString()); //article이 DB에 잘 저장되는지 확인 출력
        log.info(saved.toString());
        return "redirect:/articles/" + saved.getId(); // 리다이렉트를 작성할 위치
    }
}
