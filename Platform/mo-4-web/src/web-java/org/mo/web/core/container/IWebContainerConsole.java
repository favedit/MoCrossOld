package org.mo.web.core.container;

import org.mo.web.core.container.common.FWebContainerItem;
import org.mo.web.protocol.context.IWebContext;

//============================================================
// <T>容器控制台。</T>
//============================================================
public interface IWebContainerConsole
{
   //============================================================
   // <T>查找一个数据容器。</T>
   //
   // @param context 网页环境
   // @param type 表单类型
   // @param clazz 类对象
   // @return 表单
   //============================================================
   FWebContainerItem findContainer(IWebContext context,
                                AContainer type,
                                Class<?> clazz);
}
