package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan (
        //basePackages = "hello.core.member", //컴포넌트 스캔 위치 지정 (member 패키지 내의 component만 빈등록)
        //basePackageClasses = AutoAppConfig.class, ///지정한 클래스의 패키지를 탐색시작 위치로 지정
        //패키지를 지정하지 않으면 ComponentScan이 붙은 클래스의 패키지가 시작 위치가 됨
        //**권장방법 : 패키지 최상단에 설정정보 클래스를 위치시킴(즉 ComponentScan 클래스를 최상단에 두고 패키지 위치를 지정하지 않음)
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,
                classes = Configuration.class
                //@componet가 붙은 클래스는 자동으로 스프링 빈에 등록해줌, 필터로 제외할 타입 지정
//현재 AppConfig 클래스에는 @Configuration이 붙어있으므로 자동등록이 제외됨
//지금은 예제코드와의 충돌을 막기 위해 우선 필터를 걸어놓음
        )
)
public class AutoAppConfig {  //수동등록 빈

        @Bean(name = "memoryMemberRepository")
        MemberRepository memberRepository(){
                return new MemoryMemberRepository();
        }



}
