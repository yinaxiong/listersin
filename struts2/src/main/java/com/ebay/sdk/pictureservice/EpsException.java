/*
Copyright (c) 2008 eBay, Inc.

This program is licensed under the terms of the eBay Common Development and
Distribution License (CDDL) Version 1.0 (the "License") and any subsequent
version thereof released by eBay.  The then-current version of the License
can be found at https://www.codebase.ebay.com/Licenses.html and in the
eBaySDKLicense file that is under the eBay SDK install directory.
*/

package com.ebay.sdk.pictureservice;

import com.ebay.sdk.SdkException;

/**
 * @author cli
 *
 */
public class EpsException extends SdkException {

	private String errorName;
	private String errorMessage;

	public EpsException(String name, String message) {
		super(name + " : " + message);
		this.errorName = name;
		this.errorMessage = message;
	}

	/**
	 * Returns the errorMessage.
	 * @return String
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * Returns the errorName.
	 * @return String
	 */
	public String getErrorName() {
		return errorName;
	}

}
