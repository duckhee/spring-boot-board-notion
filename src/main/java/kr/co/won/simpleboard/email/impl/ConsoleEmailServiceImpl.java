package kr.co.won.simpleboard.email.impl;

import jakarta.annotation.PostConstruct;
import kr.co.won.simpleboard.email.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile(value = {
        "local"
})
@Slf4j
@Service
@RequiredArgsConstructor
public class ConsoleEmailServiceImpl implements EmailService {

    @PostConstruct
    public void intiService() {
        log.info("email service : {}", ConsoleEmailServiceImpl.class.getClass());
    }
}
