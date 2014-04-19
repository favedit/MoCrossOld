/*
 * @(#)FCpMessageImpl.java
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
import org.mo.logic.data.ICpMessage;

/**
 * <T>数据库逻辑包(CP_MESSAGE)的代理对象</T>
 *
 * @author MAOCY
 * @version 1.0.1
 */
public class FCpMessageImpl
      extends MSqlConnect
      implements
         ICpMessage
{
   public final String LOGIC_NAME = "CP_MESSAGE";

   public FCpMessageImpl(){
   }

   public FCpMessageImpl(ISqlConnect connect){
      super(connect);
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpMessage()
    */
   @Override
   public FSqlProcedure reset(){
      FSqlProcedure procedure = new FSqlProcedure("Reset");
      procedure.setLogicName(LOGIC_NAME);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpMessage(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure addInfo(Object logicName,
                                Object code,
                                Object param1,
                                Object param2,
                                Object param3,
                                Object param4,
                                Object param5,
                                Object param6){
      FSqlProcedure procedure = new FSqlProcedure("Add_Info");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("logic_name_", logicName, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("code_", code, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("param1_", param1, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("param2_", param2, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("param3_", param3, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("param4_", param4, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("param5_", param5, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("param6_", param6, ESqlDataType.String, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpMessage(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure addWarn(Object logicName,
                                Object code,
                                Object param1,
                                Object param2,
                                Object param3,
                                Object param4,
                                Object param5,
                                Object param6){
      FSqlProcedure procedure = new FSqlProcedure("Add_Warn");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("logic_name_", logicName, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("code_", code, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("param1_", param1, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("param2_", param2, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("param3_", param3, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("param4_", param4, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("param5_", param5, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("param6_", param6, ESqlDataType.String, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpMessage(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure raiseFatal(Object logicName,
                                   Object code,
                                   Object param1,
                                   Object param2,
                                   Object param3,
                                   Object param4,
                                   Object param5,
                                   Object param6){
      FSqlProcedure procedure = new FSqlProcedure("Raise_Fatal");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("logic_name_", logicName, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("code_", code, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("param1_", param1, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("param2_", param2, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("param3_", param3, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("param4_", param4, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("param5_", param5, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("param6_", param6, ESqlDataType.String, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpMessage(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure raiseLogic(Object logicName,
                                   Object code,
                                   Object param1,
                                   Object param2,
                                   Object param3,
                                   Object param4,
                                   Object param5,
                                   Object param6){
      FSqlProcedure procedure = new FSqlProcedure("Raise_Logic");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("logic_name_", logicName, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("code_", code, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("param1_", param1, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("param2_", param2, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("param3_", param3, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("param4_", param4, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("param5_", param5, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("param6_", param6, ESqlDataType.String, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpMessage(java.lang.Object)
    */
   @Override
   public FSqlProcedure raiseLockTable(Object logicName){
      FSqlProcedure procedure = new FSqlProcedure("Raise_Lock_Table");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("logic_name_", logicName, ESqlDataType.String, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpMessage(java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure raiseLockRecord(Object logicName,
                                        Object objectId,
                                        Object objectVersion){
      FSqlProcedure procedure = new FSqlProcedure("Raise_Lock_Record");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("logic_name_", logicName, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("object_id_", objectId, ESqlDataType.Integer, ESqlDataDirection.In);
      procedure.createParameter("object_version_", objectVersion, ESqlDataType.String, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpMessage(java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure raiseNoRecord(Object logicName,
                                      Object objectId,
                                      Object objectVersion){
      FSqlProcedure procedure = new FSqlProcedure("Raise_No_Record");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("logic_name_", logicName, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("object_id_", objectId, ESqlDataType.Integer, ESqlDataDirection.In);
      procedure.createParameter("object_version_", objectVersion, ESqlDataType.String, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpMessage(java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure raiseDuplicateRecord(Object logicName,
                                             Object objectId,
                                             Object objectVersion){
      FSqlProcedure procedure = new FSqlProcedure("Raise_Duplicate_Record");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("logic_name_", logicName, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("object_id_", objectId, ESqlDataType.Integer, ESqlDataDirection.In);
      procedure.createParameter("object_version_", objectVersion, ESqlDataType.String, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpMessage(java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure raiseRecordChange(Object logicName,
                                          Object objectId,
                                          Object objectVersion){
      FSqlProcedure procedure = new FSqlProcedure("Raise_Record_Change");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("logic_name_", logicName, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("object_id_", objectId, ESqlDataType.Integer, ESqlDataDirection.In);
      procedure.createParameter("object_version_", objectVersion, ESqlDataType.String, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpMessage(java.lang.Object)
    */
   @Override
   public FSqlProcedure fetchMessage(Object message){
      FSqlProcedure procedure = new FSqlProcedure("Fetch_Message");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("message_", message, ESqlDataType.String, ESqlDataDirection.InOut);
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
