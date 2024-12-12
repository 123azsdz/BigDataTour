package bigData.tours.service;  // 서비스 클래스를 포함하는 패키지

import bigData.tours.domain.dao.BoardAnswer;  // BoardAnswer 도메인 객체를 임포트
import bigData.tours.repository.BoardAnswerRepository;  // BoardAnswerRepository 인터페이스를 임포트
import lombok.RequiredArgsConstructor;  // Lombok의 @RequiredArgsConstructor를 임포트
import org.springframework.stereotype.Service;  // Spring의 @Service 어노테이션을 임포트

import java.util.Optional;  // Optional 클래스를 임포트

// BoardAnswer에 대한 비즈니스 로직을 처리하는 서비스 클래스
@Service
@RequiredArgsConstructor  // 생성자 주입을 위한 자동 생성자 생성
public class BoardAnswerService {

    private final BoardAnswerRepository boardAnswerRepository;  // BoardAnswerRepository 인스턴스 변수

    // 답글을 생성하는 메서드
    public void create(BoardAnswer boardAnswer) {
        boardAnswerRepository.save(boardAnswer);  // BoardAnswer 객체를 저장
    }

    // ID로 답글을 조회하는 메서드
    public Optional<BoardAnswer> getAnswer(Long id) {
        return boardAnswerRepository.findById(id);  // 주어진 ID로 답글을 조회하고 Optional로 반환
    }

    // 답글을 삭제하는 메서드
    public void delete(BoardAnswer boardAnswer) {
        boardAnswerRepository.delete(boardAnswer);  // 주어진 답글을 삭제
    }
}
