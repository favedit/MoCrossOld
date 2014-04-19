package org.mo.platform.server.monitor;

import java.util.Calendar;

import org.mo.com.lang.RDouble;
import org.mo.com.lang.RString;
import org.mo.com.net.http.FHttpService;
import org.mo.com.net.mail.FMail;
import org.mo.com.net.mail.FMailSession;
import org.mo.com.net.mail.RMail;
import org.mo.com.net.mail.TMailProperties;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.RAop;
import org.mo.platform.server.common.XProcess;
import org.mo.platform.server.common.XServer;

//============================================================
//<T>服务监视处理。</T>
//============================================================
public class FServerMonitorDispose{
 
     //============================================================
     // <T>发送email。</T>
     //
     // @param subject 标题
     // @param value 内容
     // @param to  接收者地址
     //============================================================
     public void SendMail(String subject, 
                       String value, 
                       String to) {
      
        TMailProperties properties = new TMailProperties();
        // 服务器地址
        properties.setHost("smtp.ym.163.com");
        // 发送端口
        properties.setPort(25);
        // 是否需要身份认证
        properties.setAuthentic(true);
        // 用户名
        properties.setLoginPassport("yypt@zqinet.com");
        // 密码
        properties.setLoginPassword("yunyingpingtai");
        FMail fm = new FMail();
        // 邮件接收者的地址
        fm.addCc(to);
        // 内容
        fm.setBody(value);
        // 标题
        fm.setSubject(subject);
        fm.setFrom("yypt@zqinet.com");
        FMailSession ms = RMail.findSession(properties);
        ms.send(fm);
    }
   
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
   // <T>检查系统信息，发送Email。</T>
   //
   // @param xserver 服务器对象
   //============================================================
   public void checkSystemInfo(XServer xserver) {
      
       String adminEmail = xserver.getAdminEmail();
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
         xsearch.setText(search);
         // 发送&获取
         if(!service.load(url)){
            System.err.println("服务器："+xserver.getHost()+",用户："+xserver.getUser()+",异常：服务器已关闭或未连接！");
            return;
         }
         FXmlNode xoutput = service.outputNode();
         // 检查交换内存
         FXmlNode xsystem = xoutput.findNode("System");
         if(RDouble.calculatePercent(xsystem.getLong("swap_free"), xsystem.getLong("swap_total")) >= 90){
           String message ="服务器："+ xserver.getHost() + ",用户："+xserver.getUser()+",【交换内存】的使用率已超过标准范围，请管理员查看是否正常。";
           System.out.println(""+message);
           //SendMail(xserver.getHost()+",交换内存警报", message, adminEmail);
        }
         // 检查硬盘信息
         FXmlNode xdiskInfos = xoutput.findNode("diskInfos");
         for(FXmlNode node : xdiskInfos.nodes()){
            double diskUsePercent = RDouble.parse(node.getLong("disk_usePercent"));
            //硬盘警报。
            if(diskUsePercent >= 85){
               String message = "服务器："+ xserver.getHost() + ",用户："+xserver.getUser()+",硬盘分区：【"+node.get("file_system")+"】的使用率已超过标准范围，请管理员查看是否正常。";
               System.out.println(""+message);
               //SendMail(xserver.getHost()+",硬盘警报", message, adminEmail);
            }
            //System.out.println(diskinfo.get("disk_usePercent"));
          }
         // 检查进程
         FXmlNode xprocesses = xoutput.findNode("Processes");
         for(IXmlObject xobject : xserver.children()){
            if(XProcess.isInstance(xobject)){
                 XProcess xprocess = (XProcess)xobject;
                 FXmlNode xprocessConfig = findProcessNode(xprocesses, xserver.getPath(), xprocess.getUser(), xprocess.getFlag());
                 if(xprocessConfig == null){
                    String message = "服务器："+ xserver.getHost() + ",用户："+xserver.getUser()+",【"+xprocess.getLabel()+"】进程已停止，请管理员查看。";
                     System.out.println(""+message);
                     //SendMail(xserver.getHost()+",服务器进程警报", message, adminEmail);
                 }
            }
         }
         // 检查http状态
         FXmlNode xhttp = xoutput.findNode("httpInfo");
         String httpStatus = xhttp.get("http_result");
         if(!httpStatus.equals("(pid")){
            String message ="服务器："+ xserver.getHost() + ",用户："+xserver.getUser()+",【http】已停止，请管理员查看。";
            System.out.println(""+message);
           //SendMail(xserver.getHost()+",http警报", message, adminEmail);
         }
         
   }
   //============================================================
   // <T>删除一周以前的日志文件</T>
   //
   // @param xserver 服务器对象
   //============================================================
   public void delOneWeekLog(XServer xserver){
      
      String url = "http://" + xserver.getHost() + ":" + xserver.getPort() + "/zqinet.game.server.status.ws";
      // 获得同步器控制台
      FHttpService service = new FHttpService();
      FXmlNode xexecute = service.inputNode().createNode("Execute");
      System.out.println(xserver.getUser());
      xexecute.set("user", xserver.getUser());
      if(!service.load(url,"delWeekLog")){
         System.err.println("无法连接到服务器["+xserver.getHost()+"]");
         return;
      }
      
   }
   //============================================================
   // <T>执行操作。</T>
   //
   // @return true 
   //============================================================
   public boolean serverInfoProcess(){
      IServerConsole console = RAop.find(IServerConsole.class);
      //System.out.println(console.list().length+"------");
      
      for(XServer xserver : console.list()){
         checkSystemInfo(xserver);
         delOneWeekLog(xserver);
      }
      return true;
   }
   
}
