package kr.co.won.simpleboard.board.controller;

import kr.co.won.simpleboard.board.dto.BoardForm;
import kr.co.won.simpleboard.utils.PageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(path = "/boards")
@RequiredArgsConstructor
public class BoardController {

    @GetMapping
    public String boardPagingPageRedirect() {
        return "redirect:/boards/list";
    }

    @GetMapping(path = "/create")
    public String boardRegisteredPage(Model model) {
        model.addAttribute(new BoardForm());
        return "board/boardRegisteredPage";
    }

    @PostMapping(path = "/create")
    public String boardRegisteredDo(@Validated BoardForm form, Errors errors, Model model, RedirectAttributes redirect) {
        if (errors.hasErrors()) {
            return "board/boardRegisteredPage";
        }
        return "redirect:/boards";
    }

    @GetMapping(path = "/list")
    public String boardPagingPage(PageDto pageDto) {
        return "board/boardListPage";
    }

}
