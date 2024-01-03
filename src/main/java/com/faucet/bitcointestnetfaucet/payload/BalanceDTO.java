package com.faucet.bitcointestnetfaucet.payload;

import org.bitcoinj.core.Coin;

public class BalanceDTO {
    Coin balance;
    public Coin getBalance() {
        return this.balance;
    }
    public BalanceDTO(final Coin balance) {
        this.balance = balance;
    }
}
