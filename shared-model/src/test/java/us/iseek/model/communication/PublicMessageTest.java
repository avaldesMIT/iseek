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

import org.easymock.EasyMock;
import org.hamcrest.core.StringContains;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import us.iseek.model.enums.MessageType;
import us.iseek.model.gps.Location;
import us.iseek.model.topics.HashTag;

/**
 * Tests the <tt>PublicMessage</tt>.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class PublicMessageTest {

	private PublicMessage publicMessage;

	@Before
	public void setUp() {
		this.publicMessage = new PublicMessage();
	}

	@Test
	public void testThatTheTypeOfThisMessageIsPublic() {
		Assert.assertEquals("The type of public messages should be PUBLIC", MessageType.PUBLIC,
				this.publicMessage.getType());
	}

	@Test
	public void testThatGetTopicReturnsTheTopicThatWasSet() {
		// Create mock
		HashTag topic = EasyMock.createMock(HashTag.class);

		// Set value
		this.publicMessage.setTopic(topic);

		// Verify value
		Assert.assertEquals("GetTopic should return the topic that was set.", topic, this.publicMessage.getTopic());
	}

	@Test
	public void testThatGetLocationReturnsTheLocationThatWasSet() {
		// Create mock
		Location location = EasyMock.createMock(Location.class);

		// Set value
		this.publicMessage.setLocation(location);

		// Verify value
		Assert.assertEquals("GetLocation should return the location that was set.", location,
				this.publicMessage.getLocation());
	}

	@Test
	public void testThatToStringContainsTheTopicThatWasSet() {
		// Create mock
		HashTag topic = EasyMock.createMock(HashTag.class);

		// Set value
		this.publicMessage.setTopic(topic);

		// Verify value
		Assert.assertThat("ToString should contain the topic that was set.", this.publicMessage.toString(),
				StringContains.containsString(String.valueOf(topic)));
	}

	@Test
	public void testThatToStringContainsTheLocationThatWasSet() {
		// Create mock
		Location location = EasyMock.createMock(Location.class);

		// Set value
		this.publicMessage.setLocation(location);

		// Verify value
		Assert.assertThat("ToString should contain the location that was set.", this.publicMessage.toString(),
				StringContains.containsString(String.valueOf(location)));
	}
}
