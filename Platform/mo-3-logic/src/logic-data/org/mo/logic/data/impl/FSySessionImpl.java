/*
 * @(#)FSySessionImpl.java
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
import org.mo.data.logicunit.common.FAbstractLogicUnit;
import org.mo.logic.data.ISySessionDi;

/**
 * <T>数据库逻辑包(SY_SESSION_DI)的代理对象</T>
 *
 * @author MAOCY
 * @version 1.0.1
 */
public class FSySessionImpl
      extends FAbstractLogicUnit
      implements
         ISySessionDi
{
   public final String LOGIC_NAME = "SY_SESSION_DI";

   public FSySessionImpl(){
   }

   public FSySessionImpl(ISqlConnect connect){
      super(connect);
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ISySessionDi(java.lang.Object)
    */
   @Override
   public FSqlFunction contains(Object objectId){
      FSqlFunction function = new FSqlFunction("Contains");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("object_id_", objectId, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Boolean);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ISySessionDi(java.lang.Object)
    */
   @Override
   public FSqlFunction pack(Object objectId){
      FSqlFunction function = new FSqlFunction("Pack");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("object_id_", objectId, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.String);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ISySessionDi()
    */
   @Override
   public FSqlFunction currentId(){
      FSqlFunction function = new FSqlFunction("Current_Id");
      function.setLogicName(LOGIC_NAME);
      function.setReturnType(ESqlDataType.Integer);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ISySessionDi()
    */
   @Override
   public FSqlFunction nextId(){
      FSqlFunction function = new FSqlFunction("Next_Id");
      function.setLogicName(LOGIC_NAME);
      function.setReturnType(ESqlDataType.Integer);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ISySessionDi(java.lang.Object)
    */
   @Override
   public FSqlFunction getOinf(Object objectId){
      FSqlFunction function = new FSqlFunction("Get_Oinf");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("object_id_", objectId, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.String);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ISySessionDi(java.lang.Object)
    */
   @Override
   public FSqlFunction containsIdByOgid(Object ogid){
      FSqlFunction function = new FSqlFunction("Contains_Id_By_Ogid");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("ogid_", ogid, ESqlDataType.String, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Boolean);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ISySessionDi(java.lang.Object)
    */
   @Override
   public FSqlFunction containsIdByUserId(Object userId){
      FSqlFunction function = new FSqlFunction("Contains_Id_By_User_Id");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("user_id_", userId, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Boolean);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ISySessionDi(java.lang.Object)
    */
   @Override
   public FSqlFunction getIdByOgid(Object ogid){
      FSqlFunction function = new FSqlFunction("Get_Id_By_Ogid");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("ogid_", ogid, ESqlDataType.String, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Integer);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ISySessionDi(java.lang.Object)
    */
   @Override
   public FSqlFunction getIdByUserId(Object userId){
      FSqlFunction function = new FSqlFunction("Get_Id_By_User_Id");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("user_id_", userId, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Integer);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ISySessionDi(java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlFunction findIdByOgid(Object ogid,
                                    Object exists){
      FSqlFunction function = new FSqlFunction("Find_Id_By_Ogid");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("ogid_", ogid, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("exists_", exists, ESqlDataType.Boolean, ESqlDataDirection.InOut);
      function.setReturnType(ESqlDataType.Integer);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ISySessionDi(java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlFunction findIdByUserId(Object userId,
                                      Object exists){
      FSqlFunction function = new FSqlFunction("Find_Id_By_User_Id");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("user_id_", userId, ESqlDataType.Integer, ESqlDataDirection.In);
      function.createParameter("exists_", exists, ESqlDataType.Boolean, ESqlDataDirection.InOut);
      function.setReturnType(ESqlDataType.Integer);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ISySessionDi(java.lang.Object)
    */
   @Override
   public FSqlFunction getOgid(Object objectId){
      FSqlFunction function = new FSqlFunction("Get_Ogid");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("object_id_", objectId, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.String);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ISySessionDi(java.lang.Object)
    */
   @Override
   public FSqlFunction getOver(Object objectId){
      FSqlFunction function = new FSqlFunction("Get_Over");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("object_id_", objectId, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.String);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ISySessionDi(java.lang.Object)
    */
   @Override
   public FSqlFunction getOvld(Object objectId){
      FSqlFunction function = new FSqlFunction("Get_Ovld");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("object_id_", objectId, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.String);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ISySessionDi(java.lang.Object)
    */
   @Override
   public FSqlFunction getCode(Object objectId){
      FSqlFunction function = new FSqlFunction("Get_Code");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("object_id_", objectId, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.String);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ISySessionDi(java.lang.Object)
    */
   @Override
   public FSqlFunction getStatusCd(Object objectId){
      FSqlFunction function = new FSqlFunction("Get_Status_Cd");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("object_id_", objectId, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.String);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ISySessionDi(java.lang.Object)
    */
   @Override
   public FSqlFunction getStatusLabel(Object objectId){
      FSqlFunction function = new FSqlFunction("Get_Status_Label");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("object_id_", objectId, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.String);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ISySessionDi(java.lang.Object)
    */
   @Override
   public FSqlFunction getUserId(Object objectId){
      FSqlFunction function = new FSqlFunction("Get_User_Id");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("object_id_", objectId, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Integer);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ISySessionDi(java.lang.Object)
    */
   @Override
   public FSqlFunction getLanguageId(Object objectId){
      FSqlFunction function = new FSqlFunction("Get_Language_Id");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("object_id_", objectId, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Integer);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ISySessionDi(java.lang.Object)
    */
   @Override
   public FSqlFunction getCatalogViewId(Object objectId){
      FSqlFunction function = new FSqlFunction("Get_Catalog_View_Id");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("object_id_", objectId, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Integer);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ISySessionDi(java.lang.Object)
    */
   @Override
   public FSqlFunction getRoleIds(Object objectId){
      FSqlFunction function = new FSqlFunction("Get_Role_Ids");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("object_id_", objectId, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.String);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ISySessionDi(java.lang.Object)
    */
   @Override
   public FSqlFunction getRoleCodes(Object objectId){
      FSqlFunction function = new FSqlFunction("Get_Role_Codes");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("object_id_", objectId, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.String);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ISySessionDi(java.lang.Object)
    */
   @Override
   public FSqlFunction getOrganizationIds(Object objectId){
      FSqlFunction function = new FSqlFunction("Get_Organization_Ids");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("object_id_", objectId, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.String);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ISySessionDi(java.lang.Object)
    */
   @Override
   public FSqlFunction getOrganizationCodes(Object objectId){
      FSqlFunction function = new FSqlFunction("Get_Organization_Codes");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("object_id_", objectId, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.String);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ISySessionDi(java.lang.Object)
    */
   @Override
   public FSqlFunction getCreateDate(Object objectId){
      FSqlFunction function = new FSqlFunction("Get_Create_Date");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("object_id_", objectId, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.DateTime);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ISySessionDi(java.lang.Object)
    */
   @Override
   public FSqlFunction getActiveDate(Object objectId){
      FSqlFunction function = new FSqlFunction("Get_Active_Date");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("object_id_", objectId, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.DateTime);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ISySessionDi(java.lang.Object)
    */
   @Override
   public FSqlFunction getParameters(Object objectId){
      FSqlFunction function = new FSqlFunction("Get_Parameters");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("object_id_", objectId, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.String);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ISySessionDi(java.lang.Object)
    */
   @Override
   public FSqlProcedure lockRecord(Object objectId){
      FSqlProcedure procedure = new FSqlProcedure("Lock_Record");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("object_id_", objectId, ESqlDataType.Integer, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ISySessionDi(java.lang.Object)
    */
   @Override
   public FSqlProcedure lockTable(Object mode){
      FSqlProcedure procedure = new FSqlProcedure("Lock_Table");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("mode_", mode, ESqlDataType.String, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ISySessionDi(java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure prepare(Object logic,
                                Object params){
      FSqlProcedure procedure = new FSqlProcedure("Prepare");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("params_", params, ESqlDataType.String, ESqlDataDirection.InOut);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ISySessionDi(java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure doInsert(Object logic,
                                 Object params,
                                 Object check){
      FSqlProcedure procedure = new FSqlProcedure("Do_Insert");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("params_", params, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("check_", check, ESqlDataType.String, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ISySessionDi(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure doUpdate(Object logic,
                                 Object params,
                                 Object objectId,
                                 Object check){
      FSqlProcedure procedure = new FSqlProcedure("Do_Update");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("params_", params, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("object_id_", objectId, ESqlDataType.Integer, ESqlDataDirection.In);
      procedure.createParameter("check_", check, ESqlDataType.String, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ISySessionDi(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure doSync(Object logic,
                               Object params,
                               Object objectId,
                               Object check){
      FSqlProcedure procedure = new FSqlProcedure("Do_Sync");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("params_", params, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("object_id_", objectId, ESqlDataType.Integer, ESqlDataDirection.In);
      procedure.createParameter("check_", check, ESqlDataType.String, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ISySessionDi(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure doDelete(Object logic,
                                 Object params,
                                 Object objectId,
                                 Object check){
      FSqlProcedure procedure = new FSqlProcedure("Do_Delete");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("params_", params, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("object_id_", objectId, ESqlDataType.Integer, ESqlDataDirection.In);
      procedure.createParameter("check_", check, ESqlDataType.String, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ISySessionDi()
    */
   @Override
   public FSqlProcedure doClear(){
      FSqlProcedure procedure = new FSqlProcedure("Do_Clear");
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
