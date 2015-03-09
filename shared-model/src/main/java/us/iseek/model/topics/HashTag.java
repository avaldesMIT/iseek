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

/**
 * Represents a topic of interest.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class HashTag {

	private Long id;
	private String displayName;

	/**
	 * Creates a new instance of this.
	 */
	public HashTag() {
		// Default constructor
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * @param id
	 *            - The id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return this.displayName;
	}

	/**
	 * @param displayName
	 *            - The displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "{HashTag: id=" + this.id + ", displayName=" + this.displayName + "}";
	}
}
