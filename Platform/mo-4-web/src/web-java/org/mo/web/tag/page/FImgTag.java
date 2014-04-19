/*
 * @(#)FImgTag.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.web.tag.page;

import org.mo.com.lang.RString;
import org.mo.web.tag.base.FBaseImgTag;

/**
 * <T>下拉列表标签</T>
 * <P>当格式化方式为指定时,格式化为HTML代码</P>
 * 
 * @author MAOCY
 * @version 1.00 - 2008/08/05 CREATE BY MAOCY
 * @version 1.01 - 2009/01/05 UPDATE BY YANGH
 */
public class FImgTag
      extends FBaseImgTag
{

   protected String _type;

   @Override
   public int onEnd(){
      return EVAL_PAGE;
   }

   @Override
   public int onStart(){
      /// 类型是path的情况
      if("path".equals(_type)){
         /// 输出地址模式
         String icon = _context.parseString(_src);
         boolean real = icon.startsWith("#");
         if(real){
            icon = icon.substring(1);
         }
         /// 获得主题
         String themeId = _context.session().themeId();
         String src = "/ats/" + themeId + "/rs/pic" + icon;
         if(real){
            _writer.append(_context.contextPath(src));
         }else{
            _writer.append(_context.contextPath());
            _writer.append(src);
         }
         /// 类型不是path的情况
      }else{
         _writer.append("<IMG");
         if(RString.isNotEmpty(_icon)){
            /// 获得图标
            String src = null;
            String icon = _context.parseString(_icon);
            boolean real = !icon.startsWith("#");
            if(real){
               icon = RString.replace(icon, ".", "/");
               src = "/ars/icon/" + icon + ".gif";
            }else{
               icon = icon.substring(1);
               String themeId = _context.session().themeId();
               icon = RString.replace(icon, ".", "/");
               src = "/ats/" + themeId + "/rs/icon/" + icon + ".gif";
            }
            // 生成内部代码
            _writer.append(" class='Tag_Icon'");
            _writer.append(" align='absmiddle'");
            _writer.append(" src='");
            if(real){
               _writer.append(_context.contextPath());
               _writer.append(src);
            }else{
               _writer.append(_context.contextPath(src));
            }
            _writer.append("'");
         }else if(RString.isNotEmpty(_src)){
            /// 图片路径
            String src = _context.parseString(_src);
            _writer.append(" src=\"");
            _writer.append(_context.contextPath(src));
            _writer.append("\"");
         }
         appendHtml();
         _writer.append(">");
      }
      /// 刷新字符流
      _writer.flush();
      return EVAL_BODY_INCLUDE;
   }

   @Override
   public void setType(String value){
      _type = value;
   }

}
