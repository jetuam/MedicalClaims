package com.hcl.medicalclaims.dto;

import java.time.LocalDate;
/**
 * @author priyanka
 *
 */
public class ClaimDto {

	private Integer claimId;
	private Integer claimNo;
	private Double claimAmount;
	private LocalDate admittedDate;
	private LocalDate dischargeDate;
	private String hospitalName;
	private String diagnosis;
	private String dischargeSummary;
	private LocalDate claimDate;
	private String claimStatus;
	private String claimUploadFilePath;;
	private String approverRemarks;
	private Integer policyId;
	
	
	/**
	 * @return the policyId
	 */
	public Integer getPolicyId() {
		return policyId;
	}
	/**
	 * @param policyId the policyId to set
	 */
	public void setPolicyId(Integer policyId) {
		this.policyId = policyId;
	}
	public Integer getClaimId() {
		return claimId;
	}
	public void setClaimId(Integer claimId) {
		this.claimId = claimId;
	}
	public Integer getClaimNo() {
		return claimNo;
	}
	public void setClaimNo(Integer claimNo) {
		this.claimNo = claimNo;
	}
	public Double getClaimAmount() {
		return claimAmount;
	}
	public void setClaimAmount(Double claimAmount) {
		this.claimAmount = claimAmount;
	}
	public LocalDate getAdmittedDate() {
		return admittedDate;
	}
	public void setAdmittedDate(LocalDate admittedDate) {
		this.admittedDate = admittedDate;
	}
	public LocalDate getDischargeDate() {
		return dischargeDate;
	}
	public void setDischargeDate(LocalDate dischargeDate) {
		this.dischargeDate = dischargeDate;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getDischargeSummary() {
		return dischargeSummary;
	}
	public void setDischargeSummary(String dischargeSummary) {
		this.dischargeSummary = dischargeSummary;
	}
	public LocalDate getClaimDate() {
		return claimDate;
	}
	public void setClaimDate(LocalDate claimDate) {
		this.claimDate = claimDate;
	}
	public String getClaimStatus() {
		return claimStatus;
	}
	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}
	public String getClaimUploadFilePath() {
		return claimUploadFilePath;
	}
	public void setClaimUploadFilePath(String claimUploadFilePath) {
		this.claimUploadFilePath = claimUploadFilePath;
	}
	public String getApproverRemarks() {
		return approverRemarks;
	}
	public void setApproverRemarks(String approverRemarks) {
		this.approverRemarks = approverRemarks;
	}
	
}
