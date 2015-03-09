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
 * Tests the <tt>UpdateUserScreenNameRequest</tt>.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class UpdateUserScreenNameRequestTest {

	private UpdateUserScreenNameRequest updateUserScreenNameRequest;

	@Before
	public void setUp() {
		this.updateUserScreenNameRequest = new UpdateUserScreenNameRequest();
	}

	@Test
	public void testThatGetUserIdReturnsValueFromConstructor() {
		// Create mocks
		Long userId = Long.valueOf(12345L);
		String screenName = "SCREEN_NAME";

		// Create test entity
		this.updateUserScreenNameRequest = new UpdateUserScreenNameRequest(userId, screenName);

		// Verify value
		Assert.assertEquals("GetUserId should return the userId that was set.", userId,
				this.updateUserScreenNameRequest.getUserId());
	}

	@Test
	public void testThatGetScreenNameReturnsValueFromConstructor() {
		// Create mocks
		Long userId = Long.valueOf(12345L);
		String screenName = "SCREEN_NAME";

		// Create test entity
		this.updateUserScreenNameRequest = new UpdateUserScreenNameRequest(userId, screenName);

		// Verify value
		Assert.assertEquals("GetScreenName should return the screenName that was set.", screenName,
				this.updateUserScreenNameRequest.getScreenName());
	}

	@Test
	public void testThatGetUserIdReturnsTheUserIdThatWasSet() {
		// Create mock
		Long userId = Long.valueOf(12345L);

		// Set value
		this.updateUserScreenNameRequest.setUserId(userId);

		// Verify value
		Assert.assertEquals("GetUserId should return the userId that was set.", userId,
				this.updateUserScreenNameRequest.getUserId());
	}

	@Test
	public void testThatGetScreenNameReturnsTheScreenNameThatWasSet() {
		// Create mock
		String screenName = "SCREEN_NAME";

		// Set value
		this.updateUserScreenNameRequest.setScreenName(screenName);

		// Verify value
		Assert.assertEquals("GetScreenName should return the screenName that was set.", screenName,
				this.updateUserScreenNameRequest.getScreenName());
	}

	@Test
	public void testThatToStringContainsTheUserIdThatWasSet() {
		// Create mock
		Long userId = Long.valueOf(12345L);

		// Set value
		this.updateUserScreenNameRequest.setUserId(userId);

		// Verify value
		Assert.assertThat("ToString should contain the userId that was set.",
				this.updateUserScreenNameRequest.toString(), StringContains.containsString(String.valueOf(userId)));
	}

	@Test
	public void testThatToStringContainsTheScreenNameThatWasSet() {
		// Create mock
		String screenName = "SCREEN_NAME";

		// Set value
		this.updateUserScreenNameRequest.setScreenName(screenName);

		// Verify value
		Assert.assertThat("ToString should contain the screenName that was set.",
				this.updateUserScreenNameRequest.toString(), StringContains.containsString(String.valueOf(screenName)));
	}
}
