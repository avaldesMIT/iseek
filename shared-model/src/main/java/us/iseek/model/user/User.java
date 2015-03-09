/**
 * Copyright (C) 2015 iSeek, Inc. 
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of iSeek, Inc.
 * You shall not disclose such confidential information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with 
 * iSeek, Inc.
 */
package us.iseek.model.user;

import java.sql.Timestamp;

import us.iseek.model.gps.Location;

/**
 * Represents a user in the system.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class User {

	private Long id;
	private String screenName;
	private Long facebookProfileId;

	private Location lastLocation;
	private Timestamp lastActivity;
	private Preferences preferences;

	/**
	 * Creates a new instance of this
	 */
	public User() {
		// Default constructor
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * @param id
	 *            - The id to set
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * @return the facebookProfileId
	 */
	public Long getFacebookProfileId() {
		return this.facebookProfileId;
	}

	/**
	 * @param facebookProfileId
	 *            - The facebookProfileId to set
	 */
	public void setFacebookProfileId(Long facebookProfileId) {
		this.facebookProfileId = facebookProfileId;
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
	 * @return the lastLocation
	 */
	public Location getLastLocation() {
		return this.lastLocation;
	}

	/**
	 * @param lastLocation
	 *            - The lastLocation to set
	 */
	public void setLastLocation(Location lastLocation) {
		this.lastLocation = lastLocation;
	}

	/**
	 * @return the lastActivity
	 */
	public Timestamp getLastActivity() {
		return this.lastActivity;
	}

	/**
	 * @param lastActivity
	 *            - The lastActivity to set
	 */
	public void setLastActivity(Timestamp lastActivity) {
		this.lastActivity = lastActivity;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "{User: id=" + this.id + ", screenName=" + this.screenName + ", facebookProfileId="
				+ this.facebookProfileId + ", preferences=" + this.preferences + ", lastLocation=" + this.lastLocation
				+ ", lastActivity=" + this.lastActivity + "}";
	}
}
