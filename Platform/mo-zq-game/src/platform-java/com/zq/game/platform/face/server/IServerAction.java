package com.zq.game.platform.face.server;

import org.mo.web.core.container.AContainer;
import org.mo.web.protocol.context.IWebContext;

//============================================================
// <T>服务器命令接口。</T>
//============================================================
public interface IServerAction
{
   //============================================================
   // <T>列出目录处理。</T>
   //
   // @param context 环境
   // @param page 容器
   // @return 处理结果
   //============================================================
   String catalog(IWebContext context,
                  @AContainer(name = "page") FServerContainer page);

   //============================================================
   // <T>显示服务器状态。</T>
   //
   // @param context 环境
   // @param page 容器
   // @return 处理结果
   //============================================================
   String showServer(IWebContext context,
                     @AContainer(name = "page") FServerContainer page);

   //============================================================
   // <T>显示进程状态。</T>
   //
   // @param context 环境
   // @param page 容器
   // @return 处理结果
   //============================================================
   String showProcess(IWebContext context,
                      @AContainer(name = "page") FServerContainer page);

   //============================================================
   // <T>启动进程运行。</T>
   //
   // @param context 环境
   // @param page 容器
   // @return 处理结果
   //============================================================
   String startProcess(IWebContext context,
                       @AContainer(name = "page") FServerContainer page);

   //============================================================
   // <T>停止进程运行。</T>
   //
   // @param context 环境
   // @param page 容器
   // @return 处理结果
   //============================================================
   String stopProcess(IWebContext context,
                      @AContainer(name = "page") FServerContainer page);

   //============================================================
   // <T>编译更新。</T>
   //
   // @param context 环境
   // @param page 容器
   // @return 处理结果
   //============================================================
   String compilerUpdate(IWebContext context,
                         @AContainer(name = "page") FServerContainer page);

   //============================================================
   // <T>编译代码。</T>
   //
   // @param context 环境
   // @param page 容器
   // @return 处理结果
   //============================================================
   String compilerCode(IWebContext context,
                       @AContainer(name = "page") FServerContainer page);

   //============================================================
   // <T>编译清空。</T>
   //
   // @param context 环境
   // @param page 容器
   // @return 处理结果
   //============================================================
   String compilerClear(IWebContext context,
                        @AContainer(name = "page") FServerContainer page);

   //============================================================
   // <T>编译建立。</T>
   //
   // @param context 环境
   // @param page 容器
   // @return 处理结果
   //============================================================
   String compilerBuild(IWebContext context,
                        @AContainer(name = "page") FServerContainer page);
   
   //============================================================
   // <T>服务器部署。</T>
   //
   // @param context 环境
   // @param page 容器
   // @return 处理结果
   //============================================================
   String serverDeployment(IWebContext context,
                        @AContainer(name = "page") FServerContainer page);
   
   //============================================================
   // <T>服务器部署。</T>
   //
   // @param context 环境
   // @param page 容器
   // @return 处理结果
   //============================================================
   String createUser(IWebContext context,
                        @AContainer(name = "page") FServerContainer page);
   
   //============================================================
   // <T>解压版本。</T>
   //
   // @param context 环境
   // @param page 容器
   // @return 处理结果
   //============================================================
   String extractingVersion(IWebContext context,
                        @AContainer(name = "page") FServerContainer page);
   
   //============================================================
   // <T>修改配置文件。</T>
   //
   // @param context 环境
   // @param page 容器
   // @return 处理结果
   //============================================================
   String changeConfig(IWebContext context,
                        @AContainer(name = "page") FServerContainer page);
   
   //============================================================
   // <T>执行sql处理。</T>
   //
   // @param context 环境
   // @param page 容器
   // @return 处理结果
   //============================================================
   String executeSqlDispose(IWebContext context,
                        @AContainer(name = "page") FServerContainer page);
   
   //============================================================
   // <T>更新版本。</T>
   //
   // @param context 环境
   // @param page 容器
   // @return 处理结果
   //============================================================
   String updateVersion(IWebContext context,
                        @AContainer(name = "page") FServerContainer page);
   
   //============================================================
   // <T>清除服务器进程缓存。</T>
   //
   // @param context 环境
   // @param page 容器
   // @return 处理结果
   //============================================================
   String cacheClear(IWebContext context,
                        @AContainer(name = "page") FServerContainer page);
   
   //============================================================
   // <T>执行sql补丁。</T>
   //
   // @param context 环境
   // @param page 容器
   // @return 处理结果
   //============================================================
   String executeSqlPatch(IWebContext context,
                        @AContainer(name = "page") FServerContainer page);
}
