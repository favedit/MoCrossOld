/*
 * @(#)FCpTransferImpl.java
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
import org.mo.logic.data.ICpTransfer;

/**
 * <T>数据库逻辑包(CP_TRANSFER)的代理对象</T>
 *
 * @author MAOCY
 * @version 1.0.1
 */
public class FCpTransferImpl
      extends MSqlConnect
      implements
         ICpTransfer
{
   public final String LOGIC_NAME = "CP_TRANSFER";

   public FCpTransferImpl(){
   }

   public FCpTransferImpl(ISqlConnect connect){
      super(connect);
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpTransfer(java.lang.Object)
    */
   @Override
   public FSqlFunction getGlobalProperty(Object property){
      FSqlFunction function = new FSqlFunction("Get_Global_property");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("property_", property, ESqlDataType.String, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.String);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpTransfer(java.lang.Object)
    */
   @Override
   public FSqlFunction getSessionProperty(Object property){
      FSqlFunction function = new FSqlFunction("Get_Session_property");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("property_", property, ESqlDataType.String, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.String);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpTransfer(java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure reset(Object globalPack,
                              Object sessionPack){
      FSqlProcedure procedure = new FSqlProcedure("Reset");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("global_pack_", globalPack, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("session_pack_", sessionPack, ESqlDataType.String, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpTransfer(java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure fetchResult(Object message,
                                    Object logger){
      FSqlProcedure procedure = new FSqlProcedure("Fetch_Result");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("message_", message, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("logger_", logger, ESqlDataType.String, ESqlDataDirection.InOut);
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
