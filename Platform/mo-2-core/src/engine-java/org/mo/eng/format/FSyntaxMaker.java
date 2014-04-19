package org.mo.eng.format;

import org.mo.com.lang.FString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;

public class FSyntaxMaker
      extends FAbstractSyntaxMaker
      implements
         ISyntaxMaker
{
   private static ILogger _logger = RLogger.find(FSyntaxMaker.class);

   public FSyntaxMaker(){
   }

   /**
    * <p>格式化JAVA代码</p>
    * 
    * @param value 格式化内容
    * @return 格式化后的内容
    */
   @Override
   public FString format(FString value){
      if(value == null){
         return new FString();
      }
      long startTime = System.currentTimeMillis();
      FString sub = new FString(256);
      FString html = new FString(value.length() * 4);
      html.append("<OL>\n<LI>");
      char ch;
      char chPre = 0;
      int length = value.length();
      char[] memory = value.memory();
      boolean inString = false;
      boolean inComment = false;
      boolean inCommentLine = false;
      for(int i = 0; i < length; i++){
         ch = memory[i];
         if(_splitter.indexOf(ch) >= 0){
            if(!inComment){
               // 注释开始处理
               if(ch == '/'){
                  if(memory[i + 1] == '/'){
                     html.append("<FONT color='");
                     html.append(_commentColor);
                     html.append("'>//");
                     inComment = true;
                     inCommentLine = true;
                     i++;
                     continue;
                  }
                  if(memory[i + 1] == '*'){
                     html.append("<FONT color='");
                     html.append(_commentColor);
                     html.append("'>/*");
                     inComment = true;
                     i++;
                     continue;
                  }
               }
               // 字符串处理
               if(!inString){
                  if(ch == '\"'){
                     html.append("\"<FONT color='");
                     html.append(_stringColor);
                     html.append("'>");
                     inString = true;
                     continue;
                  }
               }else{
                  if(ch == '\"'){
                     html.append(sub + "</FONT>\"");
                     inString = false;
                     sub.clear();
                     continue;
                  }
               }
            }else{
               if(chPre == '*' && ch == '/'){
                  html.append("/</FONT>");
                  inComment = false;
                  continue;
               }
            }
            // 注释关闭处理
            if(!inComment){
               if(sub.length() > 0){
                  replaceFormat(html, sub);
                  sub.clear();
               }
            }else{
               html.append(sub);
               sub.clear();
            }
            // 特殊字符的处理
            if(ch == '\n'){
               if(inComment && inCommentLine){
                  html.append("</FONT>");
                  inComment = false;
                  inCommentLine = false;
               }
               if(inComment){
                  html.append("</FONT></LI>\n<LI><FONT color='");
                  html.append(_commentColor);
                  html.append("'>");
               }else{
                  html.append("</LI>\n<LI>");
               }
            }else if(ch == ' '){
               html.append("&nbsp;");
            }else if(ch == '&'){
               html.append("&amp;");
            }else if(ch == '<'){
               html.append("&lt;");
            }else if(ch == '>'){
               html.append("&gt;");
            }else if(ch == '\r'){
               continue;
            }else if(ch == '\"'){
               html.append("&quot;");
            }else if(ch == '\t'){
               html.append("&quot;&quot;&quot;");
            }else{
               html.append(ch);
            }
            // 字符处理
            chPre = ch;
            sub.clear();
         }else{
            sub.append(memory[i]);
         }
      }
      if(!sub.isEmpty()){
         replaceFormat(html, sub);
      }
      html.append("</LI></OL>\n");
      if(_logger.debugAble()){
         _logger.debug(this, "format", "Format source in {0} ms", System.currentTimeMillis() - startTime);
      }
      return html;
   }
}
