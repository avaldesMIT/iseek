/**
 * Copyright (C) 2015 iSeek, Inc. 
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of iSeek, Inc.
 * You shall not disclose such confidential information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with 
 * iSeek, Inc.
 */
package us.iseek.services.web.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the <tt>WebUtils</tt>.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class WebUtilsTest {

	@Test
	public void testThatGetSafeStringRemovesSpecialCharacters() {
		// Create test data
		String testString = "T!@#ES&*(\"\\T_ S-T+RING.9.9";
		
		// Define expected result
		String expectedString = "TEST STRING99";
		
		// Test method
		String actualString = WebUtils.getSafeString(testString);
		Assert.assertEquals("GetSafeString should remove all special characters", expectedString, actualString);
	}
	
	@Test
	public void testThatGetSafeStringDoesNotRemoveLettersOrNumbers() {
		// Create test data
		String testString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		
		// Test method
		String actualString = WebUtils.getSafeString(testString);
		Assert.assertEquals("GetSafeString should not remove letters or numbers", testString, actualString);
	}

	@Test
	public void testThatGetSafeStringDoesNotRemoveSpaces() {
		// Create test data
		String testString = "  ";
		
		// Test method
		String actualString = WebUtils.getSafeString(testString);
		Assert.assertEquals("GetSafeString should not remove letters or numbers", testString, actualString);
	}
	
	@Test
	public void testThatWebUtilsConstructorIsPrivate() throws SecurityException, NoSuchMethodException {

		Constructor<WebUtils> constructor = WebUtils.class.getDeclaredConstructor();
		Assert.assertTrue("Utility class should have private constructor.",
				Modifier.isPrivate(constructor.getModifiers()));
	}

	@Test
	public void testThatWebUtilsCanBeConstructedWithoutRaisingExceptions() throws SecurityException,
			NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException,
			InvocationTargetException {

		Constructor<WebUtils> constructor = WebUtils.class.getDeclaredConstructor();
		constructor.setAccessible(true);
		WebUtils instance = constructor.newInstance();
		Assert.assertTrue("Class instance should be web utils.", instance instanceof WebUtils);
	}
}
