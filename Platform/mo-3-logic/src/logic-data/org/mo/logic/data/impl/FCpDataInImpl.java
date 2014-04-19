/*
 * @(#)FCpDataInImpl.java
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
import org.mo.logic.data.ICpDataIn;

/**
 * <T>数据库逻辑包(CP_DATA_IN)的代理对象</T>
 *
 * @author MAOCY
 * @version 1.0.1
 */
public class FCpDataInImpl
      extends MSqlConnect
      implements
         ICpDataIn
{
   public final String LOGIC_NAME = "CP_DATA_IN";

   public FCpDataInImpl(){
   }

   public FCpDataInImpl(ISqlConnect connect){
      super(connect);
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpDataIn()
    */
   @Override
   public FSqlFunction getSessionOwner(){
      FSqlFunction function = new FSqlFunction("Get_Session_Owner");
      function.setLogicName(LOGIC_NAME);
      function.setReturnType(ESqlDataType.Integer);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpDataIn()
    */
   @Override
   public FSqlFunction getUnitCount(){
      FSqlFunction function = new FSqlFunction("Get_Unit_Count");
      function.setLogicName(LOGIC_NAME);
      function.setReturnType(ESqlDataType.Float);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpDataIn()
    */
   @Override
   public FSqlFunction getDatasetCount(){
      FSqlFunction function = new FSqlFunction("Get_Dataset_Count");
      function.setLogicName(LOGIC_NAME);
      function.setReturnType(ESqlDataType.Float);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpDataIn(java.lang.Object)
    */
   @Override
   public FSqlFunction indexOfDataset(Object name){
      FSqlFunction function = new FSqlFunction("Index_Of_Dataset");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("name_", name, ESqlDataType.String, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Float);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpDataIn()
    */
   @Override
   public FSqlFunction getContainerCount(){
      FSqlFunction function = new FSqlFunction("Get_Container_Count");
      function.setLogicName(LOGIC_NAME);
      function.setReturnType(ESqlDataType.Float);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpDataIn()
    */
   @Override
   public FSqlProcedure reset(){
      FSqlProcedure procedure = new FSqlProcedure("Reset");
      procedure.setLogicName(LOGIC_NAME);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpDataIn(java.lang.Object)
    */
   @Override
   public FSqlProcedure unpackSession(Object pack){
      FSqlProcedure procedure = new FSqlProcedure("Unpack_Session");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("pack_", pack, ESqlDataType.String, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpDataIn(java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure unpackUnit(Object name,
                                   Object pack){
      FSqlProcedure procedure = new FSqlProcedure("Unpack_Unit");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("name_", name, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("pack_", pack, ESqlDataType.String, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpDataIn(java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure unpackDataset(Object name,
                                      Object pack){
      FSqlProcedure procedure = new FSqlProcedure("Unpack_Dataset");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("name_", name, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("pack_", pack, ESqlDataType.String, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpDataIn(java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure unpackContainer(Object name,
                                        Object pack){
      FSqlProcedure procedure = new FSqlProcedure("Unpack_Container");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("name_", name, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("pack_", pack, ESqlDataType.String, ESqlDataDirection.In);
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
