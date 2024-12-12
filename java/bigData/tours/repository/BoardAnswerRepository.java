package bigData.tours.repository;  // 리포지토리 인터페이스를 포함하는 패키지

import bigData.tours.domain.dao.BoardAnswer;  // BoardAnswer 도메인 객체를 임포트
import org.springframework.data.jpa.repository.JpaRepository;  // Spring Data JPA의 JpaRepository를 임포트

// BoardAnswer 엔티티에 대한 CRUD(생성, 읽기, 업데이트, 삭제) 작업을 제공하는 리포지토리 인터페이스
public interface BoardAnswerRepository extends JpaRepository<BoardAnswer, Long> {
    // JpaRepository를 상속받아 기본적인 CRUD 메서드를 자동으로 사용할 수 있습니다.
    // BoardAnswer 엔티티를 다루며, id의 타입은 Long입니다.
}
