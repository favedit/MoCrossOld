/*
 * @(#)XBaseTime.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.schedule.base;

import org.mo.com.lang.FMultiString;
import org.mo.com.lang.RString;
import org.mo.com.lang.face.AName;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlObject;
import org.mo.com.xml.IXmlObject;
import org.mo.logic.schedule.common.XObjectFace;

/**
 * <p>计划时间对象的XML节点基类</p>
 *
 * @author system
 */
public abstract class XBaseTime
      extends FXmlObject
      implements
         XObjectFace
{

   public static final String NAME = "Time";

   public static boolean isName(String name){
      return NAME.equals(name);
   }

   public static boolean isInstance(IXmlObject xobject){
      return NAME.equals(xobject.name());
   }

   /**
    * 名称的名称定义
    */
   public static final String PTY_NAME = "name";

   /**
    * 标签的名称定义
    */
   public static final String PTY_LABEL = "label";

   /**
    * 注释的名称定义
    */
   public static final String PTY_NOTE = "note";

   /**
    * 类型的名称定义
    */
   public static final String PTY__TYPE = "_type";

   /**
    * 有效性的名称定义
    */
   public static final String PTY_IS_VALID = "is_valid";

   /**
    * 执行时间的名称定义
    */
   public static final String PTY_EXECUTE_TIME = "execute_time";

   /**
    * 计划状态的名称定义
    */
   public static final String PTY_STATUE_CD = "statue_cd";

   /**
    * 时间类型的名称定义
    */
   public static final String PTY_TYPE_CD = "type_cd";

   /**
    * 时间间隔的名称定义
    */
   public static final String PTY_INTERVAL = "interval";

   /**
    * 执行标识的名称定义
    */
   public static final String PTY_DAY_FLAG = "day_flag";

   /**
    * 执行标识的名称定义
    */
   public static final String PTY_WEEK_FLAG = "week_flag";

   /**
    * 执行标识的名称定义
    */
   public static final String PTY_MONTH_FLAG = "month_flag";

   /**
    * 更新时间的名称定义
    */
   public static final String PTY_UPDATE_DATE = "update_date";

   @Override
   public String name(){
      return NAME;
   }

   /**
    * 名称的定义
    */
   @AName("name")
   protected String _name;

   /**
    * 标签的定义
    */
   @AName("label")
   protected FMultiString _label = new FMultiString();

   /**
    * 注释的定义
    */
   @AName("note")
   protected FMultiString _note = new FMultiString();

   /**
    * 类型的定义
    */
   @AName("_type")
   protected String __type;

   /**
    * 有效性的定义
    */
   @AName("is_valid")
   protected String _isValid;

   /**
    * 执行时间的定义
    */
   @AName("execute_time")
   protected String _executeTime;

   /**
    * 计划状态的定义
    */
   @AName("statue_cd")
   protected String _statueCd;

   /**
    * 时间类型的定义
    */
   @AName("type_cd")
   protected String _typeCd;

   /**
    * 时间间隔的定义
    */
   @AName("interval")
   protected String _interval;

   /**
    * 执行标识的定义
    */
   @AName("day_flag")
   protected String _dayFlag;

   /**
    * 执行标识的定义
    */
   @AName("week_flag")
   protected String _weekFlag;

   /**
    * 执行标识的定义
    */
   @AName("month_flag")
   protected String _monthFlag;

   /**
    * 更新时间的定义
    */
   @AName("update_date")
   protected String _updateDate;

   /**
    * 获得名称的内容。
    *
    * @return 名称
    */
   @Override
   public String getName(){
      return _name;
   }

   /**
    * 设置名称的内容。
    *
    * @param value 名称
    */
   @Override
   public void setName(String value){
      _name = value;
   }

   /**
    * 获得标签的内容。
    *
    * @return 标签
    */
   @Override
   public String getLabel(){
      return _label.get();
   }

   /**
    * 设置标签的内容。
    *
    * @param value 标签
    */
   @Override
   public void setLabel(String value){
      _label.set(value);
   }

   /**
    * 获得注释的内容。
    *
    * @return 注释
    */
   @Override
   public String getNote(){
      return _note.get();
   }

   /**
    * 设置注释的内容。
    *
    * @param value 注释
    */
   @Override
   public void setNote(String value){
      _note.set(value);
   }

   /**
    * 获得类型的内容。
    *
    * @return 类型
    */
   @Override
   public String get_type(){
      return __type;
   }

   /**
    * 设置类型的内容。
    *
    * @param value 类型
    */
   @Override
   public void set_type(String value){
      __type = value;
   }

   /**
    * 获得有效性的内容。
    *
    * @return 有效性
    */
   @Override
   public String getIsValid(){
      return _isValid;
   }

   /**
    * 设置有效性的内容。
    *
    * @param value 有效性
    */
   @Override
   public void setIsValid(String value){
      _isValid = value;
   }

   /**
    * 获得执行时间的内容。
    *
    * @return 执行时间
    */
   public String getExecuteTime(){
      return _executeTime;
   }

   /**
    * 设置执行时间的内容。
    *
    * @param value 执行时间
    */
   public void setExecuteTime(String value){
      _executeTime = value;
   }

   /**
    * 获得计划状态的内容。
    *
    * @return 计划状态
    */
   public String getStatueCd(){
      return _statueCd;
   }

   /**
    * 设置计划状态的内容。
    *
    * @param value 计划状态
    */
   public void setStatueCd(String value){
      _statueCd = value;
   }

   /**
    * 获得时间类型的内容。
    *
    * @return 时间类型
    */
   public String getTypeCd(){
      return _typeCd;
   }

   /**
    * 设置时间类型的内容。
    *
    * @param value 时间类型
    */
   public void setTypeCd(String value){
      _typeCd = value;
   }

   /**
    * 获得时间间隔的内容。
    *
    * @return 时间间隔
    */
   public String getInterval(){
      return _interval;
   }

   /**
    * 设置时间间隔的内容。
    *
    * @param value 时间间隔
    */
   public void setInterval(String value){
      _interval = value;
   }

   /**
    * 获得执行标识的内容。
    *
    * @return 执行标识
    */
   public String getDayFlag(){
      return _dayFlag;
   }

   /**
    * 设置执行标识的内容。
    *
    * @param value 执行标识
    */
   public void setDayFlag(String value){
      _dayFlag = value;
   }

   /**
    * 获得执行标识的内容。
    *
    * @return 执行标识
    */
   public String getWeekFlag(){
      return _weekFlag;
   }

   /**
    * 设置执行标识的内容。
    *
    * @param value 执行标识
    */
   public void setWeekFlag(String value){
      _weekFlag = value;
   }

   /**
    * 获得执行标识的内容。
    *
    * @return 执行标识
    */
   public String getMonthFlag(){
      return _monthFlag;
   }

   /**
    * 设置执行标识的内容。
    *
    * @param value 执行标识
    */
   public void setMonthFlag(String value){
      _monthFlag = value;
   }

   /**
    * 获得更新时间的内容。
    *
    * @return 更新时间
    */
   public String getUpdateDate(){
      return _updateDate;
   }

   /**
    * 设置更新时间的内容。
    *
    * @param value 更新时间
    */
   public void setUpdateDate(String value){
      _updateDate = value;
   }

   @Override
   public void loadConfig(FXmlNode config,
                          EXmlConfig type){
      super.loadConfig(config, type);
      if(EXmlConfig.Full == type){
         if(config.contains("name")){
            setName(config.get(PTY_NAME));
         }
         if(config.contains("label")){
            _label.unpack(config.get(PTY_LABEL));
         }
         if(config.contains("note")){
            _note.unpack(config.get(PTY_NOTE));
         }
         if(config.contains("_type")){
            set_type(config.get(PTY__TYPE));
         }
         if(config.contains("is_valid")){
            setIsValid(config.get(PTY_IS_VALID));
         }
         if(config.contains("execute_time")){
            setExecuteTime(config.get(PTY_EXECUTE_TIME));
         }
         if(config.contains("statue_cd")){
            setStatueCd(config.get(PTY_STATUE_CD));
         }
         if(config.contains("type_cd")){
            setTypeCd(config.get(PTY_TYPE_CD));
         }
         if(config.contains("interval")){
            setInterval(config.get(PTY_INTERVAL));
         }
         if(config.contains("day_flag")){
            setDayFlag(config.get(PTY_DAY_FLAG));
         }
         if(config.contains("week_flag")){
            setWeekFlag(config.get(PTY_WEEK_FLAG));
         }
         if(config.contains("month_flag")){
            setMonthFlag(config.get(PTY_MONTH_FLAG));
         }
         if(config.contains("update_date")){
            setUpdateDate(config.get(PTY_UPDATE_DATE));
         }
      }else if(EXmlConfig.Simple == type){
         if(config.contains("name")){
            setName(config.get(PTY_NAME));
         }
         if(config.contains("label")){
            setLabel(config.get(PTY_LABEL));
         }
         if(config.contains("note")){
            setNote(config.get(PTY_NOTE));
         }
         if(config.contains("_type")){
            set_type(config.get(PTY__TYPE));
         }
         if(config.contains("is_valid")){
            setIsValid(config.get(PTY_IS_VALID));
         }
         if(config.contains("execute_time")){
            setExecuteTime(config.get(PTY_EXECUTE_TIME));
         }
         if(config.contains("statue_cd")){
            setStatueCd(config.get(PTY_STATUE_CD));
         }
         if(config.contains("type_cd")){
            setTypeCd(config.get(PTY_TYPE_CD));
         }
         if(config.contains("interval")){
            setInterval(config.get(PTY_INTERVAL));
         }
         if(config.contains("day_flag")){
            setDayFlag(config.get(PTY_DAY_FLAG));
         }
         if(config.contains("week_flag")){
            setWeekFlag(config.get(PTY_WEEK_FLAG));
         }
         if(config.contains("month_flag")){
            setMonthFlag(config.get(PTY_MONTH_FLAG));
         }
         if(config.contains("update_date")){
            setUpdateDate(config.get(PTY_UPDATE_DATE));
         }
      }else if(EXmlConfig.Value == type){
         if(config.contains("name")){
            setName(config.get(PTY_NAME));
         }
         if(config.contains("label")){
            setLabel(config.get(PTY_LABEL));
         }
         if(config.contains("note")){
            setNote(config.get(PTY_NOTE));
         }
         if(config.contains("_type")){
            set_type(config.get(PTY__TYPE));
         }
         if(config.contains("is_valid")){
            setIsValid(config.get(PTY_IS_VALID));
         }
         if(config.contains("execute_time")){
            setExecuteTime(config.get(PTY_EXECUTE_TIME));
         }
         if(config.contains("statue_cd")){
            setStatueCd(config.get(PTY_STATUE_CD));
         }
         if(config.contains("type_cd")){
            setTypeCd(config.get(PTY_TYPE_CD));
         }
         if(config.contains("interval")){
            setInterval(config.get(PTY_INTERVAL));
         }
         if(config.contains("day_flag")){
            setDayFlag(config.get(PTY_DAY_FLAG));
         }
         if(config.contains("week_flag")){
            setWeekFlag(config.get(PTY_WEEK_FLAG));
         }
         if(config.contains("month_flag")){
            setMonthFlag(config.get(PTY_MONTH_FLAG));
         }
         if(config.contains("update_date")){
            setUpdateDate(config.get(PTY_UPDATE_DATE));
         }
      }else if(EXmlConfig.Default == type){
         if(RString.isEmpty(get_type())){
            if(config.contains("_type")){
               set_type(config.get(PTY__TYPE));
            }
         }
         if(RString.isEmpty(getIsValid())){
            if(config.contains("is_valid")){
               setIsValid(config.get(PTY_IS_VALID));
            }
         }
         if(RString.isEmpty(getExecuteTime())){
            if(config.contains("execute_time")){
               setExecuteTime(config.get(PTY_EXECUTE_TIME));
            }
         }
         if(RString.isEmpty(getStatueCd())){
            if(config.contains("statue_cd")){
               setStatueCd(config.get(PTY_STATUE_CD));
            }
         }
         if(RString.isEmpty(getTypeCd())){
            if(config.contains("type_cd")){
               setTypeCd(config.get(PTY_TYPE_CD));
            }
         }
         if(RString.isEmpty(getInterval())){
            if(config.contains("interval")){
               setInterval(config.get(PTY_INTERVAL));
            }
         }
         if(RString.isEmpty(getDayFlag())){
            if(config.contains("day_flag")){
               setDayFlag(config.get(PTY_DAY_FLAG));
            }
         }
         if(RString.isEmpty(getWeekFlag())){
            if(config.contains("week_flag")){
               setWeekFlag(config.get(PTY_WEEK_FLAG));
            }
         }
         if(RString.isEmpty(getMonthFlag())){
            if(config.contains("month_flag")){
               setMonthFlag(config.get(PTY_MONTH_FLAG));
            }
         }
         if(RString.isEmpty(getUpdateDate())){
            if(config.contains("update_date")){
               setUpdateDate(config.get(PTY_UPDATE_DATE));
            }
         }
      }
   }

   @Override
   public void saveConfig(FXmlNode config,
                          EXmlConfig type){
      config.setName(NAME);
      super.saveConfig(config, type);
      if(EXmlConfig.Full == type){
         if(RString.isNotEmpty(getName())){
            config.set(PTY_NAME, getName());
         }
         String label = _label.pack().toString();
         if(RString.isNotEmpty(label)){
            config.set(PTY_LABEL, label);
         }
         String note = _note.pack().toString();
         if(RString.isNotEmpty(note)){
            config.set(PTY_NOTE, note);
         }
         if(RString.isNotEmpty(get_type())){
            config.set(PTY__TYPE, get_type());
         }
         if(RString.isNotEmpty(getIsValid())){
            config.set(PTY_IS_VALID, getIsValid());
         }
         if(RString.isNotEmpty(getExecuteTime())){
            config.set(PTY_EXECUTE_TIME, getExecuteTime());
         }
         if(RString.isNotEmpty(getStatueCd())){
            config.set(PTY_STATUE_CD, getStatueCd());
         }
         if(RString.isNotEmpty(getTypeCd())){
            config.set(PTY_TYPE_CD, getTypeCd());
         }
         if(RString.isNotEmpty(getInterval())){
            config.set(PTY_INTERVAL, getInterval());
         }
         if(RString.isNotEmpty(getDayFlag())){
            config.set(PTY_DAY_FLAG, getDayFlag());
         }
         if(RString.isNotEmpty(getWeekFlag())){
            config.set(PTY_WEEK_FLAG, getWeekFlag());
         }
         if(RString.isNotEmpty(getMonthFlag())){
            config.set(PTY_MONTH_FLAG, getMonthFlag());
         }
         if(RString.isNotEmpty(getUpdateDate())){
            config.set(PTY_UPDATE_DATE, getUpdateDate());
         }
      }else if(EXmlConfig.Simple == type){
         if(RString.isNotEmpty(getName())){
            config.set(PTY_NAME, getName());
         }
         if(RString.isNotEmpty(getLabel())){
            config.set(PTY_LABEL, getLabel());
         }
         if(RString.isNotEmpty(getNote())){
            config.set(PTY_NOTE, getNote());
         }
         if(RString.isNotEmpty(get_type())){
            config.set(PTY__TYPE, get_type());
         }
         if(RString.isNotEmpty(getIsValid())){
            config.set(PTY_IS_VALID, getIsValid());
         }
         if(RString.isNotEmpty(getExecuteTime())){
            config.set(PTY_EXECUTE_TIME, getExecuteTime());
         }
         if(RString.isNotEmpty(getStatueCd())){
            config.set(PTY_STATUE_CD, getStatueCd());
         }
         if(RString.isNotEmpty(getTypeCd())){
            config.set(PTY_TYPE_CD, getTypeCd());
         }
         if(RString.isNotEmpty(getInterval())){
            config.set(PTY_INTERVAL, getInterval());
         }
         if(RString.isNotEmpty(getDayFlag())){
            config.set(PTY_DAY_FLAG, getDayFlag());
         }
         if(RString.isNotEmpty(getWeekFlag())){
            config.set(PTY_WEEK_FLAG, getWeekFlag());
         }
         if(RString.isNotEmpty(getMonthFlag())){
            config.set(PTY_MONTH_FLAG, getMonthFlag());
         }
         if(RString.isNotEmpty(getUpdateDate())){
            config.set(PTY_UPDATE_DATE, getUpdateDate());
         }
      }else if(EXmlConfig.Value == type){
         String sName = getName();
         if(RString.isNotEmpty(sName)){
            config.set(PTY_NAME, sName);
         }
         String sLabel = getLabel();
         if(RString.isNotEmpty(sLabel)){
            config.set(PTY_LABEL, sLabel);
         }
         String sNote = getNote();
         if(RString.isNotEmpty(sNote)){
            config.set(PTY_NOTE, sNote);
         }
         String s_type = get_type();
         if(RString.isNotEmpty(s_type)){
            config.set(PTY__TYPE, s_type);
         }
         String sIsValid = getIsValid();
         if(RString.isNotEmpty(sIsValid)){
            config.set(PTY_IS_VALID, sIsValid);
         }
         String sExecuteTime = getExecuteTime();
         if(RString.isNotEmpty(sExecuteTime)){
            config.set(PTY_EXECUTE_TIME, sExecuteTime);
         }
         String sStatueCd = getStatueCd();
         if(RString.isNotEmpty(sStatueCd)){
            config.set(PTY_STATUE_CD, sStatueCd);
         }
         String sTypeCd = getTypeCd();
         if(RString.isNotEmpty(sTypeCd)){
            config.set(PTY_TYPE_CD, sTypeCd);
         }
         String sInterval = getInterval();
         if(RString.isNotEmpty(sInterval)){
            config.set(PTY_INTERVAL, sInterval);
         }
         String sDayFlag = getDayFlag();
         if(RString.isNotEmpty(sDayFlag)){
            config.set(PTY_DAY_FLAG, sDayFlag);
         }
         String sWeekFlag = getWeekFlag();
         if(RString.isNotEmpty(sWeekFlag)){
            config.set(PTY_WEEK_FLAG, sWeekFlag);
         }
         String sMonthFlag = getMonthFlag();
         if(RString.isNotEmpty(sMonthFlag)){
            config.set(PTY_MONTH_FLAG, sMonthFlag);
         }
         String sUpdateDate = getUpdateDate();
         if(RString.isNotEmpty(sUpdateDate)){
            config.set(PTY_UPDATE_DATE, sUpdateDate);
         }
      }else if(EXmlConfig.Default == type){
         String s_type = get_type();
         if(RString.isNotEmpty(s_type)){
            config.set(PTY__TYPE, s_type);
         }
         String sIsValid = getIsValid();
         if(RString.isNotEmpty(sIsValid)){
            config.set(PTY_IS_VALID, sIsValid);
         }
         String sExecuteTime = getExecuteTime();
         if(RString.isNotEmpty(sExecuteTime)){
            config.set(PTY_EXECUTE_TIME, sExecuteTime);
         }
         String sStatueCd = getStatueCd();
         if(RString.isNotEmpty(sStatueCd)){
            config.set(PTY_STATUE_CD, sStatueCd);
         }
         String sTypeCd = getTypeCd();
         if(RString.isNotEmpty(sTypeCd)){
            config.set(PTY_TYPE_CD, sTypeCd);
         }
         String sInterval = getInterval();
         if(RString.isNotEmpty(sInterval)){
            config.set(PTY_INTERVAL, sInterval);
         }
         String sDayFlag = getDayFlag();
         if(RString.isNotEmpty(sDayFlag)){
            config.set(PTY_DAY_FLAG, sDayFlag);
         }
         String sWeekFlag = getWeekFlag();
         if(RString.isNotEmpty(sWeekFlag)){
            config.set(PTY_WEEK_FLAG, sWeekFlag);
         }
         String sMonthFlag = getMonthFlag();
         if(RString.isNotEmpty(sMonthFlag)){
            config.set(PTY_MONTH_FLAG, sMonthFlag);
         }
         String sUpdateDate = getUpdateDate();
         if(RString.isNotEmpty(sUpdateDate)){
            config.set(PTY_UPDATE_DATE, sUpdateDate);
         }
      }
   }

   @Override
   public String innerGet(String name){
      if(RString.isEmpty(name)){
         return null;
      }else if(PTY_NAME.equalsIgnoreCase(name)){
         return getName();
      }else if(PTY_LABEL.equalsIgnoreCase(name)){
         return getLabel();
      }else if(PTY_NOTE.equalsIgnoreCase(name)){
         return getNote();
      }else if(PTY__TYPE.equalsIgnoreCase(name)){
         return get_type();
      }else if(PTY_IS_VALID.equalsIgnoreCase(name)){
         return getIsValid();
      }else if(PTY_EXECUTE_TIME.equalsIgnoreCase(name)){
         return getExecuteTime();
      }else if(PTY_STATUE_CD.equalsIgnoreCase(name)){
         return getStatueCd();
      }else if(PTY_TYPE_CD.equalsIgnoreCase(name)){
         return getTypeCd();
      }else if(PTY_INTERVAL.equalsIgnoreCase(name)){
         return getInterval();
      }else if(PTY_DAY_FLAG.equalsIgnoreCase(name)){
         return getDayFlag();
      }else if(PTY_WEEK_FLAG.equalsIgnoreCase(name)){
         return getWeekFlag();
      }else if(PTY_MONTH_FLAG.equalsIgnoreCase(name)){
         return getMonthFlag();
      }else if(PTY_UPDATE_DATE.equalsIgnoreCase(name)){
         return getUpdateDate();
      }
      return null;
   }

   @Override
   public void innerSet(String name,
                        String value){
      if(RString.isEmpty(name)){
         return;
      }else if(PTY_NAME.equalsIgnoreCase(name)){
         setName(value);
      }else if(PTY_LABEL.equalsIgnoreCase(name)){
         setLabel(value);
      }else if(PTY_NOTE.equalsIgnoreCase(name)){
         setNote(value);
      }else if(PTY__TYPE.equalsIgnoreCase(name)){
         set_type(value);
      }else if(PTY_IS_VALID.equalsIgnoreCase(name)){
         setIsValid(value);
      }else if(PTY_EXECUTE_TIME.equalsIgnoreCase(name)){
         setExecuteTime(value);
      }else if(PTY_STATUE_CD.equalsIgnoreCase(name)){
         setStatueCd(value);
      }else if(PTY_TYPE_CD.equalsIgnoreCase(name)){
         setTypeCd(value);
      }else if(PTY_INTERVAL.equalsIgnoreCase(name)){
         setInterval(value);
      }else if(PTY_DAY_FLAG.equalsIgnoreCase(name)){
         setDayFlag(value);
      }else if(PTY_WEEK_FLAG.equalsIgnoreCase(name)){
         setWeekFlag(value);
      }else if(PTY_MONTH_FLAG.equalsIgnoreCase(name)){
         setMonthFlag(value);
      }else if(PTY_UPDATE_DATE.equalsIgnoreCase(name)){
         setUpdateDate(value);
      }
   }
}
