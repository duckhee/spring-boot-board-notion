package kr.co.won.simpleboard.board.persistence.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import jakarta.persistence.EntityManager;
import kr.co.won.simpleboard.board.domain.BoardCategoryDomain;
import kr.co.won.simpleboard.board.domain.QBoardCategoryDomain;
import kr.co.won.simpleboard.board.dto.BoardCategoryResponseDto;
import kr.co.won.simpleboard.board.persistence.BoardCategoryPersistenceExtension;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static kr.co.won.simpleboard.board.domain.QBoardCategoryDomain.boardCategoryDomain;

@Slf4j
@Transactional(readOnly = true)
public class BoardCategoryPersistenceExtensionImpl extends QuerydslRepositorySupport implements BoardCategoryPersistenceExtension {

    private final EntityManager entityManager;


    public BoardCategoryPersistenceExtensionImpl(EntityManager entityManager) {
        super(BoardCategoryDomain.class);
        this.entityManager = entityManager;
    }

    @Override
    public List<BoardCategoryDomain> findAllCategories() {
        QBoardCategoryDomain rootCategory = boardCategoryDomain;
        QBoardCategoryDomain subCategory = new QBoardCategoryDomain("subCategories");
        List<BoardCategoryDomain> categories = from(rootCategory)
                .select(rootCategory)
                .leftJoin(subCategory)
                .on(subCategory.parent.idx.eq(rootCategory.idx))
                .where(rootCategory.parent.isNull()).fetch();
        log.info("categories : {}", categories);
        return categories;
    }

    @Override
    public Page<BoardCategoryResponseDto.Categories> pagingCategories(String type, String keyword, Pageable pageable) {
        QBoardCategoryDomain rootCategory = boardCategoryDomain;
        QBoardCategoryDomain subCategory = new QBoardCategoryDomain("sub");
        JPQLQuery<BoardCategoryResponseDto.Categories> baseQuery = from(rootCategory)
                .select(Projections.fields(
                        BoardCategoryResponseDto.Categories.class,
                        rootCategory.idx.as("idx"),
                        rootCategory.name.as("name"),
                        rootCategory.categoryCode.as("categoryCode"),
                        rootCategory.depth.as("depth")
//                        rootCategory.subCategory
                ))
//                .distinct()
                .leftJoin(subCategory)
                .on(subCategory.parent.idx.eq(rootCategory.idx))
                .fetchJoin()
                .where(rootCategory.parent.isNull())
                .orderBy(rootCategory.idx.asc(), subCategory.idx.asc());
        if (type != null) {
            switch (keyword) {

            }
        }
        long totalNumber = baseQuery.fetchCount();

        List<BoardCategoryResponseDto.Categories> result = baseQuery.fetch();
        log.info("result paging : {}", result);
        return new PageImpl<>(result, pageable, totalNumber);
    }

}
