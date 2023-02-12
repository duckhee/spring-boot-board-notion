package kr.co.won.simpleboard.user.controller;

import kr.co.won.simpleboard.board.factory.BoardDomainRandomFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;

@Slf4j
@WebMvcTest(controllers = {
        UserController.class
})

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@DisplayName(value = "slice user Controller Tests")
class SliceUserControllerTest {

}