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
 * Tests the <tt>ApplicationConstants</tt>.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class ApplicationConstantsTest {

	@Test
	public void testThatApplicationConstantsConstructorIsPrivate() throws SecurityException, NoSuchMethodException {

		Constructor<ApplicationConstants> constructor = ApplicationConstants.class.getDeclaredConstructor();
		Assert.assertTrue("Utility class should have private constructor.",
				Modifier.isPrivate(constructor.getModifiers()));
	}

	@Test
	public void testThatApplicationConstantsCanBeConstructedWithoutRaisingExceptions() throws SecurityException,
			NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException,
			InvocationTargetException {

		Constructor<ApplicationConstants> constructor = ApplicationConstants.class.getDeclaredConstructor();
		constructor.setAccessible(true);
		ApplicationConstants instance = constructor.newInstance();
		Assert.assertTrue("Class instance should be application constants.", instance instanceof ApplicationConstants);
	}
}
