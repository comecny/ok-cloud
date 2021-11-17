package com.example.exception;

import java.io.Serializable;

public interface Messageable extends Serializable {

    String getDefaultMessage();

    boolean hasDefaultMessage();

    String getMessageKey();

    Object[] getArgs();
}
