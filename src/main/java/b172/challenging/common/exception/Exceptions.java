package b172.challenging.common.exception;


public enum Exceptions {

    INVALID_REQUEST(400, "1001", "올바르지 않은 요청입니다."),
    UNAUTHORIZED(401, "1002", "인증되지 않은 사용자 입니다."),

    NOT_FOUND_MEMBER(404, "2001", "요청한 ID에 해당하는 사용자를 찾을 수 없습니다."),

    NOT_FOUND_IMAGE(404, "3001", "저장된 모임 이미지가 없습니다.");


    private final int statusCode;
    private final String errorCode;
    private final String message;

    Exceptions(int statusCode, String errorCode, String message) {
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return this.message;
    }
}
