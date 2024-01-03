package com.faucet.bitcointestnetfaucet.config;

import com.faucet.bitcointestnetfaucet.context.CurrentContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WalletConfiguration {
    public WalletConfiguration() {
    }

    @Bean
    public CurrentContext currentContext() {
        return CurrentContext.init();
    }
}

