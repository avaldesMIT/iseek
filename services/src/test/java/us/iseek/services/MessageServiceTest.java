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

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
 * Tests the <tt>MessageService</tt>.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class MessageServiceTest {

	private MessageService messageService;

	private MessageMapper messageMapper;
	private MessageBroadcaster messageBroadcaster;
	private PublicMessageMapper publicMessageMapper;
	private PrivateMessageMapper privateMessageMapper;
	private MessageBroadcasterFactory messageBroadcasterFactory;

	@Before
	public void setUp() {
		// Create test mocks
		this.messageMapper = EasyMock.createNiceMock(MessageMapper.class);
		this.publicMessageMapper = EasyMock.createNiceMock(PublicMessageMapper.class);
		this.privateMessageMapper = EasyMock.createNiceMock(PrivateMessageMapper.class);
		this.messageBroadcasterFactory = EasyMock.createNiceMock(MessageBroadcasterFactory.class);

		// Create other support mocks
		this.messageBroadcaster = EasyMock.createMock(MessageBroadcaster.class);
		this.messageBroadcaster.broadcast(EasyMock.anyObject(Message.class));
		EasyMock.expectLastCall().once();

		EasyMock.expect(this.messageBroadcasterFactory.getMessageBroadcaster(EasyMock.anyObject(MessageType.class)))
				.andReturn(messageBroadcaster).once();

		// Create entity
		this.messageService = new MessageService();
		this.messageService.setMessageMapper(this.messageMapper);
		this.messageService.setPublicMessageMapper(this.publicMessageMapper);
		this.messageService.setPrivateMessageMapper(this.privateMessageMapper);
		this.messageService.setMessageBroadcasterFactory(this.messageBroadcasterFactory);
	}

	@Test
	public void testThatSendMessageInsertsMessageInformationToTheDatabase() throws UnsupportedMessageTypeException {

		// Create test data
		PrivateMessage message = EasyMock.createNiceMock(PrivateMessage.class);
		EasyMock.expect(message.getType()).andReturn(MessageType.PRIVATE).anyTimes();
		EasyMock.replay(message);

		// Set mock expectations
		this.messageMapper.insert(message);
		EasyMock.expectLastCall().once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		this.messageService.sendMessage(message);
		EasyMock.verify(this.messageMapper);
	}

	@Test
	public void testThatSendMessageInsertsPrivateMessageInformationToTheDatabaseForPrivateMessages()
			throws UnsupportedMessageTypeException {

		// Create test data
		PrivateMessage message = EasyMock.createNiceMock(PrivateMessage.class);
		EasyMock.expect(message.getType()).andReturn(MessageType.PRIVATE).anyTimes();
		EasyMock.replay(message);

		// Set mock expectations
		this.privateMessageMapper.insert(message);
		EasyMock.expectLastCall().once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		this.messageService.sendMessage(message);
		EasyMock.verify(this.privateMessageMapper);
	}

	@Test
	public void testThatSendMessageInsertsPublicMessageInformationToTheDatabaseForPublicMessages()
			throws UnsupportedMessageTypeException {

		// Create test data
		PublicMessage message = EasyMock.createNiceMock(PublicMessage.class);
		EasyMock.expect(message.getType()).andReturn(MessageType.PUBLIC).anyTimes();
		EasyMock.replay(message);

		// Set mock expectations
		this.publicMessageMapper.insert(message);
		EasyMock.expectLastCall().once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		this.messageService.sendMessage(message);
		EasyMock.verify(this.publicMessageMapper);
	}

	@Test
	public void testThatSendMessageBroadcastsMessageForPrivateMessages() throws UnsupportedMessageTypeException {

		// Create test data
		PrivateMessage message = EasyMock.createNiceMock(PrivateMessage.class);
		EasyMock.expect(message.getType()).andReturn(MessageType.PRIVATE).anyTimes();
		EasyMock.replay(message);

		// Set mock expectations
		this.messageBroadcaster.broadcast(message);
		EasyMock.expectLastCall().once();

		this.messageBroadcasterFactory = EasyMock.createMock(MessageBroadcasterFactory.class);
		EasyMock.expect(this.messageBroadcasterFactory.getMessageBroadcaster(MessageType.PRIVATE))
				.andReturn(messageBroadcaster).once();
		this.messageService.setMessageBroadcasterFactory(this.messageBroadcasterFactory);

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		this.messageService.sendMessage(message);
		EasyMock.verify(this.messageBroadcasterFactory);
	}

	@Test
	public void testThatSendMessageBroadcastsMessageForPublicMessages() throws UnsupportedMessageTypeException {
		// Create test data
		PublicMessage message = EasyMock.createNiceMock(PublicMessage.class);
		EasyMock.expect(message.getType()).andReturn(MessageType.PUBLIC).anyTimes();
		EasyMock.replay(message);

		// Set mock expectations
		MessageBroadcaster messageBroadcaster = EasyMock.createMock(MessageBroadcaster.class);
		messageBroadcaster.broadcast(message);
		EasyMock.expectLastCall().once();
		EasyMock.replay(messageBroadcaster);

		this.messageBroadcasterFactory = EasyMock.createMock(MessageBroadcasterFactory.class);
		EasyMock.expect(this.messageBroadcasterFactory.getMessageBroadcaster(MessageType.PUBLIC))
				.andReturn(messageBroadcaster).once();
		this.messageService.setMessageBroadcasterFactory(this.messageBroadcasterFactory);

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		this.messageService.sendMessage(message);
		EasyMock.verify(this.messageBroadcasterFactory);
	}

	@Test(expected = UnsupportedMessageTypeException.class)
	public void testThatSendMessageThrowsUnsupportedMessageTypeExceptionForNullMessageType()
			throws UnsupportedMessageTypeException {

		// Create test data
		Message message = EasyMock.createNiceMock(Message.class);
		EasyMock.expect(message.getType()).andReturn(null).anyTimes();
		EasyMock.replay(message);

		// Test entity
		this.messageService.sendMessage(message);
	}

	@Test
	public void testThatGetMessageMapperReturnsTheMessageMapperThatWasSet() {
		// Create mock
		MessageMapper messageMapper = EasyMock.createNiceMock(MessageMapper.class);

		// Set value
		this.messageService.setMessageMapper(messageMapper);

		// Set up mock framework
		this.readyMockFramework();

		// Verify value
		Assert.assertEquals("GetMessageMapper should return the messageMapper that was set.", messageMapper,
				this.messageService.getMessageMapper());
	}

	@Test
	public void testThatGetPublicMessageMapperReturnsThePublicMessageMapperThatWasSet() {
		// Create mock
		PublicMessageMapper publicMessageMapper = EasyMock.createNiceMock(PublicMessageMapper.class);

		// Set value
		this.messageService.setPublicMessageMapper(publicMessageMapper);

		// Set up mock framework
		this.readyMockFramework();

		// Verify value
		Assert.assertEquals("GetPublicMessageMapper should return the publicMessageMapper that was set.",
				publicMessageMapper, this.messageService.getPublicMessageMapper());
	}

	@Test
	public void testThatGetPrivateMessageMapperReturnsThePrivateMessageMapperThatWasSet() {
		// Create mock
		PrivateMessageMapper privateMessageMapper = EasyMock.createNiceMock(PrivateMessageMapper.class);

		// Set value
		this.messageService.setPrivateMessageMapper(privateMessageMapper);

		// Set up mock framework
		this.readyMockFramework();

		// Verify value
		Assert.assertEquals("GetPrivateMessageMapper should return the privateMessageMapper that was set.",
				privateMessageMapper, this.messageService.getPrivateMessageMapper());
	}

	@Test
	public void testThatGetMessageBroadcasterFactoryReturnsTheMessageBroadcasterFactoryThatWasSet() {
		// Create mock
		MessageBroadcasterFactory messageBroadcasterFactory = EasyMock.createNiceMock(MessageBroadcasterFactory.class);

		// Set value
		this.messageService.setMessageBroadcasterFactory(messageBroadcasterFactory);

		// Set up mock framework
		this.readyMockFramework();

		// Verify value
		Assert.assertEquals("GetMessageBroadcasterFactory should return the messageBroadcasterFactory that was set.",
				messageBroadcasterFactory, this.messageService.getMessageBroadcasterFactory());
	}

	/**
	 * Readies the mock framework before each test
	 */
	private void readyMockFramework() {
		EasyMock.replay(this.messageMapper);
		EasyMock.replay(this.messageBroadcaster);
		EasyMock.replay(this.publicMessageMapper);
		EasyMock.replay(this.privateMessageMapper);
		EasyMock.replay(this.messageBroadcasterFactory);
	}
}
