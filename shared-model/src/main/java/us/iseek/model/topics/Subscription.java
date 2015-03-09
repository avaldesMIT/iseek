/**
 * Copyright (C) 2015 iSeek, Inc. 
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of iSeek, Inc.
 * You shall not disclose such confidential information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with 
 * iSeek, Inc.
 */
package us.iseek.model.topics;

import us.iseek.model.gps.Location;
import us.iseek.model.user.User;

/**
 * Defines the subscription of a user in a particular location to a certain
 * topic
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class Subscription {

	private Long id;
	private Long timeToLive;

	private User user;
	private HashTag topic;
	private Location location;

	/**
	 * Creates a new instance of this.
	 */
	public Subscription() {
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
	 * @return the user
	 */
	public User getUser() {
		return this.user;
	}

	/**
	 * @param user
	 *            - The user to set
	 */
	public void setUser(User user) {
		this.user = user;
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
	 * Returns the remaining time before this subscription expires, in
	 * seconds.
	 * 
	 * @return the timeToLive - The time to live in seconds
	 */
	public Long getTimeToLive() {
		return this.timeToLive;
	}

	/**
	 * Sets the time remaining before this subscription expires, in
	 * seconds.
	 * 
	 * @param timeToLive
	 *            - The timeToLive in seconds to set
	 */
	public void setTimeToLive(Long timeToLive) {
		this.timeToLive = timeToLive;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "{Subscription: id=" + this.id + ", user=" + this.user + ", topic=" + this.topic + ", location="
				+ this.location + ", timeToLive=" + this.timeToLive + "}";
	}
}
