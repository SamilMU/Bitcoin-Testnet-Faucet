package com.faucet.bitcointestnetfaucet.service;

import com.faucet.bitcointestnetfaucet.payload.MessageDTO;
import com.faucet.bitcointestnetfaucet.payload.ScheduleJobDTO;
import com.faucet.bitcointestnetfaucet.payload.response.TransactionResponseDTO;

public interface TransactionService {
    TransactionResponseDTO sendCoins(String value, String to);

    ScheduleJobDTO startScheduledJob(ScheduleJobDTO scheduleJobDTO);

    MessageDTO stopScheduledJob();
}
