package com.zq.game.platform.face.test;

import org.mo.web.core.container.AContainer;
import org.mo.web.protocol.context.IWebContext;


public interface IHelloAction {
	
	public String construct(IWebContext context);
	//============================================================
	   // <T>编译代码。</T>
	   //
	   // @param context 环境
	   // @param page 容器
	   // @return 处理结果
	   //============================================================
	   String hehe(IWebContext context,
	                       @AContainer(name = "page") FHelloContainer page);
}
