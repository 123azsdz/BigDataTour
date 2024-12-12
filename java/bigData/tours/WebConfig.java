package bigData.tours;  // 패키지 정의

import org.springframework.context.annotation.Configuration;  // Spring의 설정 클래스를 정의하기 위한 어노테이션 임포트
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;  // 인터셉터 등록을 위한 인터페이스 임포트
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;  // Spring MVC 설정을 위한 인터페이스 임포트

// Spring MVC 관련 설정을 위한 클래스
@Configuration  // 이 클래스가 Spring의 설정 클래스임을 나타냄
public class WebConfig implements WebMvcConfigurer {

    // 인터셉터를 등록하는 메서드
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // AuthorizationInterceptor를 등록하고 특정 경로에 대해 적용
        registry.addInterceptor(new AuthorizationInterceptor())
                .addPathPatterns("/admin/**", "/user/**");  // /admin/** 및 /user/** 경로에 인터셉터 적용
    }
}
