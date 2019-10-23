package com.hcl.medicalclaims.exception;

import java.io.Serializable;

/**
 * @author srinivas
 *
 */
public class CommonException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1L;

	public CommonException(String message) {
		super(message);

	}
}
