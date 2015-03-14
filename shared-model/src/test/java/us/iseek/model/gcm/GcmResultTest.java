/**
 * Copyright (C) 2015 iSeek, Inc. 
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of iSeek, Inc.
 * You shall not disclose such confidential information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with 
 * iSeek, Inc.
 */
package us.iseek.model.gcm;

import org.hamcrest.core.StringContains;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * TODO Define type
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class GcmResultTest {

	private GcmResult gcmResult;

	@Before
	public void setUp() {
		this.gcmResult = new GcmResult();
	}

	@Test
	public void testThatGetMessage_idReturnsTheMessage_idThatWasSet() {
		// Create mock
		String message_id = "MESSAGE_ID";

		// Set value
		this.gcmResult.setMessage_id(message_id);

		// Verify value
		Assert.assertEquals("GetMessage_id should return the message_id that was set.", message_id,
				this.gcmResult.getMessage_id());
	}

	@Test
	public void testThatGetRegistration_idReturnsTheRegistration_idThatWasSet() {
		// Create mock
		String registration_id = "REGISTRATION_ID";

		// Set value
		this.gcmResult.setRegistration_id(registration_id);

		// Verify value
		Assert.assertEquals("GetRegistration_id should return the registration_id that was set.", registration_id,
				this.gcmResult.getRegistration_id());
	}

	@Test
	public void testThatGetErrorReturnsTheErrorThatWasSet() {
		// Create mock
		String error = "ERROR";

		// Set value
		this.gcmResult.setError(error);

		// Verify value
		Assert.assertEquals("GetError should return the error that was set.", error, this.gcmResult.getError());
	}

	@Test
	public void testThatToStringContainsTheMessage_idThatWasSet() {
		// Create mock
		String message_id = "MESSAGE_ID";

		// Set value
		this.gcmResult.setMessage_id(message_id);

		// Verify value
		Assert.assertThat("ToString should contain the message_id that was set.", this.gcmResult.toString(),
				StringContains.containsString(String.valueOf(message_id)));
	}

	@Test
	public void testThatToStringContainsTheRegistration_idThatWasSet() {
		// Create mock
		String registration_id = "REGISTRATION_ID";

		// Set value
		this.gcmResult.setRegistration_id(registration_id);

		// Verify value
		Assert.assertThat("ToString should contain the registration_id that was set.", this.gcmResult.toString(),
				StringContains.containsString(String.valueOf(registration_id)));
	}

	@Test
	public void testThatToStringContainsTheErrorThatWasSet() {
		// Create mock
		String error = "ERROR";

		// Set value
		this.gcmResult.setError(error);

		// Verify value
		Assert.assertThat("ToString should contain the error that was set.", this.gcmResult.toString(),
				StringContains.containsString(String.valueOf(error)));
	}
}
