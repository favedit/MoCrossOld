package org.mo.eng.data.common;

import org.mo.com.collections.FRow;
import org.mo.com.data.ISqlConnect;
import org.mo.com.data.ISqlConnection;
import org.mo.com.lang.FString;
import org.mo.com.message.FMessages;

public class FSqlTable
{
   protected ISqlConnection _connection = null;

   //private ILogger _logger = RLogger.find(FSqlTable.class);
   // 消息列表
   protected FMessages _messages = null;

   // 包名称
   protected String _table = null;

   /**
    * <p>创建数据库包处理对象的实例</p>
    * <p>create date:2005/11/03</p>
    * 
    * @param iConnectionAble 可获得链接的接口
    */
   public FSqlTable(ISqlConnect connect){
      this(connect, null);
   }

   /**
    * <p>创建数据库包处理对象的实例</p>
    * <p>create date:2005/11/03</p>
    * 
    * @param iConnectionAble 可获得链接的接口
    * @param messages 消息列表
    * @param sPackageName 包名称
    */
   public FSqlTable(ISqlConnect connect,
                    FMessages messages){
      _connection = connect.activeConnection();
      _messages = messages;
   }

   protected boolean clear(){
      return false;
   }

   //   public void doDelete(String logic,
   //                        FSqlUnit oUnit){
   //      if(_logger.debugAble()){
   //         _logger.debug(this, "doDelete", "Logic:" + sLogic + " Unit:" + oUnit);
   //      }
   //      activeConnection().executeSQL("DELETE FROM " + getTableName() + " WHERE " + getKeysWhereSql(oUnit));
   //   }
   //private FContainer piColumnsContainer;
   //private FContainer piKeyColumnsContainer;
   //   protected FSqlUnit fetchByKey()
   //          {
   //      String sSqlCmd = "SELECT * FROM " + m_sTableName + " WHERE "
   //            + getKeysWhereSql();
   //      return getActiveConnection().fetchUnit(createUnit(), sSqlCmd);
   //   }
   //
   //   public boolean checkDelete(String sLogic,
   //                              FSqlUnit oUnit){
   //      return false;
   //   }
   //
   //   public boolean checkInsert(String sLogic,
   //                              FSqlUnit oUnit){
   //      return false;
   //   }
   //
   //   public boolean checkUpdate(String sLogic,
   //                              FSqlUnit oUnit){
   //      return false;
   //   }
   //   public boolean doDeleteBy(String sLogic, FSqlUnit oUnit, String sWhere)
   //          {
   //      FLogger.debug(this, "FLTable.doDelete",
   //                    "Logic:" + sLogic + " Unit:" + oUnit + " Where:" + sWhere);
   //      boolean bResult = false;
   //      FDBConnection oConnection = getActiveConnection();
   //      if (oConnection != null) {
   //         bResult = oConnection.executeSQL("DELETE FROM " + getTableName() +
   //                                          " WHERE " + sWhere);
   //      }
   //      return bResult;
   //   }
   //
   //   protected boolean doInsert(String logic,
   //                              FSqlUnit oUnit){
   //      if(_logger.debugAble()){
   //         _logger.debug(this, "doInsert", "Logic:" + logic + " Unit:" + oUnit);
   //      }
   //      boolean bResult = false;
   //      FDBConnection oConnection = getActiveConnection();
   //      if(oConnection != null){
   //         StringBuffer oSqlCmd = new StringBuffer();
   //         FContainer pFContainer = getColumnsContainer();
   //         String arNames[] = oUnit.getKeys();
   //         FSqlTableColumn oTableColumn = null;
   //         if(arNames != null){
   //            int nCount = arNames.length;
   //            oSqlCmd.append("INSERT INTO " + getTableName().toUpperCase() + "(");
   //            int nUseCount = 0;
   //            for(int n = 0; n < nCount; n++){
   //               oTableColumn = (FSqlTableColumn) pFContainer.getItem(arNames[n]);
   //               if(oTableColumn != null && !oTableColumn.getAuto()){
   //                  if(nUseCount != 0){
   //                     oSqlCmd.append(",");
   //                  }
   //                  oSqlCmd.append(arNames[n].toUpperCase());
   //                  nUseCount++;
   //               }
   //            }
   //
   //            oSqlCmd.append(") VALUES (");
   //            nUseCount = 0;
   //            for(int n = 0; n < nCount; n++){
   //               oTableColumn = (FSqlTableColumn) pFContainer.getItem(arNames[n]);
   //               if(oTableColumn != null && !oTableColumn.getAuto()){
   //                  if(nUseCount != 0){
   //                     oSqlCmd.append(",");
   //                  }
   //                  oSqlCmd.append(oConnection.makeSqlFieldValue(oTableColumn.getType(), oUnit.getValue(arNames[n])));
   //                  nUseCount++;
   //               }
   //            }
   //
   //            oSqlCmd.append(")");
   //         }
   //         bResult = oConnection.executeSQL(oSqlCmd.toString());
   //      }
   //      return bResult;
   //   }
   //
   //   public boolean doSync(String sLogic, FSqlUnit oUnit)
   //          {
   //      boolean bResult = false;
   //      FLogger.debug(this, "FLDataConsole.doSync",
   //                    "Logic:" + sLogic + " Unit:" + oUnit);
   //      if (existsByKey(oUnit)) {
   //         bResult = doUpdate(sLogic, oUnit);
   //      } else {
   //         bResult = doInsert(sLogic, oUnit);
   //      }
   //      return bResult;
   //   }
   //
   //   public boolean doSync(String sLogic, FSqlDataset oDataSet)
   //          {
   //      if (oDataSet != null) {
   //         FSqlUnit oUnit = null;
   //         int nCount = oDataSet.getCount();
   //         for (int n = 0; n < nCount; n++) {
   //            oUnit = (FSqlUnit)oDataSet.getItem(n);
   //            if (oUnit != null) {
   //               doSync(sLogic, oUnit);
   //            }
   //         }
   //
   //      }
   //      return false;
   //   }
   //
   //   public boolean doUpdate(String sLogic,
   //                           FSqlUnit oUnit){
   //      FLogger.debug(this, "FLTable.doUpdate", "Logic:" + sLogic + " Unit:" + oUnit);
   //      boolean bResult = false;
   //      FDBConnection oConnection = getActiveConnection();
   //      if(oConnection != null){
   //         StringBuffer oSqlCmd = new StringBuffer();
   //         FContainer pFContainer = getColumnsContainer();
   //         String arNames[] = oUnit.getKeys();
   //         if(arNames != null){
   //            int nCount = arNames.length;
   //            oSqlCmd.append("UPDATE " + getTableName() + " SET ");
   //            FSqlTableColumn oTableColumn = null;
   //            int nUseCount = 0;
   //            for(int n = 0; n < nCount; n++){
   //               oTableColumn = (FSqlTableColumn) pFContainer.getItem(arNames[n]);
   //               if(oTableColumn != null && !oTableColumn.getAuto()){
   //                  if(nUseCount != 0){
   //                     oSqlCmd.append(",");
   //                  }
   //                  oSqlCmd.append(arNames[n]);
   //                  oSqlCmd.append("=");
   //                  oSqlCmd.append(oConnection.makeSqlFieldValue(oTableColumn.getType(), oUnit.getValue(arNames[n])));
   //                  nUseCount++;
   //               }
   //            }
   //
   //            oSqlCmd.append(" WHERE " + getKeysWhereSql(oUnit));
   //         }
   //         bResult = oConnection.executeSQL(oSqlCmd.toString());
   //      }
   //      return bResult;
   //   }
   //
   //   public boolean existsByKey(FSqlUnit oUnit)
   //          {
   //      String sSqlCmd = "SELECT 1 FROM " + getTableName() + " WHERE " +
   //                       getKeysWhereSql(oUnit);
   //      return true;
   //   }
   //
   //   protected boolean existsByKey()
   //          {
   //      String sSqlCmd = "SELECT 1 FROM " + getTableName() + " WHERE " +
   //                       getKeysWhereSql();
   //      return true;
   //   }
   //
   //   public FDBDataSetMeta fetchDataSetMeta(String sTableName)
   //          {
   //      FDBDataSetMeta oDataSetMeta = null;
   //      if (FString.isNotEmpty(sTableName)) {
   //         FDBConnection oConnection = getActiveConnection();
   //         if (oConnection != null) {
   //            oDataSetMeta = oConnection.fetchTableMeta(sTableName);
   //         }
   //      }
   //      return oDataSetMeta;
   //   }
   //
   //   protected String fetchFieldByKeyAsString(FSqlTableColumn oColumn)
   //          {
   //      String sSqlCmd = "SELECT " + oColumn.getName() + " FROM " + getTableName() +
   //                       " WHERE " + getKeysWhereSql();
   //      return null;
   //   }
   //
   //   protected FSqlDataset fetchTableDataset(String sFieldNames, String sWhere,
   //                                           String sOrder, int nPageSize,
   //                                           int nPagePosition)
   //          {
   //      FLogger.debug(this, "FSqlTable.fetchTableDataset",
   //                    "Field Names:" + sFieldNames + " Where:" + sWhere +
   //                    " Order:" + sOrder);
   //      return getActiveConnection().fetchTable(
   //            createDataset().getClass(),
   //            createUnit().getClass(),
   //            getTableName(), sFieldNames,
   //            sWhere, sOrder, nPageSize,
   //            nPagePosition);
   //   }
   //
   //   public FSqlDataset fetchTableDatasetBySQL(String sSqlCmd)
   //          {
   //      FLogger.debug(this, "FLDataConsole.fetchDataSetBySQL",
   //                    "SqlCmd:" + sSqlCmd);
   //      return null;
   //   }
   //
   //   protected FSqlUnit fetchTableUnit(String sFieldNames, String sWhere)
   //          {
   //      FLogger.debug(this, "FLTable.fetchTableUnit",
   //                    "Field Names:" + sFieldNames + " Where:" + sWhere);
   //      return getActiveConnection().fetchTableUnit(
   //            createUnit(), getTableName(), sFieldNames, sWhere);
   //   }
   //
   //   public boolean isEmpty()
   //          {
   //      return!isNotEmpty();
   //   }
   //
   //   public boolean isEmpty(String sWhereSql)
   //          {
   //      return!isNotEmpty(sWhereSql);
   //   }
   //
   //   public boolean isNotEmpty()
   //          {
   //      String sSqlCmd =
   //            "SELECT COUNT(*) TMP_COUNT FROM " + this.getTableName();
   //      FSqlUnit oUnit = getActiveConnection().fetchUnit(new FSqlUnit(), sSqlCmd);
   //      return oUnit.getValueAsInteger("tmp_count", 0) > 0;
   //   }
   //
   //   public boolean isNotEmpty(String sWhereSql)
   //          {
   //      String sSqlCmd =
   //            "SELECT COUNT(*) TMP_COUNT FROM " + this.getTableName();
   //      if (FString.isNotEmpty(sWhereSql)) {
   //         sSqlCmd += " WHERE " + sWhereSql;
   //      }
   //      FSqlUnit oUnit = getActiveConnection().fetchUnit(new FSqlUnit(), sSqlCmd);
   //      return oUnit.getValueAsInteger("tmp_count", 0) > 0;
   //   }
   //
   //   public int getUnitCount()
   //          {
   //      return getUnitCount(null);
   //   }
   //
   //   public int getUnitCount(String sWhereSql)
   //          {
   //      String sSqlCmd =
   //            "SELECT COUNT(*) TMP_COUNT FROM " + this.getTableName();
   //      if (FString.isNotEmpty(sWhereSql)) {
   //         sSqlCmd += " WHERE " + sWhereSql;
   //      }
   //      FSqlUnit oUnit = getActiveConnection().fetchUnit(new FSqlUnit(), sSqlCmd);
   //      return oUnit.getValueAsInteger("tmp_count", 0);
   //   }
   //
   //   protected FDBConnection getActiveConnection()
   //          {
   //      return m_oDatabaseConnection;
   //   }
   //
   //   public FContainer getColumnsContainer() {
   //      if (piColumnsContainer == null) {
   //         piColumnsContainer = new FContainer();
   //         initColumnsContainer(piColumnsContainer);
   //      }
   //      return piColumnsContainer;
   //   }
   //
   //   protected String getDataSetClassName() {
   //      String sClassName = getClass().getName();
   //      if (sClassName.endsWith("LU")) {
   //         return sClassName.substring(0, sClassName.length() - 2) + "DS";
   //      } else {
   //         return null;
   //      }
   //   }
   //
   //   protected String getFieldByKey(FSqlTableColumn oColumn) {
   //      return null;
   //   }
   //
   //   public FContainer getKeyColumnsContainer() {
   //      if (piKeyColumnsContainer == null) {
   //         piKeyColumnsContainer = new FContainer();
   //         initKeyColumnsContainer(piKeyColumnsContainer);
   //      }
   //      return piKeyColumnsContainer;
   //   }
   //
   //   protected String getKeysWhereSql(FSqlUnit oUnit) {
   //      StringBuffer oSqlCmd = new StringBuffer();
   //      FContainer piKeyColumns = getKeyColumnsContainer();
   //      int nCount = piKeyColumns.getCount();
   //      FSqlTableColumn oColumn = null;
   //      for (int n = 0; n < nCount; n++) {
   //         oColumn = (FSqlTableColumn)piKeyColumns.getItem(n);
   //         if (n != 0) {
   //            oSqlCmd.append(" AND ");
   //         }
   //         oSqlCmd.append("(" + oColumn.getName() + "=" + "'" +
   //                        oUnit.getValue(oColumn.getName()) + "')");
   //      }
   //
   //      return oSqlCmd.toString();
   //   }
   //
   //   protected String getKeysWhereSql() {
   //      StringBuffer oSqlCmd = new StringBuffer();
   //      int nCount = m_oKeyItems.getCount();
   //      for (int n = 0; n < nCount; n++) {
   //         if (n != 0) {
   //            oSqlCmd.append(" AND ");
   //         }
   //         oSqlCmd.append("(" + m_oKeyItems.getKey(n) + "=" + "'" +
   //                        m_oKeyItems.getValue(n) + "')");
   //      }
   //
   //      return oSqlCmd.toString();
   //   }
   //
   //   public FSqlTableColumn[] getPtyKeyColumns() {
   //      return null;
   //   }
   //
   //   public String getTableName() {
   //      return null;
   //   }
   //
   //   protected String getUnitClassName() {
   //      String sClassName = getClass().getName();
   //      if (sClassName.endsWith("LU")) {
   //         return sClassName.substring(0, sClassName.length() - 2) + "Unit";
   //      } else {
   //         return null;
   //      }
   //   }
   //   public boolean clearKeyFields() {
   //      m_oKeyItems.clear();
   //      return false;
   //   }
   //
   //   protected FSqlDataset createDataset()
   //          {
   //      return new FSqlDataset();
   //   }
   //
   //   protected FSqlUnit createUnit()
   //          {
   //      return new FSqlUnit();
   //   }
   public FRow fetchRow(){
      FString command = new FString();
      command.append("SELECT * FROM ");
      command.append(_table);
      //+ m_sTableName + " WHERE ") + getKeysWhereSql();
      //return getActiveConnection().fetchUnit(createUnit(), sSqlCmd);
      return _connection.find(command.toString());
   } //private FStringList m_oKeyItems;
     //   protected boolean initColumnsContainer(FContainer pFContainer) {
     //      return true;
     //   }
     //
     //   protected boolean initKeyColumnsContainer(FContainer pFContainer) {
     //      return true;
     //   }
     //
     //   public boolean lock(int nMode) {
     //      return false;
     //   }
     //

   public boolean lock(){
      return false;
   }

   //
   //   protected FSqlUnit lockByKey() {
   //      return null;
   //   }
   //
   //   public String makeSqlString(FSqlTableColumn oTableColumn, String sValue)
   //          {
   //      return oTableColumn.getName() + "=" +
   //            getActiveConnection().
   //            makeSqlFieldValue(oTableColumn.getType(), sValue);
   //   }
   //
   //   public boolean prepareInsert(String sLogic,
   //                                FSqlUnit oUnit) {
   //      return false;
   //   }
   //
   //   public boolean selectConnection(String sConnectionName) {
   //      m_sConnectionName = sConnectionName;
   //      return true;
   //   }
   //
   //   protected boolean setKeyFieldValue(FSqlTableColumn oColumn, String sValue) {
   //      String sColumnName = oColumn.getName();
   //      if (!m_oKeyItems.hasKey(sColumnName)) {
   //         m_oKeyItems.add(sColumnName, sValue);
   //      } else {
   //         m_oKeyItems.setValue(sColumnName, sValue);
   //      }
   //      return false;
   //   }
   public FMessages messages(){
      return _messages;
   }
}
