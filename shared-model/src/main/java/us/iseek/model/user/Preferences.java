/**
 * Copyright (C) 2015 iSeek, Inc. 
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of iSeek, Inc.
 * You shall not disclose such confidential information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with 
 * iSeek, Inc.
 */
package us.iseek.model.user;

/**
 * Enlists user preferences
 * 
 * @author Armando Valdes
 * @since 1.0
 */
public class Preferences {

	private Boolean broadcastLocation;
	private Boolean showProfilePicture;

	public static Preferences DEFAULT_PREFERENCES = new Preferences(true, false);

	/**
	 * Creates a new instance of this.
	 */
	public Preferences() {
		// Default constructor
	}

	/**
	 * Creates a new instance of this.
	 * 
	 * @param broadcastLocation
	 *            - Determines if the user wants to broadcast his or her
	 *            location.
	 * @param showProfilePicture
	 *            - Determines if the user wants to show his or her Facebook's
	 *            profile picture.
	 */
	public Preferences(Boolean broadcastLocation, Boolean showProfilePicture) {
		this.broadcastLocation = broadcastLocation;
		this.showProfilePicture = showProfilePicture;
	}

	/**
	 * @return the broadcastLocation
	 */
	public Boolean isBroadcastLocation() {
		return this.broadcastLocation;
	}

	/**
	 * @param broadcastLocation
	 *            - The broadcastLocation to set
	 */
	public void setBroadcastLocation(Boolean broadcastLocation) {
		this.broadcastLocation = broadcastLocation;
	}

	/**
	 * @return the showProfilePicture
	 */
	public Boolean isShowProfilePicture() {
		return this.showProfilePicture;
	}

	/**
	 * @param showProfilePicture
	 *            - The showProfilePicture to set
	 */
	public void setShowProfilePicture(Boolean showProfilePicture) {
		this.showProfilePicture = showProfilePicture;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return (13 * (this.broadcastLocation == null ? 0 : this.broadcastLocation.hashCode()))
				+ (19 * (this.showProfilePicture == null ? 0 : this.showProfilePicture.hashCode()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		// Ensure object is not null and is an instance of this class
		if (!(obj instanceof Preferences)) {
			return false;
		}

		// Compare broadcastLocation and showProfilePicture
		Preferences other = (Preferences) obj;
		boolean isBroadcastLocationEqual = this.areEqual(this.broadcastLocation, other.broadcastLocation);
		boolean isShowProfilePictureEqual = this.areEqual(this.showProfilePicture, other.showProfilePicture);
		return isBroadcastLocationEqual && isShowProfilePictureEqual;
	}

	/**
	 * If both values are null, returns true. If one of the values is null,
	 * returns false. Otherwise, compares the two values to see if they are
	 * equal.
	 * 
	 * @param b1
	 *            - The first value to compare.
	 * @param b2
	 *            - The second value to compare.
	 * @return true if and only if b1 == b2 or b1.equals(b2) if both b1 and b2
	 *         are not null.
	 */
	private boolean areEqual(Boolean b1, Boolean b2) {
		if (b1 == b2) {
			return true;
		} else if (b1 == null || b2 == null) {
			return false;
		} else {
			return b1.equals(b2);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "{Preferences: broadcastLocation=" + this.broadcastLocation + ", showProfilePicture="
				+ this.showProfilePicture + "}";
	}
}
