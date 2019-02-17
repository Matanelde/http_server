package com.project.server;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class TimeCounterData {
    private List<Long> times = new LinkedList();
    private Integer clientId;

    public TimeCounterData(Integer clientId) {
        this.clientId = clientId;
    }

    public List<Long> getTimes() {
        return times;
    }

    public Integer getClientId() {
        return clientId;
    }

    @Override
    public String toString() {
        return "TimeCounterData{" +
                "times=" + times.stream()
                .map(Date::new).collect(Collectors.toList()) +
                ", clientId=" + clientId +
                '}';
    }
}
