/**
 * Copyright (C) 2015 iSeek, Inc. 
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of iSeek, Inc.
 * You shall not disclose such confidential information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with 
 * iSeek, Inc.
 */
package us.iseek.model.request.user;

/**
 * A request to update the user's Google Cloud Messaging registration ID.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class UpdateUserGcmRegistrationIdRequest {

	private Long userId;
	private String gcmRegistrationId;

	/**
	 * Creates a new instance of this.
	 */
	public UpdateUserGcmRegistrationIdRequest() {
		// Default constructor
	}

	/**
	 * Creates a new instance of this.
	 * 
	 * @param userId
	 *            - The ID of the user for whom the display name is being
	 *            updated.
	 * @param gcmRegistrationId
	 *            - The Google Cloud Messaging registration ID to set.
	 */
	public UpdateUserGcmRegistrationIdRequest(Long userId, String gcmRegistrationId) {
		this.userId = userId;
		this.gcmRegistrationId = gcmRegistrationId;
	}

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return this.userId;
	}

	/**
	 * @param userId
	 *            - The userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the gcmRegistrationId
	 */
	public String getGcmRegistrationId() {
		return this.gcmRegistrationId;
	}

	/**
	 * @param gcmRegistrationId
	 *            - The gcmRegistrationId to set
	 */
	public void setGcmRegistrationId(String gcmRegistrationId) {
		this.gcmRegistrationId = gcmRegistrationId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "{UpdateUserScreenNameRequest: userId=" + this.userId + ", gcmRegistrationId=" + this.gcmRegistrationId
				+ "}";
	}
}
