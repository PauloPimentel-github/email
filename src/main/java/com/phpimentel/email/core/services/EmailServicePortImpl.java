package com.phpimentel.email.core.services;

import com.phpimentel.email.core.domain.EmailDomain;
import com.phpimentel.email.core.ports.EmailServicePort;
import com.phpimentel.email.core.ports.MailPort;

public class EmailServicePortImpl implements EmailServicePort {

    private MailPort mailPort;

    public EmailServicePortImpl(MailPort mailPort) {
        this.mailPort = mailPort;
    }

    @Override
    public void send(EmailDomain emailDomain) {
        this.mailPort.send(emailDomain);
    }
}
