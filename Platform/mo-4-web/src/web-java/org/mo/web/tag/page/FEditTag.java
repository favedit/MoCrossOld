/*
 * @(#)FEditTag.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.web.tag.page;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.web.core.format.RHtmlFormat;
import org.mo.web.protocol.context.FControlConfig;
import org.mo.web.protocol.context.SPageItem;
import org.mo.web.tag.base.FBaseEditTag;
import org.mo.web.tag.common.ITagHtml;

/**
 * <T>编辑控件标签</T>
 * <P>当格式化方式为指定时,格式化为HTML代码</P>
 * 
 * @author MAOCY
 * @version 1.00 - 2008/08/05 CREATE BY MAOCY
 * @version 1.01 - 2009/01/05 UPDATE BY YANGH
 */
public class FEditTag
      extends FBaseEditTag
{

   /**
    * <T>定义编辑控件类型</T>
    */
   protected String _type;

   @Override
   public void construct(FControlConfig config){
      _status = config.attribute("status");
   }

   @Override
   public int onEnd(){
      /// 返回执行结果
      return EVAL_PAGE;
   }

   @Override
   public int onStart(){
      /// 获得数据源的值
      SPageItem item = _context.parseItem(_source);
      String value = RString.nvl(_value, item.valueString());
      String name = RString.nvl(_name, item.name);
      if(RString.isEmpty(name)){
         throw new FFatalError("Item name is null. (source={0})", _source);
      }
      /// 为空的情况下
      if(RString.isEmpty(value)){
         value = _context.parseString(_default);
      }
      value = RString.nvl(value);
      /// 定义一种状态
      String status = ITagHtml.STATUS_EDIT;
      /// 状态不为空的情况
      if(!RString.isBlank(_status)){
         _status = _context.parseString(_status);
         status = _context.pageStatus().parse(_status, ITagHtml.STATUS_EDIT);
      }
      /// 状态是隐藏的情况
      if(ITagHtml.STATUS_HIDDEN.equals(status)){
         // Nothing
         /// 状态是可见的情况
      }else if(ITagHtml.STATUS_DISPLAY.equals(status)){
         /// 类型是密码的情况
         if("password".equalsIgnoreCase(_type)){
            if(!RString.isEmpty(item.valueString())){
               _writer.append(RString.repeat("*", value.length()));
            }
         }else{
            _writer.append(RHtmlFormat.textToHtml(value));
         }
         /// 状态是编辑的情况
      }else if(ITagHtml.STATUS_EDIT.equals(status)){
         _writer.append("<INPUT");
         // type attribute
         if(!RString.isEmpty(_type)){
            _writer.append(" type=\"");
            _writer.append(_type);
            _writer.append('"');
         }else{
            _writer.append(" type=\"text\"");
         }
         /// 添加name的属性
         if(null != item){
            // name attribute
            _writer.append(" name=\"");
            _writer.append(name);
            _writer.append('"');
         }
         /// 添加只读的属性
         if(!RString.isEmpty(_readonly)){
            if(RBoolean.parse(_context.parseString(_readonly))){
               _writer.append(" readonly=\"true\"");
               if(RString.isEmpty(_style)){
                  setStyle("comEditReadonly");
               }
            }
         }
         // 使用状态是否为空
         if(RString.isNotEmpty(_disabled)){
            if(RBoolean.parse(_context.parseString(_disabled))){
               _writer.append(" disabled");
               _writer.append('"');
               if(RString.isEmpty(_style)){
                  // 控件不可使用状态的样式
                  setStyle("comMemoDisabled");
               }
            }
         }
         // 值不为空的情况
         if(RString.isNotEmpty(value)){
            _writer.append(" value=\"");
            _writer.append(RHtmlFormat.textToInput(value));
            _writer.append("\"");
         }
         /// 将宽度和高度等属性添加到样式里
         // 宽度
         if(!RString.isEmpty(_width)){
            appendStyle("width:", _context.parseString(_width));
         }
         // 高度
         if(!RString.isEmpty(_height)){
            appendStyle("height:", _context.parseString(_height));
         }
         if(RString.isEmpty(_style)){
            _style = "comEdit";
         }
         /// 调用父类方法
         appendHtml();
         _writer.append(">");
      }
      /// 刷新字符流
      _writer.flush();
      return SKIP_BODY;
   }

   /**
    * <T>设置类型。</T>
    *
    * @param value 数据内容
    */
   public void setType(String value){
      _type = value;
   }
}
