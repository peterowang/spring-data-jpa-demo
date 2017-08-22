package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by BFD-593 on 2017/8/21.
 */
@Component
public class ScheduledTask {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTask.class);
    @Scheduled(cron = "0 0/2 * * 1-9 ?")//一到9月每2分钟执行
    public void cron(){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
        log.info("定时任务执行了.."+format.format(System.currentTimeMillis()));
    }
}
