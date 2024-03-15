package com.controller;

import com.common.GlobalData;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ScheduledTasks {
    private final RestTemplate restTemplate;

    public ScheduledTasks(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

//    @Scheduled(fixedRate = 5000)
    @Scheduled(cron = "0 45 16 * * ?")
    public void callApi() {
        // 调用接口
        String result = restTemplate.getForObject(
                GlobalData.IP+GlobalData.API_PREFIX+"/statisticData/dayAmount",
                String.class);
        // 处理结果...
    }
}