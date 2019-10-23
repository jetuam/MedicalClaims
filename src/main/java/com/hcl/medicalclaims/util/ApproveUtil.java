package com.hcl.medicalclaims.util;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hcl.medicalclaims.dto.ApproveRequestDto;
import com.hcl.medicalclaims.dto.ApproveResponseDto;
import com.hcl.medicalclaims.entity.ApproverDetails;
import com.hcl.medicalclaims.entity.ApproverSummary;
import com.hcl.medicalclaims.entity.ClaimDetails;
import com.hcl.medicalclaims.entity.PolicyDetails;
import com.hcl.medicalclaims.repository.ApproveSummaryRepository;
import com.hcl.medicalclaims.repository.ApproverRepository;
import com.hcl.medicalclaims.repository.ClaimRepository;
import com.hcl.medicalclaims.repository.PolicyRepository;

@Component
public class ApproveUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApproveUtil.class);
	
	@Autowired
	ApproverRepository approverRepository;
	
	@Autowired
	ApproveSummaryRepository approveSummaryRepo;
	
	@Autowired
	ClaimRepository claimRepository;
	
	@Autowired
	PolicyRepository policyRepository;
	
	public ApproveResponseDto approveManagerUtil(Optional<ApproverDetails> approverDetails, Optional<ClaimDetails> claimDetails, ApproveRequestDto approveRequest)
	{
		
		ApproveResponseDto approveResponse = new ApproveResponseDto();
		ClaimDetails claim = new ClaimDetails();
		ApproverSummary approveSummary = new ApproverSummary();
		
		LOGGER.info("inside if statement for approverDetails");
		approveSummary.setApproverId(approverDetails.get().getApproverId());
		approveSummary.setApproverRole(approverDetails.get().getApproverRole());
		ApproverSummary approve = approveSummaryRepo.save(approveSummary);
		
		ClaimDetails claims = new ClaimDetails();
		claimDetails.get().setClaimStatus(approveRequest.getClaimStatus());
		claimDetails.get().setApproverSummaryId(approve.getApproverSummaryId());
		BeanUtils.copyProperties(claimDetails.get(), claims);
		claimRepository.save(claims);
		
		
		Optional<PolicyDetails> policyDeduction =	policyRepository.findById(approveRequest.getPolicyId());
		if(policyDeduction.isPresent() && approveRequest.getClaimStatus().equals(MedicalUtils.APPROVED))
		{
			
			Double amountDeduction = claimDetails.get().getClaimAmount() + policyDeduction.get().getClaimedAmount();
			PolicyDetails policyDeduct = new PolicyDetails();						
			BeanUtils.copyProperties(policyDeduction.get(), policyDeduct);
			policyDeduct.setClaimedAmount(amountDeduction);
			policyRepository.save(policyDeduct);
		}
		approveResponse.setMessage(MedicalUtils.CLAIM_APPROVED);
		approveResponse.setPolicyNo(approveRequest.getPolicyId());
		approveResponse.setStatusCode(MedicalUtils.POLICY_HTTP_SUCCESS);
		return approveResponse;
	}
}
