package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan (
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,
                classes = Configuration.class)
)//@componet가 붙은 클래스는 자동으로 스프링 빈에 등록해줌, 필터로 제외할 타입 지정
//현재 AppConfig 클래스에는 @Configuration이 붙어있으므로 자동등록이 제외됨
//지금은 예제코드와의 충돌을 막기 위해 우선 필터를 걸어놓음
public class AutoAppConfig {

}
