package org.mo.com.net.http;

import org.mo.com.lang.FString;
import org.mo.com.lang.RInteger;
import org.mo.com.lang.RString;

//============================================================
// <T>网络地址。</T>
//============================================================
public class FUrl
{
   // 协议
   protected String _protocol;

   // 主机
   protected String _host;

   // 端口
   protected int _port = 80;

   // 路径
   protected String _path;

   // 地址
   protected String _url;

   //============================================================
   // <T>构造网络地址。</T>
   //============================================================
   public FUrl(String url){
      _url = url;
      parse(url);
   }

   //============================================================
   // <T>获得协议。</T>
   //
   // @param 协议
   //============================================================
   public String protocol(){
      return _protocol;
   }

   //============================================================
   // <T>获得主机。</T>
   //
   // @param 主机
   //============================================================
   public String host(){
      return _host;
   }

   //============================================================
   // <T>获得端口。</T>
   //
   // @param 端口
   //============================================================
   public int port(){
      return _port;
   }

   //============================================================
   // <T>获得路径。</T>
   //
   // @param 路径
   //============================================================
   public String path(){
      return _path;
   }

   //============================================================
   // <T>解析网络地址。</T>
   //
   // @param url 网络地址
   //============================================================
   protected void parse(String url){
      int protocol = url.indexOf("://");
      if(protocol != -1){
         _protocol = url.substring(0, protocol).toLowerCase();
      }
      int host = url.indexOf("/", protocol + 3);
      if(host != -1){
         _host = url.substring(protocol + 3, host);
         int port = _host.indexOf(':');
         if(port != -1){
            String portStr = _host.substring(port + 1);
            _port = RInteger.parse(portStr);
            _host = _host.substring(0, port);
         }
         _path = url.substring(host);
      }else{
         _host = url.substring(protocol + 3);
      }
      if(RString.equalsIgnoreCase(_host, "localhost")){
         _host = "127.0.0.1";
      }
   }

   //============================================================
   // <T>获得字符串。</T>
   //
   // @param 字符串
   //============================================================
   public String toString(){
      return _url;
   }

   //============================================================
   // <T>获得运行信息。</T>
   //
   // @return 运行信息
   //============================================================
   public FString dump(){
      FString dump = new FString();
      dump.append("Protocol[");
      dump.append(_protocol);
      dump.append("] Host[");
      dump.append(_host);
      dump.append("] Port[");
      dump.appendInt(_port);
      dump.append("] Path[");
      dump.append(_path);
      dump.append("]");
      return dump;
   }
}
