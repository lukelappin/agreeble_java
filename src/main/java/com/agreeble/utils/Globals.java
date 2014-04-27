package com.agreeble.utils;

import javax.servlet.http.HttpServletRequest;

public final class Globals {
	
	private static Globals _this;

	// =================================================== Constructors
	public static Globals getInstance() throws Exception {
		if (null == _this) {
			_this = new Globals();
		}
		return _this;
	}
	
	public Globals()  {
		
	}
	
	public static String GetStringValue(HttpServletRequest request,String sVarName)
	{
		String sRet = null;
		sRet = request.getParameter(sVarName);
		if(sRet == null) sRet = "";
		
		return sRet;
	}
	
}
