/**
 * Copyright (C) 2015 iSeek, Inc.
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of iSeek, Inc.
 * You shall not disclose such confidential information and shall use it only
 * in accordance with the terms of the license agreement you entered into with
 * iSeek, Inc.
 */
package us.iseek.model.constants;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the <tt>GeoConstants</tt>.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class GeoConstantsTest {

	@Test
	public void testThatGeoConstantsConstructorIsPrivate() throws SecurityException, NoSuchMethodException {

		Constructor<GeoConstants> constructor = GeoConstants.class.getDeclaredConstructor();
		Assert.assertTrue("Utility class should have private constructor.",
				Modifier.isPrivate(constructor.getModifiers()));
	}

	@Test
	public void testThatGeoConstantsCanBeConstructedWithoutRaisingExceptions() throws SecurityException,
			NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException,
			InvocationTargetException {

		Constructor<GeoConstants> constructor = GeoConstants.class.getDeclaredConstructor();
		constructor.setAccessible(true);
		GeoConstants instance = constructor.newInstance();
		Assert.assertTrue("Class instance should be geo constants.", instance instanceof GeoConstants);
	}
}
