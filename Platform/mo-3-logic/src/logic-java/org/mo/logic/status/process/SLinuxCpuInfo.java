package org.mo.logic.status.process;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.mo.com.lang.RDouble;
import org.mo.com.lang.RString;

public class SLinuxCpuInfo
      extends SCpuInfo
{

   //============================================================
   // <T>解析硬盘信息集合。</T>
   //
   // @param source 来源字符串
   //============================================================
   public void parseCpuInfo(String source){
      String[] lines = RString.split(source, "\n");
      int count = lines.length;
      for(int n = 3; n < count; n++){
         String line = RString.trim(lines[n]);
         saveCpu(line);
      }
   }

   //============================================================
   // <T>将硬盘信息推入集合。</T>
   //
   // @param source 来源字符串
   //============================================================
   private void saveCpu(String line){
      String[] lineResults = replaceSpace(line).split(" ");
      SCpuInfo cpu = new SCpuInfo();
      for(int j = 1; j < lineResults.length; j++){
         if(j == 1){
            cpu.cpuId = lineResults[j];
         }else if(j >= 2 && j <= 9){
            cpu.used = cpu.used + RDouble.parse(lineResults[j]);
         }else if(j == 10){
            cpu.avail = RDouble.parse(lineResults[j]);
         }
      }
      cpu.used = formatTwoDecmals(cpu.used);
      cpus.push(cpu);
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

   //============================================================
   // <T>double保留两位小数</T>
   //
   // @param source 来源字符串
   //============================================================
   public double formatTwoDecmals(double val){
      BigDecimal bd = new BigDecimal(val);
      return bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
   }
}
