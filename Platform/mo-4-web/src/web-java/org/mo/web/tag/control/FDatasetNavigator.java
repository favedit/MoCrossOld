/*
 * @(#)FDatasetNavigator.java
 *
 * Copyright 2004 Java Frame Studio Corporation.
 * All Rights Reserved.
 *
 */

package org.mo.web.tag.control;

import org.mo.web.tag.common.FAbstractTag;

/**
 * <p>分页控件</p>
 *
 * @author FEDT
 */
public class FDatasetNavigator
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
   //
   //   // 按键的命令
   //   private String m_sAction = null;
   //
   //   private String m_sFirst = null;
   //
   //   // 指定格式
   //   private String m_sFormat = null;
   //
   //   //指定内容
   //   private String m_sItem = null;
   //
   //   private String m_sLast = null;
   //
   //   // 控件名称
   //   private String m_sName = null;
   //
   //   private String m_sNext = null;
   //
   //   private String m_sPrior = null;
   //
   //   // 指定资源对象
   //   private String m_sSource = null;
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
   //      FString sBuffer = oContext.responseBuffer();
   //      String sPageId = pageId();
   //      String sLanguage = oContext.language();
   //      FTranslateConsole oTsConsole = FTranslateManager.console();
   //      String sFirst = oTsConsole.translate(ITranslateConstants.WEB_JSP, sPageId, m_sFirst, sLanguage);
   //      String sPrior = oTsConsole.translate(ITranslateConstants.WEB_JSP, sPageId, m_sPrior, sLanguage);
   //      String sNext = oTsConsole.translate(ITranslateConstants.WEB_JSP, sPageId, m_sNext, sLanguage);
   //      String sLast = oTsConsole.translate(ITranslateConstants.WEB_JSP, sPageId, m_sLast, sLanguage);
   //      String sFormat = oContext.source(m_sFormat);
   //      if(!RString.isEmpty(m_sFormat)){
   //         sFormat = oContext.source(m_sFormat);
   //      }
   //      if(!RString.isEmpty(sFormat)){
   //         sFormat = sFormat.toLowerCase();
   //      }
   //      FDataset oDataset = (FDataset) oContext.sourceObject(m_sItem);
   //      int nPagePosition = oDataset.pagePosition();
   //      int nPageCount = oDataset.pageCount();
   //      int nTotalCount = oDataset.totalCount();
   //      if(nPagePosition > 1){
   //         sFormat = RString.parse(sFormat, "first", "<a href='#' onclick='" + m_sAction + "(1)'>" + sFirst + "</a>");
   //         //oContext.defineString("navigator_first_page", "1");
   //      }else{
   //         sFormat = RString.parse(sFormat, "first", sFirst);
   //         //oContext.defineString("navigator_first_page", "");
   //      }
   //      if(nPagePosition > 1 && nPageCount > 0){
   //         sFormat = RString.parse(sFormat, "prior", "<a href='#' onclick='" + m_sAction + "(" + (nPagePosition - 1) + ")'>" + sPrior + "</a>");
   //         //oContext.defineString("navigator_prior_page", Integer.toString(nPagePosition - 1));
   //      }else{
   //         sFormat = RString.parse(sFormat, "prior", sPrior);
   //         //oContext.defineString("navigator_prior_page", "");
   //      }
   //      if(nPagePosition < nPageCount && nPageCount > 0){
   //         sFormat = RString.parse(sFormat, "next", "<a href='#' onclick='" + m_sAction + "(" + (nPagePosition + 1) + ")'>" + sNext + "</a>");
   //         //oContext.defineString("navigator_next_page", Integer.toString(nPagePosition + 1));
   //      }else{
   //         sFormat = RString.parse(sFormat, "next", sNext);
   //         //oContext.defineString("navigator_next_page", "");
   //      }
   //      if(nPagePosition < nPageCount){
   //         sFormat = RString.parse(sFormat, "last", "<a href='#' onclick='" + m_sAction + "(" + nPageCount + ")'>" + sLast + "</a>");
   //         //oContext.defineString("navigator_last_page", Integer.toString(nPageCount));
   //      }else{
   //         sFormat = RString.parse(sFormat, "last", sLast);
   //         //oContext.defineString("navigator_last_page", "");
   //      }
   //      sFormat = RString.parse(sFormat, "page", nPagePosition);
   //      sFormat = RString.parse(sFormat, "pages", nPageCount);
   //      sFormat = RString.parse(sFormat, "total", nTotalCount);
   //      sBuffer.append(sFormat);
   //      flush();
   //      return SKIP_BODY;
   //   }
   //
   //   /**
   //    * <p>设置按键的命令</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param sValue 按键的命令
   //    * @return TRUE：成功<br>FALSE：失败
   //    */
   //   public void setAction(String sValue){
   //      m_sAction = sValue;
   //   }
   //
   //   public void setFirst(String sFirst){
   //      m_sFirst = sFirst;
   //   }
   //
   //   /**
   //    * <p>设置指定格式</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param sValue 指定格式
   //    */
   //   public void setFormat(String sValue){
   //      m_sFormat = sValue;
   //   }
   //
   //   /**
   //    * <p>设置指定内容</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param sValue 指定内容
   //    */
   //   public void setItem(String sValue){
   //      m_sItem = sValue;
   //   }
   //
   //   public void setLast(String sLast){
   //      m_sLast = sLast;
   //   }
   //
   //   /**
   //    * <p>设置控件名称</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param sValue 控件名称
   //    * @return TRUE：成功<br>FALSE：失败
   //    */
   //   public void setName(String sValue){
   //      m_sName = sValue;
   //   }
   //
   //   public void setNext(String sNext){
   //      m_sNext = sNext;
   //   }
   //
   //   public void setPrior(String sPrior){
   //      m_sPrior = sPrior;
   //   }
   //
   //   /**
   //    * <p>设置指定资源</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param sValue 指定资源
   //    */
   //   public void setSource(String sValue){
   //      m_sSource = sValue;
   //   }
}
