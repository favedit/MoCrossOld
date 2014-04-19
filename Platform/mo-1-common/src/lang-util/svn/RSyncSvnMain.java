package svn;

import java.io.File;
import org.mo.com.io.RFile;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;

public class RSyncSvnMain
{
   private final static ILogger _logger = RLogger.find(RSyncSvnMain.class);

   //============================================================
   public static void clearEmptySvn(File root,
                                    File parent){
      int count = 0;
      File[] files = parent.listFiles();
      for(File file : files){
         if(file.isDirectory()){
            if(!".svn".equals(file.getName())){
               clearEmptySvn(root, file);
            }
         }else{
            count++;
         }
      }
      if(0 == count){
         _logger.debug(null, "clearSvn", "Delete file: {1}", parent.getAbsolutePath());
         parent.delete();
      }
   }

   //============================================================
   public static void clearSvn(File root,
                               File parent){
      File[] files = parent.listFiles();
      for(File file : files){
         if(file.isDirectory()){
            if(!".svn".equals(file.getName())){
               clearSvn(root, file);
            }
         }else{
            _logger.debug(null, "clearSvn", "Delete file: {1}", file.getAbsolutePath());
            file.delete();
         }
      }
   }

   //============================================================
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
            _logger.debug(null, "syncSvn", "Move file: {1}", filename);
            RFile.copyFile(sourceFile.getAbsolutePath(), filename);
         }
      }
   }

   //============================================================
   public static void convert(String sourcePath,
                              String targetPath){
      File source = new File(sourcePath);
      File target = new File(targetPath);
      clearSvn(source, target);
      syncSvn(source, source, target);
      clearEmptySvn(source, target);
   }

   //============================================================
   public static void convertResource(){
      convert("E:\\Develop\\Platform-Sns\\Bcdr\\Resource.DP", "E:\\Develop\\Platform-Sns\\Bcdr\\Resource.OL");
   }

   //============================================================
   public static void convertClient(){
      String sourcePath = "E:\\Develop\\Platform-Inner\\Develop\\Client\\Workspace\\";
      String targetPath = "E:\\Develop\\Platform-Sns\\Develop\\Client\\";
      convert(sourcePath + "mo-1-common", targetPath + "mo-1-common");
      convert(sourcePath + "mo-2-core", targetPath + "mo-2-core");
      convert(sourcePath + "mo-3-engine-3d", targetPath + "mo-3-engine-3d");
      convert(sourcePath + "mo-4-message3d", targetPath + "mo-4-message3d");
      convert(sourcePath + "mo-5-face", targetPath + "mo-5-face");
      convert(sourcePath + "mo-5-logic", targetPath + "mo-5-logic");
      convert(sourcePath + "mo-6-web3d", targetPath + "mo-6-web3d");
      convert(sourcePath + "mo-7-design3d", targetPath + "mo-7-design3d");
      convert(sourcePath + "mo-7-designui", targetPath + "mo-7-designui");
      convert(sourcePath + "mo-client", targetPath + "mo-client");
      convert(sourcePath + "mo-client3d", targetPath + "mo-client3d");
      convert(sourcePath + "mo-client3d-demo", targetPath + "mo-client3d-demo");
      convert(sourcePath + "mo-client-test", targetPath + "mo-client-test");
      convert(sourcePath + "mo-loader", targetPath + "mo-loader");
      convert(sourcePath + "mo-web3d", targetPath + "mo-web3d");
   }

   //============================================================
   public static void convertServer(){
      String sourcePath = "E:\\Develop\\Platform-Inner\\Develop\\Server\\";
      String targetPath = "E:\\Develop\\Platform-Sns\\Develop\\Server\\";
      convert(sourcePath + "Common", targetPath + "Common");
      convert(sourcePath + "Config", targetPath + "Config");
      convert(sourcePath + "Service", targetPath + "Service");
      convert(sourcePath + "Web", targetPath + "Web");
   }

   //============================================================
   public static void convertTools(){
      String sourcePath = "E:\\Develop\\Platform-Inner\\Develop\\Tools\\";
      String targetPath = "E:\\Develop\\Platform-Sns\\Develop\\Tools\\";
      convert(sourcePath + "1 - Common", targetPath + "1 - Common");
      convert(sourcePath + "2 - Core", targetPath + "2 - Core");
      convert(sourcePath + "3 - Design", targetPath + "3 - Design");
      convert(sourcePath + "3 - Design2d", targetPath + "3 - Design2d");
      convert(sourcePath + "3 - Design3d", targetPath + "3 - Design3d");
      convert(sourcePath + "4 - Business", targetPath + "4 - Business");
      convert(sourcePath + "4 - Manage", targetPath + "4 - Manage");
      convert(sourcePath + "5 - Utility", targetPath + "5 - Utility");
      convert(sourcePath + "6 - Test", targetPath + "6 - Test");
   }

   //============================================================
   public static void main(String[] args){
      convertResource();
      //convertClient();
      //convertServer();
      //convertTools();
   }
}
