package kr.co.won.simpleboard.board.exception;

import jakarta.validation.constraints.AssertTrue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BoardErrorCode {

    BOARD_NOT_FOUND(444, "check search board..."),
    BOARD_DELETE_FAILED(524, "board Delete Failed..."),

    BOARD_CREATE_VALIDATED_FAILED(414, "validation failed check value"),
    BOARD_CREATE_SERVER_FAILED(520, "few second later retry...");

    private int errorCode;
    private String msg;
}
