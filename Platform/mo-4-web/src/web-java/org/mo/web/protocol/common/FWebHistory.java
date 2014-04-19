package org.mo.web.protocol.common;

import java.io.Serializable;
import org.mo.web.core.session.IWebSession;

public class FWebHistory
      implements
         Serializable
{

   // 序列化标志
   private static final long serialVersionUID = 1L;

   //   public static String DEFINE_HISTORY = "history";
   //
   //   private FStringStack m_oUris = new FStringStack();

   @SuppressWarnings("unused")
   private IWebSession m_iSession = null;

   public FWebHistory(){
   }

   public FWebHistory(IWebSession iSession){
      m_iSession = iSession;
   }

   //
   //   public void push(IWebContext iContext){
   //      String sQuery = iContext.queryString();
   //      if(!FStringUtil.isEmpty(sQuery)){
   //         push(iContext.requestUri() + "?" + sQuery);
   //      }else{
   //         push(iContext.requestUri());
   //      }
   //   }
   //
   //   public void push(String sUri){
   //      if(!m_oUris.contains(sUri)){
   //         m_oUris.push(sUri);
   //      }
   //   }
   //
   //   public String pop(){
   //      return m_oUris.pop();
   //   }
   //
   //   public void clear(){
   //      m_oUris.clear();
   //   }
   //
   //   public String prior(){
   //      if(m_oUris.size() >= 2){
   //         return m_oUris.value(m_oUris.size() - 2);
   //      }
   //      return FStringUtil.EMPTY;
   //   }
   //
   //   public String prior(int nLevel){
   //      if(m_oUris.size() >= (nLevel + 1)){
   //         return m_oUris.value(m_oUris.size() - nLevel - 1);
   //      }
   //      return FStringUtil.EMPTY;
   //   }
   //
   //   public String prior(IWebContext iContext){
   //      String sContextPath = iContext.contextPath();
   //      String sPrior = prior();
   //      if(!FStringUtil.isEmpty(sPrior)){
   //         if(sPrior.startsWith(sContextPath)){
   //            sPrior = sContextPath + sPrior;
   //         }
   //         if(sContextPath.equalsIgnoreCase(sPrior)){
   //            return null;
   //         }
   //      }
   //      return sPrior;
   //   }
   //
   //   public String current(IWebContext iContext){
   //      String sPrior = null;
   //      if(m_oUris.size() > 0){
   //         sPrior = (String) m_oUris.value(m_oUris.size() - 1);
   //      }
   //      if(iContext.contextPath().equalsIgnoreCase(sPrior)){
   //         return null;
   //      }
   //      return sPrior;
   //   }
   //
   //   public String parse(String sURI,
   //                       String sValue)
   //         throws FException{
   //      if(!FStringUtil.isEmpty(sValue)){
   //         int nFind = 0;
   //         int nFindEnd = 0;
   //         String[] arParam = null;
   //         String[] arParams = sValue.split("&");
   //         for(int n = 0; n < arParams.length; n++){
   //            arParam = arParams[n].split("\\=");
   //            if(arParam.length == 2){
   //               nFind = sURI.indexOf(arParam[0] + "=");
   //               if(nFind != -1){
   //                  nFindEnd = sURI.indexOf("&", nFind);
   //                  if(nFindEnd == -1){
   //                     nFindEnd = sURI.length();
   //                  }
   //                  if(sURI.substring(nFind - 1, nFind).equals("&")){
   //                     sURI = sURI.substring(0, nFind) + arParam[0] + "=" + arParam[1]
   //                           + sURI.substring(nFindEnd);
   //                  }else{
   //                     sURI = sURI.substring(0, nFind) + "&" + arParam[0] + "=" + arParam[1]
   //                           + sURI.substring(nFindEnd);
   //                  }
   //               }
   //            }
   //         }
   //      }
   //      return sURI;
   //   }
   //
   //   public FString dump(){
   //      FString sDump = new FString();
   //      sDump.append(FClassUtil.dump(this));
   //      sDump.append(" [\n");
   //      for(String sUri : m_oUris){
   //         sDump.append(sUri);
   //         sDump.append("\n");
   //      }
   //      sDump.append("]");
   //      return sDump;
   //   }
   //
   //   public FStringList valueRef(){
   //      FStringList oValues = new FStringList();
   //      oValues.setValue("prior", prior());
   //      oValues.setValue("prior2", prior(2));
   //      oValues.setValue("prior3", prior(3));
   //      return oValues;
   //   }
}
