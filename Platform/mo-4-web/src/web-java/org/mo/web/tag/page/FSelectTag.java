/*
 * @(#)FSelectTag.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.web.tag.page;

import org.mo.com.lang.FAttributes;
import org.mo.com.lang.IObjects;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.reflect.RProperty;
import org.mo.com.xml.IXmlObject;
import org.mo.com.xml.IXmlObjects;
import org.mo.core.aop.RAop;
import org.mo.eng.list.IListConsole;
import org.mo.eng.list.common.XList;
import org.mo.web.core.format.RHtmlFormat;
import org.mo.web.protocol.context.SPageItem;
import org.mo.web.tag.base.FBaseSelectTag;
import org.mo.web.tag.common.ITagHtml;

/**
 * <T>下拉列表标签</T>
 * <P>当格式化方式为指定时,格式化为HTML代码</P>
 * 
 * @author MAOCY
 * @version 1.00 - 2008/08/05 CREATE BY MAOCY
 * @version 1.01 - 2009/01/05 UPDATE BY YANGH
 */
public class FSelectTag
      extends FBaseSelectTag
{

   /**
    * <T>构建数组</T>
    * <P>将得到的每个对象压入数组</P>
    * 
    * @param list 数组结果
    * @param objects 数组对象
    */
   protected void buildArray(FAttributes list,
                             IObjects<?> objects){
      Object[] items = objects.toObjects();
      for(Object item : items){
         if(null != item){
            String label = RProperty.getAsString(item, _label);
            String value = RProperty.getAsString(item, _value);
            list.set(label, value);
         }
      }
   }

   /**
    * <T>构建列表</T>
    * <P>将得到的每个对象压入列表</P>
    * 
    * @param result 列表结果
    * @param name 列表名称
    */
   protected void buildList(FAttributes result,
                            String name){
      IListConsole listConsole = RAop.find(IListConsole.class);
      XList list = listConsole.build(_context.parseString(name));
      IXmlObjects objects = list.children();
      if(null != objects){
         int count = objects.count();
         for(int n = 0; n < count; n++){
            IXmlObject item = objects.get(n);
            if(null != item){
               result.set(RString.nvl(item.innerGet("value")), RString.nvl(item.innerGet("label")));
            }
         }
      }
   }

   @Override
   public int onEnd(){
      /// 逻辑结束执行后的状态
      return EVAL_PAGE;
   }

   @Override
   public int onStart(){
      /// 获得变量
      SPageItem item = _context.parseItem(_source);
      String itemName = item.name;
      /// 如果名字不是空，则得到名字
      if(RString.isNotBlank(_name)){
         itemName = _context.parseString(_name);
      }
      /// 转化称字符串类型
      String value = item.valueString();
      /// 值为空的情况下得到缺省值
      if(RString.isBlank(value) && RString.isNotBlank(_default)){
         value = _context.parseString(_default);
      }
      value = RString.nvl(value).trim();
      String status = ITagHtml.STATUS_EDIT;
      /// 状态不为空的情况
      if(RString.isNotBlank(_status)){
         status = _context.parseString(_status);
         status = _context.pageStatus().parse(status, ITagHtml.STATUS_EDIT);
      }
      FAttributes list = new FAttributes();
      if(RString.isNotBlank(_enum)){
         buildList(list, _enum);
      }else if(RString.isNotBlank(_source)){
         Object source = _context.parse(_source);
         /// 将数据绑定在列表里
         if(source instanceof IObjects){
            buildArray(list, (IObjects<?>)source);
         }
      }
      /// 建立页面
      if(ITagHtml.STATUS_HIDDEN.equals(status)){
         // Nothing
      }else if(ITagHtml.STATUS_DISPLAY.equals(status)){
         _writer.append(RHtmlFormat.textToHtml(list.get(value)));
      }else if(ITagHtml.STATUS_EDIT.equals(status)){
         _writer.append("<SELECT");
         _writer.appendAttribute("name", itemName, true);
         appendHtml();
         _writer.append(">");
         if(RBoolean.parse(_empty)){
            _writer.append("<OPTION></OPTION>");
         }
         /// 依次将数据放入对应的位置
         int count = list.count();
         for(int n = 0; n < count; n++){
            _writer.append("<OPTION");
            _writer.appendAttribute("value", list.name(n), true);
            if(value.equalsIgnoreCase(list.name(n).trim())){
               _writer.append(" selected");
            }
            _writer.append('>');
            _writer.append(list.value(n));
            _writer.append("</OPTION>");
         }
         _writer.append("</SELECT>");
      }
      _writer.flush();
      return SKIP_BODY;
   }

}
