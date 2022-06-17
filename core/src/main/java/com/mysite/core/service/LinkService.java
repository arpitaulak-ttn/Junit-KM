package com.mysite.core.service;

/**
 * The interface Link service.
 */
public interface LinkService {

    /**
     * Create link string.
     *
     * @param link the link
     * @return the string
     */
    public String createLink(String link);

    /**
     * Link target string.
     *
     * @param link the link
     * @return the string
     */
    public String linkTarget(String link);
}
