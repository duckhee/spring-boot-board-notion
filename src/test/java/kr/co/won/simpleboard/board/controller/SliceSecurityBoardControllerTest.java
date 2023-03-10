package kr.co.won.simpleboard.board.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.won.simpleboard.board.domain.BoardDomain;
import kr.co.won.simpleboard.board.dto.form.BoardForm;
import kr.co.won.simpleboard.board.dto.BoardResponseDto;
import kr.co.won.simpleboard.board.factory.BoardDomainRandomFactory;
import kr.co.won.simpleboard.board.service.BoardService;
import kr.co.won.simpleboard.config.SliceSecurityConfig;
import kr.co.won.simpleboard.utils.PageDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = {
        BoardController.class
})
@Import(value = {
        BoardDomainRandomFactory.class
})
@SliceSecurityConfig
@DisplayName(value = "security Board Slice Tests")
class SliceSecurityBoardControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    BoardDomainRandomFactory factory;

    private ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private BoardService boardService;

    @DisplayName(value = "[GET] - [/boards] Board Page Redirect List Page Test")
    @Test
    void boardRootPageTests() throws Exception {
        mockMvc.perform(get("/boards"))
                .andExpect(redirectedUrl("/boards/list"))
                .andExpect(status().is3xxRedirection())
                .andDo(print());
    }


    @WithMockUser
    @DisplayName(value = "[POST] - [/boards/create] Board Create Do Test => Success")
    @Test
    void boardCreateDoTests_SUCCESS() throws Exception {
        BoardForm.Create testForm = BoardForm.Create.builder()
                .title("test")
                .content("content")
                .build();
        BoardDomain testBoard = factory.createBoard(testForm.getTitle(), testForm.getContent());
        when(boardService.createBoard(testForm)).thenReturn(BoardResponseDto.Create.ofDomain(testBoard));
        mockMvc.perform(post("/boards/create")
                        .param("title", testForm.getTitle())
                        .param("content", testForm.getContent())
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/boards/" + testBoard.getIdx()))
                .andExpect(flash().attributeExists("msg"));
    }

    @DisplayName(value = "[GET] - [/boards/list] Board List Page Test")
    @Test
    void boardListPageTests() throws Exception {
        when(boardService.pagingBoard(any())).thenReturn(Page.empty(new PageDto().makePageable(1, "idx")));
        mockMvc.perform(get("/boards/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("board/boardListPage"))
                .andExpect(model().attributeExists("pageDto", "pagingPage"));
    }

    @DisplayName(value = "[GET] - [/boards/{boardIdx}] Board Detail Page Test")
    @Test
    void boardDetailPageTests() throws Exception {
        BoardDomain testBoard = factory.createBoard("testT", "testC");
        when(boardService.detailBoard(testBoard.getIdx())).thenReturn(BoardResponseDto.Detail.ofDomain(testBoard));
        mockMvc.perform(get("/boards/{idx}", testBoard.getIdx()))
                .andExpect(status().isOk())
                .andExpect(view().name("board/boardDetailPage"))
                .andExpect(model().attributeExists("board", "pageDto"));
    }

}