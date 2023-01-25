package com.phpimentel.email.adapters.configs;

import com.phpimentel.email.EmailApplication;
import com.phpimentel.email.core.ports.MailPort;
import com.phpimentel.email.core.services.EmailServicePortImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = EmailApplication.class)
public class BeanConfiguration {

    @Bean
    public EmailServicePortImpl emailServicePortImpl(MailPort mailPort) {
        return new EmailServicePortImpl(mailPort);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
