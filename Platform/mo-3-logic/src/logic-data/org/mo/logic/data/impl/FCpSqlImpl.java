/*
 * @(#)FCpSqlImpl.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.data.impl;

import org.mo.com.data.ESqlDataDirection;
import org.mo.com.data.ESqlDataType;
import org.mo.com.data.FSqlFunction;
import org.mo.com.data.FSqlProcedure;
import org.mo.com.data.ISqlConnect;
import org.mo.com.data.MSqlConnect;
import org.mo.logic.data.ICpSql;

/**
 * <T>数据库逻辑包(CP_SQL)的代理对象</T>
 *
 * @author MAOCY
 * @version 1.0.1
 */
public class FCpSqlImpl
      extends MSqlConnect
      implements
         ICpSql
{
   public final String LOGIC_NAME = "CP_SQL";

   public FCpSqlImpl(){
   }

   public FCpSqlImpl(ISqlConnect connect){
      super(connect);
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpSql(java.lang.Object)
    */
   @Override
   public FSqlFunction executeFunction(Object functionName){
      FSqlFunction function = new FSqlFunction("Execute_Function");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("function_name_", functionName, ESqlDataType.String, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.String);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpSql(java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlFunction executeFunction(Object functionName,
                                       Object param1){
      FSqlFunction function = new FSqlFunction("Execute_Function");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("function_name_", functionName, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("param1_", param1, ESqlDataType.String, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.String);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpSql(java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlFunction executeFunction(Object functionName,
                                       Object param1,
                                       Object param2){
      FSqlFunction function = new FSqlFunction("Execute_Function");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("function_name_", functionName, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("param1_", param1, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("param2_", param2, ESqlDataType.String, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.String);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpSql(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlFunction executeFunction(Object functionName,
                                       Object param1,
                                       Object param2,
                                       Object param3){
      FSqlFunction function = new FSqlFunction("Execute_Function");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("function_name_", functionName, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("param1_", param1, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("param2_", param2, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("param3_", param3, ESqlDataType.String, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.String);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpSql(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlFunction executeFunction(Object functionName,
                                       Object param1,
                                       Object param2,
                                       Object param3,
                                       Object param4){
      FSqlFunction function = new FSqlFunction("Execute_Function");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("function_name_", functionName, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("param1_", param1, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("param2_", param2, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("param3_", param3, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("param4_", param4, ESqlDataType.String, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.String);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpSql(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlFunction executeFunction(Object functionName,
                                       Object param1,
                                       Object param2,
                                       Object param3,
                                       Object param4,
                                       Object param5){
      FSqlFunction function = new FSqlFunction("Execute_Function");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("function_name_", functionName, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("param1_", param1, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("param2_", param2, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("param3_", param3, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("param4_", param4, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("param5_", param5, ESqlDataType.String, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.String);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpSql(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlFunction executeFunction(Object functionName,
                                       Object param1,
                                       Object param2,
                                       Object param3,
                                       Object param4,
                                       Object param5,
                                       Object param6){
      FSqlFunction function = new FSqlFunction("Execute_Function");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("function_name_", functionName, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("param1_", param1, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("param2_", param2, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("param3_", param3, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("param4_", param4, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("param5_", param5, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("param6_", param6, ESqlDataType.String, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.String);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpSql(java.lang.Object)
    */
   @Override
   public FSqlProcedure executeSql(Object sql){
      FSqlProcedure procedure = new FSqlProcedure("Execute_Sql");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("sql_", sql, ESqlDataType.String, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpSql(java.lang.Object)
    */
   @Override
   public FSqlProcedure executeProcedure(Object procedureName){
      FSqlProcedure procedure = new FSqlProcedure("Execute_Procedure");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("procedure_name_", procedureName, ESqlDataType.String, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpSql(java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure executeProcedure(Object procedureName,
                                         Object param1){
      FSqlProcedure procedure = new FSqlProcedure("Execute_Procedure");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("procedure_name_", procedureName, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("param1_", param1, ESqlDataType.String, ESqlDataDirection.InOut);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpSql(java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure executeProcedure(Object procedureName,
                                         Object param1,
                                         Object param2){
      FSqlProcedure procedure = new FSqlProcedure("Execute_Procedure");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("procedure_name_", procedureName, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("param1_", param1, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("param2_", param2, ESqlDataType.String, ESqlDataDirection.InOut);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpSql(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure executeProcedure(Object procedureName,
                                         Object param1,
                                         Object param2,
                                         Object param3){
      FSqlProcedure procedure = new FSqlProcedure("Execute_Procedure");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("procedure_name_", procedureName, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("param1_", param1, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("param2_", param2, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("param3_", param3, ESqlDataType.String, ESqlDataDirection.InOut);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpSql(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure executeProcedure(Object procedureName,
                                         Object param1,
                                         Object param2,
                                         Object param3,
                                         Object param4){
      FSqlProcedure procedure = new FSqlProcedure("Execute_Procedure");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("procedure_name_", procedureName, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("param1_", param1, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("param2_", param2, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("param3_", param3, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("param4_", param4, ESqlDataType.String, ESqlDataDirection.InOut);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpSql(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure executeProcedure(Object procedureName,
                                         Object param1,
                                         Object param2,
                                         Object param3,
                                         Object param4,
                                         Object param5){
      FSqlProcedure procedure = new FSqlProcedure("Execute_Procedure");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("procedure_name_", procedureName, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("param1_", param1, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("param2_", param2, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("param3_", param3, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("param4_", param4, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("param5_", param5, ESqlDataType.String, ESqlDataDirection.InOut);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpSql(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure executeProcedure(Object procedureName,
                                         Object param1,
                                         Object param2,
                                         Object param3,
                                         Object param4,
                                         Object param5,
                                         Object param6){
      FSqlProcedure procedure = new FSqlProcedure("Execute_Procedure");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("procedure_name_", procedureName, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("param1_", param1, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("param2_", param2, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("param3_", param3, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("param4_", param4, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("param5_", param5, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("param6_", param6, ESqlDataType.String, ESqlDataDirection.InOut);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpSql()
    */
   @Override
   public FSqlProcedure compileAllInvalidObject(){
      FSqlProcedure procedure = new FSqlProcedure("Compile_All_Invalid_Object");
      procedure.setLogicName(LOGIC_NAME);
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
