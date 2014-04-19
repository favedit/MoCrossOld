package org.mo.eng.store;

import java.io.File;
import org.mo.com.io.RFile;
import org.mo.com.lang.FObject;

//============================================================
// <T>配置设置信息。</T>
//============================================================
public class FXmlConfigMeta
      extends FObject
{
   // 名称
   protected String _name;

   // 文件名称
   protected String _fileName;

   // 是否存在
   protected boolean _exists;

   // 最后修改时间
   protected long _lastModified;

   //============================================================
   // <T>构造配置设置信息。</T>
   //============================================================
   public FXmlConfigMeta(){
   }

   //============================================================
   // <T>获得名称。</T>
   //
   // @return 名称
   //============================================================
   public String name(){
      return _name;
   }

   //============================================================
   // <T>设置名称。</T>
   //
   // @param name 名称
   //============================================================
   public void setName(String name){
      _name = name;
   }

   //============================================================
   // <T>判断是否指定文件名称。</T>
   //
   // @param fileName 文件名称
   // @return 是否指定文件名称
   //============================================================
   public boolean isFileName(String fileName){
      return fileName.equals(fileName);
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
      _fileName = fileName;
      _exists = RFile.isFile(fileName);
      if(_exists){
         _lastModified = new File(fileName).lastModified();
      }
   }

   //============================================================
   // <T>获得最后修改时间。</T>
   //
   // @return 最后修改时间
   //============================================================
   public long lastModified(){
      return _lastModified;
   }
}
