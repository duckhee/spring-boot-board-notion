package kr.co.won.simpleboard.email.impl;

import jakarta.annotation.PostConstruct;
import kr.co.won.simpleboard.email.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Profile(value = {"product"})
@Slf4j
@Primary
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @PostConstruct
    public void intiService() {
        log.info("email service : {}", EmailServiceImpl.class.getClass());
    }
}
