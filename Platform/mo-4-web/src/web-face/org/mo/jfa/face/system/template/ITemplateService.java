/*
 * @(#)ITemplateService.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.jfa.face.system.template;

import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

/**
 * <T>响应页面上的一系列操作</T>
 * <P>响应页面上如下操作</P>
 * <UL>
 *    <L value = 'catalog'>展开目录时调用</L>
 *    <L value = 'delete'>删除数据时调用</L>
 *    <L value = 'insert'>新建记录时调用</L>
 *    <L value = 'list'>展开表格时调用</L>
 *    <L value = 'sort'>对数据排序时调用</L>
 *    <L value = 'source'>点击source按钮时调用</L>
 *    <L value = 'sourceSave'>点击source保存时调用</L>
 *    <L value = 'update'>对记录进行更新时调用</L>
 * </UL> 
 * @author YANGH
 * @version 1.00 - 2008/12/07
 */
public interface ITemplateService
{

   /**
    * <P>显示左边树目录</P>
    * 
    * @param context WEB环境对象
    * @param sqlContext SQL环境对象
    * @param page 数据页面
    * @return 转向地址
    */
   void catalog(IWebContext context,
                IWebInput input,
                IWebOutput output);

   /**
    * <P>对记录进行删除</P>
    * 
    * @param context WEB环境对象
    * @param sqlContext SQL环境对象
    * @param page 数据页面
    * @return 转向地址
    */
   void delete(IWebContext context,
               IWebInput input,
               IWebOutput output);

   /**
    * <P>对记录进行插入</P>
    * 
    * @param context WEB环境对象
    * @param sqlContext SQL环境对象
    * @param page 数据页面
    * @return 转向地址
    */
   void insert(IWebContext context,
               IWebInput input,
               IWebOutput output);

   /**
    * <P>对树目录进行展开</P>
    * 
    * @param context WEB环境对象
    * @param sqlContext SQL环境对象
    * @param page 数据页面
    * @return 转向地址
    */
   void list(IWebContext context,
             IWebInput input,
             IWebOutput output);

   /**
    * <P>对数据进行排序</P>
    * 
    * @param context WEB环境对象
    * @param sqlContext SQL环境对象
    * @param page 数据页面
    * @return 转向地址
    */
   void sort(IWebContext context,
             IWebInput input,
             IWebOutput output);

   /**
    * <P>对数据进行更新</P>
    * 
    * @param context WEB环境对象
    * @param sqlContext SQL环境对象
    * @param page 数据页面
    * @return 转向地址
    */
   void update(IWebContext context,
               IWebInput input,
               IWebOutput output);
}
