package org.mo.eng.store;

import java.io.File;
import org.mo.com.io.RFile;
import org.mo.com.lang.FFatalError;

//============================================================
// <T>XML数据集合信息描述。</T>
//============================================================
public class FXmlDatasetMeta
{
   // 名称
   protected String _name;

   // 文件名称
   protected String _fileName;

   // 最后变更时间
   protected long _lastModified;

   //============================================================
   // <T>构造XML数据集合信息描述。</T>
   //============================================================
   public FXmlDatasetMeta(){
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
      if(!RFile.isFile(fileName)){
         throw new FFatalError("Exists this file (file={0})", fileName);
      }
      _fileName = fileName;
      _lastModified = new File(fileName).lastModified();
   }

   //============================================================
   // <T>获得最后变更时间。</T>
   //
   // @return 最后变更时间
   //============================================================
   public long lastModified(){
      return _lastModified;
   }
}
