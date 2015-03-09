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

import us.iseek.model.constants.GeoConstants;
import us.iseek.model.converters.Converter;
import us.iseek.model.converters.measurement.MeasurementConverterFactory;
import us.iseek.model.enums.Direction;
import us.iseek.model.enums.MeasureUnit;
import us.iseek.model.exception.ConversionException;
import us.iseek.model.utils.MathUtils;

/**
 * This class represents a geographic location.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class Location {

	private Double latitude;
	private Double longitude;

	private MeasurementConverterFactory measurementConverterFactory;

	/**
	 * Creates a new instance of this.
	 */
	public Location() {
		// Default constructor
	}

	/**
	 * Creates a new geographic location defined by a certain latitude and
	 * longitude.
	 * 
	 * @param latitude
	 *            - The location's latitude
	 * @param longitude
	 *            - The location's longitude
	 */
	public Location(Double latitude, Double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * Gets the area around this location.
	 * 
	 * @param radius
	 *            - The radius of the imaginary circle around this location.
	 * @param unit
	 *            - The unit of measurement for the radius.
	 * @return A rectangular area fully enclosed in a circle defined by the
	 *         radius provided. All corners of the rectangle touch the
	 *         circumference of the defined circle.
	 * @throws ConversionException
	 *             - if there is a problem converting measurement units.
	 */
	public Area getRadialArea(Double radius, MeasureUnit unit) throws ConversionException {

		// Get start and end points for the rectangle
		Location startPoint = this.move(radius, unit, Direction.NORTHWEST);
		Location endPoint = this.move(radius, unit, Direction.SOUTHEAST);

		// Return defined area
		return new Area(startPoint, endPoint);
	}

	/**
	 * Gets a copy of this location moved by the specified amount.
	 * 
	 * @param distance
	 *            - How far the new location should be from this location.
	 * @param unit
	 *            - The unit of measurement for the distance.
	 * @param bearing
	 *            - The bearing to which the new location should move.
	 * @return A new location that would be equal to moving this location by the
	 *         distance and unit specified along the given bearing.
	 * @throws ConversionException
	 *             - if there is a problem converting measurement units.
	 */
	public Location move(Double distance, MeasureUnit unit, Direction bearing) throws ConversionException {

		// Determine if conversion is enabled
		if (this.measurementConverterFactory == null) {
			throw new ConversionException("Unit of measurement conversion is not enabled.");
		}

		// Convert distance to meters
		Measurement distanceToMove = new Measurement(distance, unit);
		Converter<Measurement, MeasureUnit> unitsConverter = this.measurementConverterFactory.getConverter(unit);
		Measurement distanceInMeters = unitsConverter.convertTo(distanceToMove, MeasureUnit.METERS);

		// Convert distance to angular distance in radians
		double delta = distanceInMeters.getMeasurement() / GeoConstants.EARTH_RADIUS_IN_METERS;
		double theta = MathUtils.toRadians(Double.valueOf(bearing.getBearing()));

		// Get starting point in radians
		double phi1 = MathUtils.toRadians(this.getLatitude());
		double lambda1 = MathUtils.toRadians(this.getLongitude());

		// Calculate end point in radians
		double phi2 = Math.asin(Math.sin(phi1) * Math.cos(delta) + Math.cos(phi1) * Math.sin(delta) * Math.cos(theta));
		double lambda2 = lambda1
				+ Math.atan2(Math.sin(theta) * Math.sin(delta) * Math.cos(phi1), Math.cos(delta) - Math.sin(phi1)
						* Math.sin(phi2));

		// Normalize longitude to -180..+180 degrees
		lambda2 = (lambda2 + 3 * Math.PI) % (2 * Math.PI) - Math.PI;

		// Return new location
		return new Location(MathUtils.toSignedDegrees(phi2), MathUtils.toSignedDegrees(lambda2));
	}

	/**
	 * @return the latitude
	 */
	public Double getLatitude() {
		return this.latitude;
	}

	/**
	 * @param latitude
	 *            - the latitude to set
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public Double getLongitude() {
		return this.longitude;
	}

	/**
	 * @param longitude
	 *            - the longitude to set
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the measurementConverterFactory
	 */
	public MeasurementConverterFactory getMeasurementConverterFactory() {
		return this.measurementConverterFactory;
	}

	/**
	 * @param measurementConverterFactory
	 *            - The measurementConverterFactory to set
	 */
	public void setMeasurementConverterFactory(MeasurementConverterFactory measurementConverterFactory) {
		this.measurementConverterFactory = measurementConverterFactory;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return (13 * (this.latitude == null ? 0 : this.latitude.hashCode()))
				+ (19 * (this.longitude == null ? 0 : this.longitude.hashCode()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		// Ensure object is not null and is an instance of this class
		if (!(obj instanceof Location)) {
			return false;
		}

		// Compare latitude and longitude
		Location other = (Location) obj;
		boolean isLatitudeEqual = this.areEqual(this.latitude, other.latitude);
		boolean isLongitudeEqual = this.areEqual(this.longitude, other.longitude);
		return isLatitudeEqual && isLongitudeEqual;
	}

	/**
	 * If both values are null, returns true. If one of the values is null,
	 * returns false. Otherwise, compares the two values provided to determine
	 * if they are equal up to <tt>MathUtils.DECIMAL_PRECISION</tt>.
	 * 
	 * @param d1
	 *            - The first value to compare.
	 * @param d2
	 *            - The second value to compare.
	 * @return true if and only if d1 == d2 or d1.equals(d2) if both d1 and d2
	 *         are not null, after rounding d1 and d2 to
	 *         <tt>MathUtils.DECIMAL_PRECISION</tt> using
	 *         <tt>MathUtils.ROUNDING_MODE</tt> as the rounding algorithm.
	 */
	private boolean areEqual(Double d1, Double d2) {
		if (d1 == d2) {
			return true;
		} else if (d1 == null || d2 == null) {
			return false;
		} else {
			return MathUtils.compare(d1, d2);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "{Location: latitude=" + this.latitude + ", longitude=" + this.longitude + "}";
	}
}
