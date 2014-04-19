/*
 * @(#)FAbstractSyntaxConsole.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.eng.format;

import org.mo.com.lang.FString;
import org.mo.com.lang.RString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.FXmlDocument;
import org.mo.com.xml.FXmlNode;
import org.mo.core.aop.face.AProperty;

/**
 * <p>HTML加色控制台基类</p>
 * 
 * @author maocy
 */
public class FAbstractSyntaxConsole
{
   private static ILogger _logger = RLogger.find(FAbstractSyntaxConsole.class);

   private FString[] _colors;

   @AProperty
   protected String _commentColor;

   @AProperty
   protected String _config;

   @AProperty
   protected String _splitter;

   @AProperty
   protected String _stringColor;

   @AProperty
   protected int _wordCount;

   private FString[] _words;

   /**
    * <p>格式化JAVA代码</p>
    * 
    * @param value 格式化内容
    * @return 格式化后的内容
    */
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

   public void initialize(){
      //_config = configNode().nodeText("Config");
      FXmlDocument oDocument = new FXmlDocument();
      oDocument.loadFile(_config);
      FXmlNode oConfigNode = oDocument.root();
      _splitter = oConfigNode.nodeText("Splitter");
      _splitter = RString.replace(_splitter, "\\r", "\r");
      _splitter = RString.replace(_splitter, "\\n", "\n");
      _splitter = RString.replace(_splitter, "\\t", "\t");
      FXmlNode oFormatNode = oConfigNode.findNode("Format");
      _commentColor = oFormatNode.nodeText("Comment", "color");
      _stringColor = oFormatNode.nodeText("String", "color");
      FXmlNode oWordNode = oConfigNode.findNode("Word");
      _wordCount = oWordNode.nodes().count();
      FXmlNode oWordItemNode = null;
      _words = new FString[_wordCount];
      _colors = new FString[_wordCount];
      for(int n = 0; n < _wordCount; n++){
         oWordItemNode = oWordNode.node(n);
         _colors[n] = new FString(oFormatNode.findNode(oWordItemNode.name()).get("color"));
         _words[n] = new FString(makeWordString(oWordItemNode.text()));
      }
   }

   public String makeWordString(String sWords){
      FString sWordString = new FString(" ");
      String[] arLines = RString.split(sWords, '\n');
      for(String sLine : arLines){
         sWordString.append(sLine.trim());
         sWordString.append(" ");
      }
      return sWordString.toString();
   }

   // 格式替换函数
   private void replaceFormat(FString sResult,
                              FString sSub){
      //      for(int n = 0; n < _wordCount; n++){
      //         if(_words[n].indexOf(' ', sSub, ' ') >= 0){
      //            sResult.append("<FONT color='");
      //            sResult.append(_colors[n]);
      //            sResult.append("'>");
      //            sResult.append(sSub);
      //            sResult.append("</FONT>");
      //            return;
      //         }
      //      }
      //      sResult.append(sSub);
   }
}
