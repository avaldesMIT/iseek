/**
 * Copyright (C) 2015 iSeek, Inc. 
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of iSeek, Inc.
 * You shall not disclose such confidential information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with 
 * iSeek, Inc.
 */
package us.iseek.services.web.controller;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import us.iseek.model.gps.Location;
import us.iseek.model.request.user.CreateUserRequest;
import us.iseek.model.request.user.UpdateUserLocationRequest;
import us.iseek.model.request.user.UpdateUserPreferencesRequest;
import us.iseek.model.request.user.UpdateUserScreenNameRequest;
import us.iseek.model.user.Preferences;
import us.iseek.model.user.User;
import us.iseek.services.IUserService;

/**
 * Tests the <tt>UserController</tt>.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class UserControllerTest {

	private static final Long USER_ID = Long.valueOf(5812L);
	private static final Long FACEBOOK_PROFILE_ID = Long.valueOf(87123L);

	private UserController userController;

	private IUserService userService;

	private User user;

	@Before
	public void setUp() {
		// Create mocks
		this.user = EasyMock.createNiceMock(User.class);
		this.userService = EasyMock.createMock(IUserService.class);

		// Create test entity
		this.userController = new UserController();
		this.userController.setUserService(this.userService);
	}

	@After
	public void tearDown() {
		// Verify delegate was called
		EasyMock.verify(this.userService);
	}

	@Test
	public void testThatGetDelegatesCallToService() {
		// Set expectations
		EasyMock.expect(this.userService.get(USER_ID)).andReturn(this.user).anyTimes();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		User actualUser = this.userController.get(USER_ID);
		Assert.assertEquals("Get should return value returned by delegate.", this.user, actualUser);
	}

	@Test
	public void testThatGetByFacebookProfileIdDelegatesCallToService() {
		// Set expectations
		EasyMock.expect(this.userService.getByFacebookProfileId(FACEBOOK_PROFILE_ID)).andReturn(this.user).anyTimes();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		User actualUser = this.userController.getByFacebookProfileId(FACEBOOK_PROFILE_ID);
		Assert.assertEquals("GetByFacebookProfileId should return value returned by delegate.", this.user, actualUser);
	}

	@Test
	public void testThatCreateUserDelegatesCallToService() {
		// Create test data
		Location location = EasyMock.createNiceMock(Location.class);

		// Set expectations
		EasyMock.expect(this.userService.createUser(FACEBOOK_PROFILE_ID, location)).andReturn(this.user).once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		User actualUser = this.userController.createUser(new CreateUserRequest(FACEBOOK_PROFILE_ID, location));
		Assert.assertEquals("CreateUser should return value returned by delegate.", this.user, actualUser);
	}

	@Test
	public void testThatUpdatePreferencesDelegatesCallToService() {
		// Create test data
		Preferences preferences = EasyMock.createNiceMock(Preferences.class);

		// Set expectations
		EasyMock.expect(this.userService.updatePreferences(USER_ID, preferences)).andReturn(this.user).once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		User actualUser = this.userController.updatePreferences(new UpdateUserPreferencesRequest(USER_ID, preferences));
		Assert.assertEquals("UpdatePreferences should return value returned by delegate.", this.user, actualUser);
	}

	@Test
	public void testThatUpdateLocationDelegatesCallToService() {
		// Create test data
		Location location = EasyMock.createNiceMock(Location.class);

		// Set expectations
		EasyMock.expect(this.userService.updateLocation(USER_ID, location)).andReturn(this.user).once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		User actualUser = this.userController.updateLocation(new UpdateUserLocationRequest(USER_ID, location));
		Assert.assertEquals("UpdateLocation should return value returned by delegate.", this.user, actualUser);
	}

	@Test
	public void testThatUpdateScreenNameDelegatesCallToService() {
		// Create test data
		String screenName = "SCREEN_NAME";

		// Set expectations
		EasyMock.expect(this.userService.updateScreenName(USER_ID, screenName)).andReturn(this.user).once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		User actualUser = this.userController.updateScreenName(new UpdateUserScreenNameRequest(USER_ID, screenName));
		Assert.assertEquals("UpdateScreenName should return value returned by delegate.", this.user, actualUser);
	}

	@Test
	public void testThatGetUserServiceReturnsTheUserServiceThatWasSet() {
		// Create mock
		IUserService userService = EasyMock.createNiceMock(IUserService.class);

		// Set value
		this.userController.setUserService(userService);

		// Set up mock framework
		this.readyMockFramework();

		// Verify value
		Assert.assertEquals("GetUserService should return the userService that was set.", userService,
				this.userController.getUserService());
	}

	/**
	 * Readies the mock framework before each test
	 */
	private void readyMockFramework() {
		EasyMock.replay(this.userService);
	}
}
