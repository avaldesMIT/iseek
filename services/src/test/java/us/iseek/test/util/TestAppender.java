/**
 * Copyright (C) 2015 iSeek, Inc. 
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of iSeek, Inc.
 * You shall not disclose such confidential information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with 
 * iSeek, Inc.
 */
package us.iseek.test.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.AsyncAppender;
import org.apache.log4j.NDC;
import org.apache.log4j.spi.LoggingEvent;

/**
 * An appender that captures the information logged by the application.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class TestAppender extends AsyncAppender {

	private String loggingContext;
	private StringBuilder logBuilder;
	private List<Throwable> loggedCauses;

	/**
	 * Creates a new instance of this.
	 */
	public TestAppender() {
		this.logBuilder = new StringBuilder();
		this.loggedCauses = new ArrayList<Throwable>();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doAppend(LoggingEvent loggingEvent) {
		this.logBuilder.append(loggingEvent.getMessage().toString());
		if (loggingEvent.getThrowableInformation() != null) {
			this.loggedCauses.add(loggingEvent.getThrowableInformation().getThrowable());
		}
		this.loggingContext = NDC.peek();
	}

	/**
	 * Gets the NDC logging context.
	 * 
	 * @return the NDC logging context as returned by <tt>NDC.peek()</tt> at the
	 *         time of the last logging event, or null if no logging events have
	 *         been logged to this appender.
	 */
	public String getLoggingContext() {
		return this.loggingContext;
	}

	/**
	 * Gets the contents of the logs.
	 * 
	 * @return A string conformed of all the logging event messages appended
	 *         together.
	 */
	public String getLog() {
		return this.logBuilder.toString();
	}

	/**
	 * Gets the list of causes logged to the appender.
	 * 
	 * @return the causes logged in the logging event throwable information, or
	 *         an empty list of no throwables were logged to this appender.
	 */
	public List<Throwable> getLoggedCauses() {
		return this.loggedCauses;
	}
}
