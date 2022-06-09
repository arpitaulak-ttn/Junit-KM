package com.mysite.core.servlets;

import com.mysite.core.exception.ConditionNotMetException;
import com.mysite.core.service.EmailService;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component(service = Servlet.class,
        property = {
                "sling.servlet.methods=" + HttpConstants.METHOD_GET,
                "sling.servlet.resourceTypes=mysite/components/form/button",
                "sling.servlet.selectors=email",
                "sling.servlet.extensions=html"
        })
public class EmailNotificationServlet extends SlingAllMethodsServlet {

    @Reference
    private EmailService emailService;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws RuntimeException, IOException {
        try {
            String body = Optional.of(request).map(req -> req.getParameter("body")).orElse(StringUtils.EMPTY);
            String subject = Optional.of(request).map(req -> req.getParameter("subject")).orElse(StringUtils.EMPTY);
            String senderEmail = Optional.of(request).map(req -> req.getParameter("email")).orElse(null);
            String redirectUrl = Optional.of(request).map(req -> req.getParameter("redirectUrl")).orElse(StringUtils.EMPTY);

            Map<String, String> emailParams = new HashMap<>();
            emailParams.put("body", body);
            emailParams.put("receiverEmail", senderEmail);
            emailParams.put("subject", subject);

            EmailService.MailSendStatus mailSendStatus = emailService.sendEmail(emailParams);
            if (mailSendStatus == EmailService.MailSendStatus.SUCCESS){
                response.setStatus(HttpServletResponse.SC_OK);
                if (StringUtils.isNotEmpty(redirectUrl)){
                    response.sendRedirect(redirectUrl);
                }
            }
        } catch (ConditionNotMetException e){
            response.setStatus(HttpStatus.SC_BAD_REQUEST);
        }
    }
}
