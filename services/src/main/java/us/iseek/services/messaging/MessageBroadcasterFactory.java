/**
 * Copyright (C) 2015 iSeek, Inc. 
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of iSeek, Inc.
 * You shall not disclose such confidential information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with 
 * iSeek, Inc.
 */
package us.iseek.services.messaging;

import java.util.Map;

import us.iseek.model.enums.MessageType;

/**
 * A factory for different types of message broadcasters.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class MessageBroadcasterFactory {

	private Map<MessageType, MessageBroadcaster> messageBroadcasterMap;

	/**
	 * Retrieves the message broadcaster for the message type specified.
	 * 
	 * @param messageType
	 *            - The message type for which a message broadcaster is sought.
	 * @return The message broadcaster that is able to broadcast the type of
	 *         message specified, or null if there is no such broadcaster in the
	 *         system.
	 */
	public MessageBroadcaster getMessageBroadcaster(MessageType messageType) {
		return this.messageBroadcasterMap.get(messageType);
	}

	/**
	 * @return the messageBroadcasterMap
	 */
	public Map<MessageType, MessageBroadcaster> getMessageBroadcasterMap() {
		return this.messageBroadcasterMap;
	}

	/**
	 * @param messageBroadcasterMap
	 *            - The messageBroadcasterMap to set
	 */
	public void setMessageBroadcasterMap(Map<MessageType, MessageBroadcaster> messageBroadcasterMap) {
		this.messageBroadcasterMap = messageBroadcasterMap;
	}
}
