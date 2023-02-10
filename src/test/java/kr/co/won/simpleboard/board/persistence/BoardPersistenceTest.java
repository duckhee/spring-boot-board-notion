package kr.co.won.simpleboard.board.persistence;

import kr.co.won.simpleboard.board.domain.BoardDomain;
import kr.co.won.simpleboard.board.factory.BoardDomainRandomFactory;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.*;

@Slf4j
@Rollback
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DisplayName(value = "Board DataBase Tests")
@Import(value = {
        BoardDomainRandomFactory.class
})
class BoardPersistenceTest {

    @Autowired
    private BoardPersistence boardPersistence;

    @Autowired
    private BoardDomainRandomFactory factory;

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
}