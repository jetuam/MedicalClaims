/**
 * 
 */
package com.hcl.medicalclaims.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.medicalclaims.constants.MedicalClaimsConstants;
import com.hcl.medicalclaims.dto.AddClaimRequestDTO;
import com.hcl.medicalclaims.dto.AddClaimResponseDTO;
import com.hcl.medicalclaims.entity.ClaimDetails;
import com.hcl.medicalclaims.exception.HospitalNotFoundException;
import com.hcl.medicalclaims.exception.PolicyNotFoundException;
import com.hcl.medicalclaims.service.ClaimService;

/**
 * @author srinivas
 * To add Claim against policy
 */
@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class ClaimController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClaimController.class);
	@Autowired
	private ClaimService claimService;

	/**
	 * @param addClaimRequest
	 * @return addClaimResponseDTO
	 * @throws PolicyNotFoundException
	 * @throws HospitalNotFoundException
	 * to add medical claim against Policy
	 */
	@PostMapping("/claims")
	public AddClaimResponseDTO addClaim(@Valid @RequestBody AddClaimRequestDTO addClaimRequest)
			throws PolicyNotFoundException, HospitalNotFoundException {
		LOGGER.info("In addClaim method of ClaimController class-----");
		ClaimDetails addClaim = claimService.addClaim(addClaimRequest);
		AddClaimResponseDTO addClaimResponse = new AddClaimResponseDTO();
		addClaimResponse.setClaimNo(addClaim.getClaimNo());
		addClaimResponse.setMessage(MedicalClaimsConstants.ADD_CLAIM_SUCCESS);
		addClaimResponse.setStatusCode(MedicalClaimsConstants.POST_STATUS_CODE);
		return addClaimResponse;
	}
}