/**
 * Copyright (C) 2015 iSeek, Inc. 
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of iSeek, Inc.
 * You shall not disclose such confidential information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with 
 * iSeek, Inc.
 */
package us.iseek.model.converters;

import us.iseek.model.exception.ConversionException;

/**
 * Defines a converter from a type of one unit to a type of the unit provided.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public interface Converter<T, U> {

	/**
	 * Converts the given type to a new type with the unit provided.
	 * 
	 * @param type
	 *            - The type to convert.
	 * @param unit
	 *            - The unit that the new type should have.
	 * @return A converted type with the specified unit.
	 * @throws ConversionException
	 *             - if there is a problem in the conversion or this is an
	 *             unsupported conversion as defined by <tt>canConvertTo</tt>
	 *             returning <tt>false</tt>.
	 */
	public T convertTo(T type, U unit) throws ConversionException;

	/**
	 * Determines if the given type can be converted to a new type with the unit
	 * provided.
	 * 
	 * @param unit
	 *            - The unit that the new type should have.
	 * @return true if and only if a type can be converted to a new type with
	 *         the unit provided.
	 */
	public boolean canConvertTo(U unit);
}
