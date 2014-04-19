package org.mo.eng.template.tag;

import org.mo.com.lang.RString;
import org.mo.com.lang.generic.MString;

//============================================================
// <T>模板标签。</T>
//============================================================
public class FTplTag
      extends FAbstractTplTag
{
   // 文本内容
   protected String _text;

   // 截取行标志
   protected boolean _trimLineFlag = false;

   //============================================================
   // <T>设置文本内容。</T>
   //
   // @param text 文本内容
   //============================================================
   public void setText(String text){
      _text = text;
   }

   //============================================================
   // <T>截取行内容。</T>
   //============================================================
   public void trimLine(){
      if(!_trimLineFlag){
         _text = RString.nvl(_text);
         if(_text.startsWith("\r") || _text.startsWith("\n")){
            _text = _text.substring(1);
         }else if(_text.startsWith("\r\n")){
            _text = _text.substring(2);
         }
         if(_text.endsWith("\r") || _text.endsWith("\n")){
            _text = _text.substring(0, _text.length() - 1);
         }else if(_text.endsWith("\r\n")){
            _text = _text.substring(0, _text.length() - 2);
         }
         _trimLineFlag = true;
      }
   }

   //============================================================
   // <T>开始处理。</T>
   //
   // @return 处理结果
   //============================================================
   @Override
   public int onStart(){
      _context.append(_text);
      return INCLUDE;
   }

   //============================================================
   // <T>循环处理。</T>
   //
   // @return 处理结果
   //============================================================
   @Override
   public int onLoop(){
      return STOP;
   }

   //============================================================
   // <T>结束处理。</T>
   //
   // @return 处理结果
   //============================================================
   @Override
   public int onEnd(){
      return CONTINUE;
   }

   //============================================================
   // <T>输出内部信息。</T>
   //
   // @param dump 内部信息
   //============================================================
   @Override
   public void onDump(MString dump){
      dump.append("FTplTag");
   }
}
