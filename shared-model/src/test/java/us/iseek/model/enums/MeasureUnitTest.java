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
 * Tests the <tt>MeasureUnit</tt>.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class MeasureUnitTest {

	@Test
	public void testThatFromSymbolRetrievesEnumConstantFromItsSymbol() {
		for (MeasureUnit constant : EnumSet.allOf(MeasureUnit.class)) {
			MeasureUnit retrievedConstant = MeasureUnit.fromSymbol(constant.toSymbol());
			Assert.assertEquals("Retrieved constant should equal constant with the same Symbol.", constant,
					retrievedConstant);
		}
	}

	@Test
	public void testThatFromSymbolReturnsNullIfThereIsNoConstantForTheSymbolProvided() {
		MeasureUnit retrievedConstant = MeasureUnit.fromSymbol("NON_EXISTING_Symbol");
		Assert.assertNull("Retrieved constant should be null if there is no constant for the Symbol provided.",
				retrievedConstant);
	}

	@Test
	public void testThatToSymbolReturnsNonEmptyStringAsSymbol() {
		for (MeasureUnit constant : EnumSet.allOf(MeasureUnit.class)) {
			Assert.assertTrue("Symbol should not be empty.", !constant.toSymbol().isEmpty());
		}
	}

	@Test
	public void testThatGetConvertiblesReturnsListOfConvertibles() {
		for (MeasureUnit constant : EnumSet.allOf(MeasureUnit.class)) {
			Assert.assertNotNull("List must not be null.", constant.getConvertibles());
			Assert.assertFalse("List must not be empty.", constant.getConvertibles().isEmpty());
		}
	}

	@Test
	public void testThatAllMeasureUnitsCanBeConvertedToThemselves() {
		for (MeasureUnit constant : EnumSet.allOf(MeasureUnit.class)) {
			Assert.assertTrue("List must not be empty.", constant.getConvertibles().contains(constant));
		}
	}

	@Test
	public void testThatEnumCanBeConvertedFromString() {
		for (MeasureUnit constant : MeasureUnit.values()) {
			String stringRepresentation = String.valueOf(constant);
			Assert.assertEquals("Enum should be able to be retrieved from string representation.", constant,
					MeasureUnit.valueOf(stringRepresentation));
		}
	}
}
