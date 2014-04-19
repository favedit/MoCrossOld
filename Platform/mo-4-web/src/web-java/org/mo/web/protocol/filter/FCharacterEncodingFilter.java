/*
 * @(#)FHttpContextListener.java
 *
 * Copyright 2005 microbject, All Rights Reserved.
 *
 */
package org.mo.web.protocol.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class FCharacterEncodingFilter
      implements
         Filter
{

   /**
    * 客户端的编码类型。
    * 默认为 UTF-8
    */
   String _encode = "UTF-8";

   public void destroy(){
   }

   public void doFilter(ServletRequest request,
                        ServletResponse response,
                        FilterChain chain) throws IOException,
                                          ServletException{
      //      if(request instanceof HttpServletRequest){
      //         HttpServletRequest httpRequest = (HttpServletRequest) request;
      //         httpRequest.setCharacterEncoding(_encode);
      //         ((HttpServletResponse) response).setCharacterEncoding(_encode);
      //         if(httpRequest.getMethod().equalsIgnoreCase("get")){
      //            encoding(httpRequest);
      //         }
      //      }
      chain.doFilter(request, response);
   }

   void encoding(HttpServletRequest request){
      Enumeration<?> names = request.getParameterNames();
      while(names.hasMoreElements()){
         String[] values = request.getParameterValues(names.nextElement().toString());
         for(int i = 0; i < values.length; ++i){
            try{
               values[i] = new String(values[i].getBytes("ISO8859-1"), _encode);
            }catch(UnsupportedEncodingException e){

               e.printStackTrace();
            }
         }
      }
   }

   public void init(FilterConfig filterConfig) throws ServletException{
      String encode = filterConfig.getInitParameter("encode");
      if(encode != null){
         _encode = encode;
      }
   }

}
