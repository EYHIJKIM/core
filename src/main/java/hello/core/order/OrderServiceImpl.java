package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    /*
    //3. 필드주입 : 의존관계 바로 주입.. 사용하지 않는 것이 나음. 외부에서 변경이 불가능함.*테스트 어려움 >> 사용하지말자~
    //테스트 코드에서는 사용해도 됨
    @Autowired  private  MemberRepository memberRepository;
    @Autowired private  DiscountPolicy discountPolicy;

    //필드주입의 경우 테스트시 객체가 null이 뜨므로 따로 setter를 생성해야함..
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }*/

    /*
    //2. 수정자주입 : set 주입을 통해 스프링 컨테이너에 빈 추가. 이때는 필드 값 변경이 일어나므로 final을 제거해야 함
    //선택,변경 가능성이 있는 의존관계
    @Autowired(required = false) //@Autowired시 주입할 대상이 없으면 오류가 발생함. 주입할 대상이 없어도 동작하게 하려면 required=false 추가
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        System.out.println("discountPolicy = " + discountPolicy);
        this.discountPolicy = discountPolicy;
    }
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        System.out.println("memberRepository = " + memberRepository);
        this.memberRepository = memberRepository;
    }*/

     private MemberRepository memberRepository;
     private  DiscountPolicy discountPolicy;

    //1. 생성자 주입 : 생성자가 한개 있으면 @Autowired 생략가능
    //@Autowired
    //필수,불변 의존관계
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        System.out.println("1. OrderServiceImpl.OrderServiceImpl");
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    //일반메서드 주입. 일반적으로 사용하지 않음.. >> 생성자 주입을 사용하므로
    @Autowired
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy){
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId,itemName,itemPrice,discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
