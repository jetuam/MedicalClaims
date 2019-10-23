/**
 * 
 */
package com.hcl.medicalclaims.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author srinivas
 *
 */
@Entity
public class ClaimDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	private String claimUploadFilePath;
	private String natureOfAilment;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "policyId", referencedColumnName = "policyId")
	private PolicyDetails policyDetails;
	private Integer approverSummaryId;
	private String approverRemarks;

	
	
	public Integer getClaimNo() {
		return claimNo;
	}

	public void setClaimNo(Integer claimNo) {
		this.claimNo = claimNo;
	}

	public Integer getApproverSummaryId() {
		return approverSummaryId;
	}

	public void setApproverSummaryId(Integer approverSummaryId) {
		this.approverSummaryId = approverSummaryId;
	}


	
	/**
	 * @return the approverRemarks
	 */
	public String getApproverRemarks() {
		return approverRemarks;
	}
	/**
	 * @param approverRemarks the approverRemarks to set
	 */
	public void setApproverRemarks(String approverRemarks) {
		this.approverRemarks = approverRemarks;
	}
	/**
	 * @return the natureOfAilment
	 */
	public String getNatureOfAilment() {
		return natureOfAilment;
	}
	/**
	 * @param natureOfAilment the natureOfAilment to set
	 */
	public void setNatureOfAilment(String natureOfAilment) {
		this.natureOfAilment = natureOfAilment;
	}

	/**
	 * @return the claimId
	 */
	
	public Integer getClaimId() {
		return claimId;
	}	

	/**
	 * @param claimId the claimId to set
	 */
	public void setClaimId(Integer claimId) {
		this.claimId = claimId;
	}

	/**
	 * @return the claimAmount
	 */
	public Double getClaimAmount() {
		return claimAmount;
	}

	/**
	 * @param claimAmount the claimAmount to set
	 */
	public void setClaimAmount(Double claimAmount) {
		this.claimAmount = claimAmount;
	}

	/**
	 * @return the admittedDate
	 */
	public LocalDate getAdmittedDate() {
		return admittedDate;
	}

	/**
	 * @param admittedDate the admittedDate to set
	 */
	public void setAdmittedDate(LocalDate admittedDate) {
		this.admittedDate = admittedDate;
	}

	/**
	 * @return the dischargeDate
	 */
	public LocalDate getDischargeDate() {
		return dischargeDate;
	}

	/**
	 * @param dischargeDate the dischargeDate to set
	 */
	public void setDischargeDate(LocalDate dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	/**
	 * @return the hospitalName
	 */
	public String getHospitalName() {
		return hospitalName;
	}

	/**
	 * @param hospitalName the hospitalName to set
	 */
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	/**
	 * @return the diagnosis
	 */
	public String getDiagnosis() {
		return diagnosis;
	}

	/**
	 * @param diagnosis the diagnosis to set
	 */
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	/**
	 * @return the dischargeSummary
	 */
	public String getDischargeSummary() {
		return dischargeSummary;
	}

	/**
	 * @param dischargeSummary the dischargeSummary to set
	 */
	public void setDischargeSummary(String dischargeSummary) {
		this.dischargeSummary = dischargeSummary;
	}

	/**
	 * @return the claimDate
	 */
	public LocalDate getClaimDate() {
		return claimDate;
	}

	/**
	 * @param claimDate the claimDate to set
	 */
	public void setClaimDate(LocalDate claimDate) {
		this.claimDate = claimDate;
	}

	/**
	 * @return the claimStatus
	 */
	public String getClaimStatus() {
		return claimStatus;
	}

	/**
	 * @param claimStatus the claimStatus to set
	 */
	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}

	/**
	 * @return the claimUploadFilePath
	 */
	public String getClaimUploadFilePath() {
		return claimUploadFilePath;
	}

	/**
	 * @param claimUploadFilePath the claimUploadFilePath to set
	 */
	public void setClaimUploadFilePath(String claimUploadFilePath) {
		this.claimUploadFilePath = claimUploadFilePath;
	}

	/**
	 * @return the policyDetails
	 */
	public PolicyDetails getPolicyDetails() {
		return policyDetails;
	}

	/**
	 * @param policyDetails the policyDetails to set
	 */
	public void setPolicyDetails(PolicyDetails policyDetails) {
		this.policyDetails = policyDetails;
	}

}
