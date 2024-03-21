package com.dongrami.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE(400, "C001", "Invalid Input Value"),
    METHOD_NOT_ALLOWED(405, "C002", "Method Not Allowed"),
    INTERNAL_SERVER_ERROR(500, "C003", "Server Error"),
    INVALID_TYPE_VALUE(400, "C004", "Invalid Type Value"),
    HANDLE_ACCESS_DENIED(403, "C005", "Access is Denied"),
    ENTITY_NOT_FOUND(400, "C006", "Entity Not Found"),
    INVALID_FILE(400, "C007", "Invalid File"),
    INVALID_PARAMETER(400, "C008", "Invalid Parameter"),
    INVALID_REQUEST(400, "C009", "Invalid Request"),
    INVALID_RESPONSE(400, "C010", "Invalid Response"),
    INVALID_TOKEN(400, "C011", "Invalid Token"),
    INVALID_AUTHENTICATION(400, "C012", "Invalid Authentication"),
    INVALID_AUTHORIZATION(400, "C013", "Invalid Authorization"),
    INVALID_SESSION(400, "C014", "Invalid Session"),
    INVALID_USER(400, "C015", "Invalid User"),

    NO_CONTENT(200, "C016", "No Content"),
    HANDLE_ALREADY_DELETED(200, "C017", "Already Deleted"),
    HANDLE_ALREADY_EMOJI(200, "C018", "Already add emoji"),
    EMOJI_NAME_ALREADY_EXIST(200, "C019", "Emoji name already exist"),

    // User-Group
    USER_GROUP_ALREADY_EXIST(400, "UG001", "사용자 그룹은 1개만 생성 가능하고, 이미 사용자 그룹이 존재합니다."),
    ;

    private final int status;
    private final String code;
    private final String message;
}
