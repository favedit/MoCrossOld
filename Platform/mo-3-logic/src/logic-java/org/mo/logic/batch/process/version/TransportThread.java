package org.mo.logic.batch.process.version;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import org.mo.com.io.RFile;
import org.mo.com.lang.RString;
import org.mo.com.xml.FXmlNode;
import org.mo.core.aop.RAop;
import org.mo.logic.batch.IBatchConsole;

public class TransportThread
      extends Thread
{
   // 客户端socket
   private Socket _client = null;

   private DataInputStream _read = null;

   // 配置文件xml
   @SuppressWarnings("unused")
   private FXmlNode _config = null;

   private DataOutputStream _write = null;

   // 连接标识
   private final String MARK_CONNECT = "connect";

   // 关闭标识
   private final String MARK_BREAK = "break";

   // 成功标识
   private final String MARK_SUCCESS = "success";

   // 失败标识
   private final String MARK_FAIL = "fail";

   // 原因标识
   private final String MARK_CAUSE = "cause";

   // 传输文件标识
   private final String MARK_TRANSPORT = "transport";

   // 执行命令标识
   private final String MARK_COMMAND = "command";

   // 客户端IP地址
   private String _address = "";

   TransportThread(Socket client,
                   FXmlNode config){
      if(null != client && client.isConnected()){
         _client = client;
         _address = _client.getInetAddress().getHostAddress();
         _config = config;
         System.out.println("==================================");
         System.out.println("客户端：" + _address + "连接成功！");
         System.out.println("==================================");
      }
   }

   protected void close(){
      // 关闭所有连接
      try{
         _read.close();
         _write.close();
         _client.close();
      }catch(Exception e){
         System.out.println("================================================");
         System.out.println("与客户端(IP" + _address + ")经断开连接失败，原因如下：");
         System.out.println(e);
         System.out.println("================================================");
         return;
      }
      System.out.println("================================================");
      System.out.println("与客户端(IP" + _address + ")已经断开连接，成功关闭所有连接。");
      System.out.println("================================================");
   }

   /**
    * <T>传输版本zip文件</T>
    * 
    */
   protected String executeCommand(){
      try{
         String batchConfig = "";
         String batchPath = "";
         for(int n = 0; n < 2; n++){
            String mark = _read.readUTF();
            if("command_path".equalsIgnoreCase(mark)){
               batchPath = _read.readUTF();
            }else if("command_name".equalsIgnoreCase(mark)){
               batchConfig = _read.readUTF();
            }
         }
         if(RString.isEmpty(batchPath)){
            return "批处理配置文件目录(command_path)为空！";
         }
         if(RString.isEmpty(batchPath)){
            return "批处理配置文件名称(command_config)为空！";
         }
         _write.writeUTF("ok");
         _write.flush();
         String isExecute = _read.readUTF();
         if("execute".equalsIgnoreCase(isExecute)){
            IBatchConsole batchConsole = RAop.find(IBatchConsole.class);
            batchConsole.loadConfig(batchPath, batchConfig);
            batchConsole.process(null);
         }else{
            System.out.println("==============没有执行命令，命令符为：===============");
            System.out.println(isExecute);
            System.out.println("================================================");
         }
      }catch(Exception e){
         System.out.println("==============执行命令失败，原因如下：===============");
         System.out.println(e);
         System.out.println("================================================");
         return e.toString();
      }
      System.out.println("==========================================");
      System.out.println("成功完成命令执行操作！");
      System.out.println("==========================================");
      return null;
   }

   @Override
   public void run(){
      // 用于获取客户端传输来的信息   
      try{
         // 传进传入流目的是读取信息
         if(null == _read){
            _read = new DataInputStream(_client.getInputStream());
         }
         // 传进传出流目的是写入信息
         if(null == _write){
            _write = new DataOutputStream(_client.getOutputStream());
         }
         // 给客户端传输连接成功标识
         _write.writeUTF(MARK_CONNECT);
         _write.flush();
         while(true){
            // 读取工作标识
            String mark = _read.readUTF();
            // 判断工作模式
            if(MARK_TRANSPORT.equalsIgnoreCase(mark)){
               // 获得执行结果
               String result = transportFile();
               if(RString.isNotEmpty(result)){
                  _write.writeUTF(MARK_FAIL);
                  _write.flush();
                  _write.writeUTF(MARK_CAUSE);
                  _write.flush();
                  _write.writeUTF(result);
                  _write.flush();
               }else{
                  _write.writeUTF(MARK_SUCCESS);
                  _write.flush();
               }
            }else if(MARK_COMMAND.equalsIgnoreCase(mark)){
               // 命令操作
               String result = executeCommand();
               if(RString.isNotEmpty(result)){
                  _write.writeUTF(MARK_FAIL);
                  _write.flush();
                  _write.writeUTF(MARK_CAUSE);
                  _write.flush();
                  _write.writeUTF(result);
                  _write.flush();
               }else{
                  _write.writeUTF(MARK_SUCCESS);
                  _write.flush();
               }
            }
            // 获得是否断开连接
            String breakMark = _read.readUTF();
            // 关闭所有连接
            if(MARK_BREAK.equalsIgnoreCase(breakMark)){
               close();
               break;
            }
         }
      }catch(IOException e){
      }
   }

   /**
    * <T>传输版本zip文件</T>
    * 
    */
   protected String transportFile(){
      try{
         // 获得传出文件的目录
         String savePath = _read.readUTF();
         //读取文件内容
         String zipName = _read.readUTF();
         System.out.println("================================================");
         System.out.println("开始传输文件" + zipName);
         System.out.println("================================================");
         if(RString.isNotEmpty(zipName)){
            try{
               File filePath = new File(savePath);
               if(!filePath.exists()){
                  filePath.mkdirs();
               }
               String zipPath = RFile.makeFilename(savePath, zipName);
               zipPath = RString.replace(zipPath, "\\", "/");
               // 创建zip文件
               File file = new File(zipPath);
               // 创建读取zip文件流
               DataOutputStream writeZip = new DataOutputStream(new FileOutputStream(file));
               byte[] buf = new byte[1024];
               // 读取zip文件
               System.out.println("==========================================");
               System.out.println("处理中请稍后.........");
               System.out.println("==========================================");
               int len = 0;
               long realLen = _read.readLong();
               while(true){
                  // 2010-03-19二次修改(修改成以对其字节数为准去做读取操作)
                  boolean yes = true;
                  double a = 0;
                  int fileLen = 0;
                  while(true){ // 是否读完网络中传输的文件
                     len = _read.read(buf);
                     a = a + len;
                     int c = (int)((a / realLen) * 100);
                     if(c % 10 == 0){
                        if(yes){
                           System.out.println("==========================================");
                           System.out.println("传输：" + c + "%");
                           System.out.println("==========================================");
                           yes = false;
                        }
                     }else{
                        yes = true;
                     }
                     try{
                        writeZip.write(buf, 0, len); // 把文件数据写到zip文件中 
                        writeZip.flush(); // 刷新缓冲区把数据写往客户端
                     }catch(IOException ioe){
                        System.out.println("==========================================");
                        System.out.println("写入zip文件时出错，原因如下：");
                        System.out.println(ioe.toString());
                        System.out.println("==========================================");
                        System.out.println(fileLen + "      " + len);
                        System.out.println("==========================================");
                     }
                     fileLen = fileLen + len;
                     if(fileLen >= realLen){
                        break;
                     }
                  }
                  break;
                  //                  long fileLength = file.length();
                  //                  int size = (int) (realLength - fileLength) / 1024;
                  //                  if(-1 < size && size < 1){
                  //                     break;
                  //                  }
               }
               writeZip.close();
               System.out.println("==============************===============");
               System.out.println("服务器传输完毕！");
               System.out.println("================================================");
               // 结束传输zip文件
               _write.writeUTF("ok");
               _write.flush();
            }catch(IOException e){
               System.out.println("==============传输失败，原因如下：===============");
               System.out.println(e);
               System.out.println("================================================");
               // 结束传输zip文件
               _write.writeUTF("fail");
               _write.flush();
               return e.toString();
            }
            System.out.println("================================================");
            System.out.println("客户端(" + _address + ")传输" + zipName + "文件完毕");
            System.out.println("================================================");
         }
      }catch(IOException e){
         System.out.println("==============传输失败，原因如下：===============");
         System.out.println(e);
         System.out.println("================================================");
         return e.toString();
      }
      return null;
   }
}
