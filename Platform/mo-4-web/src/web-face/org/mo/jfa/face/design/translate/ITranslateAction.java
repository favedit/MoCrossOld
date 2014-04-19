/*
 * @(#)ITranslateAction.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.jfa.face.design.translate;

import org.mo.web.core.container.AContainer;

import org.mo.logic.data.ISyPropertyDi;
import org.mo.logic.data.ISyTranslateDi;
import org.mo.web.protocol.context.IWebContext;

/**
 * <T>系统翻译树目录各种操作动作接口。</T>
 * <P>提供对系统翻译和系统翻译用树目录形式进行增删改和同步等各种操作的接口。</P>
 * 
 * @author YJHUA
 * @version 1.00 - 2008/12/017
 */
public interface ITranslateAction
{

   /**
    * <T>初始化整个树目录。</T>
    * 
    * @param context 页面连接
    * @param page 数据容器
    * @return 返回显示页面
    */
   String catalog(IWebContext context,
                  @AContainer(name = "page") FTranslatePage page);

   /**
    * <T>执行树节点展开操作。</T>
    * 
    * @param context 页面连接
    * @param page 数据容器
    * @return 返回显示页面
    */
   String list(IWebContext context,
               @AContainer(name = "page") FTranslatePage page);

   /**
    * <T>执行树节点的插入操作。</T>
    * 
    * @param context 页面环境
    * @param page 数据容器
    * @return 返回页面
    */
   String insert(IWebContext context,
                 @AContainer(name = "page") FTranslatePage page);

   /**
    * <T>执行树节点更新操作。</T>
    * 
    * @param context 页面连接
    * @param page 数据容器
    * @return 返回显示页面
    */
   String update(IWebContext context,
                 @AContainer(name = "page") FTranslatePage page);

   /**
    * <T>执行树目录节点的删除操作。</T>
    * 
    * @param context 页面连接
    * @param page 数据容器
    * @return 返回显示页面
    */
   String delete(IWebContext context,
                 @AContainer(name = "page") FTranslatePage page);

   /**
    * <T>执行树节点排序操作。</T>
    * 
    * @param context 页面连接
    * @param page 数据容器
    * @return 返回显示页面
    */
   String sort(IWebContext context,
               @AContainer(name = "page") FTranslatePage page);

   /** 
    * <T>把所有的业务事件存储到数据库中</T>
    * 
    * @param context 页面连接
    * @param syTranslateDi 系统翻译模块操作接口
    * @param syPropertyDi 系统属性模块操作接口（所以属性的集合）
    * @param page 数据容器
    * @return 返回显示页面
    */
   String allSyncTranslate(IWebContext context,
                           ISyTranslateDi syTranslateDi,
                           ISyPropertyDi syPropertyDi,
                           @AContainer(name = "page") FTranslatePage page);

   /** 
    * <T>把所有的业务事件存储到数据库中</T>
    * 
    * @param context 页面连接
    * @param syTranslateDi 系统翻译模块操作接口
    * @param syPropertyDi 系统属性模块操作接口（所以属性的集合）
    * @param page 数据容器
    * @return 返回显示页面
    */
   String syncTranslate(IWebContext context,
                        ISyTranslateDi syTranslateDi,
                        ISyPropertyDi syPropertyDi,
                        @AContainer(name = "page") FTranslatePage page);

}
