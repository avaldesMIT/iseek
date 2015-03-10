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

import java.util.ArrayList;
import java.util.List;

import org.easymock.Capture;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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

/**
 * Tests the <tt>TopicController</tt>.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class TopicControllerTest {

	private TopicController topicController;

	private ITopicService topicService;

	@Before
	public void setUp() {
		// Create mocks
		this.topicService = EasyMock.createMock(ITopicService.class);

		// Create test entity
		this.topicController = new TopicController();
		this.topicController.setTopicService(this.topicService);
	}

	@After
	public void tearDown() {
		// Verify delegate was called
		EasyMock.verify(this.topicService);
	}

	@Test
	public void testThatFindTopicsDelegatesCallToService() {
		// Create test data
		Location location = EasyMock.createNiceMock(Location.class);
		EasyMock.replay(location);
		List<HashTag> topics = new ArrayList<HashTag>();
		topics.add(EasyMock.createNiceMock(HashTag.class));

		// Set expectations
		EasyMock.expect(this.topicService.findTopics(EasyMock.anyObject(Location.class))).andReturn(topics).once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		List<HashTag> actualTopics = this.topicController.findTopics(location.getLatitude(), location.getLongitude());
		Assert.assertEquals("FindTopics should return value returned by delegate.", topics, actualTopics);
	}

	@Test
	public void testThatFindTopicsCallsDelegateWithCorrectLatitudeAndLongitude() {
		// Create test data
		Double latitude = Double.valueOf(123.12345);
		Double longitude = Double.valueOf(234.56789);

		Location location = EasyMock.createNiceMock(Location.class);
		EasyMock.expect(location.getLatitude()).andReturn(latitude).anyTimes();
		EasyMock.expect(location.getLongitude()).andReturn(longitude).anyTimes();
		EasyMock.replay(location);
		;

		List<HashTag> topics = new ArrayList<HashTag>();
		topics.add(EasyMock.createNiceMock(HashTag.class));

		// Set expectations
		Capture<Location> locationCapture = EasyMock.newCapture();
		EasyMock.expect(this.topicService.findTopics(EasyMock.capture(locationCapture))).andReturn(topics).once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		this.topicController.findTopics(location.getLatitude(), location.getLongitude());

		// Verify location
		Location actualLocation = locationCapture.getValue();
		Assert.assertEquals("Delegate should be called with correct latitude in location", latitude,
				actualLocation.getLatitude());
		Assert.assertEquals("Delegate should be called with correct longitude in location", longitude,
				actualLocation.getLongitude());
	}

	@Test
	public void testThatCreateTopicDelegatesCallToService() {
		// Create test data
		String displayName = "DISPLAY NAME";
		HashTag topic = EasyMock.createNiceMock(HashTag.class);

		// Set expectations
		EasyMock.expect(this.topicService.createTopic(displayName)).andReturn(topic).once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		HashTag actualTopic = this.topicController.createTopic(displayName);
		Assert.assertEquals("CreateTopic should return value returned by delegate.", topic, actualTopic);
	}

	@Test
	public void testThatCreateTopicRemovesSpecialCharactersFromDisplayName() {
		// Create test data
		String displayName = "DISPLAY_NAME\"!@#'=,";
		HashTag topic = EasyMock.createNiceMock(HashTag.class);

		// Set expectations
		Capture<String> captureDisplayName = EasyMock.newCapture();
		EasyMock.expect(this.topicService.createTopic(EasyMock.capture(captureDisplayName))).andReturn(topic).once();

		// Define expected behavior
		String expectedDisplayName = "DISPLAYNAME";

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		this.topicController.createTopic(displayName);
		String actualDisplayName = captureDisplayName.getValue();
		Assert.assertEquals("CreateTopic should strip special characters from display name.", expectedDisplayName,
				actualDisplayName);
	}

	@Test
	public void testThatSubscribeDelegatesCallToService() throws UnknownLocationException {
		// Create test data
		Long userId = Long.valueOf(581L);
		HashTag topic = EasyMock.createNiceMock(HashTag.class);
		Subscription subscription = EasyMock.createNiceMock(Subscription.class);

		// Set expectations
		EasyMock.expect(this.topicService.subscribe(userId, topic)).andReturn(subscription).once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		Subscription actualSubscription = this.topicController.subscribe(new CreateSubscriptionRequest(userId, topic));
		Assert.assertEquals("CreateTopic should return value returned by delegate.", subscription, actualSubscription);
	}

	@Test(expected = UnknownLocationException.class)
	public void testThatSubscribeThrowsUnknownLocationExceptionIfDelegateThrowsException()
			throws UnknownLocationException {

		// Create test data
		Long userId = Long.valueOf(581L);
		HashTag topic = EasyMock.createNiceMock(HashTag.class);

		// Set expectations
		EasyMock.expect(this.topicService.subscribe(userId, topic)).andThrow(new UnknownLocationException("MESSAGE"))
				.once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity (should throw exception)
		this.topicController.subscribe(new CreateSubscriptionRequest(userId, topic));
	}

	@Test
	public void testThatRenewSubscriptionDelegatesCallToService() {
		// Create test data
		Long subscriptionId = Long.valueOf(581L);
		Subscription subscription = EasyMock.createNiceMock(Subscription.class);

		// Set expectations
		EasyMock.expect(this.topicService.renewSubscription(subscriptionId)).andReturn(subscription).once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		Subscription actualSubscription = this.topicController.renewSubscription(subscriptionId);
		Assert.assertEquals("RenewSubscription should return value returned by delegate.", subscription,
				actualSubscription);
	}

	@Test
	public void testThatUpdateSubscriptionDelegatesCallToService() {
		// Create test data
		Long subscriptionId = Long.valueOf(581L);
		Location location = EasyMock.createNiceMock(Location.class);

		List<User> users = new ArrayList<User>();
		users.add(EasyMock.createNiceMock(User.class));

		// Set expectations
		EasyMock.expect(this.topicService.updateSubscription(subscriptionId, location)).andReturn(users).once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		List<User> actualUsers = this.topicController.updateSubscription(new UpdateSubscriptionLocationRequest(
				subscriptionId, location));
		Assert.assertEquals("UpdateSubscription should return value returned by delegate.", users, actualUsers);
	}

	@Test
	public void testThatUnSubscribeDelegatesCallToService() {
		// Create test data
		Long subscriptionId = Long.valueOf(581L);

		// Set expectations
		this.topicService.unSubscribe(subscriptionId);
		EasyMock.expectLastCall().once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity (mock is verified in @After method)
		this.topicController.unSubscribe(subscriptionId);
	}

	@Test
	public void testThatGetUsersInTopicDelegatesCallToService() {
		// Create test data
		HashTag topic = EasyMock.createNiceMock(HashTag.class);
		Location location = EasyMock.createNiceMock(Location.class);

		List<User> users = new ArrayList<User>();
		users.add(EasyMock.createNiceMock(User.class));

		// Set expectations
		EasyMock.expect(this.topicService.getUsersInTopic(topic, location)).andReturn(users).once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		List<User> actualUsers = this.topicController.getUsersInTopic(new GetUsersInTopicRequest(topic, location));
		Assert.assertEquals("GetUsersInTopic should return value returned by delegate.", users, actualUsers);
	}

	@Test
	public void testThatFindSubscriptionsDelegatesCallToService() {
		// Create test data
		Long userId = Long.valueOf(71234L);
		Long topicId = Long.valueOf(91283L);
		Location location = EasyMock.createNiceMock(Location.class);

		FindSubscriptionsRequest findSubscriptionsRequest = EasyMock.createNiceMock(FindSubscriptionsRequest.class);
		EasyMock.expect(findSubscriptionsRequest.getUserId()).andReturn(userId).anyTimes();
		EasyMock.expect(findSubscriptionsRequest.getTopicId()).andReturn(topicId).anyTimes();
		EasyMock.expect(findSubscriptionsRequest.getLocation()).andReturn(location).anyTimes();
		EasyMock.replay(findSubscriptionsRequest);

		List<Subscription> subscriptions = new ArrayList<Subscription>();
		subscriptions.add(EasyMock.createNiceMock(Subscription.class));

		// Set expectations
		EasyMock.expect(this.topicService.findSubscriptions(userId, topicId, location)).andReturn(subscriptions).once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		List<Subscription> actualSubscriptions = this.topicController.findSubscriptions(findSubscriptionsRequest);
		Assert.assertEquals("FindSubscriptions should return value returned by delegate.", subscriptions,
				actualSubscriptions);
	}

	@Test
	public void testThatGetTopicServiceReturnsTheTopicServiceThatWasSet() {
		// Create mock
		ITopicService topicService = EasyMock.createNiceMock(ITopicService.class);

		// Set value
		this.topicController.setTopicService(topicService);

		// Set up mock framework
		this.readyMockFramework();

		// Verify value
		Assert.assertEquals("GetTopicService should return the topicService that was set.", topicService,
				this.topicController.getTopicService());
	}

	/**
	 * Readies the mock framework before each test
	 */
	private void readyMockFramework() {
		EasyMock.replay(this.topicService);
	}
}
