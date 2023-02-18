package kr.co.won.simpleboard.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.won.simpleboard.board.factory.BoardDomainRandomFactory;
import kr.co.won.simpleboard.config.SecurityConfiguration;
import kr.co.won.simpleboard.user.domain.UserDomain;
import kr.co.won.simpleboard.user.domain.UserRole;
import kr.co.won.simpleboard.user.dto.UserForm;
import kr.co.won.simpleboard.user.dto.UserRegisteredForm;
import kr.co.won.simpleboard.user.dto.UserResponseDto;
import kr.co.won.simpleboard.user.factory.UserDomainRandomFactory;
import kr.co.won.simpleboard.user.persistence.UserPersistence;
import kr.co.won.simpleboard.user.service.UserService;
import kr.co.won.simpleboard.user.service.impl.UserServiceImpl;
import kr.co.won.simpleboard.user.validation.RegisteredValidator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@Slf4j
@WebMvcTest(controllers = {
        UserController.class
}, excludeAutoConfiguration = SecurityAutoConfiguration.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
                        SecurityConfiguration.class
                })
        })
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@DisplayName(value = "slice user Controller Tests")
@Import(value = {
        UserDomainRandomFactory.class,
        RegisteredValidator.class
})
class SliceUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserDomainRandomFactory factory;

    @Autowired
    private RegisteredValidator registeredValidator;

    private ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private UserService userService;

    // this is validator mock
    @MockBean
    private UserPersistence userPersistence;

    @DisplayName(value = "[GET] - [/users/registered] User Registered Page Test")
    @Test
    void userRegisteredPageTests() throws Exception {
        mockMvc.perform(get("/users/registered"))
                .andExpect(view().name("user/userRegisteredPage"))
                .andExpect(model().attributeExists("userRegisteredForm"))
                .andExpect(status().isOk());
    }

    @DisplayName(value = "[POST] - [/users/registered] User Registered Do Test => Success")
    @Test
    void userRegisteredDoTests_SUCCESS() throws Exception {
        UserRegisteredForm userForm = UserRegisteredForm.builder()
                .userEmail("tester@co.kr")
                .userId("tester")
                .name("tester")
                .password("won123123")
                .confirmPassword("won123123")
                .build();
        UserDomain serviceInputUser = factory.allRandomUser(userForm.getUserId(), userForm.getUserEmail(), userForm.getName(), userForm.getPassword(), UserRole.USER);
        UserDomain savedUser = factory.allRandomUser(1l, userForm.getUserId(), userForm.getUserEmail(), userForm.getName(), userForm.getPassword(), UserRole.USER);
        UserResponseDto.Registered response = UserResponseDto.Registered.ofDomain(savedUser);
        when(userService.registeredUser(userForm)).thenReturn(response);
        mockMvc.perform(post("/users/registered")
                        .param("userEmail", userForm.getUserEmail())
                        .param("userId", userForm.getUserId())
                        .param("name", userForm.getName())
                        .param("password", userForm.getPassword())
                        .param("confirmPassword", userForm.getConfirmPassword()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andExpect(flash().attributeExists("msg"));
        verify(userService).registeredUser(userForm);

    }

}