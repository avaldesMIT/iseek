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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import us.iseek.model.communication.Message;

/**
 * Broadcasts messages to the user or list of users specified by the private
 * message.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class PrivateMessageBroadcaster implements MessageBroadcaster {

	private final Log log = LogFactory.getLog(PrivateMessageBroadcaster.class);

	/**
	 * {@inheritDoc}
	 */
	public void broadcast(Message message) {
		log.debug("type=BROADCASTING_PRIVATE_MESSAGE, desc=Broadcasting private message, object=" + message);

		// TODO: Broadcast message
	}
}
