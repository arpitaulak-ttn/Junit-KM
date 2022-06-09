package com.mysite.core.exception;

/**
 * The type Condition not met exception.
 */
public class ConditionNotMetException extends RuntimeException  {

    /**
     * Instantiates a new Condition not met exception.
     *
     * @param msg the msg
     */
    public ConditionNotMetException(String msg) {
        super(msg);
    }
}
