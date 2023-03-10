package com.phpimentel.email.adapters.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

@Getter
@Builder
public class EmailDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Singular
    private Set<String> recipients;

    @NonNull
    private String subject;

    @NonNull
    private String body;

    @NonNull
    private String to;

    @Singular("model")
    private Map<String, Object> model;
}
