/*
 * @(#)ITemplateAction.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.jfa.face.system.exportExcel;

import org.mo.web.core.container.AContainer;

import org.mo.web.protocol.context.IWebContext;

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
public interface IExportExcelAction
{

   /**
    * <P>新建一条sql</P>
    * 
    * @param context WEB环境对象
    * @param sqlContext SQL环境对象
    * @param page 数据页面
    * @return 转向地址
    */
   String buildSql(IWebContext context,
                   @AContainer(name = "page") FExportExcelPage page);

   /**
    * <P>显示左边树目录</P>
    * 
    * @param context WEB环境对象
    * @param sqlContext SQL环境对象
    * @param page 数据页面
    * @return 转向地址
    */
   String catalog(IWebContext context,
                  @AContainer(name = "page") FExportExcelPage page);

   /**
    * <P>对记录进行删除</P>
    * 
    * @param context WEB环境对象
    * @param sqlContext SQL环境对象
    * @param page 数据页面
    * @return 转向地址
    */
   String delete(IWebContext context,
                 @AContainer(name = "page") FExportExcelPage page);

   /**
    * <P>对记录进行插入</P>
    * 
    * @param context WEB环境对象
    * @param sqlContext SQL环境对象
    * @param page 数据页面
    * @return 转向地址
    */
   String insert(IWebContext context,
                 @AContainer(name = "page") FExportExcelPage page);

   /**
    * <P>对树目录进行展开</P>
    * 
    * @param context WEB环境对象
    * @param sqlContext SQL环境对象
    * @param page 数据页面
    * @return 转向地址
    */
   String list(IWebContext context,
               @AContainer(name = "page") FExportExcelPage page);

   /**
    * <P>对数据进行排序</P>
    * 
    * @param context WEB环境对象
    * @param sqlContext SQL环境对象
    * @param page 数据页面
    * @return 转向地址
    */
   String sort(IWebContext context,
               @AContainer(name = "page") FExportExcelPage page);

   /**
    * <P>对source进行保存</P>
    * 
    * @param context WEB环境对象
    * @param sqlContext SQL环境对象
    * @param page 数据页面
    * @return 转向地址
    */
   String sqlSourceSave(IWebContext context,
                        @AContainer(name = "page") FExportExcelPage page);

   /**
    * <P>对数据进行更新</P>
    * 
    * @param context WEB环境对象
    * @param sqlContext SQL环境对象
    * @param page 数据页面
    * @return 转向地址
    */
   String update(IWebContext context,
                 @AContainer(name = "page") FExportExcelPage page);
}
