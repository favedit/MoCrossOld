package org.mo.util.svn;

import org.mo.com.io.FDirectory;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;

public class RDeleteSvnMain{

   private final static ILogger _logger = RLogger.find(RDeleteSvnMain.class);

   public static void main(String[] args){
      FDirectory root = new FDirectory("D:/config");
      removeSvn(root);
   }

   public static void removeSvn(FDirectory directory){
      for(FDirectory dir : directory.listDirectory()){
         if(dir.isName(".svn")){
            _logger.debug(null, "removeSvn", "Remove svn directory (file={0})", directory);
            dir.delete();
         }else{
            removeSvn(dir);
         }
      }
   }

}
