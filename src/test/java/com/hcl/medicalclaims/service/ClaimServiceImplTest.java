package com.hcl.medicalclaims.service;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;

import com.hcl.medicalclaims.dto.AddClaimRequestDTO;
import com.hcl.medicalclaims.entity.ClaimDetails;
import com.hcl.medicalclaims.entity.Hospital;
import com.hcl.medicalclaims.entity.PolicyDetails;
import com.hcl.medicalclaims.exception.HospitalNotFoundException;
import com.hcl.medicalclaims.exception.PolicyNotFoundException;
import com.hcl.medicalclaims.repository.ClaimDetailsRepository;
import com.hcl.medicalclaims.repository.HospitalRepository;
import com.hcl.medicalclaims.repository.PolicyDetailsRepository;

import junit.framework.Assert;

/**
 * @author srinivas
 * to test add claim against policy
 *
 */
@SuppressWarnings("deprecation")
@RunWith(MockitoJUnitRunner.Silent.class)
public class ClaimServiceImplTest {

	@Mock
	ClaimDetailsRepository claimDetailsRepository;
	@Mock
	PolicyDetailsRepository policyDetailsRepository;
	@Mock
	HospitalRepository hospitalRepository;
	@InjectMocks
	ClaimServiceImpl claimServiceImpl;

	private ClaimDetails addClaim;
	private Optional<Hospital> optionalHospital;
	private Optional<PolicyDetails> optionalPolicy;
	private ClaimDetails claimDetails;
	private AddClaimRequestDTO addClaimRequest;

	@Before
	public void setUp() {
		PolicyDetails policyDetails = new PolicyDetails();
		policyDetails.setPolicyId(1);
		policyDetails.setPolicyNo(12345);
		policyDetails.setPolicyType("medical");
		policyDetails.setClaimedAmount(1500.00);
		policyDetails.setEligibleAmount(3000.00);
		policyDetails.setPolicyHolderName("srinivas");
		optionalPolicy = Optional.of(policyDetails);

		Hospital hospital = new Hospital();
		hospital.setHospitalName("narayana");
		hospital.setHospitalId(1);
		optionalHospital = Optional.of(hospital);

		claimDetails = new ClaimDetails();
		claimDetails.setClaimNo(12345);
		addClaimRequest = new AddClaimRequestDTO();
		addClaimRequest.setClaimAmount(120.00);
		addClaimRequest.setPolicyNo(12345);
		addClaimRequest.setHospitalName("narayana");
		addClaimRequest.setAdmittedDate(LocalDate.now());
		addClaimRequest.setClaimUploadFilePath("fgjhj");
		addClaimRequest.setDiagnosis("wfhg");
		addClaimRequest.setDischargeDate(LocalDate.now());
		addClaimRequest.setDischargeSummary("fJHFG");
		addClaimRequest.setHospitalName("narayana");
		addClaimRequest.setNatureOfAilment("abcdef");
		addClaimRequest.setPolicyNo(12345);
		BeanUtils.copyProperties(addClaimRequest, claimDetails);
	}

	/**
	 * @throws PolicyNotFoundException
	 * @throws HospitalNotFoundException
	 */
	@Test
	public void testAddClaim() throws PolicyNotFoundException, HospitalNotFoundException {

		Mockito.when(policyDetailsRepository.findByPolicyNo(12345)).thenReturn(optionalPolicy);
		Mockito.when(hospitalRepository.findByHospitalName("narayana")).thenReturn(optionalHospital);
		Mockito.when(claimDetailsRepository.save(Mockito.any())).thenReturn(claimDetails);

		addClaim = claimServiceImpl.addClaim(addClaimRequest);

		Assert.assertNotNull(addClaim);

	}

	/**
	 * @throws PolicyNotFoundException
	 * @throws HospitalNotFoundException
	 */
	@Test(expected = PolicyNotFoundException.class)
	public void testAddClaimForPolicyNotFoundException() throws PolicyNotFoundException, HospitalNotFoundException {


		PolicyDetails policyDetails = new PolicyDetails();
		policyDetails.setPolicyId(1);
		policyDetails.setPolicyNo(12345);
		policyDetails.setPolicyType("medical");
		policyDetails.setClaimedAmount(1500.00);
		policyDetails.setEligibleAmount(3000.00);
		policyDetails.setPolicyHolderName("srinivas");
		Optional<PolicyDetails> optionalPolicy = Optional.of(policyDetails);

		Hospital hospital = new Hospital();
		hospital.setHospitalName("narayana");
		hospital.setHospitalId(1);
		Optional<Hospital> optionalHospital = Optional.of(hospital);

		ClaimDetails claimDetails = new ClaimDetails();
		claimDetails.setClaimNo(12345);
		AddClaimRequestDTO addClaimRequest = new AddClaimRequestDTO();
		addClaimRequest.setClaimAmount(120.00);
		addClaimRequest.setPolicyNo(12345);
		addClaimRequest.setHospitalName("narayana");
		addClaimRequest.setAdmittedDate(LocalDate.now());
		addClaimRequest.setClaimUploadFilePath("fgjhj");
		addClaimRequest.setDiagnosis("wfhg");
		addClaimRequest.setDischargeDate(LocalDate.now());
		addClaimRequest.setDischargeSummary("fJHFG");
		addClaimRequest.setHospitalName("narayana");
		addClaimRequest.setNatureOfAilment("abcdef");
		addClaimRequest.setPolicyNo(12345);
		BeanUtils.copyProperties(addClaimRequest, claimDetails);


		Mockito.when(policyDetailsRepository.findByPolicyNo(123)).thenReturn(optionalPolicy);
		Mockito.when(hospitalRepository.findByHospitalName("narayana")).thenReturn(optionalHospital);
		Mockito.when(claimDetailsRepository.save(Mockito.any())).thenReturn(claimDetails);

		addClaim = claimServiceImpl.addClaim(addClaimRequest);

		Assert.assertNotNull(addClaim);

	}

	/**
	 * @throws PolicyNotFoundException
	 * @throws HospitalNotFoundException
	 */
	@Test(expected = HospitalNotFoundException.class)
	public void testAddClaimForHospitalNotFoundException() throws PolicyNotFoundException, HospitalNotFoundException {


		PolicyDetails policyDetails = new PolicyDetails();
		policyDetails.setPolicyId(1);
		policyDetails.setPolicyNo(12345);
		policyDetails.setPolicyType("medical");
		policyDetails.setClaimedAmount(1500.00);
		policyDetails.setEligibleAmount(3000.00);
		policyDetails.setPolicyHolderName("srinivas");
		Optional<PolicyDetails> optionalPolicy = Optional.of(policyDetails);

		Hospital hospital = new Hospital();
		hospital.setHospitalName("narayana");
		hospital.setHospitalId(1);
		Optional<Hospital> optionalHospital = Optional.of(hospital);

		ClaimDetails claimDetails = new ClaimDetails();
		claimDetails.setClaimNo(12345);
		AddClaimRequestDTO addClaimRequest = new AddClaimRequestDTO();
		addClaimRequest.setClaimAmount(120.00);
		addClaimRequest.setPolicyNo(12345);
		addClaimRequest.setHospitalName("narayana");
		addClaimRequest.setAdmittedDate(LocalDate.now());
		addClaimRequest.setClaimUploadFilePath("fgjhj");
		addClaimRequest.setDiagnosis("wfhg");
		addClaimRequest.setDischargeDate(LocalDate.now());
		addClaimRequest.setDischargeSummary("fJHFG");
		addClaimRequest.setHospitalName("narayana");
		addClaimRequest.setNatureOfAilment("abcdef");
		addClaimRequest.setPolicyNo(12345);
		BeanUtils.copyProperties(addClaimRequest, claimDetails);


		Mockito.when(policyDetailsRepository.findByPolicyNo(12345)).thenReturn(optionalPolicy);
		Mockito.when(hospitalRepository.findByHospitalName("n")).thenReturn(optionalHospital);
		Mockito.when(claimDetailsRepository.save(Mockito.any())).thenReturn(claimDetails);

		addClaim = claimServiceImpl.addClaim(addClaimRequest);

		Assert.assertNotNull(addClaim);

	}
}
