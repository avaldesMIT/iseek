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

import java.util.HashMap;
import java.util.Map;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import us.iseek.model.converters.Converter;
import us.iseek.model.enums.MeasureUnit;
import us.iseek.model.gps.Measurement;

/**
 * Tests the <tt>MeasurementConverterFactory</tt>.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class MeasurementConverterFactoryTest {

	private MeasurementConverterFactory measurementConverterFactory;

	private Map<MeasureUnit, Converter<Measurement, MeasureUnit>> converterMap;

	@Before
	public void setUp() {
		// Create test data
		this.converterMap = new HashMap<MeasureUnit, Converter<Measurement, MeasureUnit>>();

		// Create test entity
		this.measurementConverterFactory = new MeasurementConverterFactory();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testThatGetConverterReturnsTheConverterForTheMeasureUnitFromTheConverterMap() {
		// Create mocks
		Converter<Measurement, MeasureUnit> mockConverter = EasyMock.createNiceMock(Converter.class);

		// Add test data
		MeasureUnit measureUnit = MeasureUnit.FEET;
		this.converterMap.put(measureUnit, mockConverter);

		// Set map
		this.measurementConverterFactory.setConverterMap(this.converterMap);

		// Test entity
		Assert.assertEquals(
				"GetConverter should return the converter for the measure unit provided from the converter map",
				mockConverter, this.measurementConverterFactory.getConverter(measureUnit));
	}

	@Test
	public void testThatGetConverterMapReturnsTheConverterMapThatWasSet() {
		// Add test data
		this.converterMap.put(MeasureUnit.FEET, null);

		// Set value
		this.measurementConverterFactory.setConverterMap(this.converterMap);

		// Verify value
		Assert.assertEquals("GetConverterMap should return the ConverterMap that was set.", this.converterMap,
				this.measurementConverterFactory.getConverterMap());
	}
}
