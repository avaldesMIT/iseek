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

import us.iseek.model.user.Preferences;

/**
 * Tests the <tt>UpdateUserPreferencesRequest</tt>.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class UpdateUserPreferencesRequestTest {

	private UpdateUserPreferencesRequest updateUserPreferencesRequest;

	@Before
	public void setUp() {
		this.updateUserPreferencesRequest = new UpdateUserPreferencesRequest();
	}

	@Test
	public void testThatGetUserIdReturnsValueFromConstructor() {
		// Create mocks
		Long userId = Long.valueOf(12345L);
		Preferences preferences = EasyMock.createMock(Preferences.class);

		// Create test entity
		this.updateUserPreferencesRequest = new UpdateUserPreferencesRequest(userId, preferences);

		// Verify value
		Assert.assertEquals("GetUserId should return the userId that was set.", userId,
				this.updateUserPreferencesRequest.getUserId());
	}

	@Test
	public void testThatGetPreferencesReturnsValueFromConstructor() {
		// Create mocks
		Long userId = Long.valueOf(12345L);
		Preferences preferences = EasyMock.createMock(Preferences.class);

		// Create test entity
		this.updateUserPreferencesRequest = new UpdateUserPreferencesRequest(userId, preferences);

		// Verify value
		Assert.assertEquals("GetPreferences should return the preferences that was set.", preferences,
				this.updateUserPreferencesRequest.getPreferences());
	}

	@Test
	public void testThatGetUserIdReturnsTheUserIdThatWasSet() {
		// Create mock
		Long userId = Long.valueOf(12345L);

		// Set value
		this.updateUserPreferencesRequest.setUserId(userId);

		// Verify value
		Assert.assertEquals("GetUserId should return the userId that was set.", userId,
				this.updateUserPreferencesRequest.getUserId());
	}

	@Test
	public void testThatGetPreferencesReturnsThePreferencesThatWasSet() {
		// Create mock
		Preferences preferences = EasyMock.createMock(Preferences.class);

		// Set value
		this.updateUserPreferencesRequest.setPreferences(preferences);

		// Verify value
		Assert.assertEquals("GetPreferences should return the preferences that was set.", preferences,
				this.updateUserPreferencesRequest.getPreferences());
	}

	@Test
	public void testThatToStringContainsTheUserIdThatWasSet() {
		// Create mock
		Long userId = Long.valueOf(12345L);

		// Set value
		this.updateUserPreferencesRequest.setUserId(userId);

		// Verify value
		Assert.assertThat("ToString should contain the userId that was set.",
				this.updateUserPreferencesRequest.toString(), StringContains.containsString(String.valueOf(userId)));
	}

	@Test
	public void testThatToStringContainsThePreferencesThatWasSet() {
		// Create mock
		Preferences preferences = EasyMock.createMock(Preferences.class);

		// Set value
		this.updateUserPreferencesRequest.setPreferences(preferences);

		// Verify value
		Assert.assertThat("ToString should contain the preferences that was set.",
				this.updateUserPreferencesRequest.toString(),
				StringContains.containsString(String.valueOf(preferences)));
	}
}
