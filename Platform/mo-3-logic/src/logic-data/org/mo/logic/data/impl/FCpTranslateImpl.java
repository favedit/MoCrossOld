/*
 * @(#)FCpTranslateImpl.java
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
import org.mo.logic.data.ICpTranslate;

/**
 * <T>数据库逻辑包(CP_TRANSLATE)的代理对象</T>
 *
 * @author MAOCY
 * @version 1.0.1
 */
public class FCpTranslateImpl
      extends MSqlConnect
      implements
         ICpTranslate
{
   public final String LOGIC_NAME = "CP_TRANSLATE";

   public FCpTranslateImpl(){
   }

   public FCpTranslateImpl(ISqlConnect connect){
      super(connect);
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpTranslate(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlFunction find(Object code,
                            Object languageId,
                            Object param1,
                            Object param2,
                            Object param3,
                            Object param4,
                            Object param5,
                            Object param6){
      FSqlFunction function = new FSqlFunction("Find");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("code_", code, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("language_id_", languageId, ESqlDataType.Integer, ESqlDataDirection.In);
      function.createParameter("param1_", param1, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("param2_", param2, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("param3_", param3, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("param4_", param4, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("param5_", param5, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("param6_", param6, ESqlDataType.String, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.String);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpTranslate(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlFunction translatePlsql(Object logicName,
                                      Object code,
                                      Object param1,
                                      Object param2,
                                      Object param3,
                                      Object param4,
                                      Object param5,
                                      Object param6){
      FSqlFunction function = new FSqlFunction("Translate_Plsql");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("logic_name_", logicName, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("code_", code, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("param1_", param1, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("param2_", param2, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("param3_", param3, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("param4_", param4, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("param5_", param5, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("param6_", param6, ESqlDataType.String, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.String);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpTranslate(java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlFunction translateDataset(Object logicName,
                                        Object languageId){
      FSqlFunction function = new FSqlFunction("Translate_Dataset");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("logic_name_", logicName, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("language_id_", languageId, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.String);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpTranslate(java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlFunction translateDatasetField(Object logicName,
                                             Object field,
                                             Object languageId){
      FSqlFunction function = new FSqlFunction("Translate_Dataset_Field");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("logic_name_", logicName, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("field_", field, ESqlDataType.String, ESqlDataDirection.In);
      function.createParameter("language_id_", languageId, ESqlDataType.Integer, ESqlDataDirection.In);
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
