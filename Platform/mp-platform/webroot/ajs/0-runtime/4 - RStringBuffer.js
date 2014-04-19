//==========================================================
// <T>字符串缓冲的工具类。</T>
//
// @tool
// @author maocy
// @version 1.0.1
//==========================================================
var RStringBuffer = new function(){
   var o = this;
   // Method
   o.nvl         = RStringBuffer_nvl;
   // Construct
   RMemory.register('RStringBuffer', o);
   return o;
}

//==========================================================
// <T>返回一个不为空的字符串对象。</T>
//
// @method
// @param v:value:String 字符串对象
// @return Boolean 非空字符串对象
//==========================================================
function RStringBuffer_nvl(v){
   return v ? v : new TString();
}
