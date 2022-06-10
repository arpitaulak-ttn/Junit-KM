package com.mysite.core.servlets;

import com.mysite.core.exception.ConditionNotMetException;
import com.mysite.core.service.EmailService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmailNotificationServletWithAnnotationsTest {

    @InjectMocks
    private EmailNotificationServlet emailNotificationServlet;

    @Mock
    private EmailService emailService;

    @Mock
    private SlingHttpServletRequest request;

    @Mock
    private SlingHttpServletResponse response;

    @Captor
    ArgumentCaptor<String> argCaptor;

    @Test
    public void testDoGetMethod_success_ifRedirectUrlIsEmpty() throws IOException {
        when(request.getParameter("email")).thenReturn("xyz@abc.com");
        when(emailService.sendEmail(any())).thenReturn(EmailService.MailSendStatus.SUCCESS);
        emailNotificationServlet.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
        verify(response, times(1)).setStatus(HttpServletResponse.SC_OK);
        verify(response, atLeastOnce()).setStatus(HttpServletResponse.SC_OK);
        verify(response, atLeast(0)).setStatus(HttpServletResponse.SC_OK);
        verify(response, atMost(2)).setStatus(HttpServletResponse.SC_OK);

        verify(response, never()).setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

    @Test
    public void testDoGetMethod_success_ifRedirectUrlIsNotEmpty() throws IOException {
        when(request.getParameter("email")).thenReturn("xyz@abc.com");
        when(request.getParameter("redirectUrl")).thenReturn("www.google.com");
        when(emailService.sendEmail(any())).thenReturn(EmailService.MailSendStatus.SUCCESS);
        emailNotificationServlet.doGet(request, response);
        verify(response).sendRedirect(argCaptor.capture());
        Assert.assertEquals("www.google.com", argCaptor.getValue());
    }

    @Test
    public void testDoGetMethod_failure() throws IOException {
        when(request.getParameter("email")).thenReturn("xyz@abc.com");
        when(emailService.sendEmail(any())).thenReturn(EmailService.MailSendStatus.FAILED);
        emailNotificationServlet.doGet(request, response);
        verify(response).setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

    @Test
    public void testDoGet_receivedException() throws IOException {
        when(emailService.sendEmail(any())).thenThrow(new ConditionNotMetException(EmailService.MailSendStatus.RECEIVER_MAIL_IS_EMPTY.toString()));
        emailNotificationServlet.doGet(request, response);
        verify(response).setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

}