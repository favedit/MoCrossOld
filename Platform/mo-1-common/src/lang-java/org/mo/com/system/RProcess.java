package org.mo.com.system;

//============================================================
// <T>进程工具类。</T>
//============================================================
public class RProcess
{
   //============================================================
   // <T>执行一个命令。</T>
   //
   // @param sommand 命令
   //============================================================
   public static void execute(String command){
      FProcess process = new FProcess(command);
      process.start();
   }

   //============================================================
   // <T>执行一个命令。</T>
   //
   // @param sommand 命令
   // @param destroyListener 关闭监听器
   //============================================================
   public static void execute(String command,
                              MListener destroyListener){
      FProcess process = new FProcess(command);
      process.addDestroyListener(destroyListener);
      process.start();
   }
}
