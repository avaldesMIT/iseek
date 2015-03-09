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

import org.hamcrest.core.StringContains;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the <tt>Preferences</tt>.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class PreferencesTest {

	private static final Boolean BROADCAST_LOCATION = Boolean.TRUE;
	private static final Boolean SHOW_PROFILE_PICTURE = Boolean.FALSE;

	private Preferences preferences;

	@Before
	public void setUp() {
		this.preferences = new Preferences();
	}

	@Test
	public void testThatGetBroadcastLocationReturnsFalseIfTheBroadcastLocationThatWasSetToFalse() {
		// Create mock
		Boolean broadcastLocation = Boolean.FALSE;

		// Set value
		this.preferences.setBroadcastLocation(broadcastLocation);

		// Verify value
		Assert.assertEquals("GetBroadcastLocation should return the broadcastLocation that was set.",
				broadcastLocation, this.preferences.isBroadcastLocation());
	}

	@Test
	public void testThatGetBroadcastLocationReturnsTrueIfTheBroadcastLocationThatWasSetToTrue() {
		// Create mock
		Boolean broadcastLocation = Boolean.TRUE;

		// Set value
		this.preferences.setBroadcastLocation(broadcastLocation);

		// Verify value
		Assert.assertEquals("GetBroadcastLocation should return the broadcastLocation that was set.",
				broadcastLocation, this.preferences.isBroadcastLocation());
	}

	@Test
	public void testThatGetShowProfilePictureReturnsFalseIfTheShowProfilePictureThatWasSetToFalse() {
		// Create mock
		Boolean showProfilePicture = Boolean.FALSE;

		// Set value
		this.preferences.setShowProfilePicture(showProfilePicture);

		// Verify value
		Assert.assertEquals("GetShowProfilePicture should return the showProfilePicture that was set.",
				showProfilePicture, this.preferences.isShowProfilePicture());
	}

	@Test
	public void testThatGetShowProfilePictureReturnsTrueIfTheShowProfilePictureThatWasSetToTrue() {
		// Create mock
		Boolean showProfilePicture = Boolean.TRUE;

		// Set value
		this.preferences.setShowProfilePicture(showProfilePicture);

		// Verify value
		Assert.assertEquals("GetShowProfilePicture should return the showProfilePicture that was set.",
				showProfilePicture, this.preferences.isShowProfilePicture());
	}

	@Test
	public void testThatEqualsReturnsTrueIfObjectsAreIdentical() {
		Assert.assertTrue("Equals should be reflexive.", this.preferences.equals(this.preferences));
	}

	@Test
	public void testThatEqualsReturnsTrueIfObjectsHaveSameAttributes() {
		this.preferences = new Preferences(Boolean.TRUE, Boolean.FALSE);
		Preferences other = new Preferences(Boolean.TRUE, Boolean.FALSE);
		Assert.assertTrue("Equals should return true if two preferencess have the same attributes.",
				this.preferences.equals(other));
	}

	@Test
	public void testThatEqualsReturnsTrueIfObjectsHaveEqualAttributes() {
		this.preferences = new Preferences(new Boolean(true), new Boolean(true));
		Preferences other = new Preferences(new Boolean(true), new Boolean(true));
		Assert.assertTrue("Equals should return true if two preferencess have the same attributes.",
				this.preferences.equals(other));
	}

	@Test
	public void testThatEqualsIsSymmetric() {
		this.preferences = new Preferences(Boolean.TRUE, Boolean.TRUE);
		Preferences preferencesWithSameAttributes = new Preferences(Boolean.TRUE, Boolean.TRUE);
		Assert.assertEquals("Equals should be symmetric.", this.preferences.equals(preferencesWithSameAttributes),
				preferencesWithSameAttributes.equals(this.preferences));

		Preferences preferencesWithDifferentAttributes = new Preferences(Boolean.FALSE, Boolean.FALSE);
		Assert.assertEquals("Equals should be symmetric.", this.preferences.equals(preferencesWithDifferentAttributes),
				preferencesWithDifferentAttributes.equals(this.preferences));
	}

	@Test
	public void testThatEqualsIsConsistent() {
		this.preferences = new Preferences(Boolean.TRUE, Boolean.TRUE);
		Preferences preferencesWithSameAttributes = new Preferences(Boolean.TRUE, Boolean.TRUE);
		Assert.assertEquals("Equals should be consistent.", this.preferences.equals(preferencesWithSameAttributes),
				preferencesWithSameAttributes.equals(this.preferences));

		Preferences preferencesWithDifferentAttributes = new Preferences(Boolean.FALSE, Boolean.FALSE);
		Assert.assertEquals("Equals should be consistent.",
				this.preferences.equals(preferencesWithDifferentAttributes),
				preferencesWithDifferentAttributes.equals(this.preferences));
	}

	@Test
	public void testThatEqualsIsTransitive() {
		this.preferences = new Preferences(Boolean.TRUE, Boolean.TRUE);
		Preferences firstPreferences = new Preferences(Boolean.TRUE, Boolean.TRUE);
		Preferences secondPreferences = new Preferences(Boolean.TRUE, Boolean.TRUE);

		Assert.assertEquals("Equals should be transitive.", this.preferences.equals(firstPreferences)
				&& firstPreferences.equals(secondPreferences), this.preferences.equals(secondPreferences));
	}

	@Test
	public void testThatEqualsReturnsFalseIfOtherObjectIsNull() {
		Assert.assertFalse("Equals should return false if other object is null.", this.preferences.equals(null));
	}

	@Test
	public void testThatEqualsReturnsFalseIfOtherObjectIsNotAPreferences() {
		Assert.assertFalse("Equals should return null if other object is not a preferences.",
				this.preferences.equals(new Object()));
	}

	@Test
	public void testThatEqualsReturnsTrueIfBothPreferencessHaveNullBroadcastLocation() {
		this.preferences = new Preferences(null, SHOW_PROFILE_PICTURE);
		Preferences other = new Preferences(null, SHOW_PROFILE_PICTURE);
		Assert.assertTrue("Equals should return true if two preferencess have null BroadcastLocation.",
				this.preferences.equals(other));
	}

	@Test
	public void testThatEqualsReturnsTrueIfBothPreferencessHaveNullShowProfilePicture() {
		this.preferences = new Preferences(BROADCAST_LOCATION, null);
		Preferences other = new Preferences(BROADCAST_LOCATION, null);
		Assert.assertTrue("Equals should return true if two preferencess have null ShowProfilePicture.",
				this.preferences.equals(other));
	}

	@Test
	public void testThatEqualsReturnsFalseIfBroadcastLocationIsDifferent() {
		this.preferences = new Preferences(BROADCAST_LOCATION, SHOW_PROFILE_PICTURE);
		Preferences other = new Preferences(Boolean.FALSE, SHOW_PROFILE_PICTURE);
		Assert.assertFalse("Equals should return false if two preferencess have different BroadcastLocation.",
				this.preferences.equals(other));
	}

	@Test
	public void testThatEqualsReturnsFalseIfShowProfilePictureIsDifferent() {
		this.preferences = new Preferences(BROADCAST_LOCATION, SHOW_PROFILE_PICTURE);
		Preferences other = new Preferences(BROADCAST_LOCATION, Boolean.TRUE);
		Assert.assertFalse("Equals should return false if two preferencess have different ShowProfilePicture.",
				this.preferences.equals(other));
	}

	@Test
	public void testThatEqualsReturnsFalseIfBroadcastLocationIsNullAndOtherIsNot() {
		this.preferences = new Preferences(null, SHOW_PROFILE_PICTURE);
		Preferences other = new Preferences(BROADCAST_LOCATION, SHOW_PROFILE_PICTURE);
		Assert.assertFalse("Equals should return false if two preferencess have different BroadcastLocation.",
				this.preferences.equals(other));
	}

	@Test
	public void testThatEqualsReturnsFalseIfShowProfilePictureIsNullAndOtherIsNot() {
		this.preferences = new Preferences(BROADCAST_LOCATION, null);
		Preferences other = new Preferences(BROADCAST_LOCATION, SHOW_PROFILE_PICTURE);
		Assert.assertFalse("Equals should return false if two preferencess have different ShowProfilePicture.",
				this.preferences.equals(other));
	}

	@Test
	public void testThatEqualsReturnsFalseIfBroadcastLocationIsNotNullAndOtherIs() {
		this.preferences = new Preferences(BROADCAST_LOCATION, SHOW_PROFILE_PICTURE);
		Preferences other = new Preferences(null, SHOW_PROFILE_PICTURE);
		Assert.assertFalse("Equals should return false if two preferencess have different BroadcastLocation.",
				this.preferences.equals(other));
	}

	@Test
	public void testThatEqualsReturnsFalseIfShowProfilePictureIsNotNullAndOtherIs() {
		this.preferences = new Preferences(BROADCAST_LOCATION, SHOW_PROFILE_PICTURE);
		Preferences other = new Preferences(BROADCAST_LOCATION, null);
		Assert.assertFalse("Equals should return false if two preferencess have different ShowProfilePicture.",
				this.preferences.equals(other));
	}

	@Test
	public void testThatHashCodeDoesNotThrowNullPointerExeptionIfBroadcastLocationIsNull() {
		this.preferences = new Preferences(null, SHOW_PROFILE_PICTURE);
		Assert.assertNotNull("HashCode should not throw null pointer exception if BroadcastLocation is null.",
				this.preferences.hashCode());
	}

	@Test
	public void testThatHashCodeDoesNotThrowNullPointerExeptionIfShowProfilePictureIsNull() {
		this.preferences = new Preferences(BROADCAST_LOCATION, null);
		Assert.assertNotNull("HashCode should not throw null pointer exception if ShowProfilePicture is null.",
				this.preferences.hashCode());
	}

	@Test
	public void testThatHashCodeIsEqualIfObjectsAreEqual() {
		this.preferences = new Preferences(BROADCAST_LOCATION, SHOW_PROFILE_PICTURE);
		Preferences preferencesWithSameAttributes = new Preferences(BROADCAST_LOCATION, SHOW_PROFILE_PICTURE);
		Assert.assertEquals("HashCode should be equal if objects are equal.",
				this.preferences.equals(preferencesWithSameAttributes),
				this.preferences.hashCode() == preferencesWithSameAttributes.hashCode());
	}

	@Test
	public void testThatToStringContainsTheBroadcastLocationThatWasSet() {
		// Create mock
		Boolean broadcastLocation = Boolean.TRUE;

		// Set value
		this.preferences.setBroadcastLocation(broadcastLocation);

		// Verify value
		Assert.assertThat("ToString should contain the broadcastLocation that was set.", this.preferences.toString(),
				StringContains.containsString(String.valueOf(broadcastLocation)));
	}

	@Test
	public void testThatToStringContainsTheShowProfilePictureThatWasSet() {
		// Create mock
		Boolean showProfilePicture = Boolean.TRUE;

		// Set value
		this.preferences.setShowProfilePicture(showProfilePicture);

		// Verify value
		Assert.assertThat("ToString should contain the showProfilePicture that was set.", this.preferences.toString(),
				StringContains.containsString(String.valueOf(showProfilePicture)));
	}
}
