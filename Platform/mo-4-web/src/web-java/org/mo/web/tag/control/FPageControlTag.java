/*
 * @(#)FPageControlTag.java
 *
 * Copyright 2004 Java Frame Studio Corporation.
 * All Rights Reserved.
 *
 */

package org.mo.web.tag.control;

import org.mo.web.tag.common.FAbstractTag;

/**
 * <p></p>
 * <p>
 * <br>
 *
 * </p>
 *
 * @author FEDT
 * @version 1.00 - 2005/02/18
 */
public class FPageControlTag
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
   //   //指定页面控制信息
   //   private FWebTabControl m_oPageControl = null;
   //
   //   // 按键的命令
   //   private String m_sAction = null;
   //
   //   // 控件名称
   //   private String m_sName = null;
   //
   //   //指定表单单击的事件
   //   private String m_sOnSheetClick = null;
   //
   //   //指定首页
   //   private String m_sPageIndex = null;
   //
   //   //指定首页形式
   //   private String m_sPageIndexForm = null;
   //
   //   // 控件名称
   //   private String m_sSource = null;
   //
   //   // 目标页面框架
   //   private String m_sTarget = null;
   //
   //   /**
   //    * <p>结束标签的逻辑</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param oContext 环境对象
   //    * @return 逻辑执行后的状态
   //    * @exception FError 逻辑例外
   //    */
   //   public int doEndTag(FWebContext oContext)
   //         throws FError{
   //      FString sBuffer = oContext.responseBuffer();
   //      StringBuffer oScript = new StringBuffer();
   //      if (!RString.isEmpty(m_sPageIndexForm)) {
   //         String sFormName = oContext.source(m_sPageIndexForm);
   //         String sIndexName = oContext.sourceName(m_sPageIndex);
   //         sBuffer.append("<SCRIPT>\n" + "function _onTabSheetClick(oPage){\n"
   //               + "   " + sFormName + "." + sIndexName
   //               + ".value = oPage.name;\n" + "}\n" + "</SCRIPT>\n");
   //      }
   //      if (RString.equalsIgnoreCase(m_sAction, "init")) {
   //         String sPageIndex = oContext.source(m_sPageIndex);
   //         pageControl().setPageIndex(sPageIndex);
   //         if (!RString.isEmpty(m_sPageIndexForm)) {
   //            pageControl().setOnSheetClick("_onTabSheetClick()");
   //         } else {
   //            pageControl().setOnSheetClick(m_sOnSheetClick);
   //         }
   //         oScript.appendInt(pageControl().makeRefreshScript() + "\n");
   //         oScript.appendInt(pageControl().makeOnLoadScript() + "\n");
   //         String sScript = oScript.toString();
   //         sScript = RString.parse(sScript, "name", m_sName);
   //         sScript = RString.parse(sScript, "context_path", oContext
   //               .contextPath());
   //         FBodyTag.appendOnload(this, sScript);
   //         FBodyTag.appendBeforeOnunload(this, m_sName + ".release();\n"
   //               + m_sName + "=null;\n");
   //      }
   //      flush();
   //      return EVAL_PAGE;
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
   //      FString sBuffer = oContext.responseBuffer();
   //      String sSource = m_sSource;
   //      m_oPageControl = new FWebTabControl();
   //      m_oPageControl.setName(m_sName);
   //      m_oPageControl.setTargetForm(oContext.source(m_sTarget));
   //      // ------------------------------------------------------------
   //      if (!RString.isEmpty(sSource)) {
   //         //FXmlNode oPcNode = FWebControlManager.instance().page()
   //         //.findPageControl(sSource, "db.oracle");
   //         sBuffer.append("<DIV id='id_" + m_sName + "'></DIV>"
   //               + "<SCRIPT language='javascript'>" + "var " + m_sName + "=null;"
   //               + "</SCRIPT>\n");
   //         //FWebSheet oSheet = null;
   //         //         for (FXmlNode oSheetNode : oPcNode.nodes()) {
   //         //            oSheet = new FWebSheet(oSheetNode.attribute("name"), oSheetNode
   //         //                  .attribute("label"), oSheetNode.attribute("icon"), oSheetNode
   //         //                  .attribute("action"), oSheetNode.attribute("hint"));
   //         //            m_oPageControl.sheets().push(oSheet);
   //         //         }
   //      } else {
   //         if (RString.equalsIgnoreCase(m_sAction, "init")) {
   //            oContext.responseBuffer().appendInt(
   //                  "<DIV id='id_" + m_sName + "'></DIV>"
   //                        + "<SCRIPT language='javascript'>" + "var " + m_sName
   //                        + "=null;" + "</SCRIPT>\n");
   //            return EVAL_BODY_INCLUDE;
   //         }
   //      }
   //      return SKIP_BODY;
   //   }
   //
   //   /**
   //    * <p>获得页面控制信息</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @retrun 页面控制信息
   //    */
   //   public FWebTabControl pageControl(){
   //      return m_oPageControl;
   //   }
   //
   //   /**
   //    * <p>释放标签所使用的资源</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    */
   //   public void release(){
   //      super.release();
   //      m_sAction = null;
   //      m_sTarget = null;
   //      m_sOnSheetClick = null;
   //      m_sPageIndex = null;
   //      m_oPageControl = null;
   //   }
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
   //   /**
   //    * <p>设置表单单击的事件</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param sValue 表单单击的事件
   //    */
   //   public void setOnSheetClick(String sValue){
   //      m_sOnSheetClick = sValue;
   //   }
   //
   //   /**
   //    * <p>设置首页</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param sValue 首页
   //    */
   //   public void setPageIndex(String sValue){
   //      m_sPageIndex = sValue;
   //   }
   //
   //   /**
   //    * <p>获得首页形式</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @retrun 首页形式
   //    */
   //   public void setPageIndexForm(String sValue){
   //      m_sPageIndexForm = sValue;
   //   }
   //
   //   /**
   //    * <p>设置控件名称</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param sValue 控件名称
   //    */
   //   public void setSource(String sValue){
   //      m_sSource = sValue;
   //   }
   //
   //   /**
   //    * <p>设置目标页面框架</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param sValue 目标页面框架
   //    */
   //   public void setTarget(String sValue){
   //      m_sTarget = sValue;
   //   }
}
