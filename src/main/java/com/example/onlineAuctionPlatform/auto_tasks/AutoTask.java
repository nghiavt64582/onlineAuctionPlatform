package com.example.onlineAuctionPlatform.auto_tasks;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class AutoTask {

    @Scheduled(fixedRate = 5000)
    public void scheduleFixedRateTask() {
        System.out.println("Fixed rate task: " + System.currentTimeMillis());
    }
}