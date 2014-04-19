package org.mo.web.tag.page;

import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.web.protocol.context.SPageItem;
import org.mo.web.tag.base.FBaseCheckTag;

//============================================================
// <T>多选框标签</T>
// <P>添加与多选框有关的属性设置</P>
//
// @author MAOCY
// @version 1.00 - 2008/08/05 CREATE BY MAOCY
//============================================================
public class FCheckTag
      extends FBaseCheckTag
{
   // 定义多选框的样式
   public final static String STYLE = "Tag_Check";

   // 定义被选中时的图标
   public final static String IMG_CHECKED = "/res/img/sys/radio_sel.gif";

   // 定义未选中时的图标
   public final static String IMG_NOCHECK = "/res/img/sys/radio.gif";

   //============================================================
   // <T>标签内容解析开始处理。</T>
   //
   // @return 处理结果
   //============================================================
   @Override
   public int onStart(){
      /// 获得控件的显示属性
      boolean displayOnly = RBoolean.parse(_context.parseString(_display));
      /// 得到数据源的值
      SPageItem item = _context.parseItem(_source);
      /// 根据值得到名称
      String name = item.name;
      String value = null;
      /// 值为空的情况
      if(RString.isEmpty(_value)){
         value = item.valueString();
      }else{
         /// 不为空的情况
         value = _context.parseString(_value);
      }
      if(RString.isEmpty(value)){
         value = _context.parseString(_default);
      }
      /// 取得多选框编号的值
      String id = _context.parseString(_id);
      /// 取得真属性的值
      String trueValue = _context.parseString(_true);
      /// 取得假属性的值
      String falseValue = _context.parseString(_false);
      boolean result = false;
      boolean resultInit = false;
      if(!RString.isEmpty(trueValue)){
         result = trueValue.equals(value);
         resultInit = true;
      }else if(!RString.isEmpty(falseValue)){
         result = !falseValue.equals(value);
         resultInit = true;
      }
      if(!resultInit){
         result = RBoolean.parse(value);
      }
      /// 将属性添加到字符流
      _writer.append("<INPUT type='hidden' name='", name, "'");
      if(!RString.isEmpty(id)){
         _writer.appendAttribute("id", id, true);
      }
      /// 在结果为真的情况下
      if(result){
         if(!RString.isEmpty(trueValue)){
            _writer.appendAttribute("value", trueValue, true);
         }else{
            _writer.appendAttribute("value", RBoolean.TRUE_STR, true);
         }
         /// 结果为假的情况下
      }else{
         if(!RString.isEmpty(falseValue)){
            _writer.appendAttribute("value", falseValue, true);
         }else{
            _writer.appendAttribute("value", RBoolean.FALSE_STR, true);
         }
      }
      _writer.append(">");
      /// 在复选框选中的请款下
      //      if(!true){
      //         if(result){
      //            _writer.append("<IMG src='");
      //            _writer.append(_context.contextPath());
      //            _writer.append(IMG_CHECKED);
      //            _writer.append("' align='absmiddle'>");
      //            /// 复选框未选中的情况下
      //         }else{
      //            _writer.append("<IMG src='");
      //            _writer.append(_context.contextPath());
      //            _writer.append(IMG_NOCHECK);
      //            _writer.append("' align='absmiddle'>");
      //         }
      //         /// 在复选框是只读的情况下
      //      }else{
      if(displayOnly){
         if(result){
            _writer.append("<IMG src='");
            _writer.append(_context.contextPath());
            _writer.append(IMG_CHECKED);
            _writer.append("'>");
            /// 结果为假的情况下
         }else{
            _writer.append("<IMG src='");
            _writer.append(_context.contextPath());
            _writer.append(IMG_NOCHECK);
            _writer.append("'>");
         }
         /// 在复选框不是只读的情况下
      }else{
         _writer.append("<INPUT name='" + name + "_chk' type=\"checkbox\" onclick=\"__checkSync(this,'" + name + "',");
         /// 不为真的情况下
         if(!RString.isEmpty(trueValue)){
            _writer.appendSingleQuot(trueValue);
         }else{
            _writer.appendSingleQuot(RBoolean.TRUE_STR);
         }
         _writer.append(',');
         /// 不为假的情况下
         if(!RString.isEmpty(falseValue)){
            _writer.appendSingleQuot(falseValue);
         }else{
            _writer.appendSingleQuot(RBoolean.FALSE_STR);
         }
         _writer.append(")");
         if(!RString.isEmpty(_onclick)){
            _writer.append(';');
            _writer.append(_onclick);
         }
         _writer.append('"');
         if(result){
            _writer.append(" checked");
         }
         /// 添加样式
         _writer.appendAttribute("class", _style, true);
         appendHtml();
         _writer.append(">");
      }
      //      }
      /// 刷新字符流
      _writer.flush();
      return SKIP_BODY;
   }

   //============================================================
   // <T>标签内容解析结束处理。</T>
   //
   // @return 处理结果
   //============================================================
   @Override
   public int onEnd(){
      return EVAL_PAGE;
   }
}
