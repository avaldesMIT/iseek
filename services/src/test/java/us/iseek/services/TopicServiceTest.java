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

import org.easymock.Capture;
import org.easymock.EasyMock;
import org.easymock.IAnswer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import us.iseek.model.converters.measurement.MeasurementConverterFactory;
import us.iseek.model.enums.MeasureUnit;
import us.iseek.model.exception.ConversionException;
import us.iseek.model.exception.UnknownLocationException;
import us.iseek.model.gps.Area;
import us.iseek.model.gps.Location;
import us.iseek.model.topics.HashTag;
import us.iseek.model.topics.Subscription;
import us.iseek.model.user.User;
import us.iseek.services.persistence.HashTagMapper;
import us.iseek.services.persistence.SubscriptionMapper;
import us.iseek.services.persistence.UserMapper;

/**
 * Tests the <tt>TopicService</tt>.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class TopicServiceTest {

	private static final Long HASH_TAG_ID = Long.valueOf(15123L);

	private TopicService topicService;

	private UserMapper userMapper;
	private HashTagMapper hashTagMapper;
	private SubscriptionMapper subscriptionMapper;

	@Before
	public void setUp() {
		// Create mock entities
		this.userMapper = EasyMock.createNiceMock(UserMapper.class);
		this.hashTagMapper = EasyMock.createNiceMock(HashTagMapper.class);
		this.subscriptionMapper = EasyMock.createNiceMock(SubscriptionMapper.class);

		// Create entity to test
		this.topicService = new TopicService();
		this.topicService.setUserMapper(this.userMapper);
		this.topicService.setHashTagMapper(this.hashTagMapper);
		this.topicService.setSubscriptionMapper(this.subscriptionMapper);
	}

	@Test
	public void testThatFindTopicsGetsTopicsFiveMilesAroundLocation() throws ConversionException {
		// Create test data
		Area area = EasyMock.createNiceMock(Area.class);
		Location location = EasyMock.createNiceMock(Location.class);
		EasyMock.expect(location.getRadialArea(Double.valueOf(5d), MeasureUnit.MILES)).andReturn(area).anyTimes();
		EasyMock.replay(location);

		List<HashTag> topics = new ArrayList<HashTag>();
		topics.add(EasyMock.createNiceMock(HashTag.class));

		// Set mock expectations
		EasyMock.expect(this.hashTagMapper.getByArea(area)).andReturn(topics).once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		List<HashTag> actualTopics = this.topicService.findTopics(location);
		Assert.assertEquals("Returned topics should match the topics returned by the database", topics, actualTopics);
	}

	@Test
	public void testThatFindTopicsReturnsEmptyListIfThereIsAProblemWithTheMeasurementConversion()
			throws ConversionException {

		// Create test data
		Location location = EasyMock.createNiceMock(Location.class);

		// Set mock expectations
		EasyMock.expect(location.getRadialArea(EasyMock.anyDouble(), EasyMock.anyObject(MeasureUnit.class)))
				.andThrow(new ConversionException("TEST")).anyTimes();
		EasyMock.replay(location);

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		List<HashTag> actualTopics = this.topicService.findTopics(location);
		Assert.assertEquals("Returned topics should be empty if thre is a conversion exception",
				new ArrayList<HashTag>(), actualTopics);
	}

	@Test
	public void testThatCreateTopicInsertsTopicToTheDatabase() {
		// Create test data
		String displayName = "DISPLAY_NAME";

		// Set mock expectations
		Capture<HashTag> captureTopic = EasyMock.newCapture();
		this.hashTagMapper.insert(EasyMock.capture(captureTopic));
		EasyMock.expectLastCall().once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		this.topicService.createTopic(displayName);
		Assert.assertNotNull("CreateTopic should insert topic to database", captureTopic.getValue());
		Assert.assertEquals("CreateTopic should insert topic with display name to database", displayName, captureTopic
				.getValue().getDisplayName());
	}

	@Test
	public void testThatCreateTopicReturnsHashTagWithId() {
		// Create test data
		String displayName = "DISPLAY_NAME";

		// Set mock expectations
		this.hashTagMapper.insert(EasyMock.anyObject(HashTag.class));
		EasyMock.expectLastCall().andAnswer(new IAnswer<Void>() {

			/**
			 * {@inheritDoc}
			 */
			public Void answer() throws Throwable {
				// Set hash tag's ID after insert
				((HashTag) EasyMock.getCurrentArguments()[0]).setId(HASH_TAG_ID);
				return null;
			}
		}).once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		HashTag newTopic = this.topicService.createTopic(displayName);
		Assert.assertNotNull("CreateTopic should return newly created topic", newTopic);
		Assert.assertEquals("New topic should have the display name from argument", displayName,
				newTopic.getDisplayName());
		Assert.assertEquals("New topic should have ID provided from insert", HASH_TAG_ID, newTopic.getId());
	}

	@Test
	public void testThatSubscribeCreatesSubscriptionWithUserForUserId() throws UnknownLocationException {
		// Create test data
		Long userId = Long.valueOf(5123L);
		HashTag topic = EasyMock.createNiceMock(HashTag.class);

		User user = EasyMock.createNiceMock(User.class);
		EasyMock.replay(user);

		// Create mock expectations
		EasyMock.expect(this.userMapper.get(userId)).andReturn(user).once();

		Capture<Subscription> subcriptionCapture = EasyMock.newCapture();
		this.subscriptionMapper.insert(EasyMock.capture(subcriptionCapture));
		EasyMock.expectLastCall().once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		this.topicService.subscribe(userId, topic);
		Subscription subscription = subcriptionCapture.getValue();
		Assert.assertNotNull("Subscribe should insert non-null subscription to database.", subscription);
		Assert.assertEquals("Subscribe should create subscription with user for user ID provided", user,
				subscription.getUser());
	}

	@Test
	public void testThatSubscribeCreatesSubscriptionWithTopicProvided() throws UnknownLocationException {
		// Create test data
		Long userId = Long.valueOf(5123L);
		HashTag topic = EasyMock.createNiceMock(HashTag.class);

		User user = EasyMock.createNiceMock(User.class);
		EasyMock.replay(user);

		// Create mock expectations
		EasyMock.expect(this.userMapper.get(userId)).andReturn(user).once();

		Capture<Subscription> subcriptionCapture = EasyMock.newCapture();
		this.subscriptionMapper.insert(EasyMock.capture(subcriptionCapture));
		EasyMock.expectLastCall().once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		this.topicService.subscribe(userId, topic);
		Subscription subscription = subcriptionCapture.getValue();
		Assert.assertNotNull("Subscribe should insert non-null subscription to database.", subscription);
		Assert.assertEquals("Subscribe should create subscription with topic provided", topic, subscription.getTopic());
	}

	@Test
	public void testThatSubscribeCreatesSubscriptionWithLastUserLocation() throws UnknownLocationException {
		// Create test data
		Long userId = Long.valueOf(5123L);
		HashTag topic = EasyMock.createNiceMock(HashTag.class);
		Location location = EasyMock.createNiceMock(Location.class);

		User user = EasyMock.createNiceMock(User.class);
		EasyMock.expect(user.getLastLocation()).andReturn(location).anyTimes();
		EasyMock.replay(user);

		// Create mock expectations
		EasyMock.expect(this.userMapper.get(userId)).andReturn(user).once();

		Capture<Subscription> subcriptionCapture = EasyMock.newCapture();
		this.subscriptionMapper.insert(EasyMock.capture(subcriptionCapture));
		EasyMock.expectLastCall().once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		this.topicService.subscribe(userId, topic);
		Subscription subscription = subcriptionCapture.getValue();
		Assert.assertNotNull("Subscribe should insert non-null subscription to database.", subscription);
		Assert.assertEquals("Subscribe should create subscription with user's location for user ID provided", location,
				subscription.getLocation());
	}

	@Test
	public void testThatRenewSubscriptionRenewsTheSubscription() {
		// Create test data
		Long subscriptionId = Long.valueOf(59123L);

		// Set mock expectations
		Capture<Long> captureSubscriptionId = EasyMock.newCapture();
		this.subscriptionMapper.renew(EasyMock.capture(captureSubscriptionId));
		EasyMock.expectLastCall().once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		this.topicService.renewSubscription(subscriptionId);
		Assert.assertEquals("RenewSubscription should renew the subscription for the ID provided.", subscriptionId,
				captureSubscriptionId.getValue());
		EasyMock.verify(this.subscriptionMapper);
	}

	@Test
	public void testThatRenewSubscriptionReturnsTheRenewedSubscription() {
		// Create test data
		Long subscriptionId = Long.valueOf(59123L);
		Subscription subscription = EasyMock.createNiceMock(Subscription.class);

		// Set mock expectations
		EasyMock.expect(this.subscriptionMapper.get(subscriptionId)).andReturn(subscription).once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		Subscription actualSubscription = this.topicService.renewSubscription(subscriptionId);
		Assert.assertEquals("Renew subscription should return the renewed subscription.", subscription,
				actualSubscription);
	}

	@Test
	public void testThatUpdateSubscriptionUpdatesTheSubscriptionsLocation() {
		// Create test data
		Long subscriptionId = Long.valueOf(59123L);
		Location location = EasyMock.createNiceMock(Location.class);

		Subscription subscription = EasyMock.createNiceMock(Subscription.class);
		EasyMock.replay(subscription);

		// Set mock expectations
		Capture<Location> captureLocation = EasyMock.newCapture();
		this.subscriptionMapper.updateLocation(EasyMock.eq(subscriptionId), EasyMock.capture(captureLocation));
		EasyMock.expectLastCall().once();

		EasyMock.expect(this.subscriptionMapper.get(subscriptionId)).andReturn(subscription).once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		this.topicService.updateSubscription(subscriptionId, location);
		Assert.assertEquals("UpdateSubscription should update the subscription with the location provided.", location,
				captureLocation.getValue());
		EasyMock.verify(this.subscriptionMapper);
	}

	@Test
	public void testThatUpdateSubscriptonReturnsAllUsersForTheSubscriptionAtTheNewLocation() {
		// Create test data
		Long subscriptionId = Long.valueOf(59123L);

		HashTag topic = EasyMock.createNiceMock(HashTag.class);
		Location location = EasyMock.createNiceMock(Location.class);

		Subscription subscription = EasyMock.createNiceMock(Subscription.class);
		EasyMock.expect(subscription.getTopic()).andReturn(topic).anyTimes();
		EasyMock.replay(subscription);

		List<User> users = new ArrayList<User>();
		users.add(EasyMock.createNiceMock(User.class));

		// Set mock expectations
		EasyMock.expect(this.subscriptionMapper.get(subscriptionId)).andReturn(subscription).once();
		EasyMock.expect(this.userMapper.getUsers(topic, location)).andReturn(users).once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		List<User> actualUsers = this.topicService.updateSubscription(subscriptionId, location);
		Assert.assertEquals("UpdateSubscription should return the list of users for the udpated location.", users,
				actualUsers);
	}

	@Test
	public void testThatUnSubscribeExpiresTheSubscriptionProvided() {
		// Create test data
		Long subscriptionId = Long.valueOf(59123L);

		// Set mock expectations
		Capture<Long> captureSubscriptionId = EasyMock.newCapture();
		this.subscriptionMapper.expire(EasyMock.capture(captureSubscriptionId));
		EasyMock.expectLastCall().once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		this.topicService.unSubscribe(subscriptionId);
		Assert.assertEquals("UnSubscribe should expire the subscription for the ID provided.", subscriptionId,
				captureSubscriptionId.getValue());
		EasyMock.verify(this.subscriptionMapper);
	}

	@Test
	public void testThatGetUsersInTopicGetsAllUsersSubscribedToTheTopicInTheLocationProvided() {
		// Create test data
		HashTag topic = EasyMock.createNiceMock(HashTag.class);
		Location location = EasyMock.createNiceMock(Location.class);

		List<User> users = new ArrayList<User>();
		users.add(EasyMock.createNiceMock(User.class));

		// Set mock expectations
		EasyMock.expect(this.userMapper.getUsers(topic, location)).andReturn(users).once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		List<User> actualUsers = this.topicService.getUsersInTopic(topic, location);
		Assert.assertEquals(
				"GetUsersInTopic should return all the users subscribed to the topic in the location provided", users,
				actualUsers);
	}

	@Test
	public void testThatGetUserMapperReturnsTheUserMapperThatWasSet() {
		// Create mock
		UserMapper userMapper = EasyMock.createNiceMock(UserMapper.class);

		// Set value
		this.topicService.setUserMapper(userMapper);

		// Verify value
		Assert.assertEquals("GetUserMapper should return the userMapper that was set.", userMapper,
				this.topicService.getUserMapper());
	}

	@Test
	public void testThatGetHashTagMapperReturnsTheHashTagMapperThatWasSet() {
		// Create mock
		HashTagMapper hashTagMapper = EasyMock.createNiceMock(HashTagMapper.class);

		// Set value
		this.topicService.setHashTagMapper(hashTagMapper);

		// Verify value
		Assert.assertEquals("GetHashTagMapper should return the hashTagMapper that was set.", hashTagMapper,
				this.topicService.getHashTagMapper());
	}

	@Test
	public void testThatGetSubscriptionMapperReturnsTheSubscriptionMapperThatWasSet() {
		// Create mock
		SubscriptionMapper subscriptionMapper = EasyMock.createNiceMock(SubscriptionMapper.class);

		// Set value
		this.topicService.setSubscriptionMapper(subscriptionMapper);

		// Verify value
		Assert.assertEquals("GetSubscriptionMapper should return the subscriptionMapper that was set.",
				subscriptionMapper, this.topicService.getSubscriptionMapper());
	}

	@Test
	public void testThatGetMeasurementConverterFactoryReturnsTheMeasurementConverterFactoryThatWasSet() {
		// Create mock
		MeasurementConverterFactory measurementConverterFactory = EasyMock
				.createNiceMock(MeasurementConverterFactory.class);

		// Set value
		this.topicService.setMeasurementConverterFactory(measurementConverterFactory);

		// Verify value
		Assert.assertEquals(
				"GetMeasurementConverterFactory should return the measurementConverterFactory that was set.",
				measurementConverterFactory, this.topicService.getMeasurementConverterFactory());
	}

	/**
	 * Readies the mock framework before each test
	 */
	private void readyMockFramework() {
		EasyMock.replay(this.userMapper);
		EasyMock.replay(this.hashTagMapper);
		EasyMock.replay(this.subscriptionMapper);
	}
}
