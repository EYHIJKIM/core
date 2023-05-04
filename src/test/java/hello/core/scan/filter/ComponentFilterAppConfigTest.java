package hello.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ComponentFilterAppConfigTest {

    @Test
    void filterScan(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppCOnfig.class);
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        assertThat(beanA).isNotNull(); //MyIncludeComponent, 스프링 컨테이너에 빈이 존재해야 하므로 not null

        //BeanB beanB = ac.getBean("BeanB", BeanB.class);
        assertThrows(NoSuchBeanDefinitionException.class,
        () -> ac.getBean("beanB", BeanB.class)) //MyExcludeComponent, 스프링 컨테이너에 빈이 없어야 하므로 익셉션 터져야 함
        ;
    }

    @Configuration
    @ComponentScan(//1.type = FilterType.ANNOTATION,  기본값 생략 가능
            includeFilters = @ComponentScan.Filter(classes = MyIncludeComponent.class)
            , excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppCOnfig{

    }
}
