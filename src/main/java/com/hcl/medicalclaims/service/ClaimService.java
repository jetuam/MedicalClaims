/**
 * 
 */
package com.hcl.medicalclaims.service;

import com.hcl.medicalclaims.dto.AddClaimRequestDTO;
import com.hcl.medicalclaims.entity.ClaimDetails;
import com.hcl.medicalclaims.exception.HospitalNotFoundException;
import com.hcl.medicalclaims.exception.PolicyNotFoundException;

/**
 * @author srinivas
 *
 */
public interface ClaimService {

	ClaimDetails addClaim(AddClaimRequestDTO addClaimRequest) throws PolicyNotFoundException, HospitalNotFoundException;

}
