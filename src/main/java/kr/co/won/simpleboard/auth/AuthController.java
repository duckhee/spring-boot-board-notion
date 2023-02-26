package kr.co.won.simpleboard.auth;

import kr.co.won.simpleboard.user.dto.UserUpdatePasswordForm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    @GetMapping(path = "/login")
    public String loginPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            return "redirect:/";
        }
        return "loginPage";
    }

    // TODO
    @GetMapping(path = "/forget-password")
    public String userForgetPasswordPage(Model model) {
        model.addAttribute(new UserUpdatePasswordForm());
        return "";
    }

    // TODO
    @PostMapping(path = "/forget-password")
    public String userForgetPasswordDo(Model model, @Validated UserUpdatePasswordForm form, Errors errors, RedirectAttributes flash) {
        return "";
    }

    // TODO
    @GetMapping(path = "/forget-password/verified/{verifiedId}")
    public String userForgetPasswordChangeTokenPage(@RequestParam String token, @PathVariable(name = "verifiedId") String verifiedId) {
        return "";
    }
}
