package org.mo.game.editor.face.editor.instance;

import org.mo.game.editor.face.inc.form.IFormAble;
import org.mo.web.core.container.AContainer;
import org.mo.web.protocol.context.IWebContext;

//============================================================
// <T>实例命令。</T>
//============================================================
public interface IInstanceAction
      extends
         IFormAble
{
   //============================================================
   // <T>目录处理。</T>
   //
   // @param context 环境
   // @param page 页面
   //============================================================
   String catalog(IWebContext context,
                  @AContainer(name = "page") FInstancePage page);

   //============================================================
   // <T>列表处理。</T>
   //
   // @param context 环境
   // @param page 页面
   //============================================================
   String list(IWebContext context,
               @AContainer(name = "page") FInstancePage page);

   //============================================================
   // <T>插入处理。</T>
   //
   // @param context 环境
   // @param page 页面
   //============================================================
   String insert(IWebContext context,
                 @AContainer(name = "page") FInstancePage page);

   //============================================================
   // <T>更新处理。</T>
   //
   // @param context 环境
   // @param page 页面
   //============================================================
   String update(IWebContext context,
                 @AContainer(name = "page") FInstancePage page);

   //============================================================
   // <T>删除处理。</T>
   //
   // @param context 环境
   // @param page 页面
   //============================================================
   String delete(IWebContext context,
                 @AContainer(name = "page") FInstancePage page);

   //============================================================
   // <T>排序处理。</T>
   //
   // @param context 环境
   // @param page 页面
   //============================================================
   String sort(IWebContext context,
               @AContainer(name = "page") FInstancePage page);

   //============================================================
   // <T>建立代码处理。</T>
   //
   // @param context 环境
   // @param page 页面
   //============================================================
   String buildSource(IWebContext context,
                      @AContainer(name = "page") FInstancePage page);
}
