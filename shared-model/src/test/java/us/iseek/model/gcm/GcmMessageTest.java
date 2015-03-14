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

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.hamcrest.core.StringContains;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import us.iseek.model.communication.MessageAbstract;

/**
 * Tests the <tt>GcmMessage</tt>.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class GcmMessageTest {

	private GcmMessage gcmMessage;

	@Before
	public void setUp() {
		this.gcmMessage = new GcmMessage();
	}

	@Test
	public void testThatGetDataReturnsTheDataThatWasSet() {
		// Create mock
		MessageAbstract data = EasyMock.createNiceMock(MessageAbstract.class);

		// Set value
		this.gcmMessage.setData(data);

		// Verify value
		Assert.assertEquals("GetData should return the data that was set.", data, this.gcmMessage.getData());
	}

	@Test
	public void testThatGetRegistration_idsReturnsTheRegistration_idsThatWasSet() {
		// Create mock
		List<String> registration_ids = new ArrayList<String>();
		registration_ids.add("REGISTRATION_ID");

		// Set value
		this.gcmMessage.setRegistration_ids(registration_ids);

		// Verify value
		Assert.assertEquals("GetRegistration_ids should return the registration_ids that was set.", registration_ids,
				this.gcmMessage.getRegistration_ids());
	}

	@Test
	public void testThatGetCollapse_keyReturnsTheCollapse_keyThatWasSet() {
		// Create mock
		String collapse_key = "COLLAPSE_KEY";

		// Set value
		this.gcmMessage.setCollapse_key(collapse_key);

		// Verify value
		Assert.assertEquals("GetCollapse_key should return the collapse_key that was set.", collapse_key,
				this.gcmMessage.getCollapse_key());
	}

	@Test
	public void testThatGetTime_to_liveReturnsTheTime_to_liveThatWasSet() {
		// Create mock
		Long time_to_live = Long.valueOf(58123L);

		// Set value
		this.gcmMessage.setTime_to_live(time_to_live);

		// Verify value
		Assert.assertEquals("GetTime_to_live should return the time_to_live that was set.", time_to_live,
				this.gcmMessage.getTime_to_live());
	}

	@Test
	public void testThatGetDelay_while_idleReturnsTheDelay_while_idleThatWasSet() {
		// Create mock
		Boolean delay_while_idle = Boolean.TRUE;

		// Set value
		this.gcmMessage.setDelay_while_idle(delay_while_idle);

		// Verify value
		Assert.assertEquals("GetDelay_while_idle should return the delay_while_idle that was set.", delay_while_idle,
				this.gcmMessage.getDelay_while_idle());
	}

	@Test
	public void testThatToStringContainsTheDataThatWasSet() {
		// Create mock
		MessageAbstract data = EasyMock.createNiceMock(MessageAbstract.class);

		// Set value
		this.gcmMessage.setData(data);

		// Verify value
		Assert.assertThat("ToString should contain the data that was set.", this.gcmMessage.toString(),
				StringContains.containsString(String.valueOf(data)));
	}

	@Test
	public void testThatToStringContainsTheRegistration_idsThatWasSet() {
		// Create mock
		List<String> registration_ids = new ArrayList<String>();
		registration_ids.add("REGISTRATION_ID");

		// Set value
		this.gcmMessage.setRegistration_ids(registration_ids);

		// Verify value
		Assert.assertThat("ToString should contain the registration_ids that was set.", this.gcmMessage.toString(),
				StringContains.containsString(String.valueOf(registration_ids)));
	}

	@Test
	public void testThatToStringContainsTheCollapse_keyThatWasSet() {
		// Create mock
		String collapse_key = "COLLAPSE_KEY";

		// Set value
		this.gcmMessage.setCollapse_key(collapse_key);

		// Verify value
		Assert.assertThat("ToString should contain the collapse_key that was set.", this.gcmMessage.toString(),
				StringContains.containsString(String.valueOf(collapse_key)));
	}

	@Test
	public void testThatToStringContainsTheTime_to_liveThatWasSet() {
		// Create mock
		Long time_to_live = Long.valueOf(58123L);

		// Set value
		this.gcmMessage.setTime_to_live(time_to_live);

		// Verify value
		Assert.assertThat("ToString should contain the time_to_live that was set.", this.gcmMessage.toString(),
				StringContains.containsString(String.valueOf(time_to_live)));
	}

	@Test
	public void testThatToStringContainsTheDelay_while_idleThatWasSet() {
		// Create mock
		Boolean delay_while_idle = Boolean.TRUE;

		// Set value
		this.gcmMessage.setDelay_while_idle(delay_while_idle);

		// Verify value
		Assert.assertThat("ToString should contain the delay_while_idle that was set.", this.gcmMessage.toString(),
				StringContains.containsString(String.valueOf(delay_while_idle)));
	}
}
