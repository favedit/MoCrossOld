//==========================================================
// <T>事件处理类。</T>
//
// @tool
// @param w:owner:Object 拥有者
// @param t:type:EEventType 处理类型
// @param p:process:Function 处理调用
// @history 090930 MAOCY 创建
//==========================================================
function TEventProcess(w, p, c){
   var o = this;
   // Attribute
   o.type     = null;
   o.owner    = w;
   o.invoke   = p;
   o.clazz    = RClass.name(c);
   // Method
   o.isBefore = TEventProcess_isBefore;
   o.isAfter  = TEventProcess_isAfter;
   o.process  = TEventProcess_process;
   o.dump     = TEventProcess_dump;
   return o;
}

//==========================================================
// <T>测试是否是事件前处理。</T>
// 
// @method
// @return Boolean
//    <L value='true'>是</L>
//    <L value='false'>否</L>
//==========================================================
function TEventProcess_isBefore(){
   return (EEventType.Before == this.type);
}

//==========================================================
// <T>测试是否是事件后处理。</T>
// 
// @method
// @return Boolean
//    <L value='true'>是</L>
//    <L value='false'>否</L>
//==========================================================
function TEventProcess_isAfter(){
   return (EEventType.After == this.type);
}

//==========================================================
// <T>执行事件的处理。</T>
// 
// @method
// @param s:sender:FComponent 相应事件对象
// @param t:type:EEventType 事件类型
// @return EEventStatus 事件状态
//==========================================================
function TEventProcess_process(s, t){
   var o = this;
   // 检查对象类型
   if(!s.base[o.clazz]){
      return;
   }
   // 检查函数调用
   var p = s[o.invoke];
   if(!p){
      return RMessage.fatal(o, null, 'Process invoke is null. (sender={0}, invoke={1})', RClass.dump(s), o.invoke);
   }
   // 调用函数
   var r = null;
   var sp = new TSpeed(o, 'Process invoke (owner={0}, invoke={1})', s, o.invoke);
   o.type = t;
   r = p.call(s, o);
   sp.record();
   return r;
}

//==========================================================
// <T>获取事件对象的运行信息。</T>
// 
// @method
// @return String 运行信息
//==========================================================
function TEventProcess_dump(){
   var o = this;
   return RClass.dump(o) + ':owner=' + o.owner + ',type=' + o.type + '.invoke=' + RMethod.name(o.invoke);
}
