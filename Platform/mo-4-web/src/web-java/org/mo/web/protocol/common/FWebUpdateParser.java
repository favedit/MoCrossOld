/*
 * @(#)FWebUpdateParser.java
 *
 * Copyright 2005 microbject, All Rights Reserved.
 *
 */
package org.mo.web.protocol.common;

import java.io.File;
import java.io.FileOutputStream;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import org.mo.com.io.RFile;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RInteger;
import org.mo.com.lang.RString;
import org.mo.com.lang.RUuid;

/**
 * <T>文件上载信息分解工具类。</T>
 * 
 * @author maocy
 * @version 1.00 - 2008/11/25
 */
public class FWebUpdateParser
{

   /**
    * <T>文件上传标志</T>
    */
   public final static String MULTIPART_FORM_DATA = "multipart/form-data";

   // 最大行缓冲
   private static final int MAX_LINE_SIZE = RInteger.SIZE_4K;

   // 名称常量
   private final static String CONTENT_DISPOSITION_NAME = "name=\"";

   // 文件名称常量
   private final static String CONTENT_DISPOSITION_FILE_NAME = "filename=\"";

   // 上传类型常量
   private final static String CONTENT_DISPOSITION = "Content-Disposition: form-data;";

   // 行分隔
   private final static String LINE_SPLITTER = "\r\n";

   // 行分隔长度
   private final static int LINE_SPLITTER_LENGTH = LINE_SPLITTER.length();

   // 类型常来能够
   private final static String CONTENT_TYPE = "Content-Type: ";

   /**
    * <p>判断是否是上传模式</p>
    *
    * @param request WEB输入对象
    * @return TRUE：是<br>FALSE：否
    */
   public static boolean isMultipart(HttpServletRequest request){
      if(request == null){
         return false;
      }
      String contentType = request.getContentType();
      return (contentType != null) ? contentType.indexOf(MULTIPART_FORM_DATA) != -1 : false;
   }

   // 参数列表
   private FAttributes _attributes = new FAttributes();

   // 文件列表
   private FWebUploadFiles _files;

   // 临时路径
   private String _uploadTempDir = "/UploadTmp";

   // Servelt读入流
   private ServletInputStream _servletInputStream;

   // 行字符编码
   private String _characterEncoding;

   // 读取的行
   private String _readLine;

   // 读取的下一行
   private String _nextReadLine;

   // 是否有下一样
   private boolean _hasReadNext = false;

   // 读取字节数
   private int _readCount = -1;

   // 读取下一行字节数
   private int _nextReadCount = -1;

   // 读取行的字节缓冲
   private byte[] _readLineBytes = new byte[MAX_LINE_SIZE];

   // 读取下一行的字节缓冲
   private byte[] _nextReadLineBytes = new byte[MAX_LINE_SIZE];

   /**
    * <p>创建对象的实例</p>
    */
   public FWebUpdateParser(){
   }

   /**
    * <p>获得字符编码</p>
    *
    * @return 字符编码
    */
   public String characterEncoding(){
      return _characterEncoding;
   }

   /**
    * <p>获得文件列表</p>
    *
    * @return 文件列表
    */
   public FWebUploadFiles files(){
      if(null == _files){
         _files = new FWebUploadFiles();
      }
      return _files;
   }

   /**
    * <T>获得是否含有文件。</T>
    *
    * @return 是否含有
    */
   public boolean hasFile(){
      return (null != _files) ? !_files.isEmpty() : false;
   }

   /**
    * <p>获得参数列表</p>
    *
    * @return 参数列表
    */
   public FAttributes parameters(){
      return _attributes;
   }

   /**
    * <T>分解传入的信息。</T>
    * <p>将信息分解到参数中，将文件放到指定的临时目录中<br>
    * --- name = 参数名称 <br>
    * --- file_name = 文件名称 <br>
    * --- upload_file_name = 文件缓存地址<br>
    * --- file_size = 文件大小 <br>
    * --- file_content_type = 文件类型</p>
    *
    * @return TRUE：成功<br>FALSE：失败
    */
   public boolean parse(HttpServletRequest request){
      try{
         // 判断数据类型
         String contentType = request.getContentType();
         if(contentType == null || contentType.indexOf(MULTIPART_FORM_DATA) == -1){
            return false;
         }
         // 获得文件缓冲目录
         File tempDir = new File(uploadTempDir());
         if(!tempDir.isDirectory()){
            tempDir.mkdirs();
         }
         // 初始化变量
         String boundary = null;
         int boundaryLength = -1;
         _servletInputStream = request.getInputStream();
         String fileContentType = null;
         String content = null;
         String[] contents = null;
         String name = null;
         String fileName = null;
         File uploadFile = null;
         FileOutputStream fileOutputStream = null;
         boolean isProperty = false;
         boolean isFile = false;
         StringBuffer valueBuffer = new StringBuffer();
         // 读取第一行，作为分隔符
         if(!readLine()){
            return false;
         }
         boundary = _readLine;
         String lastBoundary = _readLine.substring(0, _readLine.length() - LINE_SPLITTER_LENGTH) + "--" + LINE_SPLITTER;
         boundaryLength = boundary.length();
         while(readLine()){
            if(_readLine.startsWith(CONTENT_DISPOSITION)){
               // 分解区分标志
               contents = _readLine.split(";");
               if(2 == contents.length){
                  // 如果读取的内容为Form数据
                  isProperty = false;
                  content = contents[1];
                  if(content.indexOf(CONTENT_DISPOSITION_NAME) != -1){
                     name = content.substring(content.indexOf(CONTENT_DISPOSITION_NAME) + CONTENT_DISPOSITION_NAME.length(), content.lastIndexOf("\""));
                     isProperty = true;
                  }
                  if(isProperty){
                     // 跳过空行
                     if(!readLine()){
                        return false;
                     }
                     valueBuffer.setLength(0);
                     while(readLine()){
                        // 如果为分隔符，认为读取完毕
                        if(_readCount == boundaryLength && _readLine.equals(boundary)){
                           break;
                        }
                        // 如果为分隔符，认为读取完毕
                        if(((_nextReadCount == boundaryLength) || (_nextReadCount - 2 == boundaryLength)) && (_nextReadLine.equals(boundary) || _nextReadLine.equals(lastBoundary))){
                           // 获得数据
                           valueBuffer.append(_readLine.substring(0, _readLine.length() - LINE_SPLITTER_LENGTH));
                           break;
                        }else{
                           // 获得数据
                           valueBuffer.append(_readLine);
                        }
                     }
                     parameters().set(name, valueBuffer.toString());
                  }
               }else if(contents.length == 3){
                  // 如果读取的内容为文件
                  isFile = false;
                  content = contents[1];
                  if(content.indexOf(CONTENT_DISPOSITION_NAME) != -1){
                     name = content.substring(content.indexOf(CONTENT_DISPOSITION_NAME) + CONTENT_DISPOSITION_NAME.length(), content.lastIndexOf("\""));
                  }
                  content = contents[2];
                  if(content.indexOf(CONTENT_DISPOSITION_FILE_NAME) != -1){
                     fileName = content.substring(content.indexOf(CONTENT_DISPOSITION_FILE_NAME) + CONTENT_DISPOSITION_FILE_NAME.length(), content.lastIndexOf("\""));
                     if(!RString.isEmpty(fileName)){
                        isFile = true;
                     }
                  }
                  if(isFile){
                     // 读取文件类型
                     if(!readLine()){
                        return false;
                     }
                     if(_readLine.startsWith(CONTENT_TYPE)){
                        fileContentType = _readLine.substring(content.indexOf(CONTENT_TYPE) + CONTENT_TYPE.length()).trim();
                     }
                     // 跳过空行
                     if(!readLine()){
                        return false;
                     }

                     String sUploadFileName = RFile.makeFilename(uploadTempDir(), RUuid.uuid());
                     uploadFile = new File(sUploadFileName);
                     fileOutputStream = new FileOutputStream(uploadFile);
                     int nFileSize = 0;
                     while(readLine()){
                        // 如果为分隔符，认为读取完毕
                        if(_readCount == boundaryLength && _readLine.equals(boundary)){
                           break;
                        }
                        // 如果分隔符，认为读取完毕
                        if(((_nextReadCount == boundaryLength) || (_nextReadCount - 2 == boundaryLength)) && (_nextReadLine.equals(boundary) || _nextReadLine.equals(lastBoundary))){
                           // 获得数据
                           nFileSize += _readCount - LINE_SPLITTER_LENGTH;
                           fileOutputStream.write(_readLineBytes, 0, _readCount - LINE_SPLITTER_LENGTH);
                           break;
                        }else{
                           // 获得数据
                           nFileSize += _readCount;
                           fileOutputStream.write(_readLineBytes, 0, _readCount);
                        }
                     }
                     fileOutputStream.close();
                     // 存放文件信息
                     FWebUploadFile file = new FWebUploadFile();
                     file.setName(name);
                     file.setFileName(fileName);
                     file.setUploadName(sUploadFileName);
                     file.setLength(nFileSize);
                     file.setContentType(fileContentType);
                     files().push(file);
                  }
               }
            }
         }
      }catch(Exception e){
         throw new FFatalError(e);
      }
      return true;
   }

   // 读取一行数据
   private boolean readLine() throws Exception{
      if(_hasReadNext){
         if(_nextReadCount == -1){
            return false;
         }
         _readLine = _nextReadLine;
         _readCount = _nextReadCount;
         System.arraycopy(_nextReadLineBytes, 0, _readLineBytes, 0, _nextReadCount);
      }else{
         _readCount = _servletInputStream.readLine(_readLineBytes, 0, MAX_LINE_SIZE);
         if(_readCount == -1){
            return false;
         }
         if(_characterEncoding == null){
            _readLine = new String(_readLineBytes, 0, _readCount);
         }else{
            _readLine = new String(_readLineBytes, 0, _readCount, _characterEncoding);
         }
         _hasReadNext = true;
      }
      _nextReadCount = _servletInputStream.readLine(_nextReadLineBytes, 0, MAX_LINE_SIZE);
      if(_nextReadCount == -1){
         return false;
      }
      if(_characterEncoding == null){
         _nextReadLine = new String(_nextReadLineBytes, 0, _nextReadCount);
      }else{
         _nextReadLine = new String(_nextReadLineBytes, 0, _nextReadCount, _characterEncoding);
      }
      return true;
   }

   /**
    * <p>设置字符编码</p>
    *
    * @param sValue 字符编码
    */
   public void setCharacterEncoding(String sValue){
      _characterEncoding = sValue;
   }

   /**
    * <p>设置临时路径</p>
    *
    * @param sValue 临时路径
    */
   public void setUploadTempDir(String sValue){
      _uploadTempDir = sValue;
   }

   /**
    * <p>获得临时路径</p>
    *
    * @return 临时路径
    */
   public String uploadTempDir(){
      return _uploadTempDir;
   }
}
