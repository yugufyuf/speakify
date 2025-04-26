package com.example.speakify.exception;

import com.example.speakify.dto.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice //giup bat cac loi trong cac controller
        // khi controller nem loi runtimeException thi springboot tim den GlobalExceptionHandler
public class GlobalExceptionHandler {
    @ExceptionHandler(value = RuntimeException.class) //xu ly cac ngoai le thuoc runtimeException
//    Bình thường, khi bạn trả về một String trong API, Spring Boot sẽ tự động gói nó vào HTTP response.
//    Nhưng với ResponseEntity, bạn có thể tùy chỉnh phản hồi như:
//    - Trạng thái HTTP (200 OK, 400 Bad Request, 500 Internal Server Error...)
//    - Nội dung phản hồi (JSON, text...)
//    - Headers (dùng khi cần thêm thông tin vào phản hồi)
    ResponseEntity<ApiResponse> appExceptionHandler(AppException e) {
        //RuntimeException e là ngoai le duoc tra ve o controller
        //tra ve ApiResponse
        ErrorCode errorCode = e.getErrorCode();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(errorCode.getMessage());
        apiResponse.setCode(errorCode.getCode());
        return ResponseEntity.status(errorCode.getHttpStatus()).body(apiResponse);
        //khi tra ve se gui ma phan hoi: 400 (bad request) voi thong diep (apiResponse)
        //vi du:
        //trong ham createAccount
        //throw new RuntimeException("Email already exists")
        // thi thong diep được lấy ở đây va duoc de vao field message trong ApiResponse
        //va ma bao loi bay gio kh phai la 400 nua ma la 1001 duoc set o tren
    }

    //can phai them dependency Validation trong pom.xml trc thi moi dung dc anotation @Valid trong
    //controller de bat loi
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> validationExceptionHandler(MethodArgumentNotValidException e) {
        // Đây là một Exception Handler dùng để xử lý lỗi khi tham số đầu vào không hợp lệ
        // trong một API Spring Boot. Cụ thể:
        //Khi một request gửi dữ liệu không hợp lệ (ví dụ: mật khẩu dưới 8 ký tự), Spring Boot
        // sẽ ném ra MethodArgumentNotValidException.
        //Handler này sẽ bắt lỗi, lấy thông điệp lỗi từ annotation (message trong @Size, @NotNull,
        // @Pattern, ...) và ánh xạ nó sang một mã lỗi trong Enum ErrorCode.
        //Nếu mã lỗi không tồn tại trong ErrorCode, nó sẽ dùng một mã mặc định (INVALID_KEY) thay thế.

        String enumKey = e.getFieldError().getDefaultMessage();
//vi du: trong AccountCreateRequest
//@Size(min = 8, message = "NAME_INVALID")
//enumKey = NAME_INVALID
        ErrorCode errorCode = ErrorCode.INVALID_KEY;//dự phòng nếu không tìm thấy mã lỗi hợp lệ

        try {
            errorCode = ErrorCode.valueOf(enumKey);
            //valueOf se bien string thanh ErrorCode
            //errorCode = NAME_INVALID
        }catch (IllegalArgumentException ex){}
//        Khi nào chương trình bị crash?
//        Khi một exception (ngoại lệ) xảy ra mà không được xử lý, chương trình sẽ dừng ngay lập tức
//        và in lỗi ra console.
        //Nếu không tìm thấy, valueOf() sẽ ném ra IllegalArgumentException và chương trình sẽ
        // không bị crash vì đã có try-catch

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(errorCode.getMessage());
        apiResponse.setCode(errorCode.getCode());
        return ResponseEntity.status(errorCode.getHttpStatus()).body(apiResponse);
    }
}

//neu kh co lop nay thi khi controller tra ve runtimeException:
//{
//        "timestamp": "2025-03-31T15:55:54.145+00:00",
//        "status": 500,
//        "error": "Internal Server Error",
//        "path": "/account"
//        }