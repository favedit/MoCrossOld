/*
 * @(#)IEventAction.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.jfa.face.system.webtag;

import org.mo.web.core.container.AContainer;

import org.mo.web.protocol.context.IWebContext;

/**
 * <T>业务事件树目录各种操作动作接口类。</T>
 * <P>提供对业务事件和业务事件用树目录形式进行增删改和同步等各种操作的接口。</P>
 * 
 * @author YJHUA
 * @version 1.00 - 2008/12/08
 */
public interface IWebTagAction
{

   /**
    * <T>初始化整个树目录。</T>
    * 
    * @param context 页面连接
    * @param page 数据容器
    * @return 返回显示页面
    */
   String catalog(IWebContext context,
                  @AContainer(name = "page") FWebTagPage page);

   /**
    * <T>执行树节点展开操作。</T>
    * 
    * @param context 页面连接
    * @param page 数据容器
    * @return 返回显示页面
    */
   String list(IWebContext context,
               @AContainer(name = "page") FWebTagPage page);

   /**
    * <T>执行树节点的插入操作。</T>
    * 
    * @param context 页面环境
    * @param page 数据容器
    * @return 返回页面
    */
   String insert(IWebContext context,
                 @AContainer(name = "page") FWebTagPage page);

   /**
    * <T>执行树节点更新操作。</T>
    * 
    * @param context 页面连接
    * @param page 数据容器
    * @return 返回显示页面
    */
   String update(IWebContext context,
                 @AContainer(name = "page") FWebTagPage page);

   /**
    * <T>执行树目录节点的删除操作。</T>
    * 
    * @param context 页面连接
    * @param page 数据容器
    * @return 返回显示页面
    */
   String delete(IWebContext context,
                 @AContainer(name = "page") FWebTagPage page);

   /**
    * <T>执行树节点排序操作。</T>
    * 
    * @param context 页面连接
    * @param page 数据容器
    * @return 返回显示页面
    */
   String sort(IWebContext context,
               @AContainer(name = "page") FWebTagPage page);

   String bulid(IWebContext context,
                @AContainer(name = "page") FWebTagPage page);

   /**
    * <T>执行树节点排序操作。</T>
    * 
    * @param context 页面连接
    * @param page 数据容器
    * @return 返回显示页面
    */
   String execute(IWebContext context,
                  @AContainer(name = "page") FWebTagPage page);

}
