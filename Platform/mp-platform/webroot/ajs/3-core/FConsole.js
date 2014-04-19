//==========================================================
// <T>后台服务线程组件的基类。</T>
//
// @class FObject
// @history 091117 MAOCY 创建
//==========================================================
function FConsole(o){
   o = RClass.inherits(this, o, FObject);
   //..........................................................
   // @attribute
   o.scope = EScope.Global;
   return o;
}
