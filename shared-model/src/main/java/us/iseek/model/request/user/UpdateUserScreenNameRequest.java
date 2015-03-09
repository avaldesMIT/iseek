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
 * A request to update the user's display name.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class UpdateUserScreenNameRequest {

	private Long userId;
	private String screenName;

	/**
	 * Creates a new instance of this.
	 */
	public UpdateUserScreenNameRequest() {
		// Default constructor
	}

	/**
	 * Creates a new instance of this.
	 * 
	 * @param userId
	 *            - The ID of the user for whom the display name is being
	 *            updated.
	 * @param screenName
	 *            - The user's new screen name.
	 */
	public UpdateUserScreenNameRequest(Long userId, String screenName) {
		this.userId = userId;
		this.screenName = screenName;
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
	 * @return the screenName
	 */
	public String getScreenName() {
		return this.screenName;
	}

	/**
	 * @param screenName
	 *            - The screenName to set
	 */
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "{UpdateUserScreenNameRequest: userId=" + this.userId + ", screenName=" + this.screenName + "}";
	}
}
