/**
 * Copyright (C) 2015 iSeek, Inc.
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of iSeek, Inc.
 * You shall not disclose such confidential information and shall use it only
 * in accordance with the terms of the license agreement you entered into with
 * iSeek, Inc.
 */
package us.iseek.model.request.topic;

import org.easymock.EasyMock;
import org.hamcrest.core.StringContains;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import us.iseek.model.topics.HashTag;

/**
 * Tests the <tt>CreateSubscriptionRequest</tt>.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class CreateSubscriptionRequestTest {

	private CreateSubscriptionRequest createSubscriptionRequest;

	@Before
	public void setUp() {
		this.createSubscriptionRequest = new CreateSubscriptionRequest();
	}

	@Test
	public void testThatGetUserIdReturnsValueFromConstructor() {
		// Create mocks
		Long userId = Long.valueOf(12345L);
		HashTag topic = EasyMock.createMock(HashTag.class);

		// Create entity
		this.createSubscriptionRequest = new CreateSubscriptionRequest(userId, topic);

		// Verify value
		Assert.assertEquals("GetUserId should return the userId that was set.", userId,
				this.createSubscriptionRequest.getUserId());
	}

	@Test
	public void testThatGetTopicReturnsValueFromConstructor() {
		// Create mocks
		Long userId = Long.valueOf(12345L);
		HashTag topic = EasyMock.createMock(HashTag.class);

		// Create entity
		this.createSubscriptionRequest = new CreateSubscriptionRequest(userId, topic);

		// Verify value
		Assert.assertEquals("GetTopic should return the topic that was set.", topic,
				this.createSubscriptionRequest.getTopic());
	}

	@Test
	public void testThatGetUserIdReturnsTheUserIdThatWasSet() {
		// Create mock
		Long userId = Long.valueOf(12345L);

		// Set value
		this.createSubscriptionRequest.setUserId(userId);

		// Verify value
		Assert.assertEquals("GetUserId should return the userId that was set.", userId,
				this.createSubscriptionRequest.getUserId());
	}

	@Test
	public void testThatGetTopicReturnsTheTopicThatWasSet() {
		// Create mock
		HashTag topic = EasyMock.createMock(HashTag.class);

		// Set value
		this.createSubscriptionRequest.setTopic(topic);

		// Verify value
		Assert.assertEquals("GetTopic should return the topic that was set.", topic,
				this.createSubscriptionRequest.getTopic());
	}

	@Test
	public void testThatToStringContainsTheUserIdThatWasSet() {
		// Create mock
		Long userId = Long.valueOf(12345L);

		// Set value
		this.createSubscriptionRequest.setUserId(userId);

		// Verify value
		Assert.assertThat("ToString should contain the userId that was set.",
				this.createSubscriptionRequest.toString(), StringContains.containsString(String.valueOf(userId)));
	}

	@Test
	public void testThatToStringContainsTheTopicThatWasSet() {
		// Create mock
		HashTag topic = EasyMock.createMock(HashTag.class);

		// Set value
		this.createSubscriptionRequest.setTopic(topic);

		// Verify value
		Assert.assertThat("ToString should contain the topic that was set.", this.createSubscriptionRequest.toString(),
				StringContains.containsString(String.valueOf(topic)));
	}
}
