package org.mo.util.svn;

import java.io.File;
import org.mo.com.io.RFile;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;

public class RSyncSvnMain
{
   private final static ILogger _logger = RLogger.find(RSyncSvnMain.class);

   public static void clearEmptySvn(File parent){
      //      File[] files = parent.listFiles();
      //      for(File file : files){
      //         if(file.isDirectory()){
      //            if(!".svn".equals(file.getName())){
      //               clearSvn(file);
      //            }
      //         }else{
      //            _logger.debug(null, "clearSvn", "Delete file: {0}", file.getAbsolutePath().substring(root.getAbsolutePath().length()));
      //            file.delete();
      //         }
      //      }
   }

   public static void clearSvn(File root,
                               File parent){
      File[] files = parent.listFiles();
      for(File file : files){
         if(file.isDirectory()){
            if(!".svn".equals(file.getName())){
               clearSvn(root, file);
            }
         }else{
            _logger.debug(null, "clearSvn", "Delete file: {0}", file.getAbsolutePath().substring(root.getAbsolutePath().length()));
            file.delete();
         }
      }
   }

   public static void syncSvn(File source,
                              File sourceParent,
                              File target){
      for(File sourceFile : sourceParent.listFiles()){
         if(sourceFile.isDirectory()){
            if(!".svn".equals(sourceFile.getName())){
               syncSvn(source, sourceFile, target);
            }
         }else{
            String filename = target.getAbsolutePath() + sourceFile.getAbsolutePath().substring(source.getAbsolutePath().length());
            _logger.debug(null, "syncSvn", "Move file: {0}", filename);
            RFile.copyFile(sourceFile.getAbsolutePath(), filename);
         }
      }

   }

   public static void main(String[] args){
      File root1 = new File("D:/Workspace/eUIS");
      File root2 = new File("D:/Workspace/eUIS-version/workspace");
      clearSvn(root2, root2);
      syncSvn(root1, root1, root2);
      clearEmptySvn(root2);
   }
}
