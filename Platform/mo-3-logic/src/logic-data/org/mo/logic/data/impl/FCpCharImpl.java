/*
 * @(#)FCpCharImpl.java
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
import org.mo.logic.data.ICpChar;

/**
 * <T>数据库逻辑包(CP_CHAR)的代理对象</T>
 *
 * @author MAOCY
 * @version 1.0.1
 */
public class FCpCharImpl
      extends MSqlConnect
      implements
         ICpChar
{
   public final String LOGIC_NAME = "CP_CHAR";

   public FCpCharImpl(){
   }

   public FCpCharImpl(ISqlConnect connect){
      super(connect);
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpChar(java.lang.Object)
    */
   @Override
   public FSqlFunction parse(Object text){
      FSqlFunction function = new FSqlFunction("Parse");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("text_", text, ESqlDataType.String, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.String);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpChar(java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlFunction equalsIgnoreCase(Object value1,
                                        Object value2){
      FSqlFunction function = new FSqlFunction("Equals_Ignore_Case");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("value1_", value1, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("value2_", value2, ESqlDataType.String, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Boolean);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpChar(java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlFunction repeat(Object value,
                              Object count){
      FSqlFunction function = new FSqlFunction("Repeat");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("value_", value, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("count_", count, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.String);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpChar(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlFunction indexOf(Object text,
                               Object value,
                               Object toffset,
                               Object carecase){
      FSqlFunction function = new FSqlFunction("Index_Of");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("text_", text, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("value_", value, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("toffset_", toffset, ESqlDataType.Integer, ESqlDataDirection.In);
      function.createParameter("carecase_", carecase, ESqlDataType.Boolean, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Integer);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpChar(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlFunction startsWith(Object text,
                                  Object prefix,
                                  Object toffset,
                                  Object carecase){
      FSqlFunction function = new FSqlFunction("Starts_With");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("text_", text, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("prefix_", prefix, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("toffset_", toffset, ESqlDataType.Integer, ESqlDataDirection.In);
      function.createParameter("carecase_", carecase, ESqlDataType.Boolean, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Boolean);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpChar(java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlFunction endsWith(Object text,
                                Object suffix,
                                Object carecase){
      FSqlFunction function = new FSqlFunction("Ends_With");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("text_", text, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("suffix_", suffix, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("carecase_", carecase, ESqlDataType.Boolean, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Boolean);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpChar(java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlFunction lastIndexOf(Object text,
                                   Object findText){
      FSqlFunction function = new FSqlFunction("Last_Index_Of");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("text_", text, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("find_text_", findText, ESqlDataType.String, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Integer);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpChar(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlFunction substring(Object text,
                                 Object beginStr,
                                 Object endStr,
                                 Object carecase){
      FSqlFunction function = new FSqlFunction("Substring");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("text_", text, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("begin_str_", beginStr, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("end_str_", endStr, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("carecase_", carecase, ESqlDataType.Boolean, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.String);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpChar(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlFunction caseValue(Object value1,
                                 Object value2,
                                 Object case1,
                                 Object case2){
      FSqlFunction function = new FSqlFunction("Case_Value");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("value1_", value1, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("value2_", value2, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("case1_", case1, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("case2_", case2, ESqlDataType.String, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.String);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpChar(java.lang.Object)
    */
   @Override
   public FSqlFunction isCharacter(Object value){
      FSqlFunction function = new FSqlFunction("Is_Character");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("value_", value, ESqlDataType.String, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Boolean);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpChar(java.lang.Object)
    */
   @Override
   public FSqlFunction isLetter(Object value){
      FSqlFunction function = new FSqlFunction("Is_Letter");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("value_", value, ESqlDataType.String, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Boolean);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpChar(java.lang.Object)
    */
   @Override
   public FSqlFunction initCap(Object value){
      FSqlFunction function = new FSqlFunction("Init_Cap");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("value_", value, ESqlDataType.String, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.String);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpChar(java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure parse(Object text,
                              Object key,
                              Object value){
      FSqlProcedure procedure = new FSqlProcedure("Parse");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("text_", text, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("key_", key, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("value_", value, ESqlDataType.String, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpChar(java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure remove(Object text,
                               Object value){
      FSqlProcedure procedure = new FSqlProcedure("Remove");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("text_", text, ESqlDataType.String, ESqlDataDirection.InOut);
      procedure.createParameter("value_", value, ESqlDataType.String, ESqlDataDirection.In);
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
