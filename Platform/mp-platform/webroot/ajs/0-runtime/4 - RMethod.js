//==========================================================
// <T>对象类的函数处理的工具类。</T>
//
// @reference
// @author maocy
// @version 1.0.1
//==========================================================
var RMethod = new function(){
   var o = this;
   // Attribute
   o.virtuals   = new Object();
   o.events     = new Object();
   // Method
   o.isFunction = RMethod_isFunction;
   o.isEmpty    = RMethod_isEmpty;
   o.isVirtual  = RMethod_isVirtual;
   o.name       = RMethod_name;
   o.fullName   = RMethod_fullName;
   o.empty      = RMethod_empty;
   o.emptyTrue  = RMethod_emptyTrue;
   o.emptyFalse = RMethod_emptyFalse;
   o.emptyCall  = RMethod_emptyCall;
   o.virtual    = RMethod_virtual;
   // Construct
   o.empty._empty = true;
   o.emptyTrue._empty = true;
   o.emptyFalse._empty = true;
   // Construct
   RMemory.register('RMethod', o);
   return o;
}

//==========================================================
// <T>测试对象是否是为函数。</T>
//
// @method
// @param v:value:Object 函数对象
// @return Boolean
//    <L value='true'>是</L>
//    <L value='false'>否</L>
//==========================================================
function RMethod_isFunction(v){
   return 'function' == typeof(v);
}

//==========================================================
// <T>测试对象是否是为空函数。</T>
//
// @method
// @param v:value:Object 函数对象
// @return Boolean
//    <L value='true'>是</L>
//    <L value='false'>否</L>
//==========================================================
function RMethod_isEmpty(v){
   return (v && v._empty);
}

//==========================================================
// <T>测试对象是否是为虚函数。</T>
//
// @method
// @param v:value:Object 函数对象
// @return Boolean
//    <L value='true'>是</L>
//    <L value='false'>否</L>
//==========================================================
function RMethod_isVirtual(v){
   return (v && v._virtual);
}

//==========================================================
// <T>获得函数的字符串名称。</T>
//
// @method
// @param v:value:Function 函数对象
// @return String 字符串名称
//==========================================================
function RMethod_name(v){
   return ('function' == typeof(v)) ? RString.mid(v.toString(), 'function ', '(') : null;
}

//==========================================================
// <T>获得含有参数信息的函数的字符串名称。</T>
//
// @method
// @param v:value:Function 函数对象
// @return String 字符串名称
//==========================================================
function RMethod_fullName(v){
   return ('function' == typeof(v)) ? RString.mid(v.toString(), 'function ', ')') + ')' : null;
}

//==========================================================
// <T>没有返回值的空函数定义。</T>
//
// @method
//==========================================================
function RMethod_empty(){
}

//==========================================================
// <T>返回值为真的空函数定义。</T>
//
// @method
// @return Boolean 真值
//==========================================================
function RMethod_emptyTrue(){
   return true;
}

//==========================================================
// <T>返回值为假的空函数定义。</T>
//
// @method
// @return Boolean 假值
//==========================================================
function RMethod_emptyFalse(){
   return false;
}

//==========================================================
// <T>空调用。</T>
//
// @method
// @return Boolean 假值
//==========================================================
function RMethod_emptyCall(){
}

//==========================================================
// <T>创建一个虚函数。</T>
//
// @method
// @param v:value:Object 对象实例
// @param m:method:String 函数名称
// @return Function 虚函数
//==========================================================
function RMethod_virtual(v, m){
   var o = this;
   var n = RClass.name(v) + '.' + m;
   if(o.virtuals[n]){
      return o.virtuals[n];
   }
   // 创建虚函数对象
   var f = function(){throw new Error('Virtual method be called.(' + n + ')');};
   f._virtual = true;
   f._name = n;
   o.virtuals[n] = f;
   return f;
}
