/*
 * @(#)IProcessService.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.jfa.face.logic.process;

import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

/**
 * <T>业务-流程树目录各种操作服务接口。</T>
 * <P>提供对业务-流程和业务-流程用树目录形式进行增删改各种操作的接口。</P>
 * 
 * @author YJHUA
 * @version 1.00 - 2008/12/017
 */
public interface IProcessService
{

   /**
    * <T>初始化整个树目录。</T>
    * 
    * @param context 页面连接
    * @param input 从前台传入数据
    * @param output java端传出数据
    */
   void catalog(IWebContext context,
                IWebInput input,
                IWebOutput output);

   /**
    * <T>执行树目录节点的删除操作。</T>
    * 
    * @param context 页面连接
    * @param input 从前台传入数据
    * @param output java端传出数据
    */
   void delete(IWebContext context,
               IWebInput input,
               IWebOutput output);

   /**
    * <T>执行树节点的插入操作。</T>
    * 
    * @param context 页面连接
    * @param input 从前台传入数据
    * @param output java端传出数据
    */
   void insert(IWebContext context,
               IWebInput input,
               IWebOutput output);

   /**
    * <T>执行树节点展开操作。</T>
    * 
    * @param context 页面连接
    * @param input 从前台传入数据
    * @param output java端传出数据
    */
   void list(IWebContext context,
             IWebInput input,
             IWebOutput output);

   /**
    * <T>执行树节点排序操作。</T>
    * 
    * @param context 页面连接
    * @param input 从前台传入数据
    * @param output java端传出数据
    */
   void sort(IWebContext context,
             IWebInput input,
             IWebOutput output);

   /**
    * <T>执行树节点更新操作。</T>
    * 
    * @param context 页面连接
    * @param input 从前台传入数据
    * @param output java端传出数据
    */
   void update(IWebContext context,
               IWebInput input,
               IWebOutput output);
}
