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

import java.sql.Timestamp;

import us.iseek.model.enums.MessageType;
import us.iseek.model.user.User;

/**
 * Represents a message being sent in the system.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public abstract class Message {

	private Long id;
	private User origin;
	private String message;
	private Timestamp sentTimestamp;

	/**
	 * Gets the type of the message being sent.
	 * 
	 * @return The message type.
	 */
	public abstract MessageType getType();

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
	 * @return the origin
	 */
	public User getOrigin() {
		return this.origin;
	}

	/**
	 * @param origin
	 *            - The origin to set
	 */
	public void setOrigin(User origin) {
		this.origin = origin;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * @param message
	 *            - The message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the sentTimestamp
	 */
	public Timestamp getSentTimestamp() {
		return this.sentTimestamp;
	}

	/**
	 * @param sentTimestamp
	 *            - The sentTimestamp to set
	 */
	public void setSentTimestamp(Timestamp sentTimestamp) {
		this.sentTimestamp = sentTimestamp;
	}
}
