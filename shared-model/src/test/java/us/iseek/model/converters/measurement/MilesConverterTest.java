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

import java.util.EnumSet;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import us.iseek.model.enums.MeasureUnit;
import us.iseek.model.exception.ConversionException;
import us.iseek.model.gps.Measurement;

/**
 * Tests the <tt>MilesConverter</tt>.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class MilesConverterTest {

	private Measurement type;
	private MilesConverter milesConverter;

	@Before
	public void setUp() {
		this.milesConverter = new MilesConverter();
	}

	@Test
	public void testThatConvertToMilesFromMilesReturnsTheSameValue() throws ConversionException {

		// Get test data
		Double value = Double.valueOf(1234.56d);
		this.type = new Measurement(value, MeasureUnit.MILES);

		// Test entity
		Assert.assertEquals("Converting from miles to miles should return original value.", value, this.milesConverter
				.convertTo(this.type, MeasureUnit.MILES).getMeasurement());
	}

	@Test
	public void testThatConvertToMilesFromMilesReturnsAUnitOfMeasurementOfMiles() throws ConversionException {

		// Get test data
		Double value = Double.valueOf(1234.56d);
		this.type = new Measurement(value, MeasureUnit.MILES);

		// Test entity
		Assert.assertEquals("Converting to miles should return unit of measurement of miles.", MeasureUnit.MILES,
				this.milesConverter.convertTo(this.type, MeasureUnit.MILES).getUnitOfMeasurement());
	}

	@Test
	public void testThatConvertToKilometersFromMilesReturnsValueInKilometers() throws ConversionException {

		// Get test data
		Double value = Double.valueOf(1d);
		this.type = new Measurement(value, MeasureUnit.MILES);

		// Define expected behavior
		Double expectedValue = Double.valueOf(1.60934d);

		// Test entity
		Assert.assertEquals("Converting from miles to kilometers should return value in kilometers.", expectedValue,
				this.milesConverter.convertTo(this.type, MeasureUnit.KILOMETERS).getMeasurement());
	}

	@Test
	public void testThatConvertToKilometersFromMilesReturnsUnitOfMeasurementOfMiles() throws ConversionException {

		// Get test data
		Double value = Double.valueOf(1d);
		this.type = new Measurement(value, MeasureUnit.MILES);

		// Test entity
		Assert.assertEquals("Converting to miles should return unit of measurement of miles.", MeasureUnit.MILES,
				this.milesConverter.convertTo(this.type, MeasureUnit.MILES).getUnitOfMeasurement());
	}

	@Test
	public void testThatConvertToMetersFromMilesReturnsValueInMeters() throws ConversionException {

		// Get test data
		Double value = Double.valueOf(1d);
		this.type = new Measurement(value, MeasureUnit.MILES);

		// Define expected behavior
		Double expectedValue = Double.valueOf(1609.34d);

		// Test entity
		Assert.assertEquals("Converting from miles to meters should return value in meters.", expectedValue,
				this.milesConverter.convertTo(this.type, MeasureUnit.METERS).getMeasurement());
	}

	@Test
	public void testThatConvertToMetersFromMilesReturnsUnitOfMeasurementOfMiles() throws ConversionException {

		// Get test data
		Double value = Double.valueOf(1d);
		this.type = new Measurement(value, MeasureUnit.MILES);

		// Test entity
		Assert.assertEquals("Converting to miles should return unit of measurement of miles.", MeasureUnit.MILES,
				this.milesConverter.convertTo(this.type, MeasureUnit.MILES).getUnitOfMeasurement());
	}

	@Test
	public void testThatConvertToFeetFromMilesReturnsValueInFeet() throws ConversionException {

		// Get test data
		Double value = Double.valueOf(1d);
		this.type = new Measurement(value, MeasureUnit.MILES);

		// Define expected behavior
		Double expectedValue = Double.valueOf(5280d);

		// Test entity
		Assert.assertEquals("Converting from miles to feet should return value in feet.", expectedValue,
				this.milesConverter.convertTo(this.type, MeasureUnit.FEET).getMeasurement());
	}

	@Test
	public void testThatConvertToFeetFromMilesReturnsUnitOfMeasurementOfMiles() throws ConversionException {

		// Get test data
		Double value = Double.valueOf(1d);
		this.type = new Measurement(value, MeasureUnit.MILES);

		// Test entity
		Assert.assertEquals("Converting to miles should return unit of measurement of miles.", MeasureUnit.MILES,
				this.milesConverter.convertTo(this.type, MeasureUnit.MILES).getUnitOfMeasurement());
	}

	@Test(expected = ConversionException.class)
	public void testThatConvertToThrowsConversionExceptionIfTryingToConvertFromKilometers() throws ConversionException {

		// Get test data
		this.type = new Measurement(Double.valueOf(1234.56d), MeasureUnit.KILOMETERS);

		// Test entity
		this.milesConverter.convertTo(this.type, MeasureUnit.FEET);
	}

	@Test(expected = ConversionException.class)
	public void testThatConvertToThrowsConversionExceptionIfTryingToConvertFromFeet() throws ConversionException {

		// Get test data
		this.type = new Measurement(Double.valueOf(1234.56d), MeasureUnit.FEET);

		// Test entity
		this.milesConverter.convertTo(this.type, MeasureUnit.METERS);
	}

	@Test(expected = ConversionException.class)
	public void testThatConvertToThrowsConversionExceptionIfTryingToConvertFromMeters() throws ConversionException {

		// Get test data
		this.type = new Measurement(Double.valueOf(1234.56d), MeasureUnit.METERS);

		// Test entity
		this.milesConverter.convertTo(this.type, MeasureUnit.MILES);
	}

	@Test(expected = ConversionException.class)
	public void testThatConvertToThrowsConversionExceptionIfTryingToConvertToPounds() throws ConversionException {

		// Get test data
		this.type = new Measurement(Double.valueOf(1234.56d), MeasureUnit.MILES);

		// Test entity
		this.milesConverter.convertTo(this.type, MeasureUnit.POUNDS);
	}

	@Test(expected = ConversionException.class)
	public void testThatConvertToThrowsConversionExceptionIfTryingToConvertToKilograms() throws ConversionException {

		// Get test data
		this.type = new Measurement(Double.valueOf(1234.56d), MeasureUnit.MILES);

		// Test entity
		this.milesConverter.convertTo(this.type, MeasureUnit.KILOGRAMS);
	}

	@Test
	public void testThatCanConvertToReturnsTrueForAllMilesConvertibles() {
		// Test all miles convertibles
		List<MeasureUnit> milesConvertibles = MeasureUnit.MILES.getConvertibles();
		for (MeasureUnit milesConvertible : milesConvertibles) {
			Assert.assertTrue("Miles converter should be able to convert from " + milesConvertible.toSymbol(),
					this.milesConverter.canConvertTo(milesConvertible));
		}
	}

	@Test
	public void testThatCanConvertToReturnsFalseForNonMilesConvertibles() {
		// Get miles convertibles
		List<MeasureUnit> milesConvertibles = MeasureUnit.MILES.getConvertibles();

		// Test all convertibles
		for (MeasureUnit measureUnit : EnumSet.allOf(MeasureUnit.class)) {
			// Test non convertibles
			if (!milesConvertibles.contains(measureUnit)) {
				Assert.assertFalse("Miles converter should not be able to convert from " + measureUnit.toSymbol(),
						this.milesConverter.canConvertTo(measureUnit));
			}
		}
	}
}
