package com.phpimentel.email.dtos;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Builder
public class EmailDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Set<String> recipients;
    private String subject;
    private String body;
}
