package com.zq.logic.monitor.service.game;

import org.mo.com.lang.RString;

//============================================================
// <T>检查游戏服务信息。</T>
//============================================================
public class SGameCheckServer{

   // 用户
   public String user;

   // 进程别称
   public String processAlias;

   // 进程名称
   public String processName;

   //============================================================
   // <T>解析内容。</T>
   //
   // @param source 来源字符串
   //============================================================
   public void parse(String source){
      String[] items = RString.split(source, ":");
      user = items[0];
      processAlias = items[1];
      processName = items[2];
   }
}
