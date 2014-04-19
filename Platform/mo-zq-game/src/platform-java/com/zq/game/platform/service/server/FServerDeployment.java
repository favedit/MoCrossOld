package com.zq.game.platform.service.server;

import java.io.File;

import org.mo.com.system.FProcess;
import org.mo.com.xml.FXmlDocument;
import org.mo.com.xml.FXmlNode;

//============================================================
//<T>服务器发布。</T>
//============================================================
public class FServerDeployment 
     implements IServerDeployment{
   
   //============================================================
   // <T>修改配置文件</T>
   //
   // @param xcommand 传入参数
   // @param filename xml文件名称
   // @param xoutputExecute 传出xml
   //============================================================
    public void changeConfig(FXmlNode xcommand,
                             String filename,
                             FXmlNode xoutputExecute){
       String changeConfig = null;
       System.out.println("/home/"+xcommand.get("user", null)+"/"+xcommand.get("project_name", null)+"/Game/Configuration/global.xml");
       //判断文件是否存在
       if(!existsFile("/home/"+xcommand.get("user", null)+"/"+xcommand.get("project_name", null)+"/Game/Configuration/global.xml")){
          try {
             FXmlDocument xdoc = new FXmlDocument();
             xdoc.loadFile("/home/"+xcommand.get("user", null)+"/"+xcommand.get("project_name", null)+"/Game/Configuration/global.xml");
             for(FXmlNode node : xdoc.root().nodes()){
                    String comName = node.get("name");//Server.ClientTransfer
                    //修改外部端口
                    if(comName.equals("Game.Gate.Client.Transfer")){
                       for (FXmlNode no : node.nodes()) {
                          String name = no.name();
                          if(name.equals("Host")){
                             no.set("host", "*");
                             no.set("port", xcommand.get("client_port",null));
                          }
                      }
                    //设置数据库连接
                    }else if(comName.equals("Module.Storage.Database") || comName.equals("Module.Logger.Database")){
                       for (FXmlNode no : node.nodes()) {
                          String name = no.name();
                          if(name.equals("Connection")){
                             no.set("passport", xcommand.get("passport",null));
                             no.set("password", xcommand.get("password",null));
                             no.set("host", xcommand.get("db_host",null));
                             no.set("port", xcommand.get("db_port",null));
                          }
                       }
                    }
             }
             xdoc.saveFile("/home/"+xcommand.get("user", null)+"/"+xcommand.get("project_name", null)+"/Game/Configuration/global.xml");
             changeConfig = "外部端口和数据库连接修改成功";
          } catch (Exception e) {
             changeConfig = e.getMessage();
          }
       }else{
          changeConfig = "global.xml文件不存在";
       }
        //得到结果
      FXmlNode xoutputClient = xoutputExecute.createNode("Message",changeConfig);
      xoutputClient.set("name", "changeConfig");
   }
    
    //============================================================
    // <T>修改内部配置文件</T>
    //
    // @param xcommand 传入参数
    // @param filename xml文件名称
    // @param xoutputExecute 传出xml
    //============================================================
     public void changeInsideConfig(FXmlNode xcommand,
                                    String filename,
                                    FXmlNode xoutputExecute){
        String changeConfig = null;
        System.out.println("/home/"+xcommand.get("user", null)+"/"+xcommand.get("project_name", null)+"/Game/Configuration/global.xml");
        //判断文件是否存在
        if(!existsFile("/home/"+xcommand.get("user", null)+"/"+xcommand.get("project_name", null)+"/Game/Configuration/global.xml")){
           try {
              FXmlDocument xdoc = new FXmlDocument();
              xdoc.loadFile("/home/"+xcommand.get("user", null)+"/"+xcommand.get("project_name", null)+"/Game/Configuration/global.xml");
              
              //System.out.println(xdoc);
              //System.out.println(xdoc.root().nodes()+"*****");
              for(FXmlNode node : xdoc.root().nodes()){
                     String comName = node.get("name");//Server.ClientTransfer
                     //设置内部端口
                     if(comName.equals(filename)){
                       for (FXmlNode no : node.nodes()) {
                         String name = no.name();
                         if(name.equals("Host")){
                            String temp = xcommand.get("host",null);
                            no.set("host", temp);
                            no.set("port", xcommand.get("port",null));
                         }
                       }
                     //设置内部端口
                     }else if(filename.equals("Game.Proxy.Server.Transfer") && comName.equals("Game.Proxy.Module")){
                      for (FXmlNode no : node.nodes()) {
                             String name = no.get("name");
                             if(name.equals("host")){
                                no.setText("127.0.0.1");
                             }
                             if(name.equals("port")){
                                no.setText(xcommand.get("port",null));
                             }
                         }
                    }
              }
              xdoc.saveFile("/home/"+xcommand.get("user", null)+"/"+xcommand.get("project_name", null)+"/Game/Configuration/global.xml");
              changeConfig = filename+"修改成功";
           } catch (Exception e) {
              changeConfig = e.getMessage();
           }
        }else{
           changeConfig = "global.xml文件不存在";
        }
      //得到结果
      FXmlNode xoutputClient = xoutputExecute.createNode("Message",changeConfig);
      xoutputClient.set("name", "changeConfig");
   
    }
   //============================================================
   // <T>判断文件是否存在</T>
   //
   // @param path 文件路径
   //============================================================
   public boolean existsFile(String path){
      File file = new File(path);
      return file.exists() ? false : true;         
   }
   
   //============================================================
   // <T>执行处理</T>
   //
   // @param parameterCommand 传入命令
   // @param append 追加命令
   //============================================================
   public String serverProcess(String parameterCommand,String append){
      FProcess systemProcess = new FProcess();
      systemProcess.setOptionWait(true);
      systemProcess.setCommand("sh");
      systemProcess.addParameter("-c");
      systemProcess.addParameter(parameterCommand);
      systemProcess.appendLine(append);
      System.out.println(parameterCommand+"=================="+append);
      systemProcess.start();
      return systemProcess.fetchResult();
   }
   
   //============================================================
   // <T>服务器创建用户。</T>
   //
   // @param xcommand 传入参数
   // @param xoutputExecute 传出xml
   //============================================================
   @Override
   public void serverCreateUser(FXmlNode xcommand, 
                              FXmlNode xoutputExecute) {
      String userResult = null;
      if(existsFile("/home/"+xcommand.get("user",null)+"")){
         serverProcess("useradd -d /home/" +xcommand.get("user",null)+ " " + xcommand.get("user",null) + "",null);
         userResult="用户创建成功";
      }else{
         userResult="用户创建失败，此用户已被创建";
      }
      FXmlNode xoutputCreateuser = xoutputExecute.createNode("Message",userResult);      
      xoutputCreateuser.set("name", "createUser");
   }
   
   //============================================================
    // <T>服务器创建目录。</T>
   //
   // @param xcommand 传入参数
   // @param xoutputExecute 传出xml
    //============================================================
   @Override
   public void makeDirs(FXmlNode xcommand, 
                      FXmlNode xoutputExecute) {
      String makeDir = null;
      //File file = new File("/data/home/"+xcommand.get("user",null)+"/WP-Server");
      if(existsFile("/home/"+xcommand.get("user",null)+"/"+xcommand.get("project_name",null))){
          serverProcess("su - "+xcommand.get("user",null),"mkdir "+xcommand.get("project_name",null));
          makeDir = "文件创建成功";
      }else{
         makeDir = "此目录已存在";
      }
      FXmlNode xoutputMakeDir = xoutputExecute.createNode("Message",makeDir);
      xoutputMakeDir.set("name", "createDir");
   }
   
   //============================================================
   // <T>服务器获取资源。</T>
   //
   // @param xcommand 传入参数
   // @param xoutputExecute 传出xml
   //============================================================
   @Override
   public void secureResources(FXmlNode xcommand,
                             FXmlNode xoutputExecute) {
      String sercure = null;
       try {
          if(existsFile("/data/temp/"+xcommand.get("package_name", null))){
             serverProcess("su - root","scp root@"+xcommand.get("package_url", null)+":/data/lcwpack/"+xcommand.get("package_name", null)+" /data/temp/");
            serverProcess("su - root","chown -R "+xcommand.get("user",null)+"."+xcommand.get("user",null)+" /data/temp/"+xcommand.get("package_name", null));
            sercure = "资源获取成功";
          }else{
             sercure = "资源 已存在";
          }
      } catch (Exception e) {
         sercure = "资源获取失败，"+e.getMessage();;
      }
      FXmlNode xoutputSercure = xoutputExecute.createNode("Message",sercure);
      xoutputSercure.set("name", "secureResources");
   }
   
   //============================================================
   // <T>解压文件.tgz格式。</T>
   //
   // @param xcommand 传入参数
   // @param xoutputExecute 传出xml
   //============================================================
   @Override
   public void extractingResources(FXmlNode xcommand,
                                 FXmlNode xoutputExecute) {
      String extraction = null;
      try {
         serverProcess("su - "+xcommand.get("user",null),"tar -zxvf  /data/temp/"+xcommand.get("package_name", null)+
                       " -C /home/"+xcommand.get("user", null)+"/"+xcommand.get("project_name", null)+"/");
            extraction = "资源解压成功";
      } catch (Exception e) {
         extraction = "资源解压失败，"+e.getMessage();   
      }
      
      FXmlNode xoutputExtracting = xoutputExecute.createNode("Message",extraction);
      xoutputExtracting.set("name", "extractingResources");
   }
   
   //============================================================
   // <T>更改权限。</T>
   //
   // @param xcommand 传入参数
   // @param xoutputExecute 传出xml
   //============================================================   
   @Override
   public void changePermissions(FXmlNode xcommand, 
                               FXmlNode xoutputExecute) {
      String permissions = null;
      try {
         serverProcess("su - "+xcommand.get("user", null),"chmod a+x /home/"+xcommand.get("user",null)+"/"+xcommand.get("project_name", null)+"/Bin -R");
         serverProcess("su - "+xcommand.get("user", null),"chmod a+x /home/"+xcommand.get("user",null)+"/"+xcommand.get("project_name", null)+"/Game -R");
         permissions = "更改权限成功";
      } catch (Exception e) {
         permissions = "更改权限失败 ，"+e.getMessage();
      }
      FXmlNode xoutputExtracting = xoutputExecute.createNode("Message",permissions);
      xoutputExtracting.set("name", "changePermissions");
   }
   
   //============================================================
   // <T>压缩文件</T>
   //
   // @param xcommand 传入参数
   // @param filename 压缩后名称
   // @param xoutputExecute 传出xml
   //============================================================
   @Override
   public void compressedFiles(FXmlNode xcommand,
                             String filename,
                             FXmlNode xoutputExecute) {
      if(existsFile("/data/"+filename+"")  || !existsFile("/data/home/"+xcommand.get("user", null)+"/WP-Server/mysql")){
         serverProcess("zip -r  /data/"+filename+" /data/home/"+xcommand.get("user", null)+"/WP-Server/mysql",null);
      }
   }

   //============================================================
   // <T>修改数据表的主键初始值</T>
   //
   // @param xcommand 传入参数
   // @param xoutputExecute 传出xml
   //============================================================
   @Override
   public void changeTableMajorKey(FXmlNode xcommand,
                                 FXmlNode xoutputExecute) {
      String tableMajorKey = null;
      try {
         if(!existsFile("/home/"+xcommand.get("user", null)+"/"+xcommand.get("project_name", null)+"/Bin/sql/mysql_create.sql")){
            serverProcess("sed -i \'s/20000000000/"+xcommand.get("server_id",null)+"0000000000/g\' /home/"
                                    +xcommand.get("user", null)+"/"+xcommand.get("project_name", null)+"/Bin/sql/mysql_create.sql",null);
            tableMajorKey = "修改主键成功";
         }else{
            tableMajorKey = "文件不存在！";
         }
         
      } catch (Exception e) {
         tableMajorKey = "修改主键失败，"+e.getMessage();
      }
      FXmlNode xoutputExtracting = xoutputExecute.createNode("Message",tableMajorKey);
      xoutputExtracting.set("name", "changeTableMajorKey");
   }

   //============================================================
   // <T>创建数据库，导入数据表。</T>
   //
   // @param xcommand 传入参数
   // @param xoutputExecute 传出xml
   //============================================================
   @Override
   public void createDatabases(FXmlNode xcommand, 
                             FXmlNode xoutputExecute) {
      String createDatabase = null;
      try {
         FProcess systemProcess = new FProcess();
         systemProcess.setOptionWait(true);
         systemProcess.setCommand("sh");
         systemProcess.addParameter("-c");
        // systemProcess.addParameter("su - root");
         //systemProcess.appendLine("service mysqld start");
         systemProcess.addParameter("su - "+xcommand.get("user",null));
         //systemProcess.appendLine("mysql -ugame -pgame --default-character-set=utf8");
         systemProcess.appendLine("mysql");
         systemProcess.appendLine("CREATE DATABASE `dbname_game_qkb_wk` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;");
         systemProcess.appendLine("CREATE DATABASE `dbname_logger_qkb_wk` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;  ");
         systemProcess.appendLine("use game;");
         systemProcess.appendLine("source ~/"+xcommand.get("project_name", null)+"/Bin/sql/gm_role_name_release.sql;");
         systemProcess.appendLine("source ~/"+xcommand.get("project_name", null)+"/Bin/sql/mysql_create.sql;");
         systemProcess.appendLine("source ~/"+xcommand.get("project_name", null)+"/Bin/sql/gm_shop_item_tz.sql;");
         systemProcess.appendLine("source ~/"+xcommand.get("project_name", null)+"/Bin/sql/data_for_release_tz.sql;");
         systemProcess.appendLine("source ~/"+xcommand.get("project_name", null)+"/Bin/sql/rank.sql;");
         systemProcess.appendLine("use logger; ");
         systemProcess.appendLine("source ~/"+xcommand.get("project_name", null)+"/Bin/sql/Logger.create.sql;");
         systemProcess.start();
         createDatabase = "创建数据库，导入数据据结构成功";
      } catch (Exception e) {
         createDatabase = "创建失败，"+e.getMessage();
      }
      FXmlNode xoutputExtracting = xoutputExecute.createNode("Message",createDatabase);
      xoutputExtracting.set("name", "createDatabase");
   }
   
   
   
}
