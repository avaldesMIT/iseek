/**
 * Copyright (C) 2015 iSeek, Inc.
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of iSeek, Inc.
 * You shall not disclose such confidential information and shall use it only
 * in accordance with the terms of the license agreement you entered into with
 * iSeek, Inc.
 */
package us.iseek.model.topics;

import org.easymock.EasyMock;
import org.hamcrest.core.StringContains;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import us.iseek.model.gps.Location;
import us.iseek.model.user.User;

/**
 * Tests the <tt>Subscription</tt>.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class SubscriptionTest {

	private Subscription subscription;

	@Before
	public void setUp() {
		this.subscription = new Subscription();
	}

	@Test
	public void testThatGetIdReturnsTheIdThatWasSet() {
		// Create mock
		Long id = Long.valueOf(12345L);

		// Set value
		this.subscription.setId(id);

		// Verify value
		Assert.assertEquals("GetId should return the id that was set.", id, this.subscription.getId());
	}

	@Test
	public void testThatGetTimeToLiveReturnsTheTimeToLiveThatWasSet() {
		// Create mock
		Long timeToLive = Long.valueOf(12345L);

		// Set value
		this.subscription.setTimeToLive(timeToLive);

		// Verify value
		Assert.assertEquals("GetTimeToLive should return the timeToLive that was set.", timeToLive,
				this.subscription.getTimeToLive());
	}

	@Test
	public void testThatGetUserReturnsTheUserThatWasSet() {
		// Create mock
		User user = EasyMock.createMock(User.class);

		// Set value
		this.subscription.setUser(user);

		// Verify value
		Assert.assertEquals("GetUser should return the user that was set.", user, this.subscription.getUser());
	}

	@Test
	public void testThatGetTopicReturnsTheTopicThatWasSet() {
		// Create mock
		HashTag topic = EasyMock.createMock(HashTag.class);

		// Set value
		this.subscription.setTopic(topic);

		// Verify value
		Assert.assertEquals("GetTopic should return the topic that was set.", topic, this.subscription.getTopic());
	}

	@Test
	public void testThatGetLocationReturnsTheLocationThatWasSet() {
		// Create mock
		Location location = EasyMock.createMock(Location.class);

		// Set value
		this.subscription.setLocation(location);

		// Verify value
		Assert.assertEquals("GetLocation should return the location that was set.", location,
				this.subscription.getLocation());
	}

	@Test
	public void testThatToStringContainsTheIdThatWasSet() {
		// Create mock
		Long id = Long.valueOf(12345L);

		// Set value
		this.subscription.setId(id);

		// Verify value
		Assert.assertThat("ToString should contain the id that was set.", this.subscription.toString(),
				StringContains.containsString(String.valueOf(id)));
	}

	@Test
	public void testThatToStringContainsTheTimeToLiveThatWasSet() {
		// Create mock
		Long timeToLive = Long.valueOf(12345L);

		// Set value
		this.subscription.setTimeToLive(timeToLive);

		// Verify value
		Assert.assertThat("ToString should contain the timeToLive that was set.", this.subscription.toString(),
				StringContains.containsString(String.valueOf(timeToLive)));
	}

	@Test
	public void testThatToStringContainsTheUserThatWasSet() {
		// Create mock
		User user = EasyMock.createMock(User.class);

		// Set value
		this.subscription.setUser(user);

		// Verify value
		Assert.assertThat("ToString should contain the user that was set.", this.subscription.toString(),
				StringContains.containsString(String.valueOf(user)));
	}

	@Test
	public void testThatToStringContainsTheTopicThatWasSet() {
		// Create mock
		HashTag topic = EasyMock.createMock(HashTag.class);

		// Set value
		this.subscription.setTopic(topic);

		// Verify value
		Assert.assertThat("ToString should contain the topic that was set.", this.subscription.toString(),
				StringContains.containsString(String.valueOf(topic)));
	}

	@Test
	public void testThatToStringContainsTheLocationThatWasSet() {
		// Create mock
		Location location = EasyMock.createMock(Location.class);

		// Set value
		this.subscription.setLocation(location);

		// Verify value
		Assert.assertThat("ToString should contain the location that was set.", this.subscription.toString(),
				StringContains.containsString(String.valueOf(location)));
	}
}
