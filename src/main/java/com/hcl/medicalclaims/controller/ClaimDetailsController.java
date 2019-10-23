package com.hcl.medicalclaims.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.medicalclaims.dto.ClaimDetailsResponseDto;
import com.hcl.medicalclaims.exception.ApproverNotExistsException;
import com.hcl.medicalclaims.service.ClaimsDetailsService;
/* 
*@author priyanka
*/
@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/claims")
public class ClaimDetailsController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClaimDetailsController.class);
	@Autowired
	ClaimsDetailsService claimsDetailsService;
	/**
	 * @apiNote on the basis of policy_id
	 * @return getting claim details .
	 * @throws ApproverNotExistsException 
	 */
	@GetMapping("/{approverId}")
	public ClaimDetailsResponseDto getClaimDetails(@PathVariable Integer approverId) throws ApproverNotExistsException
	{
		LOGGER.info("inside claim details controller");
		ClaimDetailsResponseDto claimDetailsResponseDto=claimsDetailsService.getClaimDetails(approverId);
		claimDetailsResponseDto.setMessage("Successfully getting claim details!!!");
		claimDetailsResponseDto.setStatusCode(201);
		return claimDetailsResponseDto;
		
	}

}
