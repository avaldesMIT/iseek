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

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import us.iseek.model.exception.UnknownLocationException;
import us.iseek.model.gps.Location;
import us.iseek.model.request.topic.CreateSubscriptionRequest;
import us.iseek.model.request.topic.FindSubscriptionsRequest;
import us.iseek.model.request.topic.GetUsersInTopicRequest;
import us.iseek.model.request.topic.UpdateSubscriptionLocationRequest;
import us.iseek.model.topics.HashTag;
import us.iseek.model.topics.Subscription;
import us.iseek.model.user.User;
import us.iseek.services.ITopicService;
import us.iseek.services.web.utils.WebUtils;

/**
 * Provides REST services to manage topics being discussed in the system.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
@RestController
@RequestMapping("/topic")
public class TopicController {

	private final Log log = LogFactory.getLog(TopicController.class);

	@Autowired
	private ITopicService topicService;

	/**
	 * Finds all the topics being discussed in a particular geographical area.
	 * 
	 * @param location
	 *            - The location in which the topics are being discussed.
	 * @return The list of topics being discussed.
	 */
	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public List<HashTag> findTopics(@RequestParam Double latitude, @RequestParam Double longitude) {
		Location location = new Location(latitude, longitude);
		log.debug("type=RECEIVED_REST_FIND_TOPICS_REQUEST, " + "desc=Received REST request to find topics, param="
				+ location);

		return this.topicService.findTopics(location);
	}

	/**
	 * Creates a new topic of discussion.
	 * 
	 * @param displayName
	 *            - The hashtag display name for the topic.
	 * @return A hash tag with a new ID if this is a new topic. If a topic
	 *         already exists, returns the existing topic hashtag.
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public HashTag createTopic(@RequestBody String displayName) {
		log.debug("type=RECEIVED_REST_CREATE_TOPIC_REQUEST, " + "desc=Received REST request to create topic, param="
				+ displayName);

		return this.topicService.createTopic(WebUtils.getSafeString(displayName));
	}

	/**
	 * Adds a user to a topic of conversation. Requires that the user referenced
	 * by the ID provided has a known location.
	 * 
	 * @param createSubscriptionRequest
	 *            - The request to create a new subscription, including the ID
	 *            of the user joining the topic and the topic being joined.
	 * @return The details of the user's subscription.
	 * @throws UnknownLocationException
	 *             - If the location of the user referenced by the ID provided
	 *             is now known.
	 */
	@RequestMapping(value = "/subscription/create", method = RequestMethod.POST)
	public Subscription subscribe(@RequestBody CreateSubscriptionRequest createSubscriptionRequest)
			throws UnknownLocationException {

		log.debug("type=RECEIVED_REST_SUBSCRIBE_TO_TOPIC_REQUEST, "
				+ "desc=Received REST request to subscribe to topic, param=" + createSubscriptionRequest);
		return this.topicService.subscribe(createSubscriptionRequest.getUserId(), createSubscriptionRequest.getTopic());
	}

	/**
	 * Renew's the user subscription to the topic referenced by the subscription
	 * ID.
	 * 
	 * @param subscriptionId
	 *            - The ID of the subscription that references the topic for
	 *            which the user wishes to renew his/her subscription.
	 * @return the Subscription with updated time before expiry.
	 */
	@RequestMapping(value = "/subscription/renew", method = RequestMethod.POST)
	public Subscription renewSubscription(@RequestBody Long subscriptionId) {
		log.debug("type=RECEIVED_REST_RENEW_SUBSCRIPTION_REQUEST, "
				+ "desc=Received REST request to renew subscription, param=" + subscriptionId);
		return this.topicService.renewSubscription(subscriptionId);
	}

	/**
	 * Updates the user subscription to the topic referenced by the subscription
	 * ID with the user's new location.
	 * 
	 * @param updateSubscriptionLocationRequest
	 *            - The request to update the subscription's location, including
	 *            the ID of the subscription that references the topic for which
	 *            the user wishes to renew his/her subscription and the new
	 *            location for the user.
	 * @return An updated list of users for the topic referenced by the
	 *         subscription at the new user's location.
	 */
	@RequestMapping(value = "/subscription/update", method = RequestMethod.POST)
	public List<User> updateSubscription(
			@RequestBody UpdateSubscriptionLocationRequest updateSubscriptionLocationRequest) {

		log.debug("type=RECEIVED_REST_UPDATE_SUBSCRIPTION_LOC_REQUEST, "
				+ "desc=Received REST request to update subscription location, param="
				+ updateSubscriptionLocationRequest);
		return this.topicService.updateSubscription(updateSubscriptionLocationRequest.getSubscriptionId(),
				updateSubscriptionLocationRequest.getNewLocation());
	}

	/**
	 * Unsubscribes the user from the topic referenced by the subscription
	 * provided.
	 * 
	 * @param subscriptionId
	 *            - The ID for the subscription referencing the topic from which
	 *            the user should be unsubscribed, and the user that is being
	 *            unsubscribed.
	 */
	@RequestMapping(value = "/subscription/unsubcribe", method = RequestMethod.POST)
	public void unSubscribe(@RequestBody Long subscriptionId) {
		log.debug("type=RECEIVED_REST_UNSUBSCRIBE_FROM_TOPIC_REQUEST, "
				+ "desc=Received REST request to unsubscribe from topic, param=" + subscriptionId);
		this.topicService.unSubscribe(subscriptionId);
	}

	/**
	 * Gets a list of users in the topic.
	 * 
	 * @param getUsersInTopicRequest
	 *            - The request to get the list of users, including the topic of
	 *            discussion and the location where the topic is being discussed
	 * @return a list of all the users participating in the topic at a
	 *         particular location.
	 */
	@RequestMapping(value = "/getUsers", method = RequestMethod.GET)
	public List<User> getUsersInTopic(@RequestBody GetUsersInTopicRequest getUsersInTopicRequest) {

		log.debug("type=RECEIVED_REST_GET_USERS_IN_TOPIC_REQUEST, "
				+ "desc=Received REST request to get all users in topic, param=" + getUsersInTopicRequest);
		return this.topicService.getUsersInTopic(getUsersInTopicRequest.getTopic(),
				getUsersInTopicRequest.getLocation());
	}

	/**
	 * Retrieves the subscription querying by the parameters provided.
	 * 
	 * @param findSubscriptionsRequest
	 *            - The request to find user subscriptions to topic, including
	 *            the ID for the user for which the subscriptions are being, the
	 *            ID for the topic for which the subscriptions are being, and
	 *            the location for which the subscriptions are being retrieved.
	 *            Note that topics will be retrieved for this location and a
	 *            radial area around this location.
	 * @return The intersection of all subscriptions for the user, hashTag, and
	 *         location provided.
	 */
	@RequestMapping(value = "/subscription/find", method = RequestMethod.GET)
	public List<Subscription> findSubscriptions(@RequestBody FindSubscriptionsRequest findSubscriptionsRequest) {
		log.debug("type=RECEIVED_REST_FIND_SUBSCRIPTIONS_REQUEST, "
				+ "desc=Received REST request to fins subscriptions, param=" + findSubscriptionsRequest);
		return this.topicService.findSubscriptions(findSubscriptionsRequest.getUserId(),
				findSubscriptionsRequest.getTopicId(), findSubscriptionsRequest.getLocation());
	}

	/**
	 * @return the topicService
	 */
	public ITopicService getTopicService() {
		return this.topicService;
	}

	/**
	 * @param topicService
	 *            - The topicService to set
	 */
	public void setTopicService(ITopicService topicService) {
		this.topicService = topicService;
	}
}
