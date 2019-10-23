package com.hcl.medicalclaims.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author srinivas
 *
 */
public class AddClaimRequestDTO {
	@NotBlank(message = "hospitalName is mandatory")
	@NotNull(message = "hospitalName cannot be null")
	private Integer policyNo;
	private Double claimAmount;
	private LocalDate admittedDate;
	private LocalDate dischargeDate;
	@NotBlank(message = "hospitalName is mandatory")
	@NotNull(message = "hospitalName cannot be null")
	@Size(min = 2)
	private String hospitalName;
	private String diagnosis;
	private String dischargeSummary;
	private String claimUploadFilePath;
	@NotBlank(message = "natureOfAilment is mandatory")
	@NotNull(message = "natureOfAilment cannot be null")
	private String natureOfAilment;

	/**
	 * @return the policyNo
	 */
	public Integer getPolicyNo() {
		return policyNo;
	}

	/**
	 * @param policyNo the policyNo to set
	 */
	public void setPolicyNo(Integer policyNo) {
		this.policyNo = policyNo;
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

}
