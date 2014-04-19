/*
 * @(#)FProcessSyncArgs.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.jfa.face.logic.schedule;

import org.mo.logic.data.ILgScheduleDi;
import org.mo.logic.data.ILgScheduleGroupConfigDi;
import org.mo.logic.data.ILgScheduleGroupDi;
import org.mo.logic.data.ILgScheduleTimeDi;
import org.mo.logic.data.ILgScheduleTypeConfigDi;
import org.mo.logic.data.ILgScheduleTypeDi;
import org.mo.logic.data.ILgScheduleTypeEventDi;
import org.mo.logic.data.ISyPropertyDi;

/**
 * <T>存储业务流模块操作接口。</T>
 * <P>可以对业务流模块操作接口进行设置和获得操作。</P>
 * 
 * @author YJHUA
 * @version 1.00 - 2008/12/08
 */
public class FScheduleSyncArgs
{

   // 业务流分组模块操作接口
   private ILgScheduleGroupDi _lgScheduleGroupDi;

   // 业务流类型模块操作接口
   private ILgScheduleTypeDi _lgScheduleTypeDi;

   // 业务流事件模块操作接口
   private ILgScheduleTypeEventDi _lgScheduleTypeEventDi;

   // 流程类型设置模块操作接口
   private ILgScheduleTypeConfigDi _lgScheduleTypeConfigDi;

   // 流程类型设置模块操作接口
   private ILgScheduleGroupConfigDi _lgScheduleGroupConfigDi;

   // 流程类型设置模块操作接口
   private ILgScheduleTimeDi _lgScheduleTimeDi;

   // 流程类型设置模块操作接口
   private ILgScheduleDi _lgScheduleDi;

   // 流程类型设置模块操作接口
   private ISyPropertyDi _syPropertyDi;

   /**
    * <T>设置业务流分组模块操作接口操作。</T>
    * 
    * @param lgScheduleGroupDi 业务流分组模块操作接口
    */
   public void setLgScheduleGroupDi(ILgScheduleGroupDi lgScheduleGroupDi){
      _lgScheduleGroupDi = lgScheduleGroupDi;
   }

   /**
    * <T>获得业务流分组模块操作接口操作。</T>
    * 
    */
   public ILgScheduleGroupDi getLgScheduleGroupDi(){
      return _lgScheduleGroupDi;
   }

   /**
    * <T>设置业务流类型模块操作接口操作。</T>
    * 
    * @param lgScheduleTypeDi 业务流类型模块操作接口
    */
   public void setLgScheduleTypeDi(ILgScheduleTypeDi lgScheduleTypeDi){
      _lgScheduleTypeDi = lgScheduleTypeDi;
   }

   /**
    * <T>获得业务流类型模块操作接口操作。</T>
    * 
    */
   public ILgScheduleTypeDi getLgScheduleTypeDi(){
      return _lgScheduleTypeDi;
   }

   /**
    * <T>设置业务流类型设置模块操作接口操作。</T>
    * 
    * @param lgScheduleTypeConfigDi 业务流类型设置模块操作接口
    */
   public void setLgScheduleTypeConfigDi(ILgScheduleTypeConfigDi lgScheduleTypeConfigDi){
      _lgScheduleTypeConfigDi = lgScheduleTypeConfigDi;
   }

   /**
    * <T>获得业务流类型设置模块操作接口操作。</T>
    * 
    */
   public ILgScheduleTypeConfigDi getLgScheduleTypeConfigDi(){
      return _lgScheduleTypeConfigDi;
   }

   /**
    * <T>设置业务流类型设置模块操作接口操作。</T>
    * 
    * @param lgScheduleTypeConfigDi 业务流类型设置模块操作接口
    */
   public void setLgScheduleGroupConfigDi(ILgScheduleGroupConfigDi lgScheduleGroupConfigDi){
      _lgScheduleGroupConfigDi = lgScheduleGroupConfigDi;
   }

   /**
    * <T>获得业务流类型设置模块操作接口操作。</T>
    * 
    */
   public ILgScheduleGroupConfigDi getLgScheduleGroupConfigDi(){
      return _lgScheduleGroupConfigDi;
   }

   /**
    * <T>设置业务流类型设置模块操作接口操作。</T>
    * 
    * @param lgScheduleTypeConfigDi 业务流类型设置模块操作接口
    */
   public void setLgScheduleTypeEventDi(ILgScheduleTypeEventDi lgScheduleTypeEventDi){
      _lgScheduleTypeEventDi = lgScheduleTypeEventDi;
   }

   /**
    * <T>获得业务流类型设置模块操作接口操作。</T>
    * 
    */
   public ILgScheduleTypeEventDi getLgScheduleTypeEventDi(){
      return _lgScheduleTypeEventDi;
   }

   public void setSyPropertyDi(ISyPropertyDi syPropertyDi){
      _syPropertyDi = syPropertyDi;
   }

   public ISyPropertyDi getSyPropertyDi(){
      return _syPropertyDi;
   }

   public void setLgScheduleDi(ILgScheduleDi lgScheduleDi){
      _lgScheduleDi = lgScheduleDi;
   }

   public ILgScheduleDi getLgScheduleDi(){
      return _lgScheduleDi;
   }

   public void setLgScheduleTimeDi(ILgScheduleTimeDi lgScheduleTimeDi){
      _lgScheduleTimeDi = lgScheduleTimeDi;
   }

   public ILgScheduleTimeDi getLgScheduleTimeDi(){
      return _lgScheduleTimeDi;
   }
}
