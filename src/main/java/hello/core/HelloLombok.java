package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//getter,setter를 자동으로 생성해주는 롬복
@Getter
@Setter
@ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("asdfg");
        String name = helloLombok.getName();
        System.out.println("helloLombok = " + helloLombok);
    }
}
