/**
 * Copyright (C) 2015 iSeek, Inc. 
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of iSeek, Inc.
 * You shall not disclose such confidential information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with 
 * iSeek, Inc.
 */
package us.iseek.model.gcm;

import java.util.List;

import us.iseek.model.communication.MessageAbstract;

/**
 * Defines a Google Cloud Messaging message.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class GcmMessage {

	private MessageAbstract data;
	private List<String> registration_ids;

	private String collapse_key;
	private Long time_to_live;
	private Boolean delay_while_idle;

	/**
	 * @return the data
	 */
	public MessageAbstract getData() {
		return this.data;
	}

	/**
	 * @param data
	 *            - The data to set
	 */
	public void setData(MessageAbstract data) {
		this.data = data;
	}

	/**
	 * @return the registration_ids
	 */
	public List<String> getRegistration_ids() {
		return this.registration_ids;
	}

	/**
	 * @param registration_ids
	 *            - The registration_ids to set
	 */
	public void setRegistration_ids(List<String> registration_ids) {
		this.registration_ids = registration_ids;
	}

	/**
	 * @return the collapse_key
	 */
	public String getCollapse_key() {
		return this.collapse_key;
	}

	/**
	 * @param collapse_key
	 *            - The collapse_key to set
	 */
	public void setCollapse_key(String collapse_key) {
		this.collapse_key = collapse_key;
	}

	/**
	 * @return the time_to_live
	 */
	public Long getTime_to_live() {
		return this.time_to_live;
	}

	/**
	 * @param time_to_live
	 *            - The time_to_live to set
	 */
	public void setTime_to_live(Long time_to_live) {
		this.time_to_live = time_to_live;
	}

	/**
	 * @return the delay_while_idle
	 */
	public Boolean getDelay_while_idle() {
		return this.delay_while_idle;
	}

	/**
	 * @param delay_while_idle
	 *            - The delay_while_idle to set
	 */
	public void setDelay_while_idle(Boolean delay_while_idle) {
		this.delay_while_idle = delay_while_idle;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "{GcmMessage: data=" + this.data + ", registration_ids=" + this.registration_ids + ", collapse_key="
				+ this.collapse_key + ", time_to_live=" + this.time_to_live + ", delay_while_idle="
				+ this.delay_while_idle + "}";
	}
}
