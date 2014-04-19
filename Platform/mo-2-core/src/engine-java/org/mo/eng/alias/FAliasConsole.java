/*
 * @(#)FAliasConsole.java
 *
 * Copyright 2005 microbject, All Rights Reserved.
 *
 */
package org.mo.eng.alias;

import org.mo.com.lang.FFatalError;
import org.mo.com.xml.FXmlNode;
import org.mo.core.aop.face.AProperty;
import org.mo.eng.common.FAbstractConfigConsole;

/**
 * <p>别称控制台</p>
 * <p>1. 提供对XML别称设置的访问</p>
 * 
 * @author ALEX
 * @version 1.00 - 2005/11/07
 */
public class FAliasConsole
      extends FAbstractConfigConsole
      implements
         IAliasConsole
{
   @AProperty
   private String _type;

   private final String _dataName = "Alias";

   public void initializeRegister(){
      //_dataConsole.registerType(_type);
   }

   public boolean isAlias(String source){
      return isSource(source);
   }

   public String alias(String source){
      String[] items = splitSource(source);
      if(items.length == 2){
         FXmlNode node = findNode(_type, items[0], _dataName, items[1]);
         if(node != null){
            return node.text();
         }
      }else{
         throw new FFatalError("Source invalid.({0})", source);
      }
      return null;
   }
}
