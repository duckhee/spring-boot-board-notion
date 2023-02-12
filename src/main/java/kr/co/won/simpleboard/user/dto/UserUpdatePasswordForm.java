package kr.co.won.simpleboard.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdatePasswordForm {
    @NotBlank(message = "required user password")
    @Pattern(regexp = "^[0-9|a-z|A-Z|!|@|#|$|%|\\^|&]+$", message = "password pattern 0~9 a~z A~Z !@#$%^&")
    private String password;

    @NotBlank(message = "required user confirm password")
    @Pattern(regexp = "^[0-9|a-z|A-Z|!|@|#|$|%|\\^|&]+$", message = "password pattern 0~9 a~z A~Z !@#$%^&")
    private String confirmPassword;
}
