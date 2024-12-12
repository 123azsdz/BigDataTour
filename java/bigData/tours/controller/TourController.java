package bigData.tours.controller;

import bigData.tours.domain.dao.Member1;  // Member 클래스 임포트
import lombok.RequiredArgsConstructor;  // Lombok의 RequiredArgsConstructor 임포트
import lombok.extern.slf4j.Slf4j;  // SLF4J 로깅 기능을 위한 Lombok 임포트
import org.springframework.stereotype.Controller;  // Spring의 Controller 어노테이션 임포트
import org.springframework.ui.Model;  // Spring의 Model 인터페이스 임포트
import org.springframework.web.bind.annotation.GetMapping;  // GET 요청을 매핑하기 위한 어노테이션 임포트
import org.springframework.web.bind.annotation.PathVariable;  // URL 경로 변수를 가져오기 위한 어노테이션 임포트
import org.springframework.web.bind.annotation.SessionAttribute;  // 세션 속성을 가져오기 위한 어노테이션 임포트

// 로깅 기능을 제공하는 클래스. 로그 메시지를 생성하고 관리
@Slf4j
// 스프링 MVC의 Controller 역할을 하는 클래스. 클라이언트 요청을 처리하는 메소드들을 포함
@Controller
@RequiredArgsConstructor  // 생성자를 자동 생성하여 의존성 주입을 간편하게 함
public class TourController {

    // 투어 목록 페이지를 보여주는 메소드
    @GetMapping("/tours")
    public String tour(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member1 loginMember, Model model) {
        // 세션에서 로그인한 회원 정보를 가져와서 모델에 추가
        // required = false 설정으로 로그인하지 않은 경우에도 null을 반환 가능
        model.addAttribute("loginMember", loginMember);
        // "tour" 뷰로 이동하여 투어 목록 페이지를 렌더링
        return "tour";
    }

    // 특정 투어의 상세 정보를 보여주는 메소드
    @GetMapping("/tours/detail/{contentId}")
    public String detail(@PathVariable("contentId") Long contentId, @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member1 loginMember, Model model) {
        // URL에서 contentId를 추출하여 로그에 기록
        log.info("contentID : {}", contentId);

        // 세션에서 로그인한 회원 정보를 모델에 추가
        model.addAttribute("loginMember", loginMember);
        // contentId를 모델에 추가하여 뷰에서 사용 가능하도록 함
        model.addAttribute("contentId", contentId);

        // "detail" 뷰로 이동하여 해당 투어의 상세 정보를 렌더링
        return "detail";
    }
}
