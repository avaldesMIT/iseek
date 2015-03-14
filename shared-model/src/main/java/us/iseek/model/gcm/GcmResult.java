/**
 * Copyright (C) 2015 iSeek, Inc. 
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of iSeek, Inc.
 * You shall not disclose such confidential information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with 
 * iSeek, Inc.
 */
package us.iseek.model.gcm;

/**
 * Represents the status of the GCM messages processed.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class GcmResult {
	private String message_id;
	private String registration_id;
	private String error;

	/**
	 * @return the message_id
	 */
	public String getMessage_id() {
		return this.message_id;
	}

	/**
	 * @param message_id
	 *            - The message_id to set
	 */
	public void setMessage_id(String message_id) {
		this.message_id = message_id;
	}

	/**
	 * @return the registration_id
	 */
	public String getRegistration_id() {
		return this.registration_id;
	}

	/**
	 * @param registration_id
	 *            - The registration_id to set
	 */
	public void setRegistration_id(String registration_id) {
		this.registration_id = registration_id;
	}

	/**
	 * @return the error
	 */
	public String getError() {
		return this.error;
	}

	/**
	 * @param error
	 *            - The error to set
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "{GcmResult: message_id=" + this.message_id + ", registration_id=" + this.registration_id + ", error="
				+ this.error + "}";
	}
}
