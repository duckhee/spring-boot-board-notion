package kr.co.won.simpleboard.user.exception;

import lombok.Getter;

@Getter
public class UserException extends RuntimeException {

    private int errorCode;

    private String msg;

    public UserException(UserErrorCode errorCode) {
        this.errorCode = errorCode.getErrorCode();
        this.msg = errorCode.getMsg();
    }
}
