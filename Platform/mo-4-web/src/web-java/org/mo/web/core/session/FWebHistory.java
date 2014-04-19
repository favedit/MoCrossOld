/*
 * @(#)FWebHistory.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.web.core.session;

import java.util.Iterator;
import java.util.Stack;
import org.mo.com.lang.FError;
import org.mo.com.lang.FString;
import org.mo.com.lang.RString;
import org.mo.web.protocol.context.FWebContext;

/**
 * <T>访问历史控制台。</T>
 *
 * @author maocy
 * @version 1.00 - 2008/11/18
 */
public class FWebHistory
{

   public static String REF_HISTORY_CURRENT = "&history.current";

   public static String REF_HISTORY_PRIOR = "&history.prior";

   @SuppressWarnings("rawtypes")
   private final Stack m_oURIStack = new Stack();

   /**
    * <T>确定当前对象是否可以进行序列化</T>
    *
    * @return TRUE：是<B/>FALSE：否
    */
   public boolean canSerialize(){
      return true;
   }

   public boolean clear(){
      m_oURIStack.clear();
      return true;
   }

   @SuppressWarnings("rawtypes")
   public FString dump(){
      FString sDump = new FString();
      Iterator oIterator = m_oURIStack.iterator();
      //sDump.append(RClass.dumpClass(this));
      sDump.append(" [\n");
      while(oIterator.hasNext()){
         sDump.append(oIterator.next() + "\n");
      }
      sDump.append("]");
      return sDump;
   }

   public String getCurrent(FWebContext oContext){
      String sPrior = null;
      if(m_oURIStack.size() > 0){
         sPrior = (String)m_oURIStack.get(m_oURIStack.size() - 1);
      }
      if(oContext.contextPath().equalsIgnoreCase(sPrior)){
         return null;
      }
      return sPrior;
   }

   public String getPrior(){
      String sURI = "";
      if(m_oURIStack.size() >= 2){
         sURI = (String)m_oURIStack.get(m_oURIStack.size() - 2);
      }
      return sURI;
   }

   public String getPrior(FWebContext oContext){
      String sContextPath = oContext.contextPath();
      String sPrior = getPrior();
      if(!RString.isEmpty(sPrior)){
         if(sPrior.startsWith(sContextPath)){
            sPrior = sContextPath + sPrior;
         }
         if(sContextPath.equalsIgnoreCase(sPrior)){
            return null;
         }
      }
      return sPrior;
   }

   public String getPrior(int nLevel){
      String sURI = null;
      if(m_oURIStack.size() >= (nLevel + 1)){
         sURI = (String)m_oURIStack.get(m_oURIStack.size() - nLevel - 1);
      }
      return sURI;
   }

   public String parseURI(String sURI,
                          String sValue) throws FError{
      if(!RString.isEmpty(sValue)){
         int nFind = 0;
         int nFindEnd = 0;
         String[] arParam = null;
         String[] arParams = sValue.split("&");
         for(int n = 0; n < arParams.length; n++){
            arParam = arParams[n].split("\\=");
            if(arParam.length == 2){
               nFind = sURI.indexOf(arParam[0] + "=");
               if(nFind != -1){
                  nFindEnd = sURI.indexOf("&", nFind);
                  if(nFindEnd == -1){
                     nFindEnd = sURI.length();
                  }
                  if(sURI.substring(nFind - 1, nFind).equals("&")){
                     sURI = sURI.substring(0, nFind) + arParam[0] + "=" + arParam[1] + sURI.substring(nFindEnd);
                  }else{
                     sURI = sURI.substring(0, nFind) + "&" + arParam[0] + "=" + arParam[1] + sURI.substring(nFindEnd);
                  }
               }
            }
         }
      }
      return sURI;
   }

   public String pop(){
      if(!m_oURIStack.empty()){
         return (String)m_oURIStack.pop();
      }
      return null;
   }

   public boolean push(String sURI){
      sURI = sURI.replaceAll("/inc/Control.action", "/inc/ControlBack.action");
      if(m_oURIStack.indexOf(sURI) == -1){
         //m_oURIStack.push(sURI);
      }
      return true;
   }
}
