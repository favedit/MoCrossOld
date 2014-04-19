/*
 * @(#)FJsHelper.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.web.core.parser.js;

import org.mo.com.io.RFile;
import org.mo.com.lang.FStringSet;
import org.mo.com.lang.FStrings;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.FXmlNode;

/**
 * <T></T>
 *
 * @author ZENGD
 * @version 1.0.1
 */
public class FJsHelper
{

   // 定义类
   private FJsClasses _classes;

   // 定义编码
   private String _encoding = "UTF-8";

   // 日志
   private static ILogger _logger = RLogger.find(FJsHelper.class);

   /**
    * <T>初始化类对象</T>
    * 
    * @return 类对象
    */
   public FJsClasses classes(){
      if(null == _classes){
         _classes = new FJsClasses();
      }
      return _classes;
   }

   /**
    * <T>建立目录的xml</T>
    * 
    * @param space 目录名
    * @return 目录的XML
    */
   public FXmlNode makeCatalog(String space){
      FXmlNode root = new FXmlNode();
      root.set("space", space);
      if(null != _classes){
         for(FJsClass clazz : _classes.toObjects()){
            if(space.equals(clazz.space())){
               FXmlNode classNode = root.createNode("Class");
               classNode.set("space", clazz.space());
               classNode.set("name", clazz.name());
               classNode.set("description", clazz.description());
            }
         }
      }
      return root;
   }

   /**
    * <T>建立空间的xml</T>
    * 
    * @return 空间的xml
    */
   public FXmlNode makeSpace(){
      FXmlNode root = new FXmlNode();
      FStringSet set = new FStringSet();
      if(null != _classes){
         for(FJsClass clazz : _classes.toObjects()){
            String space = clazz.space();
            if(!set.contains(space)){
               FXmlNode classNode = root.createNode("Space");
               classNode.set("id", set.count());
               classNode.set("name", space);
               set.push(space);
            }
         }
      }
      return root;
   }

   /**
    * <T>依据空间名和文件路径编辑类的XML并将添加到类对象队列中</T>
    * 
    * @param space 空间名
    * @param directory 文件路径
    */
   public void parseSpace(String space,
                          String directory){
      FJsParser parser = new FJsParser();
      _logger.debug(this, "parseSpace", "Parse space (space={0},directory={1})", space, directory);
      FStrings files = RFile.listFiles(directory);
      for(String file : files){
         if(file.endsWith(".js")){
            FJsClass clazz = parser.parse(file, _encoding);
            if(null != clazz){
               clazz.setSpace(space);
               classes().push(clazz);
            }
         }
      }
   }

}
