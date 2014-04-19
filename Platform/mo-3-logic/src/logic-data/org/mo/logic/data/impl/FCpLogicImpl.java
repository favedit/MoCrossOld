/*
 * @(#)FCpLogicImpl.java
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
import org.mo.logic.data.ICpLogic;

/**
 * <T>数据库逻辑包(CP_LOGIC)的代理对象</T>
 *
 * @author MAOCY
 * @version 1.0.1
 */
public class FCpLogicImpl
      extends MSqlConnect
      implements
         ICpLogic
{
   public final String LOGIC_NAME = "CP_LOGIC";

   public FCpLogicImpl(){
   }

   public FCpLogicImpl(ISqlConnect connect){
      super(connect);
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpLogic(java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlFunction makeObjectInformation(Object ouid,
                                             Object ovld,
                                             Object over){
      FSqlFunction function = new FSqlFunction("Make_Object_Information");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("ouid_", ouid, ESqlDataType.Integer, ESqlDataDirection.In);
      function.createParameter("ovld_", ovld, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("over_", over, ESqlDataType.String, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.String);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpLogic()
    */
   @Override
   public FSqlFunction isSucceed(){
      FSqlFunction function = new FSqlFunction("Is_Succeed");
      function.setLogicName(LOGIC_NAME);
      function.setReturnType(ESqlDataType.Boolean);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpLogic()
    */
   @Override
   public FSqlFunction isFailed(){
      FSqlFunction function = new FSqlFunction("Is_Failed");
      function.setLogicName(LOGIC_NAME);
      function.setReturnType(ESqlDataType.Boolean);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpLogic()
    */
   @Override
   public FSqlFunction getResult(){
      FSqlFunction function = new FSqlFunction("Get_Result");
      function.setLogicName(LOGIC_NAME);
      function.setReturnType(ESqlDataType.String);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpLogic()
    */
   @Override
   public FSqlFunction getObjectOwner(){
      FSqlFunction function = new FSqlFunction("Get_Object_Owner");
      function.setLogicName(LOGIC_NAME);
      function.setReturnType(ESqlDataType.Integer);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpLogic()
    */
   @Override
   public FSqlFunction getObjectVersion(){
      FSqlFunction function = new FSqlFunction("Get_Object_Version");
      function.setLogicName(LOGIC_NAME);
      function.setReturnType(ESqlDataType.Date);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpLogic()
    */
   @Override
   public FSqlFunction isDataDeleteMode(){
      FSqlFunction function = new FSqlFunction("Is_Data_Delete_Mode");
      function.setLogicName(LOGIC_NAME);
      function.setReturnType(ESqlDataType.Boolean);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpLogic(java.lang.Object)
    */
   @Override
   public FSqlFunction getUserLinksInfo(Object links){
      FSqlFunction function = new FSqlFunction("Get_User_Links_Info");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("links_", links, ESqlDataType.String, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.String);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpLogic(java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlFunction getUserPickerPack(Object text,
                                         Object recordTypeName,
                                         Object recordId){
      FSqlFunction function = new FSqlFunction("Get_User_Picker_Pack");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("text_", text, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("record_type_name_", recordTypeName, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("record_id_", recordId, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.String);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpLogic()
    */
   @Override
   public FSqlProcedure reset(){
      FSqlProcedure procedure = new FSqlProcedure("Reset");
      procedure.setLogicName(LOGIC_NAME);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpLogic(java.lang.Object)
    */
   @Override
   public FSqlProcedure setResult(Object result){
      FSqlProcedure procedure = new FSqlProcedure("Set_Result");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("result_", result, ESqlDataType.String, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpLogic()
    */
   @Override
   public FSqlProcedure setSucceed(){
      FSqlProcedure procedure = new FSqlProcedure("Set_Succeed");
      procedure.setLogicName(LOGIC_NAME);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpLogic()
    */
   @Override
   public FSqlProcedure setFailed(){
      FSqlProcedure procedure = new FSqlProcedure("Set_Failed");
      procedure.setLogicName(LOGIC_NAME);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpLogic()
    */
   @Override
   public FSqlProcedure setEndPage(){
      FSqlProcedure procedure = new FSqlProcedure("Set_End_Page");
      procedure.setLogicName(LOGIC_NAME);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpLogic(java.lang.Object)
    */
   @Override
   public FSqlProcedure executeLogic(Object procedureLogic){
      FSqlProcedure procedure = new FSqlProcedure("Execute_Logic");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("procedure_logic_", procedureLogic, ESqlDataType.String, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpLogic(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure storeUserLink(Object logic,
                                      Object recordTypeId,
                                      Object recordId,
                                      Object link,
                                      Object resultLink){
      FSqlProcedure procedure = new FSqlProcedure("Store_User_Link");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("record_type_id_", recordTypeId, ESqlDataType.Integer, ESqlDataDirection.In);
      procedure.createParameter("record_id_", recordId, ESqlDataType.Integer, ESqlDataDirection.In);
      procedure.createParameter("link_", link, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("result_link_", resultLink, ESqlDataType.String, ESqlDataDirection.Out);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpLogic(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure storeUserLinks(Object logic,
                                       Object recordName,
                                       Object recordId,
                                       Object links,
                                       Object resultLinks){
      FSqlProcedure procedure = new FSqlProcedure("Store_User_Links");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("record_name_", recordName, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("record_id_", recordId, ESqlDataType.Integer, ESqlDataDirection.In);
      procedure.createParameter("links_", links, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("result_links_", resultLinks, ESqlDataType.String, ESqlDataDirection.Out);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpLogic(java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure removeUserLinks(Object recordName,
                                        Object recordId){
      FSqlProcedure procedure = new FSqlProcedure("Remove_User_Links");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("record_name_", recordName, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("record_id_", recordId, ESqlDataType.Integer, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpLogic(java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure findAllInformation(Object params,
                                           Object results){
      FSqlProcedure procedure = new FSqlProcedure("Find_All_Information");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("params_", params, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("results_", results, ESqlDataType.String, ESqlDataDirection.InOut);
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
