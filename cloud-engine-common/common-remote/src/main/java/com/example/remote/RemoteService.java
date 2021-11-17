package com.example.remote;

public interface RemoteService {

    public <T> T instanceService(Class<T> clazz);
}
