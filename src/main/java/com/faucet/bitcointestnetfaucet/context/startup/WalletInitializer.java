package com.faucet.bitcointestnetfaucet.context.startup;


import com.faucet.bitcointestnetfaucet.context.CurrentContext;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.MoreExecutors;
import org.bitcoinj.core.*;
import org.bitcoinj.kits.WalletAppKit;
import org.bitcoinj.params.TestNet3Params;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class WalletInitializer implements ApplicationRunner {
    private static final Logger log = LoggerFactory.getLogger(WalletInitializer.class);
    @Autowired
    CurrentContext context;
    private boolean hasRun = false;

    public WalletInitializer() {
    }

    public void run(ApplicationArguments args) {
        if (!this.hasRun) {
            NetworkParameters networkParameters = TestNet3Params.get();
            WalletAppKit walletAppKit = new WalletAppKit(networkParameters, new File("./testnet-wallet"), "wallet_instance_1");
            walletAppKit.startAsync();
            walletAppKit.awaitRunning();
            walletAppKit.wallet().addCoinsReceivedEventListener((wallet, tx, prevBalance, newBalance) -> {
                final Coin value = tx.getValueSentToMe(wallet);
                log.info("Received tx for " + value.toFriendlyString());
                Futures.addCallback(tx.getConfidence().getDepthFuture(1), new FutureCallback<>() {
                    public void onSuccess(TransactionConfidence result) {
                        WalletInitializer.log.info("Received tx " + value.toFriendlyString() + " is confirmed. ");
                    }

                    public void onFailure(Throwable t) {
                    }
                }, MoreExecutors.directExecutor());
            });
            Address sendToAddress = LegacyAddress.fromKey(networkParameters, walletAppKit.wallet().currentReceiveKey());
            this.context.walletAppKit = walletAppKit;
            this.context.networkParameters = networkParameters;
            this.context.receivingAddress = sendToAddress.toString();
            this.context.walletBalance = walletAppKit.wallet().getBalance();
            log.info("Send coins to: " + sendToAddress);
            log.info("currentReceiveKey: " + walletAppKit.wallet().currentReceiveKey());
            log.info("Balance: " + walletAppKit.wallet().getBalance());
            this.hasRun = true;
        }

    }
}
