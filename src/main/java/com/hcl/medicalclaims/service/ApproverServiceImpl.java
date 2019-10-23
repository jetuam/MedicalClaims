package com.hcl.medicalclaims.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.medicalclaims.dto.ApproveRequestDto;
import com.hcl.medicalclaims.dto.ApproveResponseDto;
import com.hcl.medicalclaims.entity.ApproverDetails;
import com.hcl.medicalclaims.entity.ClaimDetails;
import com.hcl.medicalclaims.exception.ApproverNotExistsException;
import com.hcl.medicalclaims.exception.ClaimNumberNotExistsException;
import com.hcl.medicalclaims.repository.ApproveSummaryRepository;
import com.hcl.medicalclaims.repository.ApproverRepository;
import com.hcl.medicalclaims.repository.ClaimRepository;
import com.hcl.medicalclaims.repository.PolicyRepository;
import com.hcl.medicalclaims.util.ApproveUtil;
import com.hcl.medicalclaims.util.MedicalUtils;

/**
 * The approver Service for approve managers
 * 
 * @author Sharath G S
 *
 */
@Service
public class ApproverServiceImpl implements ApproverService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApproverServiceImpl.class);

	@Autowired
	ApproverRepository approverRepository;

	@Autowired
	ApproveSummaryRepository approveSummaryRepo;

	@Autowired
	ClaimRepository claimRepository;

	@Autowired
	PolicyRepository policyRepository;

	@Autowired
	ApproveUtil approveUtil;

	/**
	 * The service is called for approving the claims
	 * 
	 * @param ApproveRequestDto
	 * @author Sharath G S
	 * @return ApproveResponseDto
	 * @throws ApproverNotExistsException
	 * @throws ClaimNumberNotExistsException
	 */
	@Override
	public ApproveResponseDto claimApproved(ApproveRequestDto approveRequest)
			throws ApproverNotExistsException, ClaimNumberNotExistsException {
		LOGGER.info("the events for the approve request is called");

		ApproveResponseDto approveResponse = new ApproveResponseDto();
		Optional<ApproverDetails> approverDetails = approverRepository.findByapproverId(approveRequest.getApproverId());
		Optional<ClaimDetails> claimDetails = claimRepository.findByclaimId(approveRequest.getClaimId());

		if (approverDetails.isPresent()) {
			if (claimDetails.isPresent()) {
				/*
				 * if (approverDetails.get().getApproverRole().equals(MedicalUtils.MANAGER)) {
				 * approveResponse = approveUtil.approveManagerUtil(approverDetails,
				 * claimDetails, approveRequest); } else if
				 * (approverDetails.get().getApproverRole().equals(MedicalUtils.BRANCH_MANAGER))
				 * {
				 * 
				 * approveResponse = approveUtil.approveManagerUtil(approverDetails,
				 * claimDetails, approveRequest); }
				 */

				approveResponse = approveUtil.approveManagerUtil(approverDetails, claimDetails, approveRequest);

			} else {
				throw new ClaimNumberNotExistsException(MedicalUtils.CLAIM_NO_NOT_EXISTS);
			}
		} else {
			throw new ApproverNotExistsException(MedicalUtils.APPROVER_NOT_EXISTS);
		}
		return approveResponse;

	}

}
