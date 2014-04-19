package org.mo.platform.face.test;

import org.mo.web.core.container.AContainer;
import org.mo.web.protocol.context.IWebContext;

//============================================================
// <T>标签测试接口。</T>
//============================================================
public interface ITagAction
{
   //============================================================
   // <T>构造处理</T>
   //
   // @return 处理结果
   //============================================================
   String construct(IWebContext context);

   //============================================================
   // <T>构造处理</T>
   //
   // @return 处理结果
   //============================================================
   String hello(IWebContext context,
                @AContainer(name = "tag") FTagContainer container);
}
