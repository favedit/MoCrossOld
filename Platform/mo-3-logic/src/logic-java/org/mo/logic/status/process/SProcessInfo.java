package org.mo.logic.status.process;

import org.mo.com.xml.FXmlNode;

//============================================================
// <T>进程信息。</T>
//============================================================
public class SProcessInfo
{
   // 用户
   public String user;

   // 进程编号
   public int processId;

   // CPU比率
   public float cpuRate;

   // 内存比率
   public float memoryRate;

   // 虚拟内存大小
   public long memoryVirtual;

   // 总共内存大小
   public long memoryTotal;

   // 终端
   public String tty;

   // 状态
   public String status;

   // 日期
   public String date;

   // 时间
   public String time;

   // 程序名称
   public String processName;

   // 命令行
   public String command;

   //============================================================
   // <T>解析内容。</T>
   //
   // @param source 来源字符串
   //============================================================
   public void parse(String source){
      throw new NoSuchMethodError();
   }

   //============================================================
   // <T>保存信息到设置节点。</T>
   //
   // @param xconfig 配置节点
   //============================================================
   public void saveConfig(FXmlNode xconfig){
      xconfig.set("user", user);
      xconfig.set("process_id", processId);
      xconfig.set("process_name", processName);
      xconfig.set("cpu_rate", cpuRate);
      xconfig.set("memory_rate", memoryRate);
      xconfig.set("memory_virtual", memoryVirtual);
      xconfig.set("memory_total", memoryTotal);
      xconfig.set("tty", tty);
      xconfig.set("status", status);
      xconfig.set("date", date);
      xconfig.set("time", time);
      xconfig.set("command", command);
   }
}
