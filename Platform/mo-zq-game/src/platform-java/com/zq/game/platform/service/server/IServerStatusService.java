package com.zq.game.platform.service.server;

import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

//============================================================
// <T>服务器状态接口。</T>
//============================================================
public interface IServerStatusService
{
   //============================================================
   // <T>获得服务器状态服务。</T>
   //
   // @param context 网页环境
   // @param input 输入内容
   // @param output 输出内容
   //============================================================
   void process(IWebContext context,
                IWebInput input,
                IWebOutput output);

   //============================================================
   // <T>启动处理。</T>
   //
   // @param context 网页环境
   // @param input 输入内容
   // @param output 输出内容
   //============================================================
   void processStart(IWebContext context,
                     IWebInput input,
                     IWebOutput output);

   //============================================================
   // <T>停止处理。</T>
   //
   // @param context 网页环境
   // @param input 输入内容
   // @param output 输出内容
   //============================================================
   void processStop(IWebContext context,
                    IWebInput input,
                    IWebOutput output);

   //============================================================
   // <T>命令处理。</T>
   //
   // @param context 网页环境
   // @param input 输入内容
   // @param output 输出内容
   //============================================================
   void execute(IWebContext context,
                IWebInput input,
                IWebOutput output);
   //============================================================
   // <T>新服自动发布。</T>
   //
   // @param context 网页环境
   // @param input 输入内容
   // @param output 输出内容
   //============================================================
   void serverDeployment(IWebContext context,
                         IWebInput input,
                         IWebOutput output);
   
   //============================================================
   // <T>创建用户。</T>
   //
   // @param context 网页环境
   // @param input 输入内容
   // @param output 输出内容
   //============================================================
   void createUser(IWebContext context,
                   IWebInput input,
                   IWebOutput output);
   
   //============================================================
   // <T>更新版本。</T>
   //
   // @param context 网页环境
   // @param input 输入内容
   // @param output 输出内容
   //============================================================
   void updateVersion(IWebContext context,
                          IWebInput input,
                          IWebOutput output);
   
   //============================================================
   // <T>修改配置文件。</T>
   //
   // @param context 网页环境
   // @param input 输入内容
   // @param output 输出内容
   //============================================================
   void changeConfig(IWebContext context,
                     IWebInput input,
                     IWebOutput output);
   
   //============================================================
   // <T>执行sql处理。</T>
   //
   // @param context 网页环境
   // @param input 输入内容
   // @param output 输出内容
   //============================================================
   void executeSqlDispose(IWebContext context,
                          IWebInput input,
                          IWebOutput output);
   
   //============================================================
   // <T>删除一周以前的日志文件。</T>
   //
   // @param context 网页环境
   // @param input 输入内容
   // @param output 输出内容
   //============================================================
   void delWeekLog(IWebContext context,
                   IWebInput input,
                   IWebOutput output);
   
   //============================================================
   // <T>执行sql补丁。</T>
   //
   // @param context 网页环境
   // @param input 输入内容
   // @param output 输出内容
   //============================================================
   void executeSqlPatch(IWebContext context,
                        IWebInput input,
                        IWebOutput output);
}
