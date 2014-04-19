/*
 * @(#)FFileInfos.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.core.monitor.common;

import org.mo.com.lang.FDictionary;

public class FFileInfos
      extends FDictionary<FFileInfo>
{
   public FFileInfos(){
      super(FFileInfo.class);
   }

   public void push(FFileInfo info){
      set(info.fileName(), info);
   }
}
