package com.faucet.bitcointestnetfaucet.service.Impl;


import com.faucet.bitcointestnetfaucet.context.CurrentContext;
import com.faucet.bitcointestnetfaucet.payload.response.BalanceResponseDTO;
import com.faucet.bitcointestnetfaucet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
public class WalletServiceImpl implements WalletService {
    @Autowired
    private CurrentContext currentContext;

    public WalletServiceImpl() {
    }

    public BalanceResponseDTO getBalance(String network) {
        this.fillContextIfEmpty();
        return new BalanceResponseDTO(String.valueOf((new BigDecimal(this.currentContext.walletAppKit.wallet().getBalance().value)).movePointLeft(8)), this.currentContext.networkParameters.getId(), this.currentContext.receivingAddress);
    }

    private void fillContextIfEmpty() {
        this.currentContext.walletService = Objects.isNull(this.currentContext.walletService) ? this : this.currentContext.walletService;
    }
}
