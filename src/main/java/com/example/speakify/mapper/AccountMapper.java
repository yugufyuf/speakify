package com.example.speakify.mapper;

import com.example.speakify.dto.request.AccountCreateRequest;
import com.example.speakify.dto.request.AccountUpdateRequest;
import com.example.speakify.dto.response.AccountResponse;
import com.example.speakify.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account toAccount(AccountCreateRequest request);
    AccountResponse toAccountResponse(Account account);
    void updateAccount(@MappingTarget Account account, AccountUpdateRequest request);
    //Nếu không dùng @MappingTarget, MapStruct sẽ tạo một object Account mới thay vì cập
    // nhật object đã có. Điều này có thể gây ra lỗi nếu bạn muốn cập nhật một Account đã
    // tồn tại trong database.
}
