package com.model.dto.user;

public class AccountFrozeDTO {
    private String id;
    private String frozeId;
    private String frozeType;
    private String frozeReason;
    private String startTime;
    private String endTime;

    public String getId() { return id; }

    public String getFrozeId() {
        return frozeId;
    }
    public String getFrozeType() {
        return frozeType;
    }
    public String getFrozeReason() {
        return frozeReason;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }
}
