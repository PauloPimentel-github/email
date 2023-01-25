package com.phpimentel.email.adapters.outbound.mail;

import com.phpimentel.email.adapters.configs.EmailProperties;
import com.phpimentel.email.core.domain.EmailDomain;
import com.phpimentel.email.core.exceptions.EmailException;
import com.phpimentel.email.core.ports.MailPort;
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
public class MailPortImpl implements MailPort {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailProperties emailProperties;

    @Autowired
    private Configuration freemarkerConfig;

    @Override
    public void send(EmailDomain emailDomain) {
        try {
            String body = this.proccessTemplate(emailDomain);
            MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
            String from = this.emailProperties.getFrom();
            helper.setFrom(from);
            helper.setTo(emailDomain.getRecipients().toArray(new String[0]));
            helper.setSubject(emailDomain.getSubject());
            helper.setText(body, true);

            this.mailSender.send(mimeMessage);
            log.info("E-mail enviado com sucesso!");
        } catch (Exception exception) {
            log.error("Error ao enviar e-mail", exception);
            throw new EmailException("Não foi possível enviar o e-mail", exception);
        }
    }

    private String proccessTemplate(EmailDomain emailDomain) {
        try {
            Template template = this.freemarkerConfig.getTemplate(emailDomain.getBody());
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, emailDomain.getModel());
        } catch (Exception exception) {
            throw new EmailException("Não foi possível montar o template do e-mail", exception);
        }
    }
}
