/**
 * Copyright (C) 2015 iSeek, Inc.
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of iSeek, Inc.
 * You shall not disclose such confidential information and shall use it only
 * in accordance with the terms of the license agreement you entered into with
 * iSeek, Inc.
 */
package us.iseek.model.communication;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.StringContains;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import us.iseek.model.enums.MessageType;

/**
 * Tests the <tt>PrivateMessage</tt>.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class PrivateMessageTest {

	private PrivateMessage privateMessage;

	@Before
	public void setUp() {
		this.privateMessage = new PrivateMessage();
	}

	@Test
	public void testThatTheTypeOfThisMessageIsPrivate() {
		Assert.assertEquals("The type of private messages should be PRIVATE", MessageType.PRIVATE,
				this.privateMessage.getType());
	}

	@Test
	public void testThatGetDestinationUserIdsReturnsTheDestinationUserIdsThatWasSet() {
		// Create mock
		List<Long> destinationUserIds = new ArrayList<Long>();
		destinationUserIds.add(Long.valueOf(1234L));

		// Set value
		this.privateMessage.setDestinationUserIds(destinationUserIds);

		// Verify value
		Assert.assertEquals("GetDestinationUserIds should return the destinationUserIds that was set.",
				destinationUserIds, this.privateMessage.getDestinationUserIds());
	}

	@Test
	public void testThatToStringContainsTheDestinationUserIdsThatWasSet() {
		// Create mock
		List<Long> destinationUserIds = new ArrayList<Long>();
		destinationUserIds.add(Long.valueOf(12341L));

		// Set value
		this.privateMessage.setDestinationUserIds(destinationUserIds);

		// Verify value
		Assert.assertThat("ToString should contain the destinationUserIds that was set.",
				this.privateMessage.toString(), StringContains.containsString(String.valueOf(destinationUserIds)));
	}
}
