package com.phpimentel.email.core.ports;

import com.phpimentel.email.core.domain.EmailDomain;

public interface EmailServicePort {

    void send(EmailDomain emailDomain);
}
