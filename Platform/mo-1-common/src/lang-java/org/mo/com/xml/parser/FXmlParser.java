/*
 * @(#)FXMLParser.java
 *
 * Copyright 2008 Java Frame Application Corporation.
 * All Rights Reserved.
 *
 */
package org.mo.com.xml.parser;

import org.mo.com.io.FStringFile;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FString;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlText;

/**
 * <p>XML文档解析类</p>
 *
 * @author maocy
 */
public class FXmlParser
{
   // 根元素
   private FXmlNode _root;

   // 开始标签前缀
   private String _space;

   // 开始标签
   private String _start;

   // 开始标签数组
   private char[] _tagFlags;

   // 开始标签长度
   private int _startLength;

   // 结束标签
   private String _tagEnd;

   public FXmlParser(){
   }

   /**
    * <p>创建文档的文档解析类的实例</p>
    *
    */
   public FXmlParser(String space){
      setSpace(space);
   }

   public void parse(FString xml){
      parseSource(root(), xml.toArray(), 0, xml.length(), null);
   }

   /**
    * <p>解析代码</p>
    *
    * @param xml
    */
   public void parse(String xml){
      parseSource(root(), xml.toCharArray(), 0, xml.length(), null);
   }

   /**
    * <p>解析元素属性</p>
    *
    * @param node 元素对象
    * @param sAttribute 属性内容
    */
   public void parseAttribute(FXmlNode node,
                              String sAttribute){
      sAttribute = sAttribute.trim();
      int nLength = sAttribute.length();
      if(nLength > 0){
         int n = 0;
         char loopChar = 0;
         char valueChar = 0;
         char[] arChars = sAttribute.toCharArray();
         String name = null;
         String value = null;
         FString nameBuffer = new FString();
         FString valueBuffer = new FString();
         FAttributes attributes = node.attributes();
         while(true){
            // 分解属性名称
            if(n >= nLength){
               break;
            }
            for(; n < nLength; n++){
               loopChar = arChars[n];
               if(loopChar == '='){
                  name = nameBuffer.toString();
                  nameBuffer.clear();
                  break;
               }else{
                  nameBuffer.append(loopChar);
               }
            }
            // 分解属性内容
            if(n + 1 >= nLength){
               break;
            }
            n++;
            valueChar = arChars[n];
            if(valueChar == '\'' || valueChar == '\"'){
               n++;
            }else{
               valueChar = ' ';
            }
            for(; n < nLength; n++){
               loopChar = arChars[n];
               if(loopChar == valueChar){
                  value = valueBuffer.toString();
                  valueBuffer.clear();
                  if(valueChar != ' '){
                     n++;
                  }
                  attributes.set(name.trim(), value);
                  break;
               }else{
                  valueBuffer.append(loopChar);
               }
            }
         }
      }
   }

   /**
    * <p>解析代码</p>
    *
    * @param fileName 文件名称
    */
   public void parseFile(String fileName){
      FStringFile file = new FStringFile(fileName);
      parseSource(root(), file.toArray(), 0, file.length(), null);
   }

   /**
    * <p>解析代码</p>
    *
    * @param node 元素对象
    * @param chars 解析字符流
    * @param start 开始解析位置
    * @param length 解析长度
    * @param endTag 标签结束标志
    * @return 解析位置
    */
   public int parseSource(FXmlNode node,
                          char[] chars,
                          int start,
                          int length,
                          String endTag){
      int n = start;
      char loopChar = 0;
      FString temp = new FString();
      FString tagName = new FString();
      FString tagContent = new FString();
      int equalsLength = 0;
      boolean findEndTag = (endTag != null);
      char[] endTagChars = null;
      int endTagLength = 0;
      int endEqualsLength = 0;
      if(findEndTag){
         endTagChars = endTag.toCharArray();
         endTagLength = endTag.length();
      }
      FXmlNode childNode = null;
      FXmlText childText = null;
      for(; n < length; n++){
         loopChar = chars[n];
         // 查找标签结束位置
         if(findEndTag){
            endEqualsLength = 0;
            for(int i = 0; i < endTagLength; i++){
               if(chars[n + i] == endTagChars[i]){
                  endEqualsLength++;
                  continue;
               }
               break;
            }
            if(endEqualsLength == endTagLength){
               // 计算结束位置
               n += endTagLength - 1;
               if(temp.length() > 0){
                  childText = new FXmlText();
                  childText.setText(temp.toString());
                  node.push(childText);
                  temp.clear();
               }
               return n;
            }
         }
         // 查找标签开始位置
         equalsLength = 0;
         for(int i = 0; i < _startLength; i++){
            if(chars[n + i] == _tagFlags[i]){
               equalsLength++;
               continue;
            }
            break;
         }
         if(equalsLength == _startLength){
            // 保存将前的内容
            if(temp.length() > 0){
               childText = new FXmlText();
               childText.setText(temp.toString());
               node.push(childText);
               temp.clear();
            }
            // 分解标签
            n += _startLength;
            boolean bTagClose = true; // 当前标签是否结束
            boolean bTagNameFind = false; // 当前标签是否结束
            // 查找标签名称和标签内容
            tagName.clear();
            tagContent.clear();
            while(true){
               loopChar = chars[n];
               if(loopChar == ' '){
                  bTagNameFind = true;
               }
               if(loopChar == '>'){
                  bTagNameFind = true;
                  bTagClose = false;
                  break;
               }
               if(loopChar == '/' && chars[n + 1] == '>'){
                  bTagNameFind = true;
                  bTagClose = true;
                  n++;
                  break;
               }
               if(!bTagNameFind){
                  tagName.append(loopChar);
               }else{
                  tagContent.append(loopChar);
               }
               n++;
            }
            if(!bTagClose){
               // 递归分析为关闭的标签
               childNode = new FXmlNode();
               childNode.setName(tagName.toString());
               node.push(childNode);
               parseAttribute(childNode, tagContent.toString());
               n = parseSource(childNode, chars, n + 1, length, _tagEnd + tagName.toString() + ">");
            }else{
               // 分析已经关闭的标签
               childNode = new FXmlNode();
               childNode.setName(tagName.toString());
               node.push(childNode);
               parseAttribute(childNode, tagContent.toString());
            }
         }else{
            temp.append(loopChar);
         }
      }
      if(temp.length() > 0){
         childText = new FXmlText();
         childText.setText(temp.toString());
         node.push(childText);
         temp.clear();
      }
      return n;
   }

   /**
    * <p>获得根元素</p>
    *
    * @return 根元素
    */
   public FXmlNode root(){
      if(_root == null){
         _root = new FXmlNode("Root");
      }
      return _root;
   }

   /**
    * <p>设置标签格式</p>
    *
    * @param space 标签
    * @return TRUE：成功<BR>FALSE：失败
    */
   public void setSpace(String space){
      _space = space;
      _start = "<" + space + ":";
      _tagFlags = _start.toCharArray();
      _startLength = _start.length();
      _tagEnd = "</" + space + ":";
   }

   /**
    * <p>获得标签格式</p>
    *
    * @return 根元素
    */
   public String space(){
      return _space;
   }
}
