package org.mo.com.io;

import java.io.File;
import org.mo.com.lang.FObject;
import org.mo.com.lang.RString;
import org.mo.com.lang.generic.TDumpInfo;

//============================================================
// <T>文件信息。</T>
//============================================================
public class FFileInfo
      extends FObject
{
   // 文件名称
   protected String _fileName;

   //============================================================
   // <T>构造文件信息。</T>
   //============================================================
   public FFileInfo(){
   }

   //============================================================
   // <T>构造文件信息。</T>
   //
   // @param fileName 文件名称
   //============================================================
   public FFileInfo(String fileName){
      setFileName(fileName);
   }

   //============================================================
   // <T>获得短名称。</T>
   //
   // @return 短名称
   //============================================================
   public String shortName(){
      int find = _fileName.lastIndexOf(File.separatorChar);
      if(find != -1){
         return _fileName.substring(find + 1);
      }
      return _fileName;
   }

   //============================================================
   // <T>获得文件名称。</T>
   //
   // @return 文件名称
   //============================================================
   public String fileName(){
      return _fileName;
   }

   //============================================================
   // <T>设置文件名称。</T>
   //
   // @param fileName 文件名称
   //============================================================
   public void setFileName(String fileName){
      fileName = RString.replace(fileName, '/', File.separatorChar);
      fileName = RString.replace(fileName, '\\', File.separatorChar);
      _fileName = fileName;
   }

   //============================================================
   // <T>生成运行信息。</T>
   //
   // @param info 运行信息
   // @return 运行信息
   //============================================================
   @Override
   public TDumpInfo dump(TDumpInfo info){
      info.append(_fileName);
      return info;
   }
}
