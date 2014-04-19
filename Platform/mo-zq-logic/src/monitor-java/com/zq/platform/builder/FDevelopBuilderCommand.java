package com.zq.platform.builder;

import org.mo.com.lang.FObject;
import org.mo.core.aop.face.AProperty;

//============================================================
// <T>开发编译器命令。</T>
//============================================================
public class FDevelopBuilderCommand
      extends FObject
{
   // 用户
   @AProperty
   protected String _user;

   // 路径
   @AProperty
   protected String _path;

   // 命令
   @AProperty
   protected String _command;

   //============================================================
   // <T>构造开发编译器命令。</T>
   //============================================================
   public FDevelopBuilderCommand(){
   }

   //============================================================
   // <T>获得用户。</T>
   //
   // @return 用户
   //============================================================
   public String user(){
      return _user;
   }

   //============================================================
   // <T>获得路径。</T>
   //
   // @return 路径
   //============================================================
   public String path(){
      return _path;
   }

   //============================================================
   // <T>获得命令。</T>
   //
   // @return 命令
   //============================================================
   public String command(){
      return _command;
   }
}
