/**
 * Copyright (C) 2015 iSeek, Inc. 
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of iSeek, Inc.
 * You shall not disclose such confidential information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with 
 * iSeek, Inc.
 */
package us.iseek.model.exception;

/**
 * Represents a conversion exception
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class ConversionException extends Exception {

	/**
	 * The default serial version of this exception
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new instance of this exception.
	 * 
	 * @param message
	 *            - The reason for the exception
	 */
	public ConversionException(String message) {
		super(message);
	}

	/**
	 * Creates a new instance of this exception.
	 * 
	 * @param message
	 *            - The reason for the exception
	 * @param cause
	 *            - The underlying cause of the exception
	 */
	public ConversionException(String message, Throwable cause) {
		super(message, cause);
	}
}
