/*
 * @(#)FDBHtmlItemTag.java
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
public class FDBHtmlItemTag
{

   public FDBHtmlItemTag(){
   }

}

//package org.mo.com.web.tag.db;
//
//import org.mo.com.app.msg.FMessageException;
//import org.mo.com.sql.FDBConnection;
//import org.mo.com.reflect.FClass;
//import org.mo.com.web.FWebContext;
//import org.mo.com.web.tag.html.FHtmlItemTag;
//import jfs.lang.FString;
//
//public class FDBHtmlItemTag extends FHtmlItemTag {
//
//   // ============================================================
//   private String m_sSqlProperty = null;
//
//   public String getSqlProperty() {
//      return FClass.invokeFieldValueAsString(m_sSqlProperty);
//   }
//
//   public void setSqlProperty(String value) {
//      m_sSqlProperty = value;
//   }
//
//   // ============================================================
//   private String m_sSqlResource = null;
//
//   public String getSqlResource() {
//      return FClass.invokeFieldValueAsString(m_sSqlResource);
//   }
//
//   public void setSqlResource(String value) {
//      m_sSqlResource = value;
//   }
//
//   public String getSqlCmd()
//         throws FMessageException {
//      String sProperty = getSqlProperty();
//      if (FString.isNotEmpty(sProperty)) {
//         return getProperty(sProperty);
//      }
//      String sResource = getSqlResource();
//      if (FString.isNotEmpty(sResource)) {
//         return getResource(sResource);
//      }
//      return null;
//   }
//
//   private FDBConnection m_oActiveConnection = null;
//
//   public FDBConnection getActiveConnection()
//         throws FMessageException {
//      if(m_oActiveConnection == null) {
//         FWApplication oWebApplication = getLinkApplication();
//         m_oActiveConnection = oWebApplication.
//               getLinkConnectionConsole().getActiveConnection(oWebApplication);
//      }
//      return m_oActiveConnection;
//   }
//
//   /**
//    * <p>标签处理开始</p>
//    * <p>create date:2005/02/18</p>
//    *
//    * @return 处理状态
//
//
//    */
//   public int doStartTag(FWebContext oContext)
//         throws FMessageException {
//      FString oHtml = new FString();
//      int nResult = super.doStartTag(oContext);
//      if(nResult == SKIP_BODY) {
//         if(m_oActiveConnection != null) {
//            m_oActiveConnection.close();
//            m_oActiveConnection = null;
//         }
//      }
//      oContext.write(oHtml.asString());
//      return nResult;
//   }
//
//   /**
//    * <p>标签处理结束</p>
//    * <p>create date:2005/02/18</p>
//    *
//    * @return 处理状态
//
//    */
//   public int doEndTag(FWebContext oContext)
//         throws FMessageException {
//      FString oHtml = new FString();
//      int nResult = super.doEndTag(oContext);
//      oContext.write(oHtml.asString());
//      if(m_oActiveConnection != null) {
//         m_oActiveConnection.close();
//         m_oActiveConnection = null;
//      }
//      return nResult;
//   }
//}
