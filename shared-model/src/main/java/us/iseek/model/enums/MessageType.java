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
 * Defines the type of message
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public enum MessageType {
	PRIVATE("Private"), PUBLIC("Public");

	private String symbol;

	/**
	 * Creates a enum constant.
	 * 
	 * @param symbol
	 *            - The symbol for this instance
	 */
	MessageType(String symbol) {
		this.symbol = symbol;
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
	 * Retrieves an enum constant based on the symbolic representation provided.
	 * 
	 * @param symbol
	 *            - The symbolic representation of the enum constant.
	 * @return The enum constant corresponding to the symbolic representation of
	 *         the constant, or null if no constant matches the symbol provided.
	 */
	public static MessageType fromSymbol(Object symbol) {
		// Iterate over all constants to see if any matches the symbol provided
		for (MessageType constant : EnumSet.allOf(MessageType.class)) {
			if (constant.toSymbol().equals(symbol)) {
				return constant;
			}
		}

		// No constant matched the symbol provided
		return null;
	}
}
