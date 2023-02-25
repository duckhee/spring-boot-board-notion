package kr.co.won.simpleboard.config;

import kr.co.won.simpleboard.auth.AuthService;
import kr.co.won.simpleboard.config.security.TestSecurityConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Inherited
@Import(value = {
        TestSecurityConfiguration.class,
//        SecurityConfiguration.class
})
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SliceSecurityConfig {
}
