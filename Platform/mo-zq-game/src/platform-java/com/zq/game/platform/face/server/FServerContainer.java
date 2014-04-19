package com.zq.game.platform.face.server;

import org.mo.com.xml.FXmlNode;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectPage;
import org.mo.platform.server.common.XServer;

//============================================================
// <T>服务器容器。</T>
//============================================================
public class FServerContainer
      extends FAbsXmlObjectPage<XServer>
{
   // 序列化编号
   private static final long serialVersionUID = 1L;

   // 服务器名称
   private String _serverName;

   // 系统信息
   protected FXmlNode _systemInfo;

   // 进程集合信息
   protected FXmlNode _processesInfo;
   
   //服务器执行完，返回成功或失败结果
   protected FXmlNode _returnInformation;
   
   //硬盘信息集合
   protected FXmlNode _disksInfo;
   
   //cpu信息集合
   protected FXmlNode _cpusInfo;
   
   //服务器返回执行结果
   protected FXmlNode _xmessage;
   
   
   //============================================================
   // <T>获得服务器执行结果集合信息。</T>
   //
   // @return 执行结果集合信息
   //============================================================
   public FXmlNode get_xmessage() {
      return _xmessage;
   }

   //============================================================
   // <T>设置服务器执行结果集合信息。</T>
   //
   // @return 执行结果集合信息
   //============================================================
   public void set_xmessage(FXmlNode _xmessage) {
      this._xmessage = _xmessage;
   }

   //============================================================
   // <T>获得服务器cpu集合信息。</T>
   //
   // @return 硬盘集合信息
   //============================================================
   public FXmlNode cpusInfo() {
     return _cpusInfo;
   }
   
   //============================================================
   // <T>设置服务器cpu集合信息。</T>
   //
   // @return 硬盘集合信息
   //============================================================
   public void setCpusInfo(FXmlNode cpusInfo) {
     this._cpusInfo = cpusInfo;
   }

   //============================================================
   // <T>获得服务器硬盘集合信息。</T>
   //
   // @return 硬盘集合信息
   //============================================================
   public FXmlNode disksInfo() {
     return _disksInfo;
   }
   
   //============================================================
   // <T>设置服务器硬盘信息。</T>
   //
   // @return 硬盘集合信息
   //============================================================
   public void setDisksInfo(FXmlNode diskInfo) {
      _disksInfo = diskInfo;
   }
   
   //============================================================
   // <T>获得服务器返回结果。</T>
   //
   // @return 返回结果
   //============================================================
   public FXmlNode returnInformation() {
     return _returnInformation;
   }
   
   //============================================================
   // <T>设置服务器返回结果。</T>
   //
   // @return 返回结果
   //============================================================
   public void setReturnInformation(FXmlNode returnInformation) {
     _returnInformation = returnInformation;
   }

   //============================================================
   // <T>获得服务器名称。</T>
   //
   // @return 服务器名称
   //============================================================
   public String serverName(){
      return _serverName;
   }

   //============================================================
   // <T>设置服务器名称。</T>
   //
   // @param serverName 服务器名称
   //============================================================
   public void setServerName(String serverName){
      _serverName = serverName;
   }

   //============================================================
   // <T>获得系统信息。</T>
   //
   // @return 系统信息
   //============================================================
   public FXmlNode systemInfo(){
      return _systemInfo;
   }

   //============================================================
   // <T>设置系统信息。</T>
   //
   // @param systemInfo 系统信息
   //============================================================
   public void setSystemInfo(FXmlNode systemInfo){
      _systemInfo = systemInfo;
   }

   //============================================================
   // <T>获得进程集合信息。</T>
   //
   // @return 进程集合信息
   //============================================================
   public FXmlNode processesInfo(){
      return _processesInfo;
   }

   //============================================================
   // <T>设置进程集合信息。</T>
   //
   // @param processesInfo 进程集合信息
   //============================================================
   public void setProcessesInfo(FXmlNode processesInfo){
      _processesInfo = processesInfo;
   }
}
