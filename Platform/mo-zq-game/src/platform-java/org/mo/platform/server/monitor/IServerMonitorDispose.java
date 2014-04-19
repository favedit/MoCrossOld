package org.mo.platform.server.monitor;

import org.mo.com.xml.FXmlNode;
import org.mo.platform.server.common.XServer;

public interface IServerMonitorDispose {
   
   //============================================================
   // <T>发送email。</T>
   //
   // @param subject 标题
   // @param value 内容
   // @param to  接收者地址
   //============================================================
   void SendMail(String subject, 
                 String value, 
                 String to);
   
   //============================================================
   // <T>查找处理节点。</T>
   //
   // @param xconfig 配置节点
   // @param serverPath 服务器路径
   // @param user 用户
   // @param flag 标志
   // @return 节点
   //============================================================
   FXmlNode findProcessNode(FXmlNode xconfig,
                            String serverPath,
                            String user,
                            String flag);
   //============================================================
   // <T>检查系统信息，发送Email。</T>
   //
   // @param xserver 服务器对象
   //============================================================
   void checkSystemInfo(XServer xserver);
   
   //============================================================
   // <T>删除一周以前的日志文件</T>
   //
   // @param xserver 服务器对象
   //============================================================
   void delOneWeekLog(XServer xserver);
   
   //============================================================
   // <T>执行操作。</T>
   //
   // @return true 
   //============================================================
   boolean serverInfoProcess();
}
