package org.mo.logic.status.process;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mo.com.lang.RLong;
import org.mo.com.lang.RString;

//============================================================
// <T>游戏服务信息。</T>
//============================================================
public class SLinuxServerInfo
      extends SServerInfo
{

   //============================================================
   // <T>解析内容。</T>
   //
   // @param source 来源字符串
   //============================================================
   public long parseLineValue(String line){
      int index = line.indexOf(":");
      String value = line.substring(index + 1).trim();
      if(value.endsWith(" kB")){
         value = value.substring(0, value.length() - 3);
         return RLong.parse(value) * 1024;
      }
      return RLong.parse(value);
   }

   //============================================================
   // <T>解析系统信息。</T>
   //
   // @param source 来源字符串
   //============================================================
   public void parseSystemInfo(String line){
      if(line.startsWith("MemTotal")){
         memoryTotal = parseLineValue(line);
      }else if(line.startsWith("MemFree")){
         memoryFree = parseLineValue(line);
      }else if(line.startsWith("SwapCached")){
         swapCached = parseLineValue(line);
      }else if(line.startsWith("SwapTotal")){
         swapTotal = parseLineValue(line);
      }else if(line.startsWith("SwapFree")){
         swapFree = parseLineValue(line);
      }
   }

   //============================================================
   // <T>解析系统信息集合。</T>
   //
   // @param source 来源字符串
   //============================================================
   public void parseSystemInfos(String source){
      String[] lines = RString.split(source, "\n");
      int count = lines.length;
      for(int n = 0; n < count; n++){
         String line = RString.trim(lines[n]);
         parseSystemInfo(line);
      }
   }

   //============================================================
   // <T>解析进程信息。</T>
   //
   // @param source 来源字符串
   //============================================================
   public void parseProcessInfo(String line){
      if(!line.isEmpty()){
         SLinuxProcessInfo processInfo = new SLinuxProcessInfo();
         processInfo.parse(line);
         processes.push(processInfo);
      }
   }

   //============================================================
   // <T>解析进程集合信息。</T>
   //
   // @param source 来源字符串
   //============================================================
   public void parseProcessInfos(String source,
                                 String... skips){
      String[] lines = RString.split(source, "\n");
      int count = lines.length;
      for(int n = 0; n < count; n++){
         String line = RString.trim(lines[n]);
         // 检查是否忽略
         boolean parse = true;
         if(null != skips){
            for(String skip : skips){
               if(line.contains(skip)){
                  parse = false;
                  break;
               }
            }
         }
         // 解析内容
         if(parse){
            parseProcessInfo(line);
         }
      }
   }

   //============================================================
   // <T>解析内容。</T>
   //
   // @param source 来源字符串
   //============================================================
   @Override
   public void parse(String source){
      String[] lines = RString.split(source, "\n");
      int count = lines.length;
      for(int n = 0; n < count; n++){
         String line = RString.trim(lines[n]);
         if(line.startsWith("----- memory ")){
            parseSystemInfo(lines[++n]);
            parseSystemInfo(lines[++n]);
            continue;
         }
         if(line.startsWith("----- swap ")){
            parseSystemInfo(lines[++n]);
            parseSystemInfo(lines[++n]);
            parseSystemInfo(lines[++n]);
            continue;
         }
         if(line.startsWith("----- process ")){
            for(int i = n + 1; i < count; i++){
               parseProcessInfo(lines[i]);
            }
            break;
         }
      }
   }
   
   //============================================================
   // <T>解析http信息。</T>
   //
   // @param source 来源字符串
   //============================================================
   public void parseHttp(String source){
	   String[] lines = RString.split(source, "\n");
	   int count = lines.length;
	   for(int n = 0; n < count; n++){
		   String line = RString.trim(lines[n]);
	       checkHttpResult(line);
	   }
   }
   
   //============================================================
   // <T>获取http状态信息。</T>
   //
   // @param line 来源字符串
   //============================================================
   private void checkHttpResult(String line) {
	   String[] lineResults = replaceSpace(line).split(" ");
	   httpResult = RString.parse(lineResults[1]);
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
