package com.hcl.medicalclaims.service;

import com.hcl.medicalclaims.dto.ClaimDetailsResponseDto;
import com.hcl.medicalclaims.exception.ApproverNotExistsException;
/**
 * @author priyanka
 *
 */

public interface ClaimsDetailsService {

	public ClaimDetailsResponseDto getClaimDetails(Integer approverId) throws ApproverNotExistsException;
}
