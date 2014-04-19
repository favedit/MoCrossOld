/*
 * @(#)FIterationTag.java
 *
 * Copyright 2005 microbject, All Rights Reserved.
 *
 */
package org.mo.web.tag.common;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.IterationTag;
import org.mo.com.lang.FError;

/**
 * <P>可以进行迭代的标签基类</P>
 *
 * @author MAOCY
 * @version 1.00 - 2008/08/05 CREATE BY MAOCY
 * @version 1.01 - 2009/01/05 UPDATE BY YANGH
 */
public abstract class FAbstractIterationTag
      extends FAbstractTag
      implements
         IterationTag
{

   /**
    * <P>标签内容解析后的逻辑</P>
    * <P>create date:2008/08/05</P>
    * <P>update date:2009/01/05</P>
    *
    * @return 逻辑执行后的状态
    * @exception FError 逻辑例外
    */
   public int doAfterBody() throws JspException{
      try{
         return onAfter();
      }catch(Exception e){
         throw new JspException(e);
      }
   }

   /**
    * <P>标签内容解析后的逻辑</P>
    * <P>create date:2008/08/05</P>
    * <P>update date:2009/01/05</P>
    *
    * @param oContext 环境对象
    * @return 逻辑执行后的状态
    * @exception FError 逻辑例外
    */
   public abstract int onAfter();

}
