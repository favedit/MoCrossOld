//==========================================================
// <T>对象存储的工具类。</T>
//
// @tool
// @param s:space:ESpace 存储范围
// @param n:name:String 对象名称
// @param v:value:Object 对象实例
// @author maocy
// @version 1.0.1
//==========================================================
function TSpeed(){
   var o = this;
   // Attribute
   o.arguments  = arguments;
   o.start      = new Date().getTime();
   o.callerName = RMethod.name(TSpeed.caller);
   // Method
   o.record     = TSpeed_record
   return o;
}
function TSpeed_record(){
   var o = this;
   var sp = new Date().getTime() - o.start;
   RLogger.log(ELogger.Debug, o.callerName, sp, o.arguments);
   o.arguments = null;
   o.start = null;
   o.callerName = null;
   o.record = null;
}
