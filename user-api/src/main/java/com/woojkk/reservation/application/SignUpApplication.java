package com.woojkk.reservation.application;

import com.woojkk.reservation.client.MailgunClient;
import com.woojkk.reservation.client.mailgun.SendMailForm;
import com.woojkk.reservation.domain.model.Customer;
import com.woojkk.reservation.domain.model.Manager;
import com.woojkk.reservation.domain.model.SignUpForm;
import com.woojkk.reservation.exception.CustomException;
import com.woojkk.reservation.exception.ErrorCode;
import com.woojkk.reservation.service.CustomerService;
import com.woojkk.reservation.service.ManagerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SignUpApplication {

    private final MailgunClient mailgunClient;
    private final ManagerService managerService;

    private final CustomerService customerService;

    public String managerSignUp(SignUpForm form) {
        if (managerService.isExistEmail(form.getEmail())) {
            throw new CustomException(ErrorCode.ALREADY_REGISTER_USER);
        } else {
            Manager manager = managerService.signUp(form);

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

    public String customerSignUp(SignUpForm form) {
        if (customerService.isExistEmail(form.getEmail())) {
            throw new CustomException(ErrorCode.ALREADY_REGISTER_USER);
        } else {
            Customer customer = customerService.signUp(form);

            SendMailForm sendMailForm = SendMailForm.builder()
                    .from("reservation@Service.com")
                    .to(form.getEmail())
                    .subject("가입 완료 메일")
                    .text(getEmailInfo(customer.getEmail(), customer.getName()))
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
                "감사합니다.";


    }
}
