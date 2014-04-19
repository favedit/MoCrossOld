/*
 * @(#)FDBTableTag.java
 *
 * Copyright 2004 Java Frame Studio Corporation.
 * All Rights Reserved.
 *
 */

package org.mo.web.tag.control;

import org.mo.web.tag.common.FAbstractTag;

/**
 * <p>表操作标签</p>
 * <p>1. 根据一个表的定制信息，修改表的内容</p>
 *
 * @author FEDT
 */
public class FDBTableTag
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
   //   /**
   //    * <p>开始标签的逻辑</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param oContext 环境对象
   //    * @return 逻辑执行后的状态
   //    * @exception FError 逻辑例外
   //    */
   //   public int doStartTag(FWebContext oContext) throws FError {
   //      //      FLDataSet oDataSet = (FLDataSet)getLinkItem();
   //      //      if(oDataSet != null) {
   //      //         oContext.write("<SPAN id='data_table'></SPAN>\n");
   //      //         oContext.write(
   //      //               "<SCRIPT language='javascript'>\n" +
   //      //               "var " + getName() + " = obj.create('LH.Table');\n" +
   //      //               getName() + ".linkHtmlID = 'data_table';\n" +
   //      //               "var oCols = " + getName() + ".columns;\n");
   //      //         FDBField oDBField = null;
   //      //         FDBFields oDBFields = oDataSet.getDataSetMeta().getFields();
   //      //         int nFieldCount = oDBFields.getCount();
   //      //         for(int nField = 0; nField < nFieldCount; nField++) {
   //      //            oDBField = oDBFields.getField(nField);
   //      //            oContext.write(
   //      //                  "var oCol = oCols.createColumn('LH.Table.Column.Text');" +
   //      //                  "oCol.name='" + oDBField.getName() + "';" +
   //      //                  "oCol.displayLabel='" + oDBField.getName() + "';" +
   //      //                  "oCol.dataSize=" + oDBField.getDataSize() + ";");
   //      //            if(oDBField.getName().equalsIgnoreCase("row_id") ||
   //      //               oDBField.getName().equalsIgnoreCase("row_num")) {
   //      //               oContext.write("oCol.isKey=true;oCol.isVisible=false;");
   //      //            }
   //      //            oContext.write("\n");
   //      //         }
   //      //         oContext.write(getName() + ".init();\n");
   //      //         int nCount = oDataSet.getCount();
   //      //         FLUnit oUnit = null;
   //      //         FStringList oUnitPack = new FStringList();
   //      //         for(int m = 0; m < nCount; m++) {
   //      //            oUnit = oDataSet.getUnit(m);
   //      //            oUnitPack.clear();
   //      //            for(int n = 0; n < oUnit.getCount(); n++) {
   //      //               oUnitPack.add(oUnit.getItemName(n), oUnit.getItemAsString(n));
   //      //            }
   //      //            oContext.write(getName() + ".unpackUnit('" + oUnitPack.pack() +
   //      //                            "');\n");
   //      //         }
   //      //         oContext.write("</SCRIPT>\n");
   //      //      }
   //      return SKIP_BODY;
   //   }
}
