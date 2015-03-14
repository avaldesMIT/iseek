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

import java.util.List;

import us.iseek.model.gps.Location;
import us.iseek.model.topics.HashTag;
import us.iseek.model.user.User;

/**
 * Provides methods to persist <tt>User</tt>s.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public interface UserMapper {

	/**
	 * Inserts the user into the persistent store.
	 * 
	 * @param user
	 *            - The user to insert.
	 */
	public void insert(User user);

	/**
	 * Retrieves the user from the persistent store.
	 * 
	 * @param userId
	 *            - The ID of the user to retrieve.
	 * @return the user with the ID provided or null if no user has that ID.
	 */
	public User get(Long userId);

	/**
	 * Retrieves the user from the persistent store.
	 * 
	 * @param facebookProfileId
	 *            - The user's Facebook profile ID.
	 * @return the user with the Facebook profile ID provided or null if no user
	 *         has that Facebook profile ID.
	 */
	public User getByFacebookProfileId(Long facebookProfileId);

	/**
	 * Retrieves the corresponding user ID for the user's Facebook profile ID.
	 * 
	 * @param facebookProfileId
	 *            - The user's Facebook profile ID.
	 * @return the user ID corresponding to the Facebook profile ID provided.
	 */
	public Long getUserId(Long facebookProfileId);

	/**
	 * Gets the list of users interested in the topic provided, filtered by the
	 * location provided.
	 * 
	 * @param topic
	 *            - The topic of interest
	 * @param location
	 *            - The location to use in filtering the results. Must be
	 *            provided.
	 * @return - The list of users in a particular location interested in the
	 *         topic provided.
	 */
	public List<User> getUsers(HashTag topic, Location location);

	/**
	 * Convenience method to update the user's location.<br>
	 * <br>
	 * <b>Note:</b>The user's last activity time stamp is also updated as a
	 * result of this method.
	 * 
	 * @param userId
	 *            - The ID of the user for whom the location is being updated.
	 * @param location
	 *            - The user's new location.
	 */
	public void updateLocation(Long userId, Location location);

	/**
	 * Convenience method to update the user's display name.<br>
	 * <br>
	 * <b>Note:</b>The user's last activity time stamp is also updated as a
	 * result of this method.
	 * 
	 * @param userId
	 *            - The ID of the user for whom the display name is being
	 *            updated.
	 * @param displayName
	 *            - The user's display name.
	 */
	public void updateScreenName(Long userId, String displayName);
	
	/**
	 * Convenience method to update the user's Google Cloud Messaging registration ID.<br>
	 * <br>
	 * <b>Note:</b>The user's last activity time stamp is also updated as a
	 * result of this method.
	 * 
	 * @param userId
	 * 			- The ID of the user for whom the GCM registration ID is being set.
	 * @param gcmRegistrationId
	 * 			- The Google Cloud Messaging registration ID to set.
	 */
	public void updateGcmRegistrationId(Long userId, String gcmRegistrationId);
}
