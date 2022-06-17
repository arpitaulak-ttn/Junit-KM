package com.mysite.core.servlets;

import com.mysite.core.exception.ConditionNotMetException;
import com.mysite.core.service.EmailService;
import com.mysite.core.service.impl.EmailServiceImpl;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EmailNotificationBasicTest {

    @InjectMocks
    private EmailNotificationServlet emailNotificationServlet;

    private SlingHttpServletRequest request;

    private EmailService emailService;

    private SlingHttpServletResponse response;

    private ArgumentCaptor<String> argumentCaptor;

    @Before
    public void before(){
        emailNotificationServlet = new EmailNotificationServlet();
        request = mock(SlingHttpServletRequest.class);
        emailService = mock(EmailServiceImpl.class);
        response = mock(SlingHttpServletResponse.class);
        argumentCaptor = ArgumentCaptor.forClass(String.class);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDoGetMethod_success_ifRedirectUrlIsEmpty() throws IOException {
        //GIVEN
        when(request.getParameter("email")).thenReturn("xyz@abc.com");
        when(emailService.sendEmail(any())).thenReturn(EmailService.MailSendStatus.SUCCESS);
        //WHEN
        emailNotificationServlet.doGet(request, response);
        //THEN
        verify(response).setStatus(HttpServletResponse.SC_OK);
        verify(response, times(1)).setStatus(HttpServletResponse.SC_OK);
        verify(response, atLeastOnce()).setStatus(HttpServletResponse.SC_OK);
        verify(response, atLeast(0)).setStatus(HttpServletResponse.SC_OK);
        verify(response, atMost(2)).setStatus(HttpServletResponse.SC_OK);
        verify(response, never()).setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

    @Test
    public void testDoGetMethod_success_ifRedirectUrlIsNotEmpty() throws IOException {
        //GIVEN
        when(request.getParameter("email")).thenReturn("xyz@abc.com");
        when(request.getParameter("redirectUrl")).thenReturn("www.google.com");
        when(emailService.sendEmail(any())).thenReturn(EmailService.MailSendStatus.SUCCESS);
        //WHEN
        emailNotificationServlet.doGet(request, response);
        //THEN
        verify(response).sendRedirect(argumentCaptor.capture());
        Assert.assertEquals("www.google.com", argumentCaptor.getValue());
    }

    @Test
    public void testDoGetMethod_failure() throws IOException {
        //GIVEN
        when(request.getParameter("email")).thenReturn("xyz@abc.com");
        when(emailService.sendEmail(any())).thenReturn(EmailService.MailSendStatus.FAILED);
        //WHEN
        emailNotificationServlet.doGet(request, response);
        //THEN
        verify(response).setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

    @Test
    public void testDoGet_receivedException() throws IOException {
        //GIVEN
        when(emailService.sendEmail(any())).thenThrow(new ConditionNotMetException(EmailService.MailSendStatus.RECEIVER_MAIL_IS_EMPTY.toString()));
        //WHEN
        emailNotificationServlet.doGet(request, response);
        //THEN
        verify(response).setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
}
