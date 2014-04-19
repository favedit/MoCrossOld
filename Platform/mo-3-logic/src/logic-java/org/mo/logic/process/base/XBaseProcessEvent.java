package org.mo.logic.process.base;

import org.mo.com.lang.FMultiString;
import org.mo.com.lang.RString;
import org.mo.com.lang.face.AName;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlObject;
import org.mo.com.xml.IXmlObject;
import org.mo.logic.process.common.XObjectFace;

//============================================================
// <T>事件对象的XML节点基类。</T>
//============================================================
public abstract class XBaseProcessEvent
      extends FXmlObject
      implements
         XObjectFace
{
   // 名称定义
   public static final String NAME = "ProcessEvent";

   //============================================================
   // <T>获得名称定义。</T>
   //
   // @return 名称定义
   //============================================================
   public String name(){
      return NAME;
   }

   //============================================================
   // <T>判断是否指定名称。</T>
   //
   // @param name 名称
   // @return 是否指定
   //============================================================
   public static boolean isName(String name){
      return NAME.equals(name);
   }

   //============================================================
   // <T>判断是否指定实例。</T>
   //
   // @param xobject 对象
   // @return 是否指定
   //============================================================
   public static boolean isInstance(IXmlObject xobject){
      return NAME.equals(xobject.name());
   }

   // 名称的名称定义
   public static final String PTY_NAME = "name";

   // 标签的名称定义
   public static final String PTY_LABEL = "label";

   // 注释的名称定义
   public static final String PTY_NOTE = "note";

   // 类型的名称定义
   public static final String PTY__TYPE = "_type";

   // 有效性的名称定义
   public static final String PTY_IS_VALID = "is_valid";

   // 事件分组名称的名称定义
   public static final String PTY_EVENT_GROUP_NAME = "event_group_name";

   // 流程类型名称的名称定义
   public static final String PTY_EVENT_TYPE_NAME = "event_type_name";

   // 事件优先级的名称定义
   public static final String PTY_EVENT_PRIORITY_CD = "event_priority_cd";

   // 事件执行类型的名称定义
   public static final String PTY_EVENT_EXECUTE = "event_execute";

   // 条件名称的名称定义
   public static final String PTY_CONDITION_NAME = "condition_name";

   // 流程类型名称的名称定义
   public static final String PTY_TYPE_NAME = "type_name";

   // 检查结果的名称定义
   public static final String PTY_PROCESS_RESULT = "process_result";

   // 处理顺序的名称定义
   public static final String PTY_PROCESS_ORDER = "process_order";

   // 名称的定义
   @AName("name")
   protected String _name;

   // 标签的定义
   @AName("label")
   protected FMultiString _label = new FMultiString();

   // 注释的定义
   @AName("note")
   protected FMultiString _note = new FMultiString();

   // 类型的定义
   @AName("_type")
   protected String __type;

   // 有效性的定义
   @AName("is_valid")
   protected String _isValid;

   // 事件分组名称的定义
   @AName("event_group_name")
   protected String _eventGroupName;

   // 流程类型名称的定义
   @AName("event_type_name")
   protected String _eventTypeName;

   // 事件优先级的定义
   @AName("event_priority_cd")
   protected String _eventPriorityCd;

   // 事件执行类型的定义
   @AName("event_execute")
   protected String _eventExecute;

   // 条件名称的定义
   @AName("condition_name")
   protected String _conditionName;

   // 流程类型名称的定义
   @AName("type_name")
   protected String _typeName;

   // 检查结果的定义
   @AName("process_result")
   protected String _processResult;

   // 处理顺序的定义
   @AName("process_order")
   protected String _processOrder;

   //============================================================
   // <T>获得名称的内容。</T>
   //
   // @return 名称
   //============================================================
   public String getName(){
      return _name;
   }

   //============================================================
   // <T>设置名称的内容。</T>
   //
   // @param value 名称
   //============================================================
   public void setName(String value){
      _name = value;
   }

   //============================================================
   // <T>获得标签的内容。</T>
   //
   // @return 标签
   //============================================================
   public String getLabel(){
      return _label.get();
   }

   //============================================================
   // <T>设置标签的内容。</T>
   //
   // @param value 标签
   //============================================================
   public void setLabel(String value){
      _label.set(value);
   }

   //============================================================
   // <T>获得注释的内容。</T>
   //
   // @return 注释
   //============================================================
   public String getNote(){
      return _note.get();
   }

   //============================================================
   // <T>设置注释的内容。</T>
   //
   // @param value 注释
   //============================================================
   public void setNote(String value){
      _note.set(value);
   }

   //============================================================
   // <T>获得类型的内容。</T>
   //
   // @return 类型
   //============================================================
   public String get_type(){
      return __type;
   }

   //============================================================
   // <T>设置类型的内容。</T>
   //
   // @param value 类型
   //============================================================
   public void set_type(String value){
      __type = value;
   }

   //============================================================
   // <T>获得有效性的内容。</T>
   //
   // @return 有效性
   //============================================================
   public String getIsValid(){
      return _isValid;
   }

   //============================================================
   // <T>设置有效性的内容。</T>
   //
   // @param value 有效性
   //============================================================
   public void setIsValid(String value){
      _isValid = value;
   }

   //============================================================
   // <T>获得事件分组名称的内容。</T>
   //
   // @return 事件分组名称
   //============================================================
   public String getEventGroupName(){
      return _eventGroupName;
   }

   //============================================================
   // <T>设置事件分组名称的内容。</T>
   //
   // @param value 事件分组名称
   //============================================================
   public void setEventGroupName(String value){
      _eventGroupName = value;
   }

   //============================================================
   // <T>获得流程类型名称的内容。</T>
   //
   // @return 流程类型名称
   //============================================================
   public String getEventTypeName(){
      return _eventTypeName;
   }

   //============================================================
   // <T>设置流程类型名称的内容。</T>
   //
   // @param value 流程类型名称
   //============================================================
   public void setEventTypeName(String value){
      _eventTypeName = value;
   }

   //============================================================
   // <T>获得事件优先级的内容。</T>
   //
   // @return 事件优先级
   //============================================================
   public String getEventPriorityCd(){
      return _eventPriorityCd;
   }

   //============================================================
   // <T>设置事件优先级的内容。</T>
   //
   // @param value 事件优先级
   //============================================================
   public void setEventPriorityCd(String value){
      _eventPriorityCd = value;
   }

   //============================================================
   // <T>获得事件执行类型的内容。</T>
   //
   // @return 事件执行类型
   //============================================================
   public String getEventExecute(){
      return _eventExecute;
   }

   //============================================================
   // <T>设置事件执行类型的内容。</T>
   //
   // @param value 事件执行类型
   //============================================================
   public void setEventExecute(String value){
      _eventExecute = value;
   }

   //============================================================
   // <T>获得条件名称的内容。</T>
   //
   // @return 条件名称
   //============================================================
   public String getConditionName(){
      return _conditionName;
   }

   //============================================================
   // <T>设置条件名称的内容。</T>
   //
   // @param value 条件名称
   //============================================================
   public void setConditionName(String value){
      _conditionName = value;
   }

   //============================================================
   // <T>获得流程类型名称的内容。</T>
   //
   // @return 流程类型名称
   //============================================================
   public String getTypeName(){
      return _typeName;
   }

   //============================================================
   // <T>设置流程类型名称的内容。</T>
   //
   // @param value 流程类型名称
   //============================================================
   public void setTypeName(String value){
      _typeName = value;
   }

   //============================================================
   // <T>获得检查结果的内容。</T>
   //
   // @return 检查结果
   //============================================================
   public String getProcessResult(){
      return _processResult;
   }

   //============================================================
   // <T>设置检查结果的内容。</T>
   //
   // @param value 检查结果
   //============================================================
   public void setProcessResult(String value){
      _processResult = value;
   }

   //============================================================
   // <T>获得处理顺序的内容。</T>
   //
   // @return 处理顺序
   //============================================================
   public String getProcessOrder(){
      return _processOrder;
   }

   //============================================================
   // <T>设置处理顺序的内容。</T>
   //
   // @param value 处理顺序
   //============================================================
   public void setProcessOrder(String value){
      _processOrder = value;
   }

   //============================================================
   // <T>内部获得内容置信息。</T>
   //
   // @param name 名称
   // @return 内容
   //============================================================
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
      }else if(PTY_EVENT_GROUP_NAME.equalsIgnoreCase(name)){
         return getEventGroupName();
      }else if(PTY_EVENT_TYPE_NAME.equalsIgnoreCase(name)){
         return getEventTypeName();
      }else if(PTY_EVENT_PRIORITY_CD.equalsIgnoreCase(name)){
         return getEventPriorityCd();
      }else if(PTY_EVENT_EXECUTE.equalsIgnoreCase(name)){
         return getEventExecute();
      }else if(PTY_CONDITION_NAME.equalsIgnoreCase(name)){
         return getConditionName();
      }else if(PTY_TYPE_NAME.equalsIgnoreCase(name)){
         return getTypeName();
      }else if(PTY_PROCESS_RESULT.equalsIgnoreCase(name)){
         return getProcessResult();
      }else if(PTY_PROCESS_ORDER.equalsIgnoreCase(name)){
         return getProcessOrder();
      }
      return null;
   }

   //============================================================
   // <T>内部设置内容置信息。</T>
   //
   // @param name 名称
   // @param value 内容
   //============================================================
   public void innerSet(String name, String value){
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
      }else if(PTY_EVENT_GROUP_NAME.equalsIgnoreCase(name)){
         setEventGroupName(value);
      }else if(PTY_EVENT_TYPE_NAME.equalsIgnoreCase(name)){
         setEventTypeName(value);
      }else if(PTY_EVENT_PRIORITY_CD.equalsIgnoreCase(name)){
         setEventPriorityCd(value);
      }else if(PTY_EVENT_EXECUTE.equalsIgnoreCase(name)){
         setEventExecute(value);
      }else if(PTY_CONDITION_NAME.equalsIgnoreCase(name)){
         setConditionName(value);
      }else if(PTY_TYPE_NAME.equalsIgnoreCase(name)){
         setTypeName(value);
      }else if(PTY_PROCESS_RESULT.equalsIgnoreCase(name)){
         setProcessResult(value);
      }else if(PTY_PROCESS_ORDER.equalsIgnoreCase(name)){
         setProcessOrder(value);
      }
   }

   //============================================================
   // <T>加载设置信息。</T>
   //
   // @param config 设置信息
   // @param type 类型
   //============================================================
   public void loadConfig(FXmlNode config, EXmlConfig type){
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
         if(config.contains("event_group_name")){
            setEventGroupName(config.get(PTY_EVENT_GROUP_NAME));
         }
         if(config.contains("event_type_name")){
            setEventTypeName(config.get(PTY_EVENT_TYPE_NAME));
         }
         if(config.contains("event_priority_cd")){
            setEventPriorityCd(config.get(PTY_EVENT_PRIORITY_CD));
         }
         if(config.contains("event_execute")){
            setEventExecute(config.get(PTY_EVENT_EXECUTE));
         }
         if(config.contains("condition_name")){
            setConditionName(config.get(PTY_CONDITION_NAME));
         }
         if(config.contains("type_name")){
            setTypeName(config.get(PTY_TYPE_NAME));
         }
         if(config.contains("process_result")){
            setProcessResult(config.get(PTY_PROCESS_RESULT));
         }
         if(config.contains("process_order")){
            setProcessOrder(config.get(PTY_PROCESS_ORDER));
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
         if(config.contains("event_group_name")){
            setEventGroupName(config.get(PTY_EVENT_GROUP_NAME));
         }
         if(config.contains("event_type_name")){
            setEventTypeName(config.get(PTY_EVENT_TYPE_NAME));
         }
         if(config.contains("event_priority_cd")){
            setEventPriorityCd(config.get(PTY_EVENT_PRIORITY_CD));
         }
         if(config.contains("event_execute")){
            setEventExecute(config.get(PTY_EVENT_EXECUTE));
         }
         if(config.contains("condition_name")){
            setConditionName(config.get(PTY_CONDITION_NAME));
         }
         if(config.contains("type_name")){
            setTypeName(config.get(PTY_TYPE_NAME));
         }
         if(config.contains("process_result")){
            setProcessResult(config.get(PTY_PROCESS_RESULT));
         }
         if(config.contains("process_order")){
            setProcessOrder(config.get(PTY_PROCESS_ORDER));
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
         if(config.contains("event_group_name")){
            setEventGroupName(config.get(PTY_EVENT_GROUP_NAME));
         }
         if(config.contains("event_type_name")){
            setEventTypeName(config.get(PTY_EVENT_TYPE_NAME));
         }
         if(config.contains("event_priority_cd")){
            setEventPriorityCd(config.get(PTY_EVENT_PRIORITY_CD));
         }
         if(config.contains("event_execute")){
            setEventExecute(config.get(PTY_EVENT_EXECUTE));
         }
         if(config.contains("condition_name")){
            setConditionName(config.get(PTY_CONDITION_NAME));
         }
         if(config.contains("type_name")){
            setTypeName(config.get(PTY_TYPE_NAME));
         }
         if(config.contains("process_result")){
            setProcessResult(config.get(PTY_PROCESS_RESULT));
         }
         if(config.contains("process_order")){
            setProcessOrder(config.get(PTY_PROCESS_ORDER));
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
         if(RString.isEmpty(getEventGroupName())){
            if(config.contains("event_group_name")){
               setEventGroupName(config.get(PTY_EVENT_GROUP_NAME));
            }
         }
         if(RString.isEmpty(getEventTypeName())){
            if(config.contains("event_type_name")){
               setEventTypeName(config.get(PTY_EVENT_TYPE_NAME));
            }
         }
         if(RString.isEmpty(getEventPriorityCd())){
            if(config.contains("event_priority_cd")){
               setEventPriorityCd(config.get(PTY_EVENT_PRIORITY_CD));
            }
         }
         if(RString.isEmpty(getEventExecute())){
            if(config.contains("event_execute")){
               setEventExecute(config.get(PTY_EVENT_EXECUTE));
            }
         }
         if(RString.isEmpty(getConditionName())){
            if(config.contains("condition_name")){
               setConditionName(config.get(PTY_CONDITION_NAME));
            }
         }
         if(RString.isEmpty(getTypeName())){
            if(config.contains("type_name")){
               setTypeName(config.get(PTY_TYPE_NAME));
            }
         }
         if(RString.isEmpty(getProcessResult())){
            if(config.contains("process_result")){
               setProcessResult(config.get(PTY_PROCESS_RESULT));
            }
         }
         if(RString.isEmpty(getProcessOrder())){
            if(config.contains("process_order")){
               setProcessOrder(config.get(PTY_PROCESS_ORDER));
            }
         }
      }
   }

   //============================================================
   // <T>保存设置信息。</T>
   //
   // @param config 设置信息
   // @param type 类型
   //============================================================
   public void saveConfig(FXmlNode config, EXmlConfig type){
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
         if(RString.isNotEmpty(getEventGroupName())){
            config.set(PTY_EVENT_GROUP_NAME, getEventGroupName());
         }
         if(RString.isNotEmpty(getEventTypeName())){
            config.set(PTY_EVENT_TYPE_NAME, getEventTypeName());
         }
         if(RString.isNotEmpty(getEventPriorityCd())){
            config.set(PTY_EVENT_PRIORITY_CD, getEventPriorityCd());
         }
         if(RString.isNotEmpty(getEventExecute())){
            config.set(PTY_EVENT_EXECUTE, getEventExecute());
         }
         if(RString.isNotEmpty(getConditionName())){
            config.set(PTY_CONDITION_NAME, getConditionName());
         }
         if(RString.isNotEmpty(getTypeName())){
            config.set(PTY_TYPE_NAME, getTypeName());
         }
         if(RString.isNotEmpty(getProcessResult())){
            config.set(PTY_PROCESS_RESULT, getProcessResult());
         }
         if(RString.isNotEmpty(getProcessOrder())){
            config.set(PTY_PROCESS_ORDER, getProcessOrder());
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
         if(RString.isNotEmpty(getEventGroupName())){
            config.set(PTY_EVENT_GROUP_NAME, getEventGroupName());
         }
         if(RString.isNotEmpty(getEventTypeName())){
            config.set(PTY_EVENT_TYPE_NAME, getEventTypeName());
         }
         if(RString.isNotEmpty(getEventPriorityCd())){
            config.set(PTY_EVENT_PRIORITY_CD, getEventPriorityCd());
         }
         if(RString.isNotEmpty(getEventExecute())){
            config.set(PTY_EVENT_EXECUTE, getEventExecute());
         }
         if(RString.isNotEmpty(getConditionName())){
            config.set(PTY_CONDITION_NAME, getConditionName());
         }
         if(RString.isNotEmpty(getTypeName())){
            config.set(PTY_TYPE_NAME, getTypeName());
         }
         if(RString.isNotEmpty(getProcessResult())){
            config.set(PTY_PROCESS_RESULT, getProcessResult());
         }
         if(RString.isNotEmpty(getProcessOrder())){
            config.set(PTY_PROCESS_ORDER, getProcessOrder());
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
         String sEventGroupName = getEventGroupName();
         if(RString.isNotEmpty(sEventGroupName)){
            config.set(PTY_EVENT_GROUP_NAME, sEventGroupName);
         }
         String sEventTypeName = getEventTypeName();
         if(RString.isNotEmpty(sEventTypeName)){
            config.set(PTY_EVENT_TYPE_NAME, sEventTypeName);
         }
         String sEventPriorityCd = getEventPriorityCd();
         if(RString.isNotEmpty(sEventPriorityCd)){
            config.set(PTY_EVENT_PRIORITY_CD, sEventPriorityCd);
         }
         String sEventExecute = getEventExecute();
         if(RString.isNotEmpty(sEventExecute)){
            config.set(PTY_EVENT_EXECUTE, sEventExecute);
         }
         String sConditionName = getConditionName();
         if(RString.isNotEmpty(sConditionName)){
            config.set(PTY_CONDITION_NAME, sConditionName);
         }
         String sTypeName = getTypeName();
         if(RString.isNotEmpty(sTypeName)){
            config.set(PTY_TYPE_NAME, sTypeName);
         }
         String sProcessResult = getProcessResult();
         if(RString.isNotEmpty(sProcessResult)){
            config.set(PTY_PROCESS_RESULT, sProcessResult);
         }
         String sProcessOrder = getProcessOrder();
         if(RString.isNotEmpty(sProcessOrder)){
            config.set(PTY_PROCESS_ORDER, sProcessOrder);
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
         String sEventGroupName = getEventGroupName();
         if(RString.isNotEmpty(sEventGroupName)){
            config.set(PTY_EVENT_GROUP_NAME, sEventGroupName);
         }
         String sEventTypeName = getEventTypeName();
         if(RString.isNotEmpty(sEventTypeName)){
            config.set(PTY_EVENT_TYPE_NAME, sEventTypeName);
         }
         String sEventPriorityCd = getEventPriorityCd();
         if(RString.isNotEmpty(sEventPriorityCd)){
            config.set(PTY_EVENT_PRIORITY_CD, sEventPriorityCd);
         }
         String sEventExecute = getEventExecute();
         if(RString.isNotEmpty(sEventExecute)){
            config.set(PTY_EVENT_EXECUTE, sEventExecute);
         }
         String sConditionName = getConditionName();
         if(RString.isNotEmpty(sConditionName)){
            config.set(PTY_CONDITION_NAME, sConditionName);
         }
         String sTypeName = getTypeName();
         if(RString.isNotEmpty(sTypeName)){
            config.set(PTY_TYPE_NAME, sTypeName);
         }
         String sProcessResult = getProcessResult();
         if(RString.isNotEmpty(sProcessResult)){
            config.set(PTY_PROCESS_RESULT, sProcessResult);
         }
         String sProcessOrder = getProcessOrder();
         if(RString.isNotEmpty(sProcessOrder)){
            config.set(PTY_PROCESS_ORDER, sProcessOrder);
         }
      }
   }
}