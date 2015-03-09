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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import us.iseek.model.converters.measurement.MeasurementConverterFactory;
import us.iseek.model.enums.MeasureUnit;
import us.iseek.model.exception.ConversionException;
import us.iseek.model.exception.UnknownLocationException;
import us.iseek.model.gps.Location;
import us.iseek.model.topics.HashTag;
import us.iseek.model.topics.Subscription;
import us.iseek.model.user.User;
import us.iseek.services.persistence.HashTagMapper;
import us.iseek.services.persistence.SubscriptionMapper;
import us.iseek.services.persistence.UserMapper;

/**
 * Provides services to find topics being discussed in the system.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class TopicService implements ITopicService {

	private final Log log = LogFactory.getLog(TopicService.class);
	private static final Double DEFAULT_RADIUS = Double.valueOf(5);

	private UserMapper userMapper;
	private HashTagMapper hashTagMapper;
	private SubscriptionMapper subscriptionMapper;
	private MeasurementConverterFactory measurementConverterFactory;

	/**
	 * {@inheritDoc}
	 */
	public List<HashTag> findTopics(Location location) {
		location.setMeasurementConverterFactory(this.measurementConverterFactory);
		try {
			return this.hashTagMapper.getByArea(location.getRadialArea(DEFAULT_RADIUS, MeasureUnit.MILES));
		} catch (ConversionException e) {
			// Log error and return an empty list
			log.error("There was an error computing default area to find topics.", e);
			return new ArrayList<HashTag>();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public HashTag createTopic(String displayName) {
		// Create new hash tag
		HashTag hashTag = new HashTag();
		hashTag.setDisplayName(displayName);

		// Insert hash tag
		this.hashTagMapper.insert(hashTag);

		// Retrieve and return hash tag
		return hashTag;
	}

	/**
	 * {@inheritDoc}
	 */
	public Subscription subscribe(Long userId, HashTag topic) throws UnknownLocationException {

		// Retrieve user
		User user = this.userMapper.get(userId);

		// Create new subscription
		Subscription subscription = new Subscription();
		subscription.setUser(user);
		subscription.setTopic(topic);
		subscription.setLocation(user.getLastLocation());
		this.subscriptionMapper.insert(subscription);

		// Retrieve newly created subscription
		return this.subscriptionMapper.search(user, topic, user.getLastLocation());
	}

	/**
	 * {@inheritDoc}
	 */
	public Subscription renewSubscription(Long subscriptionId) {
		// Renew subscription
		this.subscriptionMapper.renew(subscriptionId);

		// Retrieve new subscription
		return this.subscriptionMapper.get(subscriptionId);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<User> updateSubscription(Long subscriptionId, Location newLocation) {

		// Update subscription details
		this.subscriptionMapper.updateLocation(subscriptionId, newLocation);

		// Retrieve new subscription
		Subscription subscription = this.subscriptionMapper.get(subscriptionId);

		// Retrieve users subscribed at this location
		return this.getUsersInTopic(subscription.getTopic(), newLocation);
	}

	/**
	 * {@inheritDoc}
	 */
	public void unSubscribe(Long subscriptionId) {
		// Expire subscription
		this.subscriptionMapper.expire(subscriptionId);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<User> getUsersInTopic(HashTag topic, Location location) {
		// Retrieve users subscribed to the topic at the location provided
		return this.userMapper.getUsers(topic, location);
	}

	/**
	 * @return the userMapper
	 */
	public UserMapper getUserMapper() {
		return this.userMapper;
	}

	/**
	 * @param userMapper
	 *            - The userMapper to set
	 */
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	/**
	 * @return the hashTagMapper
	 */
	public HashTagMapper getHashTagMapper() {
		return this.hashTagMapper;
	}

	/**
	 * @param hashTagMapper
	 *            - The hashTagMapper to set
	 */
	public void setHashTagMapper(HashTagMapper hashTagMapper) {
		this.hashTagMapper = hashTagMapper;
	}

	/**
	 * @return the subscriptionMapper
	 */
	public SubscriptionMapper getSubscriptionMapper() {
		return this.subscriptionMapper;
	}

	/**
	 * @param subscriptionMapper
	 *            - The subscriptionMapper to set
	 */
	public void setSubscriptionMapper(SubscriptionMapper subscriptionMapper) {
		this.subscriptionMapper = subscriptionMapper;
	}

	/**
	 * @return the measurementConverterFactory
	 */
	public MeasurementConverterFactory getMeasurementConverterFactory() {
		return this.measurementConverterFactory;
	}

	/**
	 * @param measurementConverterFactory
	 *            - The measurementConverterFactory to set
	 */
	public void setMeasurementConverterFactory(MeasurementConverterFactory measurementConverterFactory) {
		this.measurementConverterFactory = measurementConverterFactory;
	}
}
