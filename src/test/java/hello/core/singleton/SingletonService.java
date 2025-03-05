package hello.core.singleton;

public class SingletonService {

    // static이기 때문에 위 class 레벨에만 존재하게 됨
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return  instance;
    }

    private  SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

//    public static void main(String[] args) {
//        SingletonService singletonService1 = new SingletonService();
//        SingletonService singletonService2 = new SingletonService();
//    }
}
