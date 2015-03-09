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
 * Tests the <tt>CreateUserRequest</tt>.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class CreateUserRequestTest {

	private CreateUserRequest createUserRequest;

	@Before
	public void setUp() {
		this.createUserRequest = new CreateUserRequest();
	}

	@Test
	public void testThatGetFacebookProfileIdReturnsValueFromConstructor() {
		// Create mocks
		Long facebookProfileId = Long.valueOf(12345L);
		Location location = EasyMock.createMock(Location.class);

		// Create test entity
		this.createUserRequest = new CreateUserRequest(facebookProfileId, location);

		// Verify value
		Assert.assertEquals("GetFacebookProfileId should return the facebookProfileId that was set.",
				facebookProfileId, this.createUserRequest.getFacebookProfileId());
	}

	@Test
	public void testThatGetLocationReturnsValueFromConstructor() {
		// Create mocks
		Long facebookProfileId = Long.valueOf(12345L);
		Location location = EasyMock.createMock(Location.class);

		// Create test entity
		this.createUserRequest = new CreateUserRequest(facebookProfileId, location);

		// Verify value
		Assert.assertEquals("GetLocation should return the location that was set.", location,
				this.createUserRequest.getLocation());
	}

	@Test
	public void testThatGetFacebookProfileIdReturnsTheFacebookProfileIdThatWasSet() {
		// Create mock
		Long facebookProfileId = Long.valueOf(12345L);

		// Set value
		this.createUserRequest.setFacebookProfileId(facebookProfileId);

		// Verify value
		Assert.assertEquals("GetFacebookProfileId should return the facebookProfileId that was set.",
				facebookProfileId, this.createUserRequest.getFacebookProfileId());
	}

	@Test
	public void testThatGetLocationReturnsTheLocationThatWasSet() {
		// Create mock
		Location location = EasyMock.createMock(Location.class);

		// Set value
		this.createUserRequest.setLocation(location);

		// Verify value
		Assert.assertEquals("GetLocation should return the location that was set.", location,
				this.createUserRequest.getLocation());
	}

	@Test
	public void testThatToStringContainsTheFacebookProfileIdThatWasSet() {
		// Create mock
		Long facebookProfileId = Long.valueOf(12345L);

		// Set value
		this.createUserRequest.setFacebookProfileId(facebookProfileId);

		// Verify value
		Assert.assertThat("ToString should contain the facebookProfileId that was set.",
				this.createUserRequest.toString(), StringContains.containsString(String.valueOf(facebookProfileId)));
	}

	@Test
	public void testThatToStringContainsTheLocationThatWasSet() {
		// Create mock
		Location location = EasyMock.createMock(Location.class);

		// Set value
		this.createUserRequest.setLocation(location);

		// Verify value
		Assert.assertThat("ToString should contain the location that was set.", this.createUserRequest.toString(),
				StringContains.containsString(String.valueOf(location)));
	}
}
