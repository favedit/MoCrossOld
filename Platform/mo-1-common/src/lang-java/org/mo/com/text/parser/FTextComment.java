package org.mo.com.text.parser;

import org.mo.com.lang.FObject;
import org.mo.com.lang.FStrings;

//============================================================
// <T>文本注释。</T>
//============================================================
public class FTextComment
      extends FObject
{
   // 代码
   protected FStrings _sourceLines = new FStrings();

   //============================================================
   // <T>文本注释。</T>
   //============================================================
   public FTextComment(){
   }

   public FStrings sourceLines(){
      return _sourceLines;
   }

   //============================================================
   // <T>增加注释字符串。</T>
   //============================================================
   public void assign(FTextComment comment){
      _sourceLines.assign(comment.sourceLines());
   }

   //============================================================
   // <T>增加注释字符串。</T>
   //============================================================
   public void push(String line){
      _sourceLines.push(line);
   }
}
