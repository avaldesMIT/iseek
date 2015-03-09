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
import us.iseek.model.topics.HashTag;

/**
 * A request to get all the user's subscribed to a topic in a particular
 * location.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class GetUsersInTopicRequest {

	private HashTag topic;
	private Location location;

	/**
	 * Creates a new instance of this.
	 */
	public GetUsersInTopicRequest() {
		// Default constructor
	}

	/**
	 * Creates a new instance of this.
	 * 
	 * @param topic
	 *            - The topic of discussion
	 * @param location
	 *            - The location where the topic is being discussed
	 */
	public GetUsersInTopicRequest(HashTag topic, Location location) {
		this.topic = topic;
		this.location = location;
	}

	/**
	 * @return the topic
	 */
	public HashTag getTopic() {
		return this.topic;
	}

	/**
	 * @param topic
	 *            - The topic to set
	 */
	public void setTopic(HashTag topic) {
		this.topic = topic;
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
		return "{GetUsersInTopicRequest: topic=" + topic + ", location=" + location + "}";
	}
}
