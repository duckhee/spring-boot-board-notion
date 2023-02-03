package kr.co.won.simpleboard.board.exception;

import lombok.Getter;

@Getter
public class BoardException extends RuntimeException {

    private int errorCode;
    private String msg;

    public BoardException(BoardErrorCode error) {
        this.errorCode = error.getErrorCode();
        this.msg = error.getMsg();
    }
}
