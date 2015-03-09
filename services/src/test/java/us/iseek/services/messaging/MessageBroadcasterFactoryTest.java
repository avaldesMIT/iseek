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

import java.util.HashMap;
import java.util.Map;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import us.iseek.model.enums.MessageType;

/**
 * Tests the <tt>MessageBroadcasterFactory</tt>.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class MessageBroadcasterFactoryTest {

	private MessageBroadcasterFactory messageBroadcasterFactory;

	private MessageBroadcaster publicBroadcaster;
	private MessageBroadcaster privateBroadcaster;
	private Map<MessageType, MessageBroadcaster> messageBroadcasterMap;

	@Before
	public void setUp() {
		// Create mocks
		this.publicBroadcaster = EasyMock.createNiceMock(PublicMessageBroadcaster.class);
		this.privateBroadcaster = EasyMock.createNiceMock(PrivateMessageBroadcaster.class);

		// Create test data
		this.messageBroadcasterMap = new HashMap<MessageType, MessageBroadcaster>();
		this.messageBroadcasterMap.put(MessageType.PUBLIC, this.publicBroadcaster);
		this.messageBroadcasterMap.put(MessageType.PRIVATE, this.privateBroadcaster);

		// Create test entity
		this.messageBroadcasterFactory = new MessageBroadcasterFactory();
		this.messageBroadcasterFactory.setMessageBroadcasterMap(this.messageBroadcasterMap);
	}

	@Test
	public void testThatGetMessageBroadcasterGetsPublicBroadcasterForPublicMessageType() {
		Assert.assertEquals("Factory should return public broadcaster for public message type.",
				this.publicBroadcaster, this.messageBroadcasterFactory.getMessageBroadcaster(MessageType.PUBLIC));
	}

	@Test
	public void testThatGetMessageBroadcasterGetsPrivateBroadcasterForPrivateMessageType() {
		Assert.assertEquals("Factory should return private broadcaster for private message type.",
				this.privateBroadcaster, this.messageBroadcasterFactory.getMessageBroadcaster(MessageType.PRIVATE));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testThatGetMessageBroadcasterMapReturnsTheMessageBroadcasterMapThatWasSet() {
		// Create mock
		Map<MessageType, MessageBroadcaster> messageBroadcasterMap = EasyMock.createNiceMock(Map.class);

		// Set value
		this.messageBroadcasterFactory.setMessageBroadcasterMap(messageBroadcasterMap);

		// Verify value
		Assert.assertEquals("GetMessageBroadcasterMap should return the messageBroadcasterMap that was set.",
				messageBroadcasterMap, this.messageBroadcasterFactory.getMessageBroadcasterMap());
	}
}
