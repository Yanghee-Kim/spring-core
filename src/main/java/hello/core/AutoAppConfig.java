package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
    // basePackages 를 지정하지 않으면 ComponentScan이 붙은 설정 정보 클래스의 패키지가 시작위치가 된다
    excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class) // 기존 예제 코드를 유지하기 위해 configuration 어노테이션을 제외하는 것으로
)
public class AutoAppConfig {

}
