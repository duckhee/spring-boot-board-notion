package kr.co.won.simpleboard.board.service;

import kr.co.won.simpleboard.board.domain.BoardDomain;
import kr.co.won.simpleboard.board.dto.BoardForm;
import kr.co.won.simpleboard.board.dto.BoardResponseDto;
import kr.co.won.simpleboard.board.exception.BoardErrorCode;
import kr.co.won.simpleboard.board.exception.BoardException;
import kr.co.won.simpleboard.board.persistence.BoardPersistence;
import kr.co.won.simpleboard.utils.PageDto;
import kr.co.won.simpleboard.utils.PageMaker;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardPersistence boardPersistence;

    @Transactional
    @Override
    public BoardResponseDto.Create createBoard(BoardForm.Create form) {
        BoardDomain newBoard = BoardDomain.of(form.getTitle(), form.getContent());
        BoardDomain savedBoard = boardPersistence.save(newBoard);
        return BoardResponseDto.Create.ofDomain(savedBoard);
    }

    @Override
    public Page<BoardResponseDto.Paging> pagingBoard(PageDto pageDto) {
        Pageable pageable = pageDto.makePageable(0, "createdAt");
        Page<BoardResponseDto.Paging> pagingPage = boardPersistence.pagingBoard(pageDto, pageable);
        return pagingPage;
    }

    @Override
    public BoardResponseDto.Detail detailBoard(Long boardIdx) {
        BoardDomain findBoard = boardPersistence.findByIdx(boardIdx).orElseThrow(() ->
                // TODO custom exception
                new IllegalArgumentException());
        return BoardResponseDto.Detail.ofDomain(findBoard);
    }

    @Transactional
    @Override
    public BoardResponseDto.Update updateBoard(Long boardIdx, BoardForm.Update form) {
        BoardDomain findBoard = boardPersistence.findByIdx(boardIdx).orElseThrow(() ->
                new BoardException(BoardErrorCode.BOARD_NOT_FOUND));
        // TODO: update hard
        if (Objects.isNull(form)) {
            if (!form.getTitle().isBlank()) {
                findBoard.setTitle(form.getTitle());
            }
            if (!form.getContent().isBlank()) {
                findBoard.setContent(form.getContent());
            }
        }

        return BoardResponseDto.Update.ofDomain(findBoard);
    }
}
