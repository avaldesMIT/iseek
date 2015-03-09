/**
 * Copyright (C) 2015 iSeek, Inc.
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of iSeek, Inc.
 * You shall not disclose such confidential information and shall use it only
 * in accordance with the terms of the license agreement you entered into with
 * iSeek, Inc.
 */
package us.iseek.model.request.user;

import org.easymock.EasyMock;
import org.hamcrest.core.StringContains;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import us.iseek.model.gps.Location;

/**
 * Tests the <tt>UpdateUserLocationRequest</tt>.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class UpdateUserLocationRequestTest {

	private UpdateUserLocationRequest updateUserLocationRequest;

	@Before
	public void setUp() {
		this.updateUserLocationRequest = new UpdateUserLocationRequest();
	}

	@Test
	public void testThatGetUserIdReturnsValueFromConstructor() {
		// Create mocks
		Long userId = Long.valueOf(12345L);
		Location location = EasyMock.createMock(Location.class);

		// Create test entity
		this.updateUserLocationRequest = new UpdateUserLocationRequest(userId, location);

		// Verify value
		Assert.assertEquals("GetUserId should return the userId that was set.", userId,
				this.updateUserLocationRequest.getUserId());
	}

	@Test
	public void testThatGetLocationReturnsValueFromConstructor() {
		// Create mocks
		Long userId = Long.valueOf(12345L);
		Location location = EasyMock.createMock(Location.class);

		// Create test entity
		this.updateUserLocationRequest = new UpdateUserLocationRequest(userId, location);

		// Verify value
		Assert.assertEquals("GetLocation should return the location that was set.", location,
				this.updateUserLocationRequest.getLocation());
	}

	@Test
	public void testThatGetUserIdReturnsTheUserIdThatWasSet() {
		// Create mock
		Long userId = Long.valueOf(12345L);

		// Set value
		this.updateUserLocationRequest.setUserId(userId);

		// Verify value
		Assert.assertEquals("GetUserId should return the userId that was set.", userId,
				this.updateUserLocationRequest.getUserId());
	}

	@Test
	public void testThatGetLocationReturnsTheLocationThatWasSet() {
		// Create mock
		Location location = EasyMock.createMock(Location.class);

		// Set value
		this.updateUserLocationRequest.setLocation(location);

		// Verify value
		Assert.assertEquals("GetLocation should return the location that was set.", location,
				this.updateUserLocationRequest.getLocation());
	}

	@Test
	public void testThatToStringContainsTheUserIdThatWasSet() {
		// Create mock
		Long userId = Long.valueOf(12345L);

		// Set value
		this.updateUserLocationRequest.setUserId(userId);

		// Verify value
		Assert.assertThat("ToString should contain the userId that was set.",
				this.updateUserLocationRequest.toString(), StringContains.containsString(String.valueOf(userId)));
	}

	@Test
	public void testThatToStringContainsTheLocationThatWasSet() {
		// Create mock
		Location location = EasyMock.createMock(Location.class);

		// Set value
		this.updateUserLocationRequest.setLocation(location);

		// Verify value
		Assert.assertThat("ToString should contain the location that was set.",
				this.updateUserLocationRequest.toString(), StringContains.containsString(String.valueOf(location)));
	}
}
