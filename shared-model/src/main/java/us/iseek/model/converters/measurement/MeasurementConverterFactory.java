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

import java.util.Map;

import us.iseek.model.converters.Converter;
import us.iseek.model.enums.MeasureUnit;
import us.iseek.model.gps.Measurement;

/**
 * A factory of converters for different units of measure.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class MeasurementConverterFactory {

	private Map<MeasureUnit, Converter<Measurement, MeasureUnit>> converterMap;

	/**
	 * Retrieves the converter for the unit of measurement specified.
	 * 
	 * @param measureUnit
	 *            - The unit of measurement for which the appropriate converter
	 *            should be retrieved.
	 * @return A converter for the unit of measurement specified.
	 */
	public Converter<Measurement, MeasureUnit> getConverter(MeasureUnit measureUnit) {
		return this.converterMap.get(measureUnit);
	}

	/**
	 * @return the converterMap
	 */
	public Map<MeasureUnit, Converter<Measurement, MeasureUnit>> getConverterMap() {
		return this.converterMap;
	}

	/**
	 * @param converterMap
	 *            - The converterMap to set
	 */
	public void setConverterMap(Map<MeasureUnit, Converter<Measurement, MeasureUnit>> converterMap) {
		this.converterMap = converterMap;
	}
}
