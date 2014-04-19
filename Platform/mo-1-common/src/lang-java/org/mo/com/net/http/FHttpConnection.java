package org.mo.com.net.http;

import org.mo.com.lang.FObject;
import org.mo.com.net.FClientSocket;
import org.mo.com.net.FSocket;

//============================================================
// <T>网络链接。</T>
//============================================================
public class FHttpConnection
      extends FObject
{
   // 网络端口
   protected FSocket _socket;

   // 网络请求
   protected FHttpRequest _request;

   // 网络应答
   protected FHttpResponse _response;

   // 网络地址
   protected FUrl _url;

   // 是否使用代理
   protected boolean _useProxy = false;

   //============================================================
   // <T>构造网络链接。</T>
   //
   // @param url 网络地址
   //============================================================
   public FHttpConnection(String url){
      _url = new FUrl(url);
   }

   //============================================================
   // <T>构造网络链接。</T>
   //
   // @param url 网络地址
   //============================================================
   public FHttpConnection(FUrl url){
      _url = url;
   }

   //============================================================
   // <T>获得网络地址。</T>
   //
   // @return 网络地址
   //============================================================
   public FUrl url(){
      return _url;
   }

   //============================================================
   // <T>获得是否使用代理。</T>
   //
   // @return 是否使用代理
   //============================================================
   public boolean useProxy(){
      return _useProxy;
   }

   //============================================================
   // <T>获得网络端口。</T>
   //
   // @return 网络端口
   //============================================================
   public FSocket socket(){
      return _socket;
   }

   //============================================================
   // <T>获得网络请求。</T>
   //
   // @return 网络请求
   //============================================================
   public FHttpRequest request(){
      if(_request == null){
         _request = new FHttpRequest(this);
      }
      return _request;
   }

   //============================================================
   // <T>获得网络应答。</T>
   //
   // @return 网络应答
   //============================================================
   public FHttpResponse response(){
      if(_response == null){
         _response = new FHttpResponse(this);
      }
      return _response;
   }

   //============================================================
   // <T>链接主机。</T>
   //============================================================
   public void connect(){
      String host = _url.host();
      int port = _url.port();
      _socket = new FClientSocket(host, port);
   }

   //============================================================
   // <T>链接代理。</T>
   //
   // @param host 地址
   // @param port 端口
   //============================================================
   public void connectProxy(String host,
                            int port){
      _useProxy = true;
      _socket = new FClientSocket(host, port);
   }

   //============================================================
   // <T>获取数据。</T>
   //============================================================
   public void fetch(){
      request().send();
      response().receive();
   }

   //============================================================
   // <T>断开链接。</T>
   //============================================================
   public void disconnect(){
      _socket.close();
   }
}
