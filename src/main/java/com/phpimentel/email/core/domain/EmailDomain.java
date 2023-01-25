package com.phpimentel.email.core.domain;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

public class EmailDomain implements Serializable {

    private static final long serialVersionUID = 1L;

    private Set<String> recipients;

    private String subject;

    private String body;

    private String to;

    private Map<String, Object> model;

    public Set<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(Set<String> recipients) {
        this.recipients = recipients;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
}
