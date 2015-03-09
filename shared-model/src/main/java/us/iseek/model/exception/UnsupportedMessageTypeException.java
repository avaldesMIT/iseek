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
 * Indicates that the message type is not supported by the operation.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class UnsupportedMessageTypeException extends Exception {

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
	public UnsupportedMessageTypeException(String message) {
		super(message);
	}
}
