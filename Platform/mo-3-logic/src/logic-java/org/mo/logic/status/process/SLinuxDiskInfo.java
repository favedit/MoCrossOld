package org.mo.logic.status.process;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.mo.com.lang.RLong;
import org.mo.com.lang.RString;

//============================================================
//<T>硬盘信息。</T>
//============================================================
public class SLinuxDiskInfo
      extends SDiskInfo
{

   //============================================================
   // <T>解析硬盘信息集合。</T>
   //
   // @param source 来源字符串
   //============================================================
   public void parsediskinfo(String source){
      String[] lines = RString.split(source, "\n");
      int count = lines.length;
      for(int n = 1; n < count; n++){
         String line = RString.trim(lines[n]);
         saveDisk(line);
      }
   }

   //============================================================
   // <T>将硬盘信息推入集合。</T>
   //
   // @param source 来源字符串
   //============================================================
   private void saveDisk(String line){
      String[] lineResults = replaceSpace(line).split(" ");
      SDiskInfo disk = new SDiskInfo();
      for(int j = 0; j < lineResults.length; j++){
         if(j == 0){
            disk.fileSystem = lineResults[j];
         }else if(j == 1){
            disk.size = RLong.parse(lineResults[j]) * 1024;
         }else if(j == 2){
            disk.used = RLong.parse(lineResults[j]) * 1024;
         }else if(j == 3){
            disk.avail = RLong.parse(lineResults[j]) * 1024;
         }else if(j == 4){
            disk.usePercent = RLong.parse(lineResults[j].substring(0, lineResults[j].lastIndexOf("%")));
         }else if(j == 5){
            disk.mountedOn = lineResults[j];
         }
      }
      disks.push(disk);
   }

   //============================================================
   // <T>把多个空格替换成一个空格</T>
   //
   // @param source 来源字符串
   //============================================================
   public String replaceSpace(String str){
      String regEX = "[' ']+";
      Pattern p = Pattern.compile(regEX);
      Matcher m = p.matcher(str);
      return m.replaceAll(" ");
   }

}
