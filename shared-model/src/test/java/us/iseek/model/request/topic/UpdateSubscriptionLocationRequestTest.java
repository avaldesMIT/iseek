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
 * Tests the <tt>UpdateSubscriptionLocationRequest</tt>.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class UpdateSubscriptionLocationRequestTest {

	private UpdateSubscriptionLocationRequest updateSubscriptionLocationRequest;

	@Before
	public void setUp() {
		this.updateSubscriptionLocationRequest = new UpdateSubscriptionLocationRequest();
	}

	@Test
	public void testThatGetSubscriptionIdReturnsValueFromConstructor() {
		// Create mocks
		Long subscriptionId = Long.valueOf(12345L);
		Location newLocation = EasyMock.createMock(Location.class);

		// Create test entity
		this.updateSubscriptionLocationRequest = new UpdateSubscriptionLocationRequest(subscriptionId, newLocation);

		// Verify value
		Assert.assertEquals("GetSubscriptionId should return the subscriptionId that was set.", subscriptionId,
				this.updateSubscriptionLocationRequest.getSubscriptionId());
	}

	@Test
	public void testThatGetNewLocationReturnsValueFromConstructor() {
		// Create mocks
		Long subscriptionId = Long.valueOf(12345L);
		Location newLocation = EasyMock.createMock(Location.class);

		// Create test entity
		this.updateSubscriptionLocationRequest = new UpdateSubscriptionLocationRequest(subscriptionId, newLocation);

		// Verify value
		Assert.assertEquals("GetNewLocation should return the newLocation that was set.", newLocation,
				this.updateSubscriptionLocationRequest.getNewLocation());
	}

	@Test
	public void testThatGetSubscriptionIdReturnsTheSubscriptionIdThatWasSet() {
		// Create mock
		Long subscriptionId = Long.valueOf(12345L);

		// Set value
		this.updateSubscriptionLocationRequest.setSubscriptionId(subscriptionId);

		// Verify value
		Assert.assertEquals("GetSubscriptionId should return the subscriptionId that was set.", subscriptionId,
				this.updateSubscriptionLocationRequest.getSubscriptionId());
	}

	@Test
	public void testThatGetNewLocationReturnsTheNewLocationThatWasSet() {
		// Create mock
		Location newLocation = EasyMock.createMock(Location.class);

		// Set value
		this.updateSubscriptionLocationRequest.setNewLocation(newLocation);

		// Verify value
		Assert.assertEquals("GetNewLocation should return the newLocation that was set.", newLocation,
				this.updateSubscriptionLocationRequest.getNewLocation());
	}

	@Test
	public void testThatToStringContainsTheSubscriptionIdThatWasSet() {
		// Create mock
		Long subscriptionId = Long.valueOf(12345L);

		// Set value
		this.updateSubscriptionLocationRequest.setSubscriptionId(subscriptionId);

		// Verify value
		Assert.assertThat("ToString should contain the subscriptionId that was set.",
				this.updateSubscriptionLocationRequest.toString(),
				StringContains.containsString(String.valueOf(subscriptionId)));
	}

	@Test
	public void testThatToStringContainsTheNewLocationThatWasSet() {
		// Create mock
		Location newLocation = EasyMock.createMock(Location.class);

		// Set value
		this.updateSubscriptionLocationRequest.setNewLocation(newLocation);

		// Verify value
		Assert.assertThat("ToString should contain the newLocation that was set.",
				this.updateSubscriptionLocationRequest.toString(),
				StringContains.containsString(String.valueOf(newLocation)));
	}
}
