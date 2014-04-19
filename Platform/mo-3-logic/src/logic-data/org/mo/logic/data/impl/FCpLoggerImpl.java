/*
 * @(#)FCpLoggerImpl.java
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
import org.mo.logic.data.ICpLogger;

/**
 * <T>数据库逻辑包(CP_LOGGER)的代理对象</T>
 *
 * @author MAOCY
 * @version 1.0.1
 */
public class FCpLoggerImpl
      extends MSqlConnect
      implements
         ICpLogger
{
   public final String LOGIC_NAME = "CP_LOGGER";

   public FCpLoggerImpl(){
   }

   public FCpLoggerImpl(ISqlConnect connect){
      super(connect);
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpLogger()
    */
   @Override
   public FSqlFunction debugable(){
      FSqlFunction function = new FSqlFunction("DebugAble");
      function.setLogicName(LOGIC_NAME);
      function.setReturnType(ESqlDataType.Boolean);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpLogger()
    */
   @Override
   public FSqlProcedure reset(){
      FSqlProcedure procedure = new FSqlProcedure("Reset");
      procedure.setLogicName(LOGIC_NAME);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpLogger(java.lang.Object)
    */
   @Override
   public FSqlProcedure setFlag(Object flag){
      FSqlProcedure procedure = new FSqlProcedure("Set_Flag");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("flag_", flag, ESqlDataType.Integer, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpLogger(java.lang.Object)
    */
   @Override
   public FSqlProcedure methodBegin(Object method){
      FSqlProcedure procedure = new FSqlProcedure("Method_Begin");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("method_", method, ESqlDataType.String, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpLogger()
    */
   @Override
   public FSqlProcedure methodEnd(){
      FSqlProcedure procedure = new FSqlProcedure("Method_End");
      procedure.setLogicName(LOGIC_NAME);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpLogger(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure print(Object message,
                              Object param1,
                              Object param2,
                              Object param3,
                              Object param4,
                              Object param5){
      FSqlProcedure procedure = new FSqlProcedure("Print");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("message_", message, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("param1_", param1, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("param2_", param2, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("param3_", param3, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("param4_", param4, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("param5_", param5, ESqlDataType.String, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpLogger(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure debug(Object logic,
                              Object method,
                              Object message,
                              Object param1,
                              Object param2,
                              Object param3,
                              Object param4,
                              Object param5){
      FSqlProcedure procedure = new FSqlProcedure("Debug");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("method_", method, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("message_", message, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("param1_", param1, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("param2_", param2, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("param3_", param3, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("param4_", param4, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("param5_", param5, ESqlDataType.String, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpLogger(java.lang.Object)
    */
   @Override
   public FSqlProcedure fetchBuffer(Object buffer){
      FSqlProcedure procedure = new FSqlProcedure("Fetch_Buffer");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("buffer_", buffer, ESqlDataType.String, ESqlDataDirection.InOut);
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
