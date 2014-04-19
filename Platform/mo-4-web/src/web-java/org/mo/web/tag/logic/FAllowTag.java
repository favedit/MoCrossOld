/*
 * @(#)FAllowTag.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.web.tag.logic;

import org.mo.web.tag.base.FBaseAllowTag;

/**
 * <T>允许标签</T>
 * <P>判断当前用户是否有权限进行操作</P>
 * 
 * @author MAOCY
 * @version 1.00 - 2008/08/05 CREATE BY MAOCY
 * @version 1.01 - 2009/01/05 UPDATE BY YANGH
 */
public class FAllowTag
      extends FBaseAllowTag
{

   @Override
   public int onEnd(){
      /// 返回逻辑执行结果
      return EVAL_PAGE;
   }

   @Override
   public int onStart(){
      //boolean bInResult = !FStringUtil.isEmpty(m_sRoles) ? oContext.session().permission()
      //      .inRoles(m_sRoles) : true;
      //boolean bOutResult = !FStringUtil.isEmpty(m_sOutRoles) ? oContext.session().permission()
      //      .inRoles(m_sOutRoles) : true;
      //return (bInResult && bOutResult) ? EVAL_BODY_INCLUDE : SKIP_BODY;
      return EVAL_BODY_INCLUDE;
   }
}
