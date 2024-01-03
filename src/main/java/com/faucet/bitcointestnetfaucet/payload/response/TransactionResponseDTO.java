package com.faucet.bitcointestnetfaucet.payload.response;

public class TransactionResponseDTO {
    String message;
    String sentTo;
    String amount;
    String txId;

    public String getMessage() {
        return this.message;
    }

    public String getSentTo() {
        return this.sentTo;
    }

    public String getAmount() {
        return this.amount;
    }

    public String getTxId() {
        return this.txId;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public void setSentTo(final String sentTo) {
        this.sentTo = sentTo;
    }

    public void setAmount(final String amount) {
        this.amount = amount;
    }

    public void setTxId(final String txId) {
        this.txId = txId;
    }

    public String toString() {
        String var10000 = this.getMessage();
        return "TransactionResponseDTO(message=" + var10000 + ", sentTo=" + this.getSentTo() + ", amount=" + this.getAmount() + ", txId=" + this.getTxId() + ")";
    }

    public TransactionResponseDTO() {
    }

    public TransactionResponseDTO(final String message, final String sentTo, final String amount, final String txId) {
        this.message = message;
        this.sentTo = sentTo;
        this.amount = amount;
        this.txId = txId;
    }
}
