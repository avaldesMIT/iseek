/**
 * Copyright (C) 2015 iSeek, Inc. 
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of iSeek, Inc.
 * You shall not disclose such confidential information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with 
 * iSeek, Inc.
 */
package us.iseek.model.communication;

import us.iseek.model.enums.MessageType;
import us.iseek.model.gps.Location;
import us.iseek.model.topics.HashTag;

/**
 * Represents a message being broadcast from a user to a hashtag in a particular
 * location.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class PublicMessage extends Message {

	private HashTag topic;
	private Location location;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MessageType getType() {
		return MessageType.PUBLIC;
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
		return "{PublicMessage: id=" + this.getId() + ", origin=" + this.getOrigin() + ", message="
				+ this.getMessage() + ", sentTimestamp=" + this.getSentTimestamp() + ", topic=" + this.topic
				+ ", location=" + this.location + "}";
	}
}
