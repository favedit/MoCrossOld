package org.mo.web.protocol.context;

import java.io.Writer;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FString;

//============================================================
// <T>字符串写出器。</T>
//============================================================
public class FStringWriter
      extends FString
{
   // 写出器
   private Writer _writer;

   //============================================================
   // <T>构造字符串写出器。</T>
   //============================================================
   public FStringWriter(){
   }

   //============================================================
   // <T>构造字符串写出器。</T>
   //
   // @param writer 写出器
   //============================================================
   public FStringWriter(Writer writer){
      _writer = writer;
   }

   //============================================================
   // <T>输出一个属性。</T>
   //
   // @param name 名称
   // @param value 内容
   //============================================================
   public void appendAttribute(String name,
                               String value){
      append(' ');
      append(name);
      append("=\"");
      append(value);
      append('"');
   }

   //============================================================
   // <T>输出一个属性。</T>
   //
   // @param name 名称
   // @param value 内容
   // @param firstBlank 开始空白
   //============================================================
   public void appendAttribute(String name,
                               String value,
                               boolean firstBlank){
      if(firstBlank){
         append(' ');
      }
      append(name);
      append("=\"");
      append(value);
      append('"');
   }

   //============================================================
   // <T>输出一个两边单引号的字符串。</T>
   //
   // @param value 内容
   //============================================================
   public void appendSingleQuot(String value){
      append('\'');
      append(value);
      append('\'');
   }

   //============================================================
   // <T>输出一个两边双引号的字符串。</T>
   //
   // @param value 内容
   //============================================================
   public void appendQuot(String value){
      append('"');
      append(value);
      append('"');
   }

   //============================================================
   // <T>关联输出器。</T>
   //
   // @param writer 输出器
   //============================================================
   public void link(Writer writer){
      _writer = writer;
      clear();
   }

   //============================================================
   // <T>解除关联。</T>
   //============================================================
   public void unlink(){
      _writer = null;
   }

   //============================================================
   // <T>刷新数据。</T>
   //
   // @return 字符串
   //============================================================
   public void flush(){
      if((null != _memory) && (_length > 0)){
         try{
            _writer.write(_memory, 0, _length);
            _writer.flush();
            clear();
         }catch(Exception e){
            throw new FFatalError(e, "Write string failure. (value={1})", new String(_memory, 0, _length));
         }
      }
   }
}
