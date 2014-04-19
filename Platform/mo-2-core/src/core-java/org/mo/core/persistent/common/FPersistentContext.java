package org.mo.core.persistent.common;

import org.mo.com.lang.RInteger;

public class FPersistentContext
      implements
         IPersistentContext
{
   private int _dataBlock = RInteger.SIZE_1K;

   private int _dataStart = RInteger.SIZE_32K;

   private int _nameAlloc = RInteger.SIZE_32K;

   // Len + KeyId + Index + KeyStr 
   private int _nameBlock = 64;

   private int _nameStart = RInteger.SIZE_1K;

   // private String _path = "C:/work/persistent";
   private String _path = "D:/workspace/tomcat/work/persistent";

   @Override
   public int dataBlock(){
      return _dataBlock;
   }

   @Override
   public int dataStart(){
      return _dataStart;
   }

   @Override
   public int nameAlloc(){
      return _nameAlloc;
   }

   @Override
   public int nameBlock(){
      return _nameBlock;
   }

   @Override
   public int nameStart(){
      return _nameStart;
   }

   @Override
   public String path(){
      return _path;
   }

   public void setDataBlock(int value){
      _dataBlock = value;
   }

   public void setDataStart(int value){
      _dataStart = value;
   }

   public void setNameAlloc(int value){
      _nameAlloc = value;
   }

   public void setNameBlock(int value){
      _nameBlock = value;
   }

   public void setNameStart(int value){
      _nameStart = value;
   }

   public void setPath(String path){
      _path = path;
   }
}
