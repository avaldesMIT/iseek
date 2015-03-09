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
import us.iseek.model.communication.PrivateMessage;
import us.iseek.model.communication.PublicMessage;
import us.iseek.model.enums.MessageType;
import us.iseek.model.exception.UnsupportedMessageTypeException;
import us.iseek.services.messaging.MessageBroadcaster;
import us.iseek.services.messaging.MessageBroadcasterFactory;
import us.iseek.services.persistence.MessageMapper;
import us.iseek.services.persistence.PrivateMessageMapper;
import us.iseek.services.persistence.PublicMessageMapper;

/**
 * Provides services to send messages to other users or groups of users, or to
 * broadcast messages to a topic.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class MessageService implements IMessageService {

	private MessageMapper messageMapper;
	private PublicMessageMapper publicMessageMapper;
	private PrivateMessageMapper privateMessageMapper;
	private MessageBroadcasterFactory messageBroadcasterFactory;

	/**
	 * Sends <tt>PrivateMessage</tt> and <tt>PublicMessage</tt>s to their
	 * specified destinations and persists the messages in the store.
	 * 
	 * {@inheritDoc}
	 */
	public void sendMessage(Message message) throws UnsupportedMessageTypeException {

		// Persist base message
		this.messageMapper.insert(message);

		// Persist message according to type
		if (MessageType.PRIVATE == message.getType()) {
			this.persistMessage((PrivateMessage) message);
		} else if (MessageType.PUBLIC == message.getType()) {
			this.persistMessage((PublicMessage) message);
		} else {
			throw new UnsupportedMessageTypeException("Message type not supported.");
		}

		// Get broadcaster for this message type
		MessageBroadcaster messageBroadcaster = this.messageBroadcasterFactory.getMessageBroadcaster(message.getType());

		// Broadcast message
		messageBroadcaster.broadcast(message);
	}

	/**
	 * Persists the message to the store.<br>
	 * <br>
	 * <b>Note:</b> Calling this method also updates the origin user's last
	 * activity timestamp.
	 * 
	 * @param privateMessage
	 *            - The message to persist.
	 */
	private void persistMessage(PrivateMessage privateMessage) {

		// Persist message
		this.privateMessageMapper.insert(privateMessage);
	}

	/**
	 * Persists the message to the store and associates it to the topic and
	 * location specified.<br>
	 * <br>
	 * <b>Note:</b> Calling this method also updates the origin user's last
	 * activity timestamp.
	 * 
	 * @param publicMessage
	 *            - The message to persist.
	 */
	private void persistMessage(PublicMessage publicMessage) {
		// Persist message
		this.publicMessageMapper.insert(publicMessage);
	}

	/**
	 * @return the messageMapper
	 */
	public MessageMapper getMessageMapper() {
		return this.messageMapper;
	}

	/**
	 * @param messageMapper
	 *            - The messageMapper to set
	 */
	public void setMessageMapper(MessageMapper messageMapper) {
		this.messageMapper = messageMapper;
	}

	/**
	 * @return the publicMessageMapper
	 */
	public PublicMessageMapper getPublicMessageMapper() {
		return this.publicMessageMapper;
	}

	/**
	 * @param publicMessageMapper
	 *            - The publicMessageMapper to set
	 */
	public void setPublicMessageMapper(PublicMessageMapper publicMessageMapper) {
		this.publicMessageMapper = publicMessageMapper;
	}

	/**
	 * @return the privateMessageMapper
	 */
	public PrivateMessageMapper getPrivateMessageMapper() {
		return this.privateMessageMapper;
	}

	/**
	 * @param privateMessageMapper
	 *            - The privateMessageMapper to set
	 */
	public void setPrivateMessageMapper(PrivateMessageMapper privateMessageMapper) {
		this.privateMessageMapper = privateMessageMapper;
	}

	/**
	 * @return the messageBroadcasterFactory
	 */
	public MessageBroadcasterFactory getMessageBroadcasterFactory() {
		return this.messageBroadcasterFactory;
	}

	/**
	 * @param messageBroadcasterFactory
	 *            - The messageBroadcasterFactory to set
	 */
	public void setMessageBroadcasterFactory(MessageBroadcasterFactory messageBroadcasterFactory) {
		this.messageBroadcasterFactory = messageBroadcasterFactory;
	}
}
