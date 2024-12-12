package bigData.tours.controller;

// 필요한 도메인, DTO, 서비스 및 기타 라이브러리 임포트
import bigData.tours.domain.dao.Member1; // 회원 도메인 클래스
import bigData.tours.domain.dto.LoginForm; // 로그인 폼 DTO
import bigData.tours.service.MemberService; // 회원 서비스
import jakarta.validation.Valid; // 유효성 검사를 위한 어노테이션
import lombok.RequiredArgsConstructor; // 의존성 주입을 위한 어노테이션
import org.springframework.stereotype.Controller; // Spring MVC의 컨트롤러임을 나타냄
import org.springframework.ui.Model; // 뷰에 데이터를 전달하기 위한 모델 클래스
import org.springframework.validation.BindingResult; // 유효성 검사 결과
import org.springframework.web.bind.annotation.GetMapping; // HTTP GET 요청 처리 어노테이션
import org.springframework.web.bind.annotation.ModelAttribute; // 모델 속성 바인딩 어노테이션
import org.springframework.web.bind.annotation.SessionAttribute; // 세션 속성 바인딩 어노테이션

import java.util.Optional; // Optional 클래스 사용

@Controller // Spring MVC의 컨트롤러로 지정
@RequiredArgsConstructor // 생성자 자동 생성 및 의존성 주입을 위한 어노테이션
public class MyPageController {

    private final MemberService memberService; // 회원 서비스 의존성 주입

    // 마이페이지 요청 처리
    @GetMapping("/myPage") // HTTP GET 요청을 처리
    public String myPageList(@Valid @ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult, @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member1 loginMember, Model model) {

        // 로그인한 회원의 정보를 조회
        Optional<Member1> members = memberService.findOne(loginMember.getId());
        // 회원 정보가 존재하는 경우 모델에 추가
        if(members.isPresent()) {
            model.addAttribute("members", members.get());
        }

        // 로그인한 회원 정보를 모델에 추가
        model.addAttribute("loginMember", loginMember);
        return "user/myPage"; // 마이페이지 뷰로 이동
    }

}
