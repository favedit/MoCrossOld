/*
 * @(#)FDBComboBoxTag.java
 *
 * Copyright 2003 Java Frame Studio Corporation.
 * All Rights Reserved.
 *
 */

package org.mo.web.tag.db;

/**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
public class FDBComboBoxTag
{

   public FDBComboBoxTag(){
   }
}

//package org.mo.com.web.tag.db;
//
//import java.util.Map;
//import org.mo.com.app.msg.FMessageException;
//import org.mo.com.sql.FDBConnection;
//import org.mo.com.sql.FDBDataSet;
//import org.mo.com.sql.FDBRow;
//import org.mo.com.reflect.FClass;
//import jfs.lang.FString;
//import org.mo.com.web.FWebContext;
//
//public class FDBComboBoxTag extends FDBHtmlItemTag {
//
//   // ============================================================
//   private String m_sFirstLine = null;
//
//   public String getFirstLine() {
//      return m_sFirstLine;
//   }
//
//   public void setFirstLine(String value) {
//      m_sFirstLine = value;
//   }
//
//   // ============================================================
//   private String m_sKeyItem = null;
//
//   public String getKeyItem() {
//      return FClass.invokeFieldValueAsString(m_sKeyItem);
//   }
//
//   public void setKeyItem(String value) {
//      m_sKeyItem = value;
//   }
//
//   // ============================================================
//   private String m_sValueItem = null;
//
//   public String getValueItem() {
//      return FClass.invokeFieldValueAsString(m_sValueItem);
//   }
//
//   public void setValueItem(String value) {
//      m_sValueItem = value;
//   }
//
//   // ============================================================
//   public int doStartTag(FWebContext oContext)
//         throws FMessageException {
//      String sSqlCmd = getSqlCmd();
//      if (FString.isNotEmpty(sSqlCmd)) {
//         String sValue = getLinkItemAsString();
//         // ------------------------------------------------------------
//         String sMapKey = sSqlCmd.toUpperCase();
//         Map oBufferMap = getBufferMap();
//         String sKeyItem = getKeyItem();
//         String sValueItem = getValueItem();
//         FDBRow oRow = null;
//         FDBDataSet oDataSet = null;
//         if (oBufferMap.containsKey(sMapKey)) {
//            oDataSet = (FDBDataSet) oBufferMap.get(sMapKey);
//         } else {
//            FDBConnection oDBConnection = getActiveConnection();
//            oDataSet = oDBConnection.fetchDataSet(sSqlCmd);
//         }
//         // ------------------------------------------------------------
//         oContext.write("<SELECT size=\"1\" name=\"" + getLinkItemName() +
//                         "\"");
//         appendHtml(oContext);
//         oContext.write(">");
//         int nCount = oDataSet.getCount();
//         String sRowKey = null;
//         String sRowValue = null;
//         for (int n = 0; n < nCount; n++) {
//            oRow = oDataSet.getRow(n);
//            sRowKey = FString.isNotEmpty(sKeyItem) ?
//                  oRow.getItemAsString(sKeyItem) : oRow.getItemAsString(0);
//            sRowValue = FString.isNotEmpty(sValueItem) ?
//                  oRow.getItemAsString(sValueItem) : oRow.getItemAsString(1);
//            oContext.write("<OPTION");
//            if (sRowKey.equals(sValue)) {
//               oContext.write(" selected");
//            }
//            oContext.write(" value='" + sRowKey + "'>");
//            oContext.write(sRowValue);
//            oContext.write("</OPTION>");
//         }
//         oContext.write("</SELECT>");
//      }
//      return SKIP_BODY;
//   }
//}
