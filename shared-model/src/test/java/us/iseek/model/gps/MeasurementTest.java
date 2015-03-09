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

import java.util.EnumSet;

import org.hamcrest.core.StringContains;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import us.iseek.model.enums.MeasureUnit;

/**
 * Tests the <tt>Measurement</tt>.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class MeasurementTest {

	private Measurement measurement;

	private Double value;
	private MeasureUnit unitOfMeasurement;

	@Before
	public void setUp() {
		// Create test data
		this.value = Double.valueOf(1234.56d);
		this.unitOfMeasurement = MeasureUnit.KILOMETERS;

		// Create test entity
		this.measurement = new Measurement(this.value, this.unitOfMeasurement);
	}

	@Test
	public void testThatCanConvertToReturnsTrueIfMeasurementsConvertiblesContainUnitOfMeasurementProvided() {
		// Test all units of measurement
		for (MeasureUnit measureUnit : EnumSet.allOf(MeasureUnit.class)) {
			this.measurement.setUnitOfMeasurement(measureUnit);

			// Test all convertibles
			for (MeasureUnit convertible : measureUnit.getConvertibles()) {
				Assert.assertTrue("CanConvertTo should be able to convert to all convertibles.",
						this.measurement.canConvertTo(convertible));
			}
		}
	}

	@Test
	public void testThatCanConvertToReturnsFalseIfMeasurementsConvertiblesDoesNotContainUnitOfMeasurementProvided() {
		// Set length unit
		this.measurement.setUnitOfMeasurement(MeasureUnit.MILES);

		// Try against weight unit
		Assert.assertFalse("CanConvertTo should return false if unit of measurment is not in convertibles",
				this.measurement.canConvertTo(MeasureUnit.POUNDS));
	}

	@Test
	public void testThatGetMeasurementReturnsMeasurementFromConstructor() {
		Assert.assertEquals("GetMeasurement should return the measurement from constructor.", this.value,
				this.measurement.getMeasurement());
	}

	@Test
	public void testThatGetUnitOfMeasurementReturnsUnitOfMeasurementFromConstructor() {
		Assert.assertEquals("GetUnitOfMeasurement should return the unitOfMeasurement from constructor.",
				this.unitOfMeasurement, this.measurement.getUnitOfMeasurement());
	}

	@Test
	public void testThatGetMeasurementReturnsTheMeasurementThatWasSet() {
		// Create mock
		Double anotherValue = Double.valueOf(5123.12d);

		// Set value
		this.measurement.setMeasurement(anotherValue);

		// Verify value
		Assert.assertEquals("GetMeasurement should return the measurement that was set.", anotherValue,
				this.measurement.getMeasurement());
		Assert.assertFalse("Constructor measurement should have been replaced.",
				this.value.equals(this.measurement.getMeasurement()));
	}

	@Test
	public void testThatGetUnitOfMeasurementReturnsTheUnitOfMeasurementThatWasSet() {
		// Create mock
		MeasureUnit unitOfMeasurement = MeasureUnit.METERS;

		// Set value
		this.measurement.setUnitOfMeasurement(unitOfMeasurement);

		// Verify value
		Assert.assertEquals("GetUnitOfMeasurement should return the unitOfMeasurement that was set.",
				unitOfMeasurement, this.measurement.getUnitOfMeasurement());
		Assert.assertFalse("Constructor unit of measurement should have been replaced.",
				this.unitOfMeasurement.equals(this.measurement.getUnitOfMeasurement()));
	}

	@Test
	public void testThatToStringContainsTheMeasurementThatWasSet() {
		// Create mock
		Double value = Double.valueOf(12341.12d);

		// Set value
		this.measurement.setMeasurement(value);

		// Verify value
		Assert.assertThat("ToString should contain the measurement that was set.", this.measurement.toString(),
				StringContains.containsString(String.valueOf(value)));
	}

	@Test
	public void testThatToStringContainsTheUnitOfMeasurementThatWasSet() {
		// Create mock
		MeasureUnit unitOfMeasurement = MeasureUnit.METERS;

		// Set value
		this.measurement.setUnitOfMeasurement(unitOfMeasurement);

		// Verify value
		Assert.assertThat("ToString should contain the unitOfMeasurement that was set.", this.measurement.toString(),
				StringContains.containsString(String.valueOf(unitOfMeasurement)));
	}
}
