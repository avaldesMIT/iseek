/**
 * Copyright (C) 2015 iSeek, Inc. 
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of iSeek, Inc.
 * You shall not disclose such confidential information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with 
 * iSeek, Inc.
 */
package us.iseek.services.messaging;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Appender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.easymock.Capture;
import org.easymock.EasyMock;
import org.hamcrest.core.StringContains;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import us.iseek.model.communication.MessageAbstract;
import us.iseek.model.communication.PublicMessage;
import us.iseek.model.constants.ApplicationConstants;
import us.iseek.model.converters.measurement.MeasurementConverterFactory;
import us.iseek.model.enums.MeasureUnit;
import us.iseek.model.exception.ConversionException;
import us.iseek.model.gcm.GcmMessage;
import us.iseek.model.gcm.GcmResponse;
import us.iseek.model.gps.Area;
import us.iseek.model.gps.Location;
import us.iseek.services.persistence.PublicMessageMapper;
import us.iseek.test.util.TestAppender;

/**
 * Tests the <tt>PublicMessageBroadcaster</tt>.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class PublicMessageBroadcasterTest {

	private PublicMessageBroadcaster publicMessageBroadcaster;

	private Location location;
	private PublicMessage message;
	private MessageAbstract messageAbstract;

	private RestTemplate restTemplate;
	private PublicMessageMapper publicMessageMapper;
	private MeasurementConverterFactory measurementConverterFactory;

	private Logger logger;
	private Appender appender;

	@Before
	public void setUp() {
		// Set up logging framework
		this.appender = new TestAppender();
		this.logger = Logger.getLogger(PublicMessageBroadcaster.class);
		this.logger.addAppender(appender);
		this.logger.setLevel(Level.INFO);

		// Create mocks
		this.location = EasyMock.createNiceMock(Location.class);
		this.message = EasyMock.createNiceMock(PublicMessage.class);
		this.messageAbstract = EasyMock.createNiceMock(MessageAbstract.class);

		this.restTemplate = EasyMock.createNiceMock(RestTemplate.class);
		this.publicMessageMapper = EasyMock.createNiceMock(PublicMessageMapper.class);
		this.measurementConverterFactory = EasyMock.createNiceMock(MeasurementConverterFactory.class);

		// Define mock expectations
		EasyMock.expect(this.message.getLocation()).andReturn(this.location).anyTimes();
		EasyMock.expect(this.message.createMessageAbstract()).andReturn(this.messageAbstract).anyTimes();

		// Create entity
		this.publicMessageBroadcaster = new PublicMessageBroadcaster();
		this.publicMessageBroadcaster.setRestTemplate(this.restTemplate);
		this.publicMessageBroadcaster.setPublicMessageMapper(this.publicMessageMapper);
		this.publicMessageBroadcaster.setMeasurementConverterFactory(this.measurementConverterFactory);
	}

	@After
	public void tearDown() {
		// Reset logging framework
		this.logger.setLevel(Level.INFO);
	}

	@Test
	public void testThatBroadcastLogsBroadcastWithDebugLevel() {
		// Set debug level
		this.logger.setLevel(Level.DEBUG);

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		this.publicMessageBroadcaster.broadcast(this.message);

		// Verify logs
		String logs = ((TestAppender) appender).getLog();
		Assert.assertThat("Log should have a type", logs,
				StringContains.containsString("type=BROADCASTING_PUBLIC_MESSAGE"));
		Assert.assertThat("Log should contain message being broadcasted", logs,
				StringContains.containsString(String.valueOf(this.message)));
	}

	@Test
	public void testThatBroadcastGetsGcmRegistrationIdsForPublicMessageId() {
		// Create test data
		List<String> recipientRegistrationIds = new ArrayList<String>();
		recipientRegistrationIds.add("RECIPIENT_REGISTRATION_ID");

		Long messageId = Long.valueOf(18324L);
		EasyMock.expect(this.message.getId()).andReturn(messageId).anyTimes();

		// Define mock expectations
		Capture<Long> captureMessageId = EasyMock.newCapture();
		EasyMock.expect(
				this.publicMessageMapper.getRecipientRegistrationIds(EasyMock.capture(captureMessageId),
						EasyMock.anyObject(Area.class))).andReturn(recipientRegistrationIds).once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		this.publicMessageBroadcaster.broadcast(this.message);

		// Verify message ID
		Assert.assertEquals("Broadcast should get GCM registration IDs for the ID of the public message", messageId,
				captureMessageId.getValue());
	}

	@Test
	public void testThatBroadcastGetsGcmRegistrationIdsForARadialAreaAroundThePublicMessageLocation()
			throws ConversionException {

		// Create test data
		List<String> recipientRegistrationIds = new ArrayList<String>();
		recipientRegistrationIds.add("RECIPIENT_REGISTRATION_ID");

		Area area = EasyMock.createNiceMock(Area.class);
		EasyMock.expect(this.location.getRadialArea(EasyMock.anyDouble(), EasyMock.anyObject(MeasureUnit.class)))
				.andReturn(area).anyTimes();

		// Define mock expectations
		Capture<Area> captureArea = EasyMock.newCapture();
		EasyMock.expect(
				this.publicMessageMapper.getRecipientRegistrationIds(EasyMock.anyLong(), EasyMock.capture(captureArea)))
				.andReturn(recipientRegistrationIds).once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		this.publicMessageBroadcaster.broadcast(this.message);

		// Verify area
		Assert.assertEquals(
				"Broadcast should get GCM registration IDs for a radial area around the public message's location",
				area, captureArea.getValue());
	}

	@Test
	public void testThatBroadcastLogsErrorIfGettingRadialAreaThrowsConversionException() throws ConversionException {

		// Prepare logger
		this.logger.setLevel(Level.ERROR);

		// Create test data
		ConversionException conversionException = EasyMock.createNiceMock(ConversionException.class);

		// Define mock expectations
		EasyMock.expect(this.location.getRadialArea(EasyMock.anyDouble(), EasyMock.anyObject(MeasureUnit.class)))
				.andThrow(conversionException).anyTimes();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		this.publicMessageBroadcaster.broadcast(this.message);

		// Verify logs
		String logs = ((TestAppender) appender).getLog();
		Assert.assertThat("Log should have a type", logs,
				StringContains.containsString("type=ERROR_GETTING_BROADCAST_AREA"));
		Assert.assertEquals("Log should contain root cause of the error", 1, ((TestAppender) appender)
				.getLoggedCauses().size());
		Assert.assertEquals("Log should contain root cause of the error", conversionException,
				((TestAppender) appender).getLoggedCauses().iterator().next());
	}

	@Test
	public void testThatBroadcastSetsRegistrationIdsToGcmMessage() {
		// Create test data
		List<String> recipientRegistrationIds = new ArrayList<String>();
		recipientRegistrationIds.add("RECIPIENT_REGISTRATION_ID");
		recipientRegistrationIds.add("RECIPIENT_REGISTRATION_ID2");

		GcmResponse gcmResponse = EasyMock.createNiceMock(GcmResponse.class);

		// Define mock expectations
		EasyMock.expect(
				this.publicMessageMapper.getRecipientRegistrationIds(EasyMock.anyLong(), EasyMock.anyObject(Area.class)))
				.andReturn(recipientRegistrationIds).once();

		Capture<HttpEntity<GcmMessage>> httpEntityCapture = EasyMock.newCapture();
		EasyMock.expect(
				this.restTemplate.postForObject(EasyMock.anyObject(String.class), EasyMock.capture(httpEntityCapture),
						EasyMock.eq(GcmResponse.class))).andReturn(gcmResponse).once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		this.publicMessageBroadcaster.broadcast(this.message);

		// Verify GCM message
		GcmMessage actualGcmMessage = httpEntityCapture.getValue().getBody();
		Assert.assertEquals("Broadcast should set registration IDs to GCM message", recipientRegistrationIds,
				actualGcmMessage.getRegistration_ids());
	}

	@Test
	public void testThatBroadcastSetsMessageAbstractFromPublicMessageToGcmMessage() {
		// Create test data
		GcmResponse gcmResponse = EasyMock.createNiceMock(GcmResponse.class);

		// Define mock expectations
		Capture<HttpEntity<GcmMessage>> httpEntityCapture = EasyMock.newCapture();
		EasyMock.expect(
				this.restTemplate.postForObject(EasyMock.anyObject(String.class), EasyMock.capture(httpEntityCapture),
						EasyMock.eq(GcmResponse.class))).andReturn(gcmResponse).once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		this.publicMessageBroadcaster.broadcast(this.message);

		// Verify GCM message
		GcmMessage actualGcmMessage = httpEntityCapture.getValue().getBody();
		Assert.assertEquals("Broadcast should set public message's abstract to GCM message's data",
				this.messageAbstract, actualGcmMessage.getData());
	}

	@Test
	public void testThatBroadcastSetsTimeToLiveGreaterThanZeroToGcmMessage() {
		// Create test data
		GcmResponse gcmResponse = EasyMock.createNiceMock(GcmResponse.class);

		// Define mock expectations
		Capture<HttpEntity<GcmMessage>> httpEntityCapture = EasyMock.newCapture();
		EasyMock.expect(
				this.restTemplate.postForObject(EasyMock.anyObject(String.class), EasyMock.capture(httpEntityCapture),
						EasyMock.eq(GcmResponse.class))).andReturn(gcmResponse).once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		this.publicMessageBroadcaster.broadcast(this.message);

		// Verify GCM message
		GcmMessage actualGcmMessage = httpEntityCapture.getValue().getBody();
		Assert.assertTrue("Broadcast should set a value greater than zero to GCM message's time to live",
				actualGcmMessage.getTime_to_live() > 0L);
	}

	@Test
	public void testThatBroadcastSetsAuthorizationKeyInGcmPostRequestHeaders() {
		// Create test data
		GcmResponse gcmResponse = EasyMock.createNiceMock(GcmResponse.class);

		// Define mock expectations
		Capture<HttpEntity<GcmMessage>> httpEntityCapture = EasyMock.newCapture();
		EasyMock.expect(
				this.restTemplate.postForObject(EasyMock.anyObject(String.class), EasyMock.capture(httpEntityCapture),
						EasyMock.eq(GcmResponse.class))).andReturn(gcmResponse).once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		this.publicMessageBroadcaster.broadcast(this.message);

		// Verify post request headers
		HttpHeaders actualHeaders = httpEntityCapture.getValue().getHeaders();
		Assert.assertEquals("Broadcast should set authorization key in request headers", 1,
				actualHeaders.get("Authorization").size());
		Assert.assertEquals("Broadcast should set authorization key in request headers", ApplicationConstants.API_KEY,
				actualHeaders.get("Authorization").iterator().next());
	}

	@Test
	public void testThatBroadcastSetsContentTypeOfJsonInGcmPostRequestHeaders() {
		// Create test data
		GcmResponse gcmResponse = EasyMock.createNiceMock(GcmResponse.class);

		// Define mock expectations
		Capture<HttpEntity<GcmMessage>> httpEntityCapture = EasyMock.newCapture();
		EasyMock.expect(
				this.restTemplate.postForObject(EasyMock.anyObject(String.class), EasyMock.capture(httpEntityCapture),
						EasyMock.eq(GcmResponse.class))).andReturn(gcmResponse).once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		this.publicMessageBroadcaster.broadcast(this.message);

		// Verify post request headers
		HttpHeaders actualHeaders = httpEntityCapture.getValue().getHeaders();
		Assert.assertEquals("Broadcast should set content type of json in request headers", 1,
				actualHeaders.get("Content-Type").size());
		Assert.assertEquals("Broadcast should set content type of json in request headers", "application/json",
				actualHeaders.get("Content-Type").iterator().next());
	}

	@Test
	public void testThatBroadcastCallsGCMUrlAddressInPostRequest() {
		// Create test data
		GcmResponse gcmResponse = EasyMock.createNiceMock(GcmResponse.class);

		// Define mock expectations
		Capture<String> captureUrl = EasyMock.newCapture();
		EasyMock.expect(
				this.restTemplate.postForObject(EasyMock.capture(captureUrl), EasyMock.anyObject(),
						EasyMock.eq(GcmResponse.class))).andReturn(gcmResponse).once();

		// Set up mock framework
		this.readyMockFramework();

		// Test entity
		this.publicMessageBroadcaster.broadcast(this.message);

		// Verify post URL
		Assert.assertEquals("Broadcast should use the right URL for GCM", "https://android.googleapis.com/gcm/send",
				captureUrl.getValue());
	}

	@Test
	public void testThatGetRestTemplateReturnsTheRestTemplateThatWasSet() {
		// Create mock
		RestTemplate restTemplate = EasyMock.createNiceMock(RestTemplate.class);

		// Set value
		this.publicMessageBroadcaster.setRestTemplate(restTemplate);

		// Verify value
		Assert.assertEquals("GetRestTemplate should return the restTemplate that was set.", restTemplate,
				this.publicMessageBroadcaster.getRestTemplate());
	}

	@Test
	public void testThatGetPublicMessageMapperReturnsThePublicMessageMapperThatWasSet() {
		// Create mock
		PublicMessageMapper publicMessageMapper = EasyMock.createNiceMock(PublicMessageMapper.class);

		// Set value
		this.publicMessageBroadcaster.setPublicMessageMapper(publicMessageMapper);

		// Verify value
		Assert.assertEquals("GetPublicMessageMapper should return the publicMessageMapper that was set.",
				publicMessageMapper, this.publicMessageBroadcaster.getPublicMessageMapper());
	}

	@Test
	public void testThatGetMeasurementConverterFactoryReturnsTheMeasurementConverterFactoryThatWasSet() {
		// Create mock
		MeasurementConverterFactory measurementConverterFactory = EasyMock
				.createNiceMock(MeasurementConverterFactory.class);

		// Set value
		this.publicMessageBroadcaster.setMeasurementConverterFactory(measurementConverterFactory);

		// Verify value
		Assert.assertEquals(
				"GetMeasurementConverterFactory should return the measurementConverterFactory that was set.",
				measurementConverterFactory, this.publicMessageBroadcaster.getMeasurementConverterFactory());
	}

	/**
	 * Sets up the mock framework to start testing.
	 */
	private void readyMockFramework() {
		EasyMock.replay(this.message);
		EasyMock.replay(this.location);
		EasyMock.replay(this.restTemplate);
		EasyMock.replay(this.publicMessageMapper);
		EasyMock.replay(this.measurementConverterFactory);

	}
}
