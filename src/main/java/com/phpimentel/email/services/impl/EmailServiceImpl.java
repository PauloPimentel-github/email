package com.phpimentel.email.services.impl;

import com.phpimentel.email.configs.EmailProperties;
import com.phpimentel.email.dtos.EmailDto;
import com.phpimentel.email.exceptions.EmailException;
import com.phpimentel.email.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailProperties emailProperties;

    @Override
    public void enviar(EmailDto emailDto) {
        try {
            MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
            helper.setFrom(this.emailProperties.getSender());
            helper.setTo(emailDto.getRecipients().toArray(new String[0]));
            helper.setSubject(emailDto.getSubject());
            helper.setText(emailDto.getBody(), true);


            this.mailSender.send(mimeMessage);
        } catch (Exception exception) {
            throw new EmailException("Não foi possível enviar o e-mail", exception);
        }
    }
}
