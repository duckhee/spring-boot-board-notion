package kr.co.won.simpleboard.user.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName(value = "User Domain and User Role Domain Uint Tests")
class UserDomainTest {

    @DisplayName(value = "01. User Domain And User Role Domain Create Test")
    @Test
    void createUserDomainTests() {
        UserDomain testUser = UserDomain.builder()
                .userId("tester")
                .userEmail("test@co.kr")
                .password("testing")
                .name("tester")
                .build();
        UserRoleDomain.builder()
                .role(UserRole.USER)
                .user(testUser)
                .build();
        assertThat(testUser.getRoles().size()).isEqualTo(1);
    }

    @DisplayName(value = "01. User Domain And multi User Role Domain Create Test")
    @Test
    void createUserDomainMultiRoleTests() {
        UserDomain testUser = UserDomain.builder()
                .userId("tester")
                .userEmail("test@co.kr")
                .password("testing")
                .name("tester")
                .build();
        UserRoleDomain.builder()
                .role(UserRole.USER)
                .user(testUser)
                .build();
        UserRoleDomain.of(testUser, UserRole.ADMIN);

        assertThat(testUser.getRoles().size()).isEqualTo(2);
    }

    @DisplayName(value = "02. User Domain has Role Tests")
    @Test
    void userDomainRoleCheckMethodTests() {
        UserDomain testUser = UserDomain.builder()
                .userId("tester")
                .userEmail("test@co.kr")
                .password("testing")
                .name("tester")
                .build();
        UserRoleDomain.builder()
                .role(UserRole.USER)
                .user(testUser)
                .build();
        UserRoleDomain.of(testUser, UserRole.ADMIN);
        assertThat(testUser.hasRole(UserRole.USER)).isTrue();
        assertThat(testUser.hasRole(UserRole.MANAGER)).isFalse();
    }

    @DisplayName(value = "02. User Domain has Role Tests")
    @Test
    void userDomainRoleCheckInputMultiRoleMethodTests() {
        UserDomain testUser = UserDomain.builder()
                .userId("tester")
                .userEmail("test@co.kr")
                .password("testing")
                .name("tester")
                .build();
        UserRoleDomain.builder()
                .role(UserRole.USER)
                .user(testUser)
                .build();
        UserRoleDomain.of(testUser, UserRole.ADMIN);
        assertThat(testUser.hasRole(UserRole.MANAGER, UserRole.ADMIN)).isFalse();
    }

}