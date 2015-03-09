/**
 * Copyright (C) 2015 iSeek, Inc. 
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of iSeek, Inc.
 * You shall not disclose such confidential information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with 
 * iSeek, Inc.
 */
package us.iseek.services.persistence;

import java.util.List;

import us.iseek.model.gps.Area;
import us.iseek.model.topics.HashTag;

/**
 * Provides methods to persist user <tt>HashTag</tt>s.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public interface HashTagMapper {

	/**
	 * Inserts the hash tag into the persistent store.
	 * 
	 * @param hashTag
	 *            - The hash tag to insert.
	 */
	public void insert(HashTag hashTag);

	/**
	 * Retrieves the hash tag with the display name provided.
	 * 
	 * @param displayName
	 *            - The display name of the hash tag to retrieve.
	 * @return The hash tag with the display name provided.
	 */
	public HashTag get(String displayName);

	/**
	 * Gets the active topics (hash tags) within the geographical area provided.
	 * 
	 * @param area
	 *            - The area to use in filtering active hash tags in the system.
	 * @return The active hash tags in the geographical area provided.
	 */
	public List<HashTag> getByArea(Area area);

}
