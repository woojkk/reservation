package com.woojkk.reservation.application;

import com.woojkk.reservation.client.MailgunClient;
import com.woojkk.reservation.client.mailgun.SendMailForm;
import com.woojkk.reservation.domain.model.Manager;
import com.woojkk.reservation.domain.model.SignUpForm;
import com.woojkk.reservation.exception.CustomException;
import com.woojkk.reservation.exception.ErrorCode;
import com.woojkk.reservation.service.ManagerSignService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class SignUpApplication {

    private final MailgunClient mailgunClient;
    private final ManagerSignService managerSignService;

    public String managerSignUp(SignUpForm form) {
        if (managerSignService.isExistEmail(form.getEmail())) {
            throw new CustomException(ErrorCode.ALREADY_REGISTER_USER);
        } else {
            Manager manager = managerSignService.signUp(form);

            SendMailForm sendMailForm = SendMailForm.builder()
                    .from("reservation@Service.com")
                    .to(form.getEmail())
                    .subject("가입 완료 메일")
                    .text(getEmailInfo(manager.getEmail(), manager.getName()))
                    .build();

            mailgunClient.sendEmail(sendMailForm);

            log.info("Send email result : " + mailgunClient.sendEmail(sendMailForm));

            return "회원 가입 완료 메일 발송 완료";
        }
    }

    private String getEmailInfo(String email, String name) {
        return "안녕하세요. []입니다.\n" +
                "회원가입이 성공적으로 완료되었습니다.\n" +
                "회원가입 정보입니다.\n\n" +
                "이름 : " + name + "\n" +
                "이메일 : " + email + "\n" +
                "이제부터 []의 다양한 기능과 서비스를 이용하실 수 있습니다.\n" +
                "로그인 후에는 개인정보 관리, 게시물 작성 등 다양한 기능을 활용해 보세요.\n\n" +
                "감사합니다.";


    }
}
