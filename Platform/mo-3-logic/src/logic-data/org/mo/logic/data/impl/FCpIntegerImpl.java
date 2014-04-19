/*
 * @(#)FCpIntegerImpl.java
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
import org.mo.logic.data.ICpInteger;

/**
 * <T>数据库逻辑包(CP_INTEGER)的代理对象</T>
 *
 * @author MAOCY
 * @version 1.0.1
 */
public class FCpIntegerImpl
      extends MSqlConnect
      implements
         ICpInteger
{
   public final String LOGIC_NAME = "CP_INTEGER";

   public FCpIntegerImpl(){
   }

   public FCpIntegerImpl(ISqlConnect connect){
      super(connect);
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpInteger(java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlFunction testLength(Object value,
                                  Object length){
      FSqlFunction function = new FSqlFunction("Test_Length");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("value_", value, ESqlDataType.Integer, ESqlDataDirection.In);
      function.createParameter("length_", length, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Boolean);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpInteger(java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlFunction testLengthMin(Object value,
                                     Object minLength){
      FSqlFunction function = new FSqlFunction("Test_Length_Min");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("value_", value, ESqlDataType.Integer, ESqlDataDirection.In);
      function.createParameter("min_length_", minLength, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Boolean);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpInteger(java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlFunction testLengthMax(Object value,
                                     Object maxLength){
      FSqlFunction function = new FSqlFunction("Test_Length_Max");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("value_", value, ESqlDataType.Integer, ESqlDataDirection.In);
      function.createParameter("max_length_", maxLength, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Boolean);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpInteger(java.lang.Object)
    */
   @Override
   public FSqlFunction testInteger(Object value){
      FSqlFunction function = new FSqlFunction("Test_Integer");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("value_", value, ESqlDataType.String, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Boolean);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpInteger(java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlFunction testGreater(Object minValue,
                                   Object minMinValue){
      FSqlFunction function = new FSqlFunction("Test_Greater");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("min_value_", minValue, ESqlDataType.Integer, ESqlDataDirection.In);
      function.createParameter("min_min_value_", minMinValue, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Boolean);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpInteger(java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlFunction testLess(Object maxValue,
                                Object maxMaxValue){
      FSqlFunction function = new FSqlFunction("Test_Less");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("max_value_", maxValue, ESqlDataType.Integer, ESqlDataDirection.In);
      function.createParameter("max_max_value_", maxMaxValue, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Boolean);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpInteger(java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlFunction testGreaterEq(Object minValue,
                                     Object minMinValue){
      FSqlFunction function = new FSqlFunction("Test_Greater_Eq");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("min_value_", minValue, ESqlDataType.Integer, ESqlDataDirection.In);
      function.createParameter("min_min_value_", minMinValue, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Boolean);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpInteger(java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlFunction testLessEq(Object maxValue,
                                  Object maxMaxValue){
      FSqlFunction function = new FSqlFunction("Test_Less_Eq");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("max_value_", maxValue, ESqlDataType.Integer, ESqlDataDirection.In);
      function.createParameter("max_max_value_", maxMaxValue, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Boolean);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpInteger(java.lang.Object)
    */
   @Override
   public FSqlFunction testPositive(Object value){
      FSqlFunction function = new FSqlFunction("Test_Positive");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("value_", value, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Boolean);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpInteger(java.lang.Object)
    */
   @Override
   public FSqlFunction testNegative(Object value){
      FSqlFunction function = new FSqlFunction("Test_Negative");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("value_", value, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Boolean);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpInteger(java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlFunction testBetween(Object value,
                                   Object minValue,
                                   Object maxValue){
      FSqlFunction function = new FSqlFunction("Test_Between");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("value_", value, ESqlDataType.Integer, ESqlDataDirection.In);
      function.createParameter("min_value_", minValue, ESqlDataType.Integer, ESqlDataDirection.In);
      function.createParameter("max_value_", maxValue, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Boolean);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpInteger(java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlFunction testRange(Object value,
                                 Object minValue,
                                 Object maxValue){
      FSqlFunction function = new FSqlFunction("Test_Range");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("value_", value, ESqlDataType.Integer, ESqlDataDirection.In);
      function.createParameter("min_value_", minValue, ESqlDataType.Integer, ESqlDataDirection.In);
      function.createParameter("max_value_", maxValue, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Boolean);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpInteger(java.lang.Object)
    */
   @Override
   public FSqlFunction parse(Object value){
      FSqlFunction function = new FSqlFunction("Parse");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("value_", value, ESqlDataType.String, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Integer);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpInteger(java.lang.Object)
    */
   @Override
   public FSqlFunction toString(Object value){
      FSqlFunction function = new FSqlFunction("To_String");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("value_", value, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.String);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpInteger(java.lang.Object)
    */
   @Override
   public FSqlFunction isInteger(Object value){
      FSqlFunction function = new FSqlFunction("Is_Integer");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("value_", value, ESqlDataType.String, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Boolean);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpInteger(java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlFunction minValue(Object value1,
                                Object value2){
      FSqlFunction function = new FSqlFunction("Min_Value");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("value1_", value1, ESqlDataType.Integer, ESqlDataDirection.In);
      function.createParameter("value2_", value2, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Integer);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpInteger(java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlFunction maxValue(Object value1,
                                Object value2){
      FSqlFunction function = new FSqlFunction("Max_Value");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("value1_", value1, ESqlDataType.Integer, ESqlDataDirection.In);
      function.createParameter("value2_", value2, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Integer);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpInteger(java.lang.Object)
    */
   @Override
   public FSqlFunction formatKilobyte(Object value){
      FSqlFunction function = new FSqlFunction("Format_Kilobyte");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("value_", value, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.String);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpInteger(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure checkLength(Object value,
                                    Object length,
                                    Object logic,
                                    Object field){
      FSqlProcedure procedure = new FSqlProcedure("Check_Length");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("value_", value, ESqlDataType.Integer, ESqlDataDirection.In);
      procedure.createParameter("length_", length, ESqlDataType.Integer, ESqlDataDirection.In);
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("field_", field, ESqlDataType.String, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpInteger(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure checkLengthMin(Object value,
                                       Object minLength,
                                       Object logic,
                                       Object field){
      FSqlProcedure procedure = new FSqlProcedure("Check_Length_Min");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("value_", value, ESqlDataType.Integer, ESqlDataDirection.In);
      procedure.createParameter("min_length_", minLength, ESqlDataType.Integer, ESqlDataDirection.In);
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("field_", field, ESqlDataType.String, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpInteger(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure checkLengthMax(Object value,
                                       Object maxLength,
                                       Object logic,
                                       Object field){
      FSqlProcedure procedure = new FSqlProcedure("Check_Length_Max");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("value_", value, ESqlDataType.Integer, ESqlDataDirection.In);
      procedure.createParameter("max_length_", maxLength, ESqlDataType.Integer, ESqlDataDirection.In);
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("field_", field, ESqlDataType.String, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpInteger(java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure checkInteger(Object value,
                                     Object logic,
                                     Object field){
      FSqlProcedure procedure = new FSqlProcedure("Check_Integer");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("value_", value, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("field_", field, ESqlDataType.String, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpInteger(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure checkGreater(Object minValue,
                                     Object minMinValue,
                                     Object logic,
                                     Object field){
      FSqlProcedure procedure = new FSqlProcedure("Check_Greater");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("min_value_", minValue, ESqlDataType.Integer, ESqlDataDirection.In);
      procedure.createParameter("min_min_value_", minMinValue, ESqlDataType.Integer, ESqlDataDirection.In);
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("field_", field, ESqlDataType.String, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpInteger(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure checkLess(Object maxValue,
                                  Object maxMaxValue,
                                  Object logic,
                                  Object field){
      FSqlProcedure procedure = new FSqlProcedure("Check_Less");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("max_value_", maxValue, ESqlDataType.Integer, ESqlDataDirection.In);
      procedure.createParameter("max_max_value_", maxMaxValue, ESqlDataType.Integer, ESqlDataDirection.In);
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("field_", field, ESqlDataType.String, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpInteger(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure checkGreaterEq(Object minValue,
                                       Object minMinValue,
                                       Object logic,
                                       Object field){
      FSqlProcedure procedure = new FSqlProcedure("Check_Greater_Eq");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("min_value_", minValue, ESqlDataType.Integer, ESqlDataDirection.In);
      procedure.createParameter("min_min_value_", minMinValue, ESqlDataType.Integer, ESqlDataDirection.In);
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("field_", field, ESqlDataType.String, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpInteger(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure checkLessEq(Object maxValue,
                                    Object maxMaxValue,
                                    Object logic,
                                    Object field){
      FSqlProcedure procedure = new FSqlProcedure("Check_Less_Eq");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("max_value_", maxValue, ESqlDataType.Integer, ESqlDataDirection.In);
      procedure.createParameter("max_max_value_", maxMaxValue, ESqlDataType.Integer, ESqlDataDirection.In);
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("field_", field, ESqlDataType.String, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpInteger(java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure checkPositive(Object value,
                                      Object logic,
                                      Object field){
      FSqlProcedure procedure = new FSqlProcedure("Check_Positive");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("value_", value, ESqlDataType.Integer, ESqlDataDirection.In);
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("field_", field, ESqlDataType.String, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpInteger(java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure checkNegative(Object value,
                                      Object logic,
                                      Object field){
      FSqlProcedure procedure = new FSqlProcedure("Check_Negative");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("value_", value, ESqlDataType.Integer, ESqlDataDirection.In);
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("field_", field, ESqlDataType.String, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpInteger(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure checkBetween(Object value,
                                     Object minValue,
                                     Object maxValue,
                                     Object logic,
                                     Object field){
      FSqlProcedure procedure = new FSqlProcedure("Check_Between");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("value_", value, ESqlDataType.Integer, ESqlDataDirection.In);
      procedure.createParameter("min_value_", minValue, ESqlDataType.Integer, ESqlDataDirection.In);
      procedure.createParameter("max_value_", maxValue, ESqlDataType.Integer, ESqlDataDirection.In);
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("field_", field, ESqlDataType.String, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpInteger(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure checkRange(Object value,
                                   Object minValue,
                                   Object maxValue,
                                   Object logic,
                                   Object field){
      FSqlProcedure procedure = new FSqlProcedure("Check_Range");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("value_", value, ESqlDataType.Integer, ESqlDataDirection.In);
      procedure.createParameter("min_value_", minValue, ESqlDataType.Integer, ESqlDataDirection.In);
      procedure.createParameter("max_value_", maxValue, ESqlDataType.Integer, ESqlDataDirection.In);
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("field_", field, ESqlDataType.String, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpInteger(java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure checkNull(Object value,
                                  Object lgName,
                                  Object name){
      FSqlProcedure procedure = new FSqlProcedure("Check_Null");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("value_", value, ESqlDataType.Integer, ESqlDataDirection.In);
      procedure.createParameter("lg_name_", lgName, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("name_", name, ESqlDataType.String, ESqlDataDirection.In);
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
