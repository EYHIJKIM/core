package hello.core.singleton;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;



class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){

        //싱글톤으로 객체를 생성함 (Spring 컨테이너)
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA : A사용자가 10000원 주문
        int userAprice = statefulService1.order("userA", 10000);
        //ThreadB : A사용자가 20000원 주문
        int userBprice = statefulService2.order("userB", 20000);

        //TreadA : 사용자A 주문 금액 조회는 만원이 나와야 하지만, 20000원이 나옴.
        //int price = statefulService1.getPrice();
        System.out.println("price = "+userAprice);

        //Assertions.assertThat(userBprice).isEqualTo(20000);

    }


    //테스트용 컨테이너
    static class TestConfig{

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }

}