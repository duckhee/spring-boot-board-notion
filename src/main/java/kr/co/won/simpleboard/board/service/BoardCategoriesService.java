package kr.co.won.simpleboard.board.service;

import kr.co.won.simpleboard.board.dto.BoardCategoryResponseDto;
import kr.co.won.simpleboard.board.dto.form.CategoryCreateForm;

import java.util.List;

public interface BoardCategoriesService {

    /**
     * Create Board Category
     */
    default BoardCategoryResponseDto.Create createBoardCategories(CategoryCreateForm form) {
        return null;
    }

    /**
     * List Board Category
     */
    default List<BoardCategoryResponseDto.Categories> listBoardCategories() {
        return null;
    }

    /**
     * Detail Board Category
     */
    default BoardCategoryResponseDto.Detail detailBoardCategory(String code) {
        return null;
    }

    /** Update Board Category */

    /** Delete Board Category */

}
