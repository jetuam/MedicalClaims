package com.hcl.medicalclaims.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author User1
 *
 */
@Entity
public class ApproverSummary {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer approverSummaryId;
	private String approverRole;
	private Integer approverId;

	public Integer getApproverId() {
		return approverId;
	}

	public void setApproverId(Integer approverId) {
		this.approverId = approverId;
	}

	/**
	 * @return the approverSummaryId
	 */
	public Integer getApproverSummaryId() {
		return approverSummaryId;
	}

	/**
	 * @param approverSummaryId the approverSummaryId to set
	 */
	public void setApproverSummaryId(Integer approverSummaryId) {
		this.approverSummaryId = approverSummaryId;
	}

	/**
	 * @return the approverRole
	 */
	public String getApproverRole() {
		return approverRole;
	}

	/**
	 * @param approverRole the approverRole to set
	 */
	public void setApproverRole(String approverRole) {
		this.approverRole = approverRole;
	}
}