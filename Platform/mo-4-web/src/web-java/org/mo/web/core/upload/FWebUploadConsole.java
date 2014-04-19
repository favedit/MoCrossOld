/*
 * @(#)FWebUploadConsole.java
 *
 * Copyright 2005 microbject, All Rights Reserved.
 *
 */
package org.mo.web.core.upload;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import org.mo.com.lang.IAttributes;
import org.mo.core.aop.face.AProperty;
import org.mo.web.protocol.common.FWebUpdateParser;
import org.mo.web.protocol.common.FWebUploadFiles;

/**
 * <T>文件上传用的解析控制台。</T>
 * 
 * @author ALEX
 * @version 1.00 - 2008/11/25
 */
public class FWebUploadConsole
      implements
         IWebUploadConsole
{

   // 上传临时路径
   @AProperty
   private String _directory;

   // 上传字符编码
   @AProperty
   private String _encoding;

   /**
    * <p>创建对象的实例</p>
    */
   public FWebUploadConsole(){
   }

   /**
    * <p>获得上传临时路径</p>
    *
    * @return 上传临时路径
    */
   public String directory(){
      return _directory;
   }

   /**
    * <p>判断是否是上传模式</p>
    *
    * @param request WEB输入对象
    * @return TRUE：是<br>FALSE：否
    */
   public boolean isMultipart(HttpServletRequest request){
      return FWebUpdateParser.isMultipart(request);
   }

   /**
    * <p>分解传入的信息</p>
    *
    * @param request WEB输入对象
    * @param parameters 参数列表
    * @param files 文件列表
    */
   @SuppressWarnings("rawtypes")
   @Override
   public void parse(HttpServletRequest request,
                     IAttributes parameters,
                     FWebUploadFiles files){
      // 设置文件解析器，解析上传文件
      FWebUpdateParser updateParser = new FWebUpdateParser();
      updateParser.setCharacterEncoding(_encoding);
      updateParser.setUploadTempDir(_directory);
      updateParser.parse(request);
      String name = null;
      Enumeration names = request.getParameterNames();
      while(names.hasMoreElements()){
         name = (String)names.nextElement();
         parameters.set(name, request.getParameter(name));
      }
      // 接收上传参数
      parameters.append(updateParser.parameters());
      // 接收上传文件
      files.append(updateParser.files());
   }

}
