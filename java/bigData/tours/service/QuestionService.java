package bigData.tours.service;  // 서비스 클래스를 포함하는 패키지

import bigData.tours.domain.dao.Board;
import bigData.tours.domain.dao.Member1;  // Member 도메인 객체를 임포트
import bigData.tours.domain.dao.Question;  // Question 도메인 객체를 임포트
import bigData.tours.domain.dao.QuestionAnswer;  // QuestionAnswer 도메인 객체를 임포트
import bigData.tours.repository.QuestionRepository;  // QuestionRepository 인터페이스를 임포트
import jakarta.persistence.criteria.*;  // JPA Criteria API를 위한 클래스 임포트
import lombok.RequiredArgsConstructor;  // Lombok의 @RequiredArgsConstructor를 임포트
import org.springframework.data.domain.Page;  // 페이지 처리 관련 클래스 임포트
import org.springframework.data.domain.PageRequest;  // 페이지 요청 클래스 임포트
import org.springframework.data.domain.Sort;  // 정렬 관련 클래스 임포트
import org.springframework.data.jpa.domain.Specification;  // JPA Specification 인터페이스 임포트
import org.springframework.stereotype.Service;  // Spring의 @Service 어노테이션을 임포트

import java.util.ArrayList;  // ArrayList 클래스 임포트
import java.util.List;  // List 인터페이스 임포트
import java.util.Optional;  // Optional 클래스 임포트

// 질문 관련 비즈니스 로직을 처리하는 서비스 클래스
@Service
@RequiredArgsConstructor  // 생성자 주입을 위한 자동 생성자 생성
public class QuestionService {

    private final QuestionRepository questionRepository;  // QuestionRepository 인스턴스 변수

    // 질문 생성 메서드
    public void create(Question question) {
        // 주어진 질문 정보를 저장
        question.setNotice(false);
        questionRepository.save(question);
    }

    // 공지사항 등록 로직
    public void createNotice(Question question) {
        question.setNotice(true); // 공지사항 여부 설정
        questionRepository.save(question);
    }

    // 전체 질문 조회 메서드
    // 페이지 번호와 검색 키워드를 입력받아 해당 페이지의 질문 목록을 반환
    // 최신순으로 데이터 확인하기

    public Page<Question> getList(int page, String kw) {
        List<Sort.Order> sorts = List.of(Sort.Order.desc("createDate"));
        PageRequest pageable = PageRequest.of(page, 10, Sort.by(sorts));

        Specification<Question> spec = search(kw).and((root, query, cb) -> cb.isFalse(root.get("isNotice")));

        return questionRepository.findAll(spec, pageable);
    }



    // 공지사항과 일반 게시글을 분리하여 가져오는 메서드
    public List<Question> getQuestionsWithNotices() {
        List<Question> notices = questionRepository.findByIsNoticeTrue(Sort.by(Sort.Direction.DESC, "createDate"));  // 공지사항 조회

        return notices;
    }

    // 로그인한 사용자의 질문만 가져오는 메서드 추가
    public Page<Question> getMyQuestions(Long memberId, int page) {
        List<Sort.Order> sorts = new ArrayList<>(); // 정렬 기준을 저장할 리스트
        sorts.add(Sort.Order.desc("createDate"));  // 생성일 기준으로 내림차순 정렬
        PageRequest pageable = PageRequest.of(page, 10, Sort.by(sorts));  // 페이징 처리
        Page<Question> result = questionRepository.findByAuthor_Id(memberId, pageable);  // 질문 목록 조회

        return result;
    }


    // 특정 질문 조회 메서드
    public Optional<Question> getQuestion(Long id) {
        return questionRepository.findById(id);  // 주어진 ID로 질문을 조회하여 Optional로 반환
    }

    // 질문 삭제 메서드
    public void delete(Question question) {
        questionRepository.delete(question);  // 주어진 질문 정보를 삭제
    }

    // 검색 기능 추가 메서드
    // JPA의 Specification 인터페이스를 사용하여 DB 검색을 유연하게 처리
    private Specification<Question> search(String kw) {
        return new Specification<Question>() {
            private static final long serialVersionUID = 1L;  // 직렬화 과정에서의 일관성 보장
            @Override
            public Predicate toPredicate(Root<Question> q, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                // 중복제거
                query.distinct(true);  // 중복된 결과 제거
                Join<Question, Member1> m1 = q.join("author", JoinType.LEFT);  // 질문 작성자 검색
                Join<Question, QuestionAnswer> a = q.join("questionAnswerList", JoinType.LEFT);  // 답변 내용 검색
                Join<QuestionAnswer, Member1> m2 = a.join("author", JoinType.LEFT);  // 답변 작성자 검색
                // 검색 조건을 OR로 연결하여 쿼리 생성
                return criteriaBuilder.or(
                        criteriaBuilder.like(q.get("subject"), "%" + kw + "%"),  // 제목 검색
                        criteriaBuilder.like(q.get("content"), "%" + kw + "%"),  // 질문 내용 검색
                        criteriaBuilder.like(m1.get("name"), "%" + kw + "%"),  // 질문 작성자 이름 검색
                        criteriaBuilder.like(m1.get("loginId"), "%" + kw + "%"),  // 질문 작성자 아이디 검색
                        criteriaBuilder.like(a.get("content"), "%" + kw + "%"),  // 답변 내용 검색
                        criteriaBuilder.like(m2.get("name"), "%" + kw + "%")  // 답변 작성자 이름 검색
                );
            }
        };
    }
    // 특정 게시글 조회수 증가 메서드
    public void increaseViewCount(Long questionId) {
        Optional<Question> questionOptional = questionRepository.findById(questionId);
        if (questionOptional.isPresent()) {
            Question question = questionOptional.get();
            question.increaseViewCount();  // 조회수 증가
            questionRepository.save(question);  // 변경된 게시글 저장
        }
    }
}
