/*
 * @(#)IPropertyAction.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.jfa.face.logic.property;

import org.mo.web.core.container.AContainer;

import org.mo.eng.data.common.ISqlContext;
import org.mo.logic.data.ISyPropertyDi;
import org.mo.logic.data.ISyPropertyGroupDi;
import org.mo.web.protocol.context.IWebContext;

/**
 * <T>属性树目录各种操作动作接口类</T>
 * <P>提供对属性树目录进行增删改和同步等各种操作的接口</P>
 * 
 * @author HYKUN
 * @version 1.00 - 2008/12/06
 */
public interface IPropertyAction
{

   /**
    * <T>显示整个树目录。</T>
    * 
    * @param context 页面环境对象。
    * @param page 页面对象。
    * @return 返回页面对象。
    */
   String catalog(IWebContext context,
                  @AContainer(name = "page") FPropertyPage page);

   /**
    * <T>执行树目录节点的删除操作。</T>
    * 
    * @param context 页面环境对象。
    * @param page 页面对象。
    * @return 返回页面对象。
    */
   String delete(IWebContext context,
                 @AContainer(name = "page") FPropertyPage page);

   /**
    * <T>同步当前所选中的属性组。</T>
    * 
    * @param context 页面环境对象。
    * @param sqlcontext 数据库环境对象。
    * @param syPropertyGroupDi 属性分组代理对象。
    * @param syPropertyDi 属性代理对象。
    * @param page 页面对象。
    * @return 返回页面对象。
    */
   String doSynchronize(IWebContext context,
                        ISqlContext sqlcontext,
                        ISyPropertyGroupDi syPropertyGroupDi,
                        ISyPropertyDi syPropertyDi,
                        @AContainer(name = "page") FPropertyPage page);

   /**
    * <T>同步所有的属性到数据库中。</T>
    * 
    * @param context 页面环境对象。
    * @param sqlcontext 数据库环境对象。
    * @param syPropertyGroupDi 属性分组代理对象。
    * @param syPropertyDi 属性代理对象。
    * @param page 页面对象。
    * @return 返回页面对象。
    */
   String doSynchronizeAll(IWebContext context,
                           ISqlContext sqlcontext,
                           ISyPropertyGroupDi syPropertyGroupDi,
                           ISyPropertyDi syPropertyDi,
                           @AContainer(name = "page") FPropertyPage page);

   /**
    * <T>执行树节点的插入操作。</T>
    * 
    * @param context 页面环境。
    * @param page 页面对象。
    * @return 返回页面。
    */
   String insert(IWebContext context,
                 @AContainer(name = "page") FPropertyPage page);

   /**
    * <T>执行树节点列表操作。</T>
    * 
    * @param context 页面环境对象。
    * @param page 页面对象。
    * @return 返回页面对象。
    */
   String list(IWebContext context,
               @AContainer(name = "page") FPropertyPage page);

   /**
    * <T>执行树节点排序操作。</T>
    * 
    * @param context 页面环境对象
    * @param page 页面对象
    * @return 返回页面对象
    */
   String sort(IWebContext context,
               @AContainer(name = "page") FPropertyPage page);

   /**
    * <T>执行树节点更新操作。</T>
    * 
    * @param context 页面环境对象
    * @param page 页面对象
    * @return 返回页面对象
    */
   String update(IWebContext context,
                 @AContainer(name = "page") FPropertyPage page);

}
