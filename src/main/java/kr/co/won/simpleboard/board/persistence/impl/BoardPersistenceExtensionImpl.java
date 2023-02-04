package kr.co.won.simpleboard.board.persistence.impl;

import com.querydsl.core.annotations.QueryProjection;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import kr.co.won.simpleboard.board.domain.BoardDomain;
import kr.co.won.simpleboard.board.domain.QBoardDomain;
import kr.co.won.simpleboard.board.dto.BoardResponseDto;
import kr.co.won.simpleboard.board.persistence.BoardPersistenceExtension;
import kr.co.won.simpleboard.utils.PageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.expression.spel.ast.Projection;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.querydsl.jpa.JPAExpressions.select;
import static com.querydsl.jpa.JPAExpressions.selectFrom;
import static kr.co.won.simpleboard.board.domain.QBoardDomain.boardDomain;

@Slf4j
@Repository
@Transactional(readOnly = true)
public class BoardPersistenceExtensionImpl extends QuerydslRepositorySupport implements BoardPersistenceExtension {

    public BoardPersistenceExtensionImpl() {
        super(BoardDomain.class);
    }

    @Override
    public Page<BoardResponseDto.Paging> pagingBoard(PageDto pageDto, Pageable pageable) {
        QBoardDomain board = boardDomain;
        JPQLQuery<BoardResponseDto.Paging> baseQuery =
                from(board)
                        .select(Projections.fields(BoardResponseDto.Paging.class,
                                board.idx.as("boardIdx"),
                                board.title.as("title"),
                                board.createdAt.as("createdAt"),
                                board.updatedAt.as("updatedAt")))
                        .where(board.deletedFlag.eq(false));
        // TODO search type define
        if (pageDto.getType() != null) {
            switch (pageDto.getType().toLowerCase()) {
                case "title":
                    baseQuery.where(board.title.like("%" + pageDto.getKeyword() + "%"));
                    break;
            }
        }

        // total count
        long totalContentNumber = baseQuery.fetchCount();
        log.info("pageable offset : {}, page number : {} get page dto : {}", pageable.getOffset(), pageable.getPageNumber(), pageDto.getPage());
        // result
        List<BoardResponseDto.Paging> resultPage = baseQuery
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .orderBy(board.createdAt.desc())
                .fetch();
        log.info("result total : {}, result page : {}, resultPage count size : {}", totalContentNumber, resultPage.toString(), resultPage.size());
        return new PageImpl<>(resultPage, pageable, totalContentNumber);
    }
}
