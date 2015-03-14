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

/**
 * TODO Define type
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class GcmResponseTest {

	private GcmResponse gcmResponse;

	@Before
	public void setUp() {
		this.gcmResponse = new GcmResponse();
	}

	@Test
	public void testThatGetMulticast_idReturnsTheMulticast_idThatWasSet() {
		// Create mock
		Long multicast_id = Long.valueOf(9123L);

		// Set value
		this.gcmResponse.setMulticast_id(multicast_id);

		// Verify value
		Assert.assertEquals("GetMulticast_id should return the multicast_id that was set.", multicast_id,
				this.gcmResponse.getMulticast_id());
	}

	@Test
	public void testThatGetSuccessReturnsTheSuccessThatWasSet() {
		// Create mock
		Long success = Long.valueOf(5712L);

		// Set value
		this.gcmResponse.setSuccess(success);

		// Verify value
		Assert.assertEquals("GetSuccess should return the success that was set.", success,
				this.gcmResponse.getSuccess());
	}

	@Test
	public void testThatGetFailureReturnsTheFailureThatWasSet() {
		// Create mock
		Long failure = Long.valueOf(12341L);

		// Set value
		this.gcmResponse.setFailure(failure);

		// Verify value
		Assert.assertEquals("GetFailure should return the failure that was set.", failure,
				this.gcmResponse.getFailure());
	}

	@Test
	public void testThatGetCanonical_idsReturnsTheCanonical_idsThatWasSet() {
		// Create mock
		Long canonical_ids = Long.valueOf(261234L);

		// Set value
		this.gcmResponse.setCanonical_ids(canonical_ids);

		// Verify value
		Assert.assertEquals("GetCanonical_ids should return the canonical_ids that was set.", canonical_ids,
				this.gcmResponse.getCanonical_ids());
	}

	@Test
	public void testThatGetGcmResultsReturnsTheGcmResultsThatWasSet() {
		// Create mock
		List<GcmResult> gcmResults = new ArrayList<GcmResult>();
		gcmResults.add(EasyMock.createNiceMock(GcmResult.class));

		// Set value
		this.gcmResponse.setGcmResults(gcmResults);

		// Verify value
		Assert.assertEquals("GetGcmResults should return the gcmResults that was set.", gcmResults,
				this.gcmResponse.getGcmResults());
	}

	@Test
	public void testThatToStringContainsTheMulticast_idThatWasSet() {
		// Create mock
		Long multicast_id = Long.valueOf(9123L);

		// Set value
		this.gcmResponse.setMulticast_id(multicast_id);

		// Verify value
		Assert.assertThat("ToString should contain the multicast_id that was set.", this.gcmResponse.toString(),
				StringContains.containsString(String.valueOf(multicast_id)));
	}

	@Test
	public void testThatToStringContainsTheSuccessThatWasSet() {
		// Create mock
		Long success = Long.valueOf(5712L);

		// Set value
		this.gcmResponse.setSuccess(success);

		// Verify value
		Assert.assertThat("ToString should contain the success that was set.", this.gcmResponse.toString(),
				StringContains.containsString(String.valueOf(success)));
	}

	@Test
	public void testThatToStringContainsTheFailureThatWasSet() {
		// Create mock
		Long failure = Long.valueOf(12341L);

		// Set value
		this.gcmResponse.setFailure(failure);

		// Verify value
		Assert.assertThat("ToString should contain the failure that was set.", this.gcmResponse.toString(),
				StringContains.containsString(String.valueOf(failure)));
	}

	@Test
	public void testThatToStringContainsTheCanonical_idsThatWasSet() {
		// Create mock
		Long canonical_ids = Long.valueOf(261234L);

		// Set value
		this.gcmResponse.setCanonical_ids(canonical_ids);

		// Verify value
		Assert.assertThat("ToString should contain the canonical_ids that was set.", this.gcmResponse.toString(),
				StringContains.containsString(String.valueOf(canonical_ids)));
	}

	@Test
	public void testThatToStringContainsTheGcmResultsThatWasSet() {
		// Create mock
		List<GcmResult> gcmResults = new ArrayList<GcmResult>();
		gcmResults.add(EasyMock.createNiceMock(GcmResult.class));

		// Set value
		this.gcmResponse.setGcmResults(gcmResults);

		// Verify value
		Assert.assertThat("ToString should contain the gcmResults that was set.", this.gcmResponse.toString(),
				StringContains.containsString(String.valueOf(gcmResults)));
	}
}
