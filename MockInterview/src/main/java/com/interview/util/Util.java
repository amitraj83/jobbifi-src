package com.interview.util;

import javax.servlet.http.HttpServletRequest;

public   class Util {
	public static String getbBaseURLpath(HttpServletRequest req){
		return req.getScheme() + "://" +
	             req.getServerName() + ":" + req.getServerPort() +
	             req.getContextPath();
	}

}
