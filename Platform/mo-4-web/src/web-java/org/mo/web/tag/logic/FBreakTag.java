/*
 * @(#)FBreakTag.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.web.tag.logic;

import org.mo.web.tag.base.FBaseBreakTag;

/**
 * <T>跳出循环标签</T>
 * <P>该标签用来让程序跳出循环</P>
 * 
 * @author MAOCY
 * @version 1.00 - 2008/08/05 CREATE BY MAOCY
 * @version 1.01 - 2009/01/05 UPDATE BY YANGH
 */
public class FBreakTag
      extends FBaseBreakTag
{

   @Override
   public int onEnd(){
      return EVAL_PAGE;
   }

   @Override
   public int onStart(){
      return SKIP_BODY;
   }

}
