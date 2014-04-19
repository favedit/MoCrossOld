package org.mo.web.protocol.common;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import org.mo.com.lang.FAttributes;

//============================================================
// <T>网络请求工具类。</T>
//============================================================
public class RWebRequest
{

   //============================================================
   // <T>构造头信息集合。</T>
   //
   // @param 网络请求
   // @return 头信息集合
   //============================================================
   public static FAttributes makeHeads(HttpServletRequest request){
      FAttributes heads = new FAttributes();
      Enumeration<?> enumNames = request.getHeaderNames();
      while(enumNames.hasMoreElements()){
         String name = (String)enumNames.nextElement();
         heads.set(name, request.getHeader(name));
      }
      return heads;
   }

   //============================================================
   // <T>构造参数集合。</T>
   //
   // @param 网络请求
   // @return 参数集合
   //============================================================
   public static FAttributes makeParameters(HttpServletRequest request){
      FAttributes params = new FAttributes();
      Enumeration<?> enumParams = request.getParameterNames();
      while(enumParams.hasMoreElements()){
         String name = (String)enumParams.nextElement();
         params.set(name, request.getParameter(name));
      }
      return params;
   }
}
