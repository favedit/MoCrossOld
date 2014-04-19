package org.mo.com.net.mail;

import java.util.Properties;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;

//============================================================
// <T>邮件属性。</T>
//============================================================ 
public class TMailProperties
{
   // 主机地址
   protected String _host;

   // 主机端口
   protected int _port;

   // 登录认证
   protected boolean _authentic = false;

   // 登录账号
   protected String _loginPassport;

   // 登录密码
   protected String _loginPassword;

   // 代理地址
   protected String _proxyHost;

   // 代理端口
   protected int _proxyPort;

   // 网络代理主机
   protected String _socketProxyHost;

   // 网络代理端口
   protected int _socketProxyPort;

   // 非代理地址集合
   protected String _nonProxyHosts;

   //============================================================
   // <T>构造邮件属性。</T>
   //============================================================ 
   public TMailProperties(){
   }

   //============================================================
   // <T>获得主机地址。</T>
   //
   // @return 主机地址
   //============================================================ 
   public String host(){
      return _host;
   }

   //============================================================
   // <T>设置主机地址。</T>
   //
   // @param host 主机地址
   //============================================================ 
   public void setHost(String host){
      _host = host;
   }

   //============================================================
   // <T>获得主机端口。</T>
   //
   // @return 主机端口
   //============================================================ 
   public int port(){
      return _port;
   }

   //============================================================
   // <T>设置主机端口。</T>
   //
   // @param port 主机端口
   //============================================================ 
   public void setPort(int port){
      _port = port;
   }

   //============================================================
   // <T>判断权限。</T>
   //
   // @return 权限
   //============================================================ 
   public boolean isAuthentic(){
      return _authentic;
   }

   //============================================================
   // <T>设置权限。</T>
   //
   // @param authentic 权限
   //============================================================ 
   public void setAuthentic(boolean authentic){
      _authentic = authentic;
   }

   //============================================================
   // <T>获得登录账号。</T>
   //
   // @return 登录账号
   //============================================================ 
   public String loginPassport(){
      return _loginPassport;
   }

   //============================================================
   // <T>设置登录账号。</T>
   //
   // @param loginPassport 登录账号
   //============================================================ 
   public void setLoginPassport(String loginPassport){
      _loginPassport = loginPassport;
   }

   //============================================================
   // <T>获得登录密码。</T>
   //
   // @return 登录密码
   //============================================================ 
   public String loginPassword(){
      return _loginPassword;
   }

   //============================================================
   // <T>设置登录密码。</T>
   //
   // @param loginPassword 登录密码
   //============================================================ 
   public void setLoginPassword(String loginPassword){
      _loginPassword = loginPassword;
   }

   //============================================================
   // <T>获得代理主机。</T>
   //
   // @return 代理主机
   //============================================================ 
   public String proxyHost(){
      return _proxyHost;
   }

   //============================================================
   // <T>设置代理主机。</T>
   //
   // @param proxyHost 代理主机
   //============================================================ 
   public void setProxyHost(String proxyHost){
      _proxyHost = proxyHost;
   }

   //============================================================
   // <T>获得代理端口。</T>
   //
   // @return 代理端口
   //============================================================ 
   public int proxyPort(){
      return _proxyPort;
   }

   //============================================================
   // <T>设置代理端口。</T>
   //
   // @param proxyPort 代理端口
   //============================================================ 
   public void setProxyPort(int proxyPort){
      _proxyPort = proxyPort;
   }

   //============================================================
   // <T>获得网络代理主机。</T>
   //
   // @return 网络代理主机
   //============================================================ 
   public String socketProxyHost(){
      return _socketProxyHost;
   }

   //============================================================
   // <T>设置网络代理主机。</T>
   //
   // @param socketProxyHost 网络代理主机
   //============================================================ 
   public void setSocketProxyHost(String socketProxyHost){
      _socketProxyHost = socketProxyHost;
   }

   //============================================================
   // <T>获得网络代理端口。</T>
   //
   // @return 网络代理端口
   //============================================================ 
   public int socketProxyPort(){
      return _socketProxyPort;
   }

   //============================================================
   // <T>设置网络代理端口。</T>
   //
   // @param socketProxyPort 网络代理端口
   //============================================================ 
   public void setSocketProxyPort(int socketProxyPort){
      _socketProxyPort = socketProxyPort;
   }

   //============================================================
   // <T>获得非代理主机集合。</T>
   //
   // @return 非代理主机集合
   //============================================================ 
   public String nonProxyHosts(){
      return _nonProxyHosts;
   }

   //============================================================
   // <T>设置非代理主机集合。</T>
   //
   // @param nonProxyHosts 非代理主机集合
   //============================================================ 
   public void setNonProxyHosts(String nonProxyHosts){
      _nonProxyHosts = nonProxyHosts;
   }

   //============================================================
   // <T>生成属性集合。</T>
   //
   // @return 属性集合
   //============================================================ 
   public Properties makeProperties(){
      // 创建服务属性对象
      Properties properties = new Properties();
      // 邮件服务器的地址
      properties.put("mail.smtp.host", _host);
      // 邮件服务器的端口
      if(_port > 0){
         properties.put("mail.smtp.port", _port);
      }
      // 设置邮箱是否使用权限认证
      properties.put("mail.smtp.auth", RBoolean.toString(isAuthentic(), "true", "false"));
      // 发信登录账号
      if(!RString.isEmpty(_loginPassport)){
         properties.put("mail.smtp.user", _loginPassport);
      }
      // 发信登录密码
      if(!RString.isEmpty(_loginPassword)){
         properties.put("mail.smtp.password", _loginPassword);
      }
      // 代理的邮件服务器的地址
      if(!RString.isEmpty(_proxyHost)){
         properties.put("mail.smtp.proxyHost", _proxyHost);
      }
      // 代理的邮件服务器的端口
      if(_proxyPort > 0){
         properties.put("mail.smtp.proxyPort", _proxyPort);
      }
      return properties;
   }
}
