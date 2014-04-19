/*
 * @(#)FCpNumberImpl.java
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
import org.mo.logic.data.ICpNumber;

/**
 * <T>数据库逻辑包(CP_NUMBER)的代理对象</T>
 *
 * @author MAOCY
 * @version 1.0.1
 */
public class FCpNumberImpl
      extends MSqlConnect
      implements
         ICpNumber
{
   public final String LOGIC_NAME = "CP_NUMBER";

   public FCpNumberImpl(){
   }

   public FCpNumberImpl(ISqlConnect connect){
      super(connect);
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpNumber(java.lang.Object)
    */
   @Override
   public FSqlFunction parse(Object value){
      FSqlFunction function = new FSqlFunction("Parse");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("value_", value, ESqlDataType.String, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Float);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpNumber(java.lang.Object)
    */
   @Override
   public FSqlFunction toString(Object value){
      FSqlFunction function = new FSqlFunction("To_String");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("value_", value, ESqlDataType.Float, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.String);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpNumber(java.lang.Object)
    */
   @Override
   public FSqlFunction isNumber(Object value){
      FSqlFunction function = new FSqlFunction("Is_Number");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("value_", value, ESqlDataType.String, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Boolean);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpNumber(java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlFunction minNumber(Object value1,
                                 Object value2){
      FSqlFunction function = new FSqlFunction("Min_Number");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("value1_", value1, ESqlDataType.Integer, ESqlDataDirection.In);
      function.createParameter("value2_", value2, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Integer);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpNumber(java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlFunction maxNumber(Object value1,
                                 Object value2){
      FSqlFunction function = new FSqlFunction("Max_Number");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("value1_", value1, ESqlDataType.Integer, ESqlDataDirection.In);
      function.createParameter("value2_", value2, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Integer);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpNumber(java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlFunction fixMinNumber(Object value,
                                    Object minRange,
                                    Object minSum){
      FSqlFunction function = new FSqlFunction("Fix_Min_Number");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("value_", value, ESqlDataType.Integer, ESqlDataDirection.In);
      function.createParameter("min_range_", minRange, ESqlDataType.Integer, ESqlDataDirection.In);
      function.createParameter("min_sum_", minSum, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Integer);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpNumber(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlFunction caseValue(Object value1,
                                 Object value2,
                                 Object case1,
                                 Object case2){
      FSqlFunction function = new FSqlFunction("Case_Value");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("value1_", value1, ESqlDataType.Integer, ESqlDataDirection.In);
      function.createParameter("value2_", value2, ESqlDataType.Integer, ESqlDataDirection.In);
      function.createParameter("case1_", case1, ESqlDataType.Integer, ESqlDataDirection.In);
      function.createParameter("case2_", case2, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Integer);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpNumber(java.lang.Object)
    */
   @Override
   public FSqlFunction formatKilobyte(Object value){
      FSqlFunction function = new FSqlFunction("Format_Kilobyte");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("value_", value, ESqlDataType.Float, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.String);
      activeConnection().execute(function);
      return function;
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
