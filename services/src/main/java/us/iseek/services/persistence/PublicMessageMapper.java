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

import us.iseek.model.communication.PublicMessage;

/**
 * Provides methods to persist user <tt>PublicMessage</tt>s.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public interface PublicMessageMapper {

	/**
	 * Inserts a public message to the persistent store.
	 * 
	 * @param publicMessage
	 *            - The public message to insert to the persistent store.
	 */
	public void insert(PublicMessage publicMessage);
}
