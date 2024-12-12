package bigData.tours;  // 패키지 정의

import org.springframework.boot.SpringApplication;  // Spring Boot 애플리케이션 실행을 위한 클래스 임포트
import org.springframework.boot.autoconfigure.SpringBootApplication;  // Spring Boot 자동 구성 지원을 위한 어노테이션 임포트

// Spring Boot 애플리케이션의 진입점
@SpringBootApplication  // 애플리케이션 설정을 자동으로 구성
public class ToursApplication {

	// 애플리케이션의 메인 메서드
	public static void main(String[] args) {
		// Spring Boot 애플리케이션 실행
		SpringApplication.run(ToursApplication.class, args);
	}

}
