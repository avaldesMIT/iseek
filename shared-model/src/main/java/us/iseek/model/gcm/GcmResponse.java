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

/**
 * Represents a successful response from the GCM server.
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class GcmResponse {

	private Long multicast_id;
	private Long success;
	private Long failure;
	private Long canonical_ids;
	private List<GcmResult> gcmResults;

	/**
	 * @return the multicast_id
	 */
	public Long getMulticast_id() {
		return this.multicast_id;
	}

	/**
	 * @param multicast_id
	 *            - The multicast_id to set
	 */
	public void setMulticast_id(Long multicast_id) {
		this.multicast_id = multicast_id;
	}

	/**
	 * @return the success
	 */
	public Long getSuccess() {
		return this.success;
	}

	/**
	 * @param success
	 *            - The success to set
	 */
	public void setSuccess(Long success) {
		this.success = success;
	}

	/**
	 * @return the failure
	 */
	public Long getFailure() {
		return this.failure;
	}

	/**
	 * @param failure
	 *            - The failure to set
	 */
	public void setFailure(Long failure) {
		this.failure = failure;
	}

	/**
	 * @return the canonical_ids
	 */
	public Long getCanonical_ids() {
		return this.canonical_ids;
	}

	/**
	 * @param canonical_ids
	 *            - The canonical_ids to set
	 */
	public void setCanonical_ids(Long canonical_ids) {
		this.canonical_ids = canonical_ids;
	}

	/**
	 * @return the gcmResults
	 */
	public List<GcmResult> getGcmResults() {
		return this.gcmResults;
	}

	/**
	 * @param gcmResults
	 *            - The gcmResults to set
	 */
	public void setGcmResults(List<GcmResult> gcmResults) {
		this.gcmResults = gcmResults;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "{GcmResponse: multicast_id=" + this.multicast_id + ", success=" + this.success + ", failure="
				+ this.failure + ", canonical_ids=" + this.canonical_ids + ", gcmResults=" + this.gcmResults + "}";
	}
}
