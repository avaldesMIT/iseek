/**
 * Copyright (C) 2015 iSeek, Inc.
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of iSeek, Inc.
 * You shall not disclose such confidential information and shall use it only
 * in accordance with the terms of the license agreement you entered into with
 * iSeek, Inc.
 */
package us.iseek.model.topics;

import org.hamcrest.core.StringContains;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the <tt>HashTag</tt>.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class HashTagTest {

	private HashTag hashTag;

	@Before
	public void setUp() {
		this.hashTag = new HashTag();
	}

	@Test
	public void testThatGetIdReturnsTheIdThatWasSet() {
		// Create mock
		Long id = Long.valueOf(12345L);

		// Set value
		this.hashTag.setId(id);

		// Verify value
		Assert.assertEquals("GetId should return the id that was set.", id, this.hashTag.getId());
	}

	@Test
	public void testThatGetDisplayNameReturnsTheDisplayNameThatWasSet() {
		// Create mock
		String displayName = "DISPLAY_NAME";

		// Set value
		this.hashTag.setDisplayName(displayName);

		// Verify value
		Assert.assertEquals("GetDisplayName should return the displayName that was set.", displayName,
				this.hashTag.getDisplayName());
	}

	@Test
	public void testThatToStringContainsTheIdThatWasSet() {
		// Create mock
		Long id = Long.valueOf(12345L);

		// Set value
		this.hashTag.setId(id);

		// Verify value
		Assert.assertThat("ToString should contain the id that was set.", this.hashTag.toString(),
				StringContains.containsString(String.valueOf(id)));
	}

	@Test
	public void testThatToStringContainsTheDisplayNameThatWasSet() {
		// Create mock
		String displayName = "DISPLAY_NAME";

		// Set value
		this.hashTag.setDisplayName(displayName);

		// Verify value
		Assert.assertThat("ToString should contain the displayName that was set.", this.hashTag.toString(),
				StringContains.containsString(String.valueOf(displayName)));
	}
}
