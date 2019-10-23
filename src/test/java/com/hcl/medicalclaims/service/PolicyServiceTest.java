package com.hcl.medicalclaims.service;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.medicalclaims.dto.PolicyResponseDetailsDto;
import com.hcl.medicalclaims.entity.PolicyDetails;
import com.hcl.medicalclaims.exception.PolicyNotExistsException;
import com.hcl.medicalclaims.repository.PolicyRepository;
import com.hcl.medicalclaims.util.MedicalUtils;

/**
 * Test cases for policy details services
 * @author Sharath G S
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class PolicyServiceTest {

	
	@Mock
	PolicyRepository policyRepository;
	
	@InjectMocks
	PolicyService policyService;
	
	PolicyDetails policyDetails = null;
	PolicyDetails policyDetailsNull = null;
	
	@Before
	public void setUp()
	{
		policyDetails = new PolicyDetails();
		policyDetails.setClaimedAmount((double) 50000);
		policyDetails.setEligibleAmount((double) 30000);
		policyDetails.setPolicyHolderName("Sharath");
		policyDetails.setPolicyId(1);
		policyDetails.setPolicyNo(1234);
		policyDetails.setPolicyType("DENTAL");
	}
	
	/**
	 * Positive test case for policy details for given policy Number
	 * @author Sharath G S
	 * @throws PolicyNotExistsException
	 */
	@Test
	public void policyDetailsTest() throws PolicyNotExistsException
	{
		Mockito.when(policyRepository.findBypolicyNo(Mockito.anyInt())).thenReturn(Optional.of(policyDetails));
		PolicyResponseDetailsDto policyResponse = policyService.getPolicyDetails(policyDetails.getPolicyNo());
		Assert.assertEquals(policyResponse.getPolicyHolderName(), policyDetails.getPolicyHolderName());
	}
	
	/**
	 * Negative test case for policy details for given policy Number
	 * @author Sharath G S
	 * @throws PolicyNotExistsException 
	 */
	@Test(expected = PolicyNotExistsException.class)
	public void policyDetailTest() throws PolicyNotExistsException
	{
		Mockito.when(policyRepository.findBypolicyNo(Mockito.anyInt())).thenReturn(Optional.empty());
		PolicyResponseDetailsDto policyResponse = policyService.getPolicyDetails(Mockito.anyInt());
		Assert.assertEquals(policyResponse.getPolicyHolderName(), policyDetails.getPolicyHolderName());
	}
}