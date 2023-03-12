package kr.co.won.simpleboard.board.dto;

import kr.co.won.simpleboard.board.domain.BoardCategoryDomain;

import java.util.List;

public class BoardCategoryResponseDto {

    public record Create(String name, String code) {
        public static Create ofDomain(BoardCategoryDomain domain) {
            return new Create(domain.getName(), domain.getCategoryCode());
        }
    }


    public record Categories(Long idx, String name, String categoryCode, int depth, List<SubCategories> subCategories) {

    }

    public record SubCategories(Long idx, Long parentIdx, String name, String categoryCode, int depth) {

    }

    public record Detail(String name, String code, Detail parent, int depth, List<Detail> subCategories) {

    }

}
