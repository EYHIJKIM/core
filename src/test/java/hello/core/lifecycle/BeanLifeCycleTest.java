package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {
    @Test
    public void lifeCycleTest(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        //ApplicationContext는 close가 없으므로.. 이번에는 Configurable~~을 사용
        ac.close();

    }

    @Configuration
    static class LifeCycleConfig{

        //destroyMethod 의 디폴트가 "(inferred)" =추론 으로 등록되어있음..이름 그대로 추론해서 종료메서드(close,shutdown 등)를 실행하는 것.
        //즉 destoryMethod를 적어주지 않아도 종료메서드가 자동으로 실행 됨. 종료메서드 실행시키기 싫으면 "" 빈값으로 넣어주면 실행 안됨.
        @Bean//(initMethod = "init", destroyMethod = "close")
        public NetworkClient networkClient(){
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
