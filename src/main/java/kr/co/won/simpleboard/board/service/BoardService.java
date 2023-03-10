package kr.co.won.simpleboard.board.service;

import kr.co.won.simpleboard.board.dto.form.BoardForm;
import kr.co.won.simpleboard.board.dto.BoardResponseDto;
import kr.co.won.simpleboard.utils.PageDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BoardService {

    /**
     * Create Board
     */
    default BoardResponseDto.Create createBoard(BoardForm.Create form) {
        return null;
    }

    /**
     * Paging Board
     */
    default Page<BoardResponseDto.Paging> pagingBoard(PageDto pageDto) {
        return Page.empty();
    }

    /**
     * Detail Board
     */
    default BoardResponseDto.Detail detailBoard(Long boardIdx) {
        return null;
    }

    /**
     * Update Board
     */
    default BoardResponseDto.Update updateBoard(Long boardIdx, BoardForm.Update form) {
        return null;
    }

    /**
     * Delete Board
     */
    default BoardResponseDto.Delete deleteBoard(Long boardIdx) {
        return null;
    }

    /**
     * Bulk Delete Board
     */
    default List<BoardResponseDto.Delete> deleteBoard(List<Long> boardIdxes) {
        return null;
    }
}
