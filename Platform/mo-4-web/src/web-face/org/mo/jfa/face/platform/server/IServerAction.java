package org.mo.jfa.face.platform.server;

import org.mo.web.core.container.AContainer;
import org.mo.web.protocol.context.IWebContext;

//============================================================
// <T>服务器命令接口。</T>
//============================================================
public interface IServerAction
{
   //============================================================
   // <T>列出目录处理。</T>
   //
   // @param context 环境
   // @param page 容器
   // @return 处理结果
   //============================================================
   String catalog(IWebContext context,
                  @AContainer(name = "page") FServerContainer page);

   //============================================================
   // <T>列出项目处理。</T>
   //
   // @param context 环境
   // @param page 容器
   // @return 处理结果
   //============================================================
   String list(IWebContext context,
               @AContainer(name = "page") FServerContainer page);

   //============================================================
   // <T>新建处理。</T>
   //
   // @param context 环境
   // @param page 容器
   // @return 处理结果
   //============================================================
   String insert(IWebContext context,
                 @AContainer(name = "page") FServerContainer page);

   //============================================================
   // <T>更新处理。</T>
   //
   // @param context 环境
   // @param page 容器
   // @return 处理结果
   //============================================================
   String update(IWebContext context,
                 @AContainer(name = "page") FServerContainer page);

   //============================================================
   // <T>删除处理。</T>
   //
   // @param context 环境
   // @param page 容器
   // @return 处理结果
   //============================================================
   String delete(IWebContext context,
                 @AContainer(name = "page") FServerContainer page);

   //============================================================
   // <T>排序处理。</T>
   //
   // @param context 环境
   // @param page 容器
   // @return 处理结果
   //============================================================
   String sort(IWebContext context,
               @AContainer(name = "page") FServerContainer page);
}
