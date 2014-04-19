package org.mo.com.net;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RDump;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.lang.reflect.RClass;

//============================================================
// <T>客户端网络链接。</T>
//============================================================
public class FClientSocket
      extends FSocket
{
   // 网络链接
   protected Socket _socket;

   //============================================================
   // <T>构造客户端网络链接。</T>
   //============================================================
   public FClientSocket(){
   }

   //============================================================
   // <T>构造客户端网络链接。</T>
   //
   // @parma socket 网络链接
   //============================================================
   public FClientSocket(Socket socket){
      _socket = socket;
   }

   //============================================================
   // <T>构造客户端网络链接。</T>
   //
   // @param host 主机地址
   // @param port 端口
   //============================================================
   public FClientSocket(String host,
                        int port){
      connect(host, port);
   }

   //============================================================
   // <T>构造客户端网络链接。</T>
   //
   // @param address 目标地址
   // @param port 目标端口
   // @param localAddress 本地地址
   // @param localPort 本地端口
   //============================================================
   public FClientSocket(InetAddress address,
                        int port,
                        InetAddress localAddress,
                        int localPort){
      connect(address, port, localAddress, localPort);
   }

   //============================================================
   // <T>获得原生网络对象。</T>
   //
   // @return 网络对象
   //============================================================
   public Socket nativeSocket(){
      return _socket;
   }

   //============================================================
   // <T>获得原生输入流。</T>
   //
   // @return 输入流
   //============================================================
   public InputStream nativeInputStream(){
      try{
         return _socket.getInputStream();
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   //============================================================
   // <T>获得原生输出流。</T>
   //
   // @return 输出流
   //============================================================
   public OutputStream nativeOutputStream(){
      try{
         return _socket.getOutputStream();
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   //============================================================
   // <T>判断是否链接。</T>
   //
   // @return 是否链接
   //============================================================
   public boolean isConnected(){
      return _socket.isConnected();
   }

   //============================================================
   // <T>获得主机地址。</T>
   //
   // @return 主机地址
   //============================================================
   public String host(){
      return _socket.getInetAddress().getHostAddress();
   }

   //============================================================
   // <T>获得主机端口。</T>
   //
   // @return 主机端口
   //============================================================
   public int port(){
      return _socket.getPort();
   }

   //============================================================
   // <T>获得本地端口。</T>
   //
   // @return 本地端口
   //============================================================
   public int localPort(){
      return _socket.getLocalPort();
   }

   //============================================================
   // <T>链接网络地址。</T>
   //
   // @param host 主机地址
   // @param port 端口
   //============================================================
   public void connect(String host,
                       int port){
      try{
         _socket = new Socket(host, port);
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   //============================================================
   // <T>链接网络地址。</T>
   //
   // @param address 目标地址
   // @param port 目标端口
   // @param localAddress 本地地址
   // @param localPort 本地端口
   //============================================================
   public boolean connect(InetAddress address,
                          int port,
                          InetAddress localAddress,
                          int localPort){
      try{
         _socket = new Socket(address, port, localAddress, localPort);
         return true;
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   //============================================================
   // <T>设置关闭延时。</T>
   //
   // @param value 延时长度
   //============================================================
   public void setSoLinger(int value){
      try{
         _socket.setSoLinger(true, value);
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   //============================================================
   // <T>设置网络超时。</T>
   //
   // @param value 网络超时
   //============================================================
   public void setSoTimeout(int value){
      try{
         _socket.setSoTimeout(value);
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   //============================================================
   // <T>关闭网络链接。</T>
   //============================================================
   @Override
   public void close(){
      try{
         //FStream.skipAvailable(m_oSocket.getInputStream());
         if(!_socket.isInputShutdown()){
            _socket.shutdownInput();
         }
         if(!_socket.isOutputShutdown()){
            _socket.shutdownOutput();
         }
         _socket.close();
         _socket = null;
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   //============================================================
   // <T>生成运行信息。</T>
   //
   // @param info 运行信息
   // @return 运行信息
   //============================================================
   @Override
   public TDumpInfo dump(TDumpInfo info){
      try{
         info.appendLine(RClass.shortName(this));
         info.appendLine(RDump.LINE_L2 + "\n");
         info.append("Inet Address:          ");
         info.appendLine(_socket.getInetAddress());
         info.append("Local Address:         ");
         info.appendLine(_socket.getLocalAddress());
         info.append("Local Port:            ");
         info.appendLine(_socket.getLocalPort());
         info.append("Local Socket Address:  ");
         info.appendLine(_socket.getLocalSocketAddress());
         info.append("OOB Inline:            ");
         info.appendLine(_socket.getOOBInline());
         info.append("Port:                  ");
         info.appendLine(_socket.getPort());
         info.append("Remote Socket Address: ");
         info.appendLine(_socket.getRemoteSocketAddress());
         info.append("So Timeout:            ");
         info.appendLine(_socket.getSoTimeout());
         info.append("So Linger:             ");
         info.appendLine(_socket.getSoLinger());
         info.append("Tcp No Delay:          ");
         info.appendLine(_socket.getTcpNoDelay());
         info.append("Traffic Class:         ");
         info.appendLine(_socket.getTrafficClass());
         info.append("Bound:                 ");
         info.appendLine(_socket.isBound());
         info.append("Closed:                ");
         info.appendLine(_socket.isClosed());
         info.append("Reuse Address:         ");
         info.appendLine(_socket.getReuseAddress());
         info.append("Send Buffer Size:      ");
         info.appendLine(_socket.getSendBufferSize());
         info.append("Receive Buffer Size:   ");
         info.appendLine(_socket.getReceiveBufferSize());
         info.append("Keep Alive:            ");
         info.appendLine(_socket.getKeepAlive());
      }catch(Exception e){
         throw new FFatalError(e);
      }
      return info;
   }
}
