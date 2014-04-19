/*
 * @(#)FXmlNodeMapMap.java
 *
 * Copyright 2005 microbject, All Rights Reserved.
 *
 */
package org.mo.com.xml;

import org.mo.com.lang.FDictionary;

/**
 * <p>XML节点哈希表的哈希表</p>
 * 
 * @author ALEX
 * @version 1.00 - 2005/10/25
 */
public class FXmlNodeDictionarys
      extends FDictionary<FXmlNodeDictionary>
{
   public FXmlNodeDictionarys(){
      super(FXmlNodeDictionary.class);
   }

   public FXmlNodeDictionary sync(String name){
      FXmlNodeDictionary map = get(name);
      if(map == null){
         map = new FXmlNodeDictionary();
         set(name, map);
      }
      return map;
   }
}
