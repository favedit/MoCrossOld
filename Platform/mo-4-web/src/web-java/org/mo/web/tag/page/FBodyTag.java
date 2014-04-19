/*
 * @(#)FBodyTag.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.web.tag.page;

import org.mo.com.lang.FString;
import org.mo.com.lang.RDump;
import org.mo.web.tag.base.FBaseBodyTag;
import org.mo.web.tag.common.FAbstractTag;

/**
 * <T>下拉列表标签</T>
 * <P>当格式化方式为指定时,格式化为HTML代码</P>
 * 
 * @author MAOCY
 * @version 1.00 - 2008/08/05 CREATE BY MAOCY
 * @version 1.01 - 2009/01/05 UPDATE BY YANGH
 */
public class FBodyTag
      extends FBaseBodyTag
{

   /**
    * <T>按键被按下的事件</T>
    */
   public static String ON_KEYDOWN = "jfa.on.keydown";

   /**
    * <T>按键的事件</T>
    */
   public static String ON_KEYPRESS = "jfa.on.keypress";

   /**
    * <T>按键起来的事件</T>
    */
   public static String ON_KEYUP = "jfa.on.keyup";

   /**
    * <T>页面开始的事件</T>
    */
   public static String ON_LOAD = "jfa.on.load";

   /**
    * <T>页面结束的事件</T>
    */
   public static String ON_UNLOAD = "jfa.on.unload";

   /**
    * <T>添加页面开始时的脚本</T>
    *
    * @param tag 标签对象
    * @param value 事件脚本
    */
   public static void appendOnload(FAbstractTag tag,
                                   String value){
      /// 调用查找属性方法
      FString onLoad = findAttribute(tag, ON_LOAD);
      /// 设置换行
      onLoad.append("//");
      onLoad.appendLine(RDump.LINE_L2);
      /// 设置数据值
      onLoad.append(value);
   }

   /**
    * <T>添加页面结束时的脚本</T>
    *
    * @param tag 标签对象
    * @param value 事件脚本
    */
   public static void appendOnunload(FAbstractTag tag,
                                     String value){
      /// 调用查找属性方法
      FString onLoad = findAttribute(tag, ON_UNLOAD);
      /// 设置换行
      onLoad.append("//");
      onLoad.appendLine(RDump.LINE_L2);
      /// 设置数据值
      onLoad.append(value);
   }

   /**
    * <T>根据标签查找属性</T>
    * <P>获得标签对象的属性内容</P>
    *
    * @param tag 标签对象
    * @param type 名称
    * @return 属性内容
    */
   private static FString findAttribute(FAbstractTag tag,
                                        String type){
      FString value = (FString)tag.context().attribute(type);
      if(null == value){
         value = new FString();
         tag.context().setAttribute(type, value);
      }
      return value;
   }

   @Override
   public int onEnd(){
      /// 往页面里添加内容
      _writer.append("<SCRIPT language='javascript'>\n");
      /// 状态设置为加载
      FString onLoad = findAttribute(this, ON_LOAD);
      onLoad.append(_onload);
      _writer.append("function __bodyLoad(){\n");
      /// 判断加载是否为空
      if(!onLoad.isEmpty()){
         _writer.append("\n");
         _writer.append(onLoad);
      }
      _writer.append("}\n");
      /// 状态设置为不加载
      FString onunLoad = findAttribute(this, ON_UNLOAD);
      onunLoad.append(_onunload);
      _writer.append("function __bodyUnload(){");
      if(!onunLoad.isEmpty()){
         _writer.append("\n");
         _writer.append(onunLoad);
      }
      _writer.append("}\n");
      /// 输出显示
      _writer.append("</SCRIPT>\n");
      _writer.append("</BODY>");
      _writer.flush();
      /// 返回执行状态
      return EVAL_PAGE;
   }

   /**
    * <T>获得键盘按下信息对象</T>
    * <P>当键盘按下时，得到所按下的键的信息</P>
    *
    * @retrun 键盘按下信息对象
    */
   public String onkeydown(){
      return (String)context().attribute(ON_KEYDOWN);
   }

   @Override
   public int onStart(){
      /// 状态设置为加载
      FString onload = findAttribute(this, ON_LOAD);
      FString script = new FString();
      script.append(onload);
      onload.clear();
      _writer.append("<BODY");
      _writer.append(" onload=\"__bodyLoad()\"");
      _writer.append(" onunload=\"__bodyUnload()\"");
      appendHtml();
      _writer.append(">");
      _writer.flush();
      // 返回执行完后状态
      return EVAL_BODY_INCLUDE;
   }
}
