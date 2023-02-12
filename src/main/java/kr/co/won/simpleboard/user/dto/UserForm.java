package kr.co.won.simpleboard.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UserForm {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Registered {
        @NotBlank(message = "required user id")
        private String userId;

        @Email(message = "not email format")
        @NotBlank(message = "required user email")
        private String userEmail;

        @NotBlank(message = "required user name")
        private String name;

        @NotBlank(message = "required user password")
        @Pattern(regexp = "^[0-9|a-z|A-Z|!|@|#|$|%|\\^|&]+$", message = "password pattern 0~9 a~z A~Z !@#$%^&")
        private String password;

        @NotBlank(message = "required user confirm password")
        @Pattern(regexp = "^[0-9|a-z|A-Z|!|@|#|$|%|\\^|&]+$", message = "password pattern 0~9 a~z A~Z !@#$%^&")
        private String confirmPassword;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update {
        private String name;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdatePassword {
        @NotBlank(message = "required user password")
        @Pattern(regexp = "^[0-9|a-z|A-Z|!|@|#|$|%|\\^|&]+$", message = "password pattern 0~9 a~z A~Z !@#$%^&")
        private String password;

        @NotBlank(message = "required user confirm password")
        @Pattern(regexp = "^[0-9|a-z|A-Z|!|@|#|$|%|\\^|&]+$", message = "password pattern 0~9 a~z A~Z !@#$%^&")
        private String confirmPassword;
    }

}
