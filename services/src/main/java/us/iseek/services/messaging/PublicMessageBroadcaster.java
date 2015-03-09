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
 * Broadcasts messages to the topic specified by the public message. The
 * broadcast is limited to the location also specified in the message. Thus,
 * users that are subscribed to a topic may not receive the message if they are
 * out of the area of interest specified by the message's location.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class PublicMessageBroadcaster implements MessageBroadcaster {

	private final Log log = LogFactory.getLog(PublicMessageBroadcaster.class);

	/**
	 * {@inheritDoc}
	 */
	public void broadcast(Message message) {
		log.debug("type=BROADCASTING_PUBLIC_MESSAGE, desc=Broadcasting public message, object=" + message);

		// TODO: Broadcast message
	}
}
