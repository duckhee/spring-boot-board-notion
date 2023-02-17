package kr.co.won.simpleboard.user.controller;

import kr.co.won.simpleboard.user.dto.UserForm;
import kr.co.won.simpleboard.user.dto.UserRegisteredForm;
import kr.co.won.simpleboard.user.dto.UserResponseDto;
import kr.co.won.simpleboard.user.service.UserService;
import kr.co.won.simpleboard.user.validation.RegisteredValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public String userProfilePage(Model model) {

        return "user/userProfilePage";
    }

    @GetMapping(path = "/verified-token")
    public String verifiedTokenPage(Model model, @RequestParam(name = "token") String token, @RequestParam(name = "email") String userEmail, RedirectAttributes flash) {

        flash.addAttribute("msg", "success registered user.");
        return "redirect:/";
    }
}
