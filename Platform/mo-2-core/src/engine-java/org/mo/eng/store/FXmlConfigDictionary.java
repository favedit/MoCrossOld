package org.mo.eng.store;

import org.mo.com.lang.FDictionary;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RString;

public class FXmlConfigDictionary
      extends FDictionary<FXmlConfig>
{
   private final FDictionary<FXmlConfig> _fileNames = new FDictionary<FXmlConfig>(FXmlConfig.class);

   public FXmlConfigDictionary(){
      super(FXmlConfig.class);
   }

   public FXmlConfig getByFileName(String fileName){
      return _fileNames.get(fileName);
   }

   @Override
   public FXmlConfig remove(String name){
      FXmlConfig config = super.remove(name);
      if(null != config){
         String fileName = config.meta().fileName();
         if(RString.isEmpty(fileName)){
            throw new FFatalError("File name is null.");
         }
         _fileNames.remove(fileName);
      }
      return config;
   }

   @Override
   public void set(String name,
                   FXmlConfig config){
      String fileName = config.meta().fileName();
      if(RString.isEmpty(fileName)){
         throw new FFatalError("File name is null.");
      }
      super.set(name, config);
      _fileNames.set(fileName, config);
   }
}
