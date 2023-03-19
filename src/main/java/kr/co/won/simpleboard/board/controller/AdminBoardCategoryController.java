package kr.co.won.simpleboard.board.controller;

import kr.co.won.simpleboard.auth.LoginUser;
import kr.co.won.simpleboard.user.domain.UserDomain;
import kr.co.won.simpleboard.utils.PageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/admin/board-categories")
@RequiredArgsConstructor
public class AdminBoardCategoryController {

    @GetMapping
    public String boardCategoriesMainPage(){
        return "redirect:/admin/board-categories/list";
    }

    @GetMapping(path = "/create")
    public String boardCategoriesRegisteredPage(@LoginUser UserDomain user, Model model){
        return "board/category/categoryRegisteredPage";
    }

    @PostMapping(path = "/create")
    public String boardCategoriesRegisteredDo(){
        return "redirect:/admin/board-categories/details";
    }

    @GetMapping(path = "/list")
    public String boardCategoriesListPage(PageDto pageDto, Model model){
        return "board/category/categoryListPage";
    }

    @GetMapping(path = "/details/{categoryCode}")
    public String boardCategoriesDetailPage(@PathVariable(name = "categoryCode") String categoryCode){
        return "board/category/categoryDetailPage";
    }

    @GetMapping(path = "/update/{categoryCode}")
    public String boardCategoriesUpdatePage(@PathVariable(name = "categoryCode") String categoryCode, Model model){
        return "board/category/categoryModifyPage";
    }

    @PostMapping(path = "/update/{categoryCode}")
    public String boardCategoriesUpdateDo(@PathVariable(name = "categoryCode") String categoryCode, Model model){
        return "redirect:/admin/board-categories/details";
    }

}
