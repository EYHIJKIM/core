package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
        //회원 도메인 실행과 테스트
        //AppConfig appConfig = new AppConfig();
        //MemberService memberService = appConfig.memberService();

        //순수 자바 말고 스프링을 이용하도록 전환
        //1. 스프링 컨테이너를 이용 >> 클래스 파라미터로 던져서 가져옴
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        //2.컨텍스트에 기본적으로 메소드 이름으로 이름이 등록됨. 따라서 불러오고자 하는 Bean 메소드 이름을 파라미터로 넘김
        MemberService memberService = applicationContext.getBean("memberService" ,MemberService.class);


        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new Member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());


    }
}
