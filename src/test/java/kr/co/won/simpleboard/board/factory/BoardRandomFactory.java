package kr.co.won.simpleboard.board.factory;


import kr.co.won.simpleboard.board.domain.BoardDomain;
import kr.co.won.simpleboard.board.persistence.BoardPersistence;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.TestComponent;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@TestComponent
public class BoardRandomFactory {

    private final BoardPersistence boardPersistence;

    public BoardRandomFactory(BoardPersistence boardPersistence) {
        this.boardPersistence = boardPersistence;
    }

    public BoardDomain createBoard(String title, String content) {
        BoardDomain board = BoardDomain.builder()
                .title(title)
                .content(content)
                .build();
        return boardPersistence.save(board);
    }

    public List<BoardDomain> createBoard(String title, String content, int loopNumber) {
        List<BoardDomain> boards = new ArrayList<>();
        for (int i = 0; i < loopNumber; i++) {
            boards.add(BoardDomain.builder()
                    .title(title + i)
                    .content(content + i)
                    .build());
        }
        return boardPersistence.saveAllAndFlush(boards);
    }


}
