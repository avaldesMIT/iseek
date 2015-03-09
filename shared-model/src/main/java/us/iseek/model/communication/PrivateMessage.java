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

import java.util.List;

import us.iseek.model.enums.MessageType;

/**
 * Represents a message being sent from a user to another user or group of
 * users.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class PrivateMessage extends Message {

	private List<Long> destinationUserIds;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MessageType getType() {
		return MessageType.PRIVATE;
	}

	/**
	 * @return the destinationUserIds
	 */
	public List<Long> getDestinationUserIds() {
		return this.destinationUserIds;
	}

	/**
	 * @param destinationUserIds
	 *            - The destinationUserIds to set
	 */
	public void setDestinationUserIds(List<Long> destinationUserIds) {
		this.destinationUserIds = destinationUserIds;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "{PrivateMessage: id=" + this.getId() + ", origin=" + this.getOrigin() + ", message="
				+ this.getMessage() + ", sentTimestamp=" + this.getSentTimestamp() + ", destinationUserIds="
				+ this.destinationUserIds + "}";
	}
}
