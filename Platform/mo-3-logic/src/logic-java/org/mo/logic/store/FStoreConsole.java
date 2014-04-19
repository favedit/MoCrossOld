package org.mo.logic.store;

import org.mo.com.io.RFile;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RString;
import org.mo.core.aop.face.AProperty;

public class FStoreConsole
      implements
         IStoreConsole
{
   @AProperty
   protected String _pathSystemConfig;

   @AProperty
   protected String _pathSystemResource;

   @AProperty
   protected String _pathTemporary;

   @AProperty
   protected String _pathUserConfig;

   @AProperty
   protected String _pathUserResource;

   @Override
   public String storeHomePath(){
      // 获得基础路径
      return _pathSystemResource;
   }

   @Override
   public String systemResourcePath(String recordCode,
                                    String recordGuid,
                                    String attachmentGuid,
                                    String extendName){
      // 获得文件路径
      String path = "/" + recordCode + "/" + recordGuid + "/" + attachmentGuid + "." + extendName;
      return _pathSystemResource + RString.toLower(path);
   }

   @Override
   public String temporaryPath(String path){
      return RFile.makeFilename(_pathTemporary, path);
   }

   public String userPath(){
      return _pathUserResource;
   }

   @Override
   public String userStorePath(EStorePlace place,
                               String userId,
                               String recordCode,
                               String recordName,
                               String recordGuid,
                               String attachmentGuid,
                               String extendName){
      // 获得基础路径
      String resultPath = null;
      switch(place){
         case UserConfig:
            resultPath = _pathUserConfig;
            break;
         case UserResource:
            resultPath = _pathUserResource;
            break;
         default:
            throw new FFatalError("Unknown enum place {0}", place);
      }
      // 获得文件路径
      recordCode = RString.toLower(recordCode);
      String fileName = RString.toLower(attachmentGuid) + "." + RString.toLower(extendName);
      return resultPath + "/" + userId + "/" + recordCode + "/" + recordGuid + "/" + fileName;
   }
}
