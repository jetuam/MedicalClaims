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
import org.springframework.http.HttpStatus;

import com.hcl.medicalclaims.dto.ApproveRequestDto;
import com.hcl.medicalclaims.dto.ApproveResponseDto;
import com.hcl.medicalclaims.entity.ApproverDetails;
import com.hcl.medicalclaims.entity.ApproverSummary;
import com.hcl.medicalclaims.entity.ClaimDetails;
import com.hcl.medicalclaims.entity.PolicyDetails;
import com.hcl.medicalclaims.exception.ApproverNotExistsException;
import com.hcl.medicalclaims.exception.ClaimNumberNotExistsException;
import com.hcl.medicalclaims.repository.ApproveSummaryRepository;
import com.hcl.medicalclaims.repository.ApproverRepository;
import com.hcl.medicalclaims.repository.ClaimRepository;
import com.hcl.medicalclaims.repository.PolicyRepository;
import com.hcl.medicalclaims.util.ApproveUtil;
import com.hcl.medicalclaims.util.MedicalUtils;

/**
 * The TestApproveService is used for test cases for approve request accept or reject
 * @author Sharath G S
 *
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class ApproveServiceTest {

	@Mock
	ApproverRepository approverRepository;
	
	@Mock
	ApproveSummaryRepository approveSummaryRepo;
	
	@Mock
	ClaimRepository claimRepository;
	
	@Mock
	PolicyRepository policyRepository;
	
	@Mock
	ApproveUtil approveUtil;
	
	@InjectMocks
	ApproverServiceImpl approveService;
	
	ApproveRequestDto approveRequest = null;
	ApproverDetails approverDetails = null;
	ClaimDetails claimDetails = null;
	PolicyDetails policyDetails = null;
	ApproverSummary approveSummary = null;
	ApproverDetails approveDetailsManager = null;
	ApproveResponseDto approveResponse = null;
	
	@Before
	public void setUp()
	{		
		policyDetails = new PolicyDetails();
		policyDetails.setClaimedAmount((double) 30000);
		policyDetails.setEligibleAmount((double) 200000);
		policyDetails.setPolicyHolderName("Sharath");
		policyDetails.setPolicyId(1);
		policyDetails.setPolicyNo(1234);
		policyDetails.setPolicyType("DENTAL");
		
		approveRequest = new ApproveRequestDto();
		approveRequest.setApproverId(1);
		approveRequest.setClaimId(1);
		approveRequest.setClaimStatus("APPROVED");
		approveRequest.setPolicyId(1);
		approveRequest.setRemarks("Approved for this claim");
		
		approverDetails = new ApproverDetails();
		approverDetails.setApproverId(1);
		approverDetails.setMailId("sharathgs777@gmail.com");
		approverDetails.setApproverName("Sharath");
		approverDetails.setPassword("test@123");
		approverDetails.setApproverRole("MANAGER");
		
		approveDetailsManager = new ApproverDetails();
		approveDetailsManager.setApproverId(1);
		approveDetailsManager.setMailId("sharathgs777@gmail.com");
		approveDetailsManager.setApproverName("Sharath");
		approveDetailsManager.setPassword("test@123");
		approveDetailsManager.setApproverRole("MANAGER");
		
		approveSummary = new ApproverSummary();
		approveSummary.setApproverId(1);
		approveSummary.setApproverRole("MANAGER");
		approveSummary.setApproverSummaryId(1);
				
		claimDetails = new ClaimDetails();
		claimDetails.setApproverSummaryId(1);
		claimDetails.setClaimAmount((double) 50000);
		claimDetails.setClaimId(1);
		claimDetails.setClaimNo(1234);
		claimDetails.setClaimStatus("PENDING");
		claimDetails.setDiagnosis("FULL BODY");
		claimDetails.setDischargeSummary("heart operation done and need to take rest");
		claimDetails.setHospitalName("APPOLLO");
		claimDetails.setNatureOfAilment("HEART");
		claimDetails.setPolicyDetails(policyDetails);
		
		approveResponse = new ApproveResponseDto();
		approveResponse.setMessage(MedicalUtils.CLAIM_APPROVED);
		approveResponse.setPolicyNo(1);
		approveResponse.setStatusCode(MedicalUtils.POLICY_HTTP_SUCCESS);
	}
	
	
	/**
	 * The positive test case for approve request for manager
	 * @throws ClaimNumberNotExistsException 
	 * @throws ApproverNotExistsException 
	 */
	@Test
	public void testApprove() throws ApproverNotExistsException, ClaimNumberNotExistsException {
		Mockito.when(approverRepository.findByapproverId(approverDetails.getApproverId())).thenReturn(Optional.of(approverDetails));
		Mockito.when(claimRepository.findByclaimId(claimDetails.getClaimId())).thenReturn(Optional.of(claimDetails));
		Mockito.when(approveUtil.approveManagerUtil(Optional.of(approverDetails), Optional.of(claimDetails), approveRequest)).thenReturn(approveResponse);
		ApproveResponseDto approved = approveService.claimApproved(approveRequest);
		Assert.assertEquals( "Claim approved successfully",approved.getMessage());
	}
	
	
	/**
	 * The positive test case for approve request for senior manager
	 * @throws ClaimNumberNotExistsException 
	 * @throws ApproverNotExistsException 
	 */
	@Test
	public void testApproveManager() throws ApproverNotExistsException, ClaimNumberNotExistsException {
		Mockito.when(approverRepository.findByapproverId(approveDetailsManager.getApproverId())).thenReturn(Optional.of(approverDetails));
		Mockito.when(claimRepository.findByclaimId(claimDetails.getClaimId())).thenReturn(Optional.of(claimDetails));
		Mockito.when(approveUtil.approveManagerUtil(Optional.of(approverDetails), Optional.of(claimDetails), approveRequest)).thenReturn(approveResponse);
		ApproveResponseDto approved = approveService.claimApproved(approveRequest);
		Assert.assertEquals( "Claim approved successfully",approved.getMessage());
	}
	
	
	/**
	 * The positive test case for approve request
	 * @author Sharath G S
	 * @throws ClaimNumberNotExistsException 
	 * @throws ApproverNotExistsException 
	 */
	/*@Test
	public void approveRequestTest() throws ApproverNotExistsException, ClaimNumberNotExistsException
	{
		Mockito.when(approverRepository.findByapproverId(approverDetails.getApproverId())).thenReturn(Optional.of(approverDetails));
		Mockito.when(claimRepository.findByclaimId(claimDetails.getClaimId())).thenReturn(Optional.of(claimDetails));
		Mockito.when(approveSummaryRepo.save(approveSummary)).thenReturn(approveSummary);
		Mockito.when(claimRepository.save(claimDetails)).thenReturn(claimDetails);
		Mockito.when(policyRepository.findById(policyDetails.getPolicyId())).thenReturn(Optional.of(policyDetails));
		Mockito.when(policyRepository.save(policyDetails)).thenReturn(policyDetails);
		ApproveResponseDto approveResponse = approveService.claimApproved(approveRequest);
		Assert.assertEquals(approveResponse.getMessage(), MedicalUtils.APPROVED);
	}*/
	
	
	/**
	 * The negative test case for validating claim number
	 * @author SharatH G S
	 * @throws ApproverNotExistsException
	 * @throws ClaimNumberNotExistsException
	 */
	@Test(expected = ClaimNumberNotExistsException.class)
	public void approveRequestTests() throws ApproverNotExistsException, ClaimNumberNotExistsException
	{
		Mockito.when(approverRepository.findByapproverId(approverDetails.getApproverId())).thenReturn(Optional.of(approverDetails));
		Mockito.when(claimRepository.findByclaimId(claimDetails.getClaimId())).thenReturn(Optional.empty());
		Mockito.when(approveUtil.approveManagerUtil(Optional.of(approverDetails), Optional.of(claimDetails), approveRequest)).thenReturn(approveResponse);
		ApproveResponseDto approved = approveService.claimApproved(approveRequest);
		Assert.assertEquals( "Claim approved successfully",approved.getMessage());
	}
	
	/**
	 * The negative case approver not exists 
	 * @author Sharath G S
	 * @throws ApproverNotExistsException
	 * @throws ClaimNumberNotExistsException
	 */
	@Test(expected = ApproverNotExistsException.class)
	public void approveRequestsTest() throws ApproverNotExistsException, ClaimNumberNotExistsException
	{
		Mockito.when(approverRepository.findByapproverId(approverDetails.getApproverId())).thenReturn(Optional.empty());
		Mockito.when(claimRepository.findByclaimId(claimDetails.getClaimId())).thenReturn(Optional.of(claimDetails));
		Mockito.when(approveUtil.approveManagerUtil(Optional.of(approverDetails), Optional.of(claimDetails), approveRequest)).thenReturn(approveResponse);
		ApproveResponseDto approved = approveService.claimApproved(approveRequest);
		Assert.assertEquals( "Claim approved successfully",approved.getMessage());
	}
	
}
