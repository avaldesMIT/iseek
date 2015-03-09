/**
 * Copyright (C) 2015 iSeek, Inc. 
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of iSeek, Inc.
 * You shall not disclose such confidential information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with 
 * iSeek, Inc.
 */
package us.iseek.services;

import us.iseek.model.gps.Location;
import us.iseek.model.user.Preferences;
import us.iseek.model.user.User;

/**
 * Provides services to manage users preferences and lifecycle.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public interface IUserService {

	/**
	 * Retrieves the user corresponding to the user ID provided.
	 * 
	 * @param userId
	 *            - The user's ID
	 * @return The user for the user ID provided.
	 */
	public User get(Long userId);

	/**
	 * Retrieves the user corresponding to the user's Facebook profile ID
	 * provided.
	 * 
	 * @param facebookProfileId
	 *            - The user's Facebook profile ID
	 * @return The user for the Facebook profile ID provided.
	 */
	public User getByFacebookProfileId(Long facebookProfileId);

	/**
	 * Creates a new user based on the facebook profile ID provided. Some of the
	 * fields for the user will be populated with default values. <br>
	 * <br>
	 * <b>Note:</b> if the user with the facebook profile ID provided already
	 * exists in the system, this method will retrieve the existing user instead
	 * of creating a new one. However, if the location provided is not null, the
	 * user's last known location will be updated with the new location
	 * provided.
	 * 
	 * @param facebookProfileId
	 *            - The facebook profile ID for the user being created.
	 * @param location
	 *            - The optional current location of the user being created.
	 * @return The user that was just created or the existing user if a user
	 *         with the facebook profile ID provided already existed in the
	 *         system.
	 */
	public User createUser(Long facebookProfileId, Location location);

	/**
	 * Updates the user's preferences.
	 * 
	 * @param userId
	 *            - The ID of the user for whom the preferences are being set.
	 * @param preferences
	 *            - The user's new preferences.
	 * @return The user with the updated preferences
	 */
	public User updatePreferences(Long userId, Preferences preferences);

	/**
	 * Updates the user's location.
	 * 
	 * @param userId
	 *            - The ID of the user for whom the location is being updated.
	 * @param location
	 *            - The user's new location.
	 * @return The user with the updated location
	 */
	public User updateLocation(Long userId, Location location);

	/**
	 * Updates the user's screen name.
	 * 
	 * @param userId
	 *            - The ID of the user for whom the screen name is being
	 *            updated.
	 * @param screenName
	 *            - The user's screen name.
	 * @return The user with the updated screen name.
	 */
	public User updateScreenName(Long userId, String screenName);
}
