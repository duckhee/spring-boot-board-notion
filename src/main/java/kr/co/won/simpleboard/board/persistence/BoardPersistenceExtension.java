package kr.co.won.simpleboard.board.persistence;

import kr.co.won.simpleboard.board.dto.BoardResponseDto;
import kr.co.won.simpleboard.utils.PageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardPersistenceExtension {

    /**
     * Paging Board
     */
    Page<BoardResponseDto.Paging> pagingBoard(PageDto pageDto, Pageable pageable);

    /** Paging Board all */
    Page<BoardResponseDto.Paging> pagingBoardAll(PageDto pageDto, Pageable pageable);
}
