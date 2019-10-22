package com.hcl.medicalclaims.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.medicalclaims.dto.ClaimDetailsResponseDto;
import com.hcl.medicalclaims.dto.ClaimDto;
import com.hcl.medicalclaims.entity.ApproverDetails;
import com.hcl.medicalclaims.entity.ClaimDetails;
import com.hcl.medicalclaims.exception.ClaimDetailsNotfoundException;
import com.hcl.medicalclaims.repository.ApproverRepository;
import com.hcl.medicalclaims.repository.ClaimDetailsRepository;
import com.hcl.medicalclaims.repository.PolicyRepository;
import com.hcl.medicalclaims.util.ExceptionConstants;

/* 
*@author priyanka
*/
@Service
public class ClaimDetailsServiceImpl implements ClaimsDetailsService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClaimDetailsServiceImpl.class);
	@Autowired
	PolicyRepository policyRepository;
	@Autowired
	ClaimDetailsRepository claimDetailsRepository;
	@Autowired
	ApproverRepository approverRepository;
	private ClaimDto claimDto;
	/**
	 * @apiNote on the basis of policy_id
	 * @return claim details
	 */
	@Override
	public ClaimDetailsResponseDto getClaimDetails(Integer approverId) {
		LOGGER.info("service for getting claim details");
	 	List<ClaimDto> claimDetailss = new ArrayList<>();
	   
	 	ClaimDetailsResponseDto claimDetailsResponseDto = new ClaimDetailsResponseDto();
	 	Optional<ApproverDetails> findById = approverRepository.findById(approverId);
	 	
	 	ApproverDetails approverDetails = findById.get();	
	 	LOGGER.info("ROLE:"+approverDetails.getApproverRole());
	 	if(approverDetails.getApproverRole().equalsIgnoreCase("MANAGER"))
	 	{	
	 		LOGGER.info("inside manager"+approverDetails.getApproverRole());
	 		String approvalStatus="submitted";
				Optional<List<ClaimDetails>> claimDetailsOptional = claimDetailsRepository.findByClaimStatus(approvalStatus);
				
				
				if(!claimDetailsOptional.isPresent()) {
					throw new ClaimDetailsNotfoundException(ExceptionConstants.CLAIM_NOT_FOUND);
				}

				 List<ClaimDetails> claimDetails = claimDetailsOptional.get();
				 claimDetails.stream().forEach(claim -> {
					claimDto = new ClaimDto();
					BeanUtils.copyProperties(claim, claimDto);
					
				//	Optional<PolicyDetails> OptionalPloicy=claimDetailsRepository.findByPolicyDetails(claim.getClaimId());
					
					claimDto.setPolicyId(claimDetailsOptional.get().get(0).getPolicyDetails().getPolicyId());
					claimDetailss.add(claimDto);
				 });
	 	}else if (approverDetails.getApproverRole().equalsIgnoreCase("SENIOR MANAGER")) {
	 		String approvalStatus="forwarded";
	 		Optional<List<ClaimDetails>> claimDetailsOptional = claimDetailsRepository.findByClaimStatus(approvalStatus);
			if(!claimDetailsOptional.isPresent()) {
				throw new ClaimDetailsNotfoundException(ExceptionConstants.CLAIM_NOT_FOUND);
			}

			 List<ClaimDetails> claimDetails = claimDetailsOptional.get();
			 claimDetails.stream().forEach(claim -> {
				claimDto = new ClaimDto();
				BeanUtils.copyProperties(claim, claimDto);
				claimDto.setPolicyId(claimDetailsOptional.get().get(0).getPolicyDetails().getPolicyId());
				claimDetailss.add(claimDto);
			 });
		}
	 	claimDetailsResponseDto.setClaimDetails(claimDetailss);
		return claimDetailsResponseDto;
	 	
	}

}
