/*
 * @(#)FBaseNumberTag.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 */
package org.mo.web.tag.base;

import org.mo.web.tag.common.FAbstractHtmlTag;

/**
 * <T>创建数字输入框标签基类。</T>
 *
 * @author YJHUA
 * @version 1.00 - 2008/12/08
 */
public abstract class FBaseNumberTag
      extends FAbstractHtmlTag
{

   // 编号
   protected String _id;

   // 名称
   protected String _name;

   // 数据源
   protected String _source;

   // 设置
   protected String _config;

   // 值
   protected String _value;

   // 状态
   protected String _status;

   // 默认值
   protected String _default;

   // 控件状态
   protected String _disabled;

   // 显示
   protected String _display;

   // 格式
   protected String _format;

   // 只读
   protected String _readonly;

   // 大小
   protected String _size;

   // 最大长度
   protected String _maxlength;

   // 宽度
   protected String _width;

   // 高度
   protected String _height;

   // 样式引入
   protected String _style;

   // 样式类
   protected String _css;

   // 状态改变是触发
   protected String _onchange;

   // 当对象设置为活动元素时触发
   protected String _onactivate;

   // 在对象失去输入焦点时触发
   protected String _onblur;

   // 鼠标左键单击对象时触发
   protected String _onclick;

   // 使用鼠标右键单击客户区打开上下文菜单时触发
   protected String _oncontextmenu;

   // 对该对象制作一个控件选中区时触发
   protected String _oncontrolselect;

   // 当对象或选中区从文档中删除并添加到系统剪贴板上时在源元素上触发
   protected String _oncut;

   // 当用户双击对象时触发
   protected String _ondblclick;

   // 从当前对象变为父文档其它对象时触发
   protected String _ondeactivate;

   // 当用户拖曳对象到一个合法拖曳目标时在目标元素上触发
   protected String _ondragenter;

   // 当用户在拖曳操作过程中将鼠标移出合法拖曳目标时在目标对象上触发
   protected String _ondragleave;

   // 当用户拖曳对象划过合法拖曳目标时持续在目标元素上触发
   protected String _ondragover;

   // 当鼠标按钮在拖曳操作过程中释放时在目标对象上触发
   protected String _ondrop;

   // 当可视滤镜更改状态或完成转换时触发
   protected String _onfilterchange;

   // 当对象获得焦点时触发
   protected String _onfocus;

   // 当元素将要被设置为焦点之前触发
   protected String _onfocusin;

   // 在移动焦点到其它元素之后立即触发于当前拥有焦点的元素上触发
   protected String _onfocusout;

   // 当用户在浏览器为当前窗口时按 f1 键时触发
   protected String _onhelp;

   // 当用户按下键盘按键时触发
   protected String _onkeydown;

   // 当用户按下字面键时触发
   protected String _onkeypress;

   // 当用户释放键盘按键时触发
   protected String _onkeyup;

   // 当对象失去鼠标捕捉时触发
   protected String _onlosecapture;

   // 当用户用任何鼠标按钮单击对象时触发
   protected String _onmousedown;

   // 当用户将鼠标指针移动到对象内时触发
   protected String _onmouseenter;

   // 当用户将鼠标指针移出对象边界时触发
   protected String _onmouseleave;

   // 当用户将鼠标划过对象时触发
   protected String _onmousemove;

   // 当用户将鼠标指针移出对象边界时触发
   protected String _onmouseout;

   // 当用户将鼠标指针移动到对象内时触发
   protected String _onmouseover;

   // 当用户在鼠标位于对象之上时释放鼠标按钮时触发
   protected String _onmouseup;

   // 当鼠标滚轮按钮旋转时触发
   protected String _onmousewheel;

   // 当对象移动时触发
   protected String _onmove;

   // 当对象停止移动时触发
   protected String _onmoveend;

   // 当对象开始移动时触发
   protected String _onmovestart;

   // 当用户粘贴数据以便从系统剪贴板向文档传送数据时在目标对象上触发
   protected String _onpaste;

   // 当在对象上发生对象上发生属性更改时触发
   protected String _onpropertychange;

   // 当对象状态变更时触发
   protected String _onreadystatechange;

   // 当对象的大小将要改变时触发
   protected String _onresize;

   // 当用户更改完控件选中区中对象的尺寸时触发
   protected String _onresizeend;

   // 当用户开始更改控件选中区中对象的尺寸时触发
   protected String _onresizestart;

   // 对象将要被选中时触发
   protected String _onselectstart;

   // 当特定时间错误发生时无条件触发，通常由将属性设置为无效值导致
   protected String _ontimeerror;

   /**
    * <T>添加页面属性。</T>
    *
    */
   public void appendHtml(){
      /// 如果编号不空，则添加该属性
      if(null != _id){
         _writer.appendAttribute("id", _id, true);
      }
      /// 如果名称不空，则添加该属性
      if(null != _name){
         _writer.appendAttribute("name", _name, true);
      }
      /// 如果显示不空，则添加该属性
      if(null != _display){
         _writer.appendAttribute("display", _display, true);
      }
      /// 如果格式不空，则添加该属性
      if(null != _format){
         _writer.appendAttribute("format", _format, true);
      }
      /// 如果只读不空，则添加该属性
      if(null != _readonly){
         _writer.appendAttribute("readonly", _readonly, true);
      }
      /// 如果大小不空，则添加该属性
      if(null != _size){
         _writer.appendAttribute("size", _size, true);
      }
      /// 如果最大长度不空，则添加该属性
      if(null != _maxlength){
         _writer.appendAttribute("maxlength", _maxlength, true);
      }
      /// 如果样式类不空，则添加该属性
      if(null != _css){
         _writer.appendAttribute("class", _css, true);
      }
      /// 如果状态改变是触发不空，则添加该属性
      if(null != _onchange){
         _writer.appendAttribute("onchange", _onchange, true);
      }
      /// 如果当对象设置为活动元素时触发不空，则添加该属性
      if(null != _onactivate){
         _writer.appendAttribute("onactivate", _onactivate, true);
      }
      /// 如果在对象失去输入焦点时触发不空，则添加该属性
      if(null != _onblur){
         _writer.appendAttribute("onblur", _onblur, true);
      }
      /// 如果鼠标左键单击对象时触发不空，则添加该属性
      if(null != _onclick){
         _writer.appendAttribute("onclick", _onclick, true);
      }
      /// 如果使用鼠标右键单击客户区打开上下文菜单时触发不空，则添加该属性
      if(null != _oncontextmenu){
         _writer.appendAttribute("oncontextmenu", _oncontextmenu, true);
      }
      /// 如果对该对象制作一个控件选中区时触发不空，则添加该属性
      if(null != _oncontrolselect){
         _writer.appendAttribute("oncontrolselect", _oncontrolselect, true);
      }
      /// 如果当对象或选中区从文档中删除并添加到系统剪贴板上时在源元素上触发不空，则添加该属性
      if(null != _oncut){
         _writer.appendAttribute("oncut", _oncut, true);
      }
      /// 如果当用户双击对象时触发不空，则添加该属性
      if(null != _ondblclick){
         _writer.appendAttribute("ondblclick", _ondblclick, true);
      }
      /// 如果从当前对象变为父文档其它对象时触发不空，则添加该属性
      if(null != _ondeactivate){
         _writer.appendAttribute("ondeactivate", _ondeactivate, true);
      }
      /// 如果当用户拖曳对象到一个合法拖曳目标时在目标元素上触发不空，则添加该属性
      if(null != _ondragenter){
         _writer.appendAttribute("ondragenter", _ondragenter, true);
      }
      /// 如果当用户在拖曳操作过程中将鼠标移出合法拖曳目标时在目标对象上触发不空，则添加该属性
      if(null != _ondragleave){
         _writer.appendAttribute("ondragleave", _ondragleave, true);
      }
      /// 如果当用户拖曳对象划过合法拖曳目标时持续在目标元素上触发不空，则添加该属性
      if(null != _ondragover){
         _writer.appendAttribute("ondragover", _ondragover, true);
      }
      /// 如果当鼠标按钮在拖曳操作过程中释放时在目标对象上触发不空，则添加该属性
      if(null != _ondrop){
         _writer.appendAttribute("ondrop", _ondrop, true);
      }
      /// 如果当可视滤镜更改状态或完成转换时触发不空，则添加该属性
      if(null != _onfilterchange){
         _writer.appendAttribute("onfilterchange", _onfilterchange, true);
      }
      /// 如果当对象获得焦点时触发不空，则添加该属性
      if(null != _onfocus){
         _writer.appendAttribute("onfocus", _onfocus, true);
      }
      /// 如果当元素将要被设置为焦点之前触发不空，则添加该属性
      if(null != _onfocusin){
         _writer.appendAttribute("onfocusin", _onfocusin, true);
      }
      /// 如果在移动焦点到其它元素之后立即触发于当前拥有焦点的元素上触发不空，则添加该属性
      if(null != _onfocusout){
         _writer.appendAttribute("onfocusout", _onfocusout, true);
      }
      /// 如果当用户在浏览器为当前窗口时按 f1 键时触发不空，则添加该属性
      if(null != _onhelp){
         _writer.appendAttribute("onhelp", _onhelp, true);
      }
      /// 如果当用户按下键盘按键时触发不空，则添加该属性
      if(null != _onkeydown){
         _writer.appendAttribute("onkeydown", _onkeydown, true);
      }
      /// 如果当用户按下字面键时触发不空，则添加该属性
      if(null != _onkeypress){
         _writer.appendAttribute("onkeypress", _onkeypress, true);
      }
      /// 如果当用户释放键盘按键时触发不空，则添加该属性
      if(null != _onkeyup){
         _writer.appendAttribute("onkeyup", _onkeyup, true);
      }
      /// 如果当对象失去鼠标捕捉时触发不空，则添加该属性
      if(null != _onlosecapture){
         _writer.appendAttribute("onlosecapture", _onlosecapture, true);
      }
      /// 如果当用户用任何鼠标按钮单击对象时触发不空，则添加该属性
      if(null != _onmousedown){
         _writer.appendAttribute("onmousedown", _onmousedown, true);
      }
      /// 如果当用户将鼠标指针移动到对象内时触发不空，则添加该属性
      if(null != _onmouseenter){
         _writer.appendAttribute("onmouseenter", _onmouseenter, true);
      }
      /// 如果当用户将鼠标指针移出对象边界时触发不空，则添加该属性
      if(null != _onmouseleave){
         _writer.appendAttribute("onmouseleave", _onmouseleave, true);
      }
      /// 如果当用户将鼠标划过对象时触发不空，则添加该属性
      if(null != _onmousemove){
         _writer.appendAttribute("onmousemove", _onmousemove, true);
      }
      /// 如果当用户将鼠标指针移出对象边界时触发不空，则添加该属性
      if(null != _onmouseout){
         _writer.appendAttribute("onmouseout", _onmouseout, true);
      }
      /// 如果当用户将鼠标指针移动到对象内时触发不空，则添加该属性
      if(null != _onmouseover){
         _writer.appendAttribute("onmouseover", _onmouseover, true);
      }
      /// 如果当用户在鼠标位于对象之上时释放鼠标按钮时触发不空，则添加该属性
      if(null != _onmouseup){
         _writer.appendAttribute("onmouseup", _onmouseup, true);
      }
      /// 如果当鼠标滚轮按钮旋转时触发不空，则添加该属性
      if(null != _onmousewheel){
         _writer.appendAttribute("onmousewheel", _onmousewheel, true);
      }
      /// 如果当对象移动时触发不空，则添加该属性
      if(null != _onmove){
         _writer.appendAttribute("onmove", _onmove, true);
      }
      /// 如果当对象停止移动时触发不空，则添加该属性
      if(null != _onmoveend){
         _writer.appendAttribute("onmoveend", _onmoveend, true);
      }
      /// 如果当对象开始移动时触发不空，则添加该属性
      if(null != _onmovestart){
         _writer.appendAttribute("onmovestart", _onmovestart, true);
      }
      /// 如果当用户粘贴数据以便从系统剪贴板向文档传送数据时在目标对象上触发不空，则添加该属性
      if(null != _onpaste){
         _writer.appendAttribute("onpaste", _onpaste, true);
      }
      /// 如果当在对象上发生对象上发生属性更改时触发不空，则添加该属性
      if(null != _onpropertychange){
         _writer.appendAttribute("onpropertychange", _onpropertychange, true);
      }
      /// 如果当对象状态变更时触发不空，则添加该属性
      if(null != _onreadystatechange){
         _writer.appendAttribute("onreadystatechange", _onreadystatechange, true);
      }
      /// 如果当对象的大小将要改变时触发不空，则添加该属性
      if(null != _onresize){
         _writer.appendAttribute("onresize", _onresize, true);
      }
      /// 如果当用户更改完控件选中区中对象的尺寸时触发不空，则添加该属性
      if(null != _onresizeend){
         _writer.appendAttribute("onresizeend", _onresizeend, true);
      }
      /// 如果当用户开始更改控件选中区中对象的尺寸时触发不空，则添加该属性
      if(null != _onresizestart){
         _writer.appendAttribute("onresizestart", _onresizestart, true);
      }
      /// 如果对象将要被选中时触发不空，则添加该属性
      if(null != _onselectstart){
         _writer.appendAttribute("onselectstart", _onselectstart, true);
      }
      /// 如果当特定时间错误发生时无条件触发，通常由将属性设置为无效值导致不空，则添加该属性
      if(null != _ontimeerror){
         _writer.appendAttribute("ontimeerror", _ontimeerror, true);
      }
      /// 如果样式不为空，则添加该样式
      if(null != _style){
         _writer.appendAttribute("style", _style, true);
      }
   }

   /**
    * <T>释放所有属性。</T>
    *
    */
   public void release(){
      _id = null;
      _name = null;
      _source = null;
      _config = null;
      _value = null;
      _status = null;
      _default = null;
      _disabled = null;
      _display = null;
      _format = null;
      _readonly = null;
      _size = null;
      _maxlength = null;
      _width = null;
      _height = null;
      _style = null;
      _css = null;
      _onchange = null;
      _onactivate = null;
      _onblur = null;
      _onclick = null;
      _oncontextmenu = null;
      _oncontrolselect = null;
      _oncut = null;
      _ondblclick = null;
      _ondeactivate = null;
      _ondragenter = null;
      _ondragleave = null;
      _ondragover = null;
      _ondrop = null;
      _onfilterchange = null;
      _onfocus = null;
      _onfocusin = null;
      _onfocusout = null;
      _onhelp = null;
      _onkeydown = null;
      _onkeypress = null;
      _onkeyup = null;
      _onlosecapture = null;
      _onmousedown = null;
      _onmouseenter = null;
      _onmouseleave = null;
      _onmousemove = null;
      _onmouseout = null;
      _onmouseover = null;
      _onmouseup = null;
      _onmousewheel = null;
      _onmove = null;
      _onmoveend = null;
      _onmovestart = null;
      _onpaste = null;
      _onpropertychange = null;
      _onreadystatechange = null;
      _onresize = null;
      _onresizeend = null;
      _onresizestart = null;
      _onselectstart = null;
      _ontimeerror = null;
      super.release();
   }

   /**
    * <T>设置设置。</T>
    *
    * @param value 数据内容
    */
   public void setConfig(String value){
      _config = value;
   }

   /**
    * <T>设置样式类。</T>
    *
    * @param value 数据内容
    */
   public void setCss(String value){
      _css = value;
   }

   /**
    * <T>设置默认值。</T>
    *
    * @param value 数据内容
    */
   public void setDefault(String value){
      _default = value;
   }

   /**
    * <T>设置控件状态。</T>
    *
    * @param value 数据内容
    */
   public void setDisabled(String value){
      _disabled = value;
   }

   /**
    * <T>设置显示。</T>
    *
    * @param value 数据内容
    */
   public void setDisplay(String value){
      _display = value;
   }

   /**
    * <T>设置格式。</T>
    *
    * @param value 数据内容
    */
   public void setFormat(String value){
      _format = value;
   }

   /**
    * <T>设置高度。</T>
    *
    * @param value 数据内容
    */
   public void setHeight(String value){
      _height = value;
   }

   /**
    * <T>设置编号。</T>
    *
    * @param value 数据内容
    */
   public void setId(String value){
      _id = value;
   }

   /**
    * <T>设置最大长度。</T>
    *
    * @param value 数据内容
    */
   public void setMaxlength(String value){
      _maxlength = value;
   }

   /**
    * <T>设置名称。</T>
    *
    * @param value 数据内容
    */
   public void setName(String value){
      _name = value;
   }

   /**
    * <T>设置当对象设置为活动元素时触发。</T>
    *
    * @param value 数据内容
    */
   public void setOnactivate(String value){
      _onactivate = value;
   }

   /**
    * <T>设置在对象失去输入焦点时触发。</T>
    *
    * @param value 数据内容
    */
   public void setOnblur(String value){
      _onblur = value;
   }

   /**
    * <T>设置状态改变是触发。</T>
    *
    * @param value 数据内容
    */
   public void setOnchange(String value){
      _onchange = value;
   }

   /**
    * <T>设置鼠标左键单击对象时触发。</T>
    *
    * @param value 数据内容
    */
   public void setOnclick(String value){
      _onclick = value;
   }

   /**
    * <T>设置使用鼠标右键单击客户区打开上下文菜单时触发。</T>
    *
    * @param value 数据内容
    */
   public void setOncontextmenu(String value){
      _oncontextmenu = value;
   }

   /**
    * <T>设置对该对象制作一个控件选中区时触发。</T>
    *
    * @param value 数据内容
    */
   public void setOncontrolselect(String value){
      _oncontrolselect = value;
   }

   /**
    * <T>设置当对象或选中区从文档中删除并添加到系统剪贴板上时在源元素上触发。</T>
    *
    * @param value 数据内容
    */
   public void setOncut(String value){
      _oncut = value;
   }

   /**
    * <T>设置当用户双击对象时触发。</T>
    *
    * @param value 数据内容
    */
   public void setOndblclick(String value){
      _ondblclick = value;
   }

   /**
    * <T>设置从当前对象变为父文档其它对象时触发。</T>
    *
    * @param value 数据内容
    */
   public void setOndeactivate(String value){
      _ondeactivate = value;
   }

   /**
    * <T>设置当用户拖曳对象到一个合法拖曳目标时在目标元素上触发。</T>
    *
    * @param value 数据内容
    */
   public void setOndragenter(String value){
      _ondragenter = value;
   }

   /**
    * <T>设置当用户在拖曳操作过程中将鼠标移出合法拖曳目标时在目标对象上触发。</T>
    *
    * @param value 数据内容
    */
   public void setOndragleave(String value){
      _ondragleave = value;
   }

   /**
    * <T>设置当用户拖曳对象划过合法拖曳目标时持续在目标元素上触发。</T>
    *
    * @param value 数据内容
    */
   public void setOndragover(String value){
      _ondragover = value;
   }

   /**
    * <T>设置当鼠标按钮在拖曳操作过程中释放时在目标对象上触发。</T>
    *
    * @param value 数据内容
    */
   public void setOndrop(String value){
      _ondrop = value;
   }

   /**
    * <T>设置当可视滤镜更改状态或完成转换时触发。</T>
    *
    * @param value 数据内容
    */
   public void setOnfilterchange(String value){
      _onfilterchange = value;
   }

   /**
    * <T>设置当对象获得焦点时触发。</T>
    *
    * @param value 数据内容
    */
   public void setOnfocus(String value){
      _onfocus = value;
   }

   /**
    * <T>设置当元素将要被设置为焦点之前触发。</T>
    *
    * @param value 数据内容
    */
   public void setOnfocusin(String value){
      _onfocusin = value;
   }

   /**
    * <T>设置在移动焦点到其它元素之后立即触发于当前拥有焦点的元素上触发。</T>
    *
    * @param value 数据内容
    */
   public void setOnfocusout(String value){
      _onfocusout = value;
   }

   /**
    * <T>设置当用户在浏览器为当前窗口时按 f1 键时触发。</T>
    *
    * @param value 数据内容
    */
   public void setOnhelp(String value){
      _onhelp = value;
   }

   /**
    * <T>设置当用户按下键盘按键时触发。</T>
    *
    * @param value 数据内容
    */
   public void setOnkeydown(String value){
      _onkeydown = value;
   }

   /**
    * <T>设置当用户按下字面键时触发。</T>
    *
    * @param value 数据内容
    */
   public void setOnkeypress(String value){
      _onkeypress = value;
   }

   /**
    * <T>设置当用户释放键盘按键时触发。</T>
    *
    * @param value 数据内容
    */
   public void setOnkeyup(String value){
      _onkeyup = value;
   }

   /**
    * <T>设置当对象失去鼠标捕捉时触发。</T>
    *
    * @param value 数据内容
    */
   public void setOnlosecapture(String value){
      _onlosecapture = value;
   }

   /**
    * <T>设置当用户用任何鼠标按钮单击对象时触发。</T>
    *
    * @param value 数据内容
    */
   public void setOnmousedown(String value){
      _onmousedown = value;
   }

   /**
    * <T>设置当用户将鼠标指针移动到对象内时触发。</T>
    *
    * @param value 数据内容
    */
   public void setOnmouseenter(String value){
      _onmouseenter = value;
   }

   /**
    * <T>设置当用户将鼠标指针移出对象边界时触发。</T>
    *
    * @param value 数据内容
    */
   public void setOnmouseleave(String value){
      _onmouseleave = value;
   }

   /**
    * <T>设置当用户将鼠标划过对象时触发。</T>
    *
    * @param value 数据内容
    */
   public void setOnmousemove(String value){
      _onmousemove = value;
   }

   /**
    * <T>设置当用户将鼠标指针移出对象边界时触发。</T>
    *
    * @param value 数据内容
    */
   public void setOnmouseout(String value){
      _onmouseout = value;
   }

   /**
    * <T>设置当用户将鼠标指针移动到对象内时触发。</T>
    *
    * @param value 数据内容
    */
   public void setOnmouseover(String value){
      _onmouseover = value;
   }

   /**
    * <T>设置当用户在鼠标位于对象之上时释放鼠标按钮时触发。</T>
    *
    * @param value 数据内容
    */
   public void setOnmouseup(String value){
      _onmouseup = value;
   }

   /**
    * <T>设置当鼠标滚轮按钮旋转时触发。</T>
    *
    * @param value 数据内容
    */
   public void setOnmousewheel(String value){
      _onmousewheel = value;
   }

   /**
    * <T>设置当对象移动时触发。</T>
    *
    * @param value 数据内容
    */
   public void setOnmove(String value){
      _onmove = value;
   }

   /**
    * <T>设置当对象停止移动时触发。</T>
    *
    * @param value 数据内容
    */
   public void setOnmoveend(String value){
      _onmoveend = value;
   }

   /**
    * <T>设置当对象开始移动时触发。</T>
    *
    * @param value 数据内容
    */
   public void setOnmovestart(String value){
      _onmovestart = value;
   }

   /**
    * <T>设置当用户粘贴数据以便从系统剪贴板向文档传送数据时在目标对象上触发。</T>
    *
    * @param value 数据内容
    */
   public void setOnpaste(String value){
      _onpaste = value;
   }

   /**
    * <T>设置当在对象上发生对象上发生属性更改时触发。</T>
    *
    * @param value 数据内容
    */
   public void setOnpropertychange(String value){
      _onpropertychange = value;
   }

   /**
    * <T>设置当对象状态变更时触发。</T>
    *
    * @param value 数据内容
    */
   public void setOnreadystatechange(String value){
      _onreadystatechange = value;
   }

   /**
    * <T>设置当对象的大小将要改变时触发。</T>
    *
    * @param value 数据内容
    */
   public void setOnresize(String value){
      _onresize = value;
   }

   /**
    * <T>设置当用户更改完控件选中区中对象的尺寸时触发。</T>
    *
    * @param value 数据内容
    */
   public void setOnresizeend(String value){
      _onresizeend = value;
   }

   /**
    * <T>设置当用户开始更改控件选中区中对象的尺寸时触发。</T>
    *
    * @param value 数据内容
    */
   public void setOnresizestart(String value){
      _onresizestart = value;
   }

   /**
    * <T>设置对象将要被选中时触发。</T>
    *
    * @param value 数据内容
    */
   public void setOnselectstart(String value){
      _onselectstart = value;
   }

   /**
    * <T>设置当特定时间错误发生时无条件触发，通常由将属性设置为无效值导致。</T>
    *
    * @param value 数据内容
    */
   public void setOntimeerror(String value){
      _ontimeerror = value;
   }

   /**
    * <T>设置只读。</T>
    *
    * @param value 数据内容
    */
   public void setReadonly(String value){
      _readonly = value;
   }

   /**
    * <T>设置大小。</T>
    *
    * @param value 数据内容
    */
   public void setSize(String value){
      _size = value;
   }

   /**
    * <T>设置数据源。</T>
    *
    * @param value 数据内容
    */
   public void setSource(String value){
      _source = value;
   }

   /**
    * <T>设置状态。</T>
    *
    * @param value 数据内容
    */
   public void setStatus(String value){
      _status = value;
   }

   /**
    * <T>设置样式引入。</T>
    *
    * @param value 数据内容
    */
   public void setStyle(String value){
      _style = value;
   }

   /**
    * <T>设置值。</T>
    *
    * @param value 数据内容
    */
   public void setValue(String value){
      _value = value;
   }

   /**
    * <T>设置宽度。</T>
    *
    * @param value 数据内容
    */
   public void setWidth(String value){
      _width = value;
   }

}
