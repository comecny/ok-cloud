package com.example.core.common;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.StringUtils;

/**
 * 针对上报数据加载类
 *
 * metrics.report.system=true
 * metrics.report.jvm=true
 * metrics.report.tomcat=true
 */
@Log4j2
public class MetricsDataInit implements InitializingBean, ApplicationContextAware {

    private  ApplicationContext applicationContext ;

    @Value("${metrics.report.system}")
    private Boolean system;

    @Value("${metrics.report.jvm}")
    private Boolean jvm;

    @Value("${metrics.report.tomcat}")
    private Boolean tomcat;

    @Value("metrics.report.system.cron")
    private String systemCron;

    @Value("metrics.report.jvm.cron")
    private String jvmCron;

    @Value("metrics.report.tomcat.cron")
    private String tomcatCron;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (system){
            log.info("加载系统检测数据上报 ... ");
            if (StringUtils.isEmpty(systemCron)) System.setProperty("metrics.report.system.cron","1 * * * * ?");
            //创建系统数据对象
            ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) applicationContext;
            DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) configurableApplicationContext.getBeanFactory();
            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(SystemMetricsDataReport.class);
            defaultListableBeanFactory.registerBeanDefinition("com.example.core.common.SystemMetricsDataReport", beanDefinitionBuilder.getRawBeanDefinition());
            SystemMetricsDataReport bean = applicationContext.getBean(SystemMetricsDataReport.class);

        }
        if (jvm){
            log.info("加载jvm检测数据上报 ... ");
        }
        if (tomcat){
            log.info("加载tomcat检测数据上报 ...");
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
