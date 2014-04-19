package com.zq.game.platform.face.test;

import org.mo.com.xml.FXmlNode;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectAction;
import org.mo.platform.server.common.XServer;
import org.mo.web.core.container.AContainer;
import org.mo.web.protocol.context.IWebContext;

public class FHelloAction 
       implements IHelloAction 
{
	
	@Override
	public String hehe(IWebContext context, FHelloContainer page) {
		
		System.out.println("1231321______________+++++++++++++++++");
	    FXmlNode xsystem = new FXmlNode();
	    xsystem.set("hh", "应该可以了吧！");
	    page.setSystemInfo(xsystem);
		return "Hehe";
	}
	@Override
	public String construct(IWebContext context) {
		System.out.println("进入构造函数---------@@----------------------------");
		return null;
	}
}
