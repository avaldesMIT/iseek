/**
 * Copyright (C) 2015 iSeek, Inc.
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of iSeek, Inc.
 * You shall not disclose such confidential information and shall use it only
 * in accordance with the terms of the license agreement you entered into with
 * iSeek, Inc.
 */
package us.iseek.model.user;

import java.sql.Timestamp;

import org.easymock.EasyMock;
import org.hamcrest.core.StringContains;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import us.iseek.model.gps.Location;

/**
 * Tests the <tt>User</tt>.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class UserTest {

	private User user;

	@Before
	public void setUp() {
		this.user = new User();
	}

	@Test
	public void testThatGetIdReturnsTheIdThatWasSet() {
		// Create mock
		Long id = Long.valueOf(12345L);

		// Set value
		this.user.setId(id);

		// Verify value
		Assert.assertEquals("GetId should return the id that was set.", id, this.user.getId());
	}

	@Test
	public void testThatGetScreenNameReturnsTheScreenNameThatWasSet() {
		// Create mock
		String screenName = "SCREEN_NAME";

		// Set value
		this.user.setScreenName(screenName);

		// Verify value
		Assert.assertEquals("GetScreenName should return the screenName that was set.", screenName,
				this.user.getScreenName());
	}

	@Test
	public void testThatGetFacebookProfileIdReturnsTheFacebookProfileIdThatWasSet() {
		// Create mock
		Long facebookProfileId = Long.valueOf(12345L);

		// Set value
		this.user.setFacebookProfileId(facebookProfileId);

		// Verify value
		Assert.assertEquals("GetFacebookProfileId should return the facebookProfileId that was set.",
				facebookProfileId, this.user.getFacebookProfileId());
	}

	@Test
	public void testThatGetLastLocationReturnsTheLastLocationThatWasSet() {
		// Create mock
		Location lastLocation = EasyMock.createMock(Location.class);

		// Set value
		this.user.setLastLocation(lastLocation);

		// Verify value
		Assert.assertEquals("GetLastLocation should return the lastLocation that was set.", lastLocation,
				this.user.getLastLocation());
	}

	@Test
	public void testThatGetLastActivityReturnsTheLastActivityThatWasSet() {
		// Create mock
		Timestamp lastActivity = EasyMock.createMock(Timestamp.class);

		// Set value
		this.user.setLastActivity(lastActivity);

		// Verify value
		Assert.assertEquals("GetLastActivity should return the lastActivity that was set.", lastActivity,
				this.user.getLastActivity());
	}

	@Test
	public void testThatGetPreferencesReturnsThePreferencesThatWasSet() {
		// Create mock
		Preferences preferences = EasyMock.createMock(Preferences.class);

		// Set value
		this.user.setPreferences(preferences);

		// Verify value
		Assert.assertEquals("GetPreferences should return the preferences that was set.", preferences,
				this.user.getPreferences());
	}

	@Test
	public void testThatToStringContainsTheIdThatWasSet() {
		// Create mock
		Long id = Long.valueOf(12345L);

		// Set value
		this.user.setId(id);

		// Verify value
		Assert.assertThat("ToString should contain the id that was set.", this.user.toString(),
				StringContains.containsString(String.valueOf(id)));
	}

	@Test
	public void testThatToStringContainsTheScreenNameThatWasSet() {
		// Create mock
		String screenName = "SCREEN_NAME";

		// Set value
		this.user.setScreenName(screenName);

		// Verify value
		Assert.assertThat("ToString should contain the screenName that was set.", this.user.toString(),
				StringContains.containsString(String.valueOf(screenName)));
	}

	@Test
	public void testThatToStringContainsTheFacebookProfileIdThatWasSet() {
		// Create mock
		Long facebookProfileId = Long.valueOf(12345L);

		// Set value
		this.user.setFacebookProfileId(facebookProfileId);

		// Verify value
		Assert.assertThat("ToString should contain the facebookProfileId that was set.", this.user.toString(),
				StringContains.containsString(String.valueOf(facebookProfileId)));
	}

	@Test
	public void testThatToStringContainsTheLastLocationThatWasSet() {
		// Create mock
		Location lastLocation = EasyMock.createMock(Location.class);

		// Set value
		this.user.setLastLocation(lastLocation);

		// Verify value
		Assert.assertThat("ToString should contain the lastLocation that was set.", this.user.toString(),
				StringContains.containsString(String.valueOf(lastLocation)));
	}

	@Test
	public void testThatToStringContainsTheLastActivityThatWasSet() {
		// Create mock
		Timestamp lastActivity = EasyMock.createMock(Timestamp.class);

		// Set value
		this.user.setLastActivity(lastActivity);

		// Verify value
		Assert.assertThat("ToString should contain the lastActivity that was set.", this.user.toString(),
				StringContains.containsString(String.valueOf(lastActivity)));
	}

	@Test
	public void testThatToStringContainsThePreferencesThatWasSet() {
		// Create mock
		Preferences preferences = EasyMock.createMock(Preferences.class);

		// Set value
		this.user.setPreferences(preferences);

		// Verify value
		Assert.assertThat("ToString should contain the preferences that was set.", this.user.toString(),
				StringContains.containsString(String.valueOf(preferences)));
	}
}
