/*
 * @(#)FEventAction.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.jfa.face.logic.event;

import org.mo.com.lang.IAttributes;
import org.mo.com.lang.RBoolean;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectAction;
import org.mo.jfa.face.apl.page.IPublicPage;
import org.mo.logic.data.ILgEventGroupConfigDi;
import org.mo.logic.data.ILgEventGroupDi;
import org.mo.logic.data.ILgEventTypeConfigDi;
import org.mo.logic.data.ILgEventTypeDi;
import org.mo.logic.data.ISyPropertyDi;
import org.mo.logic.event.ILogicEventTypeConsole;
import org.mo.logic.event.common.XProperty;
import org.mo.logic.event.common.XType;
import org.mo.logic.event.common.XTypeConfig;
import org.mo.logic.process.common.XGroupConfig;
import org.mo.logic.property.ILogicPropertyConsole;
import org.mo.logic.session.ILogicSessionConsole;
import org.mo.web.protocol.context.IWebContext;

/**
 * <T>业务事件树目录各种操作动作接口实体类。</T>
 * <P>提供对业务事件和业务事件用树目录形式进行增删改和同步等各种操作的接口。</P>
 * 
 * @author YJHUA
 * @version 1.00 - 2008/12/08
 */
public class FEventAction
      extends FAbsXmlObjectAction<IXmlObject>
      implements
         IEventAction
{

   @ALink
   protected ILogicEventTypeConsole _eventTypeConsole;

   @ALink
   protected ILogicSessionConsole _sessionConsole;

   @ALink
   protected ILogicPropertyConsole _propertyConsole;

   public final static String PAGE_CATALOG = "Catalog";

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.event.IEventAction#allSyncEvent(org.mo.web.protocol.context.IWebContext, org.mo.logic.data.ILgEventGroupDi, org.mo.logic.data.ILgEventTypeDi, org.mo.logic.data.ILgEventTypeConfigDi, org.mo.logic.data.ILgEventGroupConfigDi, org.mo.logic.data.ISyPropertyDi, org.mo.jfa.face.logic.event.FEventPage)
    */
   @Override
   public String allSyncEvent(IWebContext context,
                              ILgEventGroupDi lgEventGroupDi,
                              ILgEventTypeDi lgEventTypeDi,
                              ILgEventTypeConfigDi lgEventTypeConfigDi,
                              ILgEventGroupConfigDi lgEventGroupConfigDi,
                              ISyPropertyDi syPropertyDi,
                              FEventPage page){
      // 同步开始时处理
      lgEventGroupDi.doSyncGroupBegin(_sessionConsole.makeLogic(), null);
      // 同步所有数据
      for(IXmlObject xds : _eventTypeConsole.list()){
         if(!RBoolean.parse(xds.innerGet("is_valid"))){
            continue;
         }
         IAttributes attributes = xds.toSimpleAttributes();
         lgEventGroupDi.doSyncGroup(_sessionConsole.makeLogic(), attributes);
         if(xds.hasChild()){
            searchOneChild(xds, attributes, lgEventTypeDi, lgEventTypeConfigDi, lgEventGroupConfigDi, syPropertyDi);
         }
      }
      // 同步结束时处理
      //lgEventGroupDi.doSyncGroupEnd(_sessionConsole.makeLogic(), null);
      return IPublicPage.PROCESS_SUCCESS;
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.event.IEventAction#catalog(org.mo.web.protocol.context.IWebContext, org.mo.jfa.face.logic.event.FEventPage)
    */
   @Override
   public String catalog(IWebContext context,
                         FEventPage page){
      return catalog(_eventTypeConsole, context, page, PAGE_CATALOG);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.event.IEventAction#delete(org.mo.web.protocol.context.IWebContext, org.mo.jfa.face.logic.event.FEventPage)
    */
   @Override
   public String delete(IWebContext context,
                        FEventPage page){
      return delete(_eventTypeConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.event.IEventAction#insert(org.mo.web.protocol.context.IWebContext, org.mo.jfa.face.logic.event.FEventPage)
    */
   @Override
   public String insert(IWebContext context,
                        FEventPage page){
      return insert(_eventTypeConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.event.IEventAction#list(org.mo.web.protocol.context.IWebContext, org.mo.jfa.face.logic.event.FEventPage)
    */
   @Override
   public String list(IWebContext context,
                      FEventPage page){
      return list(_eventTypeConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   /**
    * <T>遍历第二层所有子节点。</T>
    * 
    * @param xsearch 父节点
    * @param parentAttributes 父节点属性包
    * @param lgEventTypeDi 业务事件类型模块操作接口
    * @param lgEventTypeConfigDi 业务事件类型设置模块操作接口
    * @param lgEventGroupConfigDi 业务事件分组设置模块操作接口
    * @param syPropertyDi 系统属性模块操作接口
    */
   protected void searchOneChild(IXmlObject xsearch,
                                 IAttributes parentAttributes,
                                 ILgEventTypeDi lgEventTypeDi,
                                 ILgEventTypeConfigDi lgEventTypeConfigDi,
                                 ILgEventGroupConfigDi lgEventGroupConfigDi,
                                 ISyPropertyDi syPropertyDi){
      for(IXmlObject xds : xsearch.children()){
         if(RBoolean.parse(xds.innerGet("is_valid"))){
            IAttributes attributes = xds.toSimpleAttributes();
            if(XType.isInstance(xds)){
               attributes.set("group_name", parentAttributes.get("name"));
               attributes.set("name", parentAttributes.get("name") + "." + xds.innerGet("name"));
               lgEventTypeDi.doSyncType(_sessionConsole.makeLogic(), attributes);
            }else if(XGroupConfig.isInstance(xds)){
               attributes.set("group_name", parentAttributes.get("name"));
               lgEventGroupConfigDi.doSyncGroupConfig(_sessionConsole.makeLogic(), attributes);
            }
            if(xds.hasChild()){
               searchTwoChild(xds, attributes, lgEventTypeConfigDi, syPropertyDi);
            }
         }
      }
   }

   /**
    * <T>查找系统的属性分组。</T>
    * 
    * @param datasetName 数据集名称
    * @return 系统属性分组名称
    */
   protected String searchPropertyGroup(String datasetName){
      for(IXmlObject xds : _propertyConsole.list()){
         if(datasetName.equals(xds.innerGet("dataset"))){
            return xds.innerGet("name");
         }
      }
      return null;
   }

   /**
    * <T>遍历第三层所有子节点。</T>
    * 
    * @param xsearch 父节点
    * @param parentAttributes 父节点属性包
    * @param lgEventTypeConfigDi 业务事件类型设置模块操作接口
    * @param syPropertyDi 系统属性模块操作接口
    */
   protected void searchTwoChild(IXmlObject xsearch,
                                 IAttributes parentAttributes,
                                 ILgEventTypeConfigDi lgEventTypeConfigDi,
                                 ISyPropertyDi syPropertyDi){
      for(IXmlObject xds : xsearch.children()){
         if(!RBoolean.parse(xds.innerGet("is_valid"))){
            continue;
         }
         IAttributes attributes = xds.toSimpleAttributes();
         if(XTypeConfig.isInstance(xds)){
            attributes.set("type_name", parentAttributes.get("name"));
            lgEventTypeConfigDi.doSyncTypeConfig(_sessionConsole.makeLogic(), attributes);
         }else if(XProperty.isInstance(xds)){
            String propertyGroup = searchPropertyGroup("logic.event");
            if(null != propertyGroup){
               attributes.set("group_name", propertyGroup);
            }
            attributes.set("type_name", parentAttributes.get("name"));
            attributes.set("table_name", "LG_EVENT");
            //syPropertyDi.doSyncProperty(_sessionConsole.makeLogic(), attributes.pack());
         }
      }
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.event.IEventAction#sort(org.mo.web.protocol.context.IWebContext, org.mo.jfa.face.logic.event.FEventPage)
    */
   @Override
   public String sort(IWebContext context,
                      FEventPage page){
      return sort(_eventTypeConsole, context, page, IPublicPage.XOBJECT_SORT);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.event.IEventAction#syncEvent(org.mo.web.protocol.context.IWebContext, org.mo.logic.data.ILgEventGroupDi, org.mo.logic.data.ILgEventTypeDi, org.mo.logic.data.ILgEventTypeConfigDi, org.mo.logic.data.ILgEventGroupConfigDi, org.mo.logic.data.ISyPropertyDi, org.mo.jfa.face.logic.event.FEventPage)
    */
   @Override
   public String syncEvent(IWebContext context,
                           ILgEventGroupDi lgEventGroupDi,
                           ILgEventTypeDi lgEventTypeDi,
                           ILgEventTypeConfigDi lgEventTypeConfigDi,
                           ILgEventGroupConfigDi lgEventGroupConfigDi,
                           ISyPropertyDi syPropertyDi,
                           FEventPage page){
      String name = page.getSelectCollection();
      IXmlObject xds = _eventTypeConsole.find(name);
      // 同步开始时处理
      lgEventGroupDi.doSyncGroupBegin(_sessionConsole.makeLogic(), name);
      if(RBoolean.parse(xds.innerGet("is_valid"))){
         IAttributes attributes = xds.toSimpleAttributes();
         lgEventGroupDi.doSyncGroup(_sessionConsole.makeLogic(), attributes);
         if(xds.hasChild()){
            searchOneChild(xds, attributes, lgEventTypeDi, lgEventTypeConfigDi, lgEventGroupConfigDi, syPropertyDi);
         }
      }
      // 同步结束时处理
      //lgEventGroupDi.doSyncGroupEnd(_sessionConsole.makeLogic(), name);
      return IPublicPage.PROCESS_SUCCESS;
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.event.IEventAction#update(org.mo.web.protocol.context.IWebContext, org.mo.jfa.face.logic.event.FEventPage)
    */
   @Override
   public String update(IWebContext context,
                        FEventPage page){
      return update(_eventTypeConsole, context, page, IPublicPage.XOBJECT_FORM);
   }
}
