package bigData.tours.repository;  // 리포지토리 인터페이스를 포함하는 패키지

import bigData.tours.domain.dao.Question;  // Question 도메인 객체를 임포트
import org.springframework.data.domain.Page;  // 페이지 처리 기능을 위한 Page 클래스를 임포트
import org.springframework.data.domain.Pageable;  // 페이지 요청 정보를 위한 Pageable 클래스를 임포트
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;  // JPA 스펙을 정의하기 위한 Specification 클래스를 임포트
import org.springframework.data.jpa.repository.JpaRepository;  // Spring Data JPA의 JpaRepository를 임포트

import java.util.List;

// Question 엔티티에 대한 CRUD(생성, 읽기, 업데이트, 삭제) 작업을 제공하는 리포지토리 인터페이스
public interface QuestionRepository extends JpaRepository<Question, Long> {

    // 제목으로 질문을 찾기 위한 메서드 (구현 필요)

    // 제목과 내용으로 질문을 찾기 위한 메서드 (구현 필요)

    // 조건에 맞는 질문을 페이지 단위로 조회하기 위한 메서드
    // Specification을 사용하여 조건을 정의하고, Pageable을 사용하여 페이지 정보 제공
    Page<Question> findAll(Specification<Question> spec, Pageable pageable);
    Page<Question> findByAuthor_Id(Long memberId, Pageable pageable);

    // 공지사항만 조회하는 메서드
    List<Question> findByIsNoticeTrue(Sort sort);

}
