package org.mo.com.data;

import org.mo.com.lang.FString;
import org.mo.com.lang.RString;

//============================================================
// <T>SQL字符串。</T>
//============================================================
public class FSql
      extends FString
{
   //============================================================
   // <T>构造SQL字符串。</T>
   //============================================================
   public FSql(){
   }

   //============================================================
   // <T>构造SQL字符串。</T>
   //
   // @param capacity 容量 
   //============================================================
   public FSql(int capacity){
      super(capacity);
   }

   //============================================================
   // <T>构造SQL字符串。</T>
   //
   // @param data 字符集合 
   // @param charset 编码名称 
   //============================================================
   public FSql(byte[] data,
               String charset){
      append(data, charset);
   }

   //============================================================
   // <T>构造SQL字符串。</T>
   //
   // @param data 字符集合 
   //============================================================
   public FSql(char[] data){
      append(data, 0, data.length);
   }

   //============================================================
   // <T>构造SQL字符串。</T>
   //
   // @param data 字符集合 
   // @param offset 索引 
   // @param length 长度
   //============================================================
   public FSql(char[] data,
               int offset,
               int length){
      append(data, offset, length);
   }

   //============================================================
   // <T>构造SQL字符串。</T>
   //
   // @param value 字符串 
   //============================================================
   public FSql(String value){
      assign(value);
   }

   //============================================================
   // <T>构造SQL字符串。</T>
   //
   // @param value 字符串 
   //============================================================
   public FSql(FString value){
      assign(value);
   }

   //============================================================
   // <T>追加布尔字符串。</T>
   //
   // @param value 字符串 
   //============================================================
   public void appendFieldBoolean(String value){
      if(!RString.isEmpty(value)){
         append('\'');
         append(value);
         append('\'');
      }else{
         append(RSql.NULL);
      }
   }

   //============================================================
   // <T>追加时间字符串。</T>
   //
   // @param value 字符串 
   //============================================================
   public void appendFieldDate(String value){
      if(!RString.isEmpty(value)){
         append('\'');
         append(value);
         append('\'');
      }else{
         append(RSql.NULL);
      }
   }

   //============================================================
   // <T>追加整数字符串。</T>
   //
   // @param value 字符串 
   //============================================================
   public void appendFieldInteger(String value){
      if(!RString.isEmpty(value)){
         append(value);
      }else{
         append(RSql.NULL);
      }
   }

   //============================================================
   // <T>追加字符字符串。</T>
   //
   // @param value 字符串 
   //============================================================
   public void appendFieldString(String value){
      if(!RString.isEmpty(value)){
         append('\'');
         append(value);
         append('\'');
      }else{
         append(RSql.NULL);
      }
   }

   //============================================================
   // <T>追加字符字符串。</T>
   //
   // @param value 字符串 
   //============================================================
   public void appendFieldString(FString value){
      if(value != null && !value.isEmpty()){
         append('\'');
         append(value);
         append('\'');
      }else{
         append(RSql.NULL);
      }
   }

   //============================================================
   // <T>绑定字符串。</T>
   //
   // @param name 名称 
   // @param value 内容 
   //============================================================
   public void bind(String name,
                    String value){
      innerReplace("{" + name + "}", value);
   }

   //============================================================
   // <T>绑定字符串。</T>
   //
   // @param name 名称 
   // @param value 内容 
   //============================================================
   public void bindString(String name,
                          String value){
      innerReplace("{" + name + "}", "'" + value + "'");
   }

   //============================================================
   // <T>获得内容字符串。</T>
   //
   // @return 字符串
   //============================================================
   @Override
   public String toString(){
      return RSql.formatSql(super.toString());
   }
}
