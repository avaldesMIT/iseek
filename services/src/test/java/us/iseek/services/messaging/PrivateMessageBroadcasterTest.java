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

import org.apache.log4j.Appender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.easymock.EasyMock;
import org.hamcrest.core.StringContains;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import us.iseek.model.communication.Message;
import us.iseek.model.communication.PrivateMessage;
import us.iseek.test.util.TestAppender;

/**
 * Tests the <tt>PrivateMessageBroadcaster</tt>.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class PrivateMessageBroadcasterTest {

	private PrivateMessageBroadcaster privateMessageBroadcaster;

	private Message message;

	private Logger logger;
	private Appender appender;

	@Before
	public void setUp() {
		// Set up logging framework
		this.appender = new TestAppender();
		this.logger = Logger.getLogger(PrivateMessageBroadcaster.class);
		this.logger.addAppender(appender);
		this.logger.setLevel(Level.INFO);

		// Create mocks
		this.message = EasyMock.createNiceMock(PrivateMessage.class);

		// Create entity
		this.privateMessageBroadcaster = new PrivateMessageBroadcaster();
	}

	@After
	public void tearDown() {
		// Reset logging framework
		logger.setLevel(Level.INFO);
	}

	@Test
	public void testThatBroadcastLogsBroadcastWithDebugLevel() {
		// Set debug level
		logger.setLevel(Level.DEBUG);

		// Test entity
		this.privateMessageBroadcaster.broadcast(this.message);

		// Verify logs
		String logs = ((TestAppender) appender).getLog();
		Assert.assertThat("Log should have a type", logs,
				StringContains.containsString("type=BROADCASTING_PRIVATE_MESSAGE"));
		Assert.assertThat("Log should contain message being broadcasted", logs,
				StringContains.containsString(String.valueOf(this.message)));
	}
}
