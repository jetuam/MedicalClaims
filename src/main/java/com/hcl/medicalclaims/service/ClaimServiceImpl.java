/**
 * 
 */
package com.hcl.medicalclaims.service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.medicalclaims.constants.MedicalClaimsConstants;
import com.hcl.medicalclaims.dto.AddClaimRequestDTO;
import com.hcl.medicalclaims.entity.ClaimDetails;
import com.hcl.medicalclaims.entity.Hospital;
import com.hcl.medicalclaims.entity.PolicyDetails;
import com.hcl.medicalclaims.exception.HospitalNotFoundException;
import com.hcl.medicalclaims.exception.PolicyNotFoundException;
import com.hcl.medicalclaims.repository.ClaimDetailsRepository;
import com.hcl.medicalclaims.repository.HospitalRepository;
import com.hcl.medicalclaims.repository.PolicyDetailsRepository;

/**
 * @author srinivas
 *
 */
@Service
public class ClaimServiceImpl implements ClaimService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClaimServiceImpl.class);

	@Autowired
	ClaimDetailsRepository claimDetailsRepository;
	@Autowired
	PolicyDetailsRepository policyDetailsRepository;
	@Autowired
	HospitalRepository hospitalRepository;
	Random random = new Random();

	/**
	 * @param addClaimRequest
	 * @return claimDetails
	 * @throws PolicyNotFoundException
	 * @throws HospitalNotFoundException
	 */
	@Override
	public ClaimDetails addClaim(AddClaimRequestDTO addClaimRequest)
			throws PolicyNotFoundException, HospitalNotFoundException {
		LOGGER.info("In addClaim() method of ClaimServiceImpl class----");
		ClaimDetails claimDetails = new ClaimDetails();
		BeanUtils.copyProperties(addClaimRequest, claimDetails);
		Optional<PolicyDetails> optionalPolicyDetails = policyDetailsRepository
				.findByPolicyNo(addClaimRequest.getPolicyNo());
		if (!optionalPolicyDetails.isPresent()) {
			throw new PolicyNotFoundException(MedicalClaimsConstants.POLICY_NOT_FOUND_EXCEPTION);
		}
		Optional<Hospital> findByHospitalName = hospitalRepository
				.findByHospitalName(addClaimRequest.getHospitalName());
		if (!findByHospitalName.isPresent()) {
			throw new HospitalNotFoundException(MedicalClaimsConstants.HOSPITAL_NOT_FOUND_EXCEPTION);
		}

		int nextInt = random.nextInt(12345);
		claimDetails.setClaimNo(nextInt);
		claimDetails.setClaimDate(LocalDate.now());
		claimDetails.setPolicyDetails(optionalPolicyDetails.get());
		claimDetails.setClaimUploadFilePath(addClaimRequest.getClaimUploadFilePath());
		claimDetails.setClaimStatus(MedicalClaimsConstants.CLAIM_STATUS);
		return claimDetailsRepository.save(claimDetails);
	}

}
