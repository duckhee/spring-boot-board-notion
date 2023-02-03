package kr.co.won.simpleboard.board.service;

import kr.co.won.simpleboard.board.domain.BoardDomain;
import kr.co.won.simpleboard.board.dto.BoardForm;
import kr.co.won.simpleboard.board.dto.BoardResponseDto;
import kr.co.won.simpleboard.board.persistence.BoardPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardPersistence boardPersistence;

    @Transactional
    @Override
    public BoardResponseDto.Create createBoard(BoardForm form) {
        BoardDomain newBoard = BoardDomain.of(form.getTitle(), form.getContent());
        BoardDomain savedBoard = boardPersistence.save(newBoard);
        return BoardResponseDto.Create.ofDomain(savedBoard);
    }
}
