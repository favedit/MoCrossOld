/*
 * @(#)FButtonTag.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.web.tag.page;

import org.mo.com.lang.RString;
import org.mo.web.protocol.context.FControlConfig;
import org.mo.web.tag.base.FBaseButtonTag;

/**
 * <T>按钮标签</T>
 * <P>添加与按钮属性有关的html属性</P>
 * 
 * @author MAOCY
 * @version 1.00 - 2008/08/05 CREATE BY MAOCY
 * @version 1.01 - 2009/01/05 UPDATE BY YANGH
 */
public class FButtonTag
      extends FBaseButtonTag
{

   /**
    * <T>显示状态</T>
    */
   public static String STATUS_DISPLAY = "display";

   /**
    * <T>隐藏状态</T>
    */
   public static String STATUS_HIDDEN = "hidden";

   @Override
   public void construct(FControlConfig config){
      _status = config.attribute("status");
   }

   @Override
   public int onEnd(){
      return EVAL_PAGE;
   }

   @Override
   public int onStart(){
      /// 判断其状态是否为可见
      String status = STATUS_DISPLAY;
      if(!RString.isBlank(_status)){
         _status = _context.parseString(_status);
         status = _context.pageStatus().parse(_status, STATUS_DISPLAY);
      }
      /// 添加原则：双引号里的内容原样输出
      /// 判断是否是隐藏标签
      if(STATUS_HIDDEN.equals(status)){
         // Nothing
         // 如果是显示
      }else if(STATUS_DISPLAY.equals(status)){
         _writer.append("<INPUT");
         /// 判断类型是否为空
         _writer.append(" type='");
         if(!RString.isEmpty(_type)){
            _writer.append(_type);
         }else{
            _writer.append("button");
         }
         _writer.append("'");
         /// 判断名称是否为空
         if(!RString.isEmpty(_name)){
            _writer.append(" name='");
            _writer.append(_name);
            _writer.append("'");
         }
         /// 判断标题是否为空
         if(!RString.isEmpty(_caption)){
            _writer.appendAttribute("value", _caption);
         }
         /// 判断动作是否为空
         if(RString.isNotBlank(_action)){
            _writer.append(" onclick=\"");
            _writer.append(_context.parseString(_action));
            _writer.append("\"");
         }else{
            /// 判断属性是否为空（不包含空格）
            if(RString.isNotBlank(_page)){
               // 获得函数和页面的定义
               String method = _method;
               String page = _page;
               if(page.contains("@")){
                  String[] items = RString.splitTwo(page, '@', true);
                  method = items[0];
                  page = items[1];
               }
               // 使用Tag的JS进行页面转向
               _writer.append(" onclick='RTag.goPage(this,");
               _writer.append(RString.isEmpty(_target) ? "null" : _target);
               _writer.append(",\"");
               _writer.append(_context.contextPath(page));
               // 输出页面定义
               if(RString.isNotBlank(method)){
                  _writer.append(page.contains("?") ? "&do=" : "?do=");
                  _writer.append(method);
               }
               _writer.append("\")'");
            }else if(RString.isNotBlank(_method)){
               /// 加入动作方法
               _writer.append(" onclick='RTag.goPage(this,");
               if(RString.isEmpty(_target)){
                  _writer.append("null");
               }else{
                  _writer.append(_target);
               }
               _writer.append(",\"?do=", _method, "\")'");
               if(RString.isEmpty(_method)){
                  _writer.append("null");
               }else{
                  _writer.append(_method);
               }
            }
         }
         /// 调用父类方法添加属性信息
         appendHtml();
         _writer.append(">");
         /// 将所有已缓冲输出写入底层流来刷新此流
         _writer.flush();
      }
      return SKIP_BODY;
   }

}
