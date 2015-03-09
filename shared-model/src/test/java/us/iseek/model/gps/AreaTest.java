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

/**
 * Tests the <tt>Area</tt>.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class AreaTest {

	private static final Location START_POINT = new Location(new Double(1.23d), new Double(2.34d));
	private static final Location END_POINT = new Location(new Double(3.45d), new Double(4.56d));

	private Area area;

	private Location endPoint;
	private Location startPoint;

	@Before
	public void setUp() {
		// Create mocks
		this.endPoint = EasyMock.createMock(Location.class);
		this.startPoint = EasyMock.createMock(Location.class);

		// Create test entity
		this.area = new Area(this.startPoint, this.endPoint);
	}

	@Test
	public void testThatEqualsReturnsTrueIfObjectsAreIdentical() {
		Assert.assertTrue("Equals should be reflexive.", this.area.equals(this.area));
	}

	@Test
	public void testThatEqualsReturnsTrueIfObjectsHaveSameAttributes() {
		this.area = new Area(new Location(Double.valueOf(1.23d), Double.valueOf(2.34d)), new Location(
				Double.valueOf(3.45d), Double.valueOf(4.56d)));
		Area other = new Area(new Location(Double.valueOf(1.23d), Double.valueOf(2.34d)), new Location(
				Double.valueOf(3.45d), Double.valueOf(4.56d)));
		Assert.assertTrue("Equals should return true if two areas have the same attributes.", this.area.equals(other));
	}

	@Test
	public void testThatEqualsReturnsTrueIfObjectsHaveEqualAttributes() {
		this.area = new Area(new Location(new Double(1.23d), new Double(2.34d)), new Location(new Double(3.45d),
				new Double(4.56d)));
		Area other = new Area(new Location(new Double(1.23d), new Double(2.34d)), new Location(new Double(3.45d),
				new Double(4.56d)));
		Assert.assertTrue("Equals should return true if two areas have the same attributes.", this.area.equals(other));
	}

	@Test
	public void testThatEqualsIsSymmetric() {
		this.area = new Area(new Location(new Double(1.23d), new Double(2.34d)), new Location(new Double(3.45d),
				new Double(4.56d)));
		Area areaWithSameAttributes = new Area(new Location(new Double(1.23d), new Double(2.34d)), new Location(
				new Double(3.45d), new Double(4.56d)));
		Assert.assertEquals("Equals should be symmetric.", this.area.equals(areaWithSameAttributes),
				areaWithSameAttributes.equals(this.area));

		Area areaWithDifferentAttributes = new Area(new Location(new Double(2.34d), new Double(3.45d)), new Location(
				new Double(4.56d), new Double(5.67d)));
		Assert.assertEquals("Equals should be symmetric.", this.area.equals(areaWithDifferentAttributes),
				areaWithDifferentAttributes.equals(this.area));
	}

	@Test
	public void testThatEqualsIsConsistent() {
		this.area = new Area(new Location(new Double(1.23d), new Double(2.34d)), new Location(new Double(3.45d),
				new Double(4.56d)));
		Area areaWithSameAttributes = new Area(new Location(new Double(1.23d), new Double(2.34d)), new Location(
				new Double(3.45d), new Double(4.56d)));
		Assert.assertEquals("Equals should be consistent.", this.area.equals(areaWithSameAttributes),
				areaWithSameAttributes.equals(this.area));

		Area areaWithDifferentAttributes = new Area(new Location(new Double(2.34d), new Double(3.45d)), new Location(
				new Double(4.56d), new Double(5.67d)));
		Assert.assertEquals("Equals should be consistent.", this.area.equals(areaWithDifferentAttributes),
				areaWithDifferentAttributes.equals(this.area));
	}

	@Test
	public void testThatEqualsIsTransitive() {
		this.area = new Area(new Location(new Double(1.23d), new Double(2.34d)), new Location(new Double(3.45d),
				new Double(4.56d)));
		Area firstArea = new Area(new Location(new Double(1.23d), new Double(2.34d)), new Location(new Double(3.45d),
				new Double(4.56d)));
		Area secondArea = new Area(new Location(new Double(1.23d), new Double(2.34d)), new Location(new Double(3.45d),
				new Double(4.56d)));

		Assert.assertEquals("Equals should be transitive.",
				this.area.equals(firstArea) && firstArea.equals(secondArea), this.area.equals(secondArea));
	}

	@Test
	public void testThatEqualsReturnsFalseIfOtherObjectIsNull() {
		Assert.assertFalse("Equals should return false if other object is null.", this.area.equals(null));
	}

	@Test
	public void testThatEqualsReturnsFalseIfOtherObjectIsNotAnArea() {
		Assert.assertFalse("Equals should return null if other object is not an area.", this.area.equals(new Object()));
	}

	@Test
	public void testThatEqualsReturnsTrueIfBothAreasHaveNullStartPoint() {
		this.area = new Area(null, END_POINT);
		Area other = new Area(null, END_POINT);
		Assert.assertTrue("Equals should return true if two areas have null StartPoint.", this.area.equals(other));
	}

	@Test
	public void testThatEqualsReturnsTrueIfBothAreasHaveNullEndPoint() {
		this.area = new Area(START_POINT, null);
		Area other = new Area(START_POINT, null);
		Assert.assertTrue("Equals should return true if two areas have null EndPoint.", this.area.equals(other));
	}

	@Test
	public void testThatEqualsReturnsFalseIfStartPointIsDifferent() {
		this.area = new Area(START_POINT, END_POINT);
		Area other = new Area(new Location(Double.valueOf(18.1234d), Double.valueOf(12.12345d)), END_POINT);
		Assert.assertFalse("Equals should return false if two areas have different StartPoint.",
				this.area.equals(other));
	}

	@Test
	public void testThatEqualsReturnsFalseIfEndPointIsDifferent() {
		this.area = new Area(START_POINT, END_POINT);
		Area other = new Area(START_POINT, new Location(Double.valueOf(18.1234d), Double.valueOf(12.12345d)));
		Assert.assertFalse("Equals should return false if two areas have different EndPoint.", this.area.equals(other));
	}

	@Test
	public void testThatEqualsReturnsFalseIfStartPointIsNullAndOtherIsNot() {
		this.area = new Area(null, END_POINT);
		Area other = new Area(START_POINT, END_POINT);
		Assert.assertFalse("Equals should return false if two areas have different StartPoint.",
				this.area.equals(other));
	}

	@Test
	public void testThatEqualsReturnsFalseIfEndPointIsNullAndOtherIsNot() {
		this.area = new Area(START_POINT, null);
		Area other = new Area(START_POINT, END_POINT);
		Assert.assertFalse("Equals should return false if two areas have different EndPoint.", this.area.equals(other));
	}

	@Test
	public void testThatEqualsReturnsFalseIfStartPointIsNotNullAndOtherIs() {
		this.area = new Area(START_POINT, END_POINT);
		Area other = new Area(null, END_POINT);
		Assert.assertFalse("Equals should return false if two areas have different StartPoint.",
				this.area.equals(other));
	}

	@Test
	public void testThatEqualsReturnsFalseIfEndPointIsNotNullAndOtherIs() {
		this.area = new Area(START_POINT, END_POINT);
		Area other = new Area(START_POINT, null);
		Assert.assertFalse("Equals should return false if two areas have different EndPoint.", this.area.equals(other));
	}

	@Test
	public void testThatHashCodeDoesNotThrowNullPointerExeptionIfStartPointIsNull() {
		this.area = new Area(null, END_POINT);
		Assert.assertNotNull("HashCode should not throw null pointer exception if StartPoint is null.",
				this.area.hashCode());
	}

	@Test
	public void testThatHashCodeDoesNotThrowNullPointerExeptionIfEndPointIsNull() {
		this.area = new Area(START_POINT, null);
		Assert.assertNotNull("HashCode should not throw null pointer exception if EndPoint is null.",
				this.area.hashCode());
	}

	@Test
	public void testThatHashCodeIsEqualIfObjectsAreEqual() {
		this.area = new Area(START_POINT, END_POINT);
		Area areaWithSameAttributes = new Area(START_POINT, END_POINT);
		Assert.assertEquals("HashCode should be equal if objects are equal.", this.area.equals(areaWithSameAttributes),
				this.area.hashCode() == areaWithSameAttributes.hashCode());
	}

	@Test
	public void testThatGetStartPointReturnsStartPointFromConstructor() {
		Assert.assertEquals("GetStartPoint should return the startPoint from constructor.", this.startPoint,
				this.area.getStartPoint());
	}

	@Test
	public void testThatGetEndPointReturnsEndPointFromConstructor() {
		Assert.assertEquals("GetEndPoint should return the endPoint from constructor.", this.endPoint,
				this.area.getEndPoint());
	}

	@Test
	public void testThatGetStartPointReturnsTheStartPointThatWasSet() {
		// Create mock
		Location anotherStartPoint = EasyMock.createMock(Location.class);

		// Set value
		this.area.setStartPoint(anotherStartPoint);

		// Verify value
		Assert.assertEquals("GetStartPoint should return the startPoint that was set.", anotherStartPoint,
				this.area.getStartPoint());
		Assert.assertFalse("Constructor startPoint should have been replaced",
				this.startPoint.equals(this.area.getStartPoint()));
	}

	@Test
	public void testThatGetEndPointReturnsTheEndPointThatWasSet() {
		// Create mock
		Location anotherEndPoint = EasyMock.createMock(Location.class);

		// Set value
		this.area.setEndPoint(anotherEndPoint);

		// Verify value
		Assert.assertEquals("GetEndPoint should return the endPoint that was set.", anotherEndPoint,
				this.area.getEndPoint());
		Assert.assertFalse("Constructor endPoint should have been replaced",
				this.endPoint.equals(this.area.getEndPoint()));
	}

	@Test
	public void testThatToStringContainsTheStartPointThatWasSet() {
		// Create mock
		Location startPoint = EasyMock.createMock(Location.class);

		// Set value
		this.area.setStartPoint(startPoint);

		// Verify value
		Assert.assertThat("ToString should contain the startPoint that was set.", this.area.toString(),
				StringContains.containsString(String.valueOf(startPoint)));
	}

	@Test
	public void testThatToStringContainsTheEndPointThatWasSet() {
		// Create mock
		Location endPoint = EasyMock.createMock(Location.class);

		// Set value
		this.area.setEndPoint(endPoint);

		// Verify value
		Assert.assertThat("ToString should contain the endPoint that was set.", this.area.toString(),
				StringContains.containsString(String.valueOf(endPoint)));
	}
}
