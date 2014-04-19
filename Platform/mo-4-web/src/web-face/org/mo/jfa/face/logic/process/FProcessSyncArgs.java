/*
 * @(#)FProcessSyncArgs.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.jfa.face.logic.process;

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

/**
 * <T>存储业务流模块操作接口。</T>
 * <P>可以对业务流模块操作接口进行设置和获得操作。</P>
 * 
 * @author YJHUA
 * @version 1.00 - 2008/12/08
 */
public class FProcessSyncArgs
{

   // 业务流分组模块操作接口
   private ILgProcessGroupDi _lgProcessGroupDi;

   // 业务流类型模块操作接口
   private ILgProcessTypeDi _lgProcessTypeDi;

   // 业务流条件模块操作接口
   private ILgProcessCdtDi _lgProcessCdtDi;

   // 业务流事件模块操作接口
   private ILgProcessEventDi _lgProcessEventDi;

   // 任务类型模块操作接口
   private ILgTaskTypeDi _lgTaskTypeDi;

   // 任务条件模块操作接口
   private ILgTaskCdtDi _lgTaskCdtDi;

   // 任务事件模块操作接口
   private ILgTaskEventDi _lgTaskEventDi;

   // 任务事件设置模块操作接口
   private ILgTaskEventConfigDi _lgTaskEventConfigDi;

   // 流程事件设置模块操作接口
   private ILgProcessEventConfigDi _lgProcessEventConfigDi;

   // 流程类型设置模块操作接口
   private ILgProcessTypeConfigDi _lgProcessTypeConfigDi;

   // 流程条件设置模块操作接口
   private ILgProcessCdtConfigDi _lgProcessCdtConfigDi;

   // 任务类型设置模块操作接口
   private ILgTaskTypeConfigDi _lgTaskTypeConfigDi;

   // 任务条件设置模块操作接口
   private ILgTaskCdtConfigDi _lgTaskCdtConfigDi;

   /**
    * <T>设置业务流分组模块操作接口操作。</T>
    * 
    * @param lgProcessGroupDi 业务流分组模块操作接口
    */
   public void setLgProcessGroupDi(ILgProcessGroupDi lgProcessGroupDi){
      _lgProcessGroupDi = lgProcessGroupDi;
   }

   /**
    * <T>获得业务流分组模块操作接口操作。</T>
    * 
    */
   public ILgProcessGroupDi getLgProcessGroupDi(){
      return _lgProcessGroupDi;
   }

   /**
    * <T>设置业务流类型模块操作接口操作。</T>
    * 
    * @param lgProcessTypeDi 业务流类型模块操作接口
    */
   public void setLgProcessTypeDi(ILgProcessTypeDi lgProcessTypeDi){
      _lgProcessTypeDi = lgProcessTypeDi;
   }

   /**
    * <T>获得业务流类型模块操作接口操作。</T>
    * 
    */
   public ILgProcessTypeDi getLgProcessTypeDi(){
      return _lgProcessTypeDi;
   }

   /**
    * <T>设置业务流条件模块操作接口操作。</T>
    * 
    * @param lgProcessCdtDi 业务流条件模块操作接口
    */
   public void setLgProcessCdtDi(ILgProcessCdtDi lgProcessCdtDi){
      _lgProcessCdtDi = lgProcessCdtDi;
   }

   /**
    * <T>获得业务流条件模块操作接口操作。</T>
    * 
    */
   public ILgProcessCdtDi getLgProcessCdtDi(){
      return _lgProcessCdtDi;
   }

   /**
    * <T>设置业务流事件模块操作接口操作。</T>
    * 
    * @param lgProcessEventDi 业务流事件模块操作接口
    */
   public void setLgProcessEventDi(ILgProcessEventDi lgProcessEventDi){
      _lgProcessEventDi = lgProcessEventDi;
   }

   /**
    * <T>获得业务流事件模块操作接口操作。</T>
    * 
    */
   public ILgProcessEventDi getLgProcessEventDi(){
      return _lgProcessEventDi;
   }

   /**
    * <T>设置任务类型模块操作接口操作。</T>
    * 
    * @param lgTaskTypeDi 任务类型模块操作接口
    */
   public void setLgTaskTypeDi(ILgTaskTypeDi lgTaskTypeDi){
      _lgTaskTypeDi = lgTaskTypeDi;
   }

   /**
    * <T>获得任务类型模块操作接口操作。</T>
    * 
    */
   public ILgTaskTypeDi getLgTaskTypeDi(){
      return _lgTaskTypeDi;
   }

   /**
    * <T>设置任务条件模块操作接口操作。</T>
    * 
    * @param lgTaskCdtDi 任务条件模块操作接口
    */
   public void setLgTaskCdtDi(ILgTaskCdtDi lgTaskCdtDi){
      _lgTaskCdtDi = lgTaskCdtDi;
   }

   /**
    * <T>获得任务条件模块操作接口操作。</T>
    * 
    */
   public ILgTaskCdtDi getLgTaskCdtDi(){
      return _lgTaskCdtDi;
   }

   /**
    * <T>设置任务事件模块操作接口操作。</T>
    * 
    * @param lgTaskEventDi 任务事件模块操作接口
    */
   public void setLgTaskEventDi(ILgTaskEventDi lgTaskEventDi){
      _lgTaskEventDi = lgTaskEventDi;
   }

   /**
    * <T>获得任务事件模块操作接口操作。</T>
    * 
    */
   public ILgTaskEventDi getLgTaskEventDi(){
      return _lgTaskEventDi;
   }

   /**
    * <T>设置任务事件设置模块操作接口操作。</T>
    * 
    * @param lgTaskEventConfigDi 任务事件设置模块操作接口
    */
   public void setLgTaskEventConfigDi(ILgTaskEventConfigDi lgTaskEventConfigDi){
      _lgTaskEventConfigDi = lgTaskEventConfigDi;
   }

   /**
    * <T>获得任务事件设置模块操作接口操作。</T>
    * 
    */
   public ILgTaskEventConfigDi getLgTaskEventConfigDi(){
      return _lgTaskEventConfigDi;
   }

   /**
    * <T>设置流程事件设置模块操作接口操作。</T>
    * 
    * @param lgProcessEventConfigDi 流程事件设置模块操作接口
    */
   public void setLgProcessEventConfigDi(ILgProcessEventConfigDi lgProcessEventConfigDi){
      _lgProcessEventConfigDi = lgProcessEventConfigDi;
   }

   /**
    * <T>获得流程事件设置模块操作接口操作。</T>
    * 
    */
   public ILgProcessEventConfigDi getLgProcessEventConfigDi(){
      return _lgProcessEventConfigDi;
   }

   /**
    * <T>设置业务流类型设置模块操作接口操作。</T>
    * 
    * @param lgProcessTypeConfigDi 业务流类型设置模块操作接口
    */
   public void setLgProcessTypeConfigDi(ILgProcessTypeConfigDi lgProcessTypeConfigDi){
      _lgProcessTypeConfigDi = lgProcessTypeConfigDi;
   }

   /**
    * <T>获得业务流类型设置模块操作接口操作。</T>
    * 
    */
   public ILgProcessTypeConfigDi getLgProcessTypeConfigDi(){
      return _lgProcessTypeConfigDi;
   }

   /**
    * <T>设置业务流条件设置模块操作接口操作。</T>
    * 
    * @param lgProcessCdtConfigDi 业务流条件设置模块操作接口
    */
   public void setLgProcessCdtConfigDi(ILgProcessCdtConfigDi lgProcessCdtConfigDi){
      _lgProcessCdtConfigDi = lgProcessCdtConfigDi;
   }

   /**
    * <T>获得业务流条件设置模块操作接口操作。</T>
    * 
    */
   public ILgProcessCdtConfigDi getLgProcessCdtConfigDi(){
      return _lgProcessCdtConfigDi;
   }

   /**
    * <T>设置任务类型模块操作接口操作。</T>
    * 
    * @param lgTaskTypeConfigDi 任务类型设置模块操作接口
    */
   public void setLgTaskTypeConfigDi(ILgTaskTypeConfigDi lgTaskTypeConfigDi){
      _lgTaskTypeConfigDi = lgTaskTypeConfigDi;
   }

   /**
    * <T>获得任务类型模块操作接口操作。</T>
    * 
    */
   public ILgTaskTypeConfigDi getLgTaskTypeConfigDi(){
      return _lgTaskTypeConfigDi;
   }

   /**
    * <T>设置任务条件设置模块操作接口操作。</T>
    * 
    * @param lgTaskCdtConfigDi 任务条件设置模块操作接口
    */
   public void setLgTaskCdtConfigDi(ILgTaskCdtConfigDi lgTaskCdtConfigDi){
      _lgTaskCdtConfigDi = lgTaskCdtConfigDi;
   }

   /**
    * <T>获得任务条件设置模块操作接口操作。</T>
    * 
    */
   public ILgTaskCdtConfigDi getLgTaskCdtConfigDi(){
      return _lgTaskCdtConfigDi;
   }
}
