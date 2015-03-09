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

/**
 * Contains a number of geological constants used in the application.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class GeoConstants {

	/**
	 * Utility constructor
	 */
	private GeoConstants() {
		// Hide utility constructor
	}

	/** Defines the number of feet in a mile. */
	public static final Double FEET_IN_A_MILE = Double.valueOf(5280d);

	/** Defines the number of meters in a mile. */
	public static final Double METERS_IN_A_MILE = Double.valueOf(1609.34d);

	/** Defines the number of meters in a kilometer. */
	public static final Double METERS_IN_A_KILOMETER = Double.valueOf(1000d);

	/** Defines the number of feet in a meter. */
	public static final Double FEET_IN_A_METER = Double.valueOf(3.28084d);

	/** Defines the Earth's radius in meters. */
	public static final Double EARTH_RADIUS_IN_METERS = Double.valueOf(6371000d);
}
