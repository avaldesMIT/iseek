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
import us.iseek.services.persistence.PreferencesMapper;
import us.iseek.services.persistence.UserMapper;

/**
 * Provides services to manage users preferences and lifecycle.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class UserService implements IUserService {

	private static final String DEFAULT_SCREEN_NAME = "iSeek User";
	
	private UserMapper userMapper;
	private PreferencesMapper preferencesMapper;

	/**
	 * {@inheritDoc}
	 */
	public User get(Long userId) {
		return this.userMapper.get(userId);
	}

	/**
	 * {@inheritDoc}
	 */
	public User getByFacebookProfileId(Long facebookProfileId) {
		return this.userMapper.getByFacebookProfileId(facebookProfileId);
	}

	/**
	 * {@inheritDoc}
	 */
	public User createUser(Long facebookProfileId, Location location) {
		// Determine if this user already exists
		User user = this.getByFacebookProfileId(facebookProfileId);

		// If the user already exists, update location and return updated user
		if (user != null) {
			return this.updateLocation(user.getId(), location);
		}

		// Otherwise, create user prototype
		user = new User();
		user.setLastLocation(location);
		user.setScreenName(DEFAULT_SCREEN_NAME);
		user.setFacebookProfileId(facebookProfileId);
		this.userMapper.insert(user);

		// UserId should be populated by framework
		Long userId = user.getId();

		// Create default preferences
		Preferences preferences = Preferences.DEFAULT_PREFERENCES;
		this.preferencesMapper.insert(userId, preferences);

		// Retrieve newly created user
		return this.userMapper.get(userId);
	}

	/**
	 * {@inheritDoc}
	 */
	public User updatePreferences(Long userId, Preferences preferences) {
		// Update preferences for this user
		this.preferencesMapper.update(userId, preferences);

		// Retrieve updated user
		return this.userMapper.get(userId);
	}

	/**
	 * {@inheritDoc}
	 */
	public User updateLocation(Long userId, Location location) {
		// Update user's location
		this.userMapper.updateLocation(userId, location);

		// Retrieve updated user
		return this.userMapper.get(userId);
	}

	/**
	 * {@inheritDoc}
	 */
	public User updateScreenName(Long userId, String screenName) {
		// Update user's display name
		this.userMapper.updateScreenName(userId, screenName);

		// Retrieve updated user
		return this.userMapper.get(userId);
	}

	/**
	 * @return the userMapper
	 */
	public UserMapper getUserMapper() {
		return this.userMapper;
	}

	/**
	 * @param userMapper
	 *            - The userMapper to set
	 */
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	/**
	 * @return the preferencesMapper
	 */
	public PreferencesMapper getPreferencesMapper() {
		return this.preferencesMapper;
	}

	/**
	 * @param preferencesMapper
	 *            - The preferencesMapper to set
	 */
	public void setPreferencesMapper(PreferencesMapper preferencesMapper) {
		this.preferencesMapper = preferencesMapper;
	}
}
