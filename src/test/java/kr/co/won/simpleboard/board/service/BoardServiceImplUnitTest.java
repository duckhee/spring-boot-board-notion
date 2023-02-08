package kr.co.won.simpleboard.board.service;


import kr.co.won.simpleboard.board.domain.BoardDomain;
import kr.co.won.simpleboard.board.dto.BoardForm;
import kr.co.won.simpleboard.board.dto.BoardResponseDto;
import kr.co.won.simpleboard.board.factory.BoardDomainRandomFactory;
import kr.co.won.simpleboard.board.persistence.BoardPersistence;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
@DisplayName(value = "Board Service Unit Tests")
@Import(value = {
        BoardDomainRandomFactory.class
})
class BoardServiceImplUnitTest {

    @Mock
    private BoardPersistence boardPersistence;


    @InjectMocks
    private BoardServiceImpl boardService;
    private BoardDomainRandomFactory factory = new BoardDomainRandomFactory();

    @DisplayName(value = "[BoardService] create Board Tests => Success")
    @Test
    void creatBoardServiceTests_SUCCESS() {
        BoardForm.Create form = BoardForm.Create.builder()
                .title("title")
                .content("content")
                .build();
        BoardDomain newBoard = factory.createBoard(null, form.getTitle(), form.getContent());
        BoardDomain testBoard = factory.createBoard(form.getTitle(), form.getContent());
        when(boardPersistence.save(newBoard)).thenReturn(testBoard);
        BoardResponseDto.Create savedBoardResponse = boardService.createBoard(form);
        assertEquals(savedBoardResponse.title(), form.getTitle());
        assertNotNull(savedBoardResponse.boardIdx());

    }
}