package com.hcl.medicalclaims.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.hcl.medicalclaims.dto.ApproveRequestDto;
import com.hcl.medicalclaims.dto.ApproveResponseDto;
import com.hcl.medicalclaims.exception.ApproverNotExistsException;
import com.hcl.medicalclaims.exception.ClaimNumberNotExistsException;
import com.hcl.medicalclaims.service.ApproverServiceImpl;
import com.hcl.medicalclaims.util.MedicalUtils;

/**
 * The controller test cases
 * @author Sharath G S
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ApproveControllerTest {

	
	@Mock
	ApproverServiceImpl approveService;
	
	@InjectMocks
	ApproveController approveController;
	
	ApproveRequestDto approveRequest = null;
	ApproveResponseDto approveResponse = null;
	
	@Before
	public void setUp()
	{
		approveRequest = new ApproveRequestDto();
		approveRequest.setApproverId(1);
		approveRequest.setClaimId(1);
		approveRequest.setClaimStatus("APPROVED");
		approveRequest.setPolicyId(1);
		approveRequest.setRemarks("Approved for this claim");
		
		approveResponse = new ApproveResponseDto();
		approveResponse.setMessage(MedicalUtils.CLAIM_APPROVED);
		approveResponse.setPolicyNo(1);
		approveResponse.setStatusCode(MedicalUtils.POLICY_HTTP_SUCCESS);
	}
	
	/**
	 * Positive test case for approve controller
	 * @author Sharath G S
	 * @throws ClaimNumberNotExistsException 
	 * @throws ApproverNotExistsException 
	 */
	@Test
	public void approveControllerTest() throws ApproverNotExistsException, ClaimNumberNotExistsException
	{
		Mockito.when(approveService.claimApproved(approveRequest)).thenReturn(approveResponse);
		ResponseEntity<ApproveResponseDto> approveResponseDto = approveController.approveClaims(approveRequest);
		ApproveResponseDto approved = approveResponseDto.getBody();
		Assert.assertEquals(approved.getMessage(), approveResponse.getMessage());
	}
}
