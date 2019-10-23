package com.hcl.medicalclaims.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.medicalclaims.dto.ApproveRequestDto;
import com.hcl.medicalclaims.dto.ApproveResponseDto;
import com.hcl.medicalclaims.exception.ApproverNotExistsException;
import com.hcl.medicalclaims.exception.ClaimNumberNotExistsException;
import com.hcl.medicalclaims.service.ApproverService;

/**
 * The controller is for approving claims
 * @author Sharath G S
 * */
@RequestMapping("/api")
@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class ApproveController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApproveController.class);
	
	@Autowired
	ApproverService approveService;
	/**
	 * The controller is for approver who approves the claim
	 * @author Sharath G S
	 * @param approveRequest
	 * @return ApproveResponseDto
	 * @throws ClaimNumberNotExistsException 
	 * @throws ApproverNotExistsException 
	 */
	@PostMapping("/approver")
	public ResponseEntity<ApproveResponseDto> approveClaims( @Valid @RequestBody ApproveRequestDto approveRequest) throws ApproverNotExistsException, ClaimNumberNotExistsException
	{
		LOGGER.info("the approve controller is called");
		return new ResponseEntity<>(approveService.claimApproved(approveRequest),HttpStatus.OK);
	}
	
}
