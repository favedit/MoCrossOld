//==========================================================
// <T>字符串的工具类。</T>
//
// @reference
//==========================================================
var RChar = new function(){
   var o = this;
   // @method
   o.parse     = RChar_parse;
   o.toInteger = RChar_toInteger;
   // @construct
   RMemory.register('RChar', o);
   return o;
}

//==========================================================
// <T>将一个数字转换为字符</T>
//
// @method
// @param n:number:Number 数字
// @return 字符
//==========================================================
function RChar_parse(n){
   return String.fromCharCode(n);
}

//==========================================================
// <T>将一个字符转换为数字</T>
//
// @method
// @param c:char:String 字符
// @return 数字
//==========================================================
function RChar_toInteger(c){
   return c.charCodeAt(0);
}
