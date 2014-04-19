package com.zq.game.platform.service.server;

import org.mo.com.xml.FXmlNode;

//============================================================
//<T>服务器发布接口。</T>
//============================================================
public interface IServerDeployment 
{
   //============================================================
   // <T>服务器创建用户。</T>
   //
   // @param xcommand 传入参数
   // @param xoutputExecute 传出xml
   //============================================================
   void serverCreateUser(FXmlNode xcommand, 
                       FXmlNode xoutputExecute);
   
   //============================================================
   // <T>服务器创建目录。</T>
   //
   // @param xcommand 传入参数
   // @param xoutputExecute 传出xml
   //============================================================
   void makeDirs(FXmlNode xcommand, 
               FXmlNode xoutputExecute);
    
   //============================================================
   // <T>执行处理，返回结果</T>
   //
   // @param parameterCommand 传入命令
   // @param append 追加命令
   //============================================================
   String serverProcess(String parameterCommand,String append);
   
   //============================================================
   // <T>服务器获取资源。</T>
   //
   // @param xcommand 传入参数
   // @param xoutputExecute 传出xml
   //============================================================
   void secureResources(FXmlNode xcommand, 
                      FXmlNode xoutputExecute);
    
   //============================================================
   // <T>解压文件.tgz格式。</T>
   //
   // @param xcommand 传入参数
   // @param xoutputExecute 传出xml
   //============================================================
   void extractingResources(FXmlNode xcommand, 
                          FXmlNode xoutputExecute);
    
   //============================================================
   // <T>更改权限。</T>
   //
   // @param xcommand 传入参数
   // @param xoutputExecute 传出xml
   //============================================================
   void changePermissions(FXmlNode xcommand, 
                        FXmlNode xoutputExecute);
   
    //============================================================
    // <T>判断文件是否存在</T>
    //
    // @param path 文件路径
    //============================================================
    boolean existsFile(String path);
     
    //============================================================
    // <T>修改配置文件</T>
    //
    // @param xcommand  传入参数
    // @param filename 修改文件名称
    // @param xoutputExecute 传出xml
    //============================================================
    void changeConfig(FXmlNode xcommand,
                    String filename,
                    FXmlNode xoutputExecute);
    //============================================================
    // <T>修改内部配置文件</T>
    //
    // @param xcommand 传入参数
    // @param filename xml文件名称
    // @param xoutputExecute 传出xml
    //============================================================
     public void changeInsideConfig(FXmlNode xcommand,
                                    String filename,
                                    FXmlNode xoutputExecute);
    //============================================================
    // <T>压缩文件</T>
    //
    // @param xcommand 传入参数
    // @param filename 压缩后名称
    // @param xoutputExecute 传出xml
    //============================================================
    void compressedFiles(FXmlNode xcommand,
                       String filename,
                       FXmlNode xoutputExecute);   
    //============================================================
    // <T>修改数据表，的主键初始值</T>
    //
    // @param xcommand 传入参数
    // @param xoutputExecute 传出xml
    //============================================================
    void changeTableMajorKey(FXmlNode xcommand,
                           FXmlNode xoutputExecute);
     
    //============================================================
    // <T>创建数据库，导入数据表。</T>
    //
    // @param xcommand 传入参数
    // @param xoutputExecute 传出xml
    //============================================================
    void createDatabases(FXmlNode xcommand,
                         FXmlNode xoutputExecute);
    
   
}
