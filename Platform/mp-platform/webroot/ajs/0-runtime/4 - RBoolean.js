//==========================================================
// 布尔操作的引用类
//
// @reference
// @author maochunyang
// @version 1.0.1
//==========================================================
var RBoolean = new function(){
   var o = this;
   // Method
   o.isTrue   = RBool_isTrue;
   o.toString = RBool_toString;
   // Construct
   RMemory.register('RBool', o);
   return o;
}
var RBool = RBoolean;

//==========================================================
// 判断字符串是否为真值
//
// @method
// @param v:value:String 
// @return Boolean 
//==========================================================
function RBool_isTrue(v){
   return (v == EBool.True);
}

//==========================================================
// 把真假值转化为字符串
//
// @method
// @param v:value:Boolean 
// @return String 
//==========================================================
function RBool_toString(v){
   return v ? EBool.True : EBool.False;
}
