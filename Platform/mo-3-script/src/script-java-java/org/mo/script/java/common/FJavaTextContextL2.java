package org.mo.script.java.common;

import org.mo.com.text.parser.FTextContext;

//============================================================
// <T>文本环境。</T>
//============================================================
public class FJavaTextContextL2
      extends FTextContext
{
   //============================================================
   // <T>文本环境。</T>
   //============================================================
   public FJavaTextContextL2(){
   }

   public boolean isLine(char value){
      return (-1 != LineSplitters.indexOf(value));
   }

   public boolean isLineComment(String line){
      return line.startsWith("//");
   }

   public boolean isTokenBegin(char value){
      return ('(' == value);
   }

   public boolean isTokenEnd(char value){
      return (')' == value);
   }

   public boolean isSplitter(char value){
      return (-1 != Splitters.indexOf(value));
   }
}
