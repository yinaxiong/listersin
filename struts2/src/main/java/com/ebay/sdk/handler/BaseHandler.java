/*
Copyright (c) 2008 eBay, Inc.

This program is licensed under the terms of the eBay Common Development and 
Distribution License (CDDL) Version 1.0 (the "License") and any subsequent 
version thereof released by eBay.  The then-current version of the License 
can be found at https://www.codebase.ebay.com/Licenses.html and in the 
eBaySDKLicense file that is under the eBay SDK install directory.
*/
package com.ebay.sdk.handler;

import javax.xml.ws.handler.MessageContext;

/**
 * a common supper class for all handlers
 * @author boyang
 * @version 1.0
 *
 * @param <T>
 */
public class BaseHandler<T extends MessageContext> {
	protected String HandlerName = null;

	public boolean handleFault(T mc) {
		
		return true;
		
	}
	
	public void close(MessageContext mc) {
	}

	public void setHandlerName(String handlerName) {
		HandlerName = handlerName;
	}
	
}
