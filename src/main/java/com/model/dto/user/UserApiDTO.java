package com.model.dto.user;

public class UserApiDTO {
    private String id;
    private String apiId;
    private String userApiId;
    private String apiName;
    private String requestTime;
    private String status;
    private int statusCode;
    private String reason;

    public String getId() {
        return id;
    }
    public String getApiId() { return apiId; }
    public String getUserApiId() {
        return userApiId;
    }
    public String getApiName() {
        return apiName;
    }
    public String getRequestTime() {
        return requestTime;
    }
    public String getStatus() {
        return status;
    }
    public int getStatusCode() {
        return statusCode;
    }
    public String getReason() {
        return reason;
    }
}
