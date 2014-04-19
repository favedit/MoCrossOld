/*
 * @(#)FCpSyncDataImpl.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.data.impl;

import org.mo.com.data.ESqlDataDirection;
import org.mo.com.data.ESqlDataType;
import org.mo.com.data.FSqlProcedure;
import org.mo.com.data.ISqlConnect;
import org.mo.com.data.MSqlConnect;
import org.mo.logic.data.ICpSyncData;

/**
 * <T>数据库逻辑包(CP_SYNC_DATA)的代理对象</T>
 *
 * @author MAOCY
 * @version 1.0.1
 */
public class FCpSyncDataImpl
      extends MSqlConnect
      implements
         ICpSyncData
{
   public final String LOGIC_NAME = "CP_SYNC_DATA";

   public FCpSyncDataImpl(){
   }

   public FCpSyncDataImpl(ISqlConnect connect){
      super(connect);
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpSyncData(java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure clearTable(Object tableName,
                                   Object databaseName){
      FSqlProcedure procedure = new FSqlProcedure("Clear_Table");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("table_name_", tableName, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("database_name_", databaseName, ESqlDataType.String, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpSyncData(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure syncTable(Object tableName,
                                  Object databaseName,
                                  Object orderBy,
                                  Object versionBegin,
                                  Object versionEnd){
      FSqlProcedure procedure = new FSqlProcedure("Sync_Table");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("table_name_", tableName, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("database_name_", databaseName, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("order_by_", orderBy, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("version_begin_", versionBegin, ESqlDataType.Date, ESqlDataDirection.In);
      procedure.createParameter("version_end_", versionEnd, ESqlDataType.Date, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICmCityDi(java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure doInsert(Object logic,
                                 Object params){
      FSqlProcedure procedure = new FSqlProcedure("Do_Insert");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("params_", params, ESqlDataType.String, ESqlDataDirection.InOut);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICmCityDi(java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure doUpdate(Object logic,
                                 Object params){
      FSqlProcedure procedure = new FSqlProcedure("Do_Update");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("params_", params, ESqlDataType.String, ESqlDataDirection.InOut);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICmCityDi(java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure doSync(Object logic,
                               Object params){
      FSqlProcedure procedure = new FSqlProcedure("Do_Sync");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("params_", params, ESqlDataType.String, ESqlDataDirection.InOut);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICmCityDi(java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure doDelete(Object logic,
                                 Object params){
      FSqlProcedure procedure = new FSqlProcedure("Do_Delete");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("params_", params, ESqlDataType.String, ESqlDataDirection.InOut);
      activeConnection().execute(procedure);
      return procedure;
   }
}
