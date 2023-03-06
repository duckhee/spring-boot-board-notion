package kr.co.won.simpleboard.board.persistence.impl;

import kr.co.won.simpleboard.board.domain.BoardCategoryDomain;
import kr.co.won.simpleboard.board.domain.QBoardCategoryDomain;
import kr.co.won.simpleboard.board.persistence.BoardCategoryPersistenceExtension;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static com.querydsl.jpa.JPAExpressions.selectFrom;
import static kr.co.won.simpleboard.board.domain.QBoardCategoryDomain.boardCategoryDomain;

@Slf4j
public class BoardCategoryPersistenceExtensionImpl extends QuerydslRepositorySupport implements BoardCategoryPersistenceExtension {


    public BoardCategoryPersistenceExtensionImpl() {
        super(BoardCategoryDomain.class);
    }

    @Override
    public List<BoardCategoryDomain> findAllCategories() {
        QBoardCategoryDomain categoryDomain = boardCategoryDomain;
        QBoardCategoryDomain subCategory = new QBoardCategoryDomain("sub");
        List<BoardCategoryDomain> categories = selectFrom(categoryDomain)
                .distinct()
                .leftJoin(categoryDomain.subCategory, subCategory)
                .fetchJoin()
                .where(categoryDomain.parent.isNull())
                .orderBy(categoryDomain.depth.asc(), subCategory.depth.asc())
                .fetch();
        log.info("categories : {}", categories.toString());
        return categories;
    }
}
