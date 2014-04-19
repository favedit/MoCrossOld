//==========================================================
// <T>校验管理类。</T>
//
// @manager
// @history 091126 MAOCY 创建
//==========================================================
function MValidator(o){
   o = RClass.inherits(this, o);
   //..........................................................
   // @attribute
   o._validable = false;
   o._valid     = true;
   o._validText = null;
   //..........................................................
   // @process
   o.oeValid    = RMethod.empty;
   return o;
}
