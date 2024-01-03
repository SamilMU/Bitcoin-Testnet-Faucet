package com.faucet.bitcointestnetfaucet.controller;

import com.faucet.bitcointestnetfaucet.payload.response.BalanceResponseDTO;
import com.faucet.bitcointestnetfaucet.service.WalletService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/wallet"})
public class BalanceController {
    private final WalletService walletService;

    @PostMapping({"/balance"})
    public BalanceResponseDTO getBalance(String network) {
        return this.walletService.getBalance(network);
    }

    public BalanceController(final WalletService walletService) {
        this.walletService = walletService;
    }
}
