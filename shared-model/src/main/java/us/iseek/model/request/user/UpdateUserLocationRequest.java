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

import us.iseek.model.gps.Location;

/**
 * A request to update the user's location.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class UpdateUserLocationRequest {

	private Long userId;
	private Location location;

	/**
	 * Creates a new instance of this.
	 */
	public UpdateUserLocationRequest() {
		// Default constructor
	}

	/**
	 * Creates a new instance of this.
	 * 
	 * @param userId
	 *            - The ID of the user for whom the location is being updated.
	 * @param location
	 *            - The user's new location.
	 */
	public UpdateUserLocationRequest(Long userId, Location location) {
		this.userId = userId;
		this.location = location;
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
	 * @return the location
	 */
	public Location getLocation() {
		return this.location;
	}

	/**
	 * @param location
	 *            - The location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "{UpdateUserLocationRequest: userId=" + userId + ", location=" + location + "}";
	}
}
