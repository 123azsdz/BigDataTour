package bigData.tours.repository;  // 리포지토리 인터페이스를 포함하는 패키지

import bigData.tours.domain.dao.Member1;  // Member 도메인 객체를 임포트
import org.springframework.data.jpa.repository.JpaRepository;  // Spring Data JPA의 JpaRepository를 임포트

import java.util.Optional;  // Optional 클래스를 임포트하여 null을 안전하게 처리하기 위함

// Member 엔티티에 대한 CRUD(생성, 읽기, 업데이트, 삭제) 작업을 제공하는 리포지토리 인터페이스
public interface MemberRepository extends JpaRepository<Member1, Long> {

    // 로그인 ID로 회원을 찾는 메서드
    Optional<Member1> findByLoginId(String loginId);
}
