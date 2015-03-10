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
 * A request to find the subscriptions for a user, topic, and geographic
 * location.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class FindSubscriptionsRequest {

	private Long userId;
	private Long topicId;
	private Location location;

	/**
	 * Creates a new instance of this.
	 */
	public FindSubscriptionsRequest() {
		// Default constructor
	}

	/**
	 * Creates a new instance of this.
	 * 
	 * @param userId
	 *            - The ID of the user subscribed to the topic.
	 * @param topicId
	 *            - The ID of the topic the user is subscribed to.
	 * @param location
	 *            - The location where the user subscribed to the topic.
	 */
	public FindSubscriptionsRequest(Long userId, Long topicId, Location location) {
		this.userId = userId;
		this.topicId = topicId;
		this.setLocation(location);
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
	 * @return the topicId
	 */
	public Long getTopicId() {
		return this.topicId;
	}

	/**
	 * @param topicId
	 *            - The topicId to set
	 */
	public void setTopicId(Long topicId) {
		this.topicId = topicId;
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
		return "{FindSubscriptionsRequest: userId=" + this.userId + ", topicId=" + this.topicId + ", location="
				+ this.location + "}";
	}
}
