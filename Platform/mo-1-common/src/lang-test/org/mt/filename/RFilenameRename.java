package org.mt.filename;

import org.mo.com.io.FFileInfo;
import org.mo.com.io.FFileInfos;
import org.mo.com.io.RDirectory;
import org.mo.com.io.RFile;
import org.mo.com.lang.RString;

public class RFilenameRename
{
   protected static FFileInfos _sources;

   protected static FFileInfos _targets;

   public static FFileInfo findSource(String code){
      for(FFileInfo source : _sources){
         String shortName = source.shortName();
         if(shortName.contains("第" + code + "話")){
            return source;
         }
      }
      return null;
   }

   public static void main(String[] args){
      String sourcePath = "E:/Download/Finish/! ! [动画-体育] 灌篮高手 (1993-101.mkv-960x720-52.0G)";
      String targetPath = "E:/Download/Finish/! ! [动画-体育] 灌篮高手 (1993-101.mkv-960x720-52.0G)/srt";
      _sources = RDirectory.listFiles(sourcePath, false);
      _targets = RDirectory.listFiles(targetPath, false);
      for(FFileInfo target : _targets){
         String shortName = target.shortName();
         String code = RString.mid(shortName, "灌篮高手", ".srt");
         if(code.startsWith("0")){
            code = code.substring(1);
         }
         // 查找来源
         FFileInfo source = findSource(code);
         if(null != source){
            System.out.println(code);
            String sourceShortName = source.shortName();
            sourceShortName = sourceShortName.substring(0, sourceShortName.length() - 4) + ".srt";
            RFile.rename(target.fileName(), targetPath + "/" + sourceShortName);
         }
      }
   }
}
