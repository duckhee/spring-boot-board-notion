package kr.co.won.simpleboard.board.dto;

import kr.co.won.simpleboard.board.domain.BoardCategoryDomain;

public class BoardCategoryResponseDto {

    public record Create(String name, String code) {
        public static Create ofDomain(BoardCategoryDomain domain) {
            return new Create(domain.getName(), domain.getCategoryCode());
        }
    }


}
