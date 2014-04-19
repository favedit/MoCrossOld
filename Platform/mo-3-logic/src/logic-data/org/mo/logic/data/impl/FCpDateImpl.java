/*
 * @(#)FCpDateImpl.java
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
import org.mo.logic.data.ICpDate;

/**
 * <T>数据库逻辑包(CP_DATE)的代理对象</T>
 *
 * @author MAOCY
 * @version 1.0.1
 */
public class FCpDateImpl
      extends MSqlConnect
      implements
         ICpDate
{
   public final String LOGIC_NAME = "CP_DATE";

   public FCpDateImpl(){
   }

   public FCpDateImpl(ISqlConnect connect){
      super(connect);
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpDate(java.lang.Object)
    */
   @Override
   public FSqlFunction parse(Object value){
      FSqlFunction function = new FSqlFunction("Parse");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("value_", value, ESqlDataType.String, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Date);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpDate()
    */
   @Override
   public FSqlFunction getMinDate(){
      FSqlFunction function = new FSqlFunction("Get_Min_Date");
      function.setLogicName(LOGIC_NAME);
      function.setReturnType(ESqlDataType.Date);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpDate()
    */
   @Override
   public FSqlFunction getMaxDate(){
      FSqlFunction function = new FSqlFunction("Get_Max_Date");
      function.setLogicName(LOGIC_NAME);
      function.setReturnType(ESqlDataType.Date);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpDate(java.lang.Object)
    */
   @Override
   public FSqlFunction toString(Object value){
      FSqlFunction function = new FSqlFunction("To_String");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("value_", value, ESqlDataType.Date, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.String);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpDate()
    */
   @Override
   public FSqlFunction currentDate(){
      FSqlFunction function = new FSqlFunction("Current_Date");
      function.setLogicName(LOGIC_NAME);
      function.setReturnType(ESqlDataType.Date);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpDate()
    */
   @Override
   public FSqlFunction currentTime(){
      FSqlFunction function = new FSqlFunction("Current_Time");
      function.setLogicName(LOGIC_NAME);
      function.setReturnType(ESqlDataType.Date);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpDate(java.lang.Object)
    */
   @Override
   public FSqlFunction testMonth(Object month){
      FSqlFunction function = new FSqlFunction("Test_Month");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("month_", month, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Boolean);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpDate(java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlFunction testDay(Object year,
                               Object month,
                               Object day){
      FSqlFunction function = new FSqlFunction("Test_Day");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("year_", year, ESqlDataType.Integer, ESqlDataDirection.In);
      function.createParameter("month_", month, ESqlDataType.Integer, ESqlDataDirection.In);
      function.createParameter("day_", day, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Boolean);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpDate(java.lang.Object)
    */
   @Override
   public FSqlFunction testHour(Object hour){
      FSqlFunction function = new FSqlFunction("Test_Hour");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("hour_", hour, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Boolean);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpDate(java.lang.Object)
    */
   @Override
   public FSqlFunction testMinute(Object minute){
      FSqlFunction function = new FSqlFunction("Test_Minute");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("minute_", minute, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Boolean);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpDate(java.lang.Object)
    */
   @Override
   public FSqlFunction testSecond(Object second){
      FSqlFunction function = new FSqlFunction("Test_Second");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("second_", second, ESqlDataType.Integer, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Boolean);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpDate(java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlFunction testBetween(Object date,
                                   Object beforeDate,
                                   Object afterDate){
      FSqlFunction function = new FSqlFunction("Test_Between");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("date_", date, ESqlDataType.Date, ESqlDataDirection.In);
      function.createParameter("before_date_", beforeDate, ESqlDataType.Date, ESqlDataDirection.In);
      function.createParameter("after_date_", afterDate, ESqlDataType.Date, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Boolean);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpDate(java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlFunction testRange(Object date,
                                 Object beforeDate,
                                 Object afterDate){
      FSqlFunction function = new FSqlFunction("Test_Range");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("date_", date, ESqlDataType.Date, ESqlDataDirection.In);
      function.createParameter("before_date_", beforeDate, ESqlDataType.Date, ESqlDataDirection.In);
      function.createParameter("after_date_", afterDate, ESqlDataType.Date, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Boolean);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpDate(java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlFunction testAfter(Object date,
                                 Object afterDate){
      FSqlFunction function = new FSqlFunction("Test_After");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("date_", date, ESqlDataType.Date, ESqlDataDirection.In);
      function.createParameter("after_date_", afterDate, ESqlDataType.Date, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Boolean);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpDate(java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlFunction testBefore(Object date,
                                  Object beforeDate){
      FSqlFunction function = new FSqlFunction("Test_Before");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("date_", date, ESqlDataType.Date, ESqlDataDirection.In);
      function.createParameter("before_date_", beforeDate, ESqlDataType.Date, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Boolean);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpDate(java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlFunction testAfterEq(Object date,
                                   Object afterDate){
      FSqlFunction function = new FSqlFunction("Test_After_Eq");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("date_", date, ESqlDataType.Date, ESqlDataDirection.In);
      function.createParameter("after_date_", afterDate, ESqlDataType.Date, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Boolean);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpDate(java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlFunction testBeforeEq(Object date,
                                    Object beforeDate){
      FSqlFunction function = new FSqlFunction("Test_Before_Eq");
      function.setLogicName(LOGIC_NAME);
      function.createParameter("date_", date, ESqlDataType.Date, ESqlDataDirection.In);
      function.createParameter("before_date_", beforeDate, ESqlDataType.Date, ESqlDataDirection.In);
      function.setReturnType(ESqlDataType.Boolean);
      activeConnection().execute(function);
      return function;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpDate(java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure checkDate(Object value,
                                  Object logic,
                                  Object field){
      FSqlProcedure procedure = new FSqlProcedure("Check_Date");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("value_", value, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("field_", field, ESqlDataType.String, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpDate(java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure checkTime(Object value,
                                  Object logic,
                                  Object field){
      FSqlProcedure procedure = new FSqlProcedure("Check_Time");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("value_", value, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("field_", field, ESqlDataType.String, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpDate(java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure checkDatetime(Object value,
                                      Object logic,
                                      Object field){
      FSqlProcedure procedure = new FSqlProcedure("Check_Datetime");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("value_", value, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("field_", field, ESqlDataType.String, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpDate(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure checkBetween(Object value,
                                     Object logic,
                                     Object field,
                                     Object beforeDate,
                                     Object afterDate){
      FSqlProcedure procedure = new FSqlProcedure("Check_Between");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("value_", value, ESqlDataType.Date, ESqlDataDirection.In);
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("field_", field, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("before_date_", beforeDate, ESqlDataType.Date, ESqlDataDirection.In);
      procedure.createParameter("after_date_", afterDate, ESqlDataType.Date, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpDate(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure checkRange(Object value,
                                   Object logic,
                                   Object field,
                                   Object beforeDate,
                                   Object afterDate){
      FSqlProcedure procedure = new FSqlProcedure("Check_Range");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("value_", value, ESqlDataType.Date, ESqlDataDirection.In);
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("field_", field, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("before_date_", beforeDate, ESqlDataType.Date, ESqlDataDirection.In);
      procedure.createParameter("after_date_", afterDate, ESqlDataType.Date, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpDate(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure checkAfter(Object value,
                                   Object logic,
                                   Object field,
                                   Object afterDate){
      FSqlProcedure procedure = new FSqlProcedure("Check_After");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("value_", value, ESqlDataType.Date, ESqlDataDirection.In);
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("field_", field, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("after_date_", afterDate, ESqlDataType.Date, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpDate(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure checkBefore(Object value,
                                    Object logic,
                                    Object field,
                                    Object beforeDate){
      FSqlProcedure procedure = new FSqlProcedure("Check_Before");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("value_", value, ESqlDataType.Date, ESqlDataDirection.In);
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("field_", field, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("before_date_", beforeDate, ESqlDataType.Date, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpDate(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure checkAfterEq(Object value,
                                     Object logic,
                                     Object field,
                                     Object afterDate){
      FSqlProcedure procedure = new FSqlProcedure("Check_After_Eq");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("value_", value, ESqlDataType.Date, ESqlDataDirection.In);
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("field_", field, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("after_date_", afterDate, ESqlDataType.Date, ESqlDataDirection.In);
      activeConnection().execute(procedure);
      return procedure;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.data.ICpDate(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
    */
   @Override
   public FSqlProcedure checkBeforeEq(Object value,
                                      Object logic,
                                      Object field,
                                      Object beforeDate){
      FSqlProcedure procedure = new FSqlProcedure("Check_Before_Eq");
      procedure.setLogicName(LOGIC_NAME);
      procedure.createParameter("value_", value, ESqlDataType.Date, ESqlDataDirection.In);
      procedure.createParameter("logic_", logic, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("field_", field, ESqlDataType.String, ESqlDataDirection.In);
      procedure.createParameter("before_date_", beforeDate, ESqlDataType.Date, ESqlDataDirection.In);
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
