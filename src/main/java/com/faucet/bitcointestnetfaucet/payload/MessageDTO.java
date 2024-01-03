package com.faucet.bitcointestnetfaucet.payload;

public class MessageDTO {
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageDTO(final String message) {
        this.message = message;
    }
}
