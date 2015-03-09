/**
 * Copyright (C) 2015 iSeek, Inc. 
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of iSeek, Inc.
 * You shall not disclose such confidential information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with 
 * iSeek, Inc.
 */
package us.iseek.services.persistence;

import us.iseek.model.gps.Location;
import us.iseek.model.topics.HashTag;
import us.iseek.model.topics.Subscription;
import us.iseek.model.user.User;

/**
 * Provides methods to persist user <tt>Subscription</tt>s.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public interface SubscriptionMapper {

	/**
	 * Inserts the subscription to the persistent store.
	 * 
	 * @param subscription
	 *            - The subscription to insert.
	 */
	public void insert(Subscription subscription);

	/**
	 * Retrieves the subscription referenced by the ID provided.
	 * 
	 * @param subscriptionId
	 *            - The ID of the subscription to retrieve.
	 * @return The subscription for the ID provided.
	 */
	public Subscription get(Long subscriptionId);

	/**
	 * Retrieves the subscription querying by the parameters provided.
	 * 
	 * @param user
	 *            - The user for which the subscriptions are being retrieved.
	 * @param hashTag
	 *            - The topic for which the subscriptions are being retrieved.
	 * @param location
	 *            - The location for which the subscriptions are being
	 *            retrieved.
	 * @return The intersection of all subscriptions for the user, hashTag, and
	 *         location.
	 */
	public Subscription search(User user, HashTag hashTag, Location location);

	/**
	 * Renews the last activity timestamp of the subscription, resulting in an
	 * extended <tt>subscription.timeToLive</tt>.
	 * 
	 * @param subscriptionId
	 *            - The ID of the subscription to renew.
	 */
	public void renew(Long subscriptionId);

	/**
	 * Updates the location for the subscription with the ID provided. <br>
	 * <br>
	 * <b>Note:</b> Updating the location of the subscription also results in
	 * the last activity timestamp being updated to the current system time.
	 * 
	 * @param subscriptionId
	 *            - The ID of the subscription to update.
	 */
	public void updateLocation(Long subscriptionId, Location location);

	/**
	 * Expires the subscription with the ID provided, so that
	 * <tt>subscription.timeToLive</tt> would return 0.
	 * 
	 * @param subscriptionId
	 *            - The ID of the subscription to update.
	 */
	public void expire(Long subscriptionId);
}
