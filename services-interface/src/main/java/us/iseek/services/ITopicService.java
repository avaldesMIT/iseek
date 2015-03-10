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

import java.util.List;

import us.iseek.model.exception.UnknownLocationException;
import us.iseek.model.gps.Location;
import us.iseek.model.topics.HashTag;
import us.iseek.model.topics.Subscription;
import us.iseek.model.user.User;

/**
 * Provides services to find topics being discussed in the system.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public interface ITopicService {

	/**
	 * Finds all the topics being discussed in a particular geographical area.
	 * 
	 * @param location
	 *            - The location in which the topics are being discussed.
	 * @return The list of topics being discussed.
	 */
	public List<HashTag> findTopics(Location location);

	/**
	 * Creates a new topic of discussion.
	 * 
	 * @param displayName
	 *            - The hashtag display name for the topic.
	 * @return A hash tag with a new ID if this is a new topic. If a topic
	 *         already exists, returns the existing topic hashtag.
	 */
	public HashTag createTopic(String displayName);

	/**
	 * Retrieves the subscription querying by the parameters provided.
	 * 
	 * @param user
	 *            - The ID for the user for which the subscriptions are being
	 *            retrieved.
	 * @param hashTag
	 *            - The ID for the topic for which the subscriptions are being
	 *            retrieved.
	 * @param location
	 *            - The location for which the subscriptions are being
	 *            retrieved. Note that topics will be retrieved for this
	 *            location and a radial area around this location.
	 * @return The intersection of all subscriptions for the user, hashTag, and
	 *         location provided.
	 */
	public List<Subscription> findSubscriptions(Long userId, Long topicId, Location location);

	/**
	 * Adds a user to a topic of conversation. Requires that the user referenced
	 * by the ID provided has a known location.
	 * 
	 * @param userId
	 *            - The ID of the user joining the topic.
	 * @param topic
	 *            - The topic being joined.
	 * @return The details of the user's subscription.
	 * @throws UnknownLocationException
	 *             - If the location of the user referenced by the ID provided
	 *             is now known.
	 */
	public Subscription subscribe(Long userId, HashTag topic) throws UnknownLocationException;

	/**
	 * Renew's the user subscription to the topic referenced by the subscription
	 * ID.
	 * 
	 * @param subscriptionId
	 *            - The ID of the subscription that references the topic for
	 *            which the user wishes to renew his/her subscription.
	 * @return the Subscription with updated time before expiry.
	 */
	public Subscription renewSubscription(Long subscriptionId);

	/**
	 * Updates the user subscription to the topic referenced by the subscription
	 * ID with the user's new location.
	 * 
	 * @param subscriptionId
	 *            - The ID of the subscription that references the topic for
	 *            which the user wishes to renew his/her subscription.
	 * @param newLocation
	 *            - The new location for the user.
	 * @return An updated list of users for the topic referenced by the
	 *         subscription at the new user's location.
	 */
	public List<User> updateSubscription(Long subscriptionId, Location newLocation);

	/**
	 * Unsubscribes the user from the topic referenced by the subscription
	 * provided.
	 * 
	 * @param subscriptionId
	 *            - The ID for the subscription referencing the topic from which
	 *            the user should be unsubscribed, and the user that is being
	 *            unsubscribed.
	 */
	public void unSubscribe(Long subscriptionId);

	/**
	 * Gets a list of users in the topic.
	 * 
	 * @param topic
	 *            - The topic of discussion
	 * @param location
	 *            - The location where the topic is being discussed
	 * @return a list of all the users participating in the topic at a
	 *         particular location.
	 */
	public List<User> getUsersInTopic(HashTag topic, Location location);
}
