package kr.co.won.simpleboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

@SpringBootApplication
public class NotionSimpleBoardApplication {


    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(NotionSimpleBoardApplication.class);
        application.addListeners(new ApplicationPidFileWriter());
        application.run(args);

    }

}
