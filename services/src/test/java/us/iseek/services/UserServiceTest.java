/**
 * Copyright (C) 2015 iSeek, Inc. 
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of iSeek, Inc.
 * You shall not disclose such confidential information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with 
 * iSeek, Inc.
 */
package us.iseek.services;

import org.easymock.Capture;
import org.easymock.EasyMock;
import org.easymock.IAnswer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import us.iseek.model.gps.Location;
import us.iseek.model.user.Preferences;
import us.iseek.model.user.User;
import us.iseek.services.persistence.PreferencesMapper;
import us.iseek.services.persistence.UserMapper;

/**
 * Tests the <tt>UserService</tt>.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class UserServiceTest {

	private static final Long USER_ID = Long.valueOf(1234L);
	private static final Long FACEBOOK_PROFILE_ID = Long.valueOf(12934L);
	
	private static final String DEFAULT_SCREEN_NAME = "iSeek User";
	
	private UserService userService;

	private User user;
	private UserMapper userMapper;
	private PreferencesMapper preferencesMapper;

	@Before
	public void setUp() {
		// Create mocks
		this.user = EasyMock.createNiceMock(User.class);
		this.userMapper = EasyMock.createNiceMock(UserMapper.class);
		this.preferencesMapper = EasyMock.createNiceMock(PreferencesMapper.class);

		// Create entity to test
		this.userService = new UserService();
		this.userService.setUserMapper(this.userMapper);
		this.userService.setPreferencesMapper(this.preferencesMapper);
	}

	@Test
	public void testThatGetReturnsTheUserForTheIdProvided() {
		// Set mock expectations
		EasyMock.expect(this.userMapper.get(USER_ID)).andReturn(this.user).once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		User actualUser = this.userService.get(USER_ID);
		Assert.assertEquals("Get should return the user for the ID provided.", this.user, actualUser);
	}

	@Test
	public void testThatGetByFacebookProfileIdReturnsTheUserForTheFacebookProfileIdProvided() {
		// Set mock expectations
		EasyMock.expect(this.userMapper.getByFacebookProfileId(FACEBOOK_PROFILE_ID)).andReturn(this.user).once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		User actualUser = this.userService.getByFacebookProfileId(FACEBOOK_PROFILE_ID);
		Assert.assertEquals("Get should return the user for the Facebook profile ID provided.", this.user, actualUser);
	}

	@Test
	public void testThatCreateUserUpdatesTheLocationOfTheUserIfAUserWithTheFacebookProfileIdAlreadyExists() {
		// Create test data
		Location location = EasyMock.createNiceMock(Location.class);

		// Set mock expectations
		EasyMock.expect(this.user.getId()).andReturn(USER_ID).anyTimes();
		EasyMock.expect(this.userMapper.getByFacebookProfileId(FACEBOOK_PROFILE_ID)).andReturn(this.user).once();
		this.userMapper.updateLocation(USER_ID, location);
		EasyMock.expectLastCall().once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		this.userService.createUser(FACEBOOK_PROFILE_ID, location);
		EasyMock.verify(this.userMapper);
	}

	@Test
	public void testThatCreateUserReturnsUserWithUpdatedLocationIfAUserWithTheFacebookProfileIdAlreadyExists() {
		// Create test data
		Location location = EasyMock.createNiceMock(Location.class);

		// Set mock expectations
		EasyMock.expect(this.user.getId()).andReturn(USER_ID).anyTimes();
		EasyMock.expect(this.userMapper.getByFacebookProfileId(FACEBOOK_PROFILE_ID)).andReturn(this.user).once();
		EasyMock.expect(this.userMapper.get(USER_ID)).andReturn(this.user).once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		User actualUser = this.userService.createUser(FACEBOOK_PROFILE_ID, location);
		Assert.assertEquals(
				"Create user should return user with updated location if a user with the Facebook profile ID already exists.",
				this.user, actualUser);
	}

	@Test
	public void testThatCreateUserInsertsUserToTheDatabaseWithDefaultScreenNameIfAUserWithTheFacebookProfileIdDoesntExist() {
		// Create test data
		Location location = EasyMock.createNiceMock(Location.class);

		// Set mock expectations
		Capture<User> captureUser = EasyMock.newCapture();
		this.userMapper.insert(EasyMock.capture(captureUser));
		EasyMock.expectLastCall().once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		this.userService.createUser(FACEBOOK_PROFILE_ID, location);
		String actualScreenName = captureUser.getValue().getScreenName();
		Assert.assertEquals("Create user should create user with default screen name.",
				DEFAULT_SCREEN_NAME, actualScreenName);
	}
	
	@Test
	public void testThatCreateUserInsertsUserToTheDatabaseWithCorrectFacebookProfileIdIfAUserWithTheFacebookProfileIdDoesntExist() {
		// Create test data
		Location location = EasyMock.createNiceMock(Location.class);

		// Set mock expectations
		Capture<User> captureUser = EasyMock.newCapture();
		this.userMapper.insert(EasyMock.capture(captureUser));
		EasyMock.expectLastCall().once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		this.userService.createUser(FACEBOOK_PROFILE_ID, location);
		Long actualFacebookProfileId = captureUser.getValue().getFacebookProfileId();
		Assert.assertEquals("Create user should create user with Facbook profile ID from parameter.",
				FACEBOOK_PROFILE_ID, actualFacebookProfileId);
	}

	@Test
	public void testThatCreateUserInsertsUserToTheDatabaseWithCorrectLocationIfAUserWithTheFacebookProfileIdDoesntExist() {
		// Create test data
		Location location = EasyMock.createNiceMock(Location.class);

		// Set mock expectations
		Capture<User> captureUser = EasyMock.newCapture();
		this.userMapper.insert(EasyMock.capture(captureUser));
		EasyMock.expectLastCall().once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		this.userService.createUser(FACEBOOK_PROFILE_ID, location);
		Location actualLocation = captureUser.getValue().getLastLocation();
		Assert.assertEquals("Create user should create user with location from parameter.", location, actualLocation);
	}

	@Test
	public void testThatCreateUserInsertsDefaultPreferencesForTheUserToTheDatabaseIfAUserWithTheFacebookProfileIdDoesntExist() {
		// Create test data
		Location location = EasyMock.createNiceMock(Location.class);

		// Set mock expectations
		EasyMock.expect(this.user.getId()).andReturn(USER_ID).anyTimes();

		this.userMapper.insert(EasyMock.anyObject(User.class));
		EasyMock.expectLastCall().andAnswer(new IAnswer<Void>() {

			/**
			 * {@inheritDoc}
			 */
			public Void answer() throws Throwable {
				// Set user's ID after insert
				((User) EasyMock.getCurrentArguments()[0]).setId(USER_ID);
				return null;
			}
		}).once();

		Capture<Preferences> capturePreferences = EasyMock.newCapture();
		this.preferencesMapper.insert(EasyMock.eq(USER_ID), EasyMock.capture(capturePreferences));
		EasyMock.expectLastCall().once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		this.userService.createUser(FACEBOOK_PROFILE_ID, location);
		Preferences actualPreferences = capturePreferences.getValue();
		Assert.assertEquals("Create user should set default broadcast location preferences.",
				Preferences.DEFAULT_PREFERENCES.isBroadcastLocation(), actualPreferences.isBroadcastLocation());
		Assert.assertEquals("Create user should set default show profile picture preferences.",
				Preferences.DEFAULT_PREFERENCES.isShowProfilePicture(), actualPreferences.isShowProfilePicture());
	}

	@Test
	public void testThatCreateUserReturnsNewlyCreatedUserIfAUserWithTheFacebookProfileIdDoesntExist() {
		// Create test data
		Location location = EasyMock.createNiceMock(Location.class);

		// Set mock expectations
		EasyMock.expect(this.user.getId()).andReturn(USER_ID).anyTimes();

		this.userMapper.insert(EasyMock.anyObject(User.class));
		EasyMock.expectLastCall().andAnswer(new IAnswer<Void>() {

			/**
			 * {@inheritDoc}
			 */
			public Void answer() throws Throwable {
				// Set user's ID after insert
				((User) EasyMock.getCurrentArguments()[0]).setId(USER_ID);
				return null;
			}
		});

		EasyMock.expect(this.userMapper.get(USER_ID)).andReturn(this.user).once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		User actualUser = this.userService.createUser(FACEBOOK_PROFILE_ID, location);
		Assert.assertEquals("Create user should return newly created user.", this.user, actualUser);
	}

	@Test
	public void testThatUpdatePreferencesUpdatesPreferencesForTheUserIdProvided() {
		// Set test data
		Preferences preferences = EasyMock.createNiceMock(Preferences.class);

		// Set mock expectations
		Capture<Preferences> preferencesCapture = EasyMock.newCapture();
		this.preferencesMapper.update(EasyMock.eq(USER_ID), EasyMock.capture(preferencesCapture));
		EasyMock.expectLastCall().once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		this.userService.updatePreferences(USER_ID, preferences);
		Assert.assertEquals("Updated preferences should be the preferences in the parameter", preferences,
				preferencesCapture.getValue());
		EasyMock.verify(this.preferencesMapper);
	}

	@Test
	public void testThatUpdatePreferencesReturnsTheUpdatedUserWithTheNewPreferences() {
		// Set test data
		Preferences preferences = EasyMock.createNiceMock(Preferences.class);

		// Set mock expectations
		EasyMock.expect(this.userMapper.get(USER_ID)).andReturn(this.user).once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		User actualUser = this.userService.updatePreferences(USER_ID, preferences);
		Assert.assertEquals("UpdatePreferences should return the updated user", this.user, actualUser);
	}

	@Test
	public void testThatUpdateLocationUpdatesTheLocationForTheUserIdProvided() {
		// Set test data
		Location location = EasyMock.createNiceMock(Location.class);

		// Set mock expectations
		Capture<Location> locationCapture = EasyMock.newCapture();
		this.userMapper.updateLocation(EasyMock.eq(USER_ID), EasyMock.capture(locationCapture));
		EasyMock.expectLastCall().once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		this.userService.updateLocation(USER_ID, location);
		Assert.assertEquals("Updated location should be the location in the parameter", location,
				locationCapture.getValue());
		EasyMock.verify(this.userMapper);
	}

	@Test
	public void testThatUpdateLocationReturnsTheUpdatedUserWtihTheNewLocation() {
		// Set test data
		Location location = EasyMock.createNiceMock(Location.class);

		// Set mock expectations
		EasyMock.expect(this.userMapper.get(USER_ID)).andReturn(this.user).once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		User actualUser = this.userService.updateLocation(USER_ID, location);
		Assert.assertEquals("UpdatePreferences should return the updated user", this.user, actualUser);
	}

	@Test
	public void testThatUpdateScreenNameUpdatesTheScreenNameForTheUserIdProvided() {
		// Set test data
		String screenName = "SCREEN_NAME";

		// Set mock expectations
		Capture<String> screenNameCapture = EasyMock.newCapture();
		this.userMapper.updateScreenName(EasyMock.eq(USER_ID), EasyMock.capture(screenNameCapture));
		EasyMock.expectLastCall().once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		this.userService.updateScreenName(USER_ID, screenName);
		Assert.assertEquals("Updated screen name should be the screen name in the parameter", screenName,
				screenNameCapture.getValue());
		EasyMock.verify(this.userMapper);
	}

	@Test
	public void testThatUpdateScreenNameReturnsTheUpdatedUserWithTheNewScreenName() {
		// Set test data
		String screenName = "SCREEN_NAME";

		// Set mock expectations
		EasyMock.expect(this.userMapper.get(USER_ID)).andReturn(this.user).once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		User actualUser = this.userService.updateScreenName(USER_ID, screenName);
		Assert.assertEquals("UpdatePreferences should return the updated user", this.user, actualUser);
	}

	@Test
	public void testThatGetUserMapperReturnsTheUserMapperThatWasSet() {
		// Create mock
		UserMapper userMapper = EasyMock.createNiceMock(UserMapper.class);

		// Set value
		this.userService.setUserMapper(userMapper);

		// Set up mock framework
		this.readyMockFramework();

		// Verify value
		Assert.assertEquals("GetUserMapper should return the userMapper that was set.", userMapper,
				this.userService.getUserMapper());
	}

	@Test
	public void testThatGetPreferencesMapperReturnsThePreferencesMapperThatWasSet() {
		// Create mock
		PreferencesMapper preferencesMapper = EasyMock.createNiceMock(PreferencesMapper.class);

		// Set value
		this.userService.setPreferencesMapper(preferencesMapper);

		// Set up mock framework
		this.readyMockFramework();

		// Verify value
		Assert.assertEquals("GetPreferencesMapper should return the preferencesMapper that was set.",
				preferencesMapper, this.userService.getPreferencesMapper());
	}

	/**
	 * Readies the mock framework before each test
	 */
	private void readyMockFramework() {
		EasyMock.replay(this.user);
		EasyMock.replay(this.userMapper);
		EasyMock.replay(this.preferencesMapper);
	}
}
