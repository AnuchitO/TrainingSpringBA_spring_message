package com.spt.evt.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AjaxUtils {
	private static final Logger logger = LoggerFactory.getLogger(AjaxUtils.class);
	
	public static boolean isAjaxRequest(String requestedWith) {
		return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
	}

	private AjaxUtils() {}

}
