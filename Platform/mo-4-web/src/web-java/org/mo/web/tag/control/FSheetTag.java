/*
 * @(#)FSheetTag.java
 *
 * Copyright 2005 microbject, All Rights Reserved.
 *
 */
package org.mo.web.tag.control;

import org.mo.web.tag.common.FAbstractTag;

/**
 * <p>页面标签</p>
 *
 * @author ALEX
 * @version 1.00 - 2005/10/26
 */
public class FSheetTag
      extends FAbstractTag
{

   @Override
   public int onEnd(){
      // TODO Auto-generated method stub
      return 0;
   }

   @Override
   public int onStart(){
      // TODO Auto-generated method stub
      return 0;
   }

   //   //指定控件名称
   //   private String m_sName = null;
   //
   //   /**
   //    * <p>设置控件名称</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param sValue 控件名称
   //    */
   //   public void setName(String sValue){
   //      m_sName = sValue;
   //   }
   //
   //   //指定显示标签的信息
   //   private String m_sCaption = null;
   //
   //   /**
   //    * <p>设置显示标签的信息</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param sValue 显示标签的信息
   //    */
   //   public void setCaption(String sValue){
   //      m_sCaption = sValue;
   //   }
   //
   //   //按键的命令
   //   private String m_sAction = null;
   //
   //   /**
   //    * <p>设置按键的命令</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param sValue 按键的命令
   //    */
   //   public void setAction(String sValue){
   //      m_sAction = sValue;
   //   }
   //
   //   //指定图标
   //   private String m_sIcon = null;
   //
   //   /**
   //    * <p>设置图标</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param sValue 图标
   //    */
   //   public void setIcon(String sValue){
   //      m_sIcon = sValue;
   //   }
   //
   //   /**
   //    * <p>开始标签的逻辑</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param oContext 环境对象
   //    * @return 逻辑执行后的状态
   //    * @exception FError 逻辑例外
   //    */
   //   public int doStartTag(FWebContext oContext)
   //         throws FError{
   //      FPageControlTag oPageControlTag = (FPageControlTag) findParent(FPageControlTag.class);
   //      if (oPageControlTag != null) {
   //         FWebTabControl oPageControl = oPageControlTag.pageControl();
   //         if (oPageControl != null) {
   //            String sAction = oContext.source(m_sAction);
   //            String sIcon = oContext.source(m_sIcon);
   //            String sCaption = oContext.source(m_sCaption);
   //            FWebSheet oSheet = new FWebSheet(m_sName, sCaption, sIcon, sAction,
   //                  "Hint");
   //            oSheet.setName(m_sName);
   //            oPageControl.sheets().push(oSheet);
   //         }
   //      }
   //      return SKIP_BODY;
   //   }
}
