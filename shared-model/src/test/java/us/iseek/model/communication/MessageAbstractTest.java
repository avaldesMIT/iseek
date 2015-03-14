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

import org.hamcrest.core.StringContains;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the <tt>MessageAbstract</tt>.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class MessageAbstractTest {

	private MessageAbstract messageAbstract;

	@Before
	public void setUp() {
		// Create test entity
		this.messageAbstract = new MessageAbstract();
	}

	@Test
	public void testThatGetIdReturnsTheIdThatWasSet() {
		// Create mock
		Long id = Long.valueOf(1234L);

		// Set value
		this.messageAbstract.setId(id);

		// Verify value
		Assert.assertEquals("GetId should return the id that was set.", id, this.messageAbstract.getId());
	}

	@Test
	public void testThatGetSenderUserIdReturnsTheSenderUserIdThatWasSet() {
		// Create mock
		Long senderUserId = Long.valueOf(67312L);

		// Set value
		this.messageAbstract.setSenderUserId(senderUserId);

		// Verify value
		Assert.assertEquals("GetSenderUserId should return the senderUserId that was set.", senderUserId,
				this.messageAbstract.getSenderUserId());
	}

	@Test
	public void testThatGetMessageReturnsTheMessageThatWasSet() {
		// Create mock
		String message = "MESSAGE";

		// Set value
		this.messageAbstract.setMessage(message);

		// Verify value
		Assert.assertEquals("GetMessage should return the message that was set.", message,
				this.messageAbstract.getMessage());
	}

	@Test
	public void testThatGetSenderScreenNameReturnsTheSenderScreenNameThatWasSet() {
		// Create mock
		String senderScreenName = "SENDER_SCREEN_NAME";

		// Set value
		this.messageAbstract.setSenderScreenName(senderScreenName);

		// Verify value
		Assert.assertEquals("GetSenderScreenName should return the senderScreenName that was set.", senderScreenName,
				this.messageAbstract.getSenderScreenName());
	}

	@Test
	public void testThatGetSentTimestampReturnsTheSentTimestampThatWasSet() {
		// Create mock
		Timestamp sentTimestamp = new Timestamp(1L);

		// Set value
		this.messageAbstract.setSentTimestamp(sentTimestamp);

		// Verify value
		Assert.assertEquals("GetSentTimestamp should return the sentTimestamp that was set.", sentTimestamp,
				this.messageAbstract.getSentTimestamp());
	}

	@Test
	public void testThatToStringContainsTheIdThatWasSet() {
		// Create mock
		Long id = Long.valueOf(1234L);

		// Set value
		this.messageAbstract.setId(id);

		// Verify value
		Assert.assertThat("ToString should contain the id that was set.", this.messageAbstract.toString(),
				StringContains.containsString(String.valueOf(id)));
	}

	@Test
	public void testThatToStringContainsTheSenderUserIdThatWasSet() {
		// Create mock
		Long senderUserId = Long.valueOf(67312L);

		// Set value
		this.messageAbstract.setSenderUserId(senderUserId);

		// Verify value
		Assert.assertThat("ToString should contain the senderUserId that was set.", this.messageAbstract.toString(),
				StringContains.containsString(String.valueOf(senderUserId)));
	}

	@Test
	public void testThatToStringContainsTheMessageThatWasSet() {
		// Create mock
		String message = "MESSAGE";

		// Set value
		this.messageAbstract.setMessage(message);

		// Verify value
		Assert.assertThat("ToString should contain the message that was set.", this.messageAbstract.toString(),
				StringContains.containsString(String.valueOf(message)));
	}

	@Test
	public void testThatToStringContainsTheSenderScreenNameThatWasSet() {
		// Create mock
		String senderScreenName = "SENDER_SCREEN_NAME";

		// Set value
		this.messageAbstract.setSenderScreenName(senderScreenName);

		// Verify value
		Assert.assertThat("ToString should contain the senderScreenName that was set.",
				this.messageAbstract.toString(), StringContains.containsString(String.valueOf(senderScreenName)));
	}

	@Test
	public void testThatToStringContainsTheSentTimestampThatWasSet() {
		// Create mock
		Timestamp sentTimestamp = new Timestamp(1L);

		// Set value
		this.messageAbstract.setSentTimestamp(sentTimestamp);

		// Verify value
		Assert.assertThat("ToString should contain the sentTimestamp that was set.", this.messageAbstract.toString(),
				StringContains.containsString(String.valueOf(sentTimestamp)));
	}
}
