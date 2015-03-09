/**
 * Copyright (C) 2015 iSeek, Inc.
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of iSeek, Inc.
 * You shall not disclose such confidential information and shall use it only
 * in accordance with the terms of the license agreement you entered into with
 * iSeek, Inc.
 */
package us.iseek.model.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the <tt>MathUtils</tt>.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class MathUtilsTest {

	@Test
	public void testThatToRadiansConvertsZeroDegreesToZeroRadians() {
		// Create test data
		Double degrees = Double.valueOf(0d);
		Double expectedRadians = Double.valueOf(0d);

		// Test entity
		Assert.assertEquals("0 degrees should be converted to 0 radians", expectedRadians, MathUtils.toRadians(degrees));
	}

	@Test
	public void testThatToRadiansConverts90DegreesToPIOverTwoRadians() {
		// Create test data
		Double degrees = Double.valueOf(90d);
		Double expectedRadians = Math.PI / 2;

		// Test entity
		Assert.assertEquals("90 degrees should be converted to PI/2 radians", expectedRadians,
				MathUtils.toRadians(degrees));
	}

	@Test
	public void testThatToRadiansConverts180DegreesToPIRadians() {
		// Create test data
		Double degrees = Double.valueOf(180d);
		Double expectedRadians = Math.PI;

		// Test entity
		Assert.assertEquals("180 degrees should be converted to PI radians", expectedRadians,
				MathUtils.toRadians(degrees));
	}

	@Test
	public void testThatToRadiansConverts360DegreesTo2PIRadians() {
		// Create test data
		Double degrees = Double.valueOf(360d);
		Double expectedRadians = Math.PI * 2;

		// Test entity
		Assert.assertEquals("360 degrees should be converted to 2PI radians", expectedRadians,
				MathUtils.toRadians(degrees));
	}

	@Test
	public void testThatToDegreesConvertsZeroDegreesToZeroDegrees() {
		// Create test data
		Double radians = Double.valueOf(0d);
		Double expectedDegrees = Double.valueOf(0d);

		// Test entity
		Assert.assertEquals("0 radians should be converted to 0 degrees", expectedDegrees,
				MathUtils.toSignedDegrees(radians));
	}

	@Test
	public void testThatToDegreesConvertsPIOverTwoRadiansTo90Degrees() {
		// Create test data
		Double radians = Math.PI / 2;
		Double expectedDegrees = Double.valueOf(90d);

		// Test entity
		Assert.assertEquals("PI/2 radians should be converted to 90 Degrees", expectedDegrees,
				MathUtils.toSignedDegrees(radians));
	}

	@Test
	public void testThatToDegreesConvertsPIRadiansTo180Degrees() {
		// Create test data
		Double radians = Math.PI;
		Double expectedDegrees = Double.valueOf(180d);

		// Test entity
		Assert.assertEquals("PI radians should be converted to 180 Degrees", expectedDegrees,
				MathUtils.toSignedDegrees(radians));
	}

	@Test
	public void testThatToDegreesConverts2PIRadiansTo360Degrees() {
		// Create test data
		Double radians = Math.PI * 2;
		Double expectedDegrees = Double.valueOf(360d);

		// Test entity
		Assert.assertEquals("2PI radians should be converted to 360 Degrees", expectedDegrees,
				MathUtils.toSignedDegrees(radians));
	}

	@Test
	public void testThatMathUtilsConstructorIsPrivate() throws SecurityException, NoSuchMethodException {

		Constructor<MathUtils> constructor = MathUtils.class.getDeclaredConstructor();
		Assert.assertTrue("Utility class should have private constructor.",
				Modifier.isPrivate(constructor.getModifiers()));
	}

	@Test
	public void testThatMathUtilsCanBeConstructedWithoutRaisingExceptions() throws SecurityException,
			NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException,
			InvocationTargetException {

		Constructor<MathUtils> constructor = MathUtils.class.getDeclaredConstructor();
		constructor.setAccessible(true);
		MathUtils instance = constructor.newInstance();
		Assert.assertTrue("Class instance should be math utils.", instance instanceof MathUtils);
	}

	@Test
	public void testThatCompareToReturnsTrueIfNumbersAreEqualToTheTenthDecimal() {
		// Create test data
		Double value1 = Double.valueOf(1.1234567891);
		Double value2 = Double.valueOf(1.12345678912);

		Assert.assertTrue("CompareTo should return true if numbers are equal to the tenth decimal.",
				MathUtils.compare(value1, value2));
	}

	@Test
	public void testThatCompareToReturnsFalseIfNumbersRoundedUpAreNotEqualToTheTenthDecimal() {
		// Create test data
		Double value1 = Double.valueOf(1.1234567891);
		Double value2 = Double.valueOf(1.12345678915);

		Assert.assertFalse("CompareTo should return true if numbers are not equal to the tenth decimal.",
				MathUtils.compare(value1, value2));
	}

	@Test
	public void testThatCompareToReturnsFalseIfNumbersAreNotEqualToTheTenthDecimal() {
		// Create test data
		Double value1 = Double.valueOf(1.123456788);
		Double value2 = Double.valueOf(1.123456789);

		Assert.assertFalse("CompareTo should return true if numbers are not equal to the tenth decimal.",
				MathUtils.compare(value1, value2));
	}
}
