package kr.co.won.simpleboard.board.service;

import kr.co.won.simpleboard.board.dto.BoardForm;
import kr.co.won.simpleboard.board.dto.BoardResponseDto;

public interface BoardService {

    /**
     * Create Board
     */
    default BoardResponseDto.Create createBoard(BoardForm form) {
        return null;
    }

}
