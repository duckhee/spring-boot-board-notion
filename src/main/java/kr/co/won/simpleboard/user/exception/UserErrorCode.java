package kr.co.won.simpleboard.user.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserErrorCode {

    USER_NOT_PERMISSION(524, "not have permission"),
    USER_NOT_FOUND(544, "user not found"),
    USER_ALREADY_HAVE(514, "user already registered.");

    private int errorCode;

    private String msg;
}
