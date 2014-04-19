/*
 * @(#)IProcessAction.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.jfa.face.logic.process;

import org.mo.web.core.container.AContainer;

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
import org.mo.web.protocol.context.IWebContext;

/**
 * <T>业务流树目录各种操作动作接口类。</T>
 * <P>提供对业务流和业务流子任务用树目录形式进行增删改和同步等各种操作的接口。</P>
 * 
 * @author YJHUA
 * @version 1.00 - 2008/12/08
 */
public interface IProcessAction
{

   /**
    * <T>初始化整个树目录。</T>
    * 
    * @param context 页面连接
    * @param page 数据容器
    * @return 返回显示页面
    */
   String catalog(IWebContext context,
                  @AContainer(name = "page") FProcessPage page);

   /**
    * <T>执行树节点展开操作。</T>
    * 
    * @param context 页面连接
    * @param page 数据容器
    * @return 返回显示页面
    */
   String list(IWebContext context,
               @AContainer(name = "page") FProcessPage page);

   /**
    * <T>执行树节点的插入操作。</T>
    * 
    * @param context 页面环境
    * @param page 数据容器
    * @return 返回页面
    */
   String insert(IWebContext context,
                 @AContainer(name = "page") FProcessPage page);

   /**
    * <T>执行树节点更新操作。</T>
    * 
    * @param context 页面连接
    * @param page 数据容器
    * @return 返回显示页面
    */
   String update(IWebContext context,
                 @AContainer(name = "page") FProcessPage page);

   /**
    * <T>执行树目录节点的删除操作。</T>
    * 
    * @param context 页面连接
    * @param page 数据容器
    * @return 返回显示页面
    */
   String delete(IWebContext context,
                 @AContainer(name = "page") FProcessPage page);

   /**
    * <T>执行树节点排序操作。</T>
    * 
    * @param context 页面连接
    * @param page 数据容器
    * @return 返回显示页面
    */
   String sort(IWebContext context,
               @AContainer(name = "page") FProcessPage page);

   /** 
    * <T>把所有的业务流存储到数据库中。</T>
    * 
    * @param context 页面连接
    * @param lgProcessGroupDi 业务流分组模块操作接口
    * @param lgProcessTypeDi 业务流类型模块操作接口
    * @param lgProcessCdtDi 业务流条件模块操作接口
    * @param lgProcessEventDi 业务流事件模块操作接口
    * @param lgTaskTypeDi 任务类型模块操作接口
    * @param lgTaskCdtDi 任务条件模块操作接口
    * @param lgTaskEventDi 任务事件模块操作接口
    * @param lgTaskEventConfigDi 任务事件设置模块操作接口
    * @param lgProcessEventConfigDi 业务流事件设置模块操作接口
    * @param lgProcessTypeConfigDi 业务流类型设置模块操作接口
    * @param lgProcessCdtConfigDi 业务流条件设置模块操作接口
    * @param lgTaskTypeConfigDi 业务类型设置模块操作接口
    * @param lgTaskCdtConfigDi 业务条件设置模块操作接口
    * @param page 数据容器
    * @return 返回显示页面
    */
   String allSyncProcess(IWebContext context,
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
                         @AContainer(name = "page") FProcessPage page);

   /** 
    * <T>把当前的业务流存储到数据库中。</T>
    * 
    * @param context 页面连接
    * @param lgProcessGroupDi 业务流分组模块操作接口
    * @param lgProcessTypeDi 业务流类型模块操作接口
    * @param lgProcessCdtDi 业务流条件模块操作接口
    * @param lgProcessEventDi 业务流事件模块操作接口
    * @param lgTaskTypeDi 任务类型模块操作接口
    * @param lgTaskCdtDi 任务条件模块操作接口
    * @param lgTaskEventDi 任务事件模块操作接口
    * @param lgTaskEventConfigDi 任务事件设置模块操作接口
    * @param lgProcessEventConfigDi 业务流事件设置模块操作接口
    * @param lgProcessTypeConfigDi 业务流类型设置模块操作接口
    * @param lgProcessCdtConfigDi 业务流条件设置模块操作接口
    * @param lgTaskTypeConfigDi 业务类型设置模块操作接口
    * @param lgTaskCdtConfigDi 业务条件设置模块操作接口
    * @param page 数据容器
    * @return 返回显示页面
    */
   String syncProcess(IWebContext context,
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
                      @AContainer(name = "page") FProcessPage page);

}
