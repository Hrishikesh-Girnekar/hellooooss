package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.Business;
import com.app.entities.Campaign;
import com.app.entities.Investor;
import com.app.entities.Transaction;
import com.app.service.AdminService;
import com.app.service.BusinessService;
import com.app.service.CampaignService;
import com.app.service.InvestorService;
import com.app.service.TransactionService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private TransactionService transactionService;

	@Autowired
	private CampaignService campaignService;

	@Autowired
	private BusinessService businessService;

	@Autowired
	private InvestorService investorService;
	
	@PostMapping("/login")
	public ResponseEntity<?> getAdminDetails(@RequestBody String email, @RequestBody String password) {
		return new ResponseEntity<>(adminService.getAdminDetails(email, password), HttpStatus.OK);
	}

	// View all transactions
	@GetMapping("/transactions")
	public ResponseEntity<List<Transaction>> getAllTransactions() {
		List<Transaction> transactions = transactionService.getAllTransactions();
		return ResponseEntity.ok(transactions);
	}

	// View all campaigns
	@GetMapping("/campaigns")
	public ResponseEntity<List<Campaign>> getAllCampaigns() {
		List<Campaign> campaigns = campaignService.getAllCampaigns();
		return ResponseEntity.ok(campaigns);
	}

	// View campaigns for a particular business
	@GetMapping("/businesses/{businessId}/campaigns")
	public ResponseEntity<List<Campaign>> getCampaignsForBusiness(@PathVariable Long businessId) {
		List<Campaign> campaigns = campaignService.getCampaignsForBusiness(businessId);
		return ResponseEntity.ok(campaigns);
	}

	// View transactions for a particular campaign
	@GetMapping("/campaigns/{campaignId}/transactions")
	public ResponseEntity<List<Transaction>> getTransactionsForCampaign(@PathVariable Long campaignId) {
		List<Transaction> transactions = transactionService.getTransactionsForCampaign(campaignId);
		return ResponseEntity.ok(transactions);
	}

	// View transactions for a particular investor
	@GetMapping("/investors/{investorId}/transactions")
	public ResponseEntity<List<Transaction>> getTransactionsForInvestor(@PathVariable Long investorId) {
		List<Transaction> transactions = transactionService.getTransactionsForInvestor(investorId);
		return ResponseEntity.ok(transactions);
	}

	// View investors for a particular campaign
	@GetMapping("/campaigns/{campaignId}/investors")
	public ResponseEntity<List<Investor>> getInvestorsForCampaign(@PathVariable Long campaignId) {
		List<Investor> investors = investorService.getInvestorsForCampaign(campaignId);
		return ResponseEntity.ok(investors);
	}

	// View investors for a particular business
	@GetMapping("/businesses/{businessId}/investors")
	public ResponseEntity<List<Investor>> getInvestorsForBusiness(@PathVariable Long businessId) {
		List<Investor> investors = investorService.getInvestorsForBusiness(businessId);
		return ResponseEntity.ok(investors);
	}

	// Approve a campaign
	@PostMapping("/campaigns/{campaignId}/approve")
	public ResponseEntity<String> approveCampaign(@PathVariable Long campaignId) {
		campaignService.approveCampaign(campaignId);
		return ResponseEntity.ok("Campaign approved successfully");
	}

	// Manage business
	@PutMapping("/businesses/{businessId}")
	public ResponseEntity<String> updateBusiness(@PathVariable Long businessId, @RequestBody Business business) {
		businessService.updateBusiness(businessId, business);
		return ResponseEntity.ok("Business updated successfully");
	}

	// Manage investors
	@PutMapping("/investors/{investorId}")
	public ResponseEntity<String> updateInvestor(@PathVariable Long investorId, @RequestBody Investor investor) {
		investorService.updateInvestor(investorId, investor);
		return ResponseEntity.ok("Investor updated successfully");
	}

	// View list of businesses
	@GetMapping("/businesses")
	public ResponseEntity<List<Business>> getAllBusinesses() {
		List<Business> businesses = businessService.getAllBusinesses();
		return ResponseEntity.ok(businesses);
	}

	// Delete a business
	@DeleteMapping("/businesses/{businessId}")
	public ResponseEntity<String> deleteBusiness(@PathVariable Long businessId) {
		businessService.deleteBusiness(businessId);
		return ResponseEntity.ok("Business deleted successfully");
	}

	// Remove a campaign
	@DeleteMapping("/campaigns/{campaignId}")
	public ResponseEntity<String> removeCampaign(@PathVariable Long campaignId) {
		campaignService.removeCampaign(campaignId);
		return ResponseEntity.ok("Campaign removed successfully");
	}

	// Approve a business
	@PostMapping("/businesses/{businessId}/approve")
	public ResponseEntity<String> approveBusiness(@PathVariable Long businessId) {
		businessService.approveBusiness(businessId);
		return ResponseEntity.ok("Business approved successfully");
	}

	// View list of investors
	@GetMapping("/investors")
	public ResponseEntity<List<Investor>> getAllInvestors() {
		List<Investor> investors = investorService.getAllInvestors();
		return ResponseEntity.ok(investors);
	}

	// Delete an investor
	@DeleteMapping("/investors/{investorId}")
	public ResponseEntity<String> deleteInvestor(@PathVariable Long investorId) {
		investorService.deleteInvestor(investorId);
		return ResponseEntity.ok("Investor deleted successfully");
	}
}
