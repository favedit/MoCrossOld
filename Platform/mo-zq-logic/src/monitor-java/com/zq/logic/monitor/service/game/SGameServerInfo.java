package com.zq.logic.monitor.service.game;

import org.mo.com.lang.FObjects;
import org.mo.com.lang.RLong;
import org.mo.com.lang.RString;

//============================================================
// <T>游戏服务信息。</T>
//============================================================
public class SGameServerInfo{

   // 内存总共大小
   public long memoryTotal;

   // 内存自由大小
   public long memoryFree;

   // 交换使用大小
   public long swapCached;

   // 交换总共大小
   public long swapTotal;

   // 交换自由大小
   public long swapFree;

   // 进程信息集合
   public FObjects<SGameProcessInfo> processes = new FObjects<SGameProcessInfo>(SGameProcessInfo.class);

   //============================================================
   // <T>查找进程信息。</T>
   //
   // @param user 用户
   // @param processName 进程名称
   // @return 进程信息
   //============================================================
   public SGameProcessInfo findProcess(String user,
                                       String processName){
      for(SGameProcessInfo processInfo : processes){
         if(user.equals(processInfo.user) && processInfo.command.contains(processName)){
            return processInfo;
         }
      }
      return null;
   }

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
   // <T>解析内容。</T>
   //
   // @param source 来源字符串
   //============================================================
   public void parseLineInfo(String line){
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
   // <T>解析内容。</T>
   //
   // @param source 来源字符串
   //============================================================
   public void parseProcessInfo(String line){
      if(!line.isEmpty()){
         SGameProcessInfo processInfo = new SGameProcessInfo();
         processInfo.parse(line);
         processes.push(processInfo);
      }
   }

   //============================================================
   // <T>解析内容。</T>
   //
   // @param source 来源字符串
   //============================================================
   public void parse(String source){
      String[] lines = RString.split(source, "\n");
      int count = lines.length;
      for(int n = 0; n < count; n++){
         String line = RString.trim(lines[n]);
         if(line.startsWith("----- memory ")){
            parseLineInfo(lines[++n]);
            parseLineInfo(lines[++n]);
            continue;
         }
         if(line.startsWith("----- swap ")){
            parseLineInfo(lines[++n]);
            parseLineInfo(lines[++n]);
            parseLineInfo(lines[++n]);
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
}
