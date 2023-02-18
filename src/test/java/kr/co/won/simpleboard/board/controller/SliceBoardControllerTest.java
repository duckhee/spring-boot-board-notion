package kr.co.won.simpleboard.board.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.won.simpleboard.board.domain.BoardDomain;
import kr.co.won.simpleboard.board.dto.BoardForm;
import kr.co.won.simpleboard.board.dto.BoardResponseDto;
import kr.co.won.simpleboard.board.factory.BoardDomainRandomFactory;
import kr.co.won.simpleboard.board.service.BoardService;
import kr.co.won.simpleboard.config.SecurityConfiguration;
import kr.co.won.simpleboard.utils.PageDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@WebMvcTest(controllers = {
        BoardController.class
}, excludeAutoConfiguration = SecurityAutoConfiguration.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
                        SecurityConfiguration.class
                })
        })
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@DisplayName(value = "slice board Controller Tests")
@Import(value = {
        BoardDomainRandomFactory.class
})
class SliceBoardControllerTest {

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
                .andExpect(status().is3xxRedirection());
    }

    @DisplayName(value = "[GET] - [/boards/create] Board Create Page Test")
    @Test
    void boardCreatePageTests() throws Exception {
        mockMvc.perform(get("/boards/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("board/boardRegisteredPage"))
                .andExpect(model().attributeExists("boardForm"));
    }

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
                        .param("content", testForm.getContent()))
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

    @DisplayName(value = "[GET] - [/boards/{boardIdx}/modify] Board Modify Page Test")
    @Test
    void boardModifyPageTests() throws Exception {
        BoardDomain testBoard = factory.createBoard("testT", "testC");
        when(boardService.detailBoard(testBoard.getIdx())).thenReturn(BoardResponseDto.Detail.ofDomain(testBoard));
        mockMvc.perform(get("/boards/{idx}/modify", testBoard.getIdx()))
                .andExpect(status().isOk())
                .andExpect(view().name("board/boardModifyPage"))
                .andExpect(model().attributeExists("board", "boardForm"));
    }

    @DisplayName(value = "[POST] - [/boards/{boardIdx}/modify] Board Modify Do Test => Success")
    @Test
    void boardModifyDoTests_SUCCESS() throws Exception {
        BoardDomain testBoard = factory.createBoard("tt", "cc");
        BoardForm.Update updateForm = BoardForm.Update.builder()
                .title("updateTitle")
                .content("updateContent")
                .build();
        BoardResponseDto.Update updateResponse = BoardResponseDto.Update.ofDomain(testBoard);
        when(boardService.updateBoard(testBoard.getIdx(), updateForm)).thenReturn(updateResponse);
        mockMvc.perform(post("/boards/{idx}/modify", testBoard.getIdx())
                        .param("title", updateForm.getTitle())
                        .param("content", updateForm.getContent()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/boards/" + testBoard.getIdx()))
                .andExpect(flash().attributeExists("msg"));
    }

    @DisplayName(value = "[POST] - [/boards/{boardIdx}/delete] Board Delete Do Test => Success")
    @Test
    void boardDeleteDoTest_SUCCESS() throws Exception {
        BoardDomain testBoard = factory.createBoard("tt", "cc");
        when(boardService.deleteBoard(testBoard.getIdx())).thenReturn(BoardResponseDto.Delete.ofDomain(testBoard));
        mockMvc.perform(post("/boards/{idx}/delete", testBoard.getIdx()))
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attributeExists("msg"));
    }

    @DisplayName(value = "[POST] - [/boards/bulk-delete] Board Delete Bulk Test => Success")
    @Test
    void boardBulkDeleteTest_SUCCES() throws Exception {
        List<Long> testBoardIdxes = Arrays.asList(1L, 2L, 3L);
        List<BoardResponseDto.Delete> response = testBoardIdxes.stream().map(factory::createBoard)
                .map(BoardResponseDto.Delete::ofDomain).collect(Collectors.toList());
        when(boardService.deleteBoard(testBoardIdxes)).thenReturn(response);
        mockMvc.perform(post("/boards/bulk-delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testBoardIdxes)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/boards/list"));
    }

}