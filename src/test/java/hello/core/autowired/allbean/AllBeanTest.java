package hello.core.autowired.allbean;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class AllBeanTest {

    @Test
    void findAllBean(){
        /*
            1. AutoAppConfig : @Configuration이 붙은 최상위 클래스로, 해당 클래스의 하위에 있는 @Component들을 스프링 빈에 등록한다
            2. discountService 빈에 의존주입을 하여 객체 생성
                >> 이 때 DiscountService는 멤버변수 Map으로 모든 DiscountPolicy를 주입 받는다. (fixDiscountPolicy / rateDiscountPolicy)
            3. DiscountService.discount(Member,가격,"discount방법")
                   discount에서는 멤버변수 Map에서 "discount방법" 을 key로 하여 객체를 생성하여 할인방법을 실행한다
                   ex) discountService.discount(member, 10000, "fixDiscountPolicy"); 의 경우 고정할인정책으로 1000원 할인 return

         */
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class,DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);

        //고정할인정책 확인
        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);

        //비율할인정책 확인
        int rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");
        assertThat(rateDiscountPrice).isEqualTo(2000);

    }


    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policyList;

        @Autowired
        DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policyList) {
            this.policyMap = policyMap;
            this.policyList = policyList;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policyList = " + policyList);
        }

        //다형성 메소드
        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member, price);

        }
    }
}
