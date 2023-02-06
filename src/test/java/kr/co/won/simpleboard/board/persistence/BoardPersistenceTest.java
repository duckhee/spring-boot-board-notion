package kr.co.won.simpleboard.board.persistence;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.*;

@Slf4j
@Rollback(value = true)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DisplayName(value = "Board DataBase Tests")
class BoardPersistenceTest {

    @Autowired
    private BoardPersistence boardPersistence;
}