/**
 * Copyright (C) 2015 iSeek, Inc.
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of iSeek, Inc.
 * You shall not disclose such confidential information and shall use it only
 * in accordance with the terms of the license agreement you entered into with
 * iSeek, Inc.
 */
package us.iseek.model.enums;

import java.util.EnumSet;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the <tt>Direction</tt>.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class DirectionTest {

	@Test
	public void testThatFromSymbolRetrievesEnumConstantFromItsSymbol() {
		for (Direction constant : EnumSet.allOf(Direction.class)) {
			Direction retrievedConstant = Direction.fromSymbol(constant.toSymbol());
			Assert.assertEquals("Retrieved constant should equal constant with the same Symbol.", constant,
					retrievedConstant);
		}
	}

	@Test
	public void testThatFromSymbolReturnsNullIfThereIsNoConstantForTheSymbolProvided() {
		Direction retrievedConstant = Direction.fromSymbol("NON_EXISTING_Symbol");
		Assert.assertNull("Retrieved constant should be null if there is no constant for the Symbol provided.",
				retrievedConstant);
	}

	@Test
	public void testThatToSymbolReturnsNonEmptyStringAsSymbol() {
		for (Direction constant : EnumSet.allOf(Direction.class)) {
			Assert.assertTrue("Symbol should not be empty.", !constant.toSymbol().isEmpty());
		}
	}

	@Test
	public void testThatGetBearingReturnsPositiveValueBearing() {
		for (Direction constant : EnumSet.allOf(Direction.class)) {
			Assert.assertTrue("Bearing be a value.", constant.getBearing() >= 0);
		}
	}

	@Test
	public void testThatEnumCanBeConvertedFromString() {
		for (Direction constant : Direction.values()) {
			String stringRepresentation = String.valueOf(constant);
			Assert.assertEquals("Enum should be able to be retrieved from string representation.", constant,
					Direction.valueOf(stringRepresentation));
		}
	}
}
