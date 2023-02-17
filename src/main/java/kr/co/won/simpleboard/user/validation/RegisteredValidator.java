package kr.co.won.simpleboard.user.validation;

import kr.co.won.simpleboard.user.dto.UserForm;
import kr.co.won.simpleboard.user.dto.UserRegisteredForm;
import kr.co.won.simpleboard.user.persistence.UserPersistence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegisteredValidator implements Validator {

    private final UserPersistence userPersistence;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(UserRegisteredForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserRegisteredForm form = (UserRegisteredForm) target;
        log.info("get form data : {}", form);
        if (userPersistence.existsByEmail(form.getUserEmail())) {
            errors.rejectValue("userEmail", "registered.email", "already registered email");
        }
        if (userPersistence.existsByUserId(form.getUserId())) {
            errors.rejectValue("userId", "registered.userId", "already registered Id");
        }
        if (!form.getPassword().equals(form.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "not.match.password", "not match password");
        }
    }
}
