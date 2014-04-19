package org.mo.game.editor.face.editor.instance;

import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.core.aop.face.ALink;
import org.mo.game.editor.core.enums.IEnumConsole;
import org.mo.game.editor.core.instance.EInstanceSource;
import org.mo.game.editor.core.instance.IInstanceConsole;
import org.mo.game.editor.core.instance.common.XInstanceGroup;
import org.mo.game.editor.face.apl.page.IPublicPage;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectAction;
import org.mo.web.core.format.IFormatConsole;
import org.mo.web.protocol.context.IWebContext;

//============================================================
// <T>实例命令。</T>
//============================================================
public class FInstanceAction
      extends FAbsXmlObjectAction<XInstanceGroup>
      implements
         IInstanceAction
{
   // 日志输出结果
   private static ILogger _logger = RLogger.find(FInstanceAction.class);

   // 页面目录
   public final String PAGE_CATALOG = "Catalog";

   // 选择集合
   public final String SEL_COL = "sel_collection";

   // 页面来源
   public final static String PAGE_SOURCE = "Source";

   // 枚举控制台
   @ALink
   protected IEnumConsole _enumConsole;

   // 实例控制台
   @ALink
   protected IInstanceConsole _instanceConsole;

   // 格式化控制台
   @ALink
   protected IFormatConsole _formatConsole;

   //============================================================
   // <T>目录处理。</T>
   //
   // @param context 环境
   // @param page 页面
   //============================================================
   @Override
   public String catalog(IWebContext context,
                         FInstancePage page){
      return PAGE_CATALOG;
   }

   //============================================================
   // <T>列表处理。</T>
   //
   // @param context 环境
   // @param page 页面
   //============================================================
   @Override
   public String list(IWebContext context,
                      FInstancePage page){
      return list(_instanceConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   //============================================================
   // <T>插入处理。</T>
   //
   // @param context 环境
   // @param page 页面
   //============================================================
   @Override
   public String insert(IWebContext context,
                        FInstancePage page){
      return insert(_instanceConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   //============================================================
   // <T>更新处理。</T>
   //
   // @param context 环境
   // @param page 页面
   //============================================================
   @Override
   public String update(IWebContext context,
                        FInstancePage page){
      return update(_instanceConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   //============================================================
   // <T>删除处理。</T>
   //
   // @param context 环境
   // @param page 页面
   //============================================================
   @Override
   public String delete(IWebContext context,
                        FInstancePage page){
      return delete(_instanceConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   //============================================================
   // <T>排序处理。</T>
   //
   // @param context 环境
   // @param page 页面
   //============================================================
   @Override
   public String sort(IWebContext context,
                      FInstancePage page){
      return sort(_instanceConsole, context, page, IPublicPage.XOBJECT_SORT);
   }

   //============================================================
   // <T>建立代码处理。</T>
   //
   // @param context 环境
   // @param page 页面
   //============================================================
   @Override
   public String buildSource(IWebContext context,
                             FInstancePage page){
      String type = context.parameter("type");
      _logger.debug(this, "buildAllSource", "Build type source. (type={0})", type);
      // 生成代码
      if("all".equals(type)){
         _instanceConsole.buildSource(EInstanceSource.All);
      }else if("allcpp".equals(type)){
         _instanceConsole.buildSource(EInstanceSource.AllCpp);
      }else if("allas".equals(type)){
         _instanceConsole.buildSource(EInstanceSource.AllAs);
      }else if("allcs".equals(type)){
         _instanceConsole.buildSource(EInstanceSource.AllCs);
      }
      return IPublicPage.PROCESS_SUCCESS;
   }
}
