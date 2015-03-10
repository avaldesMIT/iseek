/**
 * Copyright (C) 2015 iSeek, Inc. 
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of iSeek, Inc.
 * You shall not disclose such confidential information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with 
 * iSeek, Inc.
 */
package us.iseek.services;

import us.iseek.model.communication.Message;
import us.iseek.model.exception.UnsupportedMessageTypeException;

/**
 * Provides services to send messages to other users or groups of users, or to
 * broadcast messages to a topic.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public interface IMessageService {

	/**
	 * Sends the message according to the instructions specified in the message.<br>
	 * <br>
	 * <b>Note:</b> Calling this method also updates the origin user's last
	 * activity timestamp.
	 * 
	 * @param message
	 *            - The message to send, along with the instructions on who to
	 *            send it to.
	 * @throws UnsupportedMessageTypeException
	 *             - If the message type provided is not supported by the
	 *             system.
	 */
	public void sendMessage(Message message) throws UnsupportedMessageTypeException;
}
