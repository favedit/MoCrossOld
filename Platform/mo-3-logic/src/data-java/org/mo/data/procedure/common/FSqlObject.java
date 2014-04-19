/*
 * @(#)FSqlObject.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.data.procedure.common;

import org.mo.com.lang.FObject;
import org.mo.com.lang.IDump;

public abstract class FSqlObject
      extends FObject
      implements
         IDump
{
   protected FSqlParserContext _context;

   public FSqlObject(FSqlParserContext context){
      _context = context;
   }

   public FSqlParserContext context(){
      return _context;
   }

   public void setContext(FSqlParserContext context){
      _context = context;
   }
}
