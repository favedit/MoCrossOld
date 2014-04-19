/*
 * @(#)FScheduletAction.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.jfa.face.logic.schedule;

import org.mo.com.lang.IAttributes;
import org.mo.com.lang.RBoolean;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectAction;
import org.mo.jfa.face.apl.page.IPublicPage;
import org.mo.logic.data.ILgScheduleDi;
import org.mo.logic.data.ILgScheduleGroupConfigDi;
import org.mo.logic.data.ILgScheduleGroupDi;
import org.mo.logic.data.ILgScheduleTimeDi;
import org.mo.logic.data.ILgScheduleTypeConfigDi;
import org.mo.logic.data.ILgScheduleTypeDi;
import org.mo.logic.data.ILgScheduleTypeEventDi;
import org.mo.logic.data.ISyPropertyDi;
import org.mo.logic.event.common.XType;
import org.mo.logic.event.common.XTypeConfig;
import org.mo.logic.process.common.XGroupConfig;
import org.mo.logic.property.ILogicPropertyConsole;
import org.mo.logic.schedule.ILogicScheduleGroupConsole;
import org.mo.logic.schedule.common.XSchedule;
import org.mo.logic.schedule.common.XTypeEvent;
import org.mo.logic.session.ILogicSessionConsole;
import org.mo.web.protocol.context.IWebContext;

/**
 * <T>业务事件树目录各种操作动作接口实体类。</T>
 * <P>提供对业务事件和业务事件用树目录形式进行增删改和同步等各种操作的接口。</P>
 * 
 * @author YJHUA
 * @version 1.00 - 2008/12/08
 */
public class FScheduleAction
      extends FAbsXmlObjectAction<IXmlObject>
      implements
         IScheduleAction
{

   @ALink
   protected ILogicScheduleGroupConsole _ScheduleGroupConsole;

   @ALink
   protected ILogicSessionConsole _sessionConsole;

   @ALink
   protected ILogicPropertyConsole _propertyConsole;

   public final static String PAGE_CATALOG = "Catalog";

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.event.IEventAction#allSyncEvent(org.mo.web.protocol.context.IWebContext, org.mo.logic.data.ILgEventGroupDi, org.mo.logic.data.ILgEventTypeDi, org.mo.logic.data.ILgEventTypeConfigDi, org.mo.logic.data.ILgEventGroupConfigDi, org.mo.logic.data.ISyPropertyDi, org.mo.jfa.face.logic.event.FEventPage)
    */
   @Override
   public String allSyncSchedule(IWebContext context,
                                 ILgScheduleGroupDi lgScheduleGroupDi,
                                 ILgScheduleTypeDi lgScheduleTypeDi,
                                 ILgScheduleTypeConfigDi lgScheduleTypeConfigDi,
                                 ILgScheduleGroupConfigDi lgScheduleGroupConfigDi,
                                 ILgScheduleTypeEventDi lgScheduleTypeEventDi,
                                 ILgScheduleTimeDi lgScheduleTimeDi,
                                 ILgScheduleDi lgScheduleDi,
                                 ISyPropertyDi syPropertyDi,
                                 FSchedulePage page){
      _scheduleSyncArgs = new FScheduleSyncArgs();
      _scheduleSyncArgs.setLgScheduleGroupDi(lgScheduleGroupDi);
      _scheduleSyncArgs.setLgScheduleGroupConfigDi(lgScheduleGroupConfigDi);
      _scheduleSyncArgs.setLgScheduleTypeDi(lgScheduleTypeDi);
      _scheduleSyncArgs.setLgScheduleTypeConfigDi(lgScheduleTypeConfigDi);
      _scheduleSyncArgs.setLgScheduleTypeEventDi(lgScheduleTypeEventDi);
      _scheduleSyncArgs.setLgScheduleDi(lgScheduleDi);
      _scheduleSyncArgs.getLgScheduleGroupDi().doSyncGroupBegin(_sessionConsole.makeLogic(), null);
      _scheduleSyncArgs.setLgScheduleTimeDi(lgScheduleTimeDi);
      for(IXmlObject xds : _ScheduleGroupConsole.list()){
         IAttributes attributes = xds.toSimpleAttributes();
         if(RBoolean.parse(attributes.get("is_valid"))){
            _scheduleSyncArgs.getLgScheduleGroupDi().doSyncGroup(_sessionConsole.makeLogic(), attributes);
            if(xds.hasChild()){
               searchOneChild(xds, attributes);
            }
         }
      }
      //_scheduleSyncArgs.getLgScheduleGroupDi().doSyncGroupEnd(_sessionConsole.makeLogic(), null);
      return IPublicPage.PROCESS_SUCCESS;
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.event.IEventAction#catalog(org.mo.web.protocol.context.IWebContext, org.mo.jfa.face.logic.event.FEventPage)
    */
   @Override
   public String catalog(IWebContext context,
                         FSchedulePage page){
      return catalog(_ScheduleGroupConsole, context, page, PAGE_CATALOG);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.event.IEventAction#delete(org.mo.web.protocol.context.IWebContext, org.mo.jfa.face.logic.event.FEventPage)
    */
   @Override
   public String delete(IWebContext context,
                        FSchedulePage page){
      return delete(_ScheduleGroupConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.event.IEventAction#insert(org.mo.web.protocol.context.IWebContext, org.mo.jfa.face.logic.event.FEventPage)
    */
   @Override
   public String insert(IWebContext context,
                        FSchedulePage page){
      return insert(_ScheduleGroupConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.event.IEventAction#list(org.mo.web.protocol.context.IWebContext, org.mo.jfa.face.logic.event.FEventPage)
    */
   @Override
   public String list(IWebContext context,
                      FSchedulePage page){
      return list(_ScheduleGroupConsole, context, page, IPublicPage.XOBJECT_FORM);
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
                                 IAttributes parentAttributes){
      for(IXmlObject xds : xsearch.children()){
         IAttributes attributes = xds.toSimpleAttributes();
         if(RBoolean.parse(attributes.get("is_valid"))){
            if(XType.isInstance(xds)){
               attributes.set("group_code", parentAttributes.get("name"));
               attributes.set("code", parentAttributes.get("name") + "." + xds.innerGet("name"));
               attributes.set("type_code", attributes.get("code"));
               _scheduleSyncArgs.getLgScheduleTypeDi().doSyncType(_sessionConsole.makeLogic(), attributes);
            }else if(XGroupConfig.isInstance(xds)){
               attributes.set("group_code", parentAttributes.get("name"));
               _scheduleSyncArgs.getLgScheduleGroupConfigDi().doSyncGroupConfig(_sessionConsole.makeLogic(), attributes);
            }
            if(xds.hasChild()){
               searchTwoChild(xds, attributes);
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
    * <T>遍历第四层所有子节点。</T>
    * 
    * @param xsearch 父节点
    * @param parentAttributes 父节点属性包
    * @param lgEventTypeConfigDi 业务事件类型设置模块操作接口
    * @param syPropertyDi 系统属性模块操作接口
    */
   protected void searchThreeChild(IXmlObject xsearch,
                                   IAttributes parentAttributes){
      for(IXmlObject xds : xsearch.children()){
         IAttributes attributes = xds.toSimpleAttributes();
         if(RBoolean.parse(attributes.get("is_valid"))){
            attributes.set("group_code", parentAttributes.get("group_code"));
            attributes.set("schedule_code", parentAttributes.get("code"));
            attributes.set("type_code", parentAttributes.get("type_code"));
            _scheduleSyncArgs.getLgScheduleTimeDi().doSyncTime(_sessionConsole.makeLogic(), attributes);
         }
      }
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
                                 IAttributes parentAttributes){
      for(IXmlObject xds : xsearch.children()){
         IAttributes attributes = xds.toSimpleAttributes();
         if(RBoolean.parse(attributes.get("is_valid"))){
            attributes.set("group_code", parentAttributes.get("group_code"));
            if(XTypeConfig.isInstance(xds)){
               attributes.set("type_code", parentAttributes.get("code"));
               _scheduleSyncArgs.getLgScheduleTypeConfigDi().doSyncTypeConfig(_sessionConsole.makeLogic(), attributes);
            }else if(XTypeEvent.isInstance(xds)){
               attributes.set("type_code", parentAttributes.get("code"));
               attributes.set("event_group_code", xds.innerGet("event_group_name"));
               attributes.set("event_type_code", xds.innerGet("event_group_name") + "." + xds.innerGet("event_type_name"));
               _scheduleSyncArgs.getLgScheduleTypeEventDi().doSyncTypeEvent(_sessionConsole.makeLogic(), attributes);
            }else if(XSchedule.isInstance(xds)){
               attributes.set("type_code", parentAttributes.get("code"));
               attributes.set("code", parentAttributes.get("type_code") + "." + xds.innerGet("name"));
               _scheduleSyncArgs.getLgScheduleDi().doSyncSchedule(_sessionConsole.makeLogic(), attributes);
            }
            if(xds.hasChild()){
               searchThreeChild(xds, attributes);
            }
         }
      }
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.event.IEventAction#sort(org.mo.web.protocol.context.IWebContext, org.mo.jfa.face.logic.event.FEventPage)
    */
   @Override
   public String sort(IWebContext context,
                      FSchedulePage page){
      return sort(_ScheduleGroupConsole, context, page, IPublicPage.XOBJECT_SORT);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.event.IEventAction#syncEvent(org.mo.web.protocol.context.IWebContext, org.mo.logic.data.ILgEventGroupDi, org.mo.logic.data.ILgEventTypeDi, org.mo.logic.data.ILgEventTypeConfigDi, org.mo.logic.data.ILgEventGroupConfigDi, org.mo.logic.data.ISyPropertyDi, org.mo.jfa.face.logic.event.FEventPage)
    */
   @Override
   public String syncSchedule(IWebContext context,
                              ILgScheduleGroupDi lgScheduleGroupDi,
                              ILgScheduleTypeDi lgScheduleTypeDi,
                              ILgScheduleTypeConfigDi lgScheduleTypeConfigDi,
                              ILgScheduleGroupConfigDi lgScheduleGroupConfigDi,
                              ILgScheduleTypeEventDi lgScheduleTypeEventDi,
                              ILgScheduleTimeDi lgScheduleTimeDi,
                              ILgScheduleDi lgScheduleDi,
                              ISyPropertyDi syPropertyDi,
                              FSchedulePage page){
      _scheduleSyncArgs = new FScheduleSyncArgs();
      _scheduleSyncArgs.setLgScheduleGroupDi(lgScheduleGroupDi);
      _scheduleSyncArgs.setLgScheduleGroupConfigDi(lgScheduleGroupConfigDi);
      _scheduleSyncArgs.setLgScheduleTypeDi(lgScheduleTypeDi);
      _scheduleSyncArgs.setLgScheduleTypeConfigDi(lgScheduleTypeConfigDi);
      _scheduleSyncArgs.setLgScheduleTypeEventDi(lgScheduleTypeEventDi);
      _scheduleSyncArgs.setLgScheduleDi(lgScheduleDi);
      _scheduleSyncArgs.setLgScheduleTimeDi(lgScheduleTimeDi);
      String name = page.getSelectCollection();
      IXmlObject xds = _ScheduleGroupConsole.find(name);
      IAttributes attributes = xds.toSimpleAttributes();
      _scheduleSyncArgs.getLgScheduleGroupDi().doSyncGroupBegin(_sessionConsole.makeLogic(), attributes.get("name"));
      attributes.set("code", attributes.get("name"));
      if(RBoolean.parse(attributes.get("is_valid"))){
         _scheduleSyncArgs.getLgScheduleGroupDi().doSyncGroup(_sessionConsole.makeLogic(), attributes);
         if(xds.hasChild()){
            searchOneChild(xds, attributes);
         }
      }
      //_scheduleSyncArgs.getLgScheduleGroupDi().doSyncGroupEnd(_sessionConsole.makeLogic(), attributes.get("name"));
      return IPublicPage.PROCESS_SUCCESS;
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.event.IEventAction#update(org.mo.web.protocol.context.IWebContext, org.mo.jfa.face.logic.event.FEventPage)
    */
   @Override
   public String update(IWebContext context,
                        FSchedulePage page){
      return update(_ScheduleGroupConsole, context, page, IPublicPage.XOBJECT_FORM);
   }
}
