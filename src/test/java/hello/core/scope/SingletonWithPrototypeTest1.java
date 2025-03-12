package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.inject.Provider;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean = ac.getBean(PrototypeBean.class);
        prototypeBean.addCount();
        assertThat(prototypeBean.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

//    @Test
//    void singletonclientUsePrototype() {
//        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
//
//        ClientBean clientBean = ac.getBean(ClientBean.class);
//        int count1 = clientBean.logic();
//        assertThat(count1).isEqualTo(1);
//
//        ClientBean clientBean2 = ac.getBean(ClientBean.class);
//        int count2 = clientBean2.logic();
//        assertThat(count2).isEqualTo(1);
//    }

//    @Scope("singleton")
//    static class ClientBean {
//        // 근데 프로토타입 빈은 사용할 때마다 새로 생성되는 것을 의도한 것.. 밑에 처럼 할거면 싱글톤을 사용하지.. -> 싱글톤 빈과 프로토타입 빈을 함께 사용할 수 있을까? 둘의 본래의 역할을 유지하면서
//        private final PrototypeBean prototypeBean; // 생성 시점에 주입
//
//        public ClientBean(PrototypeBean prototypeBean) {
//            this.prototypeBean = prototypeBean;
//        }
//
//        public int logic() {
//            prototypeBean.addCount();
//            return prototypeBean.getCount();
//        }
//    }

    @Scope("singleton")
    static class ClientBean {
        @Autowired
//        private ObjectProvider<PrototypeBean> prototypeBeanProvider;
        private Provider<PrototypeBean> prototypeBeanProvider;

        public int logic() {
//            PrototypeBean prototypeBean = prototypeBeanProvider.getObject(); // annotationContext를 직접 호출하는 대신
            PrototypeBean prototypeBean = prototypeBeanProvider.get(); // annotationContext를 직접 호출하는 대신
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init = " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy = " + this);
        }
    }
}
