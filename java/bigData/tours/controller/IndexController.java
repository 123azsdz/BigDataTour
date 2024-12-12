package bigData.tours.controller;

// 필요한 라이브러리 및 패키지 임포트
import bigData.tours.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest; // HTTP 요청을 처리하기 위한 클래스
import jakarta.servlet.http.HttpSession; // HTTP 세션을 처리하기 위한 클래스
import lombok.RequiredArgsConstructor; // 의존성 주입을 위한 어노테이션
import org.springframework.stereotype.Controller; // 이 클래스가 Spring MVC의 컨트롤러임을 나타냄
import org.springframework.ui.Model; // 뷰에 데이터를 전달하기 위한 모델 클래스
import org.springframework.web.bind.annotation.GetMapping; // GET 요청을 처리하기 위한 어노테이션

@Controller // Spring MVC의 컨트롤러로 지정
@RequiredArgsConstructor // 생성자 자동 생성 및 의존성 주입을 위한 어노테이션
public class IndexController {

    // MemberRepository 의존성 주입
    private final MemberRepository memberRepository;

    // 루트 경로("/")에 대한 GET 요청을 처리하는 메서드
    @GetMapping("/") // HTTP GET 요청을 처리
    public String index(HttpServletRequest request, Model model) {
        // 세션을 가져오는데, 존재하지 않으면 null을 반환
        HttpSession session = request.getSession(false);

        // 세션이 없는 경우 홈 페이지로 리디렉션
        if (session == null) {
            return "index"; // "index" 뷰로 이동
        }

        // 로그인 시점에 세션에서 회원 객체를 찾음
        Object loginMember = session.getAttribute(SessionConst.LOGIN_MEMBER);

        // 세션에 회원 데이터가 없는 경우 홈 페이지로 리디렉션
        if (loginMember == null) {
            return "index"; // "index" 뷰로 이동
        }

        // 세션이 유지되면 로그인한 회원 정보를 모델에 추가
        model.addAttribute("loginMember", loginMember); // 로그인한 회원 정보 모델에 추가
        return "loginIndex"; // "loginIndex" 뷰로 이동
    }
}
