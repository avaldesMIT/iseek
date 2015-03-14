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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import us.iseek.model.request.user.CreateUserRequest;
import us.iseek.model.request.user.UpdateUserGcmRegistrationIdRequest;
import us.iseek.model.request.user.UpdateUserLocationRequest;
import us.iseek.model.request.user.UpdateUserPreferencesRequest;
import us.iseek.model.request.user.UpdateUserScreenNameRequest;
import us.iseek.model.user.User;
import us.iseek.services.IUserService;

/**
 * Provides REST services to manage users preferences and lifecycle.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

	private final Log log = LogFactory.getLog(UserController.class);

	@Autowired
	private IUserService userService;

	/**
	 * Retrieves the user corresponding to the user ID provided.
	 * 
	 * @param userId
	 *            - The user's ID
	 * @return The user for the user ID provided.
	 */
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public User get(@RequestParam Long userId) {
		log.debug("type=RECEIVED_REST_GET_USER_REQUEST, " + "desc=Received REST request to get user, param=" + userId);
		return this.userService.get(userId);
	}

	/**
	 * Retrieves the user corresponding to the user's Facebook profile ID
	 * provided.
	 * 
	 * @param facebookProfileId
	 *            - The user's Facebook profile ID
	 * @return The user for the Facebook profile ID provided.
	 */
	@RequestMapping(value = "/getByFacebookProfileId", method = RequestMethod.GET)
	public User getByFacebookProfileId(@RequestParam Long facebookProfileId) {
		log.debug("type=RECEIVED_REST_GET_USER_BY_FB_PROFILE_REQUEST, "
				+ "desc=Received REST request to get user by Facebook profile, param=" + facebookProfileId);
		return this.userService.getByFacebookProfileId(facebookProfileId);
	}

	/**
	 * Creates a new user based on the parameters in the request providfed. Some
	 * of the fields for the user will be populated with default values. <br>
	 * <br>
	 * <b>Note:</b> if the user with the Facebook profile ID provided in the
	 * request already exists in the system, this method will retrieve the
	 * existing user instead of creating a new one. However, if the location
	 * provided in the request is not null, the user's last known location will
	 * be updated with the new location provided.
	 * 
	 * @param createUserRequest
	 *            - The request to create the user containing the Facebook
	 *            profile ID for the user to create and an optional current
	 *            location of the user being created.
	 * @return The user that was just created or the existing user if a user
	 *         with the Facebook profile ID provided already existed in the
	 *         system.
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public User createUser(@RequestBody CreateUserRequest createUserRequest) {
		log.debug("type=RECEIVED_REST_CREATE_USER_REQUEST, " + "desc=Received REST request to create user, param="
				+ createUserRequest);
		return this.userService.createUser(createUserRequest.getFacebookProfileId(), createUserRequest.getLocation());
	}

	/**
	 * Updates the user's preferences.
	 * 
	 * @param updateUserPreferencesRequest
	 *            - The request to update the user's preferences containing ID
	 *            of the user for whom the preferences are being set and the
	 *            user's new preferences.
	 * @return The user with the updated preferences
	 */
	@RequestMapping(value = "/updatePreferences", method = RequestMethod.POST)
	public User updatePreferences(@RequestBody UpdateUserPreferencesRequest updateUserPreferencesRequest) {

		log.debug("type=RECEIVED_REST_UPDATE_USER_PREF_REQUEST, "
				+ "desc=Received REST request to update user's preferences, param=" + updateUserPreferencesRequest);
		return this.userService.updatePreferences(updateUserPreferencesRequest.getUserId(),
				updateUserPreferencesRequest.getPreferences());
	}

	/**
	 * Updates the user's location.
	 * 
	 * @param updateUserLocationRequest
	 *            - The request to update the user's location containing the ID
	 *            of the user for whom the location is being updated and the
	 *            user's new location.
	 * @return The user with the updated location
	 */
	@RequestMapping(value = "/updateLocation", method = RequestMethod.POST)
	public User updateLocation(@RequestBody UpdateUserLocationRequest updateUserLocationRequest) {

		log.debug("type=RECEIVED_REST_UPDATE_USER_LOC_REQUEST, "
				+ "desc=Received REST request to update user's location, param=" + updateUserLocationRequest);
		return this.userService.updateLocation(updateUserLocationRequest.getUserId(),
				updateUserLocationRequest.getLocation());
	}

	/**
	 * Updates the user's screen name.
	 * 
	 * @param updateUserScreenNameRequest
	 *            - The request to update the user's screen name containing the
	 *            ID of the user for whom the screen name is being updated and
	 *            the user's new screen name.
	 * @return The user with the updated screen name.
	 */
	@RequestMapping(value = "/updateScreenName", method = RequestMethod.POST)
	public User updateScreenName(@RequestBody UpdateUserScreenNameRequest updateUserScreenNameRequest) {

		log.debug("type=RECEIVED_REST_UPDATE_USER_NAME_REQUEST, "
				+ "desc=Received REST request to update user's display name, param=" + updateUserScreenNameRequest);
		return this.userService.updateScreenName(updateUserScreenNameRequest.getUserId(),
				updateUserScreenNameRequest.getScreenName());
	}

	/**
	 * Updates the user's Google Cloud Messaging registration ID.
	 * 
	 * @param updateUserGcmRegistrationRequest
	 *            - The request to update the user's GCM registration containing
	 *            the ID of the user for whom the screen name is being updated
	 *            and the user's new GCM registration ID.
	 * @return The user with the updated GCM registration ID.
	 */
	@RequestMapping(value = "/updateGcmRegistrationId", method = RequestMethod.POST)
	public User updateGcmRegistrationId(@RequestBody UpdateUserGcmRegistrationIdRequest updateUserGcmRegistrationRequest) {

		log.debug("type=RECEIVED_REST_UPDATE_USER_GCM_REGISTRATION_REQUEST, "
				+ "desc=Received REST request to update user's GCM registration ID, param="
				+ updateUserGcmRegistrationRequest);
		return this.userService.updateGcmRegistrationId(updateUserGcmRegistrationRequest.getUserId(),
				updateUserGcmRegistrationRequest.getGcmRegistrationId());
	}

	/**
	 * @return the userService
	 */
	public IUserService getUserService() {
		return this.userService;
	}

	/**
	 * @param userService
	 *            - The userService to set
	 */
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
}
