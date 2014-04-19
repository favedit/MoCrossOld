/*
 * @(#)FCpSessionImpl.java
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
import org.mo.logic.data.ICpSession;

/**
 * <T>数据库逻辑包(CP_SESSION)的代理对象</T>
 *
 * @author MAOCY
 * @version 1.0.1
 */
public class FCpSessionImpl
      extends MSqlConnect
      implements
         ICpSession
{
   public final String LOGIC_NAME = "CP_SESSION";

   public FCpSessionImpl(){
   }

   public FCpSessionImpl(ISqlConnect connect){
      super(connect);
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpSession(java.lang.Object)
    */
   @Override
   public FSqlFunction build(Object action){
      FSqlFunction function = new FSqlFunction("Build");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("action_", action, ESqlDataType.String, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.String);
      activeConnection().execute(function);
      return function;
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
    * @see org.mo.logic.data.ICpSession(java.lang.Object)
    */
   @Override
   public FSqlFunction getParameter(Object name){
      FSqlFunction function = new FSqlFunction("Get_Parameter");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("name_", name, ESqlDataType.String, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.String);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpSession(java.lang.Object)
    */
   @Override
   public FSqlFunction isActiveUser(Object userId){
      FSqlFunction function = new FSqlFunction("Is_Active_User");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("user_id_", userId, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Boolean);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpSession()
    */
   @Override
   public FSqlFunction languageId(){
      FSqlFunction function = new FSqlFunction("Language_Id");
      function.setLogicName(LOGIC_NAME);
      function.setReturnType(ESqlDataType.Integer);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpSession(java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure link(Object logic,
                             Object parameters){
      FSqlProcedure procedure = new FSqlProcedure("Link");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("parameters_", parameters, ESqlDataType.String, ESqlDataDirection.InOut);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpSession(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure login(Object logic,
                              Object parameters,
                              Object configuration,
                              Object properties,
                              Object catalogPack,
                              Object componentPack,
                              Object notifyPack,
                              Object friendPack,
                              Object visitPack){
      FSqlProcedure procedure = new FSqlProcedure("Login");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("parameters_", parameters, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("configuration_", configuration, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("properties_", properties, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("catalog_pack_", catalogPack, ESqlDataType.String, ESqlDataDirection.Out);
      procedure.createParameter("component_pack_", componentPack, ESqlDataType.String, ESqlDataDirection.Out);
      procedure.createParameter("notify_pack_", notifyPack, ESqlDataType.String, ESqlDataDirection.Out);
      procedure.createParameter("friend_pack_", friendPack, ESqlDataType.String, ESqlDataDirection.Out);
      procedure.createParameter("visit_pack_", visitPack, ESqlDataType.String, ESqlDataDirection.Out);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpSession(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure loginDirect(Object logic,
                                    Object parameters,
                                    Object configuration,
                                    Object properties,
                                    Object catalogPack,
                                    Object componentPack,
                                    Object notifyPack,
                                    Object friendPack,
                                    Object visitPack){
      FSqlProcedure procedure = new FSqlProcedure("Login_Direct");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("parameters_", parameters, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("configuration_", configuration, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("properties_", properties, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("catalog_pack_", catalogPack, ESqlDataType.String, ESqlDataDirection.Out);
      procedure.createParameter("component_pack_", componentPack, ESqlDataType.String, ESqlDataDirection.Out);
      procedure.createParameter("notify_pack_", notifyPack, ESqlDataType.String, ESqlDataDirection.Out);
      procedure.createParameter("friend_pack_", friendPack, ESqlDataType.String, ESqlDataDirection.Out);
      procedure.createParameter("visit_pack_", visitPack, ESqlDataType.String, ESqlDataDirection.Out);
      activeConnection().execute(procedure);
      return procedure;
   }

   @Override
   public FSqlProcedure LoginUserHistory(Object logic,
                                         Object parameters){
      FSqlProcedure procedure = new FSqlProcedure("Login_User_History");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("params_", parameters, ESqlDataType.String, ESqlDataDirection.InOut);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpSession(java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure logout(Object logic,
                               Object parameters){
      FSqlProcedure procedure = new FSqlProcedure("Logout");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("parameters_", parameters, ESqlDataType.String, ESqlDataDirection.InOut);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpSession()
    */
   @Override
   public FSqlFunction sessionId(){
      FSqlFunction function = new FSqlFunction("Session_Id");
      function.setLogicName(LOGIC_NAME);
      function.setReturnType(ESqlDataType.Integer);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpSession(java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure unlink(Object logic,
                               Object parameters,
                               Object logger){
      FSqlProcedure procedure = new FSqlProcedure("Unlink");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("parameters_", parameters, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("logger_", logger, ESqlDataType.String, ESqlDataDirection.InOut);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpSession()
    */
   @Override
   public FSqlFunction userId(){
      FSqlFunction function = new FSqlFunction("User_Id");
      function.setLogicName(LOGIC_NAME);
      function.setReturnType(ESqlDataType.Integer);
      activeConnection().execute(function);
      return function;
   }
}
