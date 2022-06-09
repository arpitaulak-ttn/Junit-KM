package com.mysite.core.bean;

public class FormErrorResponse {

    private String reason;
    private String reasonCode;

    /**
     * Gets reason.
     *
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * Sets reason.
     *
     * @param reason the reason
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * Gets reason code.
     *
     * @return the reason code
     */
    public String getReasonCode() {
        return reasonCode;
    }

    /**
     * Sets reason code.
     *
     * @param reasonCode the reason code
     */
    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }
}
