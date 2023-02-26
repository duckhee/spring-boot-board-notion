package kr.co.won.simpleboard.user.controller;

import kr.co.won.simpleboard.auth.LoginUser;
import kr.co.won.simpleboard.user.domain.UserDomain;
import kr.co.won.simpleboard.user.dto.*;
import kr.co.won.simpleboard.user.service.UserService;
import kr.co.won.simpleboard.user.validation.RegisteredValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * validation
     */
    private final RegisteredValidator registeredValidator;


    @InitBinder(value = "userRegisteredForm")
    public void initRegisteredValidator(WebDataBinder dataBinder) {
        dataBinder.addValidators(registeredValidator);
    }

    @GetMapping(path = "/registered")
    public String userRegisteredPage(Model model) {
        model.addAttribute(new UserRegisteredForm());
        return "user/userRegisteredPage";
    }

    @PostMapping(path = "/registered")
    public String userRegisteredDo(@Validated UserRegisteredForm form, Errors errors, Model model, RedirectAttributes flash) {
        log.info("post registered user : {}", form);
        if (errors.hasErrors()) {
            log.info("validation error : {}", errors.toString());
            model.addAttribute(form);
            return "user/userRegisteredPage";
        }
        UserResponseDto.Registered newUser = userService.registeredUser(form);
        flash.addFlashAttribute("msg", "registered user. verified email " + newUser.email() + " send. check email");
        // TODO login page redirect
        return "redirect:/";
    }

    // TODO profile using get Spring SecuritySessionHolder
    @GetMapping(path = "/profile")
    public String userProfilePage(@LoginUser UserDomain loginUser, Model model) {
        log.info("login user : {}", loginUser);
        UserResponseDto.Profile userInfo = userService.userProfileByUserId(loginUser.getUserId());
        model.addAttribute("userInfo", userInfo);
        return "user/userProfilePage";
    }

    @GetMapping(path = "/profile/update")
    public String userInformationUpdatePage(@LoginUser UserDomain loginUser, Model model) {
        UserResponseDto.Profile profile = userService.userProfileByUserId(loginUser.getUserId());
        model.addAttribute("user", loginUser);
        model.addAttribute("userInfo", profile);
        model.addAttribute(new UserUpdateProfileForm());
        return "";
    }

    @PostMapping(path = "/profile/update")
    public String userInformationUpdateDo(@LoginUser UserDomain loginUser, @Validated UserUpdateProfileForm form, Errors errors, Model model, RedirectAttributes flash) {
        return "";
    }

    @GetMapping(path = "/forget-password")
    public String userForgetPasswordPage(Model model) {
        model.addAttribute(new UserUpdatePasswordForm());
        return "";
    }

    @PostMapping(path = "/forget-password")
    public String userForgetPasswordDo(Model model, @Validated UserUpdatePasswordForm form, Errors errors, RedirectAttributes flash) {
        return "";
    }

    @GetMapping(path = "/verified-token")
    public String verifiedTokenPage(Model model, @RequestParam(name = "token") String token, @RequestParam(name = "email") String userEmail) {
        UserResponseDto.Verified verifiedEmail = userService.verifiedTokenUser(userEmail, token);
        if (verifiedEmail.verifiedFlag()) {
            model.addAttribute("message", "success registered user.");
        } else {
            model.addAttribute("message", "email verified failed. check your email.");
        }
        return "email/EmailVerifiedPage";
    }
}
