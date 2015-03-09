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
 * A request to create a user in the system.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class CreateUserRequest {

	private Long facebookProfileId;
	private Location location;

	/**
	 * Creates a new instance of this.
	 */
	public CreateUserRequest() {
		// Default constructor
	}

	/**
	 * Creates a new instance of this.
	 * 
	 * @param facebookProfileId
	 *            - The Facebook profile ID for the user being created.
	 * @param location
	 *            - The optional current location of the user being created.
	 */
	public CreateUserRequest(Long facebookProfileId, Location location) {
		this.facebookProfileId = facebookProfileId;
		this.location = location;
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
		return "{CreateUserRequest: facebookProfileId=" + this.facebookProfileId + ", location=" + this.location + "}";
	}
}
