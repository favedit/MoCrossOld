/*
 * @(#)FLogicEvent.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.event;

import org.mo.com.collections.FObjectDictionary;
import org.mo.com.collections.FRow;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.IAttributes;
import org.mo.com.lang.REnum;
import org.mo.com.lang.RString;
import org.mo.com.lang.type.TDate;
import org.mo.com.lang.type.TInteger;

/**
 * <T>事件结构类</T>
 * <P>该类定义了事件对象的主体结构，并定义了各个字段的存取函数</P>
 * 
 * @author HYKUN
 * @version 1.00 - 2008/10/27
 */
public class FLogicEvent
      implements
         ILogicEvent
{
   /**
    * <T>事件开始日期。</T>
    */
   public static String PTY_BEGIN_DATE = "begin_date";

   /**
    * <T>事件创建日期。</T>
    */
   public static String PTY_CREATE_DATE = "create_date";

   /**
    * <T>事件创建者标识。</T>
    */
   public static String PTY_CREATE_USER_ID = "createUserId";

   /**
    * <T>事件创建者名称。</T>
    */
   public static String PTY_CREATE_USER_NAME = "create_user_Name";

   /**
    * <T>事件结束日期。</T>
    */
   public static String PTY_END_DATE = "end_date";

   /**
    * <T>事件设置信息。</T>
    */
   public static String PTY_EVENT_CONFIG = "event_config";

   /**
    * <T>事件组设置信息。</T>
    */
   public static String PTY_GROUP_CONFIG = "group_config";

   /**
    * <T>事件组标识。</T>
    */
   public static String PTY_GROUP_ID = "group_id";

   /**
    * <T>事件组名称。</T>
    */
   public static String PTY_GROUP_NAME = "group_name";

   /**
    * <T>事件标识。</T>
    */
   public static String PTY_OUID = "ouid";

   /**
    * <T>事件优先级。</T>
    */
   public static String PTY_PRIORITY_CD = "priority_cd";

   /**
    * <T>事件处理类型。</T>
    */
   public static String PTY_PROCESS_CD = "process_cd";

   /**
    * <T>事件处理调用。</T>
    */
   public static String PTY_PROCESS_INVOKE = "process_invoke";

   /**
    * <T>事件处理百分比。</T>
    */
   public static String PTY_PROCESS_PERCENT = "process_percent";

   /**
    * <T>事件处理者标识。</T>
    */
   public static String PTY_PROCESS_USER_ID = "process_user_id";

   /**
    * <T>事件处理者名称。</T>
    */
   public static String PTY_PROCESS_USER_NAME = "process_user_name";

   /**
    * <T>事件类接收者标识。</T>
    */
   public static String PTY_RECEIVE_USER_ID = "receive_user_id";

   /**
    * <T>事件类接收者名称。</T>
    */
   public static String PTY_RECEIVE_USER_NAME = "receive_user_name";

   /**
    * <T>事件状态。</T>
    */
   public static String PTY_STATUS_TYPE = "status_cd";

   /**
    * <T>事件类型设置。</T>
    */
   public static String PTY_TYPE_CONFIG = "type_config";

   /**
    * <T>事件类型标识。</T>
    */
   public static String PTY_TYPE_ID = "type_id";

   /**
    * <T>事件类型名称。</T>
    */
   public static String PTY_TYPE_NAME = "type_name";

   // 开始日期
   private TDate _beginDate = new TDate();

   // 创建日期
   private TDate _createDate = new TDate();

   // 创建者标识
   private TInteger _createUserId = new TInteger();

   // 创建者名称
   private String _createUserName;

   // 结束日期
   private TDate _endDate = new TDate();

   // 事件设置列表
   private IAttributes _eventConfig;

   private FObjectDictionary _eventParameters;

   // 事件组设置列表
   private IAttributes _groupConfig;

   // 组标识
   private TInteger _groupId = new TInteger();

   // 组名称
   private String _groupName;

   // 事件标识
   private TInteger _id = new TInteger();

   // 参数列表
   private IAttributes _parameters;

   // 优先级
   private ELogicEventPriority _priority;

   // 处理调用
   private String _processInvoke;

   // 处理百分比
   private TInteger _processPercent = new TInteger();

   // 处理类型
   private ELogicEventProcess _processType;

   // 处理者标识
   private TInteger _processUserId = new TInteger();

   // 处理者名称
   private String _processUserName;

   // 接收者标识
   private TInteger _receiveUserId = new TInteger();

   // 接收者名称
   private String _receiveUserName;

   // 结果列表 
   private FLogicEventResults _results;

   // 事件状态
   private ELogicEventStatus _status;

   // 类型设置列表
   private IAttributes _typeConfig;

   // 类型标识
   private TInteger _typeId = new TInteger();

   // 类型名称
   private String _typeName;

   /* (non-Javadoc)
    * @see org.mo.logic.event.ILogicEvent#addResult(org.mo.logic.event.ILogicEventResult)
    */
   @Override
   public void addResult(ILogicEventResult result){
      results().push(result);
   }

   /* (non-Javadoc)
    * @see org.mo.logic.event.ILogicEvent#beginDate()
    */
   @Override
   public TDate beginDate(){
      return _beginDate;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.event.ILogicEvent#compare(org.mo.logic.event.FLogicEvent, java.lang.Object[])
    */
   @Override
   public int compare(FLogicEvent item,
                      Object... params){
      if(_priority.toInteger() > item.priority().toInteger()){
         return 1;
      }else if(_priority == item._priority && _createDate.isBefore(item._createDate)){
         return 1;
      }
      return -1;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.event.ILogicEvent#createDate()
    */
   @Override
   public TDate createDate(){
      return _createDate;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.event.ILogicEvent#createUserId()
    */
   @Override
   public TInteger createUserId(){
      return _createUserId;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.event.ILogicEvent#createUserName()
    */
   @Override
   public String createUserName(){
      return _createUserName;
   }

   @Override
   public TDate endDate(){
      return _endDate;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.event.ILogicEvent#eventConfig()
    */
   public IAttributes eventConfig(){
      if(null == _eventConfig){
         _eventConfig = new FAttributes();
      }
      return _eventConfig;
   }

   public FObjectDictionary eventParameters(){
      if(null == _eventParameters){
         _eventParameters = new FObjectDictionary();
      }
      return _eventParameters;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.event.ILogicEvent#groupConfig()
    */
   @Override
   public IAttributes groupConfig(){
      if(null == _groupConfig){
         _groupConfig = new FAttributes();
      }
      return _groupConfig;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.event.ILogicEvent#groupId()
    */
   @Override
   public TInteger groupId(){
      return _groupId;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.event.ILogicEvent#groupName()
    */
   @Override
   public String groupName(){
      return _groupName;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.event.ILogicEvent#hasResult()
    */
   @Override
   public boolean hasResult(){
      return (null != _results) ? !_results.isEmpty() : false;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.event.ILogicEvent#id()
    */
   @Override
   public TInteger id(){
      return _id;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.event.ILogicEvent#loadConfig(org.mo.com.lang.FRow)
    */
   @Override
   public void loadConfig(FRow row){
      // 获取基本信息
      _id.set(row.getInteger(PTY_OUID));
      _groupId.set(row.getInteger(PTY_GROUP_ID));
      setGroupName(row.get(PTY_GROUP_NAME));
      _typeId.set(row.getInteger(PTY_TYPE_ID));
      setTypeName((PTY_TYPE_NAME));
      setStatus(row.get(PTY_STATUS_TYPE));
      setPriority(row.get(PTY_PRIORITY_CD));
      setProcessType(row.get(PTY_PROCESS_CD));
      setProcessInvoke(row.get(PTY_PROCESS_INVOKE));
      _processPercent.set(row.getInteger(PTY_PROCESS_PERCENT));
      // 获取用户信息
      _processUserId.set(row.getInteger(PTY_PROCESS_USER_ID));
      setProcessUserName(row.get(PTY_PROCESS_USER_NAME));
      _receiveUserId.set(row.getInteger(PTY_RECEIVE_USER_ID));
      setReceiveUserName(row.get(PTY_RECEIVE_USER_NAME));
      _createUserId.set(row.getInteger(PTY_CREATE_USER_ID));
      setCreateUserName(row.get(PTY_CREATE_USER_NAME));
      // 获取时间信息
      _beginDate.set(row.getDate(PTY_BEGIN_DATE));
      _endDate.set(row.getDate(PTY_END_DATE));
      _createDate.set(row.getDate(PTY_CREATE_DATE));
      // 读取事件组的设置信息
      String groupConfig = row.get(PTY_GROUP_CONFIG);
      if(RString.isNotEmpty(groupConfig)){
         //groupConfig().unpack(groupConfig);
         parameters().append(groupConfig());
      }
      // 读取事件类型的设置信息
      String typeConfig = row.get(PTY_TYPE_CONFIG);
      if(RString.isNotEmpty(typeConfig)){
         //typeConfig().unpack(typeConfig);
         parameters().append(typeConfig());
      }
      // 读取事件的设置信息
      String eventConfig = row.get(PTY_EVENT_CONFIG);
      if(RString.isNotEmpty(eventConfig)){
         //eventConfig().unpack(eventConfig);
         parameters().append(eventConfig());
      }
   }

   /* (non-Javadoc)
    * @see org.mo.logic.event.ILogicEvent#parameter(java.lang.String)
    */
   @Override
   public String parameter(String name){
      return parameters().get(name);
   }

   /* (non-Javadoc)
    * @see org.mo.logic.event.ILogicEvent#parameters()
    */
   @Override
   public IAttributes parameters(){
      if(null == _parameters){
         _parameters = new FAttributes();
      }
      return _parameters;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.event.ILogicEvent#priority()
    */
   @Override
   public ELogicEventPriority priority(){
      return _priority;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.event.ILogicEvent#processInvoke()
    */
   @Override
   public String processInvoke(){
      return _processInvoke;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.event.ILogicEvent#processPercent()
    */
   @Override
   public TInteger processPercent(){
      return _processPercent;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.event.ILogicEvent#processType()
    */
   @Override
   public ELogicEventProcess processType(){
      return _processType;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.event.ILogicEvent#processUserId()
    */
   @Override
   public TInteger processUserId(){
      return _processUserId;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.event.ILogicEvent#processUserName()
    */
   @Override
   public String processUserName(){
      return _processUserName;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.event.ILogicEvent#receiveUserId()
    */
   @Override
   public TInteger receiveUserId(){
      return _receiveUserId;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.event.ILogicEvent#receiveUserName()
    */
   @Override
   public String receiveUserName(){
      return _receiveUserName;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.event.ILogicEvent#results()
    */
   @Override
   public FLogicEventResults results(){
      if(null == _results){
         _results = new FLogicEventResults();
      }
      return _results;
   }

   /** 
    * <T>设置事件的开始日期。</T>
    * 
    * @param beginDate 开始日期
    */
   public void setBeginDate(TDate beginDate){
      _beginDate = beginDate;
   }

   /** 
    * <T>设置事件的创建日期。</T>
    * 
    * @param createDate 创建日期
    */
   public void setCreateDate(TDate createDate){
      _endDate = createDate;
   }

   /** 
    * <T>设置事件的创建者标识。</T>
    * 
    * @param createUserId 创建者标识
    */
   public void setCreateUserId(TInteger createUserId){
      _typeId = createUserId;
   }

   /** 
    * <T>设置事件的创建者名称。</T>
    * 
    * @param createUserName 创建者名称
    */
   public void setCreateUserName(String createUserName){
      _createUserName = createUserName;
   }

   /** 
    * <T>设置事件的结束日期。</T>
    * 
    * @param endDate 结束日期
    */
   public void setEndDate(TDate endDate){
      _endDate = endDate;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.event.ILogicEvent#setError(org.mo.logic.event.ILogicEventResult)
    */
   @Override
   public void setError(ILogicEventResult error){
      results().clear();
      results().push(error);
   }

   /**
    * <T>设置事件的组标识。</T>
    * 
    * @param groupId 组标识
    */
   public void setGroupId(TInteger groupId){
      _groupId = groupId;
   }

   /**
    * <T>设置事件的组名称。</T>
    * 
    * @param groupName 组名称
    */
   public void setGroupName(String groupName){
      _groupName = groupName;
   }

   /**
    * <T>设置事件的标识</T>
    * 
    * @param id 事件标识
    */
   public void setId(TInteger id){
      _id = id;
   }

   /**
    * <T>设置事件的优先级。</T>
    * 
    * @param priorityType 优先级
    */
   public void setPriority(String priorityType){
      _priority = REnum.parseStart(ELogicEventPriority.class, priorityType);
   }

   /**
    * <T>设置事件的处理调用。</T>
    * 
    * @param processInvoke 处理调用
    */
   public void setProcessInvoke(String processInvoke){
      _processInvoke = processInvoke;
   }

   /**
    * <T>设置事件的处理百分比。</T>
    * 
    * @param processPercent 处理百分比
    */
   public void setProcessPercent(TInteger processPercent){
      _processPercent = processPercent;
   }

   /**
    * <T>设置事件的处理类型。</T>
    * 
    * @param processType 处理类型
    */
   public void setProcessType(String processType){
      _processType = REnum.parseStart(ELogicEventProcess.class, processType);
   }

   /**
    * <T>设置事件的处理者标识。</T>
    * 
    * @param processUserId 处理者标识
    */
   public void setProcessUserId(TInteger processUserId){
      _typeId = processUserId;
   }

   /**
    * <T>设置事件的处理者名称。</T>
    * 
    * @param processUserName 处理者名称
    */
   public void setProcessUserName(String processUserName){
      _processUserName = processUserName;
   }

   /**
    * <T>设置事件的接收者标识。</T>
    * 
    * @param receiveUserId 接收者标识
    */
   public void setReceiveUserId(TInteger receiveUserId){
      _receiveUserId = receiveUserId;
   }

   /**
    * <T>设置事件的接收者名称。</T>
    * 
    * @param receiveUserName 接收者名称
    */
   public void setReceiveUserName(String receiveUserName){
      _receiveUserName = receiveUserName;
   }

   /**
    * <T>设置事件的状态。</T>
    * 
    * @param statusType 事件状态
    */
   public void setStatus(String statusType){
      _status = REnum.parse(ELogicEventStatus.class, statusType);
   }

   /* (non-Javadoc)
    * @see org.mo.logic.event.ILogicEvent#setTimeout(org.mo.logic.event.ILogicEventResult)
    */
   @Override
   public void setTimeout(ILogicEventResult timeout){
      results().clear();
      results().push(timeout);
   }

   /**
    * <T>设置事件的类型标识。</T>
    * 
    * @param typeId 类型标识
    */
   public void setTypeId(TInteger typeId){
      _typeId = typeId;
   }

   /**
    * <T>设置事件的类型名称。</T>
    * 
    * @param typeName 类型名称
    */
   public void setTypeName(String typeName){
      _typeName = typeName;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.event.ILogicEvent#status()
    */
   @Override
   public ELogicEventStatus status(){
      return _status;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.event.ILogicEvent#typeConfig()
    */
   @Override
   public IAttributes typeConfig(){
      if(null == _typeConfig){
         _typeConfig = new FAttributes();
      }
      return _typeConfig;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.event.ILogicEvent#typeId()
    */
   @Override
   public TInteger typeId(){
      return _typeId;
   }

   /* (non-Javadoc)
    * @see org.mo.logic.event.ILogicEvent#typeName()
    */
   @Override
   public String typeName(){
      return _typeName;
   }
}
