package com.example.core.exception;

public class RBaseException extends Exception implements Messageable{

    @Override
    public String getDefaultMessage() {
        return null;
    }

    @Override
    public boolean hasDefaultMessage() {
        return false;
    }

    @Override
    public String getMessageKey() {
        return null;
    }

    @Override
    public Object[] getArgs() {
        return new Object[0];
    }
}
