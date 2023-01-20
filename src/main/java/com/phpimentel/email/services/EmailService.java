package com.phpimentel.email.services;

import com.phpimentel.email.dtos.EmailDto;

public interface EmailService {

    void enviar(EmailDto emailDto);
}
