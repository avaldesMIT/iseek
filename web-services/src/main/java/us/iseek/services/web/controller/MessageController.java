/**
 * Copyright (C) 2015 iSeek, Inc. 
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of iSeek, Inc.
 * You shall not disclose such confidential information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with 
 * iSeek, Inc.
 */
package us.iseek.services.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import us.iseek.model.communication.PrivateMessage;
import us.iseek.model.communication.PublicMessage;
import us.iseek.model.exception.UnsupportedMessageTypeException;
import us.iseek.services.IMessageService;

/**
 * Provides REST services to send messages to other users or groups of users, or
 * to broadcast messages to a topic.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
@RestController
@RequestMapping("/message")
public class MessageController {

	private final Log log = LogFactory.getLog(MessageController.class);

	@Autowired
	private IMessageService messageService;

	/**
	 * {@inheritDoc}
	 */
	@RequestMapping(value = "/public/send", method = RequestMethod.POST)
	public void sendPublicMessage(@RequestBody PublicMessage message) throws UnsupportedMessageTypeException {

		log.debug("type=RECEIVED_REST_MESSAGE_REQUEST, " + "desc=Received REST request to send message, param="
				+ message);
		this.messageService.sendMessage(message);
	}

	/**
	 * {@inheritDoc}
	 */
	@RequestMapping(value = "/private/send", method = RequestMethod.POST)
	public void sendPrivateMessage(@RequestBody PrivateMessage message) throws UnsupportedMessageTypeException {

		log.debug("type=RECEIVED_REST_MESSAGE_REQUEST, " + "desc=Received REST request to send message, param="
				+ message);
		this.messageService.sendMessage(message);
	}

	/**
	 * @return the messageService
	 */
	public IMessageService getMessageService() {
		return this.messageService;
	}

	/**
	 * @param messageService
	 *            - The messageService to set
	 */
	public void setMessageService(IMessageService messageService) {
		this.messageService = messageService;
	}
}
