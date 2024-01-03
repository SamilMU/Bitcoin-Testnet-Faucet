package com.faucet.bitcointestnetfaucet.context;


import com.faucet.bitcointestnetfaucet.jobs.DepositScheduler;
import com.faucet.bitcointestnetfaucet.payload.ScheduleJobDTO;
import com.faucet.bitcointestnetfaucet.service.Impl.TransactionServiceImpl;
import com.faucet.bitcointestnetfaucet.service.Impl.WalletServiceImpl;
import com.faucet.bitcointestnetfaucet.utils.TimeUtils;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.kits.WalletAppKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CurrentContext {
    private static final Logger log = LoggerFactory.getLogger(CurrentContext.class);
    public WalletAppKit walletAppKit;
    public NetworkParameters networkParameters;
    public String receivingAddress;
    public TransactionServiceImpl transactionService;
    public WalletServiceImpl walletService;
    public String scheduledDepositReceivingAddress;
    public String ScheduledDepositAmount;
    public Coin walletBalance;
    public ScheduledExecutorService executorService;

    public CurrentContext() {
    }

    public static CurrentContext init() {
        return new CurrentContext();
    }

    public void startScheduler(ScheduleJobDTO scheduledJobDTO) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        String[] splittedTime = scheduledJobDTO.getStartTime().split(":");
        long initialDelay = TimeUtils.calculateInitialDelay(Integer.parseInt(splittedTime[0]), Integer.parseInt(splittedTime[1]), Integer.parseInt(splittedTime[2]));
        executorService.scheduleAtFixedRate(new DepositScheduler(this), initialDelay, scheduledJobDTO.getInterval(), TimeUnit.MINUTES);
        log.info("Your task has been scheduled using ScheduledExecutorService and will execute in :" + initialDelay);
        this.executorService = executorService;
    }

    public void killScheduler() {
        this.executorService.shutdown();
    }
}
