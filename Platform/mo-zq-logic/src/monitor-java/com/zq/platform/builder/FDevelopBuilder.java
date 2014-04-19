package com.zq.platform.builder;

import org.mo.com.lang.FObject;
import org.mo.com.lang.RString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.system.FProcess;
import org.mo.core.aop.face.AProperty;

//============================================================
// <T>开发编译器。</T>
//============================================================
public abstract class FDevelopBuilder
      extends FObject
      implements
         IDevelopBuilder
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(FDevelopBuilder.class);

   // 有效性
   @AProperty
   protected boolean _valid;

   // 用户
   @AProperty
   protected String _user;

   // 间隔
   @AProperty
   protected int _interval;

   //============================================================
   // <T>构造开发编译器。</T>
   //============================================================
   public FDevelopBuilder(){
   }

   //============================================================
   // <T>初始化处理。</T>
   //============================================================
   @Override
   public void initialize(){
   }

   //============================================================
   // <T>获得有效性。</T>
   //
   // @return 有效性
   //============================================================
   @Override
   public boolean isValid(){
      return _valid;
   }

   //============================================================
   // <T>获得间隔。</T>
   //
   // @return 间隔
   //============================================================
   @Override
   public int interval(){
      return _interval;
   }

   //============================================================
   // <T>执行命令逻辑。</T>
   //
   // @return 处理结果
   //============================================================
   public String executeCommand(FDevelopBuilderCommand command){
      _logger.debug(this, "commandClear", "Command clear.");
      // 成命令
      String user = RString.nvl(command.user(), _user);
      String path = command.path();
      String batch = command.command();
      // 执行处理
      FProcess process = new FProcess("sh", true);
      process.appendLine("su - " + user);
      process.appendLine("cd " + path);
      process.appendLine(batch);
      process.start();
      // 处理结果
      return process.fetchResult();
   }

   //============================================================
   // <T>执行监视器的逻辑。</T>
   //
   // @return 处理结果
   //============================================================
   public abstract boolean execute();

   //============================================================
   // <T>执行处理。</T>
   //
   // @return 处理结果
   //============================================================
   @Override
   public boolean process(){
      _logger.debug(this, "process", "Process logic. (user={1})", _user);
      // 执行处理
      return execute();
   }
}
