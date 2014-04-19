package org.mo.com.text.parser;

import org.mo.com.lang.FObject;

//============================================================
// <T>文本环境。</T>
//============================================================
public class FTextContext
      extends FObject
      implements
         ITextContext
{
   protected String LineSplitters = "\r\n";

   protected String Splitters = "\r\n";

   // 文本行集合
   protected FTextLines _lines = new FTextLines();

   //============================================================
   // <T>文本环境。</T>
   //============================================================
   public FTextContext(){
   }

   public FTextLines lines(){
      return _lines;
   }

   public boolean isLine(char value){
      return (-1 != LineSplitters.indexOf(value));
   }

   public boolean isLineComment(String line){
      return line.startsWith("//");
   }

   public boolean isTokenBegin(char value){
      return ('{' == value);
   }

   public boolean isTokenEnd(char value){
      return ('}' == value);
   }

   public boolean isSplitter(char value){
      return (-1 != Splitters.indexOf(value));
   }
}
