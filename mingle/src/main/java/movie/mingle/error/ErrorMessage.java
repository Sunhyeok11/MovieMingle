package movie.mingle.error;

public class ErrorMessage {
    public static String getMessage(ErrorCode errorCode) {
        switch (errorCode) {
            case USERNAME_EXISTS:
                return "사용자 이름이 이미 존재합니다.";
            case EMAIL_EXISTS:
                return "이메일이 이미 존재합니다.";
            case INSUFFICIENT_STOCK:
                return "재고가 부족합니다.";
            default:
                return "알 수 없는 오류가 발생했습니다.";
        }
    }
}
