package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.Campaign;
import com.app.entities.Transaction;
import com.app.service.CampaignService;
import com.app.service.TransactionService;

@RestController
@RequestMapping("/investor")
public class InvestorController {
    @Autowired
    private TransactionService transactionService;
    
    @Autowired
	private CampaignService campaignService;
	
	@GetMapping("/campaigns")
	public List<Campaign> getAllCampaigns() {
		return campaignService.getAllCampaigns();
	}

    @PostMapping("/transactions")
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.createTransaction(transaction);
    }

    @GetMapping("/transactions")
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }
}
