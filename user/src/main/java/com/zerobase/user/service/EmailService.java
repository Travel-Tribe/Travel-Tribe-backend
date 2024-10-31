package com.zerobase.user.service;

import static com.zerobase.user.dto.response.BasicErrorCode.EMAIL_SENDING_ERROR;
import static com.zerobase.user.dto.response.BasicErrorCode.ILLEGAL_EMAIL_ADDRESS_ERROR;

import com.zerobase.user.exception.BizException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendVerificationCode(String to, String verificationCode) {
        String subject = "귀하의 이메일 인증 코드";
        String content = "귀하의 인증 코드는 다음과 같습니다: " + verificationCode;

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, false);

            mailSender.send(message);
            log.info("Verification email sent to {}", to);
        }catch (AddressException e) {
            log.error("Failed to send email to {}", to, e);
            throw new BizException(ILLEGAL_EMAIL_ADDRESS_ERROR);
        } catch (MessagingException e) {
            log.error("Failed to send email to {}", to, e);
            throw new BizException(EMAIL_SENDING_ERROR);
        }
    }

    public void sendResetPassword(String to, String Password) {
        String subject = "귀하의 임시 비밀번호";
        String content = "귀하의 임시 비밀번호 다음과 같습니다: " + Password;

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, false);

            mailSender.send(message);
            log.info("Verification email sent to {}", to);
        }catch (AddressException e) {
            log.error("Failed to send email to {}", to, e);
            throw new BizException(ILLEGAL_EMAIL_ADDRESS_ERROR);
        } catch (MessagingException e) {
            log.error("Failed to send email to {}", to, e);
            throw new BizException(EMAIL_SENDING_ERROR);
        }
    }
}

