package kr.co.won.simpleboard.board.controller;

import kr.co.won.simpleboard.board.dto.BoardForm;
import kr.co.won.simpleboard.board.dto.BoardResponseDto;
import kr.co.won.simpleboard.board.exception.BoardErrorCode;
import kr.co.won.simpleboard.board.exception.BoardException;
import kr.co.won.simpleboard.board.service.BoardService;
import kr.co.won.simpleboard.utils.PageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.LongPredicate;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping(path = "/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public String boardPagingPageRedirect() {
        return "redirect:/boards/list";
    }

    @GetMapping(path = "/create")
    public String boardRegisteredPage(Model model) {
        log.info("board create page");
        model.addAttribute("boardForm", new BoardForm.Create());
        return "board/boardRegisteredPage";
    }

    @PostMapping(path = "/create")
    public String boardRegisteredDo(@Validated BoardForm.Create form, Errors errors, Model model, RedirectAttributes flash) {
        log.info("board create do");
        if (errors.hasErrors()) {
            log.info("validation error : {}", form.toString());
            model.addAttribute("boardForm", form);
            return "board/boardRegisteredPage";
        }
        BoardResponseDto.Create savedBoard = boardService.createBoard(form);
        log.info("saved board ::: {}", savedBoard);
        flash.addFlashAttribute("msg", "create board success");
        return "redirect:/boards/" + savedBoard.boardIdx();
    }

    @GetMapping(path = "/list")
    public String boardPagingPage(PageDto pageDto, Model model) {
        log.info("page dto : {}", pageDto);
        Page<BoardResponseDto.Paging> pagingPage = boardService.pagingBoard(pageDto);
        log.info("paging result = {}", pagingPage);
        model.addAttribute("pagingPage", pagingPage);
        return "board/boardListPage";
    }

    @GetMapping(path = "/{boardIdx}")
    public String boardDetailPage(@PathVariable(name = "boardIdx") Long idx, Model model) {
        BoardResponseDto.Detail findBoard = boardService.detailBoard(idx);
        model.addAttribute("board", findBoard);
        return "board/boardDetailPage";
    }

    @GetMapping(path = "/{boardIdx}/modify")
    public String boardUpdatePage(@PathVariable(name = "boardIdx") Long boardIdx, Model model) {
        BoardResponseDto.Detail findBoard = boardService.detailBoard(boardIdx);
        // TODO Check
        BoardForm.Update updateForm = new BoardForm.Update();
        updateForm.setTitle(findBoard.title());
        updateForm.setContent(findBoard.content());
        model.addAttribute("boardForm", updateForm);
        return "board/boardModifyPage";
    }

    @PostMapping(path = "/{boardIdx}/modify")
    public String boardUpdateDo(@PathVariable(name = "boardIdx") Long boardIdx, Model model, @Validated BoardForm.Update form, Errors errors, RedirectAttributes flash) {
        if (errors.hasErrors()) {
            model.addAttribute("boardForm", form);
            return "board/boardModifyPage";
        }
        BoardResponseDto.Update update = boardService.updateBoard(boardIdx, form);
        flash.addFlashAttribute("msg", update.boardIdx() + " blog update");
        return "redirect:/boards/{boardIdx}";
    }

    @PostMapping(path = "/{boardIdx}/delete")
    public String boardDeleteOneDo(@PathVariable(name = "boardIdx") Long boardIdx, RedirectAttributes flash) {
        BoardResponseDto.Delete delete = boardService.deleteBoard(boardIdx);
        // TODO check
        if (Objects.isNull(delete)) {
            flash.addFlashAttribute("error", boardIdx);
            throw new BoardException(BoardErrorCode.BOARD_DELETE_FAILED);
        }
        flash.addFlashAttribute("msg", "delete success");
        return "redirect:/boards/list";
    }

    // TODO bulk delete do
    @PostMapping(path = "/bulk-delete")
    public String boardDeleteBulkDo(@RequestBody List<Long> idxes, RedirectAttributes flash) {
        List<BoardResponseDto.Delete> deleteResults = boardService.deleteBoard(idxes);
        // TODO checking
        List<Long> results = deleteResults.stream().map(BoardResponseDto.Delete::boardIdx).collect(Collectors.toList());
        if (idxes.stream().allMatch(value -> results.contains(value))) {
            log.info("all delete");
        } else {
            log.info("not all delete");
        }

        return "redirect:/boards/list";
    }

}
