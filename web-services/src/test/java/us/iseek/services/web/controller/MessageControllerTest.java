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

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import us.iseek.model.communication.PrivateMessage;
import us.iseek.model.communication.PublicMessage;
import us.iseek.model.exception.UnsupportedMessageTypeException;
import us.iseek.services.IMessageService;

/**
 * Tests the <tt>MessageController</tt>.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class MessageControllerTest {

	private MessageController messageController;

	private IMessageService messageService;

	@Before
	public void setUp() {
		// Create mocks
		this.messageService = EasyMock.createMock(IMessageService.class);

		// Create entity to test
		this.messageController = new MessageController();
		this.messageController.setMessageService(this.messageService);
	}

	@Test
	public void testThatSendPublicMessageDelegatesCallToService() throws UnsupportedMessageTypeException {
		// Create mocks
		PublicMessage message = EasyMock.createNiceMock(PublicMessage.class);

		// Set expectations
		this.messageService.sendMessage(message);
		EasyMock.expectLastCall().once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		this.messageController.sendPublicMessage(message);
		EasyMock.verify(this.messageService);
	}

	@Test
	public void testThatSendPrivateMessageDelegatesCallToService() throws UnsupportedMessageTypeException {
		// Create mocks
		PrivateMessage message = EasyMock.createNiceMock(PrivateMessage.class);

		// Set expectations
		this.messageService.sendMessage(message);
		EasyMock.expectLastCall().once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		this.messageController.sendPrivateMessage(message);
		EasyMock.verify(this.messageService);
	}

	@Test
	public void testThatGetMessageServiceReturnsTheMessageServiceThatWasSet() {
		// Create mock
		IMessageService messageService = EasyMock.createNiceMock(IMessageService.class);

		// Set value
		this.messageController.setMessageService(messageService);

		// Verify value
		Assert.assertEquals("GetMessageService should return the messageService that was set.", messageService,
				this.messageController.getMessageService());
	}

	/**
	 * Readies the mock framework before each test
	 */
	private void readyMockFramework() {
		EasyMock.replay(this.messageService);
	}
}
