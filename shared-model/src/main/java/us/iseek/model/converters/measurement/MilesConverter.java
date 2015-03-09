/**
 * Copyright (C) 2015 iSeek, Inc. 
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of iSeek, Inc.
 * You shall not disclose such confidential information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with 
 * iSeek, Inc.
 */
package us.iseek.model.converters.measurement;

import java.util.List;

import us.iseek.model.constants.GeoConstants;
import us.iseek.model.converters.Converter;
import us.iseek.model.enums.MeasureUnit;
import us.iseek.model.exception.ConversionException;
import us.iseek.model.gps.Measurement;

/**
 * Converts miles to other units of measurement.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class MilesConverter implements Converter<Measurement, MeasureUnit> {

	/**
	 * Converts a measurement in miles to the new unit of measurement.
	 * 
	 * @param type
	 *            - The measurement in miles.
	 * @param unit
	 *            - The unit that the new measurement should have.
	 * @return A converted measurement with the specified unit.
	 * @throws ConversionException
	 *             - if there is a problem in the conversion or this is an
	 *             unsupported conversion as defined by <tt>canConvertTo</tt>
	 *             returning <tt>false</tt>.
	 */
	public Measurement convertTo(Measurement type, MeasureUnit unit) throws ConversionException {

		// Determine if the type can be converted
		if (MeasureUnit.MILES != type.getUnitOfMeasurement()) {
			throw new ConversionException("Cannot convert from " + type.getUnitOfMeasurement());
		} else if (!this.canConvertTo(unit)) {
			throw new ConversionException("Unsupported conversion type.");
		}

		// Get measurement from type
		Double measurement = type.getMeasurement();

		// Convert units
		switch (unit) {
		case KILOMETERS:
			return new Measurement(
					((measurement * GeoConstants.METERS_IN_A_MILE) / GeoConstants.METERS_IN_A_KILOMETER),
					MeasureUnit.KILOMETERS);
		case METERS:
			return new Measurement((measurement * GeoConstants.METERS_IN_A_MILE), MeasureUnit.METERS);
		case FEET:
			return new Measurement((measurement * GeoConstants.FEET_IN_A_MILE), MeasureUnit.FEET);
		default:
			// Miles
			return type;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean canConvertTo(MeasureUnit unit) {
		List<MeasureUnit> milesConvertibles = MeasureUnit.MILES.getConvertibles();
		return milesConvertibles.contains(unit);
	}
}
