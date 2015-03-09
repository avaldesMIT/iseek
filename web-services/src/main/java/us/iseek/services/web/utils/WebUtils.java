/**
 * Copyright (C) 2015 iSeek, Inc. 
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of iSeek, Inc.
 * You shall not disclose such confidential information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with 
 * iSeek, Inc.
 */
package us.iseek.services.web.utils;

/**
 * Provides web utility methods
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public final class WebUtils {

	/**
	 * Utility class constructor
	 */
	private WebUtils() {
		// Hidden constructor
	}

	/**
	 * Returns a string with no special characters.
	 * 
	 * @param string
	 *            - The string to strip of special characters
	 * @return A string s2 such that string.equals(s2) after string has been
	 *         stripped off any special characters.
	 */
	public static String getSafeString(String string) {
		return string.replaceAll("[^a-zA-Z0-9 ]", "");
	}
}
