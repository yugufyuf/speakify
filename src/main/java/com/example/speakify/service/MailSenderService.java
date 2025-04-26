package com.example.speakify.service;

import com.example.speakify.dto.request.SendEmailRequest;
import com.example.speakify.exception.AppException;
import com.example.speakify.exception.ErrorCode;
import com.example.speakify.repository.AccountRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.regex.Pattern;

@Slf4j
@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MailSenderService {
    private JavaMailSender mailSender;

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    public boolean sendMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);

        System.out.println("Sending email to: " + to);
        try {
            mailSender.send(message);
            System.out.println("Email sent successfully!");
            return true;
        } catch (MailException e) {
            e.printStackTrace();
            System.out.println("Failed to send email: " + e.getMessage());
            return false;
        }  // Thêm dòng này để thực sự gửi email
    }

    public boolean sendOTPMail(String email){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Ma OTP");
        message.setText("Ma dang ky tai khoan pdfHub: 123456" /*+ otpService.generateOTP()*/);

        try {
            mailSender.send(message);
            log.info("Email sent successfully!");
            return true;
        } catch (MailException e) {
            e.printStackTrace();
            log.info("Failed to send email: " + e.getMessage());
            return false;
        }
    }
}
