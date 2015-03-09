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

import us.iseek.model.user.Preferences;

/**
 * A request to update the user's preferences.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class UpdateUserPreferencesRequest {

	private Long userId;
	private Preferences preferences;

	/**
	 * Creates a new instance of this.
	 */
	public UpdateUserPreferencesRequest() {
		// Default constructor
	}

	/**
	 * Creates a new instance of this.
	 * 
	 * @param userId
	 *            - The ID of the user for whom the preferences are being set.
	 * @param preferences
	 *            - The user's new preferences.
	 */
	public UpdateUserPreferencesRequest(Long userId, Preferences preferences) {
		this.userId = userId;
		this.preferences = preferences;
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
	 * @return the preferences
	 */
	public Preferences getPreferences() {
		return this.preferences;
	}

	/**
	 * @param preferences
	 *            - The preferences to set
	 */
	public void setPreferences(Preferences preferences) {
		this.preferences = preferences;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "{UpdateUserPreferencesRequest: userId=" + userId + ", preferences=" + preferences + "}";
	}
}
