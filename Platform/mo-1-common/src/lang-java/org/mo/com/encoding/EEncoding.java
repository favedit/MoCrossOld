package org.mo.com.encoding;

public enum EEncoding{
   Gb2312,
   Utf8;
   @Override
   public String toString(){
      if(this == Utf8){
         return "UTF-8";
      }
      return "";
   }
}
