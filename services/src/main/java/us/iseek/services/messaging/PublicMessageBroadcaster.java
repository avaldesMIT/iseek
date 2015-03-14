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

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import us.iseek.model.communication.Message;
import us.iseek.model.communication.PublicMessage;
import us.iseek.model.constants.ApplicationConstants;
import us.iseek.model.converters.measurement.MeasurementConverterFactory;
import us.iseek.model.exception.ConversionException;
import us.iseek.model.gcm.GcmMessage;
import us.iseek.model.gcm.GcmResponse;
import us.iseek.model.gps.Area;
import us.iseek.model.gps.Location;
import us.iseek.services.persistence.PublicMessageMapper;

/**
 * Broadcasts messages to the topic specified by the public message. The
 * broadcast is limited to the location also specified in the message. Thus,
 * users that are subscribed to a topic may not receive the message if they are
 * out of the area of interest specified by the message's location.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class PublicMessageBroadcaster implements MessageBroadcaster {

	private final Log log = LogFactory.getLog(PublicMessageBroadcaster.class);

	private final Long TIME_TO_LIVE = Long.valueOf(180L);
	private final String GCM_SEND_URL = "https://android.googleapis.com/gcm/send";

	private RestTemplate restTemplate;
	private PublicMessageMapper publicMessageMapper;
	private MeasurementConverterFactory measurementConverterFactory;

	/**
	 * {@inheritDoc}
	 */
	public void broadcast(Message message) {
		log.debug("type=BROADCASTING_PUBLIC_MESSAGE, desc=Broadcasting public message, object=" + message);

		// Get message to broadcast
		GcmMessage gcmMessage = new GcmMessage();
		gcmMessage.setTime_to_live(TIME_TO_LIVE);
		gcmMessage.setData(message.createMessageAbstract());

		// Get broadcast location
		PublicMessage publicMessage = (PublicMessage) message;
		Location broadcastLocation = publicMessage.getLocation();
		broadcastLocation.setMeasurementConverterFactory(this.measurementConverterFactory);

		try {
			// Define broadcast area
			Area broadcastArea = broadcastLocation.getRadialArea(ApplicationConstants.DEFAULT_RADIUS,
					ApplicationConstants.DEFAULT_UNIT);

			// Set recipients
			List<String> recipientGcmRegistrationIds = this.publicMessageMapper.getRecipientRegistrationIds(
					publicMessage.getId(), broadcastArea);
			gcmMessage.setRegistration_ids(recipientGcmRegistrationIds);

			// Set HTTP headers
			MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
			headers.add("Authorization", ApplicationConstants.API_KEY);
			headers.add("Content-Type", "application/json");

			// Post message to GCM server
			HttpEntity<GcmMessage> postRequest = new HttpEntity<GcmMessage>(gcmMessage, headers);
			GcmResponse gcmResponse = this.restTemplate.postForObject(GCM_SEND_URL, postRequest, GcmResponse.class);
			log.debug("type=SENT_GCM_MESSAGE, desc=Sent GCM message: " + gcmMessage + ", result=" + gcmResponse);
		} catch (ConversionException e) {
			log.error("type=ERROR_GETTING_BROADCAST_AREA, desc=There was an error computing "
					+ "the broadcast area from the message location. Message will be lost", e);
		}
	}

	/**
	 * @return the publicMessageMapper
	 */
	public PublicMessageMapper getPublicMessageMapper() {
		return this.publicMessageMapper;
	}

	/**
	 * @param publicMessageMapper
	 *            - The publicMessageMapper to set
	 */
	public void setPublicMessageMapper(PublicMessageMapper publicMessageMapper) {
		this.publicMessageMapper = publicMessageMapper;
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

	/**
	 * @return the restTemplate
	 */
	public RestTemplate getRestTemplate() {
		return this.restTemplate;
	}

	/**
	 * @param restTemplate
	 *            - The restTemplate to set
	 */
	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
}
