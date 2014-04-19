/*
 * @(#)FWebUploadFile.java
 *
 * Copyright 2005 microbject, All Rights Reserved.
 *
 */
package org.mo.web.protocol.common;

import org.mo.com.io.RFile;
import org.mo.com.lang.FObject;
import org.mo.com.lang.RInteger;
import org.mo.com.lang.generic.INamed;
import org.mo.com.lang.generic.TDumpInfo;

/**
 * <p>上载文件</p>
 * 
 * @author ALEX
 * @version 1.00 - 2005/10/20
 */
public class FWebUploadFile
      extends FObject
      implements
         INamed<String>
{
   private String _contentType = null;

   private String _fileName = null;

   private int _length = 0;

   private String _name = null;

   private String _uploadName = null;

   public FWebUploadFile(){
   }

   public FWebUploadFile(String pack){
   }

   public String contentType(){
      return _contentType;
   }

   @Override
   public TDumpInfo dump(TDumpInfo info){
      info.append(" Name:" + name());
      info.append(" File Name:" + _fileName);
      info.append(" Upload Name:" + _uploadName);
      info.append(" Length:" + _length);
      info.append(" Content Type:" + _contentType);
      return info;
   }

   public String fileName(){
      return _fileName;
   }

   public int length(){
      return _length;
   }

   public void move(String fileName){
      RFile.copyFile(uploadName(), fileName);
   }

   @Override
   public String name(){
      return _name;
   }

   public void setContentType(String sValue){
      _contentType = sValue;
   }

   public void setFileName(String sValue){
      _fileName = sValue;
   }

   public void setLength(int nValue){
      _length = nValue;
   }

   public void setLength(String sValue){
      _length = RInteger.parse(sValue);
   }

   public void setName(String sValue){
      _name = sValue;
   }

   public void setUploadName(String sValue){
      _uploadName = sValue;
   }

   public String uploadName(){
      return _uploadName;
   }
}
