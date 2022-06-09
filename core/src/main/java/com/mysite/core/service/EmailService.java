package com.mysite.core.service;

import org.apache.sling.api.resource.Resource;

import java.util.Map;

public interface EmailService {

    enum MailSendStatus {
        SUCCESS, FAILED, RECEIVER_MAIL_IS_EMPTY;
    }

    MailSendStatus sendEmail(Map<String, String> emailParams);
}
