package kr.co.won.simpleboard.email.impl;

import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import kr.co.won.simpleboard.email.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.nio.charset.StandardCharsets;


@Profile(value = {"product"})
@Slf4j
@Primary
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @PostConstruct
    public void intiService() {
        log.info("email service : {}", EmailServiceImpl.class.getClass());
    }


    private void sendHtmlEmail(String templatePath) throws MessagingException {
        Context context = new Context();
        // TODO setting context
        // create string html page
        String message = templateEngine.process(templatePath, context);
        // html template mime type setting
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        // mime setting helper
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());
        mimeMessageHelper.setText(message, true);
        javaMailSender.send(mimeMessage);

    }
}
