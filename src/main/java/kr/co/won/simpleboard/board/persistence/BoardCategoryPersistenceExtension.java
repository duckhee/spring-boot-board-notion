package kr.co.won.simpleboard.board.persistence;

import kr.co.won.simpleboard.board.domain.BoardCategoryDomain;

import java.util.List;

public interface BoardCategoryPersistenceExtension {

    public List<BoardCategoryDomain> findAllCategories();


}
