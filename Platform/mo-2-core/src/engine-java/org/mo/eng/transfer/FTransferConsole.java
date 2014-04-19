package org.mo.eng.transfer;

import org.mo.com.io.RFile;
import org.mo.com.lang.FObjects;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.IXmlObject;
import org.mo.com.xml.RXmlObject;
import org.mo.core.aop.face.AProperty;
import org.mo.eng.store.FXmlConfigConsole;
import org.mo.eng.transfer.common.XCsvImport;

public class FTransferConsole
      extends FXmlConfigConsole<XCsvImport>
      implements
         ITransferConsole
{
   @AProperty
   protected String _pathTemporary;

   @AProperty
   protected String _pathUpload;

   @Override
   protected FObjects<XCsvImport> createCollection(){
      return new FObjects<XCsvImport>(XCsvImport.class);
   }

   @Override
   public FCsvImport findCsvImport(String name,
                                   String columnLabel[],
                                   String columnName[]){
      IXmlObject config = get(name);
      FXmlNode configNode = RXmlObject.convertDeepToNode(config, EXmlConfig.Simple);
      FCsvImport file = new FCsvImport();
      if(null != columnLabel){
         file.loadConfigByLabel(configNode, columnLabel);
      }else if(null != columnName){
         file.loadConfigByName(configNode, columnName);
      }else{
         file.loadConfig(configNode);
      }
      return file;
   }

   @Override
   public String makeStorePath(String name){
      return RFile.makeFilename(_pathTemporary, name);
   }

   @Override
   public String makeUploadPath(String name){
      return RFile.makeFilename(_pathUpload, name);
   }
}
