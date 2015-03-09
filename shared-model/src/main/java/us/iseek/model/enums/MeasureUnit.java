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

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Enumerates the different units of measurement supported by the system.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public enum MeasureUnit {
	POUNDS("LB", "LB,KG"), KILOGRAMS("KG", "KG,LB"), FEET("FT", "FT,MI,M,KM"), MILES("MI", "MI,KM,M,FT"), METERS("M",
			"M,MI,KM,FT"), KILOMETERS("KM", "KM,MI,M,FT");

	private String symbol;
	private String convertibles;

	/**
	 * Creates a enum constant.
	 * 
	 * @param symbol
	 *            - The symbol for this instance
	 * @param convertibles
	 *            - The units of measurement to which this unit of measurement
	 *            can be converted
	 */
	MeasureUnit(String symbol, String convertibles) {
		this.symbol = symbol;
		this.convertibles = convertibles;
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
	 * Retrieves all the units of measurement to which this unit of measurement
	 * can be converted to.
	 * 
	 * @return All the units of measurement to which this unit of measurement
	 *         can be converted to.
	 */
	public List<MeasureUnit> getConvertibles() {
		// Convert string to list
		List<MeasureUnit> convertiblesList = new ArrayList<MeasureUnit>();
		StringTokenizer tokenizer = new StringTokenizer(this.convertibles, ",");

		// Add each convertible to list
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			MeasureUnit convertible = MeasureUnit.fromSymbol(token);
			convertiblesList.add(convertible);
		}

		// Return all convertibles
		return convertiblesList;
	}

	/**
	 * Retrieves an enum constant based on the symbolic representation provided.
	 * 
	 * @param symbol
	 *            - The symbolic representation of the enum constant.
	 * @return The enum constant corresponding to the symbolic representation of
	 *         the constant, or null if no constant matches the symbol provided.
	 */
	public static MeasureUnit fromSymbol(Object symbol) {
		// Iterate over all constants to see if any matches the symbol provided
		for (MeasureUnit constant : EnumSet.allOf(MeasureUnit.class)) {
			if (constant.toSymbol().equals(symbol)) {
				return constant;
			}
		}

		// No constant matched the symbol provided
		return null;
	}
}
