package com.faucet.bitcointestnetfaucet.jobs;

import com.faucet.bitcointestnetfaucet.context.CurrentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DepositScheduler implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(DepositScheduler.class);
    final CurrentContext context;

    public DepositScheduler(CurrentContext context) {
        this.context = context;
    }

    public void run() {
        log.info("////////////////////////////////////////////////");
        log.info("Your job has been successfully executed");
        log.info("////////////////////////////////////////////////");
        this.context.transactionService.sendCoins(this.context.ScheduledDepositAmount, this.context.scheduledDepositReceivingAddress);
        log.info("Task executed");
    }
}


