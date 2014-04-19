package org.mo.jfa.core.database;

public class FSourceServlet
{
   //   public void process(IWebContext iContext,
   //                       IServlet piServlet,
   //                       HttpServletRequest oRequest,
   //                       HttpServletResponse oResponse)
   //         throws Exception{
   //      String sType = FStringUtil.toNvlLowerCase(iContext.parameter("type"));
   //      String sName = iContext.parameter("name");
   //      String sTableName = sName + "_DS";
   //      // ------------------------------------------------------------
   //      FString sSqlCmd = null;
   //      String sDownloadName = null;
   //      if (sType.equals("package.head")) {
   //         FWfcSqlSource oSourceUtil = new FWfcSqlSource(iContext, sTableName);
   //         sSqlCmd = oSourceUtil.makePackageHeadSource();
   //         sDownloadName = sName.toLowerCase() + ".pkh";
   //      } else if (sType.equals("package.body")) {
   //         FWfcSqlSource oSourceUtil = new FWfcSqlSource(iContext, sTableName);
   //         sSqlCmd = oSourceUtil.makePackageHeadSource();
   //         sDownloadName = sName.toLowerCase() + ".pky";
   //      }
   //      byte[] arBuffer = sSqlCmd.toString().getBytes();
   //      // ------------------------------------------------------------
   //      // 设置输出信息
   //      //oResponse.setContentType(IWebContentType.STREAM);
   //      oResponse.setContentType("text/plain");
   //      oResponse.setContentLength(arBuffer.length);
   //      oResponse.setDateHeader(IWebHeaderType.EXPIRES, 1);
   //      oResponse.setHeader(IWebHeaderType.PRAGMA, IWebHeaderType.NO_CACHE);
   //      oResponse
   //            .setHeader(IWebHeaderType.CACHE_CONTROL, IWebHeaderType.NO_CACHE);
   //      oResponse.setHeader("Content-disposition", "attachment; filename="
   //            + sDownloadName);
   //      DataOutput oDataOutput = new DataOutputStream(oResponse.getOutputStream());
   //      for (int n = 0; n < arBuffer.length; n++) {
   //         oDataOutput.writeByte(arBuffer[n]);
   //      }
   //      oResponse.flushBuffer();
   //   }

}
