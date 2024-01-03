package com.faucet.bitcointestnetfaucet.payload.response;

public class BalanceResponseDTO {
    String balance;
    String network;
    String receivingAddress;

    public String getBalance() {
        return this.balance;
    }

    public String getNetwork() {
        return this.network;
    }

    public String getReceivingAddress() {
        return this.receivingAddress;
    }

    public void setBalance(final String balance) {
        this.balance = balance;
    }

    public void setNetwork(final String network) {
        this.network = network;
    }

    public void setReceivingAddress(final String receivingAddress) {
        this.receivingAddress = receivingAddress;
    }

    public String toString() {
        String var10000 = this.getBalance();
        return "BalanceResponseDTO(balance=" + var10000 + ", network=" + this.getNetwork() + ", receivingAddress=" + this.getReceivingAddress() + ")";
    }

    public BalanceResponseDTO(final String balance, final String network, final String receivingAddress) {
        this.balance = balance;
        this.network = network;
        this.receivingAddress = receivingAddress;
    }

    public BalanceResponseDTO() {
    }
}
