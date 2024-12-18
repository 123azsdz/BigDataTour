package bigData.tours.service;  // 서비스 클래스를 포함하는 패키지

import bigData.tours.domain.dao.Member1;  // Member 도메인 객체를 임포트
import bigData.tours.repository.MemberRepository;  // MemberRepository 인터페이스를 임포트
import lombok.RequiredArgsConstructor;  // Lombok의 @RequiredArgsConstructor를 임포트
import org.springframework.stereotype.Service;  // Spring의 @Service 어노테이션을 임포트

import java.util.Optional;  // Optional 클래스를 임포트

// 로그인 관련 비즈니스 로직을 처리하는 서비스 클래스
@Service
@RequiredArgsConstructor  // 생성자 주입을 위한 자동 생성자 생성
public class LoginService {

    private final MemberRepository memberRepository;  // MemberRepository 인스턴스 변수

    // 로그인 기능을 제공하는 메서드
    public Member1 login(String loginId, String password) {
        // 주어진 loginId로 회원 조회
        Optional<Member1> findMemberOptional = memberRepository.findByLoginId(loginId);

        // 회원이 존재하는지 확인
        if (findMemberOptional.isPresent()) {
            Member1 member1 = findMemberOptional.get();  // 회원 정보를 가져옴
            // 입력된 비밀번호와 저장된 비밀번호를 비교
            if (member1.getPassword().equals(password)) {
                return member1;  // 로그인 성공, 회원 객체 반환
            } else {
                return null;  // 비밀번호 불일치, null 반환
            }
        }
        return null;  // 회원이 존재하지 않음, null 반환
    }
}
