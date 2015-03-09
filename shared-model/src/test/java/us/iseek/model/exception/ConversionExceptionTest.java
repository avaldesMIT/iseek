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

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the <tt>ConversionException</tt>.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class ConversionExceptionTest {

	private final static String MESSAGE = "MESSAGE";

	private ConversionException conversionException;

	@Before
	public void setUp() {
		this.conversionException = new ConversionException(MESSAGE);
	}

	@Test
	public void testThatGetMessageReturnsExceptionsMessage() {
		Assert.assertEquals("The exception should have the message it was constructed with.", MESSAGE,
				this.conversionException.getMessage());
	}

	@Test
	public void testThatGetCauseReturnsExceptionsCause() {
		// Create mock
		Throwable throwable = EasyMock.createMock(Throwable.class);
		EasyMock.replay(throwable);

		// Test method
		this.conversionException = new ConversionException(MESSAGE, throwable);
		Assert.assertEquals("The exception should return the cause of the exception.", throwable,
				this.conversionException.getCause());
	}
}
