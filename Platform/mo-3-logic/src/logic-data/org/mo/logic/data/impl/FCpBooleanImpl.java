/*
 * @(#)FCpBooleanImpl.java
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
import org.mo.logic.data.ICpBoolean;

/**
 * <T>数据库逻辑包(CP_BOOLEAN)的代理对象</T>
 *
 * @author MAOCY
 * @version 1.0.1
 */
public class FCpBooleanImpl
      extends MSqlConnect
      implements
         ICpBoolean
{
   public final String LOGIC_NAME = "CP_BOOLEAN";

   public FCpBooleanImpl(){
   }

   public FCpBooleanImpl(ISqlConnect connect){
      super(connect);
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpBoolean(java.lang.Object)
    */
   @Override
   public FSqlFunction parse(Object value){
      FSqlFunction function = new FSqlFunction("Parse");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("value_", value, ESqlDataType.String, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.String);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpBoolean(java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlFunction isTrue(Object value,
                              Object allowNull){
      FSqlFunction function = new FSqlFunction("Is_True");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("value_", value, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("allow_null_", allowNull, ESqlDataType.Boolean, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Boolean);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpBoolean(java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlFunction isFalse(Object value,
                               Object allowNull){
      FSqlFunction function = new FSqlFunction("Is_False");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("value_", value, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("allow_null_", allowNull, ESqlDataType.Boolean, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Boolean);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpBoolean(java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlFunction toString(Object value,
                                Object allowNull){
      FSqlFunction function = new FSqlFunction("To_String");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("value_", value, ESqlDataType.Boolean, ESqlDataDirection.In);
      function.createParameter("allow_null_", allowNull, ESqlDataType.Boolean, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.String);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpBoolean(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlFunction toString(Object value,
                                Object trueValue,
                                Object falseValue,
                                Object allowNull){
      FSqlFunction function = new FSqlFunction("To_String");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("value_", value, ESqlDataType.Boolean, ESqlDataDirection.In);
      function.createParameter("true_value_", trueValue, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("false_value_", falseValue, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("allow_null_", allowNull, ESqlDataType.Boolean, ESqlDataDirection.In);
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
