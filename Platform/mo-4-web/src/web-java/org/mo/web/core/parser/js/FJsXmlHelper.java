package org.mo.web.core.parser.js;

import org.mo.com.io.RFile;
import org.mo.com.lang.FStrings;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;

public class FJsXmlHelper
{

   private FJsXmlClasses _classes;

   private String _encoding = "UTF-8";

   private static ILogger _logger = RLogger.find(FJsHelper.class);

   public FJsXmlClasses classes(){
      if(null == _classes){
         _classes = new FJsXmlClasses();
      }

      return _classes;
   }

   public void parseSpace(String space,
                          String directory){
      FJsXmlParser parser = new FJsXmlParser();
      _logger.debug(this, "parseSpace", "Parse space (space={0},directory={1})", space, directory);
      FStrings files = RFile.listFiles(directory);
      for(String file : files){
         if(file.endsWith(".js")){
            FJsXmlClass clazz = parser.parse(file, _encoding);
            if(null != clazz){
               clazz.setSpace(space);
               classes().push(clazz);
            }
         }
      }
   }

}
