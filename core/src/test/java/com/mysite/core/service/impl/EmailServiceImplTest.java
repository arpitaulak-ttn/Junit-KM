package com.mysite.core.service.impl;

import com.day.cq.mailer.MessageGateway;
import com.day.cq.mailer.MessageGatewayService;
import com.mysite.core.exception.ConditionNotMetException;
import com.mysite.core.service.EmailService;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmailServiceImplTest {

    @InjectMocks
    private EmailServiceImpl emailServiceimpl;

    @Mock
    private MessageGatewayService messageGatewayService;

    @Mock
    private MessageGateway messageGateway;

    @Mock
    private Map<String, String> params;

    @Before
    public void before(){
        when(messageGatewayService.getGateway(any())).thenReturn(messageGateway);
    }

    @Test
    public void testSuccessfulExecution(){
        //GIVEN
        when(params.get("receiverEmail")).thenReturn("xyz@abc.com");
        when(params.getOrDefault("subject", StringUtils.EMPTY)).thenReturn("This is the subject of the email");
        when(params.getOrDefault("body", StringUtils.EMPTY)).thenReturn("This is the body of the email");
        //WHEN
        EmailService.MailSendStatus mailSendStatus = emailServiceimpl.sendEmail(params);
        //THEN
        Assert.assertEquals("Successful execution", EmailService.MailSendStatus.SUCCESS, mailSendStatus);
    }

    @Test(expected = ConditionNotMetException.class)
    public void testExceptionExecution(){
        emailServiceimpl.sendEmail(params);
    }

    @Test
    public void testFailedExecutionWhenBodyNotAvailable(){
        //GIVEN
        when(params.get("receiverEmail")).thenReturn("xyz@abc.com");
        when(params.getOrDefault("subject", StringUtils.EMPTY)).thenReturn("This is the subject of the email");
        //WHEN
        EmailService.MailSendStatus mailSendStatus = emailServiceimpl.sendEmail(params);
        //THEN
        Assert.assertEquals("Failed execution", EmailService.MailSendStatus.FAILED, mailSendStatus);
    }
}