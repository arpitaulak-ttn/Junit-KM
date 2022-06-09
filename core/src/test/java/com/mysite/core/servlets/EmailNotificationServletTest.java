package com.mysite.core.servlets;

import com.mysite.core.exception.ConditionNotMetException;
import com.mysite.core.service.EmailService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmailNotificationServletTest {

    @InjectMocks
    private EmailNotificationServlet emailNotificationServlet;

    @Mock
    private EmailService emailService;

    @Mock
    private SlingHttpServletRequest request;

    @Mock
    private SlingHttpServletResponse response;

    @Mock
    private ResourceResolver resourceResolver;

    @Mock
    private Resource resource;

    @Before
    public void before(){
        when(request.getResourceResolver()).thenReturn(resourceResolver);
        when(resourceResolver.getResource("/etc/notification/email.html")).thenReturn(resource);
    }

    @Test
    public void testDoGetMethodSuccess() throws IOException {
        when(request.getParameter("email")).thenReturn("xyz@abc.com");
        when(emailService.sendEmail(any())).thenReturn(EmailService.MailSendStatus.SUCCESS);
        emailNotificationServlet.doGet(request, response);
        verify(response).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    public void testDoGetFailed() throws IOException {
        when(emailService.sendEmail(any())).thenThrow(new ConditionNotMetException(EmailService.MailSendStatus.RECEIVER_MAIL_IS_EMPTY.toString()));
        emailNotificationServlet.doGet(request, response);
        verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    @Test
    public void testDoGetMethodRedirect() throws IOException {
        when(request.getParameter("email")).thenReturn("xyz@abc.com");
        when(request.getParameter("redirectUrl")).thenReturn("www.google.com");
        when(emailService.sendEmail(any())).thenReturn(EmailService.MailSendStatus.SUCCESS);
        emailNotificationServlet.doGet(request, response);
        verify(response).sendRedirect("www.google.com");
    }
}