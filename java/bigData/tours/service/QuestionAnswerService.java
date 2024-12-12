package bigData.tours.service;  // 서비스 클래스를 포함하는 패키지

import bigData.tours.domain.dao.QuestionAnswer;  // QuestionAnswer 도메인 객체를 임포트
import bigData.tours.repository.QuestionAnswerRepository;  // QuestionAnswerRepository 인터페이스를 임포트
import lombok.RequiredArgsConstructor;  // Lombok의 @RequiredArgsConstructor를 임포트
import org.springframework.stereotype.Service;  // Spring의 @Service 어노테이션을 임포트

import java.util.Optional;  // Optional 클래스를 임포트

// 질문에 대한 답글 관련 비즈니스 로직을 처리하는 서비스 클래스
@Service
@RequiredArgsConstructor  // 생성자 주입을 위한 자동 생성자 생성
public class QuestionAnswerService {

    private final QuestionAnswerRepository questionAnswerRepository;  // QuestionAnswerRepository 인스턴스 변수

    // 답글 생성 메서드
    public void create(QuestionAnswer questionAnswer) {
        // 주어진 답글 정보를 저장
        questionAnswerRepository.save(questionAnswer);
    }

    // 답글 조회 메서드
    public Optional<QuestionAnswer> getAnswer(Long id) {
        // 주어진 ID로 답글을 조회하여 Optional로 반환
        return questionAnswerRepository.findById(id);
    }

    // 답글 삭제 메서드
    public void delete(QuestionAnswer questionAnswer) {
        // 주어진 답글 정보를 삭제
        questionAnswerRepository.delete(questionAnswer);
    }
}
