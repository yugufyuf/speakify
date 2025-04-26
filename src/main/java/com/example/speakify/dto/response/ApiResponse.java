package com.example.speakify.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@JsonInclude(JsonInclude.Include.NON_NULL) //nhung field nao null thi kh hien len json
@Data //co the thay the cho @Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
//Lớp ApiResponse<T> này là một generic class dùng để định nghĩa cấu trúc phản hồi chung cho API
// trong dự án của bạn.
//T là một generic type parameter (tham số kiểu dữ liệu tổng quát).
//Nó cho phép ApiResponse hoạt động với bất kỳ kiểu dữ liệu nào mà không cần tạo nhiều phiên bản khác nhau.

//Lý do khai báo T ở class ApiResponse<T> thay vì chỉ ở biến result:
//1.     Biến result chỉ là một phần của class
//     Nếu khai báo T chỉ ở result, thì ApiResponse vẫn không biết trước kiểu dữ liệu mà nó sẽ chứa.
//2.    Khai báo T ở cấp class giúp cả class trở thành generic
//     Khi khai báo <T> ở class, tất cả thuộc tính và phương thức trong class có thể sử dụng T,
//     hông chỉ riêng result.
//3.    Tái sử dụng linh hoạt trên nhiều kiểu dữ liệu
//     Nếu T chỉ được khai báo ở result, thì code và message vẫn sẽ có kiểu cố định (int, String).
//     Nhưng nếu sau này bạn muốn thêm một danh sách lỗi (List<String> errors), T có thể áp dụng luôn,
//     không cần sửa nhiều code.

//Generic trong Java là một tính năng giúp bạn viết code linh hoạt và tái sử dụng bằng cách cho phép
// các class, interface và method hoạt động với nhiều kiểu dữ liệu khác nhau, mà không cần phải viết
// lại code cho từng kiểu cụ thể.

//vi du: trong AccountController
//ApiResponse<Account> thi Account la T
//va khi thuc hien ham createAccount xong thi no se tra ve trong field result la Account
//{
//    "code": 1000,
//    "result": {
//        "id": "d6d3755c-f02d-4461-a0e2-6a9282fb0848",
//        "name": "pham anh anh",
//        "password": "123445678",
//        "email": "pham456789@gmail.com"
//    }
//}

public class ApiResponse<T>{
    @Builder.Default
    int code = 1000;
    @Builder.Default
    String message = "Success";
    T result;
}

//1.Ý nghĩa từng trường
//code: Mã trạng thái HTTP (ví dụ 200 cho thành công, 400 cho lỗi).
//message: Thông điệp mô tả trạng thái (ví dụ "Success" hoặc "Bad Request").
//result: Dữ liệu thực tế mà API trả về, kiểu dữ liệu T giúp linh hoạt với nhiều loại dữ liệu khác nhau.

//2. Cách sử dụng trong API
//Giả sử bạn có một API để lấy danh sách người dùng, bạn có thể trả về ApiResponse thay vì trả về
// thẳng danh sách thi khi dung lop ApiResponse nay se tra ve 1 json:
//{
//    "code": 200,
//    "message": "Success",
//    "result": ["Alice", "Bob", "Charlie"]
//}