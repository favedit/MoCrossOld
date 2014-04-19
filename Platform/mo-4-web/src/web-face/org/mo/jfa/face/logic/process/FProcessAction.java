/*
 * @(#)FProcessAction.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.jfa.face.logic.process;

import org.mo.com.lang.IAttributes;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectAction;
import org.mo.jfa.face.apl.page.IPublicPage;
import org.mo.logic.data.ILgProcessCdtConfigDi;
import org.mo.logic.data.ILgProcessCdtDi;
import org.mo.logic.data.ILgProcessEventConfigDi;
import org.mo.logic.data.ILgProcessEventDi;
import org.mo.logic.data.ILgProcessGroupDi;
import org.mo.logic.data.ILgProcessTypeConfigDi;
import org.mo.logic.data.ILgProcessTypeDi;
import org.mo.logic.data.ILgTaskCdtConfigDi;
import org.mo.logic.data.ILgTaskCdtDi;
import org.mo.logic.data.ILgTaskEventConfigDi;
import org.mo.logic.data.ILgTaskEventDi;
import org.mo.logic.data.ILgTaskTypeConfigDi;
import org.mo.logic.data.ILgTaskTypeDi;
import org.mo.logic.process.ILogicProcessConsole;
import org.mo.logic.process.common.XProcess;
import org.mo.logic.process.common.XProcessCondition;
import org.mo.logic.process.common.XProcessEvent;
import org.mo.logic.process.common.XProcessResult;
import org.mo.logic.process.common.XTask;
import org.mo.logic.process.common.XTaskCondition;
import org.mo.logic.process.common.XTaskEvent;
import org.mo.logic.process.common.XTaskResult;
import org.mo.logic.session.ILogicSessionConsole;
import org.mo.web.protocol.context.IWebContext;

/**
 * <T>业务流树目录各种操作动作实体类。</T>
 * <P>提供对业务流和业务流子任务用树目录形式进行增删改和同步等各种操作的接口。</P>
 * 
 * @author YJHUA
 * @version 1.00 - 2008/12/08
 */
public class FProcessAction
      extends FAbsXmlObjectAction<IXmlObject>
      implements
         IProcessAction
{

   /// 转向页面的名称
   public final static String PAGE_CATALOG = "Catalog";

   // 业务流控制台对象
   @ALink
   protected ILogicProcessConsole _processConsole;

   // 业务控制台对象
   @ALink
   protected ILogicSessionConsole _sessionConsole;

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.process.IProcessAction#allSyncProcess(org.mo.web.protocol.context.IWebContext, org.mo.logic.data.ILgProcessGroupDi,
    *  org.mo.logic.data.ILgProcessTypeDi, org.mo.logic.data.ILgProcessCdtDi, org.mo.logic.data.ILgProcessEventDi, org.mo.logic.data.ILgTaskTypeDi,
    *  org.mo.logic.data.ILgTaskCdtDi, org.mo.logic.data.ILgTaskEventDi, org.mo.logic.data.ILgTaskEventConfigDi, org.mo.logic.data.ILgProcessEventConfigDi,
    *  org.mo.logic.data.ILgProcessTypeConfigDi, org.mo.logic.data.ILgProcessCdtConfigDi, org.mo.logic.data.ILgTaskTypeConfigDi, 
    *  org.mo.logic.data.ILgTaskCdtConfigDi, org.mo.jfa.face.logic.process.FProcessPage)
    */
   @Override
   public String allSyncProcess(IWebContext context,
                                ILgProcessGroupDi lgProcessGroupDi,
                                ILgProcessTypeDi lgProcessTypeDi,
                                ILgProcessCdtDi lgProcessCdtDi,
                                ILgProcessEventDi lgProcessEventDi,
                                ILgTaskTypeDi lgTaskTypeDi,
                                ILgTaskCdtDi lgTaskCdtDi,
                                ILgTaskEventDi lgTaskEventDi,
                                ILgTaskEventConfigDi lgTaskEventConfigDi,
                                ILgProcessEventConfigDi lgProcessEventConfigDi,
                                ILgProcessTypeConfigDi lgProcessTypeConfigDi,
                                ILgProcessCdtConfigDi lgProcessCdtConfigDi,
                                ILgTaskTypeConfigDi lgTaskTypeConfigDi,
                                ILgTaskCdtConfigDi lgTaskCdtConfigDi,
                                FProcessPage page){
      _processSyncArgs = new FProcessSyncArgs();
      _processSyncArgs.setLgProcessGroupDi(lgProcessGroupDi);
      _processSyncArgs.setLgProcessTypeDi(lgProcessTypeDi);
      _processSyncArgs.setLgProcessCdtDi(lgProcessCdtDi);
      _processSyncArgs.setLgProcessEventDi(lgProcessEventDi);
      _processSyncArgs.setLgProcessTypeConfigDi(lgProcessTypeConfigDi);
      _processSyncArgs.setLgProcessCdtConfigDi(lgProcessCdtConfigDi);
      _processSyncArgs.setLgProcessEventConfigDi(lgProcessEventConfigDi);
      _processSyncArgs.setLgTaskTypeDi(lgTaskTypeDi);
      _processSyncArgs.setLgTaskTypeConfigDi(lgTaskTypeConfigDi);
      _processSyncArgs.setLgTaskCdtDi(lgTaskCdtDi);
      _processSyncArgs.setLgTaskCdtConfigDi(lgTaskCdtConfigDi);
      _processSyncArgs.setLgTaskEventDi(lgTaskEventDi);
      _processSyncArgs.setLgTaskEventConfigDi(lgTaskEventConfigDi);
      for(IXmlObject xds : _processConsole.list()){
         IAttributes attributes = xds.toSimpleAttributes();
         _processSyncArgs.getLgProcessGroupDi().doSyncGroup(_sessionConsole.makeLogic(), attributes);
         if(xds.hasChild()){
            doProcess(xds);
         }
      }
      return IPublicPage.PROCESS_SUCCESS;
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.process.IProcessAction#catalog(org.mo.web.protocol.context.IWebContext, org.mo.jfa.face.logic.process.FProcessPage)
    */
   @Override
   public String catalog(IWebContext context,
                         FProcessPage page){
      return catalog(_processConsole, context, page, PAGE_CATALOG);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.process.IProcessAction#delete(org.mo.web.protocol.context.IWebContext, org.mo.jfa.face.logic.process.FProcessPage)
    */
   @Override
   public String delete(IWebContext context,
                        FProcessPage page){
      return delete(_processConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   /**
    * <T>查找第二层（业务流类型和分组设置）节点并存储到数据库中。</T>
    * <P>根据类型表的字段打包，并传到数据库端。</P>
    * <P>根据分组设置表的字段打包，并传到数据库端。</P>
    * 
    * @param xsearch 父节点对象
    */
   protected void doProcess(IXmlObject xsearch){
      for(IXmlObject xProcess : xsearch.children()){
         IAttributes attributes = xProcess.toSimpleAttributes();
         if(XProcess.isInstance(xProcess)){
            attributes.set("name", xsearch.innerGet("name") + "." + xProcess.innerGet("name"));
            attributes.set("group_name", xsearch.innerGet("name"));
            _processSyncArgs.getLgProcessTypeDi().doSyncType(_sessionConsole.makeLogic(), attributes);
            if(xProcess.hasChild()){
               for(IXmlObject xChild : xProcess.children()){
                  if(XProcessCondition.isInstance(xChild)){
                     doProcessCondition(xChild, attributes);
                  }else if(XTask.isInstance(xChild)){
                     doTask(xChild, attributes);
                  }else{
                     // 调用流程类型设置
                     IAttributes attConfig = xChild.toSimpleAttributes();
                     attConfig.set("process_type_name", attributes.get("name"));
                     _processSyncArgs.getLgProcessTypeConfigDi().doSyncTypeConfig(_sessionConsole.makeLogic(), attConfig);
                  }
               }
            }
         }else{
            attributes.set("group_name", xsearch.innerGet("name"));
            //lgProcessGroupConfig.doSyncType(_sessionConsole.makeLogic(), attributes);
         }
      }
   }

   /**
    * <T>查找第三，四，五层的业务流有关节点并存储到数据库中。</T>
    * <P title='第三层'>第三层节点是业务条件节点。</P>
    * <P title='第四层'>第四层节点是业务条件设置节点，条件结果节点（该节点可有可无）或业务流事件节点。</P>
    * <P title='第五层'>第五层节点是业务事件设置节点和事件设置节点。</P>
    * 
    * @param xsearch 父节点对象
    * @param attributes 业务流类型属性包
    */
   protected void doProcessCondition(IXmlObject xCondition,
                                     IAttributes attributes){
      IAttributes attCondition = xCondition.toSimpleAttributes();
      attCondition.set("process_type_name", attributes.get("name"));
      attCondition.set("process_order", String.valueOf(++_processConditionOrder));
      _processSyncArgs.getLgProcessCdtDi().doSyncProcessCondition(_sessionConsole.makeLogic(), attCondition);
      if(xCondition.hasChild()){
         for(IXmlObject xChild : xCondition.children()){
            if(XProcessResult.isInstance(xChild)){
               if(xChild.hasChild()){
                  for(IXmlObject xEvent : xChild.children()){
                     IAttributes attEvent = xEvent.toSimpleAttributes();
                     attEvent.set("condition_name", attCondition.get("name"));
                     attEvent.set("process_result", xChild.innerGet("data_value"));
                     attEvent.set("process_type_name", attCondition.get("process_type_name"));
                     attEvent.set("process_order", String.valueOf(++_processEventOrder));
                     String eventTypeName = xEvent.innerGet("event_group_name") + "." + xEvent.innerGet("event_type_name");
                     attEvent.set("event_type_name", eventTypeName);
                     _processSyncArgs.getLgProcessEventDi().doSyncProcessEvent(_sessionConsole.makeLogic(), attEvent);
                     if(xEvent.hasChild()){
                        doProcessEventConfig(xEvent, attEvent);
                     }
                  }
               }
            }else if(XProcessEvent.isInstance(xChild)){
               IAttributes attEvent = xChild.toSimpleAttributes();
               attEvent.set("condition_name", attCondition.get("name"));
               attEvent.set("process_type_name", attributes.get("process_type_name"));
               attEvent.set("process_order", String.valueOf(++_processEventOrder));
               String eventTypeName = xChild.innerGet("event_group_name") + "." + xChild.innerGet("event_type_name");
               attEvent.set("event_type_name", eventTypeName);
               _processSyncArgs.getLgProcessEventDi().doSyncProcessEvent(_sessionConsole.makeLogic(), attEvent);
               if(xChild.hasChild()){
                  doProcessEventConfig(xChild, attEvent);
               }
            }else{
               // 调用流程条件设置
               IAttributes attConfig = xChild.toSimpleAttributes();
               attConfig.set("process_type_name", attCondition.get("process_type_name"));
               attConfig.set("condition_name", attCondition.get("name"));
               _processSyncArgs.getLgProcessCdtConfigDi().doSyncConditionConfig(_sessionConsole.makeLogic(), attConfig);
            }

         }
      }
   }

   /**
    * <T>处理流程事件设置并存储到数据库中。</T>
    * 
    * @param xEvent 父节点对象
    * @param attributes 任务事件属性包
    */
   protected void doProcessEventConfig(IXmlObject xEvent,
                                       IAttributes attributes){
      for(IXmlObject xConfig : xEvent.children()){
         IAttributes attConfig = xConfig.toSimpleAttributes();
         attConfig.set("event_name", attributes.get("name"));
         attConfig.set("condition_name", attributes.get("condition_name"));
         attConfig.set("process_type_name", attributes.get("process_type_name"));
         _processSyncArgs.getLgProcessEventConfigDi().doSyncEventConfig(_sessionConsole.makeLogic(), attConfig);
      }
   }

   /**
    * <T>查找第三任务节点和四层任务类型设置节点并存储到数据库中。</T>
    * <P title='第三层'>第三层节点是任务类型节点。</P>
    * <P title='第四层'>第四层节点是任务类型设置节点。</P>
    * 
    * @param xsearch 父节点对象
    * @param attributes 业务流类型属性包
    */
   protected void doTask(IXmlObject xTask,
                         IAttributes attributes){
      IAttributes attTask = xTask.toSimpleAttributes();
      attTask.set("process_type_name", attributes.get("name"));
      _processSyncArgs.getLgTaskTypeDi().doSyncType(_sessionConsole.makeLogic(), attTask);
      if(xTask.hasChild()){
         for(IXmlObject xChild : xTask.children()){
            if(XTaskCondition.isInstance(xChild)){
               doTaskCondition(xChild, attTask);
            }else{
               // 调用typeConfig包的代理方法
               IAttributes attTypeConfig = xChild.toNode(EXmlConfig.Simple).attributes();
               attTypeConfig.set("task_type_name", attTask.get("name"));
               _processSyncArgs.getLgTaskTypeConfigDi().doSyncTypeConfig(_sessionConsole.makeLogic(), attTypeConfig);
            }
         }
      }
   }

   /**
    * <T>查找第四任务条件节点和该层的子节点并存储到数据库中。</T>
    * <P title='第四层'>第四层节点是任务条件节点。</P>
    * <P title='第五层'>第五层节点是任务条件设置节点，条件结果（可无）。</P>
    * <P title='第六层'>第六层节点是任务事件节点或条件结果没有，则是事件设置节点。</P>
    * <P title='第七层'>第七层节点是条件结果节点如果存在则该层为事件设置节点。</P>
    * 
    * @param xsearch 父节点对象
    * @param attributes 任务类型属性包
    */
   protected void doTaskCondition(IXmlObject xCondition,
                                  IAttributes attributes){
      IAttributes attCondition = xCondition.toSimpleAttributes();
      attCondition.set("task_type_name", attributes.get("name"));
      attCondition.set("process_order", String.valueOf(++_taskConditionOrder));
      //_processSyncArgs.getLgTaskCdtDi().doSyncTaskCondition(_sessionConsole.makeLogic(), attCondition);
      if(xCondition.hasChild()){
         for(IXmlObject xChild : xCondition.children()){
            if(XTaskResult.isInstance(xChild)){
               if(xChild.hasChild()){
                  for(IXmlObject xEvent : xChild.children()){
                     IAttributes attEvent = xEvent.toSimpleAttributes();
                     attEvent.set("condition_name", attCondition.get("name"));
                     attEvent.set("process_result", xChild.innerGet("data_value"));
                     attEvent.set("task_type_name", attCondition.get("task_type_name"));
                     attEvent.set("process_order", String.valueOf(++_taskEventOrder));
                     String eventTypeName = xEvent.innerGet("event_group_name") + "." + xEvent.innerGet("event_type_name");
                     attEvent.set("event_type_name", eventTypeName);
                     _processSyncArgs.getLgTaskEventDi().doSyncTaskEvent(_sessionConsole.makeLogic(), attEvent);
                     if(xEvent.hasChild()){
                        doTaskEventConfig(xEvent, attEvent);
                     }
                  }
               }
            }else if(XTaskEvent.isInstance(xChild)){
               IAttributes attEvent = xChild.toSimpleAttributes();
               attEvent.set("condition_name", attCondition.get("name"));
               attEvent.set("task_type_name", attCondition.get("task_type_name"));
               attEvent.set("process_order", String.valueOf(++_taskEventOrder));
               String eventTypeName = xChild.innerGet("event_group_name") + "." + xChild.innerGet("event_type_name");
               attEvent.set("event_type_name", eventTypeName);
               _processSyncArgs.getLgTaskEventDi().doSyncTaskEvent(_sessionConsole.makeLogic(), attEvent);
               if(xChild.hasChild()){
                  doTaskEventConfig(xChild, attEvent);
               }
            }else{
               // 调用流程条件设置
               IAttributes attConfig = xChild.toSimpleAttributes();
               attConfig.set("task_type_name", attCondition.get("task_type_name"));
               attConfig.set("condition_name", attCondition.get("name"));
               //_processSyncArgs.getLgTaskCdtConfigDi().doSyncConditionConfig(_sessionConsole.makeLogic(), attConfig);
            }

         }
      }
   }

   /**
    * <T>处理任务事件设置并存储到数据库中。</T>
    * 
    * @param xEvent 父节点对象
    * @param attributes 任务事件属性包
    */
   protected void doTaskEventConfig(IXmlObject xEvent,
                                    IAttributes attributes){
      for(IXmlObject xConfig : xEvent.children()){
         IAttributes attConfig = xConfig.toSimpleAttributes();
         attConfig.set("event_name", attributes.get("name"));
         attConfig.set("condition_name", attributes.get("condition_name"));
         attConfig.set("task_type_name", attributes.get("task_type_name"));
         _processSyncArgs.getLgTaskEventConfigDi().doSyncEventConfig(_sessionConsole.makeLogic(), attConfig);
      }
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.process.IProcessAction#insert(org.mo.web.protocol.context.IWebContext, org.mo.jfa.face.logic.process.FProcessPage)
    */
   @Override
   public String insert(IWebContext context,
                        FProcessPage page){
      return insert(_processConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.process.IProcessAction#list(org.mo.web.protocol.context.IWebContext, org.mo.jfa.face.logic.process.FProcessPage)
    */
   @Override
   public String list(IWebContext context,
                      FProcessPage page){
      return list(_processConsole, context, page, IPublicPage.XOBJECT_FORM);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.process.IProcessAction#sort(org.mo.web.protocol.context.IWebContext, org.mo.jfa.face.logic.process.FProcessPage)
    */
   @Override
   public String sort(IWebContext context,
                      FProcessPage page){
      return sort(_processConsole, context, page, IPublicPage.XOBJECT_SORT);
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.process.IProcessAction#syncProcess(org.mo.web.protocol.context.IWebContext, org.mo.logic.data.ILgProcessGroupDi,
    *  org.mo.logic.data.ILgProcessTypeDi, org.mo.logic.data.ILgProcessCdtDi, org.mo.logic.data.ILgProcessEventDi, org.mo.logic.data.ILgTaskTypeDi, 
    *  org.mo.logic.data.ILgTaskCdtDi, org.mo.logic.data.ILgTaskEventDi, org.mo.logic.data.ILgTaskEventConfigDi, org.mo.logic.data.ILgProcessEventConfigDi, 
    *  org.mo.logic.data.ILgProcessTypeConfigDi, org.mo.logic.data.ILgProcessCdtConfigDi, org.mo.logic.data.ILgTaskTypeConfigDi, org.mo.logic.data.ILgTaskCdtConfigDi, 
    *  org.mo.jfa.face.logic.process.FProcessPage)
    */
   @Override
   public String syncProcess(IWebContext context,
                             ILgProcessGroupDi lgProcessGroupDi,
                             ILgProcessTypeDi lgProcessTypeDi,
                             ILgProcessCdtDi lgProcessCdtDi,
                             ILgProcessEventDi lgProcessEventDi,
                             ILgTaskTypeDi lgTaskTypeDi,
                             ILgTaskCdtDi lgTaskCdtDi,
                             ILgTaskEventDi lgTaskEventDi,
                             ILgTaskEventConfigDi lgTaskEventConfigDi,
                             ILgProcessEventConfigDi lgProcessEventConfigDi,
                             ILgProcessTypeConfigDi lgProcessTypeConfigDi,
                             ILgProcessCdtConfigDi lgProcessCdtConfigDi,
                             ILgTaskTypeConfigDi lgTaskTypeConfigDi,
                             ILgTaskCdtConfigDi lgTaskCdtConfigDi,
                             FProcessPage page){
      _processSyncArgs = new FProcessSyncArgs();
      _processSyncArgs.setLgProcessGroupDi(lgProcessGroupDi);
      _processSyncArgs.setLgProcessTypeDi(lgProcessTypeDi);
      _processSyncArgs.setLgProcessCdtDi(lgProcessCdtDi);
      _processSyncArgs.setLgProcessEventDi(lgProcessEventDi);
      _processSyncArgs.setLgProcessTypeConfigDi(lgProcessTypeConfigDi);
      _processSyncArgs.setLgProcessCdtConfigDi(lgProcessCdtConfigDi);
      _processSyncArgs.setLgProcessEventConfigDi(lgProcessEventConfigDi);
      _processSyncArgs.setLgTaskTypeDi(lgTaskTypeDi);
      _processSyncArgs.setLgTaskTypeConfigDi(lgTaskTypeConfigDi);
      _processSyncArgs.setLgTaskCdtDi(lgTaskCdtDi);
      _processSyncArgs.setLgTaskCdtConfigDi(lgTaskCdtConfigDi);
      _processSyncArgs.setLgTaskEventDi(lgTaskEventDi);
      _processSyncArgs.setLgTaskEventConfigDi(lgTaskEventConfigDi);
      String name = page.getSelectCollection();
      IXmlObject xGroup = _processConsole.get(name);
      _processSyncArgs.getLgProcessGroupDi().doSyncGroup(_sessionConsole.makeLogic(), xGroup.toSimpleAttributes());
      if(xGroup.hasChild()){
         doProcess(xGroup);
      }
      return IPublicPage.PROCESS_SUCCESS;
   }

   /* (non-Javadoc)
    * @see org.mo.jfa.face.logic.process.IProcessAction#update(org.mo.web.protocol.context.IWebContext, org.mo.jfa.face.logic.process.FProcessPage)
    */
   @Override
   public String update(IWebContext context,
                        FProcessPage page){
      return update(_processConsole, context, page, IPublicPage.XOBJECT_FORM);
   }
}
