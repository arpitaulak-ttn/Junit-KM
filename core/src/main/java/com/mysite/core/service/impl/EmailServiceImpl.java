package com.mysite.core.service.impl;

import com.day.cq.mailer.MessageGateway;
import com.day.cq.mailer.MessageGatewayService;
import com.mysite.core.exception.ConditionNotMetException;
import com.mysite.core.service.EmailService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Component(service = EmailService.class)
public class EmailServiceImpl implements EmailService {

    private static final Logger LOG = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Reference
    private MessageGatewayService messageGatewayService;

    @Override
    public MailSendStatus sendEmail(Map<String, String> emailParams) {
        try {
            String mailId = emailParams.get("receiverEmail");

            if (StringUtils.isNotEmpty(mailId)) {
                Email email = new HtmlEmail();
                email.addTo(mailId);
                email.setSubject(emailParams.getOrDefault("subject", StringUtils.EMPTY));
                email.setMsg(emailParams.getOrDefault("body", StringUtils.EMPTY));
                MessageGateway<Email> messageGateway = messageGatewayService.getGateway(HtmlEmail.class);
                messageGateway.send(email);
                return MailSendStatus.SUCCESS;
            } else {
                throw new ConditionNotMetException(MailSendStatus.RECEIVER_MAIL_IS_EMPTY.toString());
            }
        } catch (EmailException e) {
            LOG.error(e.getMessage(), e);
        }
        return MailSendStatus.FAILED;
    }
}
