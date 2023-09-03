package com.app.controller;

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

import com.app.dto.ApiResponse;
import com.app.entities.Business;
import com.app.entities.Campaign;
import com.app.service.BusinessService;
import com.app.service.CampaignService;
import com.app.service.InvestorService;

@RestController
@RequestMapping("/business")
public class BusinessController {
	
	@Autowired
	private BusinessService businessService;
	
	@Autowired
	private CampaignService campaignService;
	
	@Autowired
	private InvestorService investorService;
	
	@PostMapping
	public ResponseEntity<?> createBusiness(@RequestBody Business business) {
		return new ResponseEntity<>(new ApiResponse(businessService.createBusiness(business)), HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> getBusinessDetails(@RequestBody String email, @RequestBody String password) {
		return new ResponseEntity<>(businessService.getBusinessDetails(email, password), HttpStatus.OK);
	}
	
	@PutMapping("/businesses/{businessId}")
	public ResponseEntity<?> updateBusiness(@PathVariable Long businessId, @RequestBody Business business) {
		return new ResponseEntity<>(new ApiResponse(businessService.updateBusiness(businessId, business)), HttpStatus.OK);	
	}
	
	@DeleteMapping("/{businessId}")
	public ResponseEntity<?> deleteBusiness(@PathVariable Long businessId) {
		return new ResponseEntity<>(new ApiResponse(businessService.deleteBusiness(businessId)), HttpStatus.OK);
	}
	
	@GetMapping("/{businessId}/campaigns")
	public ResponseEntity<?> getCampaignsForBusiness(@PathVariable Long businessId) {
		return new ResponseEntity<>(campaignService.getCampaignsForBusiness(businessId), HttpStatus.OK);
	}

	@PostMapping("/campaigns")
	public ResponseEntity<?> createCampaign(@RequestBody Campaign campaign) {
		return new ResponseEntity<>(new ApiResponse(campaignService.createCampaign(campaign)), HttpStatus.CREATED);
	}

	@PutMapping("/campaigns/{id}")
	public ResponseEntity<?> updateCampaign(@PathVariable Long id, @RequestBody Campaign campaign) {
		return new ResponseEntity<>(new ApiResponse(campaignService.updateCampaign(id, campaign)), HttpStatus.CREATED);
	}

	@DeleteMapping("/campaigns/{id}")
	public ResponseEntity<?> deleteCampaign(@PathVariable Long id) {
		return new ResponseEntity<>(new ApiResponse(campaignService.deleteCampaign(id)), HttpStatus.OK);
	}

	@GetMapping("/campaigns/{campaignId}/investors")
	public ResponseEntity<?> getInvestorsForCampaign(@PathVariable Long campaignId) {
		return new ResponseEntity<>(investorService.getInvestorsForCampaign(campaignId), HttpStatus.OK);
	}

	@GetMapping("/{businessId}/investors")
	public ResponseEntity<?> getInvestorsForBusiness(@PathVariable Long businessId) {
		return new ResponseEntity<>(investorService.getInvestorsForBusiness(businessId), HttpStatus.OK);
	}
}
