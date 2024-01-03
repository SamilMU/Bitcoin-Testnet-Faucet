package com.faucet.bitcointestnetfaucet.service;

import com.faucet.bitcointestnetfaucet.payload.response.BalanceResponseDTO;

public interface WalletService {
    BalanceResponseDTO getBalance(String network);
}
