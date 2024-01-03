package com.faucet.bitcointestnetfaucet.controller;


import com.faucet.bitcointestnetfaucet.payload.DepositDTO;
import com.faucet.bitcointestnetfaucet.payload.MessageDTO;
import com.faucet.bitcointestnetfaucet.payload.ScheduleJobDTO;
import com.faucet.bitcointestnetfaucet.payload.response.TransactionResponseDTO;
import com.faucet.bitcointestnetfaucet.service.TransactionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/deposit"})
public class DepositController {
    private final TransactionService transactionService;

    @PostMapping({"/send"})
    public TransactionResponseDTO sendCoins(@RequestBody DepositDTO depositDTO) {
        return this.transactionService.sendCoins(depositDTO.getValue(), depositDTO.getTo());
    }

    @PostMapping({"/startScheduler"})
    public ScheduleJobDTO startScheduler(@RequestBody ScheduleJobDTO scheduleJobDTO) {
        return this.transactionService.startScheduledJob(scheduleJobDTO);
    }

    @GetMapping({"/stopScheduler"})
    public MessageDTO stopScheduler() {
        return this.transactionService.stopScheduledJob();
    }

    public DepositController(final TransactionService transactionService) {
        this.transactionService = transactionService;
    }
}
