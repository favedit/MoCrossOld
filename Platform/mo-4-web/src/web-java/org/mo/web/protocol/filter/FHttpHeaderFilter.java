package org.mo.web.protocol.filter;

public class FHttpHeaderFilter
{

   //   public static boolean filterHeader(FHttpContext oHttpContext){
   //      return filterHeader(oHttpContext, -1);
   //   }
   //
   //   public static boolean filterHeader(FHttpContext oHttpContext,
   //                                      int nContentLength){
   //      // 获得输入信息
   //      FStringList oRequestHeaders = oHttpContext.getRequestHeaders();
   //      String sUserAgent = oRequestHeaders.get("user-agent");
   //      // 获得输出信息
   //      FStringList oHeaders = oHttpContext.getResponseHeaders();
   //      oHeaders.set("Pragma", "No-cache");
   //      oHeaders.set("Cache-Control", "No-cache");
   //      if(sUserAgent.startsWith("Mozilla") && sUserAgent.indexOf("Opera") == -1){
   //         oHeaders.set("Content-Type", IWebContentType.HTML);
   //      }else{
   //         oHeaders.set("Content-Type", IWebContentType.WML);
   //      }
   //      if(nContentLength > 0){
   //         oHeaders.set("Content-Length", Integer.toString(nContentLength));
   //      }
   //      return true;
   //   }
   //
   //   public static FStringList rebuildHeaders(List oHeaderFilter,
   //                                            FStringList oHeaders){
   //      FStringList oNewHeaders = new FStringList();
   //      FHttpHeader oHttpHeader = null;
   //      String sHeaderPrefix = null;
   //      String sHeaderName = null;
   //      String sHeaderValue = null;
   //      int nCount = oHeaderFilter.size();
   //      for(int n = 0; n < nCount; n++){
   //         oHttpHeader = (FHttpHeader) oHeaderFilter.get(n);
   //         sHeaderPrefix = oHttpHeader.getPrefix();
   //         if(!FStringUtil.isEmpty(sHeaderPrefix)){
   //            //oNewHeaders.append(oHeaders.getFilterByKey(sHeaderPrefix));
   //            continue;
   //         }
   //         sHeaderName = oHttpHeader.getName();
   //         sHeaderValue = oHttpHeader.getValue();
   //         if(FStringUtil.isEmpty(sHeaderValue)){
   //            sHeaderValue = oHeaders.get(sHeaderName);
   //         }
   //         if(FStringUtil.isEmpty(sHeaderValue)){
   //            sHeaderValue = oHttpHeader.getDefault();
   //         }
   //         if(FStringUtil.isEmpty(sHeaderValue)){
   //            continue;
   //         }
   //         oNewHeaders.add(sHeaderName, sHeaderValue);
   //
   //      }
   //      return oNewHeaders;
   //   }

}
