package com.phpimentel.email.controllers;

import com.phpimentel.email.dtos.EmailDto;
import com.phpimentel.email.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping
    public void sendEmail(@RequestBody EmailDto emailDto) {
        var message = EmailDto.builder()
                .subject(emailDto.getSubject())
                .body("template.html")
                .model("username", emailDto.getTo())
                .to(emailDto.getTo())
                .recipients(emailDto.getRecipients())
                .build();

        this.emailService.send(message);
    }
}
