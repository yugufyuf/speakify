package com.example.speakify.exception;

public class AppException extends RuntimeException {
    public AppException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    //Gọi super(errorCode.getMessage()):
  //Truyền thông điệp lỗi từ ErrorCode lên RuntimeException (cha của AppException).
  //Khi ném ngoại lệ, getMessage() của RuntimeException sẽ chứa thông điệp từ ErrorCode.

  //ví dụ: throw new AppException(ErrorCode.USER_EXISTED);
  //ErrorCode.USER_EXISTED.getMessage() → "User already existed"
  //Gọi super("User already existed"), nên RuntimeException sẽ chứa thông điệp lỗi này.

    private ErrorCode errorCode;

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
