/**
 * Copyright (C) 2015 iSeek, Inc. 
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of iSeek, Inc.
 * You shall not disclose such confidential information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with 
 * iSeek, Inc.
 */
package us.iseek.model.gps;

import java.util.List;

import us.iseek.model.enums.MeasureUnit;

/**
 * Defines a measurement.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class Measurement {

	private Double measurement;
	private MeasureUnit unitOfMeasurement;

	/**
	 * Creates a new instance of this measurement specifying the measurement and
	 * the unit of measurement.
	 * 
	 * @param measurement
	 *            - The measurement
	 * @param unitOfMeasurement
	 *            - The unit of measurement
	 */
	public Measurement(Double measurement, MeasureUnit unitOfMeasurement) {
		this.measurement = measurement;
		this.unitOfMeasurement = unitOfMeasurement;
	}

	/**
	 * Determines if this measurement can be converted to the unit of
	 * measurement provided.
	 * 
	 * @param unitOfMeasurement
	 *            - The unit of measurement to which this measurement should be
	 *            converted.
	 * @return true if and only if this measurement can be converted to the unit
	 *         of measurement provided.
	 */
	public boolean canConvertTo(MeasureUnit unitOfMeasurement) {
		// Get the convertibles for this unit of measurement
		List<MeasureUnit> convertibles = this.unitOfMeasurement.getConvertibles();

		// Determine if this unit can be converted to the unit provided
		return convertibles.contains(unitOfMeasurement);
	}

	/**
	 * @return the measurement
	 */
	public Double getMeasurement() {
		return this.measurement;
	}

	/**
	 * @param measurement
	 *            - The measurement to set
	 */
	public void setMeasurement(Double measurement) {
		this.measurement = measurement;
	}

	/**
	 * @return the unitOfMeasurement
	 */
	public MeasureUnit getUnitOfMeasurement() {
		return this.unitOfMeasurement;
	}

	/**
	 * @param unitOfMeasurement
	 *            - The unitOfMeasurement to set
	 */
	public void setUnitOfMeasurement(MeasureUnit unitOfMeasurement) {
		this.unitOfMeasurement = unitOfMeasurement;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "{Measurement: measurement=" + this.measurement + ", unitOfMeasurement=" + this.unitOfMeasurement + "}";
	}
}
