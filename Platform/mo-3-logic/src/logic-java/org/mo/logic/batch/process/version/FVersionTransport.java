package org.mo.logic.batch.process.version;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import org.mo.com.lang.FStrings;
import org.mo.com.lang.IAttributes;
import org.mo.com.lang.RString;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlNodes;
import org.mo.logic.batch.process.FBatchSqlCommand;

public class FVersionTransport
      extends FBatchSqlCommand
      implements
         IVersionTransport
{
   // 客户端文件路径（属性名称）
   private final static String CLIENT_PATH = "client_path";

   // 客户端文件路径（属性名称）
   private final static String SERVER_IP = "server_ip";

   // 客户端文件路径（属性名称）
   private final static String SERVER_SPORT = "server_sport";

   // 服务器文件路径（属性名称）
   private final static String SERVER_PATH = "server_path";

   // 配置文件路径（属性名称）
   private final static String COMMAND_PATH = "command_path";

   // 配置文件名称（属性名称）
   private final static String COMMAND_NAME = "command_name";

   private DataInputStream _read = null;

   private DataOutputStream _write = null;

   // 连接标识
   private String MARK_CONNECT = "connect";

   // 关闭标识
   private String MARK_BREAK = "break";

   // 成功标识
   private String MARK_SUCCESS = "success";

   // 失败标识
   private String MARK_FAIL = "fail";

   // 原因标识
   private String MARK_CAUSE = "cause";

   // 传输文件标识
   private String MARK_TRANSPORT = "transport";

   // 执行命令标识
   private String MARK_COMMAND = "command";

   // 服务器socket
   private Socket _server = null;

   // 服务器IP地址
   private String _address = "";

   // 服务器端口号
   private int _sport = 0;

   protected void close(){
      // 关闭所有连接
      try{
         _write.writeUTF(MARK_BREAK);
         _write.flush();
         _read.close();
         _write.close();
         _server.close();
      }catch(Exception e){
         System.out.println("================================================");
         System.out.println("与服务器IP(" + _address + ")经断开连接失败，原因如下：");
         System.out.println(e);
         System.out.println("================================================");
         return;
      }
      System.out.println("================================================");
      System.out.println("与服务器IP(" + _address + ")已经断开连接，成功关闭所有连接。");
      System.out.println("================================================");
   }

   @Override
   public void commandClient(FXmlNode config,
                             FStrings allLog,
                             FStrings failLog){
      _address = config.get(SERVER_IP);
      _sport = config.getInt(SERVER_SPORT);
      IAttributes items[] = getItem(config);
      boolean isConnect = connectServer();
      if(isConnect){
         if(null != items){
            int length = items.length;
            boolean isContinue = false;
            for(int n = 0; n < length; n++){
               IAttributes item = items[n];
               try{
                  if(isContinue){
                     _write.writeUTF("continue");
                     _write.flush();
                     System.out.println("==========================================");
                     System.out.println("继续执行");
                     System.out.println("==========================================");
                  }
                  String configPath = item.get(COMMAND_PATH);
                  String configName = item.get(COMMAND_NAME);
                  // 连接服务器
                  // 告诉服务器做执行命令操作
                  _write.writeUTF(MARK_COMMAND);
                  _write.flush();
                  // 写入批处理配置文件目录
                  _write.writeUTF(COMMAND_PATH);
                  _write.flush();
                  _write.writeUTF(configPath);
                  _write.flush();
                  // 写入批处理配置文件名称
                  _write.writeUTF(COMMAND_NAME);
                  _write.flush();
                  _write.writeUTF(configName);
                  _write.flush();
                  String ok = _read.readUTF();
                  if("ok".equalsIgnoreCase(ok)){
                     // 开始执行
                     _write.writeUTF("execute");
                     _write.flush();
                     System.out.println("==========================================");
                     System.out.println("执行" + configName + "命令开始");
                     System.out.println("==========================================");
                     System.out.println("==========================================");
                     System.out.println("请等待，服务器正在处理中！");
                     System.out.println("==========================================");
                  }else{
                     // 执行失败
                     String failmark = _read.readUTF();
                     if(MARK_FAIL.equalsIgnoreCase(failmark)){
                        System.out.println("==========================================");
                        System.out.println("执行命令失败！");
                     }
                     failmark = _read.readUTF();
                     if(MARK_CAUSE.equalsIgnoreCase(failmark)){
                        System.out.println("失败原因：" + _read.readUTF());
                     }
                     System.out.println("==========================================");
                  }
                  // 判断执行结果是否成功
                  String result = _read.readUTF();
                  if(MARK_SUCCESS.equalsIgnoreCase(result)){
                     // 关闭所有连接和流
                     System.out.println("==========================================");
                     System.out.println("执行(" + configName + ")命令成功完成！");
                     System.out.println("==========================================");
                  }else if(MARK_FAIL.equalsIgnoreCase(result)){
                     // 执行失败
                     System.out.println("==========================================");
                     System.out.println("执行(" + configName + ")命令失败！");
                     String mark = _read.readUTF();
                     if(MARK_CAUSE.equalsIgnoreCase(mark)){
                        System.out.println("失败原因：" + _read.readUTF());
                        System.out.println("==========================================");
                     }
                     close();
                  }
               }catch(Exception e){
                  System.out.println("==========================================");
                  System.out.println("执行命令失败！");
                  System.out.println("失败原因：" + e.toString());
                  System.out.println("==========================================");
                  close();
               }
               isContinue = true;
            }
            // 关闭所有连接
            close();
         }
      }else{
         System.out.println("==========================================");
         System.out.println("连接服务器(IP" + _address + ")端口：(" + _sport + ")失败！");
         System.out.println("==========================================");
         close();
      }
   }

   protected boolean connectServer(){
      try{
         if(RString.isEmpty(_address) && _sport >= 0){
            System.out.println("==========================================");
            System.out.println("连接服务器的IP:" + _address + "   端口号：" + _sport + "为空!");
            System.out.println("==========================================");
            return false;
         }
         if(null == _server){
            _server = new Socket(_address, _sport);
         }
         // 创建与服务器连接输入流
         if(null == _read){
            _read = new DataInputStream(_server.getInputStream());
         }
         // 传进传出流目的是写入信息
         if(null == _write){
            _write = new DataOutputStream(_server.getOutputStream());
         }
         String state = _read.readUTF();
         if(MARK_CONNECT.equalsIgnoreCase(state)){
            System.out.println("==========================================");
            System.out.println("已经成功连接服务器IP:" + _address + "   端口号：" + _sport);
            System.out.println("==========================================");
         }else{
            System.out.println("==========================================");
            System.out.println("连接服务器IP:" + _address + "   端口号：" + _sport + "失败！");
            System.out.println("==========================================");
            return false;
         }
      }catch(Exception e){
         System.out.println("==========================================");
         System.out.println("连接服务器IP:" + _address + "   端口号：" + _sport + "失败！原因如下：");
         System.out.println(e);
         System.out.println("==========================================");
         return false;
      }
      return true;
   }

   protected IAttributes[] getItem(FXmlNode config){
      if(null != config){
         FXmlNodes itemNodes = config.nodes("Item");
         IAttributes items[] = new IAttributes[itemNodes.count()];
         int n = 0;
         for(FXmlNode itemNode : itemNodes){
            IAttributes item = itemNode.attributes();
            items[n] = item;
            n++;
         }
         return items;
      }
      return null;
   }

   /**
    * <T>获得指定目录下的zip文件目录</T>
    * <P>如果没有指定zip文件，则查找最后版本的zip文件目录</p>
    * 
    */
   protected String getZipName(String zipPath){
      if(RString.isEmpty(zipPath)){
         zipPath = "D:/eUIS-Store";
      }
      try{
         File file = new File(zipPath);
         // 判断指定的目录是否为zip文件包
         if(file.isFile()){
            return zipPath;
         }
         // 查找最后一个导入文件
         String newPath = "";
         for(File zipFile : file.listFiles()){
            String path = zipFile.getAbsolutePath();
            if(path.compareTo(zipPath) > 0){
               newPath = path;
            }
         }
         return newPath;
      }catch(Exception e){
         System.out.println("========================================");
         System.out.println(e);
         System.out.println("查找zip对象失败！");
         System.out.println("========================================");
      }
      return null;
   }

   @Override
   public void transportClient(FXmlNode config,
                               FStrings allLog,
                               FStrings failLog){
      _address = config.get(SERVER_IP);
      _sport = config.getInt(SERVER_SPORT);
      IAttributes items[] = getItem(config);
      if(null != items){
         int length = items.length;
         for(int n = 0; n < length; n++){
            IAttributes item = items[n];
            try{
               // 连接服务器
               boolean isConnect = connectServer();
               if(isConnect){
                  // 告诉服务器做版本文件传输操作
                  _write.writeUTF(MARK_TRANSPORT);
                  _write.flush();
                  // 获得服务器保存文件路径
                  String serverOutput = item.get(SERVER_PATH);
                  // 获得本地版本文件
                  String intputFile = item.get(CLIENT_PATH);
                  String zipPath = getZipName(intputFile);
                  File file = new File(zipPath);
                  if(file.canRead()){
                     // 传输zip名字
                     String zipName = file.getName();
                     System.out.println("==========================================");
                     System.out.println("开始传输zip版本文件：" + zipName);
                     System.out.println("==========================================");
                     _write.writeUTF(serverOutput);
                     _write.flush();
                     _write.writeUTF(zipName);
                     _write.flush();
                     String result = "";
                     // 创建读取zip文件流
                     DataInputStream readZip = new DataInputStream(new FileInputStream(file));
                     byte[] buf = new byte[1024];
                     // 读取zip文件
                     System.out.println("==========================================");
                     System.out.println("处理中请稍后.........");
                     System.out.println("==========================================");
                     int count = 0;
                     double a = 0;
                     long fileLength = file.length();
                     _write.writeLong(fileLength);
                     _write.flush();
                     while(true){
                        boolean yes = true;
                        int writeCount = 0;
                        int canReadLen = 1024;
                        while(true){ // 是否读完文件
                           canReadLen = (int)(fileLength - writeCount);
                           if(canReadLen > 1024){
                              canReadLen = 1024;
                           }else if(canReadLen < 0){
                              canReadLen = 0;
                           }
                           try{
                              count = readZip.read(buf, 0, canReadLen);
                              if(count <= 0){
                                 break;
                              }
                           }catch(IOException ioe){
                              System.out.println("==========================================");
                              System.out.println("读取zip包时出错，原因如下：");
                              System.out.println(ioe.toString());
                              System.out.println("==========================================");
                           }
                           _write.write(buf, 0, canReadLen); // 把文件数据写出网络缓冲区 
                           _write.flush(); // 刷新缓冲区把数据写往客户端
                           writeCount = writeCount + count;
                           a = a + count;
                           int c = (int)((a / fileLength) * 100);
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
                        }
                        // 结束该文件传输
                        //                        _write.write("0003".getBytes());
                        //                        _write.flush();
                        //                        _write.write("end".getBytes());
                        //                        _write.flush();
                        // 判断是否结束传输
                        String markResult = _read.readUTF();
                        if("ok".equalsIgnoreCase(markResult)){
                           System.out.println("==========================================");
                           System.out.println("版本文件：" + zipName + "传输成功完毕！");
                           System.out.println("==========================================");
                           readZip.close();
                           break;
                        }
                     }
                     result = _read.readUTF();
                     if(MARK_FAIL.equalsIgnoreCase(result)){
                        System.out.println("==========================================");
                        System.out.println("版本文件：" + zipName + "传输失败！");
                        if(MARK_CAUSE.equalsIgnoreCase(_read.readUTF())){
                           String cause = _read.readUTF();
                           System.out.println("错误原因：" + cause);
                        }
                        System.out.println("==========================================");
                     }else if(MARK_SUCCESS.equalsIgnoreCase(result)){
                        // 继续传输
                     }
                  }
               }
            }catch(Exception e){
               System.out.println("==========传输文件失败，失败原因如下：=======");
               System.out.println(e);
               System.out.println("==========================================");
               break;
            }
         }
         // 传输文件结束
         try{
            // 关闭所有连接和流
            _write.writeUTF(MARK_BREAK);
            _write.flush();
            close();
         }catch(Exception e){
            System.out.println("===============关闭连接失败！==============");
            System.out.println("出错原因：" + e);
            System.out.println("==========================================");
         }
      }
   }

   @Override
   public void versionServer(FXmlNode config,
                             FStrings allLog,
                             FStrings failLog){
      ServerSocket server = null;
      //for(FXmlNode serverNode : config.node("Item")){
      if(null != config){
         int serverSport = config.getInt("sport");
         try{
            server = new ServerSocket(serverSport);
            if(null != server && !server.isClosed()){
               System.out.println("===========================================");
               System.out.println("已启动服务器：IP " + InetAddress.getLocalHost().getHostAddress() + "  端口号 " + serverSport);
               System.out.println("===========================================");
               while(true){
                  System.out.println("===========================================");
                  System.out.println("等待客户端连接！");
                  System.out.println("===========================================");
                  Socket client = server.accept();
                  if(null != client && client.isConnected()){
                     TransportThread clientThread = new TransportThread(client, config);
                     clientThread.start();
                  }else{
                     System.out.println("===========================================");
                     System.out.println("等待客户端连接！");
                     System.out.println("===========================================");
                  }
               }
            }else{
               System.out.println("===========================================");
               System.out.println("启动服务器：IP " + "server.getInetAddress().getHostAddress();" + "端口号 " + "" + "失败！");
               System.out.println("===========================================");
            }
         }catch(IOException e){
         }
      }
      //}
   }
}
