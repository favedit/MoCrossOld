package com.zq.game.platform.face.server;

import com.zq.game.face.IGamePage;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RDouble;
import org.mo.com.lang.RLong;
import org.mo.com.lang.RString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.net.http.FHttpService;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectAction;
import org.mo.platform.server.IServerConsole;
import org.mo.platform.server.common.XProcess;
import org.mo.platform.server.common.XServer;
import org.mo.platform.server.monitor.FServerMonitor;
import org.mo.web.core.container.AContainer;
import org.mo.web.protocol.context.IWebContext;

import sun.org.mozilla.javascript.internal.regexp.SubString;

//============================================================
// <T>服务器命令。</T>
//============================================================
public class FServerAction
      extends FAbsXmlObjectAction<XServer>
      implements
         IServerAction
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(FServerAction.class);
   
   // 页面定义
   public final static String PAGE_CATALOG = "Catalog";
   
   // 服务器控制台
   @ALink
   protected IServerConsole _serverConsole;

   //============================================================
   // <T>查找处理节点。</T>
   //
   // @param xconfig 配置节点
   // @param serverPath 服务器路径
   // @param user 用户
   // @param flag 标志
   // @return 节点
   //============================================================
   protected FXmlNode findProcessNode(FXmlNode xconfig,
                                      String serverPath,
                                      String user,
                                      String flag){
      // 参数处理
      if(serverPath.startsWith("~")){
         serverPath = serverPath.substring(1);
      }
      flag = "/" + flag;
      // 查找结点
      for(FXmlNode xnode : xconfig.nodes()){
         // 判断名称
         String findUser = xnode.get("user", null);
         if(!RString.isEmpty(user)){
            if(!user.equals(findUser)){
               continue;
            }
         }
         // 判断命令
         String findCommand = xnode.get("command");
         if(!RString.isEmpty(serverPath)){
            if(!findCommand.contains(serverPath)){
               continue;
            }
         }
         if(!findCommand.contains(flag)){
            continue;
         }
         return xnode;
      }
      return null;
   }

   //============================================================
   // <T>列出目录处理。</T>
   //
   // @param context 环境
   // @param page 容器
   // @return 处理结果
   //============================================================
   @Override
   public String catalog(IWebContext context,
                         FServerContainer page){
      return PAGE_CATALOG;
   }
   
   //============================================================
   // <T>显示服务器状态。</T>
   //
   // @param context 环境
   // @param page 容器
   // @return 处理结果
   //============================================================
   @Override
   public String showServer(IWebContext context,
                            FServerContainer page){
     //192_168_2_50_test.test.work
      String serverName = context.parameter("sel_collection");
      XServer xserver = _serverConsole.get(serverName);
      // 生成地址
      String url = "http://" + xserver.getHost() + ":" + xserver.getPort() + "/zqinet.game.server.status.ws";
      
      // 生成查询
      int index = 0;
      String search = "-E \"";
      for(IXmlObject xobject : xserver.children()){
         if(XProcess.isInstance(xobject)){
            XProcess xprocess = (XProcess)xobject;
            //System.out.println(xprocess.getLabel());
            String flag = xprocess.getFlag();
            if(!RString.isEmpty(flag)){
               if(index > 0){
                  search += "|";
               }
               search += flag;
               index++;
            }
         }
      }
      search += "\"";
      // 获得同步器控制台
      FHttpService service = new FHttpService();
      FXmlNode xsearch = service.inputNode().createNode("Search");
      xsearch.set("user",xserver.getUser());
      xsearch.setText(search);
      service.load(url);//发送&获取
      FXmlNode xoutput = service.outputNode();
      //已经拿到服务器的详细信息
      FXmlNode xsystem = xoutput.findNode("System");
      xsystem.set("label", xserver.getLabel());
      xsystem.set("host", xserver.getHost());
      long memoryTotal = xsystem.getLong("memory_total");
      long memoryFree = xsystem.getLong("memory_free");
      long swapTotal = xsystem.getLong("swap_total");
      long swapFree = xsystem.getLong("swap_free");
      xsystem.set("memory_total_label", RLong.formatMemoryPad(memoryTotal));
      xsystem.set("memory_use_label", RLong.formatMemoryPad(memoryTotal - memoryFree));
      xsystem.set("memory_free_label", RLong.formatMemoryPad(memoryFree));
      xsystem.set("swap_total_label", RLong.formatMemoryPad(swapTotal));
      xsystem.set("swap_use_label", RLong.formatMemoryPad(swapTotal - swapFree));
      xsystem.set("swap_free_label", RLong.formatMemoryPad(swapFree));
      //得到物理内存使用率
      xsystem.set("memory_totle_percent_label",RDouble.calculatePercent(memoryFree, memoryTotal));
      //得到交换内存使用率
      xsystem.set("swap_totle_percent_label",RDouble.calculatePercent(swapFree, swapTotal));
      //............................................................
      //生成硬盘使用信息
      FXmlNode xdiskInfos = xoutput.findNode("diskInfos");
      FXmlNode xdisks = new FXmlNode();
      for(FXmlNode node : xdiskInfos.nodes()){
         FXmlNode diskinfo = xdisks.createNode();
         long diskSize = node.getLong("disk_size");
         long diskUsed = node.getLong("disk_used");
         long diskAvail = node.getLong("disk_avail");
         double diskUsePercent = RDouble.parse(node.getLong("disk_usePercent"));
         diskinfo.set("file_system", node.get("file_system"));
         diskinfo.set("disk_size", RLong.formatMemoryPad(diskSize, 'k'));
         diskinfo.set("disk_used", RLong.formatMemory(diskUsed, 'k'));
         diskinfo.set("disk_avail", RLong.formatMemoryPad(diskAvail, 'k'));
         diskinfo.set("mounted_on", node.get("mounted_on"));
         diskinfo.set("disk_userate", diskUsePercent);
        }
      //............................................................
      //生成cpu使用信息
      FXmlNode xcpuInfos = xoutput.findNode("cpuInfos");
      FXmlNode xcpus = new FXmlNode();
      for(FXmlNode node : xcpuInfos.nodes()){
         FXmlNode xcpuinfo = xcpus.createNode();
         xcpuinfo.set("cpu_id",node.get("cpu_id"));
         xcpuinfo.set("cpu_used",node.get("cpu_used"));
         xcpuinfo.set("cpu_avail",node.get("cpu_avail"));
         
      }
      
      
      //............................................................
      // 生成服务信息
      FXmlNode xprocesses = xoutput.findNode("Processes");
      FXmlNode xprocessesInfo = new FXmlNode();
      String processStartCodes = null;
      String processStopCodes = null;
      String courseNames = null;
      int id = 0;
      long processesMemoryVirtual = 0;
      long processesMemoryTotal = 0;
      String serverPath = xserver.getPath();
      for(IXmlObject xobject : xserver.children()){
         if(XProcess.isInstance(xobject)){
            XProcess xprocess = (XProcess)xobject;
            String user = xprocess.getUser();
            FXmlNode xprocessInfo = xprocessesInfo.createNode();
            String processName = xprocess.getName();
            xprocessInfo.set("id", ++id);
            xprocessInfo.set("name", xprocess.getName());
            xprocessInfo.set("label", xprocess.getLabel());
            xprocessInfo.set("user", xprocess.getUser());
            xprocessInfo.set("option_wait", xprocess.getOptionWait());
            String courseName = xprocess.getName();
            if(RString.isEmpty(courseNames)){
               courseNames = courseName;
            }else{
               courseNames += "|" + courseName;   
            }
            //到服务器获取数据
            FXmlNode xprocessConfig = findProcessNode(xprocesses, serverPath, user, xprocess.getFlag());
            String processCode = null;
            if(xprocessConfig != null){
               long processMemoryVirtual = xprocessConfig.getLong("memory_virtual");
               processesMemoryVirtual += processMemoryVirtual;
               long processMemoryTotal = xprocessConfig.getLong("memory_total");
               processesMemoryTotal += processMemoryTotal;
               processCode = processName + ":" + xprocessConfig.get("process_id");
               xprocessInfo.set("status", "run");
               xprocessInfo.set("process_code", processCode);
               xprocessInfo.set("cpu_rate", xprocessConfig.get("cpu_rate") + "%");
               xprocessInfo.set("memory_rate", xprocessConfig.get("memory_rate") + "%");
               xprocessInfo.set("memory_virtual", RLong.formatMemoryPad(processMemoryVirtual, 'k'));
               xprocessInfo.set("memory_total", RLong.formatMemoryPad(processMemoryTotal, 'k'));
               if(RString.isEmpty(processStopCodes)){
                  processStopCodes = processCode;
               }else{
                  processStopCodes += "|" + processCode;
               }
            }else{
               processCode = xprocess.getName();
               xprocessInfo.set("status", "stop");
               if(RString.isEmpty(processStartCodes)){
                  processStartCodes = processCode;
               }else{
                  processStartCodes += "|" + processCode;
               }
            }
         }
      }
      xprocessesInfo.set("memory_virtual", RLong.formatMemoryPad(processesMemoryVirtual, 'k'));
      xprocessesInfo.set("memory_total", RLong.formatMemoryPad(processesMemoryTotal, 'k'));
      xprocessesInfo.set("start_codes", processStartCodes);
      xprocessesInfo.set("stop_codes", processStopCodes);
      xprocessesInfo.set("server_files",courseNames);
      page.setServerName(serverName);
      page.setSystemInfo(xsystem);
      page.setProcessesInfo(xprocessesInfo);
      page.setDisksInfo(xdisks);
      page.setCpusInfo(xcpus);
      return "ServerStatus";
   }
   
   //============================================================
   // <T>显示进程状态。</T>
   //
   // @param context 环境
   // @param page 容器
   // @return 处理结果
   //============================================================
   @Override
   public String showProcess(IWebContext context,
                             FServerContainer page){
      return "ProcessStatus";
   }

   //============================================================
   // <T>启动进程运行。</T>
   //
   // @param context 环境
   // @param page 容器
   // @return 处理结果
   //============================================================
   @Override
   public String startProcess(IWebContext context,
                              @AContainer(name = "page") FServerContainer page){
     
      String serverName = page.serverName();
      //根据develop.192_168_2_1.root.branch从配置文件中找到信息
      XServer xserver = _serverConsole.get(serverName);
      // 生成地址
      String url = "http://" + xserver.getHost() + ":" + xserver.getPort() + "/zqinet.game.server.status.ws";
      // 获得进程编号（服务器名称）
      String processCode = context.parameter("process_code");
      if(RString.isEmpty(processCode)){
         return null;
      }
      // 获得同步器控制台
      FHttpService service = new FHttpService();
      FXmlNode xprocesses = service.inputNode().createNode("Processes");
      String[] processItems = RString.split(processCode, '|');
      for(String processItem : processItems){
         XProcess process = (XProcess)xserver.search("name", processItem);
         if(process != null){
            FXmlNode xprocess = xprocesses.createNode("Process");
            xprocess.set("code", processItem);
            xprocess.set("user", process.getUser());
            xprocess.set("option_wait", process.getOptionWait());
            xprocess.createNode("Command").setText(process.getCommand());
         }
      }
      // 发送内容
      service.load(url, "processStart");
      return IGamePage.PROCESS_SUCCESS;
   }

   //============================================================
   // <T>停止进程运行。</T>
   //
   // @param context 环境
   // @param page 容器
   // @return 处理结果
   //============================================================
   @Override
   public String stopProcess(IWebContext context,
                             @AContainer(name = "page") FServerContainer page){
      // 获得服务器
      String serverName = page.serverName();
      XServer xserver = _serverConsole.get(serverName);
      if(xserver == null){
         throw new FFatalError("Server name is null");
      }
      // 获得进程编号
      String processCode = context.parameter("process_code");
      if(RString.isEmpty(processCode)){
         throw new FFatalError("Process code is null");
      }
      // 生成地址
      String url = "http://" + xserver.getHost() + ":" + xserver.getPort() + "/zqinet.game.server.status.ws";
      // 获得同步器控制台
      FHttpService service = new FHttpService();
      FXmlNode xprocesses = service.inputNode().createNode("Processes");
      String[] processItems = RString.split(processCode, '|');
      for(String processItem : processItems){
         String[] items = RString.splitTwo(processItem, ':');
         if(items.length == 2){
            XProcess process = (XProcess)xserver.search("name", items[0]);
            if(process != null){
               FXmlNode xprocess = xprocesses.createNode("Process");
               xprocess.set("code", items[1]);
               xprocess.set("user", process.getUser());
               String stopFlag = process.getStopFlag();
               if(!RString.isEmpty(stopFlag)){
                  xprocess.set("type", stopFlag);
               }
            }
         }
      }
      // 发送内容
      service.load(url, "processStop");
      return IGamePage.PROCESS_SUCCESS;
   }

   //============================================================
   // <T>编译更新。</T>
   //
   // @param context 环境
   // @param page 容器
   // @return 处理结果
   //============================================================
   @Override
   public String compilerUpdate(IWebContext context,
                                @AContainer(name = "page") FServerContainer page){
      // 获得服务器
      String serverName = page.serverName();
      XServer xserver = _serverConsole.get(serverName);
      if(xserver == null){
         throw new FFatalError("Server name is null");
      }
      String serverUser = xserver.getUser();
      String serverPath = xserver.getPath();
      String command = "svn up " + serverPath;
      // 生成地址
      String url = "http://" + xserver.getHost() + ":" + xserver.getPort() + "/zqinet.game.server.status.ws";
      // 获得同步器控制台
      FHttpService service = new FHttpService();
      FXmlNode xexecute = service.inputNode().createNode("Execute");
      FXmlNode xcommand = xexecute.createNode("Command");
      xcommand.set("user", serverUser);
      xcommand.setText(command);
      // 发送内容
      service.load(url, "execute");
      return IGamePage.PROCESS_SUCCESS;
   }

   //============================================================
   // <T>编译代码。</T>
   //
   // @param context 环境
   // @param page 容器
   // @return 处理结果
   //============================================================
   @Override
   public String compilerCode(IWebContext context,
                              @AContainer(name = "page") FServerContainer page){
      // 获得服务器
      String serverName = page.serverName();
      XServer xserver = _serverConsole.get(serverName);
      if(xserver == null){
         throw new FFatalError("Server name is null");
      }
      String serverUser = xserver.getUser();
      String serverPath = xserver.getPath();
      String command = serverPath + "/Config/mogm-source.sh";
      String url = "http://" + xserver.getHost() + ":" + xserver.getPort() + "/zqinet.game.server.status.ws";
      // 获得同步器控制台
      FHttpService service = new FHttpService();
      FXmlNode xexecute = service.inputNode().createNode("Execute");
      FXmlNode xcommand = xexecute.createNode("Command");
      xcommand.set("user", serverUser);
      xcommand.setText(command);
      // 发送内容
      service.load(url, "execute");
      return IGamePage.PROCESS_SUCCESS;
   }
   //============================================================
   // <T>编译清空。</T>
   //
   // @param context 环境
   // @param page 容器
   // @return 处理结果
   //============================================================
   @Override
   public String compilerClear(IWebContext context,
                               @AContainer(name = "page") FServerContainer page){
      // 获得服务器
      String serverName = page.serverName();
      XServer xserver = _serverConsole.get(serverName);
      if(xserver == null){
         throw new FFatalError("Server name is null");
      }
      String serverUser = xserver.getUser();
      String serverPath = xserver.getPath();
      String command = serverPath + "/Bin/pmc_project_clean_all.sh";
      String url = "http://" + xserver.getHost() + ":" + xserver.getPort() + "/zqinet.game.server.status.ws";
      // 获得同步器控制台
      FHttpService service = new FHttpService();
      FXmlNode xexecute = service.inputNode().createNode("Execute");
      FXmlNode xcommand = xexecute.createNode("Command");
      xcommand.set("user", serverUser);
      xcommand.setText(command);
      // 发送内容
      service.load(url, "execute");
      return IGamePage.PROCESS_SUCCESS;
   }

   //============================================================
   // <T>编译建立。</T>
   //
   // @param context 环境
   // @param page 容器
   // @return 处理结果
   //============================================================
   @Override
   public String compilerBuild(IWebContext context,
                               @AContainer(name = "page") FServerContainer page){
      // 获得服务器
      String serverName = page.serverName();
      XServer xserver = _serverConsole.get(serverName);
      if(xserver == null){
         throw new FFatalError("Server name is null");
      }
      String serverUser = xserver.getUser();
      String serverPath = xserver.getPath();
      // 生成命令
      String command = serverPath + "/Bin/pmd_project_make_distcc.sh";
      // 生成地址
      String url = "http://" + xserver.getHost() + ":" + xserver.getPort() + "/zqinet.game.server.status.ws";
      // 获得同步器控制台
      FHttpService service = new FHttpService();
      FXmlNode xexecute = service.inputNode().createNode("Execute");
      FXmlNode xcommand = xexecute.createNode("Command");
      xcommand.set("user", serverUser);
      xcommand.setText(command);
      // 发送内容
      service.load(url, "execute");
      return IGamePage.PROCESS_SUCCESS;
   }
   
   //============================================================
   // <T>新服自动发布</T>
   //
   // @param context 环境
   // @param page 容器
   // @return 处理结果
   //============================================================
   @Override
   public String serverDeployment(IWebContext context,
                               @AContainer(name = "page") FServerContainer page){
      String serverName = page.serverName();
      XServer xserver = _serverConsole.get(serverName);
      // 生成地址
      String url = "http://" + xserver.getHost() + ":" + xserver.getPort() + "/zqinet.game.server.status.ws";
      //得到用户名称
      FHttpService service = new FHttpService();
      FXmlNode xexecute = service.inputNode().createNode("Execute");
      FXmlNode xcommand = xexecute.createNode("Command");
      xcommand.set("user", xserver.getUser());
      xcommand.set("project_name", xserver.getPath().substring(xserver.getPath().indexOf('/')+1));
      xcommand.set("package_url", xserver.getPackageUrl());
      xcommand.set("package_name", xserver.getPackageName());
      xcommand.set("client_port", xserver.getClientPort());
      xcommand.set("passport", xserver.getDatabasePassport());
      xcommand.set("password", xserver.getDatabasePassword());
      xcommand.set("db_host", xserver.getDatabaseHost());
      xcommand.set("db_port", xserver.getDatabasePort());
      xcommand.set("server_id",xserver.getServerId());
      xcommand.set("project_path", xserver.getPath());
      
      System.out.println(xcommand.get("project_path"));
      // 获得进程文件名称
      String processCode = context.parameter("server_files");
      if(RString.isEmpty(processCode)){
          throw new FFatalError("Process code is null");
      }
      FXmlNode xserverConfig = xexecute.createNode("ServerConfig");
      String[] processItems = RString.split(processCode, '|');
      for(String processItem : processItems){
            XProcess process = (XProcess)xserver.search("name", processItem);
            if(process != null){
               FXmlNode xserverParameter = xserverConfig.createNode("Server");
               
               xserverParameter.set("course_name", processItem);
               
               //获取进程host
               xserverParameter.set("host", process.getHost());
               
               //获取进程端口
               xserverParameter.set("port", process.getPort());
            }
      }
      service.load(url, "serverDeployment");
      //输出linux返回的执行结果
      FXmlNode xoutput = service.outputNode();
      FXmlNode xMessage = new FXmlNode().createNode("Message");
      for(FXmlNode xConfig : xoutput){
         for(FXmlNode node : xConfig.nodes()){
            String name = node.text();
            xMessage.set(node.get("name"), name);
         }
      }
      page.setSystemInfo(xMessage);
      return IGamePage.PROCESS_SUCCESS;
   }
   
   //============================================================
   // <T>创建用户。</T>
   //
   // @param context 环境
   // @param page 容器
   // @return 处理结果
   //============================================================
   public String createUser(IWebContext context,
                            FServerContainer page){
      String serverName = page.serverName();
      XServer xserver = _serverConsole.get(serverName);
      // 生成地址
      String url = "http://" + xserver.getHost() + ":" + xserver.getPort() + "/zqinet.game.server.status.ws";
      // 得到相关信息
      FHttpService service = new FHttpService();
      FXmlNode xexecute = service.inputNode().createNode("Execute");
      FXmlNode xcommand = xexecute.createNode("Command");
      xcommand.set("user", xserver.getUser());
      xcommand.set("project_name", xserver.getPath().substring(xserver.getPath().indexOf('/')+1));
      // 发送请求
      service.load(url, "createUser");
      FXmlNode xoutput = service.outputNode();
      FXmlNode execute = xoutput.findNode("Execute");
      FXmlNode messagelist = execute.findNode("MessageList");
      for(FXmlNode node : messagelist){
         _logger.info(this, "createUser", "createuser succeed/fail. (name={1})", node.text());
      }
      return IGamePage.PROCESS_SUCCESS;
   }
   
   //============================================================
   // <T>解压版本。</T>
   //
   // @param context 环境
   // @param page 容器
   // @return 处理结果
   //============================================================
   @Override
   public String extractingVersion(IWebContext context, FServerContainer page) {
      String serverName = page.serverName();
      XServer xserver = _serverConsole.get(serverName);
      // 生成地址
      String url = "http://" + xserver.getHost() + ":" + xserver.getPort() + "/zqinet.game.server.status.ws";
      // 得到相关信息
      FHttpService service = new FHttpService();
      FXmlNode xexecute = service.inputNode().createNode("Execute");
      FXmlNode xcommand = xexecute.createNode("Command");
      xcommand.set("user", xserver.getUser());
      xcommand.set("package_url", xserver.getPackageUrl());
      xcommand.set("package_name", xserver.getPackageName());
      xcommand.set("project_name", xserver.getPath().substring(xserver.getPath().indexOf('/')+1));
      // 发送请求
      service.load(url, "updateVersion");
      return IGamePage.PROCESS_SUCCESS;
   }
   
   //============================================================
   // <T>修改配置文件。</T>
   //
   // @param context 环境
   // @param page 容器
   // @return 处理结果
   //============================================================
   @Override
   public String changeConfig(IWebContext context, FServerContainer page) {
      String serverName = page.serverName();
      XServer xserver = _serverConsole.get(serverName);
      // 生成地址
      String url = "http://" + xserver.getHost() + ":" + xserver.getPort() + "/zqinet.game.server.status.ws";
      //得到用户名称
      FHttpService service = new FHttpService();
      FXmlNode xexecute = service.inputNode().createNode("Execute");
      FXmlNode xcommand = xexecute.createNode("Command");
      xcommand.set("user", xserver.getUser());
      xcommand.set("project_name", xserver.getPath().substring(xserver.getPath().indexOf('/')+1));
      xcommand.set("client_port", xserver.getClientPort());
      xcommand.set("passport", xserver.getDatabasePassport());
      xcommand.set("password", xserver.getDatabasePassword());
      xcommand.set("db_host", xserver.getDatabaseHost());
      xcommand.set("db_port", xserver.getDatabasePort());
      xcommand.set("server_id",xserver.getServerId());
      
      String processCode = context.parameter("server_files");
      if(RString.isEmpty(processCode)){
          throw new FFatalError("Process code is null");
      }
      FXmlNode xserverConfig = xexecute.createNode("ServerConfig");
      System.out.println(xserverConfig);
      String[] processItems = RString.split(processCode, '|');
      for(String processItem : processItems){
            XProcess process = (XProcess)xserver.search("name", processItem);
            if(process != null){
               FXmlNode xserverParameter = xserverConfig.createNode("Server");
               xserverParameter.set("course_name", processItem);
               //获取进程host
               xserverParameter.set("host", process.getHost());
               //获取进程端口
               xserverParameter.set("port", process.getPort());
            }
      }
      //发送请求
      service.load(url, "changeConfig");
      return IGamePage.PROCESS_SUCCESS;
   }
   
   //============================================================
   // <T>执行sql处理。</T>
   //
   // @param context 环境
   // @param page 容器
   // @return 处理结果
   //============================================================
   @Override
   public String executeSqlDispose(IWebContext context, FServerContainer page) {
      String serverName = page.serverName();
      XServer xserver = _serverConsole.get(serverName);
      // 生成地址
      String url = "http://" + xserver.getHost() + ":" + xserver.getPort() + "/zqinet.game.server.status.ws";
      //得到相关信息
      FHttpService service = new FHttpService();
      FXmlNode xexecute = service.inputNode().createNode("Execute");
      FXmlNode xcommand = xexecute.createNode("Command");
      xcommand.set("user", xserver.getUser());
      xcommand.set("project_name", xserver.getPath().substring(xserver.getPath().indexOf('/')+1));
      //发送请求
      service.load(url, "executeSqlDispose");
      return IGamePage.PROCESS_SUCCESS;
   }
   
   //============================================================
   // <T>更新版本。</T>
   //
   // @param context 环境
   // @param page 容器
   // @return 处理结果
   //============================================================
   @Override
   public String updateVersion(IWebContext context, FServerContainer page) {
      String serverName = page.serverName();
      XServer xserver = _serverConsole.get(serverName);
      // 生成地址
      String url = "http://" + xserver.getHost() + ":" + xserver.getPort() + "/zqinet.game.server.status.ws";
      // 得到相关信息
      FHttpService service = new FHttpService();
      FXmlNode xexecute = service.inputNode().createNode("Execute");
      FXmlNode xcommand = xexecute.createNode("Command");
      xcommand.set("user", xserver.getUser());
      xcommand.set("package_url", xserver.getPackageUrl());
      xcommand.set("package_name", xserver.getPackageName());
      // 发送请求
      service.load(url, "updateVersion");
      return IGamePage.PROCESS_SUCCESS;
   }
   
   //============================================================
   // <T>清除服务器进程缓存。</T>
   //
   // @param context 环境
   // @param page 容器
   // @return 处理结果
   //============================================================
   public String cacheClear(IWebContext context,
                            FServerContainer page){
      // 获得服务器
      String serverName = page.serverName();
      XServer xserver = _serverConsole.get(serverName);
      if(xserver == null){
         throw new FFatalError("Server name is null");
      }
      String serverUser = xserver.getUser();
      String serverPath = xserver.getPath();
      String command = serverPath + "/Bin/prs_project_remove_share.sh";
      String url = "http://" + xserver.getHost() + ":" + xserver.getPort() + "/zqinet.game.server.status.ws";
      // 获得同步器控制台
      FHttpService service = new FHttpService();
      FXmlNode xexecute = service.inputNode().createNode("Execute");
      FXmlNode xcommand = xexecute.createNode("Command");
      xcommand.set("user", serverUser);
      xcommand.setText(command);
      // 发送内容
      service.load(url, "execute");
      
      return IGamePage.PROCESS_SUCCESS;
   }
   
   //============================================================
   // <T>执行sql补丁。</T>
   //
   // @param context 环境
   // @param page 容器
   // @return 处理结果
   //============================================================
   @Override
   public String executeSqlPatch(IWebContext context, FServerContainer page) {
      // 获得服务器
      String serverName = page.serverName();
      XServer xserver = _serverConsole.get(serverName);
      if(xserver == null){
         throw new FFatalError("Server name is null");
      }
      String serverUser = xserver.getUser();
      String serverPath = xserver.getPath();
      String command = serverPath + "/Bin/sql/gm_shop_item_tz.sql";
      String url = "http://" + xserver.getHost() + ":" + xserver.getPort() + "/zqinet.game.server.status.ws";
      // 获得同步器控制台
      FHttpService service = new FHttpService();
      FXmlNode xexecute = service.inputNode().createNode("Execute");
      FXmlNode xcommand = xexecute.createNode("Command");
      xcommand.set("user", serverUser);
      xcommand.setText(command);
      // 发送内容
      service.load(url, "executeSqlPatch");
      
      return IGamePage.PROCESS_SUCCESS;
   }
}
