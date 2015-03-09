/**
 * Copyright (C) 2015 iSeek, Inc. 
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of iSeek, Inc.
 * You shall not disclose such confidential information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with 
 * iSeek, Inc.
 */
package us.iseek.services.persistence;

import us.iseek.model.user.Preferences;

/**
 * Provides methods to persist user <tt>Preferences</tt>.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public interface PreferencesMapper {

	/**
	 * Inserts the preferences for the user ID provided.
	 * 
	 * @param userId
	 *            - The ID of the user to whom the preferences belong.
	 * @param preferences
	 *            - The user's preferences to insert.
	 */
	public void insert(Long userId, Preferences preferences);

	/**
	 * Updates the preferences for the user ID provided. *
	 * 
	 * @param userId
	 *            - The ID of the user to whom the preferences belong.
	 * @param preferences
	 *            - The user's preferences to update.
	 */
	public void update(Long userId, Preferences preferences);
}
