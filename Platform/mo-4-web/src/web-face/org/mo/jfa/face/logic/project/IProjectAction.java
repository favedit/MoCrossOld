/*
 * @(#)IEventAction.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.jfa.face.logic.project;

import org.mo.logic.data.ISyPropertyDi;
import org.mo.web.core.container.AContainer;
import org.mo.web.protocol.context.IWebContext;

/**
 * <T>业务事件树目录各种操作动作接口类。</T>
 * <P>提供对业务事件和业务事件用树目录形式进行增删改和同步等各种操作的接口。</P>
 * 
 * @author YJHUA
 * @version 1.00 - 2008/12/08
 */
public interface IProjectAction
{

   /**
    * <T>初始化整个树目录。</T>
    * 
    * @param context 页面连接
    * @param page 数据容器
    * @return 返回显示页面
    */
   String catalog(IWebContext context,
                  @AContainer(name = "page") FProjectPage page);

   /**
    * <T>执行树节点展开操作。</T>
    * 
    * @param context 页面连接
    * @param page 数据容器
    * @return 返回显示页面
    */
   String list(IWebContext context,
               @AContainer(name = "page") FProjectPage page);

   /**
    * <T>执行树节点的插入操作。</T>
    * 
    * @param context 页面环境
    * @param page 数据容器
    * @return 返回页面
    */
   String insert(IWebContext context,
                 @AContainer(name = "page") FProjectPage page);

   /**
    * <T>执行树节点更新操作。</T>
    * 
    * @param context 页面连接
    * @param page 数据容器
    * @return 返回显示页面
    */
   String update(IWebContext context,
                 @AContainer(name = "page") FProjectPage page);

   /**
    * <T>执行树目录节点的删除操作。</T>
    * 
    * @param context 页面连接
    * @param page 数据容器
    * @return 返回显示页面
    */
   String delete(IWebContext context,
                 @AContainer(name = "page") FProjectPage page);

   /**
    * <T>执行树节点排序操作。</T>
    * 
    * @param context 页面连接
    * @param page 数据容器
    * @return 返回显示页面
    */
   String sort(IWebContext context,
               @AContainer(name = "page") FProjectPage page);

   /** 
    * <T>把所有的业务事件存储到数据库中</T>
    * 
    * @param context 页面连接
    * @param lgEventGroupDi 事件分组模块操作接口
    * @param lgEventTypeDi 事件类型模块操作接口
    * @param lgEventTypeConfigDi 事件类型设置模块操作接口
    * @param lgEventGroupConfigDi 事件分组设置模块操作接口
    * @param syPropertyDi 系统属性模块操作接口（所以属性的集合）
    * @param page 数据容器
    */
   String allSyncProject(IWebContext context,
                         ISyPropertyDi syPropertyDi,
                         @AContainer(name = "page") FProjectPage page);

   /** 
    * <T>把当前的业务事件存储到数据库中</T>
    * 
    * @param context 页面连接
    * @param lgEventGroupDi 事件分组模块操作接口
    * @param lgEventTypeDi 事件类型模块操作接口
    * @param lgEventTypeConfigDi 事件类型设置模块操作接口
    * @param lgEventGroupConfigDi 事件分组设置模块操作接口
    * @param syPropertyDi 系统属性模块操作接口（所以属性的集合）
    * @param page 数据容器
    */
   String syncProject(IWebContext context,
                      ISyPropertyDi syPropertyDi,
                      @AContainer(name = "page") FProjectPage page);

}
