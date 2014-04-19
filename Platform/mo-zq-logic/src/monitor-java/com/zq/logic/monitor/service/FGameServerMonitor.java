package com.zq.logic.monitor.service;

import com.zq.logic.monitor.service.game.SGameCheckServer;
import com.zq.logic.monitor.service.game.SGameProcessInfo;
import com.zq.logic.monitor.service.game.SGameServerInfo;

import com.zq.logic.monitor.console.FServerMonitor;
import org.mo.com.lang.FObjects;
import org.mo.com.lang.FStrings;
import org.mo.com.lang.RString;
import org.mo.com.lang.reflect.RClass;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.net.http.RHttp;
import org.mo.com.net.mail.FMail;
import org.mo.core.aop.face.ALink;
import org.mo.core.aop.face.AProperty;
import org.mo.eng.mail.IMailConsole;

//============================================================
// <T>游戏服务监视。</T>
//============================================================
public class FGameServerMonitor
      extends FServerMonitor{

   // 日志输出接口
   private static ILogger _logger = RLogger.find(FGameServerMonitor.class);

   // 邮件控制台
   @ALink
   protected IMailConsole _mailConsole;

   // 信息
   @AProperty
   protected String _info;

   // 处理间隔
   @AProperty
   protected String _url;

   // 服务器信息
   @AProperty
   protected String _servers;

   // 检查服务器信息
   protected FObjects<SGameCheckServer> _checkServers = new FObjects<SGameCheckServer>(SGameCheckServer.class);

   //============================================================
   // <T>设置处理。</T>
   //============================================================
   public void setup(){
      super.setup();
      String[] lines = RString.split(_servers, '\n');
      for(String line : lines){
         String[] serverInfos = RString.split(line, '|');
         for(String serverInfo : serverInfos){
            serverInfo = serverInfo.trim();
            if(!serverInfo.isEmpty()){
               SGameCheckServer checkServer = new SGameCheckServer();
               checkServer.parse(serverInfo);
               _checkServers.push(checkServer);
            }
         }
      }
   }

   //============================================================
   // <T>获得名称。</T>
   //
   // @return 名称
   //============================================================
   public String name(){
      return RClass.shortName(this) + "[" + _info + "]";
   }

   //============================================================
   // <T>发送邮件。</T>
   //
   // @return 处理结果
   //============================================================
   public void sendMail(String subject,
                        String content){
      FMail mail = new FMail();
      // 标题
      mail.setSubject(subject);
      // 邮件发送者
      mail.setFrom("m@zqinet.com");
      // 内容
      mail.setBody(content);
      // 发送邮件
      _mailConsole.sendGroup("zqinet.monitor", mail);
   }

   //============================================================
   // <T>同步处理。</T>
   //
   // @return 处理结果
   //============================================================
   public boolean process(){
      boolean valid = true;
      String subject = "";
      String reason = "";
      // 获得同步器控制台
      String value = RHttp.loadUrlString(_url);
      // 解析信息
      SGameServerInfo serverInfo = new SGameServerInfo();
      serverInfo.parse(value);
      //............................................................
      // 检查交换内存
      if(serverInfo.swapFree < 1024 * 1024 * 1024){
         valid = false;
         subject = "SWAP [" + (serverInfo.swapFree / 1024) + "kb]";
         reason = "Swap not enought";
      }
      //............................................................
      // 检查所有进程是否合法
      String validProcess = null;
      FStrings validInfos = new FStrings();
      for(SGameCheckServer checkServer : _checkServers){
         SGameProcessInfo processInfo = serverInfo.findProcess(checkServer.user, checkServer.processName);
         if(null == processInfo){
            if(null == validProcess){
               validProcess = checkServer.processAlias + "@" + checkServer.user;
            }else{
               validProcess += "," + checkServer.processAlias + "@" + checkServer.user;
            }
            validInfos.push("Process invalid. (user=" + checkServer.user + " process=" + checkServer.processName + ")");
         }
      }
      if(!validInfos.isEmpty()){
         valid = false;
         subject = "SVRD [" + validProcess + "]";
         reason = "Server down [" + validProcess + "]";
      }
      //............................................................
      // 发送邮件
      if(!valid){
         String content = reason + "\n";
         content += "------------------------------\n";
         content += "Memory:" + (serverInfo.memoryTotal - serverInfo.memoryFree) + "/" + serverInfo.memoryTotal + "\n";
         content += "Swap:  " + (serverInfo.swapTotal - serverInfo.swapFree) + "/" + serverInfo.swapTotal + "\n";
         content += "------------------------------\n";
         content += "Process:\n" + validInfos.join("\n");
         sendMail(_info + ":" + subject, content);
         _logger.warn(this, "process", "Invalid status.\n{1}", content);
      }
      return true;
   }
}
