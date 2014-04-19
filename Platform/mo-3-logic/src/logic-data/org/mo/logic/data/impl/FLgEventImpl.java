/*
 * @(#)FLgEventImpl.java
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
import org.mo.logic.data.ILgEventDi;

/**
 * <T>数据库逻辑包(LG_EVENT_DI)的代理对象</T>
 *
 * @author MAOCY
 * @version 1.0.1
 */
public class FLgEventImpl
      extends FAbstractLogicUnit
      implements
         ILgEventDi
{
   public final String LOGIC_NAME = "LG_EVENT_DI";

   public FLgEventImpl(){
   }

   public FLgEventImpl(ISqlConnect connect){
      super(connect);
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ILgEventDi(java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlFunction getIdByParams(Object typeId,
                                     Object name){
      FSqlFunction function = new FSqlFunction("Get_Id_By_Params");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("type_id_", typeId, ESqlDataType.Integer, ESqlDataDirection.In);
      function.createParameter("name_", name, ESqlDataType.String, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Integer);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ILgEventDi(java.lang.Object)
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
    * @see org.mo.logic.data.ILgEventDi(java.lang.Object)
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
    * @see org.mo.logic.data.ILgEventDi()
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
    * @see org.mo.logic.data.ILgEventDi()
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
    * @see org.mo.logic.data.ILgEventDi(java.lang.Object)
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
    * @see org.mo.logic.data.ILgEventDi(java.lang.Object)
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
    * @see org.mo.logic.data.ILgEventDi(java.lang.Object)
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
    * @see org.mo.logic.data.ILgEventDi(java.lang.Object, java.lang.Object)
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
    * @see org.mo.logic.data.ILgEventDi(java.lang.Object)
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
    * @see org.mo.logic.data.ILgEventDi(java.lang.Object)
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
    * @see org.mo.logic.data.ILgEventDi(java.lang.Object)
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
    * @see org.mo.logic.data.ILgEventDi(java.lang.Object)
    */
   @Override
   public FSqlFunction getGroupId(Object objectId){
      FSqlFunction function = new FSqlFunction("Get_Group_Id");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("object_id_", objectId, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Integer);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ILgEventDi(java.lang.Object)
    */
   @Override
   public FSqlFunction getTypeId(Object objectId){
      FSqlFunction function = new FSqlFunction("Get_Type_Id");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("object_id_", objectId, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Integer);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ILgEventDi(java.lang.Object)
    */
   @Override
   public FSqlFunction getNetworkId(Object objectId){
      FSqlFunction function = new FSqlFunction("Get_Network_Id");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("object_id_", objectId, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Integer);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ILgEventDi(java.lang.Object)
    */
   @Override
   public FSqlFunction getServerId(Object objectId){
      FSqlFunction function = new FSqlFunction("Get_Server_Id");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("object_id_", objectId, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Integer);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ILgEventDi(java.lang.Object)
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
    * @see org.mo.logic.data.ILgEventDi(java.lang.Object)
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
    * @see org.mo.logic.data.ILgEventDi(java.lang.Object)
    */
   @Override
   public FSqlFunction getPriorityCd(Object objectId){
      FSqlFunction function = new FSqlFunction("Get_Priority_Cd");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("object_id_", objectId, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.String);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ILgEventDi(java.lang.Object)
    */
   @Override
   public FSqlFunction getPriorityLabel(Object objectId){
      FSqlFunction function = new FSqlFunction("Get_Priority_Label");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("object_id_", objectId, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.String);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ILgEventDi(java.lang.Object)
    */
   @Override
   public FSqlFunction getBeginDate(Object objectId){
      FSqlFunction function = new FSqlFunction("Get_Begin_Date");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("object_id_", objectId, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.DateTime);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ILgEventDi(java.lang.Object)
    */
   @Override
   public FSqlFunction getEndDate(Object objectId){
      FSqlFunction function = new FSqlFunction("Get_End_Date");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("object_id_", objectId, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.DateTime);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ILgEventDi(java.lang.Object)
    */
   @Override
   public FSqlFunction getProcessUserId(Object objectId){
      FSqlFunction function = new FSqlFunction("Get_Process_User_Id");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("object_id_", objectId, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Integer);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ILgEventDi(java.lang.Object)
    */
   @Override
   public FSqlFunction getProcessPercent(Object objectId){
      FSqlFunction function = new FSqlFunction("Get_Process_Percent");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("object_id_", objectId, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Integer);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ILgEventDi(java.lang.Object)
    */
   @Override
   public FSqlFunction getResultCd(Object objectId){
      FSqlFunction function = new FSqlFunction("Get_Result_Cd");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("object_id_", objectId, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.String);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ILgEventDi(java.lang.Object)
    */
   @Override
   public FSqlFunction getResultLabel(Object objectId){
      FSqlFunction function = new FSqlFunction("Get_Result_Label");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("object_id_", objectId, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.String);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ILgEventDi(java.lang.Object)
    */
   @Override
   public FSqlFunction getCreateUserId(Object objectId){
      FSqlFunction function = new FSqlFunction("Get_Create_User_Id");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("object_id_", objectId, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Integer);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ILgEventDi(java.lang.Object)
    */
   @Override
   public FSqlFunction getReceiveUserId(Object objectId){
      FSqlFunction function = new FSqlFunction("Get_Receive_User_Id");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("object_id_", objectId, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Integer);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ILgEventDi(java.lang.Object)
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
    * @see org.mo.logic.data.ILgEventDi(java.lang.Object)
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
    * @see org.mo.logic.data.ILgEventDi(java.lang.Object)
    */
   @Override
   public FSqlFunction getNote(Object objectId){
      FSqlFunction function = new FSqlFunction("Get_Note");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("object_id_", objectId, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.String);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ILgEventDi(java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure executeEvent(Object logic,
                                     Object typeId,
                                     Object parameters){
      FSqlProcedure procedure = new FSqlProcedure("Execute_Event");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("type_id_", typeId, ESqlDataType.Integer, ESqlDataDirection.In);
      procedure.createParameter("parameters_", parameters, ESqlDataType.String, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ILgEventDi(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure doPublishById(Object logic,
                                      Object eventId,
                                      Object groupId,
                                      Object typeId,
                                      Object priorityCd,
                                      Object createUserId,
                                      Object receiveUserId,
                                      Object parameters,
                                      Object note){
      FSqlProcedure procedure = new FSqlProcedure("Do_Publish_By_Id");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("event_id_", eventId, ESqlDataType.Integer, ESqlDataDirection.Out);
      procedure.createParameter("group_id_", groupId, ESqlDataType.Integer, ESqlDataDirection.In);
      procedure.createParameter("type_id_", typeId, ESqlDataType.Integer, ESqlDataDirection.In);
      procedure.createParameter("priority_cd_", priorityCd, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("create_user_id_", createUserId, ESqlDataType.Integer, ESqlDataDirection.In);
      procedure.createParameter("receive_user_id_", receiveUserId, ESqlDataType.Integer, ESqlDataDirection.In);
      procedure.createParameter("parameters_", parameters, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("note_", note, ESqlDataType.String, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ILgEventDi(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure doSetParameter(Object logic,
                                       Object eventId,
                                       Object name,
                                       Object dataValue){
      FSqlProcedure procedure = new FSqlProcedure("Do_Set_Parameter");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("event_id_", eventId, ESqlDataType.Integer, ESqlDataDirection.In);
      procedure.createParameter("name_", name, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("data_value_", dataValue, ESqlDataType.String, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ILgEventDi(java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure doWait(Object logic,
                               Object objectId){
      FSqlProcedure procedure = new FSqlProcedure("Do_Wait");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("object_id_", objectId, ESqlDataType.Integer, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ILgEventDi(java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure doStart(Object logic,
                                Object objectId){
      FSqlProcedure procedure = new FSqlProcedure("Do_Start");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("object_id_", objectId, ESqlDataType.Integer, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ILgEventDi(java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure doStop(Object logic,
                               Object objectId,
                               Object parameters){
      FSqlProcedure procedure = new FSqlProcedure("Do_Stop");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("object_id_", objectId, ESqlDataType.Integer, ESqlDataDirection.In);
      procedure.createParameter("parameters_", parameters, ESqlDataType.String, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ILgEventDi(java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure doProcessPrecent(Object logic,
                                         Object objectId,
                                         Object percent){
      FSqlProcedure procedure = new FSqlProcedure("Do_Process_Precent");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("object_id_", objectId, ESqlDataType.Integer, ESqlDataDirection.In);
      procedure.createParameter("percent_", percent, ESqlDataType.Integer, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ILgEventDi(java.lang.Object)
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
    * @see org.mo.logic.data.ILgEventDi(java.lang.Object)
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
    * @see org.mo.logic.data.ILgEventDi(java.lang.Object, java.lang.Object)
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
    * @see org.mo.logic.data.ILgEventDi(java.lang.Object, java.lang.Object, java.lang.Object)
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
    * @see org.mo.logic.data.ILgEventDi(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
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
    * @see org.mo.logic.data.ILgEventDi(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
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
    * @see org.mo.logic.data.ILgEventDi(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
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
    * @see org.mo.logic.data.ILgEventDi()
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
