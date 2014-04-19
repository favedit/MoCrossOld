package org.mo.data.database;

import org.mo.com.collections.FDataset;
import org.mo.com.collections.FRow;
import org.mo.com.data.FSql;
import org.mo.com.data.ISqlConnection;
import org.mo.com.lang.FObject;
import org.mo.core.aop.face.ALink;
import org.mo.core.aop.face.AProperty;
import org.mo.eng.data.FConnectionConsole;
import org.mo.eng.data.IConnectionConsole;
import org.mo.eng.data.IDatabaseConsole;

//============================================================
// <T>数据链接控制台。</T>
//============================================================ 
public class FDataDatabaseConsole
      extends FObject
      implements
         IDataDatabaseConsole
{
   // 链接鉴定字符串
   public final static String ID_CONNECTION = "SYNC:CONN:";

   // 主域数据库名称
   @AProperty
   protected String _databaseDomainName;

   // 分析数据库名称
   @AProperty
   protected String _databaseAnalysisName;

   // 数据库链接控制台
   @ALink
   protected IDatabaseConsole _databaseConsole;

   //============================================================
   // <T>加载处理。</T>
   //============================================================
   @Override
   public void initialize(){
      // 收集数据库链接
      ISqlConnection connection = _databaseConsole.alloc(_databaseDomainName);
      //............................................................
      // 获得数据库集合
      FSql databaseSql = new FSql("SELECT * FROM DM_DATA_DATABASE");
      FDataset databaseDataset = connection.fetchDataset(databaseSql.toString());
      for(FRow databaseRow : databaseDataset){
         // 获得信息
         String id = databaseRow.get("ouid");
         String host = databaseRow.get("host");
         String port = databaseRow.get("port");
         String passport = databaseRow.get("passport");
         String password = databaseRow.get("password");
         String databaseName = databaseRow.get("database_name");
         // 创建链接控制台
         FConnectionConsole connectionConsole = new FConnectionConsole();
         connectionConsole.setName(ID_CONNECTION + id);
         connectionConsole.setDriverClass("org.mo.data.driver.FSqlMysqlConnection");
         connectionConsole.setDriverName("com.mysql.jdbc.Driver");
         connectionConsole.setUrl("jdbc:mysql://" + host + ":" + port + "/" + databaseName);
         connectionConsole.setPassport(passport);
         connectionConsole.setPassword(password);
         connectionConsole.setInitConnectionNumber(4);
         connectionConsole.setMaxConnectionNumber(256);
         connectionConsole.initializeConnections();
         // 放入管理器
         _databaseConsole.add(connectionConsole);
      }
      //............................................................
      // 释放数据库链接
      _databaseConsole.free(connection);
   }

   //============================================================
   // <T>获得主域链接控制台。</T>
   //
   // @return 链接控制台
   //============================================================
   @Override
   public IConnectionConsole domainConnectionConsole(){
      return _databaseConsole.connectionConsole(_databaseDomainName);
   }

   //============================================================
   // <T>获得分析链接控制台。</T>
   //
   // @return 链接控制台
   //============================================================
   @Override
   public IConnectionConsole analysisConnectionConsole(){
      return _databaseConsole.connectionConsole(_databaseAnalysisName);
   }

   //============================================================
   // <T>获得数据链接控制台。</T>
   //
   // @return 链接控制台
   //============================================================
   @Override
   public IConnectionConsole dataConnectionConsole(long id){
      return _databaseConsole.connectionConsole(ID_CONNECTION + id);
   }
}
