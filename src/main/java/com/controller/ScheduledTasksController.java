package com.controller;

import com.constant.GlobalConstant;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ScheduledTasksController {
    private final RestTemplate restTemplate;

    public ScheduledTasksController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

//    @Scheduled(fixedRate = 5000)
    @Scheduled(cron = "0 59 23 * * ?")
    public void callApi() {
        // 调用登录注册统计接口
        String dayAmount = restTemplate.getForObject(
                GlobalConstant.IP+ GlobalConstant.API_PREFIX+"/statisticData/dayAmount",
                String.class);
        // 调用vip销售统计接口
        String dayVip = restTemplate.getForObject(
                GlobalConstant.IP+ GlobalConstant.API_PREFIX+"/statisticData/dayVip",
                String.class);
        // 调用接口调用接口
        String dayApi = restTemplate.getForObject(
                GlobalConstant.IP+ GlobalConstant.API_PREFIX+"/statisticData/dayApi",
                String.class);
    }
}