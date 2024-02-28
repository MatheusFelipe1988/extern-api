package com.api.extern.business.configuration;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FakeMailService {

    private final JavaMailSender javaMailSender;

    @Value("${send.mail.from}")
    private String mailFrom;

    @Value("${send.mail.to}")
    private String mailTo;

    public void postMailException(Exception e){

        try {
            final MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true,
                    StandardCharsets.UTF_8.name());

            mimeMessageHelper.setFrom(new InternetAddress(mailFrom));
            mimeMessageHelper.setTo(new InternetAddress(mailTo));
            mimeMessageHelper.setSubject("Error to system");
            mimeMessageHelper.setText("Ocurred error system" + "\n" + e.getMessage() + "\n"
            + LocalDateTime.now());

            javaMailSender.send(message);

        }catch (MessagingException exception){
            exception.printStackTrace();
        }

    }

}
