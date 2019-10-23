package com.hcl.medicalclaims.service;

import com.hcl.medicalclaims.dto.ClaimDetailsResponseDto;
/**
 * @author priyanka
 *
 */

public interface ClaimsDetailsService {

	public ClaimDetailsResponseDto getClaimDetails(Integer approverId);
}
