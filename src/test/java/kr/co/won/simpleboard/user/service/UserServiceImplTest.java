package kr.co.won.simpleboard.user.service;

import kr.co.won.simpleboard.user.domain.UserDomain;
import kr.co.won.simpleboard.user.dto.UserForm;
import kr.co.won.simpleboard.user.dto.UserRegisteredForm;
import kr.co.won.simpleboard.user.dto.UserResponseDto;
import kr.co.won.simpleboard.user.factory.UserDomainRandomFactory;
import kr.co.won.simpleboard.user.persistence.UserPersistence;
import kr.co.won.simpleboard.user.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Import;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
@DisplayName(value = "User Service Unit Tests")
@Import(value = {
        UserDomainRandomFactory.class
})
class UserServiceImplTest {

    @Mock
    private UserPersistence userPersistence;

    @InjectMocks
    private UserServiceImpl userService;

    private UserDomainRandomFactory factory = new UserDomainRandomFactory();

    @DisplayName(value = "[UserService] registered user Tests => Success")
    @Test
    void createUserServiceTests_SUCCESS() {
        UserRegisteredForm form = UserRegisteredForm.builder()
                .userId("tester")
                .name("tester")
                .userEmail("tester@co.kr")
                .password("1234")
                .confirmPassword("1234")
                .build();

        UserDomain testUser = factory.allRandomUser(null, form.getUserId(), form.getUserEmail(), form.getName(), form.getPassword());
        UserDomain savedUser = factory.allRandomUser(1l, form.getUserId(), form.getUserEmail(), form.getName(), form.getPassword());
        when(userPersistence.save(testUser)).thenReturn(savedUser);
        UserResponseDto.Registered registeredUser = userService.registeredUser(form);
        Assertions.assertEquals(registeredUser.userId(), form.getUserId());
        Assertions.assertNotNull(registeredUser.userIdx());
        verify(userPersistence).save(testUser);

    }

}