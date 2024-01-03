package com.faucet.bitcointestnetfaucet.payload;

import jakarta.validation.constraints.NotEmpty;

public class ScheduleJobDTO {
    @NotEmpty String startTime;
    int interval = 480;
    String amount = "0.000101";
    @NotEmpty String receivingAddress = "2N9MQsfeexArCPaayqRMGZBEshnTe6nTwtL";
    String message;

    public String getStartTime() {
        return this.startTime;
    }

    public int getInterval() {
        return this.interval;
    }

    public String getAmount() {
        return this.amount;
    }

    public String getReceivingAddress() {
        return this.receivingAddress;
    }

    public String getMessage() {
        return this.message;
    }

    public void setStartTime(final String startTime) {
        this.startTime = startTime;
    }

    public void setInterval(final int interval) {
        this.interval = interval;
    }

    public void setAmount(final String amount) {
        this.amount = amount;
    }

    public void setReceivingAddress(final String receivingAddress) {
        this.receivingAddress = receivingAddress;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public ScheduleJobDTO(final String startTime, final int interval, final String amount, final String receivingAddress, final String message) {
        this.startTime = startTime;
        this.interval = interval;
        this.amount = amount;
        this.receivingAddress = receivingAddress;
        this.message = message;
    }

    public ScheduleJobDTO() {
    }
}
