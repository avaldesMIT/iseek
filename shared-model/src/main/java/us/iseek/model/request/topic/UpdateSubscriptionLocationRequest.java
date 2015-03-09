/**
 * Copyright (C) 2015 iSeek, Inc. 
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of iSeek, Inc.
 * You shall not disclose such confidential information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with 
 * iSeek, Inc.
 */
package us.iseek.model.request.topic;

import us.iseek.model.gps.Location;

/**
 * A request to update a subscription's location.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class UpdateSubscriptionLocationRequest {

	private Long subscriptionId;
	private Location newLocation;

	/**
	 * Creates a new instance of this.
	 */
	public UpdateSubscriptionLocationRequest() {
		// Default constructor
	}

	/**
	 * Creates a new instance of this.
	 * 
	 * @param subscriptionId
	 *            - The ID of the subscription that references the topic for
	 *            which the user wishes to renew his/her subscription.
	 * @param newLocation
	 *            - The new location for the user.
	 */
	public UpdateSubscriptionLocationRequest(Long subscriptionId, Location newLocation) {

		this.subscriptionId = subscriptionId;
		this.newLocation = newLocation;
	}

	/**
	 * @return the subscriptionId
	 */
	public Long getSubscriptionId() {
		return this.subscriptionId;
	}

	/**
	 * @param subscriptionId
	 *            - The subscriptionId to set
	 */
	public void setSubscriptionId(Long subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	/**
	 * @return the newLocation
	 */
	public Location getNewLocation() {
		return this.newLocation;
	}

	/**
	 * @param newLocation
	 *            - The newLocation to set
	 */
	public void setNewLocation(Location newLocation) {
		this.newLocation = newLocation;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "{UpdateSubscriptionLocationRequest: subscriptionId=" + subscriptionId + ", newLocation=" + newLocation
				+ "}";
	}
}
