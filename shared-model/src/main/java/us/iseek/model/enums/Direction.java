/**
 * Copyright (C) 2015 iSeek, Inc. 
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of iSeek, Inc.
 * You shall not disclose such confidential information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with 
 * iSeek, Inc.
 */
package us.iseek.model.enums;

import java.util.EnumSet;

/**
 * Enumerates the different directions of travel
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public enum Direction {
	EAST("E", 90), WEST("W", 270), NORTH("N", 0), SOUTH("S", 180), NORTHEAST("NE", 45), NORTHWEST("NW", 315), SOUTHEAST(
			"SE", 135), SOUTHWEST("SW", 225);

	private String symbol;
	private int bearing;

	/**
	 * Creates a enum constant.
	 * 
	 * @param symbol
	 *            - The symbol for this instance
	 * @param bearing
	 *            - The bearing for this instance
	 */
	Direction(String symbol, int bearing) {
		this.symbol = symbol;
		this.bearing = bearing;
	}

	/**
	 * Retrieves the symbolic representation of this enum.
	 * 
	 * @return the symbol.
	 */
	public String toSymbol() {
		return this.symbol;
	}

	/**
	 * @return the bearing
	 */
	public int getBearing() {
		return this.bearing;
	}

	/**
	 * Retrieves an enum constant based on the symbolic representation provided.
	 * 
	 * @param symbol
	 *            - The symbolic representation of the enum constant.
	 * @return The enum constant corresponding to the symbolic representation of
	 *         the constant, or null if no constant matches the symbol provided.
	 */
	public static Direction fromSymbol(Object symbol) {
		// Iterate over all constants to see if any matches the symbol provided
		for (Direction constant : EnumSet.allOf(Direction.class)) {
			if (constant.toSymbol().equals(symbol)) {
				return constant;
			}
		}

		// No constant matched the symbol provided
		return null;
	}
}
