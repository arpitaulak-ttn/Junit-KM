package com.mysite.core.bean;

/**
 * The type Cta link data.
 */
public class CtaLinkData {

    private String link;
    private String target;

    public CtaLinkData(){

    }

    public CtaLinkData(String link, String target) {
        this.link = link;
        this.target = target;
    }

    /**
     * Gets link.
     *
     * @return the link
     */
    public String getLink() {
        return link;
    }

    /**
     * Sets link.
     *
     * @param link the link
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * Gets target.
     *
     * @return the target
     */
    public String getTarget() {
        return target;
    }

    /**
     * Sets target.
     *
     * @param target the target
     */
    public void setTarget(String target) {
        this.target = target;
    }
}
