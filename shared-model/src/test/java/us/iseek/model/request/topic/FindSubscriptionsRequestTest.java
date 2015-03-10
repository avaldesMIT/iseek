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

import us.iseek.model.gps.Location;

/**
 * Tests the <tt>FindSubscriptionsRequest</tt>.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class FindSubscriptionsRequestTest {

	private FindSubscriptionsRequest findSubscriptionsRequest;

	@Before
	public void setUp() {
		this.findSubscriptionsRequest = new FindSubscriptionsRequest();
	}

	@Test
	public void testThatGetUserIdReturnsValueFromConstructor() {
		// Create mocks
		Long userId = Long.valueOf(12345L);
		Long topicId = Long.valueOf(58121L);
		Location location = EasyMock.createMock(Location.class);

		// Create entity
		this.findSubscriptionsRequest = new FindSubscriptionsRequest(userId, topicId, location);

		// Verify value
		Assert.assertEquals("GetUserId should return the userId that was set.", userId,
				this.findSubscriptionsRequest.getUserId());
	}

	@Test
	public void testThatGetTopicIdReturnsValueFromConstructor() {
		// Create mocks
		Long userId = Long.valueOf(12345L);
		Long topicId = Long.valueOf(58121L);
		Location location = EasyMock.createMock(Location.class);

		// Create entity
		this.findSubscriptionsRequest = new FindSubscriptionsRequest(userId, topicId, location);

		// Verify value
		Assert.assertEquals("GetTopicId should return the topicId that was set.", topicId,
				this.findSubscriptionsRequest.getTopicId());
	}

	@Test
	public void testThatGetLocationReturnsValueFromConstructor() {
		// Create mocks
		Long userId = Long.valueOf(12345L);
		Long topicId = Long.valueOf(58121L);
		Location location = EasyMock.createMock(Location.class);

		// Create entity
		this.findSubscriptionsRequest = new FindSubscriptionsRequest(userId, topicId, location);

		// Verify value
		Assert.assertEquals("GetLocation should return the location that was set.", location,
				this.findSubscriptionsRequest.getLocation());
	}

	@Test
	public void testThatGetUserIdReturnsTheUserIdThatWasSet() {
		// Create mock
		Long userId = Long.valueOf(12345L);

		// Set value
		this.findSubscriptionsRequest.setUserId(userId);

		// Verify value
		Assert.assertEquals("GetUserId should return the userId that was set.", userId,
				this.findSubscriptionsRequest.getUserId());
	}

	@Test
	public void testThatGetTopicIdReturnsTheTopicIdThatWasSet() {
		// Create mock
		Long topicId = Long.valueOf(58121L);

		// Set value
		this.findSubscriptionsRequest.setTopicId(topicId);

		// Verify value
		Assert.assertEquals("GetTopicId should return the topicId that was set.", topicId,
				this.findSubscriptionsRequest.getTopicId());
	}

	@Test
	public void testThatGetLocationReturnsTheLocationThatWasSet() {
		// Create mock
		Location location = EasyMock.createMock(Location.class);

		// Set value
		this.findSubscriptionsRequest.setLocation(location);

		// Verify value
		Assert.assertEquals("GetLocation should return the location that was set.", location,
				this.findSubscriptionsRequest.getLocation());
	}

	@Test
	public void testThatToStringContainsTheUserIdThatWasSet() {
		// Create mock
		Long userId = Long.valueOf(12345L);

		// Set value
		this.findSubscriptionsRequest.setUserId(userId);

		// Verify value
		Assert.assertThat("ToString should contain the userId that was set.", this.findSubscriptionsRequest.toString(),
				StringContains.containsString(String.valueOf(userId)));
	}

	@Test
	public void testThatToStringContainsTheTopicIdThatWasSet() {
		// Create mock
		Long topicId = Long.valueOf(58121L);

		// Set value
		this.findSubscriptionsRequest.setTopicId(topicId);

		// Verify value
		Assert.assertThat("ToString should contain the topicId that was set.",
				this.findSubscriptionsRequest.toString(), StringContains.containsString(String.valueOf(topicId)));
	}

	@Test
	public void testThatToStringContainsTheLocationThatWasSet() {
		// Create mock
		Location location = EasyMock.createMock(Location.class);

		// Set value
		this.findSubscriptionsRequest.setLocation(location);

		// Verify value
		Assert.assertThat("ToString should contain the location that was set.",
				this.findSubscriptionsRequest.toString(), StringContains.containsString(String.valueOf(location)));
	}
}
