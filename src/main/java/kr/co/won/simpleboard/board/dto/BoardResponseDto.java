package kr.co.won.simpleboard.board.dto;

import com.querydsl.core.annotations.QueryProjection;
import kr.co.won.simpleboard.board.domain.BoardDomain;
import lombok.Data;

import java.time.LocalDateTime;

public class BoardResponseDto {

    public record Create(Long boardIdx, String title) {
        public static Create ofDomain(BoardDomain board) {
            return new Create(board.getIdx(), board.getTitle());
        }
    }

    @Data
    public static class Paging {

        private String title;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        private Paging(String title, LocalDateTime createdAt, LocalDateTime updatedAt) {
            this.title = title;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
        }

        public static Paging ofDomain(BoardDomain board) {
            return new Paging(board.getTitle(), board.getCreatedAt(), board.getUpdatedAt());
        }
    }

    public record Detail(Long boardIdx, String title, String content, LocalDateTime createdAt,
                         LocalDateTime updatedAt) {
        public static Detail ofDomain(BoardDomain board) {
            return new Detail(board.getIdx(), board.getTitle(), board.getContent(), board.getCreatedAt(), board.getUpdatedAt());
        }
    }

    public record Update(Long boardIdx) {
        public static Update ofDomain(BoardDomain board) {
            return new Update(board.getIdx());
        }
    }

    public record Delete(Long boardIdx){
        public static Delete ofDomain(BoardDomain board){
            return new Delete(board.getIdx());
        }
    }
}
