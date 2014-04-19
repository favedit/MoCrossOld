package org.mo.logic.batch.common;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.FXmlNode;
import org.mo.logic.batch.IBatchConsole;

public class FBatchInclude
      extends FAbstractBatchBase
{
   @SuppressWarnings("unused")
   private static ILogger _logger = RLogger.find(FBatchInclude.class);

   public static String NAME = "Include";

   @SuppressWarnings("unused")
   private FXmlNode _config;

   private String _path;

   public FBatchInclude(IBatchConsole console){
      super(console);
   }

   public void loadConfig(FXmlNode config){
      _config = config;
      _path = config.get("path");
      if(RString.isEmpty(_path)){
         throw new FFatalError("Include path is empty. (node={0})", config.xml());
      }
   }

   public String path(){
      return _path;
   }
}
