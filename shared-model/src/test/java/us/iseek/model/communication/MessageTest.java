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
