package com.zq.game.platform.face.test;

import org.mo.com.xml.FXmlNode;

public class FHelloContainer {
     
	private String str1;
	
	private String str2;
	
	private FXmlNode _systemInfo;
	
	public FXmlNode systemInfo() {
		return _systemInfo;
	}
	public void setSystemInfo(FXmlNode _systemInfo) {
		this._systemInfo = _systemInfo;
	}
	public String getStr1() {
		return str1;
	}
	public void setStr1(String str1) {
		this.str1 = str1;
	}
	public String getStr2() {
		return str2;
	}
	public void setStr2(String str2) {
		this.str2 = str2;
	}
	
	
}
