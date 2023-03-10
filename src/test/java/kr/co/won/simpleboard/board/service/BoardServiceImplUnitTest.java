package kr.co.won.simpleboard.board.service;


import kr.co.won.simpleboard.board.domain.BoardDomain;
import kr.co.won.simpleboard.board.dto.form.BoardForm;
import kr.co.won.simpleboard.board.dto.BoardResponseDto;
import kr.co.won.simpleboard.board.factory.BoardDomainRandomFactory;
import kr.co.won.simpleboard.board.persistence.BoardPersistence;
import kr.co.won.simpleboard.utils.PageDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
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
        verify(boardPersistence).save(newBoard);
    }

    @DisplayName(value = "[BoardService] paging Board Test => Success")
    @Test
    void pagingBoardServiceTests_SUCCESS() {
        PageDto pageDto = new PageDto();
        Pageable pageable = pageDto.makePageable(0, "createdAt");
        when(boardPersistence.pagingBoard(pageDto, pageable)).thenReturn(Page.empty());
        Page<BoardResponseDto.Paging> result = boardService.pagingBoard(pageDto);
        Assertions.assertEquals(result.getSize(), Page.empty().getSize());
        verify(boardPersistence).pagingBoard(pageDto, pageable);
    }

    @DisplayName(value = "[BoardService] detail Board Test => Success")
    @Test
    void detailBoardServiceTests_SUCCESS() {
        BoardDomain board = factory.createBoard("test title", "testing content");
        when(boardPersistence.findByIdx(board.getIdx())).thenReturn(Optional.of(board));

        assertDoesNotThrow(() -> {
            BoardResponseDto.Detail result = boardService.detailBoard(board.getIdx());

            Assertions.assertEquals(board.getTitle(), result.title());
            Assertions.assertEquals(board.getContent(), result.content());
            Assertions.assertEquals(board.getIdx(), result.boardIdx());
            Assertions.assertEquals(board.getCreatedAt(), result.createdAt());
            Assertions.assertEquals(board.getUpdatedAt(), result.updatedAt());
        });

        verify(boardPersistence).findByIdx(board.getIdx());
    }

    @DisplayName(value = "[BoardService] update Board Test => Success")
    @Test
    void updateBoardServiceTests_SUCCESS() {
        BoardDomain board = factory.createBoard("test title", "test content");
        BoardForm.Update updateForm = BoardForm.Update.builder()
                .title("tt")
                .content("cc")
                .build();
        when(boardPersistence.findByIdx(board.getIdx())).thenReturn(Optional.of(board));
        BoardResponseDto.Update updateBoard = boardService.updateBoard(board.getIdx(), updateForm);

        Assertions.assertEquals(updateBoard.boardIdx(), board.getIdx());
        verify(boardPersistence).findByIdx(board.getIdx());
    }

    @DisplayName(value = "[BoardService] delete Board Test => Success")
    @Test
    void deleteBoardServiceTests_SUCCESS() {
        BoardDomain board = factory.createBoard("test title", "test content");
        when(boardPersistence.findByIdx(board.getIdx())).thenReturn(Optional.of(board));

        assertDoesNotThrow(() -> {
            BoardResponseDto.Delete deleteResponse = boardService.deleteBoard(board.getIdx());
            assertThat(deleteResponse.boardIdx().equals(board.getIdx()));
        });

        verify(boardPersistence).findByIdx(board.getIdx());
    }

    @DisplayName(value = "[BoardService] delete Bulk Board Test => SUCCESS")
    @Test
    void deleteBulkBoardServiceTests_SUCCESS() {
        BoardDomain board = factory.createBoard("t", "c");
        BoardDomain board1 = factory.createBoard("a", "b");
        BoardDomain board2 = factory.createBoard("q", "w");
        List<Long> deleteIdxes = Arrays.asList(board2.getIdx(), board1.getIdx(), board.getIdx());
        when(boardPersistence.findByIdxIn(deleteIdxes)).thenReturn(Arrays.asList(board2, board1, board));
        List<BoardResponseDto.Delete> resultResponse = boardService.deleteBoard(deleteIdxes);
        List<Long> responseIdxes = resultResponse.stream().mapToLong(BoardResponseDto.Delete::boardIdx).boxed().collect(Collectors.toList());

        assertThat(responseIdxes).containsAll(deleteIdxes);
        verify(boardPersistence).findByIdxIn(deleteIdxes);
        verify(boardPersistence).deleteAllInBatch(Arrays.asList(board2, board1, board));
    }

}