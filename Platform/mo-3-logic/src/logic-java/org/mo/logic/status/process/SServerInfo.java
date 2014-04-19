package org.mo.logic.status.process;


import org.mo.com.lang.FObjects;
import org.mo.com.xml.FXmlNode;

//============================================================
// <T>服务器信息。</T>
//============================================================
public class SServerInfo
{
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
   
   //http状态
   public String httpResult;
   
   // 进程信息集合
   public FObjects<SProcessInfo> processes = new FObjects<SProcessInfo>(SProcessInfo.class);
   
   //============================================================
   // <T>查找进程信息。</T>
   //
   // @param user 用户
   // @param processName 进程名称
   // @return 进程信息
   //============================================================
   public SProcessInfo findProcess(String user,
                                   String processName){
      for(SProcessInfo processInfo : processes){
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
   public void parse(String source){
      throw new NoSuchMethodError();
   }

   //============================================================
   // <T>保存系统集合信息。</T>
   //
   // @param xconfig 配置节点
   //============================================================
   public void saveSystemConfig(FXmlNode xconfig){
      xconfig.set("memory_total", memoryTotal);
      xconfig.set("memory_free", memoryFree);
      xconfig.set("swap_cached", swapCached);
      xconfig.set("swap_total", swapTotal);
      xconfig.set("swap_free", swapFree);
      xconfig.set("http_result", httpResult);
   }
   
   //============================================================
   // <T>保存http信息。</T>
   //
   // @param xconfig 配置节点
   //============================================================
   public void saveHttpConfig(FXmlNode xconfig){
      xconfig.set("http_result", httpResult);
   }
   //============================================================
   // <T>保存进程集合信息。</T>
   //
   // @param xconfig 配置节点
   //============================================================
   public void saveProcessConfig(FXmlNode xconfig){
      xconfig.set("count", processes.count());
      for(SProcessInfo process : processes){
         FXmlNode xprocess = xconfig.createNode("Process");
         process.saveConfig(xprocess);
      }
   }
   
}
