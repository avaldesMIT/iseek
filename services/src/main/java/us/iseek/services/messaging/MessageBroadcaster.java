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

import us.iseek.model.communication.Message;

/**
 * Broadcasts messages to the audience specified by the message.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public interface MessageBroadcaster {

	/**
	 * Broadcasts the message to the audience specified by the message.
	 * Implementing classes may specify how the audience is specified.
	 * 
	 * @param message
	 *            - The message to broadcast.
	 */
	public void broadcast(Message message);
}
