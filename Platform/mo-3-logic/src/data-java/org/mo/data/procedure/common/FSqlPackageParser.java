/*
 * @(#)FSqlPackageParser.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.data.procedure.common;

import org.mo.com.collections.FDataset;
import org.mo.com.collections.FRow;
import org.mo.com.data.FSql;
import org.mo.com.data.ISqlConnect;
import org.mo.com.data.MSqlConnect;
import org.mo.com.lang.FString;
import org.mo.com.lang.FStrings;
import org.mo.com.lang.IDump;
import org.mo.com.lang.RDump;
import org.mo.com.lang.RString;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.resource.IResource;
import org.mo.com.resource.RResource;
import org.mo.com.xml.FXmlNode;
import org.mo.data.dataset.EDatasetSourceType;

/**
 * <T>该类为存储数据的容器<br>
 * 根据包名查询数据库得出包中的内容，并解析包<br>
 * 生成解析后的包，包中包括function、procedure及参数</T>
 * 
 * @author maocy
 * @version 1.00 - 2008/09/03
 */
public class FSqlPackageParser
      extends MSqlConnect
      implements
         IDump
{
   private static String _encoding = "UTF-8";

   private static IResource _resource = RResource.find(FSqlPackageParser.class);

   /**
    * 函数的分割符标志
    */
   // repeat方法是“-”字符串重复60次组成一个新的字符串
   public final static String FUNCTION_SPLITTER = RString.repeat("-", 60);

   public final static String PACKAGE_BEGIN = "-- Package define begin ------------------------------------";

   public final static String PACKAGE_END = "-- Package define End --------------------------------------";

   /**
    * 解析描述线标志
    */
   public final static String PARSE_DESCRIPTION_LINE = RString.repeat("-", 60);

   /**
    * 用户代码的标志
    */
   public final static String USER_DEFINE = "user.define";

   /**
    * 用户代码的开始标志
    */
   public final static String USER_DEFINE_BEGIN = "-------------------- Define Begin --------------------------";

   /**
    * 用户结束的结束标志
    */
   public final static String USER_DEFINE_END = "-------------------- Define End ----------------------------";

   protected FSqlParserContext _context = new FSqlParserContext();

   // 描述信息
   private String _description = "";

   // function（方法）集合
   private final FSqlFunctions _functions = new FSqlFunctions();

   // 被解析包的包名
   private String _name = null;

   // procedure（过程）的集合
   private final FSqlProcedures _procedures = new FSqlProcedures();

   /**
    * <p>用父类的构造函数创建PackageParser对象</p>
    * 
    * @param oConnection 连接数据库           
    * @param packageName 包的名字           
    * @ 
    */
   public FSqlPackageParser(ISqlConnect connect,
                            String packageName){
      super(connect);
      setName(packageName);
   }

   //获得包体的Sql语句，包体包括function和procedure的处理
   protected FString bodySql(){
      String source = _resource.findString("package.body");
      source = RString.parse(source, "name", _name);
      return new FSql(source);
   }

   /**
    * <p>获得描述信息</p>
    * 
    * @return描述信息（String类型）
    */
   public String description(){
      return _description;
   }

   @Override
   public TDumpInfo dump(TDumpInfo info){
      RDump.dump(info, this);
      info.appendLine();
      info.appendLine(RDump.LINE_L2);
      info.append(_functions.dump());
      info.appendLine(RDump.LINE_L2);
      info.append(_procedures.dump());
      return info;
   }

   /**
    * <p>获得包体，包括包中的function方法体和procedure的过程体</p>
    * 
    * @return 记录集合（FStrings类型)
    */
   public FStrings fetchBody(){
      FStrings lines = new FStrings();
      FDataset ds = activeConnection().fetchDataset(bodySql().toString());
      if(ds != null){
         for(FRow row : ds){
            lines.push(row.get("TEXT"));
         }
      }
      return lines;
   }

   public FString fetchBodyString(){
      FString source = new FString();
      FDataset ds = activeConnection().fetchDataset(bodySql().toString());
      if(ds != null){
         for(FRow row : ds){
            source.append(row.get("TEXT"));
         }
      }
      return source;
   }

   /**
    * <p>获得包头，包括包中的function方法定义和procedure过程定义</p>
    * 
    * @return 记录集合（FString类型）
    */
   public FStrings fetchHead(){
      FStrings lines = new FStrings();
      FDataset ds = activeConnection().fetchDataset(headSql().toString());
      if(ds != null){
         for(FRow row : ds){
            lines.push(row.get("TEXT"));
         }
      }
      return lines;
   }

   public FString fetchHeadString(){
      FString source = new FString();
      FDataset ds = activeConnection().fetchDataset(headSql().toString());
      if(ds != null){
         for(FRow row : ds){
            source.append(row.get("TEXT"));
         }
      }
      return source;
   }

   /**
    * 获得用户定义代码。
    * 
    * @param type 代码类型
    * @return 用户定义代码
    */
   public FString fetchUserDefine(EDatasetSourceType type){
      // 获得数据库数据包
      FStrings lines = null;
      if(EDatasetSourceType.PackageHead == type){
         lines = fetchHead();
      }else if(EDatasetSourceType.PackageBody == type){
         lines = fetchBody();
      }
      // 查找用户定义的部分
      int begin = 0, end = 0;
      int count = lines.count();
      for(int i = 0; i < count; i++){
         String line = lines.get(i);
         if(RString.isNotEmpty(line)){
            line = line.trim();
            if(USER_DEFINE_BEGIN.equals(line)){
               begin = i;
            }else if(USER_DEFINE_END.equals(line)){
               end = i;
            }
         }
      }
      // 获取用户定义的部分
      FString source = new FString();
      for(int i = begin + 1; i < end; i++){
         source.append(lines.get(i));
      }
      return source;
   }

   /**
    * <p>获得function方法集合</p>
    * 
    * @return function 方法集合
    */
   public FSqlFunctions functions(){
      return _functions;
   }

   // 获得包头的Sql语句（包头包括：function和procedure的定义声明）
   protected FString headSql(){
      String source = _resource.findString("package.head");
      source = RString.parse(source, "name", _name);
      return new FSql(source);
   }

   public String logicName(){
      String source = fetchHead().join();
      String find = RString.mid(source.toString(), "LG_NAME", "\n");
      return RString.mid(find, "'", "'");
   }

   /**
    * <p>生成XML节点该节点可以包括子节点<br>
    * 根结点为package包名<br>
    * 下一节点为function节点和<br>
    * procedure节点<br>
    * function和procedure节点的子节点为参数</p>
    * 
    * @return 根结点
    */
   public FXmlNode makeXmlNode(){
      FSqlParser parser = new FSqlParser();
      FSqlPackage sqlParser = parser.parse(fetchHeadString(), fetchBodyString(), name(), _encoding);
      return sqlParser.makeNode();
   }

   /**
    * <p> 获得包名</p>
    * 
    * @return 包名（String类型）
    */
   public String name(){
      return _name;
   }

   /**
    * <p>获得procedure过程集合</p>
    * 
    * @return procedure过程集合（FSqlProcedures类型）
    */
   public FSqlProcedures procedures(){
      return _procedures;
   }

   /**
    * <p>设置描述信息</p>
    * 
    * @param sValue 描述信息          
    * @return TRUE? 设置成功<br>
    *          FALSE? 设置失败
    */
   public boolean setDescription(String sValue){
      _description = sValue;
      return true;
   }

   /**
    * <p>设置包名</p>
    * 
    * @param packageName 新包名
    * @return TRUE?设置成功FALSE设置失败
    */
   public void setName(String packageName){
      _name = packageName;
   }
}
