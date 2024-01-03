package com.faucet.bitcointestnetfaucet.payload;

public class DepositDTO {
    String value;
    String to;

    public void setValue(String value) {
        this.value = value;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getValue() {
        return this.value;
    }

    public DepositDTO(final String value, final String to) {
        this.value = value;
        this.to = to;
    }
}
