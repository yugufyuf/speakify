package com.example.speakify.service;

import com.example.speakify.dto.request.AccountCreateRequest;
import com.example.speakify.dto.request.AccountUpdateRequest;
import com.example.speakify.dto.response.AccountResponse;
import com.example.speakify.entity.Account;
import com.example.speakify.exception.AppException;
import com.example.speakify.exception.ErrorCode;
import com.example.speakify.mapper.AccountMapper;
import com.example.speakify.repository.AccountRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor// = @Autowired + final
//Tự động tạo constructor chỉ chứa các field final hoặc có annotation @NonNull.
//Nếu có nhiều field, nó chỉ tạo constructor cho các field bắt buộc đó.
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountService {
    AccountRepository accountRepository;
    AccountMapper accountMapper;
//    @Autowired
    final MailSenderService mailSenderService;

    public Account createAccount(AccountCreateRequest request) {
        if(accountRepository.existsByEmail(request.getEmail()))
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        Account account = accountMapper.toAccount(request);
        //MapStruct chỉ cập nhat object trong RAM, kh lưu vào database
        //Khi gọi accountMapper.updateAccount(account, request), dữ liệu của account sẽ được
        // cập nhat trong bộ nhớ (RAM), nhưng chưa ghi xuống database.
        //Spring Data JPA không tự động lưu object đã chỉnh sửa nếu bạn không gọi save().

        mailSenderService.sendMail(request.getEmail(), "Signin Speakify", "Success");
        return accountRepository.save(account);
    }

    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public AccountResponse getAccountById(String id) {
        return accountMapper.toAccountResponse(accountRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));
    }

    public Account getAccount(String id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
    }

    public AccountResponse updateAccount(String id, AccountUpdateRequest request) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        if (request.getName() != null) {
            account.setName(request.getName());
        }
        if (request.getPassword() != null) {
            account.setPassword(request.getPassword());
        }
        return accountMapper.toAccountResponse(accountRepository.save(account));
    }

    public String deleteAccount(String id) {
        accountRepository.deleteById(id);
        if(accountRepository.existsById(id)) {
            return "Delete failed";
        }
        return "Delete Success";
    }
}