//==========================================================
// <T>可加载和保存数据的接口。</T>
//
// @manger
// @history 091012 MAOCY 创建
//==========================================================
function MValue(o){
   o = RClass.inherits(this, o);
   //..........................................................
   // @method
   o.loadValue = RMethod.virtual(o, 'loadValue');
   o.saveValue = RMethod.virtual(o, 'saveValue');
   return o;
}
