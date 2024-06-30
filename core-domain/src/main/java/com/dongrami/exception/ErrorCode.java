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

    // 초대 코드
    INVITE_CODE_EMPTY(400, "IC001", "짝꿍 초대 코드는 비어 있을 수 없습니다."),
    INVITE_CODE_INVALID(400, "IC002", "유효하지 않은 초대 코드입니다."),

    // 할 일
    TODO_NOT_EXIST(400, "TD001", "할 일이 존재하지 않습니다."),
    TODO_ALREADY_DELETED_CANNOT_UPDATE(400, "TD002", "이미 삭제되어 정보 수정이 불가능합니다."),
    TODO_ALREADY_COMPLETED_CANNOT_UPDATE(400, "TD003", "이미 완료된 할 일은 수정이 불가능합니다."),
    TODO_ALREADY_DELETED(400, "TD004", "이미 삭제된 할 일입니다."),
    TODO_INVALID_AUTHORIZATION(400, "TD005", "해당 할 일에 권한이 없습니다."),

    DELETE_USER_NOT_NULL(400, "DU001", "삭제 사용자는 null이 될 수 없습니다."),

    // 일기
    DIARY_NOT_EXIST(400, "DR001", "일기가 존재하지 않습니다."),
    DIARY_ALREADY_DELETED_CANNOT_UPDATE(400, "DR002", "이미 삭제된 일기는 수정이 불가능합니다."),
    DIARY_ALREADY_DELETED_CANNOT_DELETE(400, "DR003", "이미 삭제된 일기는 삭제가 불가능합니다."),
    DIARY_NOT_OWNER_CANNOT_UPDATE(400, "DR004", "일기의 소유자가 아니면 수정할 수 없습니다."),
    DIARY_NOT_OWNER_CANNOT_DELETE(400, "DR005", "일기의 소유자가 아니면 삭제할 수 없습니다."),

    // 태그
    TAG_NOT_EXIST(400, "TG001", "태그가 존재하지 않습니다."),

    // 감정
    FEELING_NAME_DUPLICATE(400, "FL001", "이미 존재하는 감정명입니다."),
    FEELING_ORDER_DUPLICATE(400, "FL002", "이미 존재하는 감정 순서입니다."),
    FEELING_NOT_EXIST(400, "FL003", "감정이 존재하지 않습니다."),

    // 일정
    CALENDAR_NOT_EXIST(400, "CD001", "일정이 존재하지 않습니다."),

    // 짝꿍
    PAIR_USER_ALREADY_EXIST(400, "PU001", "이미 짝꿍이 존재합니다."),;

    private final int status;
    private final String code;
    private final String message;
    }
