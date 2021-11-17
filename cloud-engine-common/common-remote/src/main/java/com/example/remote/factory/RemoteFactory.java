package com.example.remote.factory;

import com.example.remote.RemoteService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * rpc工厂
 */
public abstract class RemoteFactory implements ApplicationContextAware, RemoteService {

  public ApplicationContext applicationContext;

  public <T> T instance(Class<T> clazz) {
    return  applicationContext.getBean(clazz);
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }

}
