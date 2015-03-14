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

/**
 * Represents a summary of the message. Provides references to entities such as
 * the sender.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class MessageAbstract {

	private Long id;
	private Long senderUserId;
	private String message;
	private String senderScreenName;
	private Timestamp sentTimestamp;

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
	 * @return the senderUserId
	 */
	public Long getSenderUserId() {
		return this.senderUserId;
	}

	/**
	 * @param senderUserId
	 *            - The senderUserId to set
	 */
	public void setSenderUserId(Long senderUserId) {
		this.senderUserId = senderUserId;
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

	/**
	 * @return the senderScreenName
	 */
	public String getSenderScreenName() {
		return this.senderScreenName;
	}

	/**
	 * @param senderScreenName
	 *            - The senderScreenName to set
	 */
	public void setSenderScreenName(String senderScreenName) {
		this.senderScreenName = senderScreenName;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "{MessageAbstract: id=" + this.id + ", senderUserId=" + this.senderUserId + ", senderScreenName="
				+ this.senderScreenName + ", message=" + this.message + ", sentTimestamp=" + this.sentTimestamp + "}";
	}
}
