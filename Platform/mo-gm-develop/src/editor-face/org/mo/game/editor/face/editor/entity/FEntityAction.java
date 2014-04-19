package org.mo.game.editor.face.editor.entity;

import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.game.editor.core.entity.EEntitySource;
import org.mo.game.editor.core.entity.IEntityConsole;
import org.mo.game.editor.core.entity.common.XEntity;
import org.mo.game.editor.core.entity.common.XEntityGroup;
import org.mo.game.editor.core.enums.IEnumConsole;
import org.mo.game.editor.core.enums.common.XEnum;
import org.mo.game.editor.face.apl.page.IPublicPage;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectAction;
import org.mo.web.core.format.IFormatConsole;
import org.mo.web.protocol.context.IWebContext;

public class FEntityAction
      extends FAbsXmlObjectAction<XEntityGroup>
      implements
         IEntityAction{

   private static ILogger _logger = RLogger.find(FEntityAction.class);

   public final String PAGE_CATALOG = "Catalog";

   public final String SEL_COL = "sel_collection";

   public final static String PAGE_SOURCE = "Source";

   @ALink
   protected IEnumConsole _enumConsole;

   @ALink
   protected IEntityConsole _entityConsole;

   @ALink
   protected IFormatConsole _formatConsole;

   //============================================================
   @Override
   public String buildAllSource(IWebContext context,
                                FEntityPage page){
      String type = context.parameter("type");
      _logger.debug(this, "buildAllSource", "Build type source. (type={0})", type);
      // 生成代码
      if("all".equals(type)){
         _entityConsole.buildAll(EEntitySource.All);
      }else if("cpp".equals(type)){
         _entityConsole.buildAll(EEntitySource.AllCpp);
      }else if("as".equals(type)){
         _entityConsole.buildAll(EEntitySource.AllAs);
      }else if("cs".equals(type)){
         _entityConsole.buildAll(EEntitySource.AllCs);
      }else if("java".equals(type)){
         _entityConsole.buildAll(EEntitySource.AllJava);
      }
      return IPublicPage.PROCESS_SUCCESS;
   }

   //============================================================
   @Override
   public String catalog(IWebContext context,
                         FEntityPage page){
      return PAGE_CATALOG;
   }

   //============================================================
   @Override
   public String delete(IWebContext context,
                        FEntityPage page){
      return delete(_entityConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   //============================================================
   @Override
   public String fetchProperty(IWebContext context,
                               FEntityPage page){
      // 获得上传的数据
      page.appachContext(context);
      // 查找选中的实体
      String collection = page.getSelectCollection();
      XEntityGroup xgroup = _entityConsole.get(collection);
      String component = page.getSelectComponent();
      IXmlObject xentity = xgroup.children().search(component);
      boolean changed = false;
      // 获得属性组
      String propertyGroup = xentity.innerGet(XEntity.PTY_PROPERTY_GROUP);
      if(!RString.isBlank(propertyGroup)){
         XEnum xenum = _enumConsole.searchEnumObject(propertyGroup);
         if(null != xenum){
            for(IXmlObject xentityitem : xentity.children()){
               // 查看是否锁定
               if(!RBoolean.parse(xentityitem.innerGet("is_lock"))){
                  // 获得定义
                  String itemName = xentityitem.innerGet("name");
                  IXmlObject xenumitem = xenum.find("name", itemName);
                  if(null != xenumitem){
                     String propertyName = xenumitem.innerGet("name");
                     xentityitem.innerSet("is_set", RBoolean.TRUE_STR);
                     xentityitem.innerSet("is_get", RBoolean.TRUE_STR);
                     xentityitem.innerSet("property_name", propertyName);
                     changed = true;
                  }
               }
            }
         }
      }
      if(changed){
         _entityConsole.store(xgroup);
      }
      return IPublicPage.PROCESS_SUCCESS;
   }

   //============================================================
   @Override
   public String insert(IWebContext context,
                        FEntityPage page){
      return insert(_entityConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   //============================================================
   @Override
   public String list(IWebContext context,
                      FEntityPage page){
      return list(_entityConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   //============================================================
   @Override
   public String sort(IWebContext context,
                      FEntityPage page){
      return sort(_entityConsole, context, page, IPublicPage.XOBJECT_SORT);
   }

   //============================================================
   @Override
   public String update(IWebContext context,
                        FEntityPage page){
      return update(_entityConsole, context, page, IPublicPage.XOBJECT_FORM);
   }
}
