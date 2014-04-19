package com.zq.platform.builder;

import org.mo.com.lang.FString;
import org.mo.com.lang.RInteger;
import org.mo.com.lang.RString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.core.aop.face.AProperty;

//============================================================
// <T>开发游戏构建器。</T>
//============================================================
public class FDevelopGameBuilder
      extends FDevelopBuilder
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(FDevelopGameBuilder.class);

   // 更新命令
   @AProperty
   protected FDevelopBuilderCommand _commandUpdate;

   // 清除命令
   @AProperty
   protected FDevelopBuilderCommand _commandClear;

   // 构建命令
   @AProperty
   protected FDevelopBuilderCommand _commandBuild;

   // 上个版本
   protected int _versionPrior;

   // 当前版本
   protected int _versionCurrent;

   // 输出内容
   protected FString _data = new FString();

   //============================================================
   // <T>构造服务监视。</T>
   //============================================================
   public FDevelopGameBuilder(){
   }

   //============================================================
   // <T>命令更新。</T>
   //
   // @return 处理结果
   //============================================================
   public boolean commandUpdate(){
      // 执行命令
      String result = executeCommand(_commandUpdate);
      // 处理结果
      String[] lines = RString.split(result, '\n');
      for(String line : lines){
         if(RString.isNotEmpty(line)){
            if(line.startsWith("A ")){
               // 新建处理
            }else if(line.startsWith("U ")){
               // 更新处理
            }else if(line.startsWith("D ")){
               // 删除处理
            }else{
               if(line.contains("版本 ")){
                  _versionCurrent = RInteger.parse(RString.mid(line, "版本 ", "。"));
               }
            }
         }
      }
      _logger.info(this, "commandUpdate", "Command update. (current={1}, prior={2})", _versionCurrent, _versionPrior);
      // 追加信息
      _data.appendRepeat("=", 60);
      _data.append('\n');
      _data.append(result);
      // 检查版本是否变更
      return (_versionPrior != _versionCurrent);
   }

   //============================================================
   // <T>命令清空。</T>
   //
   // @return 处理结果
   //============================================================
   public boolean commandClear(){
      _logger.info(this, "commandClear", "Command clear.");
      // 执行命令
      String result = executeCommand(_commandClear);
      // 追加信息
      _data.appendRepeat("=", 60);
      _data.append('\n');
      _data.append(result);
      return true;
   }

   //============================================================
   // <T>命令构建。</T>
   //
   // @return 处理结果
   //============================================================
   public boolean commandBuild(){
      _logger.info(this, "commandBuild", "Command build.");
      // 执行命令
      String result = executeCommand(_commandBuild);
      // 追加信息
      _data.appendRepeat("=", 60);
      _data.append('\n');
      _data.append(result);
      return true;
   }

   //============================================================
   // <T>执行监视器的逻辑。</T>
   //
   // @return 处理结果
   //============================================================
   @Override
   public boolean execute(){
      _data.clear();
      boolean changed = commandUpdate();
      if(changed){
         // 重新编译
         commandClear();
         commandBuild();
         // 更新版本
         _versionPrior = _versionCurrent;
      }
      return false;
   }
}
