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

import org.easymock.EasyMock;
import org.hamcrest.core.StringContains;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import us.iseek.model.converters.Converter;
import us.iseek.model.converters.measurement.MeasurementConverterFactory;
import us.iseek.model.converters.measurement.MilesConverter;
import us.iseek.model.enums.Direction;
import us.iseek.model.enums.MeasureUnit;
import us.iseek.model.exception.ConversionException;

/**
 * Tests the <tt>Location</tt>.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class LocationTest {

	private static final Double LATITUDE = Double.valueOf(42.359368d);
	private static final Double LONGITUDE = Double.valueOf(-71.094208d);
	private Location location;

	private Converter<Measurement, MeasureUnit> converter;
	private MeasurementConverterFactory measurementConverterFactory;

	@Before
	public void setUp() throws ConversionException {
		// Create supporting entities
		this.converter = new MilesConverter();

		// Create mocks
		this.measurementConverterFactory = EasyMock.createNiceMock(MeasurementConverterFactory.class);
		EasyMock.expect(this.measurementConverterFactory.getConverter(EasyMock.anyObject(MeasureUnit.class)))
				.andReturn(this.converter).anyTimes();
		EasyMock.replay(this.measurementConverterFactory);

		// Create test entity
		this.location = new Location(LATITUDE, LONGITUDE);
	}

	@Test
	public void testThatGetRadialAreaGetsRadialAreaAroundLocationProvided() throws ConversionException {
		// Set up entity
		this.location.setMeasurementConverterFactory(this.measurementConverterFactory);

		// Set expectations
		Area expectedArea = new Area(new Location(42.3696012235d, -71.1080600264d), new Location(42.3491331097d,
				-71.0803604850d));

		// Test entity
		Area radialArea = this.location.getRadialArea(Double.valueOf(1d), MeasureUnit.MILES);
		Assert.assertEquals("GetRadialArea should get correct area around location provided.", expectedArea, radialArea);
	}

	@Test(expected = ConversionException.class)
	public void testThatGetRadialAreaThrowsConversionExceptionIfMeasurementConverterFactoryIsNotSet()
			throws ConversionException {
		this.location.getRadialArea(Double.valueOf(1d), MeasureUnit.MILES);
	}

	@Test
	public void testThatMoveZeroMilesReturnsTheSameLocation() throws ConversionException {

		// Set up entity
		this.location.setMeasurementConverterFactory(this.measurementConverterFactory);

		// Test entity
		Location newLocation = this.location.move(Double.valueOf(0d), MeasureUnit.MILES, Direction.EAST);
		Assert.assertEquals("Moving zero miles should return same location as original location.", this.location,
				newLocation);
	}

	@Test
	public void testThatMoveWestTwoHundredMetersReturnsCorrectLocation() throws ConversionException {

		// Set up entity
		this.location.setMeasurementConverterFactory(this.measurementConverterFactory);

		// Set expectations
		Location expectedLocation = new Location(42.3593678273d, -71.1005122782d);

		// Test entity
		Location newLocation = this.location.move(Double.valueOf(0.321868d), MeasureUnit.MILES, Direction.WEST);
		Assert.assertEquals("Moving 200 m to the West should return new location.", expectedLocation, newLocation);
	}

	@Test(expected = ConversionException.class)
	public void testThatMoveThrowsConversionExceptionIfMeasurementConverterFactoryIsNotSet() throws ConversionException {
		this.location.move(Double.valueOf(1d), MeasureUnit.MILES, Direction.EAST);
	}

	@Test
	public void testThatEqualsReturnsTrueIfObjectsAreIdentical() {
		Assert.assertTrue("Equals should be reflexive.", this.location.equals(this.location));
	}

	@Test
	public void testThatEqualsReturnsTrueIfObjectsHaveSameAttributes() {
		this.location = new Location(Double.valueOf(1.23d), Double.valueOf(2.34d));
		Location other = new Location(Double.valueOf(1.23d), Double.valueOf(2.34d));
		Assert.assertTrue("Equals should return true if two locations have the same attributes.",
				this.location.equals(other));
	}

	@Test
	public void testThatEqualsReturnsTrueIfObjectsHaveEqualAttributes() {
		this.location = new Location(new Double(1.23d), new Double(2.34d));
		Location other = new Location(new Double(1.23d), new Double(2.34d));
		Assert.assertTrue("Equals should return true if two locations have the same attributes.",
				this.location.equals(other));
	}

	@Test
	public void testThatEqualsIsSymmetric() {
		this.location = new Location(new Double(1.23d), new Double(2.34d));
		Location locationWithSameAttributes = new Location(new Double(1.23d), new Double(2.34d));
		Assert.assertEquals("Equals should be symmetric.", this.location.equals(locationWithSameAttributes),
				locationWithSameAttributes.equals(this.location));

		Location locationWithDifferentAttributes = new Location(new Double(2.34d), new Double(3.45d));
		Assert.assertEquals("Equals should be symmetric.", this.location.equals(locationWithDifferentAttributes),
				locationWithDifferentAttributes.equals(this.location));
	}

	@Test
	public void testThatEqualsIsConsistent() {
		this.location = new Location(new Double(1.23d), new Double(2.34d));
		Location locationWithSameAttributes = new Location(new Double(1.23d), new Double(2.34d));
		Assert.assertEquals("Equals should be consistent.", this.location.equals(locationWithSameAttributes),
				locationWithSameAttributes.equals(this.location));

		Location locationWithDifferentAttributes = new Location(new Double(2.34d), new Double(3.45d));
		Assert.assertEquals("Equals should be consistent.", this.location.equals(locationWithDifferentAttributes),
				locationWithDifferentAttributes.equals(this.location));
	}

	@Test
	public void testThatEqualsIsTransitive() {
		this.location = new Location(new Double(1.23d), new Double(2.34d));
		Location firstLocation = new Location(new Double(1.23d), new Double(2.34d));
		Location secondLocation = new Location(new Double(1.23d), new Double(2.34d));

		Assert.assertEquals("Equals should be transitive.",
				this.location.equals(firstLocation) && firstLocation.equals(secondLocation),
				this.location.equals(secondLocation));
	}

	@Test
	public void testThatEqualsReturnsFalseIfOtherObjectIsNull() {
		Assert.assertFalse("Equals should return false if other object is null.", this.location.equals(null));
	}

	@Test
	public void testThatEqualsReturnsFalseIfOtherObjectIsNotALocation() {
		Assert.assertFalse("Equals should return null if other object is not a location.",
				this.location.equals(new Object()));
	}

	@Test
	public void testThatEqualsReturnsTrueIfBothLocationsHaveNullLatitude() {
		this.location = new Location(null, LONGITUDE);
		Location other = new Location(null, LONGITUDE);
		Assert.assertTrue("Equals should return true if two locations have null Latitude.", this.location.equals(other));
	}

	@Test
	public void testThatEqualsReturnsTrueIfBothLocationsHaveNullLongitude() {
		this.location = new Location(LATITUDE, null);
		Location other = new Location(LATITUDE, null);
		Assert.assertTrue("Equals should return true if two locations have null Longitude.",
				this.location.equals(other));
	}

	@Test
	public void testThatEqualsReturnsFalseIfLatitudeIsDifferent() {
		this.location = new Location(LATITUDE, LONGITUDE);
		Location other = new Location(Double.valueOf(12.12345d), LONGITUDE);
		Assert.assertFalse("Equals should return false if two locations have different Latitude.",
				this.location.equals(other));
	}

	@Test
	public void testThatEqualsReturnsFalseIfLongitudeIsDifferent() {
		this.location = new Location(LATITUDE, LONGITUDE);
		Location other = new Location(LATITUDE, Double.valueOf(12.12345d));
		Assert.assertFalse("Equals should return false if two locations have different Longitude.",
				this.location.equals(other));
	}

	@Test
	public void testThatEqualsReturnsFalseIfLatitudeIsNullAndOtherIsNot() {
		this.location = new Location(null, LONGITUDE);
		Location other = new Location(LATITUDE, LONGITUDE);
		Assert.assertFalse("Equals should return false if two locations have different Latitude.",
				this.location.equals(other));
	}

	@Test
	public void testThatEqualsReturnsFalseIfLongitudeIsNullAndOtherIsNot() {
		this.location = new Location(LATITUDE, null);
		Location other = new Location(LATITUDE, LONGITUDE);
		Assert.assertFalse("Equals should return false if two locations have different Longitude.",
				this.location.equals(other));
	}

	@Test
	public void testThatEqualsReturnsFalseIfLatitudeIsNotNullAndOtherIs() {
		this.location = new Location(LATITUDE, LONGITUDE);
		Location other = new Location(null, LONGITUDE);
		Assert.assertFalse("Equals should return false if two locations have different Latitude.",
				this.location.equals(other));
	}

	@Test
	public void testThatEqualsReturnsFalseIfLongitudeIsNotNullAndOtherIs() {
		this.location = new Location(LATITUDE, LONGITUDE);
		Location other = new Location(LATITUDE, null);
		Assert.assertFalse("Equals should return false if two locations have different Longitude.",
				this.location.equals(other));
	}

	@Test
	public void testThatHashCodeDoesNotThrowNullPointerExeptionIfLatitudeIsNull() {
		this.location = new Location(null, LONGITUDE);
		Assert.assertNotNull("HashCode should not throw null pointer exception if Latitude is null.",
				this.location.hashCode());
	}

	@Test
	public void testThatHashCodeDoesNotThrowNullPointerExeptionIfLongitudeIsNull() {
		this.location = new Location(LATITUDE, null);
		Assert.assertNotNull("HashCode should not throw null pointer exception if Longitude is null.",
				this.location.hashCode());
	}

	@Test
	public void testThatHashCodeIsEqualIfObjectsAreEqual() {
		this.location = new Location(LATITUDE, LONGITUDE);
		Location locationWithSameAttributes = new Location(LATITUDE, LONGITUDE);
		Assert.assertEquals("HashCode should be equal if objects are equal.",
				this.location.equals(locationWithSameAttributes),
				this.location.hashCode() == locationWithSameAttributes.hashCode());
	}

	@Test
	public void testThatGetLatitudeReturnsTheLatitudeFromConstructor() {
		// Create mock
		Double latitude = Double.valueOf(85.12512d);
		Double longitude = Double.valueOf(12341.12d);

		// Set value
		this.location = new Location(latitude, longitude);

		// Verify value
		Assert.assertEquals("GetLatitude should return the latitude set in the constructor.", latitude,
				this.location.getLatitude());
	}

	@Test
	public void testThatGetLatitudeReturnsTheLatitudeThatWasSet() {
		// Create mock
		Double latitude = Double.valueOf(85.12512d);

		// Set value
		this.location = new Location();
		this.location.setLatitude(latitude);

		// Verify value
		Assert.assertEquals("GetLatitude should return the latitude that was set.", latitude,
				this.location.getLatitude());
	}

	@Test
	public void testThatGetLongitudeReturnsTheLongitudeFromConstructor() {
		// Create mock
		Double latitude = Double.valueOf(85.12512d);
		Double longitude = Double.valueOf(12341.12d);

		// Set value
		this.location = new Location(latitude, longitude);

		// Verify value
		Assert.assertEquals("GetLongitude should return the longitude set in the constructor.", longitude,
				this.location.getLongitude());
	}

	@Test
	public void testThatGetLongitudeReturnsTheLongitudeThatWasSet() {
		// Create mock
		Double longitude = Double.valueOf(12341.12d);

		// Set value
		this.location = new Location();
		this.location.setLongitude(longitude);

		// Verify value
		Assert.assertEquals("GetLongitude should return the longitude that was set.", longitude,
				this.location.getLongitude());
	}

	@Test
	public void testThatGetMeasurementConverterFactoryReturnsTheMeasurementConverterFactoryThatWasSet() {
		// Create mock
		MeasurementConverterFactory measurementConverterFactory = EasyMock
				.createMock(MeasurementConverterFactory.class);

		// Set value
		this.location.setMeasurementConverterFactory(measurementConverterFactory);

		// Verify value
		Assert.assertEquals(
				"GetMeasurementConverterFactory should return the measurementConverterFactory that was set.",
				measurementConverterFactory, this.location.getMeasurementConverterFactory());
	}

	@Test
	public void testThatToStringContainsTheLatitudeThatWasSet() {
		// Create mock
		Double latitude = Double.valueOf(-91.31234d);

		// Set value
		this.location.setLatitude(latitude);

		// Verify value
		Assert.assertThat("ToString should contain the latitude that was set.", this.location.toString(),
				StringContains.containsString(String.valueOf(latitude)));
	}

	@Test
	public void testThatToStringContainsTheLongitudeThatWasSet() {
		// Create mock
		Double longitude = Double.valueOf(18234.12d);
		// Set value
		this.location.setLongitude(longitude);

		// Verify value
		Assert.assertThat("ToString should contain the longitude that was set.", this.location.toString(),
				StringContains.containsString(String.valueOf(longitude)));
	}
}
