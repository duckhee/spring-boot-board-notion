package kr.co.won.simpleboard.board.persistence;

import kr.co.won.simpleboard.board.domain.BoardDomain;
import kr.co.won.simpleboard.board.dto.BoardResponseDto;
import kr.co.won.simpleboard.utils.PageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardPersistenceExtension {

    /**
     * Paging Board
     */
    Page<BoardResponseDto.Paging> pagingBoard(PageDto pageDto, Pageable pageable);

    List<BoardDomain> testingBoard();

}
