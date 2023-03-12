package kr.co.won.simpleboard.board.persistence;

import kr.co.won.simpleboard.board.domain.BoardCategoryDomain;
import kr.co.won.simpleboard.board.dto.BoardCategoryResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardCategoryPersistenceExtension {

    List<BoardCategoryDomain> findAllCategories();


    Page<BoardCategoryResponseDto.Categories> pagingCategories(String type, String keyword, Pageable pageable);


}
