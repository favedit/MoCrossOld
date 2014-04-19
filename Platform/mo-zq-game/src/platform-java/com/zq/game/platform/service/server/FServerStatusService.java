package com.zq.game.platform.service.server;


import java.io.File;
import java.util.Date;

import org.mo.com.console.FService;
import org.mo.com.lang.RString;
import org.mo.com.lang.type.TDateTime;
import org.mo.com.system.FProcess;
import org.mo.com.xml.FXmlNode;
import org.mo.logic.status.process.SLinuxCpuInfo;
import org.mo.logic.status.process.SLinuxDiskInfo;
import org.mo.logic.status.process.SLinuxServerInfo;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;


//============================================================
// <T>服务器状态服务。</T>
//============================================================
public class FServerStatusService
      extends FService
      implements
         IServerStatusService
{
   // 服务器部署
   protected FServerDeployment deployment = new FServerDeployment();
   // 时间间隔 7天
   protected int _truncateSpan = 1000 * 60 * 60 * 24 * 7;
   //============================================================
   // <T>获得服务器状态服务。</T>
   //
   // @param context 网页环境
   // @param input 输入内容
   // @param output 输出内容
   //============================================================
   @Override
   public void process(IWebContext context,
                       IWebInput input,
                       IWebOutput output){
     FServerDeployment process = new FServerDeployment();
      // 获得信息
      FXmlNode xsearch = input.config().findNode("Search");
      String user = xsearch.get("user", null);
      String search = null;
      if(xsearch != null){
         search = xsearch.text();
      }
      SLinuxServerInfo serverInfo = new SLinuxServerInfo();
      SLinuxDiskInfo diskInfo = new SLinuxDiskInfo();
      SLinuxCpuInfo cpuInfo = new SLinuxCpuInfo();
      //............................................................
      // 执行进程处理
      // 获取输出内容
      String systemResult = process.serverProcess("cat /proc/meminfo | grep -E 'Mem|Swap'", null);
      // 解析信息
      serverInfo.parseSystemInfos(systemResult);
      FXmlNode xsystem = output.config().createNode("System");
      serverInfo.saveSystemConfig(xsystem);
      //............................................................
      // 进程查找信息
      if(!RString.isEmpty(search)){
         String grep = "grep " + search;
         // 执行进程处理
         // 获取输出内容
         String processResult = process.serverProcess("ps aux | grep "+user+" | "+grep, null);
         // 解析信息
         serverInfo.parseProcessInfos(processResult, grep);
         FXmlNode xprocess = output.config().createNode("Processes");
         serverInfo.saveProcessConfig(xprocess);
      }
      //............................................................
      // 获取输出内容linux的输出结果
      String diskResult = process.serverProcess("df -l", null);
      // 解析硬盘信息
      diskInfo.parsediskinfo(diskResult);
      FXmlNode xdisk = output.config().createNode("diskInfos");
      diskInfo.saveDiskConfig(xdisk);
      
      //............................................................
      // 获取linux的cpu信息
      String cpuResult = process.serverProcess("mpstat -P ALL", null);
      // 解析cpu信息
      cpuInfo.parseCpuInfo(cpuResult);
      FXmlNode xcpu = output.config().createNode("cpuInfos");
      cpuInfo.saveCpuConfig(xcpu);
      //............................................................
      // http信息查找
      String httpResult = process.serverProcess("/etc/init.d/httpd status", null);
      serverInfo.parseHttp(httpResult);
      FXmlNode xhttp = output.config().createNode("httpInfo");
      serverInfo.saveHttpConfig(xhttp);
      
   }

   //============================================================
   // <T>服务器，启动处理。</T>
   //
   // @param context 网页环境
   // @param input 输入内容
   // @param output 输出内容
   //============================================================
   @Override
   public void processStart(IWebContext context,
                            IWebInput input,
                            IWebOutput output){
      // 获得信息
      FXmlNode xinput = input.config();
      FXmlNode xprocesses = xinput.findNode("Processes");
      if(xprocesses != null){
         for(FXmlNode xprocess : xprocesses){
            // 启动进程处理
            String processUser = xprocess.get("user", null);
            boolean optionWait = xprocess.getBoolean("option_wait", false);
            String processCommand = xprocess.findNode("Command").text();
            if(!RString.isEmpty(processCommand)){
               FProcess systemProcess = new FProcess();
               systemProcess.setOptionBackground(true);
               systemProcess.setOptionWait(optionWait);
               systemProcess.setCommand("sh");
               systemProcess.addParameter("-c");
               String command = null;
               if(optionWait){
                  command = processCommand;
               }else{
                  command = "setsid " + processCommand + " > /dev/null 2>&1 &";
               }
               if(!RString.isEmpty(processUser)){
                  command = "su - " + processUser + " -c \"" + command + "\"";
               }
               System.out.println(command+"-----------------------------");
               systemProcess.addParameter(command);
               systemProcess.start();
            }
         }
      }
   }

   //============================================================
   // <T>服务器，停止处理。</T>
   //
   // @param context 网页环境
   // @param input 输入内容
   // @param output 输出内容
   //============================================================
   @Override
   public void processStop(IWebContext context,
                           IWebInput input,
                           IWebOutput output){
      // 获得信息
      FXmlNode xinput = input.config();
      FXmlNode xprocesses = xinput.findNode("Processes");
      //............................................................
      if(xprocesses != null){
         for(FXmlNode xprocess : xprocesses.nodes()){
            // 杀死进程处理
            int processCode = xprocess.getInt("code", 0);
            String processUser = xprocess.get("user", null);
            String processType = xprocess.get("type", "-9");
            if(processCode > 0){
               FProcess systemProcess = new FProcess();
               systemProcess.setOptionWait(true);
               systemProcess.setCommand("sh");
               systemProcess.addParameter("-c");
               String command = "kill " + processType + " " + processCode;
               if(!RString.isEmpty(processUser)){
                  command = "su - " + processUser + " -c \"" + command + "\"";
               }
               System.out.println(command+"--------------------------------------");
               systemProcess.addParameter(command);
               systemProcess.start();
            }
         }
      }
   }

   //============================================================
   // <T>命令处理。</T>
   //
   // @param context 网页环境
   // @param input 输入内容
   // @param output 输出内容
   //============================================================
   @Override
   public void execute(IWebContext context,
                       IWebInput input,
                       IWebOutput output){
      // 获得信息
      FXmlNode xinput = input.config();
      FXmlNode xexecute = xinput.findNode("Execute");
      FXmlNode xoutput = output.config();
      FXmlNode xoutputExecute = xoutput.createNode("Execute");
      //............................................................
      if(xexecute != null){
         for(FXmlNode xcommand : xexecute.nodes()){
            if(xcommand.isName("Command")){
               // 杀死进程处理
               String processUser = xcommand.get("user", null);
               String command = xcommand.text();
               // 执行处理
               FProcess systemProcess = new FProcess();
               systemProcess.setOptionWait(true);
               systemProcess.setCommand("sh");
               systemProcess.addParameter("-c");
               String parameterCommand = "su - " + processUser + " -c \"" + command + "\"";
               systemProcess.addParameter(parameterCommand);
               systemProcess.start();
               // 获取输出内容
               String processResult = systemProcess.fetchResult();
               // 解析信息
               FXmlNode xoutputCommand = xoutputExecute.createNode("Command");
               xoutputCommand.setText(processResult);
            }
         }
      }
   }
   
   //============================================================
   // <T>新服发布。</T>
   //
   // @param context 网页环境
   // @param input 输入内容
   // @param output 输出内容
   //============================================================
   @Override
   public void serverDeployment(IWebContext context,
                                IWebInput input,
                                IWebOutput output){
      // 获得信息
      FXmlNode xinput = input.config();
      FXmlNode xexecute = xinput.findNode("Execute");
      // 输出结果
      FXmlNode xoutput = output.config();
      //FXmlNode xoutputExecute = xoutput.createNode("Execute");
      FXmlNode xmessageList = xoutput.createNode("MlessageList");
      xmessageList.set("status", "succeed/fail");
      //............................................................
      if(xexecute != null){
         FXmlNode xcommand = xexecute.findNode("Command");
          if(xcommand.isName("Command")){
         
         //添加用户
         deployment.serverCreateUser(xcommand,xmessageList);
         
         //新建目录
         deployment.makeDirs(xcommand,xmessageList);
         
         //从指定服务器获取资源
         deployment.secureResources(xcommand,xmessageList);
         
         //解压资源到指定路径
         deployment.extractingResources(xcommand,xmessageList);
         
         //更改权限
         deployment.changePermissions(xcommand, xmessageList);
         
         //设置对外端口号
         deployment.changeConfig(xcommand, null,xmessageList);
         //设置数据库连接
        //deployment.changeConfig(xcommand, null,xmessageList);
         //设置内部端口
        FXmlNode xserverConfig = xexecute.findNode("ServerConfig");
        for(FXmlNode node : xserverConfig){
            String nameVal = node.get("course_name")+".Server.Transfer";
            xserverConfig.set("host", node.get("host"));
            xserverConfig.set("port",node.get("port"));
            xserverConfig.set("user", xcommand.get("user", null));
            xserverConfig.set("project_name",xcommand.get("project_name", null));
            if(nameVal != null){
               deployment.changeInsideConfig(xserverConfig, nameVal,xmessageList);
            }
         }
         //修改数据表的主键
         deployment.changeTableMajorKey(xcommand, xmessageList);
           //创建数据库
           deployment.createDatabases(xcommand, xmessageList);
          }
      }
   }

   //============================================================
   // <T>创建用户。</T>
   //
   // @param context 网页环境
   // @param input 输入内容
   // @param output 输出内容
   //============================================================
   public void createUser(IWebContext context,
                          IWebInput input,
                          IWebOutput output){
      // 获得信息
      FXmlNode xinput = input.config();
      FXmlNode xexecute = xinput.findNode("Execute");
      // 输出结果
      FXmlNode xoutput = output.config();
      FXmlNode xoutputExecute = xoutput.createNode("Execute");
      FXmlNode xmessageList = xoutputExecute.createNode("MessageList");
      xmessageList.set("status", "succeed/fail");
      if(xexecute != null){
          FXmlNode xcommand = xexecute.findNode("Command");
          if(xcommand.isName("Command")){
              //添加用户
              deployment.serverCreateUser(xcommand,xmessageList);
              //新建目录
              deployment.makeDirs(xcommand,xmessageList);
          }
      }
   }

   //============================================================
   // <T>更新版本。</T>
   //
   // @param context 网页环境
   // @param input 输入内容
   // @param output 输出内容
   //============================================================
   @Override
   public void updateVersion(IWebContext context, 
                             IWebInput input,
                             IWebOutput output) {
      // 获得信息
      FXmlNode xinput = input.config();
      FXmlNode xexecute = xinput.findNode("Execute");
      // 输出结果
      FXmlNode xoutput = output.config();
      FXmlNode xoutputExecute = xoutput.createNode("Execute");
      FXmlNode xmessageList = xoutputExecute.createNode("MessageList");
      xmessageList.set("status", "succeed/fail");
      if(xexecute != null){
          FXmlNode xcommand = xexecute.findNode("Command");
          if(xcommand.isName("Command")){
             
           //从指定服务器获取资源
           deployment.secureResources(xcommand,xmessageList);
            
           //解压资源到指定路径
          deployment.extractingResources(xcommand,xmessageList);
            
           //更改权限
           deployment.changePermissions(xcommand, xmessageList);
          }
      }
   }
   
   //============================================================
   // <T>修改配置文件。</T>
   //
   // @param context 网页环境
   // @param input 输入内容
   // @param output 输出内容
   //============================================================
   @Override
   public void changeConfig(IWebContext context, 
                            IWebInput input, 
                            IWebOutput output) {
      // 获得信息
      FXmlNode xinput = input.config();
      FXmlNode xexecute = xinput.findNode("Execute");
      // 输出结果
      FXmlNode xoutput = output.config();
      FXmlNode xoutputExecute = xoutput.createNode("Execute");
      FXmlNode xmessageList = xoutputExecute.createNode("MessageList");
      xmessageList.set("status", "succeed/fail");
      if(xexecute != null){
          FXmlNode xcommand = xexecute.findNode("Command");
          if(xcommand.isName("Command")){
             //设置对外端口号&数据库连接
           deployment.changeConfig(xcommand, null,xmessageList);
            //设置内部端口
           FXmlNode xserverConfig = xexecute.findNode("ServerConfig");
           for(FXmlNode node : xserverConfig){
               String nameVal = node.get("course_name")+".Server.Transfer";
               xserverConfig.set("host", node.get("host"));
               xserverConfig.set("port",node.get("port"));
               xserverConfig.set("user", xcommand.get("user", null));
               xserverConfig.set("project_name",xcommand.get("project_name", null));
               if(nameVal != null){
                  deployment.changeInsideConfig(xserverConfig, nameVal,xmessageList);
               }
            }
            //修改数据表的主键
            deployment.changeTableMajorKey(xcommand, xmessageList);
          }
      }
   }
   
   //============================================================
   // <T>执行sql处理。</T>
   //
   // @param context 网页环境
   // @param input 输入内容
   // @param output 输出内容
   //============================================================
   @Override
   public void executeSqlDispose(IWebContext context, 
                                 IWebInput input,
                                 IWebOutput output) {
      // 获得信息
      FXmlNode xinput = input.config();
      FXmlNode xexecute = xinput.findNode("Execute");
      // 输出结果
      FXmlNode xoutput = output.config();
      FXmlNode xoutputExecute = xoutput.createNode("Execute");
      FXmlNode xmessageList = xoutputExecute.createNode("MessageList");
      xmessageList.set("status", "succeed/fail");
      if(xexecute != null){
         FXmlNode xcommand = xexecute.findNode("Command");
         if(xcommand.isName("Command")){
           //创建数据库
           deployment.createDatabases(xcommand, xmessageList);
         }
      }
   }
   
   //============================================================
   // <T>删除一周以前的日志文件。</T>
   //
   // @param context 网页环境
   // @param input 输入内容
   // @param output 输出内容
   //============================================================
   @Override
   public void delWeekLog(IWebContext context, 
                          IWebInput input,
                          IWebOutput output) {
      // 当前时间减7天
      TDateTime recordDate = new TDateTime(new Date());
      recordDate.truncate(-_truncateSpan);
      
      FXmlNode xinput = input.config();
      FXmlNode xexecute = xinput.findNode("Execute");
      // 获取路径
      String path = "/data/home/"+xexecute.get("user",null)+"/WP-Server/Logger/";
      File file = new File(path);
      File[] files = file.listFiles();
      for(int i = 0; i < files.length; i++){
         if(files[i].isFile()){
            String filename = files[i].getName();
            String subfile = filename.substring(filename.lastIndexOf("_")-6,filename.lastIndexOf("_"));
            if(subfile.equals(recordDate.format("yyMMdd"))){
               files[i].delete();
            }
         }
      }
   }
   
   //============================================================
   // <T>执行sql补丁。</T>
   //
   // @param context 网页环境
   // @param input 输入内容
   // @param output 输出内容
   //============================================================
   @Override
   public void executeSqlPatch(IWebContext context,
                               IWebInput input,
                               IWebOutput output){
      // 获得信息
      FXmlNode xinput = input.config();
      FXmlNode xexecute = xinput.findNode("Execute");
      FXmlNode xoutput = output.config();
      FXmlNode xoutputExecute = xoutput.createNode("Execute");
      if(xexecute != null){
         FXmlNode xcommand = xexecute.findNode("Command");
         if(xcommand.isName("Command")){
            String sqlPatch = null;
            try {
               FProcess systemProcess = new FProcess();
               systemProcess.setOptionWait(true);
               systemProcess.setCommand("sh");
               systemProcess.addParameter("-c");
               // systemProcess.addParameter("su - root");
               //systemProcess.appendLine("service mysqld start");
               systemProcess.addParameter("su - "+xcommand.get("user",null));
               systemProcess.appendLine("mysql -ugame -pgame --default-character-set=utf8");
               systemProcess.appendLine("use game;");
               systemProcess.appendLine("source ~/"+xcommand.get("project_name", null)+"/Bin/sql/gm_shop_item_tz.sql;");
               systemProcess.start();
               sqlPatch = "升级补丁成功";
            } catch (Exception e) {
               sqlPatch = "升级补丁失败，"+e;
            }
            FXmlNode xoutputExtracting = xoutputExecute.createNode("Message",sqlPatch);
            xoutputExtracting.set("name", "sqlPatch");
         }
      }
   }
   
}
