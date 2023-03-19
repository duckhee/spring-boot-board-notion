package kr.co.won.simpleboard.board.factory;

import kr.co.won.simpleboard.board.domain.BoardCategoryDomain;
import kr.co.won.simpleboard.board.persistence.BoardCategoryPersistence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.TestComponent;

@Slf4j
@TestComponent
public class BoardCategoryDomainFactory {

    private final BoardCategoryPersistence categoryPersistence;

    public BoardCategoryDomainFactory(BoardCategoryPersistence categoryPersistence) {
        this.categoryPersistence = categoryPersistence;
    }

    public BoardCategoryDomain createCategory(String code, String name, int depth, BoardCategoryDomain parent) {
        BoardCategoryDomain newCategory = BoardCategoryDomain.of(code, name, depth, parent);
        return categoryPersistence.save(newCategory);
    }

}
