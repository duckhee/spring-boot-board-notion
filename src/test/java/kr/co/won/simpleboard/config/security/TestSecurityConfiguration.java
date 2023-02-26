package kr.co.won.simpleboard.config.security;

import kr.co.won.simpleboard.auth.AuthService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@TestConfiguration
public class TestSecurityConfiguration {

    @MockBean
    private AuthService authService;
    @Order(Ordered.HIGHEST_PRECEDENCE)
    @Bean
    public SecurityFilterChain appSecurity(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .securityMatcher("/**")
                .authorizeHttpRequests()
                .requestMatchers("/boards", "/users/registered")
                .permitAll()
                .requestMatchers(HttpMethod.GET, "/boards/list", "users/verified-token", "/boards/*")
                .permitAll()
                .anyRequest().hasAnyRole("ADMIN", "MANAGER", "USER");

        httpSecurity.userDetailsService(authService);
        httpSecurity.formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .permitAll();
        httpSecurity.logout().logoutUrl("/logout")
                .logoutSuccessUrl("/login");
        return httpSecurity.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> {
            web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations().toString(), "/plugins/**", "/build/**", "/imgs/**", "/dist/**");
        };
    }
    @Bean
    @Primary
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return rawPassword.equals(encodedPassword);
            }
        };
    }

}
