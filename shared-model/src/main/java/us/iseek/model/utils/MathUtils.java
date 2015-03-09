/**
 * Copyright (C) 2015 iSeek, Inc. 
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of iSeek, Inc.
 * You shall not disclose such confidential information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with 
 * iSeek, Inc.
 */
package us.iseek.model.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Provides mathematical utility methods
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public final class MathUtils {

	/** The precision used in arithmetic calculations. */
	private static final int DECIMAL_PRECISION = 10;

	/**
	 * The rounding method to use in calculations going beyond
	 * <tt>MathUtils.DECIMAL_PRECISION</tt>.
	 */
	private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

	private static final Double RADIANS_DEGREES = Double.valueOf(180d);

	/**
	 * Utility class constructor
	 */
	private MathUtils() {
		// Hidden constructor
	}

	/**
	 * Converts the given signed degrees to radians.
	 * 
	 * @param degrees
	 *            - The signed degrees to convert
	 * @return The converted degrees
	 */
	public static Double toRadians(Double degrees) {
		return (degrees * Math.PI) / RADIANS_DEGREES;
	}

	/**
	 * Converts the given radians to signed degrees.
	 * 
	 * @param radians
	 *            - The radians to convert
	 * @return The converted radians
	 */
	public static Double toSignedDegrees(Double radians) {
		return (radians * RADIANS_DEGREES) / Math.PI;
	}

	/**
	 * Compares the two values provided.
	 * 
	 * @param d1
	 *            - The first value to compare.
	 * @param d2
	 *            - The second value to compare.
	 * @return true if and only if d1.equals(d2) after rounding d1 and d2 to
	 *         <tt>MathUtils.DECIMAL_PRECISION</tt> using
	 *         <tt>MathUtils.ROUNDING_MODE</tt> as the rounding algorithm.
	 */
	public static boolean compare(Double d1, Double d2) {
		// Round numbers to the specified precision
		BigDecimal value1 = new BigDecimal(String.valueOf(d1)).setScale(DECIMAL_PRECISION, ROUNDING_MODE);
		BigDecimal value2 = new BigDecimal(String.valueOf(d2)).setScale(DECIMAL_PRECISION, ROUNDING_MODE);

		// Compare rounded values
		return value1.compareTo(value2) == 0;
	}
}
