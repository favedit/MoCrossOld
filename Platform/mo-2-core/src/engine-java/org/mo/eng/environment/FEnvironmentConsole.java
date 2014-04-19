/*
 * @(#)FEnvironmentConsole.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.eng.environment;

import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObjects;
import org.mo.com.lang.IAttributes;
import org.mo.com.lang.RString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.AProperty;
import org.mo.eng.environment.common.XEnvironment;
import org.mo.eng.environment.common.XProperty;
import org.mo.eng.store.FXmlConfigConsole;
import org.mo.eng.template.common.XVariable;

/**
 * 全局环境配置控制台
 * 
 * @author maocy
 */
public class FEnvironmentConsole
      extends FXmlConfigConsole<XEnvironment>
      implements
         IEnvironmentConsole
{
   private static final ILogger _logger = RLogger.find(FEnvironmentConsole.class);

   private final FAttributes _defines = new FAttributes();

   @AProperty
   protected String _homepath;

   private final FAttributes _registers = new FAttributes();

   public final String FLG_BEGIN = "${";

   public final String FLG_END = "}";

   public final char FLG_SPLITER_CHAR = '|';

   @Override
   protected FObjects<XEnvironment> createCollection(){
      return new FObjects<XEnvironment>(XEnvironment.class);
   }

   @Override
   public String findDefine(String source){
      // 判断是否已经取过
      if(_defines.contains(source)){
         return _defines.get(source);
      }
      // 获得新的定义
      String[] items = RString.splitTwo(source, FLG_SPLITER_CHAR, false);
      if(null != items){
         loadDefine(items[0]);
      }
      // 返回结果
      return _defines.get(source);
   }

   @Override
   public String findHomePath(){
      return _homepath;
   }

   @Override
   public String findRegister(String name){
      return _registers.get(name);
   }

   @Override
   public String findServerDefine(String server,
                                  String source){
      String serverName = source + "@" + server;
      // 判断是否已经取过
      if(_defines.contains(serverName)){
         return _defines.get(serverName);
      }
      if(_defines.contains(source)){
         return _defines.get(source);
      }
      // 获得新的定义
      String[] items = RString.splitTwo(source, FLG_SPLITER_CHAR, false);
      if(null != items){
         loadDefine(items[0]);
      }
      // 返回结果
      if(_defines.contains(serverName)){
         return _defines.get(serverName);
      }
      return _defines.get(source);
   }

   protected void loadDefine(String environmentName){
      XEnvironment xenvironment = get(environmentName);
      if(xenvironment.hasChild()){
         // 处理所有属性
         for(IXmlObject xproperty : xenvironment.children()){
            if(XProperty.isInstance(xproperty)){
               XProperty property = (XProperty)xproperty;
               String propertyName = property.getName();
               _defines.set(environmentName + FLG_SPLITER_CHAR + propertyName, property.getDataValue());
               // 处理所有变量
               if(property.hasChild()){
                  for(IXmlObject xvariable : property.children()){
                     if(XVariable.isInstance(xvariable)){
                        //                        XVariable variable = (XVariable)xvariable;
                        //                        String serverName = variable.getServer();
                        //                        _defines.set(environmentName + FLG_SPLITER_CHAR + propertyName + "@" + serverName, variable.getDataValue());
                     }
                  }
               }
            }
         }
      }
   }

   @Override
   public void loadDirectory(String directory){
      _logger.debug(this, "loadDirectory", "Load directory config. (directory={0})", directory);
   }

   @Override
   public String parse(String source){
      // 如果源为空的情况
      if(null == source){
         return null;
      }
      // 分解 ${?|?}的情况
      int start = source.lastIndexOf(FLG_BEGIN);
      if(-1 != start){
         int end = source.indexOf(FLG_END, start);
         if(-1 == end){
            throw new FFatalError("Invalid source (source={0}).", source);
         }
         // 获得内部定义的对象
         String value = findDefine(source.substring(start + FLG_BEGIN.length(), end));
         // 将对象转换为系统标识，重新取最终内容
         source = source.substring(0, start) + value + source.substring(end + FLG_END.length());
         if(source.contains(FLG_BEGIN)){
            return parse(source);
         }
      }
      return source;
   }

   @Override
   public String parseServer(String server,
                             String source){
      // 如果源为空的情况
      if(null == source){
         return null;
      }
      // 分解 ${?|?}的情况
      int start = source.lastIndexOf(FLG_BEGIN);
      if(-1 != start){
         int end = source.indexOf(FLG_END, start);
         if(-1 == end){
            throw new FFatalError("Invalid source (source={0}).", source);
         }
         // 获得内部定义的对象
         String value = findServerDefine(server, source.substring(start + FLG_BEGIN.length(), end));
         // 将对象转换为系统标识，重新取最终内容
         source = source.substring(0, start) + value + source.substring(end + FLG_END.length());
         if(source.contains(FLG_BEGIN)){
            return parseServer(server, source);
         }
      }
      return source;
   }

   @Override
   public void register(String name,
                        String value){
      _registers.set(name, value);
      _defines.set(name, value);
   }

   @Override
   public IAttributes registers(){
      return _registers;
   }

   @Override
   public void reset(){
      _defines.clear();
   }
}
