package org.mo.logic.store;

public interface IStoreConsole
{
   String storeHomePath();

   /**
    * <T>根据文件的相关信息得出文件的路径。</T>
    * 
    * @param type
    * @param group
    * @param name
    * @param path
    * @return
    */
   String systemResourcePath(String recordCode,
                             String recordGuid,
                             String attachmentGuid,
                             String extendName);

   String temporaryPath(String path);

   String userPath();

   String userStorePath(EStorePlace place,
                        String userId,
                        String recordCode,
                        String recordName,
                        String recordGuid,
                        String attachmentGuid,
                        String extendName);
}
