package kr.co.won.simpleboard.board.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import kr.co.won.simpleboard.board.domain.BoardDomain;
import kr.co.won.simpleboard.board.dto.BoardResponseDto;
import kr.co.won.simpleboard.board.factory.BoardDomainRandomFactory;
import kr.co.won.simpleboard.board.factory.BoardRandomFactory;
import kr.co.won.simpleboard.utils.PageDto;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.*;

@Slf4j
@Rollback
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DisplayName(value = "Board DataBase Tests")
@Import(value = {
        BoardDomainRandomFactory.class,
        BoardRandomFactory.class
})
class BoardPersistenceTest {

    @Autowired
    private BoardPersistence boardPersistence;

    @Autowired
    private BoardDomainRandomFactory factory;
    @Autowired
    private BoardRandomFactory dbFactory;

    @PersistenceContext
    private EntityManager entityManager;

    @DisplayName(value = "01. create Board Test")
    @Test
    void boardCreateTests() {
        BoardDomain testBoard = BoardDomain.builder()
                .title("testBoard")
                .content("test Content")
                .build();
        BoardDomain savedBoard = boardPersistence.save(testBoard);
        Assertions.assertThat(savedBoard.getIdx()).isNotNull();
        assertEquals(savedBoard.getTitle(), testBoard.getTitle());
        Assertions.assertThat(savedBoard.getContent()).isEqualTo(testBoard.getContent());
        Assertions.assertThat(savedBoard.getCreatedAt()).isNotNull();
        Assertions.assertThat(savedBoard.getUpdatedAt()).isNotNull();
        Assertions.assertThat(savedBoard.isDeletedFlag())
                .isEqualTo(testBoard.isDeletedFlag())
                .isEqualTo(false);
    }

    @DisplayName(value = "02. paging Board Test")
    @Test
    void boardPagingTests() {
        int pageSize = 10;
        PageDto pageDto = new PageDto();
        pageDto.setSize(pageSize);
        Pageable pageable = pageDto.makePageable(0, "createdAt");
        List<BoardDomain> board = dbFactory.createBoard("title", "content", pageSize);
        entityManager.flush();
        entityManager.clear();
        Page<BoardResponseDto.Paging> pagingBoard = boardPersistence.pagingBoard(pageDto, pageable);
        Assertions.assertThat(pagingBoard.getTotalElements()).isEqualTo(board.size());

    }

    @DisplayName(value = "04. delete Board Test")
    @Test
    void boardDeleteTests() {
        BoardDomain board = dbFactory.createBoard("testing", "testing content");
        entityManager.flush();
        entityManager.clear();
        boardPersistence.delete(board);
        Optional<BoardDomain> findBoard = boardPersistence.findByIdx(board.getIdx());

        assertThrows(NoSuchElementException.class, () -> {
            findBoard.get();
        });
//        List<BoardDomain> findBoard = entityManager.createQuery("SELECT * FROM BoardDomain board WHERE board.idx=:boardIdx", BoardDomain.class)
//                .setParameter("boardIdx", board.getIdx()).getResultList();
//        log.info("board find : {}", findBoard.toString());
    }
}