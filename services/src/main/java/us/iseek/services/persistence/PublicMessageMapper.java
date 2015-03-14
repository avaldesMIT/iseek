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

import us.iseek.model.communication.PublicMessage;
import us.iseek.model.gps.Area;

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

	/**
	 * Retrieves the GCM registration IDs for the recipients of the public
	 * message referenced by the ID provided.
	 * 
	 * @param publicMessageId
	 *            - The ID of the public message for which the recipients' GCM
	 *            registration ID is required.
	 * @param broadcastArea
	 *            - An area to limit the recipients of this message. Users
	 *            outside of this area will not be considered as recipients.
	 * @return A list of GCM registration IDs that correspond to all of the
	 *         recipients of the public message provided.
	 */
	public List<String> getRecipientRegistrationIds(Long publicMessageId, Area broadcastArea);
}
