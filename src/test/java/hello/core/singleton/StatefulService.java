package hello.core.singleton;

public class StatefulService {
/*
    private int price; //상태를 유지하는 필드 10000 >> 20000 으로 되버림

    public void order(String name, int price){
        System.out.println("name = " + name + " price ="+price);
        this.price = price; //여기가 문제
    }
    public int getPrice(){
        return price;
    }

 */

    //해결 코드
    //private int price; //상태를 유지하는 필드 10000 >> 20000 으로 되버림

    public int order(String name, int price){
        System.out.println("name = " + name + " price ="+price);
       //this.price = price; //여기가 문제 stateless하게 설계
        return price;
    }


}
