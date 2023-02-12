package kr.co.won.simpleboard.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

    @GetMapping(path = "/registered")
    public String userRegisteredPage() {
        return "";
    }

    @PostMapping(path = "/registered")
    public String userRegisteredDo() {
        return "";
    }
}
