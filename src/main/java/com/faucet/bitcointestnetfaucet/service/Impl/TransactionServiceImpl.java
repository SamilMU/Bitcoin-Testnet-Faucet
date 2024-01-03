package com.faucet.bitcointestnetfaucet.service.Impl;


import com.faucet.bitcointestnetfaucet.context.CurrentContext;
import com.faucet.bitcointestnetfaucet.payload.MessageDTO;
import com.faucet.bitcointestnetfaucet.payload.ScheduleJobDTO;
import com.faucet.bitcointestnetfaucet.payload.response.TransactionResponseDTO;
import com.faucet.bitcointestnetfaucet.service.TransactionService;
import com.faucet.bitcointestnetfaucet.utils.TimeUtils;
import com.google.common.util.concurrent.MoreExecutors;
import org.bitcoinj.core.Address;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.InsufficientMoneyException;
import org.bitcoinj.core.LegacyAddress;
import org.bitcoinj.wallet.SendRequest;
import org.bitcoinj.wallet.Wallet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TransactionServiceImpl implements TransactionService {
    private static final Logger log = LoggerFactory.getLogger(TransactionServiceImpl.class);
    @Autowired
    private CurrentContext currentContext;

    public TransactionServiceImpl() {
    }

    public TransactionResponseDTO sendCoins(String value, String to) {
        this.fillContextIfEmpty();

        try {
            Address toAddress = LegacyAddress.fromBase58(this.currentContext.networkParameters, to);
            double amountToWithdrawBTC = Double.parseDouble(value);
            long amountToWithdrawSatoshis = Coin.parseCoin(String.valueOf(amountToWithdrawBTC)).getValue();
            TransactionResponseDTO responseDTO;
            if (this.currentContext.walletBalance.compareTo(Coin.valueOf(amountToWithdrawSatoshis)) >= 0) {
                SendRequest sendRequest = SendRequest.to(toAddress, Coin.parseCoin(value));
                sendRequest.feePerKb = Coin.parseCoin("0.0005");
                Wallet.SendResult sendResult = this.currentContext.walletAppKit.wallet().sendCoins(this.currentContext.walletAppKit.peerGroup(), sendRequest);
                sendResult.broadcastComplete.addListener(() -> log.warn("Sent coins onwards! Transaction hash is " + sendResult.tx.getTxId()), MoreExecutors.directExecutor());

                try {
                    TimeUtils.sleep(2500);
                    sendResult.tx.getConfidence().getDepthFuture(0).get();
                    log.info("Transaction successfully confirmed: " + sendResult.tx.getTxId());
                    responseDTO = new TransactionResponseDTO("Transaction sent!", to, value, sendResult.tx.getTxId().toString());
                } catch (Exception var12) {
                    responseDTO = new TransactionResponseDTO("An error has occurred!", null, null, null);
                    log.error("Error occurred while waiting for confirmation: " + var12.getMessage());
                }

                return responseDTO;
            } else {
                responseDTO = new TransactionResponseDTO("Your balance is insufficient", null, null, null);
                return responseDTO;
            }
        } catch (InsufficientMoneyException var13) {
            throw new RuntimeException(var13);
        }
    }

    public ScheduleJobDTO startScheduledJob(ScheduleJobDTO scheduleJobDTO) {
        this.fillContextIfEmpty();
        this.currentContext.scheduledDepositReceivingAddress = scheduleJobDTO.getReceivingAddress();
        this.currentContext.ScheduledDepositAmount = scheduleJobDTO.getAmount();
        this.currentContext.startScheduler(scheduleJobDTO);
        scheduleJobDTO.setMessage("Your job has been successfully scheduled");
        return scheduleJobDTO;
    }

    public MessageDTO stopScheduledJob() {
        this.currentContext.killScheduler();
        return new MessageDTO("Your scheduled job has been successfully cancelled");
    }

    private void fillContextIfEmpty() {
        this.currentContext.transactionService = Objects.isNull(this.currentContext.transactionService) ? this : this.currentContext.transactionService;
    }
}
