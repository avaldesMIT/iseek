/**
 * Copyright (C) 2015 iSeek, Inc. 
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of iSeek, Inc.
 * You shall not disclose such confidential information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with 
 * iSeek, Inc.
 */
package us.iseek.model.constants;

import us.iseek.model.enums.MeasureUnit;

/**
 * Defines constants to be used across the application.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public final class ApplicationConstants {

	/**
	 * Utility constructor
	 */
	private ApplicationConstants() {
		// Hide utility constructor
	}
	
	/**
	 * Stores the public API key for the application
	 */
	public static final String API_KEY = "key=AIzaSyAKJwvmwO2SDUzDDJ2r3_gp_Iii0c0I4S8";

	/**
	 * Defines the default radius for message broadcasting
	 */
	public static final Double DEFAULT_RADIUS = Double.valueOf(5d);

	/**
	 * Defines the default unit of measurement
	 */
	public static final MeasureUnit DEFAULT_UNIT = MeasureUnit.MILES;
}
