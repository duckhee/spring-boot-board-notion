package kr.co.won.simpleboard.user.validation;

import kr.co.won.simpleboard.user.dto.UserForm;
import kr.co.won.simpleboard.user.dto.UserRegisteredForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegisteredValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(UserRegisteredForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserRegisteredForm form = (UserRegisteredForm) target;
        log.info("get form data : {}", form);
        if (!form.getPassword().equals(form.getConfirmPassword())) {
            errors.rejectValue("password", "not.match.password", "not match password");
        }
    }
}
