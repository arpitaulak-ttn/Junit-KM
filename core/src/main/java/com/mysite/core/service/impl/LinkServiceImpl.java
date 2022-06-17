package com.mysite.core.service.impl;

import com.mysite.core.service.EmailService;
import com.mysite.core.service.LinkService;
import org.apache.commons.lang3.StringUtils;
import org.osgi.service.component.annotations.Component;

@Component(service = LinkService.class)
public class LinkServiceImpl implements LinkService {

    public static final String URL_HTTP_PROTOCOL = "http://";
    public static final String URL_HTTPS_PROTOCOL = "https://";
    public static final String NO_PROTOCOL = "//";
    public static final String FILE_EXT_PDF = ".pdf";
    public static final String WWW = "www.";
    public static final String JAVASCRIPT_METHOD_HREF = "javascript";
    public static final String HASH = "#";
    public static final String HTML_EXTN = ".html";

    @Override
    public String createLink(String link) {
        if (StringUtils.startsWithIgnoreCase(link, URL_HTTP_PROTOCOL)
                || StringUtils.startsWithIgnoreCase(link, URL_HTTPS_PROTOCOL)
                || StringUtils.contains(link, HTML_EXTN)
                || StringUtils.startsWithIgnoreCase(link, NO_PROTOCOL)
                || StringUtils.endsWithIgnoreCase(link, FILE_EXT_PDF)
                || StringUtils.startsWith(link, JAVASCRIPT_METHOD_HREF)
                || StringUtils.contains(link, HASH)) {
            return link;
        } else if (StringUtils.startsWithIgnoreCase(link, WWW)) {
            return URL_HTTPS_PROTOCOL + link;
        } else {
            if (StringUtils.isNotBlank(link)) {
                return link + HTML_EXTN;
            }
            return StringUtils.EMPTY;
        }
    }

    @Override
    public String linkTarget(String link) {
        if (StringUtils.startsWithIgnoreCase(link, URL_HTTP_PROTOCOL)
                || StringUtils.startsWithIgnoreCase(link, URL_HTTPS_PROTOCOL)
                || StringUtils.startsWithIgnoreCase(link, NO_PROTOCOL)
                || StringUtils.startsWithIgnoreCase(link, WWW)
                || StringUtils.endsWithIgnoreCase(link, FILE_EXT_PDF)) {
            return "_blank";
        } else {
            return "_self";
        }
    }
}
