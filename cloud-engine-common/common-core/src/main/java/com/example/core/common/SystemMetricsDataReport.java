package com.example.core.common;

import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;

@Log4j2
public class SystemMetricsDataReport {

    @Scheduled(cron = "${metrics.report.system.cron}")
    public void report(){
        log.info("开始进行系统数据上报 .. ");
    }
}
