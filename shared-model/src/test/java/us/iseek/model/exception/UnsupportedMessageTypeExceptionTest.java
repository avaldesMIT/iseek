/**
 * Copyright (C) 2015 iSeek, Inc.
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of iSeek, Inc.
 * You shall not disclose such confidential information and shall use it only
 * in accordance with the terms of the license agreement you entered into with
 * iSeek, Inc.
 */
package us.iseek.model.exception;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the <tt>UnsupportedMessageTypeException</tt>.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class UnsupportedMessageTypeExceptionTest {

	private final static String MESSAGE = "MESSAGE";

	private UnsupportedMessageTypeException unsupportedMessageTypeException;

	@Before
	public void setUp() {
		this.unsupportedMessageTypeException = new UnsupportedMessageTypeException(MESSAGE);
	}

	@Test
	public void testThatGetMessageReturnsExceptionsMessage() {
		Assert.assertEquals("The exception should have the message it was constructed with.", MESSAGE,
				this.unsupportedMessageTypeException.getMessage());
	}
}
