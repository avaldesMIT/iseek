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

/**
 * Defines an area specified by a rectangle running from a start point at the
 * North West corner of the rectangle to an end point at the South East corner
 * of the rectangle.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class Area {

	private Location startPoint;
	private Location endPoint;

	/**
	 * Defines a new instance of this defined by a start point at the North West
	 * corner of an imaginary rectangle that extends to an end point at the
	 * South East corner of that rectangle.
	 * 
	 * @param startPoint
	 *            - The location of the North West corner of the rectangle
	 * @param endPoint
	 *            - The location of the South East corner of the rectangle
	 */
	public Area(Location startPoint, Location endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}

	/**
	 * @return the startPoint
	 */
	public Location getStartPoint() {
		return this.startPoint;
	}

	/**
	 * @param startPoint
	 *            the startPoint to set
	 */
	public void setStartPoint(Location startPoint) {
		this.startPoint = startPoint;
	}

	/**
	 * @return the endPoint
	 */
	public Location getEndPoint() {
		return this.endPoint;
	}

	/**
	 * @param endPoint
	 *            the endPoint to set
	 */
	public void setEndPoint(Location endPoint) {
		this.endPoint = endPoint;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return (13 * (this.startPoint == null ? 0 : this.startPoint.hashCode()))
				+ (19 * (this.endPoint == null ? 0 : this.endPoint.hashCode()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		// Ensure object is not null and is an instance of this class
		if (!(obj instanceof Area)) {
			return false;
		}

		// Compare startPoint and endPoint
		Area other = (Area) obj;
		boolean isStartPointEqual = (this.startPoint == other.startPoint || (this.startPoint != null && this.startPoint
				.equals(other.startPoint)));
		boolean isEndPointEqual = (this.endPoint == other.endPoint || (this.endPoint != null && this.endPoint
				.equals(other.endPoint)));
		return isStartPointEqual && isEndPointEqual;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "{Area: startPoint=" + this.startPoint + ", endPoint=" + this.endPoint + "}";
	}
}
