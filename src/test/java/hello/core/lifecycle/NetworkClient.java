package hello.core.lifecycle;



public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);

        }

        public void setUrl(String url) {
            this.url = url;
        }

        //서비스 시작시 호출

        public void connect(){
            System.out.println("connect: " + url);

        }

        public void call(String message){
            System.out.println("call  : " + url + " message = " + message);

        }

        //서비스 종료시 호출
        public void disconnect(){
            System.out.println("close " +url);
        }


        //@InitializingBean 상속 메소드 (초기화 인터페이스) : 의존주입이 끝나면 실행하는 메소드

        public void init() {
            System.out.println("NetworkClient.init");
            connect();
            call("초기화 연결 메시지");
    }

    //@DisposableBean 상속 메소드(소멸 인터페이스) : 종료될때실행

    public void close() {
        disconnect();
    }
    /*
    생성자 호출, url = null << 1. 생성자 호출시에는 null
    NetworkClient.afterPropertiesSet  <<2. 의존관계 주입 후 초기화까지 완료되었다면, afterPropertiesSet이 실행
    connect: http://hello-spring.dev <<3. url이 셋팅된걸 볼수있음
    call  : http://hello-spring.dev message = 초기화 연결 메시지
    13:30:48.345 [main] DEBUG org.springframework.context.annotation.AnnotationConfigApplicationContext - Closing org.springframework.context.annotation.AnnotationConfigApplicationContext@69997e9d, started on Tue Jun 06 13:30:48 KST 2023
    close http://hello-spring.dev
     */
}
