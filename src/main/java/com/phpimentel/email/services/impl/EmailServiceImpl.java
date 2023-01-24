package com.phpimentel.email.services.impl;

import com.phpimentel.email.configs.EmailProperties;
import com.phpimentel.email.dtos.EmailDto;
import com.phpimentel.email.exceptions.EmailException;
import com.phpimentel.email.services.EmailService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailProperties emailProperties;

    @Autowired
    private Configuration freemarkerConfig;

    @Override
    public void send(EmailDto emailDto) {
        try {
            String body = this.proccessTemplate(emailDto);
            MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
            String from = this.emailProperties.getFrom();
            helper.setFrom(from);
            helper.setTo(emailDto.getRecipients().toArray(new String[0]));
            helper.setSubject(emailDto.getSubject());
            helper.setText(body, true);

            this.mailSender.send(mimeMessage);
            log.info("E-mail enviado com sucesso...");
        } catch (Exception exception) {
            throw new EmailException("Não foi possível enviar o e-mail", exception);
        }
    }

    private String proccessTemplate(EmailDto emailDto) {
        try {
            Template template = this.freemarkerConfig.getTemplate(emailDto.getBody());
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, emailDto.getModel());
        } catch (Exception exception) {
            throw new EmailException("Não foi possível montar o template do e-mail", exception);
        }
    }
}
