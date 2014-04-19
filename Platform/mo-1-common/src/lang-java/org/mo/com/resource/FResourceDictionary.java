/*
 * @(#)FResourceMap.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.com.resource;

import org.mo.com.lang.FDictionary;

//============================================================
// <T>资源字典。</T>
//============================================================
public class FResourceDictionary
      extends FDictionary<FResource>
{
   //============================================================
   // <T>构造资源字典。</T>
   //============================================================
   public FResourceDictionary(){
      super(FResource.class);
   }
}
