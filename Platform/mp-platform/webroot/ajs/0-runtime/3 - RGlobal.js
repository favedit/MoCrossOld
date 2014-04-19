//==========================================================
// <T>全局对象的管理类。</T>
//
// @reference
// @author maocy
// @version 1.0.1
//==========================================================
var RGlobal = new function(){
   var o = this;
   /// @attribute TMap 全局对象的存储表
   o.instances = new TMap();
   // Method
   o.get       = RGlobal_get;
   o.set       = RGlobal_set;
   return o;
}
//----------------------------------------------------------
// 全局化当前对象
if(top){
   if(top.RGlobal){
      RGlobal = top.RGlobal;
   }
}

//==========================================================
// <T>根据名称获得一个全局对象。</T>
//
// @method
// @param n:name:String 名称
// @return Object 全局对象
//==========================================================
function RGlobal_get(n){
   return this.instances.get(n);
}

//==========================================================
// <T>根据名称存储一个全局对象。</T>
//
// @method
// @param n:name:String 名称
// @param v:value:Object 全局对象
//==========================================================
function RGlobal_set(n, v){
   this.instances.set(n, v);
}
