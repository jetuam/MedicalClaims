package com.hcl.medicalclaims.util;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class MedicalUtils {

	public static final String POLICY_DETAILS_NOT_EXISTS = "Policy number is not valid";
	public static final String POLICY_DETAILS_EXISTS = "Policy number exists";
	public static final Integer POLICY_HTTP_SUCCESS = HttpStatus.OK.value();
	public static final String APPROVER_NOT_EXISTS = "Approver does not exists";
	public static final String CLAIM_NO_NOT_EXISTS = "Claim Number does not exists";
	public static final String MANAGER = "MANAGER";
	public static final String BRANCH_MANAGER = "BRANCH MANAGER";
	public static final String CLAIM_APPROVED = "Claim approved successfully";
	public static final String APPROVED = "Approved";
	public static final String REJECTED = "ReJected";
	public static final String FORWARDED = "Forwarded";
	
	
}
