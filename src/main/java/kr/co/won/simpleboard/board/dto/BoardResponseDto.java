package kr.co.won.simpleboard.board.dto;

import kr.co.won.simpleboard.board.domain.BoardDomain;

import java.time.LocalDateTime;

public class BoardResponseDto {

    public static record Create(String title) {
        public static Create ofDomain(BoardDomain board) {
            return new Create(board.getTitle());
        }
    }

    public static record Detail(String title, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
        public static Detail ofDomain(BoardDomain board) {
            return new Detail(board.getTitle(), board.getContent(), board.getCreatedAt(), board.getUpdatedAt());
        }
    }
}
