
  package com.hcl.medicalclaims.controller;
  
  import static org.junit.Assert.assertNotNull;
  
  import java.time.LocalDate; import java.util.ArrayList; import
  java.util.List;
  
  import org.junit.Test; import org.junit.runner.RunWith; import
  org.mockito.InjectMocks; import org.mockito.Mock; import org.mockito.Mockito;
  import org.mockito.junit.MockitoJUnitRunner;
  
  import com.hcl.medicalclaims.dto.ClaimDetailsResponseDto; import
  com.hcl.medicalclaims.dto.ClaimDto; import
  com.hcl.medicalclaims.entity.ClaimDetails; import
  com.hcl.medicalclaims.entity.PolicyDetails;
import com.hcl.medicalclaims.exception.ApproverNotExistsException;
import
  com.hcl.medicalclaims.service.ClaimDetailsServiceImpl;
/*
@author priyanka*/
  
  @RunWith(MockitoJUnitRunner.class) public class ClaimDetailsControllerTest {
  
  @Mock ClaimDetailsServiceImpl claimDetailsServiceImpl;
  
  @InjectMocks ClaimDetailsController claimDetailsController;
  ClaimDetailsResponseDto claimDetailsResponseDto; ClaimDetails claimDetails;
  List<ClaimDto> claimDetailss; PolicyDetails polictDetails;
  List<PolicyDetails> polictDetailss; ClaimDto claimDto;
  
  @Test public void getClaimDetailsTest() throws ApproverNotExistsException { claimDetails=new ClaimDetails();
  claimDetailss = new ArrayList<>(); polictDetails=new PolicyDetails();
  polictDetails.setPolicyId(1); polictDetails.setPolicyNo(1234);
  polictDetails.setPolicyType("Dental");
  polictDetails.setPolicyHolderName("Priyanka");
  polictDetails.setClaimedAmount(2000.0);
  polictDetails.setEligibleAmount(3000.0); polictDetailss=new ArrayList<>();
  polictDetailss.add(polictDetails); claimDetails=new ClaimDetails(); LocalDate
  toDate = LocalDate.of(2019, 02, 05); claimDetails.setAdmittedDate(toDate);
  LocalDate toDate1=LocalDate.of(2019, 04, 06); LocalDate
  dischargeDate=LocalDate.of(2019, 07, 05); claimDetails.setClaimDate(toDate1);
  claimDetails.setApproverRemarks("aad"); claimDetails.setClaimAmount(3240.0);
  claimDetails.setClaimId(1); claimDetails.setClaimNo(2345);
  claimDetails.setClaimStatus("approved");
  claimDetails.setClaimUploadFilePath("path");
  claimDetails.setDiagnosis("typhoid");
  claimDetails.setDischargeDate(dischargeDate);
  claimDetails.setDischargeSummary("successful treatment");
  claimDetails.setHospitalName("asd");
  claimDetails.setPolicyDetails(polictDetails); claimDto=new ClaimDto();
  claimDto.setAdmittedDate(toDate); claimDto.setClaimDate(toDate1);
  claimDto.setDischargeDate(dischargeDate); claimDto.setPolicyId(1);
  claimDto.setApproverRemarks("sdf"); claimDto.setClaimAmount(2000.0);
  claimDto.setClaimId(1); claimDto.setClaimNo(1234);
  claimDto.setClaimStatus("approved"); claimDto.setClaimUploadFilePath("path");
  claimDto.setDiagnosis("typhoid"); claimDto.setHospitalName("asd");
  claimDto.setPolicyId(1); claimDetailss.add(claimDto);
  claimDetailsResponseDto=new ClaimDetailsResponseDto();
  claimDetailsResponseDto.setClaimDetails(claimDetailss);
  Mockito.when(claimDetailsServiceImpl.getClaimDetails(Mockito.anyInt())).thenReturn(
  claimDetailsResponseDto);
  claimDetailsResponseDto=claimDetailsController.getClaimDetails(Mockito.anyInt());
  assertNotNull(claimDetailsResponseDto); }
  
  }
 