package org.mo.logic.status.process;

import org.mo.com.lang.RFloat;
import org.mo.com.lang.RInteger;
import org.mo.com.lang.RLong;

//============================================================
// <T>游戏进程信息。</T>
//============================================================
public class SLinuxProcessInfo
      extends SProcessInfo
{
   //============================================================
   // <T>解析内容。</T>
   //
   // @param source 来源字符串
   //============================================================
   @Override
   public void parse(String source){
      // 读取用户
      int index = source.indexOf(' ');
      if(-1 != index){
         user = source.substring(0, index);
      }
      source = source.substring(index).trim();
      // 读取进程编号
      index = source.indexOf(' ');
      if(-1 != index){
         processId = RInteger.parse(source.substring(0, index));
      }
      source = source.substring(index).trim();
      // 读取CPU比率
      index = source.indexOf(' ');
      if(-1 != index){
         cpuRate = RFloat.parse(source.substring(0, index));
      }
      source = source.substring(index).trim();
      // 读取内存比率
      index = source.indexOf(' ');
      if(-1 != index){
         memoryRate = RFloat.parse(source.substring(0, index));
      }
      source = source.substring(index).trim();
      // 读取虚拟内存大小
      index = source.indexOf(' ');
      if(-1 != index){
         memoryVirtual = RLong.parse(source.substring(0, index)) * 1024;
      }
      source = source.substring(index).trim();
      // 读取总共内存大小
      index = source.indexOf(' ');
      if(-1 != index){
         memoryTotal = RLong.parse(source.substring(0, index)) * 1024;
      }
      source = source.substring(index).trim();
      // 读取终端
      index = source.indexOf(' ');
      if(-1 != index){
         tty = source.substring(0, index);
      }
      source = source.substring(index).trim();
      // 读取状态
      index = source.indexOf(' ');
      if(-1 != index){
         status = source.substring(0, index);
      }
      source = source.substring(index).trim();
      // 读取日期
      index = source.indexOf(' ');
      if(-1 != index){
         date = source.substring(0, index);
      }
      source = source.substring(index).trim();
      // 读取时间
      index = source.indexOf(' ');
      if(-1 != index){
         time = source.substring(0, index);
      }
      source = source.substring(index).trim();
      // 读取命令
      index = source.indexOf(' ');
      if(-1 == index){
         index = source.length();
      }
      int indexStart = source.lastIndexOf('/', index);
      if(-1 == indexStart){
         processName = source.substring(0, index);
      }else{
         processName = source.substring(indexStart + 1, index);
      }
      command = source;
   }
}
