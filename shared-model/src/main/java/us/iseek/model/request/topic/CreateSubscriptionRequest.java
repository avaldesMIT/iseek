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

import us.iseek.model.topics.HashTag;

/**
 * A request to add a user to a topic of conversation in a particular location.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class CreateSubscriptionRequest {

	private Long userId;
	private HashTag topic;

	/**
	 * Creates a new instance of this.
	 */
	public CreateSubscriptionRequest() {
		// Default constructor
	}

	/**
	 * Creates a new instance of this.
	 * 
	 * @param userId
	 *            - The ID of the user joining the topic.
	 * @param topic
	 *            - The topic being joined.
	 */
	public CreateSubscriptionRequest(Long userId, HashTag topic) {
		this.userId = userId;
		this.topic = topic;
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
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "{CreateSubscriptionRequest: userId=" + userId + ", topic=" + topic + "}";
	}
}
