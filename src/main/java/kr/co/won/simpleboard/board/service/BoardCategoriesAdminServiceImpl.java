package kr.co.won.simpleboard.board.service;

import kr.co.won.simpleboard.board.domain.BoardCategoryDomain;
import kr.co.won.simpleboard.board.dto.BoardCategoryResponseDto;
import kr.co.won.simpleboard.board.dto.form.CategoryCreateForm;
import kr.co.won.simpleboard.board.persistence.BoardCategoryPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class BoardCategoriesAdminServiceImpl implements BoardCategoriesService {

    private final BoardCategoryPersistence categoryPersistence;

    @Transactional
    @Override
    public BoardCategoryResponseDto.Create createBoardCategories(CategoryCreateForm form) {
        BoardCategoryDomain savedCategory;
        if (!StringUtils.hasText(form.getParentCode())) {
            BoardCategoryDomain newRootCategory = BoardCategoryDomain.of(form.getCode(), form.getName(), 0, null);
            savedCategory = categoryPersistence.save(newRootCategory);
        } else {
            // get parent category
            BoardCategoryDomain findParent = categoryPersistence.findByCategoryCode(form.getParentCode()).orElseThrow(() -> {
                // TODO change custom exception
                return new IllegalArgumentException();
            });
            BoardCategoryDomain newSubCategory = BoardCategoryDomain.of(form.getCode(), form.getName(), findParent.getDepth() + 1, findParent);
            savedCategory = categoryPersistence.save(newSubCategory);
        }
        if (savedCategory != null) {
            return BoardCategoryResponseDto.Create.ofDomain(savedCategory);
        }
        // todo custom exception create board category failed
        throw new IllegalArgumentException("");
    }

    @Override
    public BoardCategoryResponseDto.Detail detailBoardCategory(String code) {
        return BoardCategoriesService.super.detailBoardCategory(code);
    }

    @Override
    public List<BoardCategoryResponseDto.Categories> listBoardCategories() {

        return BoardCategoriesService.super.listBoardCategories();
    }
}
