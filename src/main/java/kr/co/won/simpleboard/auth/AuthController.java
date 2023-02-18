package kr.co.won.simpleboard.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping(path = "/login")
    public String loginPage() {
        return "loginPage";
    }
}
