package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor //Final(필수) 멤버변수 값을 생성자에서 자동으로 넣어주는 롬복 @
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

     private final MemberRepository memberRepository;
     private final DiscountPolicy discountPolicy;

    //**1. 생성자 주입 : 생성자가 한개 있으면 **@Autowired 생략가능
    // >> 생성자 주입을 사용하라 (1.불변,누락을 막을 수 있음/ 2.final 키워드 사용가능 : 값이 설정되지 않는 오류를 컴파일 시점에서 막아줌!(깜빡하고 생성자에서 값을 안넣어준경우))
    //@Autowired
    //필수,불변 의존관계

    //@RequiredArgsConstructor 가 하는 기능이 바로 아래 생성자 코드
    //qualifier : 동일한 인터페이스를 사용하는 경우 autowired를 했을 때, 예외가 날 수 있음(nosuchbeandefinition). 이 때 이름을 명명해주는 것
    // >> >> 주입받을 때 모든 코드에 qualifier를 명명해 주어야 한다는 번거로움이 존재..
    //priamary : 우선순위를 정해주는 것. 동일한 인터페이스를 사용하는 경우 . primary를 많이 사용한다.
    //ex) 메인 DB / 보조 DB(거의안씀) 가 있을 때, 메인 DB 빈에 primary를 걸어주고 보조를 사용하는 경우에는 qualifier or 이름지정을 해서 사용하는 것.
    // qualifier가 우선순위가 높음(더 구체적이고 자세한 경우가 우선순위가 높음!)
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        System.out.println("1. OrderServiceImpl.OrderServiceImpl");
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    //일반메서드 주입. 일반적으로 사용하지 않음.. >> 생성자 주입을 사용하므로
    /*
    @Autowired
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy){
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    */

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
