package org.mo.com.net;

import java.net.ServerSocket;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RDump;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.lang.reflect.RClass;

//============================================================
// <T>服务器网络链接。</T>
//============================================================
public class FServerSocket
      extends FSocket
{
   // 服务器网络链接
   protected ServerSocket _socket;

   //============================================================
   // <T>构造服务器网络链接。</T>
   //============================================================
   public FServerSocket(){
   }

   //============================================================
   // <T>构造服务器网络链接。</T>
   //
   // @param port 端口
   //============================================================
   public FServerSocket(int port){
      try{
         _socket = new ServerSocket(port);
      }catch(Exception e){
         throw new FFatalError(e, "Port={0}", port);
      }
   }

   //============================================================
   // <T>构造服务器网络链接。</T>
   //
   // @param port 端口
   // @param backlog 缓冲大小
   //============================================================
   public FServerSocket(int port,
                        int backlog){
      try{
         _socket = new ServerSocket(port, backlog);
      }catch(Exception e){
         throw new FFatalError(e, "Port={0},Backlog={1}", port, backlog);
      }
   }

   //============================================================
   // <T>接收网络链接。</T>
   //
   // @return 网络链接
   //============================================================
   public FSocket accept(){
      try{
         return new FClientSocket(_socket.accept());
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
         _socket.close();
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
         info.append("Inet Address:         ");
         info.appendLine(_socket.getInetAddress());
         info.append("Local Port:           ");
         info.appendLine(_socket.getLocalPort());
         info.append("Local Socket Address: ");
         info.appendLine(_socket.getLocalSocketAddress());
         info.append("Bound:                ");
         info.appendLine(_socket.isBound());
         info.append("Closed:               ");
         info.appendLine(_socket.isClosed());
         info.append("Reuse Address:        ");
         info.appendLine(_socket.getReuseAddress());
         info.append("So Timeout:           ");
         info.appendLine(_socket.getSoTimeout());
         info.append("Receive Buffer Size:  ");
         info.appendLine(+_socket.getReceiveBufferSize());
      }catch(Exception e){
         throw new FFatalError(e);
      }
      return info;
   }
}
