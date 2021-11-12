package com.example.core.exception;

import java.io.Serializable;

public interface Messageable extends Serializable {

    String getDefaultMessage();

    boolean hasDefaultMessage();

    String getMessageKey();

    Object[] getArgs();
}
