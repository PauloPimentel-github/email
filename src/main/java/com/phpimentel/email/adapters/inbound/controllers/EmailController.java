package com.phpimentel.email.adapters.inbound.controllers;

import com.phpimentel.email.adapters.dtos.EmailDto;
import com.phpimentel.email.core.domain.EmailDomain;
import com.phpimentel.email.core.ports.EmailServicePort;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmailServicePort emailServicePort;

    @PostMapping
    public void sendEmail(@RequestBody EmailDto emailDto) {
        var email = EmailDto.builder()
                .subject(emailDto.getSubject())
                .body("template.html")
                .model("username", emailDto.getTo())
                .to(emailDto.getTo())
                .recipients(emailDto.getRecipients())
                .build();

        var emailDomain = this.modelMapper.map(email, EmailDomain.class);
        this.emailServicePort.send(emailDomain);
    }
}
