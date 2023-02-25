package kr.co.won.simpleboard.config;

import kr.co.won.simpleboard.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final AuthService authService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

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
}
