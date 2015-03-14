/**
 * Copyright (C) 2015 iSeek, Inc.
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of iSeek, Inc.
 * You shall not disclose such confidential information and shall use it only
 * in accordance with the terms of the license agreement you entered into with
 * iSeek, Inc.
 */
package us.iseek.model.communication;

import java.sql.Timestamp;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import us.iseek.model.user.User;

/**
 * Tests the <tt>Message</tt>.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class MessageTest {

	private Message message;

	@Before
	public void setUp() {
		this.message = EasyMock.createMockBuilder(Message.class).createMock();
	}

	@Test
	public void testThatCreateMessageAbstractCreatesAbstractWithIdThatWasSet() {
		// Create mock
		Long id = Long.valueOf(1234L);

		// Set value
		this.message.setId(id);

		// Test entity
		MessageAbstract messageAbstract = this.message.createMessageAbstract();

		// Verify value
		Assert.assertEquals("CreateMessageAbstract should create abstract with the id that was set.", id,
				messageAbstract.getId());
	}

	@Test
	public void testThatCreateMessageAbstractCreatesAbstractWithSenderUserIdFromOriginThatWasSet() {
		// Create mocks
		Long senderUserId = Long.valueOf(67312L);
		User origin = EasyMock.createNiceMock(User.class);
		EasyMock.expect(origin.getId()).andReturn(senderUserId).anyTimes();
		EasyMock.replay(origin);

		// Set value
		this.message.setOrigin(origin);

		// Test entity
		MessageAbstract messageAbstract = this.message.createMessageAbstract();

		// Verify value
		Assert.assertEquals("CreateMessageAbstract should create abstract with the user ID from origin that was set.",
				senderUserId, messageAbstract.getSenderUserId());
	}

	@Test
	public void testThatCreateMessageAbstractCreatesAbstractWithSenderScreenNameFromOriginThatWasSet() {
		// Create mocks
		String senderScreenName = "SENDER_SCREEN_NAME";
		User origin = EasyMock.createNiceMock(User.class);
		EasyMock.expect(origin.getScreenName()).andReturn(senderScreenName).anyTimes();
		EasyMock.replay(origin);

		// Set value
		this.message.setOrigin(origin);

		// Test entity
		MessageAbstract messageAbstract = this.message.createMessageAbstract();

		// Verify value
		Assert.assertEquals(
				"CreateMessageAbstract should create abstract with the user screen name from origin that was set.",
				senderScreenName, messageAbstract.getSenderScreenName());
	}

	@Test
	public void testThatCreateMessageAbstractCreatesAbstractWithNoSenderUserIdIfNoUserWasSet() {
		// Set value
		this.message.setOrigin(null);

		// Test entity
		MessageAbstract messageAbstract = this.message.createMessageAbstract();

		// Verify value
		Assert.assertNull("CreateMessageAbstract should create abstract with no senderUserId if no origin was set.",
				messageAbstract.getSenderUserId());
	}

	@Test
	public void testThatCreateMessageAbstractCreatesAbstractWithNoSenderScreenNameIfNoUserWasSet() {
		// Set value
		this.message.setOrigin(null);

		// Test entity
		MessageAbstract messageAbstract = this.message.createMessageAbstract();

		// Verify value
		Assert.assertNull(
				"CreateMessageAbstract should create abstract with no senderScreenName if no origin was set.",
				messageAbstract.getSenderScreenName());
	}

	@Test
	public void testThatCreateMessageAbstractCreatesAbstractWithMessageThatWasSet() {
		// Create mock
		String message = "MESSAGE";

		// Set value
		this.message.setMessage(message);

		// Test entity
		MessageAbstract messageAbstract = this.message.createMessageAbstract();

		// Verify value
		Assert.assertEquals("CreateMessageAbstract should create abstract with the message that was set.", message,
				messageAbstract.getMessage());
	}

	@Test
	public void testThatCreateMessageAbstractCreatesAbstractWithSentTimestampThatWasSet() {
		// Create mock
		Timestamp sentTimestamp = new Timestamp(1L);

		// Set value
		this.message.setSentTimestamp(sentTimestamp);

		// Test entity
		MessageAbstract messageAbstract = this.message.createMessageAbstract();

		// Verify value
		Assert.assertEquals("CreateMessageAbstract should create abstract with the sentTimestamp that was set.",
				sentTimestamp, messageAbstract.getSentTimestamp());
	}

	@Test
	public void testThatGetIdReturnsTheIdThatWasSet() {
		// Create mock
		Long id = Long.valueOf(12345L);

		// Set value
		this.message.setId(id);

		// Verify value
		Assert.assertEquals("GetId should return the id that was set.", id, this.message.getId());
	}

	@Test
	public void testThatGetOriginReturnsTheOriginThatWasSet() {
		// Create mock
		User origin = EasyMock.createMock(User.class);

		// Set value
		this.message.setOrigin(origin);

		// Verify value
		Assert.assertEquals("GetOrigin should return the origin that was set.", origin, this.message.getOrigin());
	}

	@Test
	public void testThatGetMessageReturnsTheMessageThatWasSet() {
		// Create mock
		String message = "MESSAGE";

		// Set value
		this.message.setMessage(message);

		// Verify value
		Assert.assertEquals("GetMessage should return the message that was set.", message, this.message.getMessage());
	}

	@Test
	public void testThatGetSentTimestampReturnsTheSentTimestampThatWasSet() {
		// Create mock
		Timestamp sentTimestamp = EasyMock.createMock(Timestamp.class);

		// Set value
		this.message.setSentTimestamp(sentTimestamp);

		// Verify value
		Assert.assertEquals("GetSentTimestamp should return the sentTimestamp that was set.", sentTimestamp,
				this.message.getSentTimestamp());
	}
}
