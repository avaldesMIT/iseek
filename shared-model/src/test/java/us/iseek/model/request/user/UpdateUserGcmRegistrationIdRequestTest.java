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

import org.hamcrest.core.StringContains;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the <tt>UpdateUserGcmRegistrationIdRequest</tt>.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class UpdateUserGcmRegistrationIdRequestTest {

	private UpdateUserGcmRegistrationIdRequest updateUserGcmRegistrationIdRequest;

	@Before
	public void setUp() {
		this.updateUserGcmRegistrationIdRequest = new UpdateUserGcmRegistrationIdRequest();
	}

	@Test
	public void testThatGetUserIdReturnsValueFromConstructor() {
		// Create mocks
		Long userId = Long.valueOf(12345L);
		String gcmRegistrationId = "GMC_REGISTRATION_ID";

		// Create test entity
		this.updateUserGcmRegistrationIdRequest = new UpdateUserGcmRegistrationIdRequest(userId, gcmRegistrationId);

		// Verify value
		Assert.assertEquals("GetUserId should return the userId that was set.", userId,
				this.updateUserGcmRegistrationIdRequest.getUserId());
	}

	@Test
	public void testThatGetGcmRegistrationIdReturnsValueFromConstructor() {
		// Create mocks
		Long userId = Long.valueOf(12345L);
		String gcmRegistrationId = "GMC_REGISTRATION_ID";

		// Create test entity
		this.updateUserGcmRegistrationIdRequest = new UpdateUserGcmRegistrationIdRequest(userId, gcmRegistrationId);

		// Verify value
		Assert.assertEquals("GetGcmRegistrationId should return the gcmRegistrationId that was set.",
				gcmRegistrationId, this.updateUserGcmRegistrationIdRequest.getGcmRegistrationId());
	}

	@Test
	public void testThatGetUserIdReturnsTheUserIdThatWasSet() {
		// Create mock
		Long userId = Long.valueOf(12345L);

		// Set value
		this.updateUserGcmRegistrationIdRequest.setUserId(userId);

		// Verify value
		Assert.assertEquals("GetUserId should return the userId that was set.", userId,
				this.updateUserGcmRegistrationIdRequest.getUserId());
	}

	@Test
	public void testThatGetGcmRegistrationIdReturnsTheGcmRegistrationIdThatWasSet() {
		// Create mock
		String gcmRegistrationId = "GMC_REGISTRATION_ID";

		// Set value
		this.updateUserGcmRegistrationIdRequest.setGcmRegistrationId(gcmRegistrationId);

		// Verify value
		Assert.assertEquals("GetGcmRegistrationId should return the gcmRegistrationId that was set.",
				gcmRegistrationId, this.updateUserGcmRegistrationIdRequest.getGcmRegistrationId());
	}

	@Test
	public void testThatToStringContainsTheUserIdThatWasSet() {
		// Create mock
		Long userId = Long.valueOf(12345L);

		// Set value
		this.updateUserGcmRegistrationIdRequest.setUserId(userId);

		// Verify value
		Assert.assertThat("ToString should contain the userId that was set.",
				this.updateUserGcmRegistrationIdRequest.toString(),
				StringContains.containsString(String.valueOf(userId)));
	}

	@Test
	public void testThatToStringContainsTheGcmRegistrationIdThatWasSet() {
		// Create mock
		String gcmRegistrationId = "GMC_REGISTRATION_ID";

		// Set value
		this.updateUserGcmRegistrationIdRequest.setGcmRegistrationId(gcmRegistrationId);

		// Verify value
		Assert.assertThat("ToString should contain the gcmRegistrationId that was set.",
				this.updateUserGcmRegistrationIdRequest.toString(),
				StringContains.containsString(String.valueOf(gcmRegistrationId)));
	}
}
