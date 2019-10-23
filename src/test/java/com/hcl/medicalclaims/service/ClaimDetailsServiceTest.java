
  package com.hcl.medicalclaims.service;
  
  import static org.junit.Assert.assertNotNull;
  
  import java.time.LocalDate; import java.util.ArrayList; import
  java.util.List; import java.util.Optional;
  
  import org.junit.Before; import org.junit.Test; import
  org.junit.runner.RunWith; import org.mockito.InjectMocks; import
  org.mockito.Mock; import org.mockito.Mockito; import
  org.mockito.junit.MockitoJUnitRunner;
  
  import com.hcl.medicalclaims.dto.ClaimDetailsResponseDto; import
  com.hcl.medicalclaims.dto.ClaimDto;
import com.hcl.medicalclaims.entity.ApproverDetails;
import
  com.hcl.medicalclaims.entity.ClaimDetails; import
  com.hcl.medicalclaims.entity.PolicyDetails;
import com.hcl.medicalclaims.exception.ApproverNotExistsException;
import com.hcl.medicalclaims.exception.ClaimDetailsNotfoundException;
import com.hcl.medicalclaims.repository.ApproverRepository;
import
  com.hcl.medicalclaims.repository.ClaimDetailsRepository; import
  com.hcl.medicalclaims.repository.PolicyRepository;
 /**
	 * @author priyanka
	 *
	 */
		  @RunWith(MockitoJUnitRunner.class) public class ClaimDetailsServiceTest {
		  
		  @InjectMocks 
		  ClaimDetailsServiceImpl claimDetailsServiceImpl;
		  
		  @Mock 
		  ClaimDetailsRepository claimDetailsRepository;
		  
		  @Mock 
		  ApproverRepository approverRepository;
		  
		  @Mock 
		  PolicyRepository policyRepository; 
		  List<ClaimDto> claimDetailss;
		  ClaimDto claimDto; 
		  ClaimDetailsResponseDto claimDetailsResponseDto;
		  List<ClaimDetails> claimDet; 
		  ClaimDetails claimDetails; 
		  PolicyDetails polictDetails; 
		  Integer approverId;
		  Optional<List<ClaimDetails>> claimDetailsOptional;
		  List<PolicyDetails> polictDetailss;
		  Optional<ApproverDetails> findById;
		  ApproverDetails approverDetails;
		  ApproverDetails approverDetailsManager;
		  
		  
		  @Before 
		  public void setup() {
			  claimDetailss = new ArrayList<>();
		  polictDetails=new PolicyDetails(); polictDetails.setPolicyId(1);
		  polictDetails.setPolicyNo(1234); polictDetails.setPolicyType("Dental");
		  polictDetails.setPolicyHolderName("Priyanka");
		  polictDetails.setClaimedAmount(2000.0);
		  polictDetails.setEligibleAmount(3000.0); 
		  polictDetailss=new ArrayList<>();
		  polictDetailss.add(polictDetails); 
		  claimDetails=new ClaimDetails(); LocalDate
		  toDate = LocalDate.of(2019, 02, 05); 
		  claimDetails.setAdmittedDate(toDate);
		  LocalDate toDate1=LocalDate.of(2019, 04, 06); 
		  LocalDate dischargeDate=LocalDate.of(2019, 07, 05); 
		  claimDetails.setClaimDate(toDate1);
		  claimDetails.setApproverRemarks("aad"); claimDetails.setClaimAmount(3240.0);
		  claimDetails.setClaimId(1); 
		  claimDetails.setClaimNo(2345);
		  claimDetails.setClaimStatus("approved");
		  claimDetails.setClaimUploadFilePath("path");
		  claimDetails.setDiagnosis("typhoid");
		  claimDetails.setDischargeDate(dischargeDate);
		  claimDetails.setDischargeSummary("successful treatment");
		  claimDetails.setHospitalName("asd");
		  claimDetails.setPolicyDetails(polictDetails); 
		  claimDetailss=new ArrayList<>(); 
		  claimDto=new ClaimDto(); claimDto.setAdmittedDate(toDate);
		  claimDto.setClaimDate(toDate1); 
		  claimDto.setDischargeDate(dischargeDate);
		  
		  claimDto.setApproverRemarks("sdf"); 
		  claimDto.setClaimAmount(2000.0);
		  claimDto.setClaimId(1); 
		  claimDto.setClaimNo(1234);
		  claimDto.setClaimStatus("approved");
		  claimDto.setClaimUploadFilePath("path");
		  claimDto.setDiagnosis("typhoid"); 
		  claimDto.setHospitalName("asd");
		  
		  
		  approverDetails=new ApproverDetails();
		  approverDetails.setApproverId(1);
		  approverDetails.setApproverName("Priyanka");
		  approverDetails.setApproverRole("MANAGER");
		  approverDetails.setMailId("Priyanka@gmail.com");
		  approverDetails.setPassword("ad@123");
		  findById=Optional.of(approverDetails);
		  
		  
		  approverDetailsManager=new ApproverDetails();
		  approverDetailsManager.setApproverId(1);
		  approverDetailsManager.setApproverName("Priyanka");
		  approverDetailsManager.setApproverRole("MANAGER");
		  approverDetailsManager.setMailId("Priyanka@gmail.com");
		  approverDetailsManager.setPassword("ad@123");
		  findById=Optional.of(approverDetailsManager);
		  
		  claimDetailss.add(claimDto); claimDet=new ArrayList<>();
		  claimDet.add(claimDetails);
		  claimDetailsOptional=Optional.ofNullable(claimDet);
		  claimDetailsResponseDto=new ClaimDetailsResponseDto();
		  claimDetailsResponseDto.setClaimDetails(claimDetailss); 
		  }
		  

		  @Test 
		  public void getClaimDetailsTest() throws ApproverNotExistsException 
		  {

		  Mockito.when(approverRepository.findById(Mockito.any())).thenReturn(findById);
		  Mockito.when(claimDetailsRepository.findByClaimStatus(Mockito.anyString())).thenReturn(claimDetailsOptional); 
		  ClaimDetailsResponseDto claimDetailsResponseDto=claimDetailsServiceImpl.getClaimDetails(Mockito.anyInt());
		  assertNotNull(claimDetailsResponseDto); 
		  } 
		  
		  /**
		   * The negative test case for claim details
		   * @author Sharath G S
		 * @throws ApproverNotExistsException 
		   * 
		   */
		  @Test(expected = ClaimDetailsNotfoundException.class)
		  public void getClaimDetailsTestsForClaimDetails() throws ApproverNotExistsException
		  {
			  Mockito.when(approverRepository.findById(Mockito.any())).thenReturn(findById);
			  Mockito.when(claimDetailsRepository.findByClaimStatus(Mockito.anyString())).thenReturn(claimDetailsOptional.empty()); 
			  ClaimDetailsResponseDto claimDetailsResponseDto=claimDetailsServiceImpl.getClaimDetails(Mockito.anyInt());
			  assertNotNull(claimDetailsResponseDto); 
		  }
		  
		  /**
		   * The negative test case for claim details
		   * @author Sharath G S
		   * @throws ApproverNotExistsException
		   */
		  @Test(expected = ApproverNotExistsException.class) 
		  public void getClaimDetailsTestForApprover() throws ApproverNotExistsException 
		  {

		  Mockito.when(approverRepository.findById(Mockito.any())).thenReturn(findById.empty());
		  Mockito.when(claimDetailsRepository.findByClaimStatus(Mockito.anyString())).thenReturn(claimDetailsOptional); 
		  ClaimDetailsResponseDto claimDetailsResponseDto=claimDetailsServiceImpl.getClaimDetails(Mockito.anyInt());
		  assertNotNull(claimDetailsResponseDto); 
		  }
		  
		  /**
		   * The positive test case for senior manager
		   * @author Sharath G S
		 * @throws ApproverNotExistsException 
		   */
		  @Test
		  public void getClaimsDetailsManager() throws ApproverNotExistsException
		  {
			  Mockito.when(approverRepository.findById(Mockito.any())).thenReturn(Optional.of(approverDetailsManager));
			  Mockito.when(claimDetailsRepository.findByClaimStatus(Mockito.anyString())).thenReturn(claimDetailsOptional); 
			  ClaimDetailsResponseDto claimDetailsResponseDto=claimDetailsServiceImpl.getClaimDetails(Mockito.anyInt());
			  assertNotNull(claimDetailsResponseDto); 
		  }
}
		 