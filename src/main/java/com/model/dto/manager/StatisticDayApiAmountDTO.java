package com.model.dto.manager;

public class StatisticDayApiAmountDTO {
    private String id;
    private String date;
    private String apiName;
    private String successAmount;
    private String failAmount;

    public String getId() {
        return id;
    }
    public String getDate() {
        return date;
    }
    public String getApiName() {
        return apiName;
    }
    public String getSuccessAmount() {
        return successAmount;
    }
    public String getFailAmount() {
        return failAmount;
    }
}
