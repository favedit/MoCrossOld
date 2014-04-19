/*
 * @(#)FEditorTag.java
 *
 * Copyright 2005 microbject, All Rights Reserved.
 *
 */
package org.mo.web.tag.control;

import org.mo.web.tag.common.FAbstractTag;

/**
 * <p>通过XML节点配置的编辑器</p>
 *
 * @author ALEX
 */
public class FEditorTag
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
   //   // 内容项目
   //   private String m_sItem = null;
   //
   //   // 值项目
   //   private String m_sValue = null;
   //
   //   /**
   //    * <p>开始标签的逻辑</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param oContext 环境对象
   //    * @return 逻辑执行后的状态
   //    * @exception FError 逻辑例外
   //    */
   //   @SuppressWarnings("unchecked")
   //   public int doStartTag(FWebContext oContext)
   //         throws FError{
   //      // 获得参数
   //      Object oItem = oContext.sourceObject(m_sItem);
   //      INamingStringValue iParams = null;
   //      if (oItem instanceof IValueAble) {
   //         iParams = (INamingStringValue) ((IValueAble) oItem).valueRef();
   //      }
   //      if (iParams == null) {
   //         FString sMsg = new FString();
   //         sMsg.append("Item is not IValueAble interface. [");
   //         sMsg.append(oItem.getClass());
   //         sMsg.append("]");
   //         throw new FFatalError(this, "doStartTag", sMsg);
   //      }
   //      Object oValue = oContext.sourceObject(m_sValue);
   //      INamingStringValue iValue = null;
   //      if (oItem instanceof IValueAble) {
   //         iValue = (INamingStringValue) ((IValueAble) oValue).valueRef();
   //      }
   //      if (iValue == null) {
   //         FString sMsg = new FString();
   //         sMsg.append("Value is not IValueAble interface. [");
   //         sMsg.append(oItem.getClass());
   //         sMsg.append("]");
   //         throw new FFatalError(this, "doStartTag", sMsg);
   //      }
   //      // 生成内容
   //      FString sBuffer = oContext.responseBuffer();
   //      String sName = iParams.get("name");
   //      String sObjectName = oContext.sourceObjectName(iValue, sName);
   //      String sValue = iValue.get(sName);
   //      String sDataSize = iParams.get("data_size");
   //      String sDataDefault = iParams.get("data_default");
   //      if (RString.isEmpty(sValue)) {
   //         sValue = sDataDefault;
   //      }
   //      String sEditType = RString.nvl(iParams.get("edit_type"));
   //      if (sEditType.equalsIgnoreCase("label")) {
   //         // Label
   //         sBuffer.append(sValue);
   //         sBuffer.append("<INPUT type='hidden' name='");
   //         sBuffer.append(sObjectName);
   //         sBuffer.append("' value='");
   //         sBuffer.append(sValue);
   //         sBuffer.append("'>");
   //      } else if (sEditType.equalsIgnoreCase("check")) {
   //         // Check box
   //         String sEditTrue = RString.nvl(iParams.get("edit_true"),
   //               FBooleanUtil.RBoolean);
   //         String sEditFalse = RString.nvl(iParams.get("edit_false"),
   //               FBooleanUtil.RBoolean);
   //         boolean bResult = RString.equalsIgnoreCase(sValue, sEditTrue);
   //         // 建立内容
   //         sBuffer.append("<INPUT type=\"hidden\" name=\"");
   //         sBuffer.append(sObjectName);
   //         sBuffer.append("\" value=\"");
   //         if (bResult) {
   //            sBuffer.append(sEditTrue);
   //         } else {
   //            sBuffer.append(sEditFalse);
   //         }
   //         sBuffer.append("\">");
   //         sBuffer.append("<INPUT type=\"checkbox\"");
   //         sBuffer.append(" class='comCheckBox'");
   //         sBuffer.append(" onclick='IHTML.chkSync(this,");
   //         sBuffer.append(sObjectName);
   //         sBuffer.append(",\"");
   //         sBuffer.append(sEditTrue);
   //         sBuffer.append("\",\"");
   //         sBuffer.append(sEditFalse);
   //         sBuffer.append("\")'");
   //         if (bResult) {
   //            sBuffer.append(" checked");
   //         }
   //         sBuffer.append(">");
   //      } else if (sEditType.equalsIgnoreCase("number")) {
   //         // Edit
   //         String sEditSize = iParams.get("edit_size");
   //         sBuffer.append("<INPUT class='comNumber' name='");
   //         sBuffer.append(sObjectName);
   //         sBuffer.append("' size='");
   //         sBuffer.append(sEditSize);
   //         sBuffer.append("' maxlength='");
   //         sBuffer.append(sDataSize);
   //         sBuffer.append("' value='");
   //         sBuffer.append(sValue);
   //         sBuffer.append("'>");
   //      } else if (sEditType.equalsIgnoreCase("edit")) {
   //         // Edit
   //         String sEditSize = iParams.get("edit_size");
   //         sBuffer.append("<INPUT name='");
   //         sBuffer.append(sObjectName);
   //         sBuffer.append("' size='");
   //         sBuffer.append(sEditSize);
   //         sBuffer.append("' maxlength='");
   //         sBuffer.append(sDataSize);
   //         sBuffer.append("' value='");
   //         sBuffer.append(sValue);
   //         sBuffer.append("'>");
   //      } else if (sEditType.equalsIgnoreCase("memo")) {
   //         // Memo
   //         String sEditRows = iParams.get("edit_rows");
   //         String sEditCols = iParams.get("edit_cols");
   //         sBuffer.append("<TEXTAREA name='");
   //         sBuffer.append(sObjectName);
   //         sBuffer.append("' rows='");
   //         sBuffer.append(sEditRows);
   //         sBuffer.append("' cols='");
   //         sBuffer.append(sEditCols);
   //         sBuffer.append("'>");
   //         sBuffer.append(sValue);
   //         sBuffer.append("</TEXTAREA>");
   //      } else if (sEditType.equalsIgnoreCase("combo")) {
   //         // ComboBox
   //         String sEditSource = iParams.get("edit_source");
   //         String sEditEmpty = iParams.get("edit_empty");
   //         FStringList oList = FListManager.console().findListString(sEditSource);
   //         sBuffer.append("<SELECT size='1' class='comComboBox' name='");
   //         sBuffer.append(sObjectName);
   //         sBuffer.append("'>");
   //         if (RBoolean.parse(sEditEmpty)) {
   //            sBuffer.append("<OPTION></OPTION>");
   //         }
   //         int nCount = oList.size();
   //         for (int n = 0; n < nCount; n++) {
   //            sBuffer.append("<OPTION");
   //            if (sValue != null) {
   //               if (oList.name(n).trim().equalsIgnoreCase(sValue.trim())) {
   //                  sBuffer.append(" selected");
   //               }
   //            }
   //            sBuffer.append(" value='");
   //            sBuffer.appendInt(oList.name(n));
   //            sBuffer.append("'>");
   //            sBuffer.appendInt(oList.get(n));
   //            sBuffer.append("</OPTION>");
   //         }
   //         sBuffer.append("</SELECT>");
   //      }
   //      flush();
   //      return SKIP_BODY;
   //   }
   //
   //   /**
   //    * <p>设置内容项目</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param sValue 内容项目
   //    */
   //   public void setItem(String sValue){
   //      m_sItem = sValue;
   //   }
   //
   //   /**
   //    * <p>设置内容项目</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param sValue 内容项目
   //    */
   //   public void setValue(String sValue){
   //      m_sValue = sValue;
   //   }
}
