//==========================================================
// <T>文本编辑管理接口。</T>
//
// @class MValidator, MListView, MZoom
// @history 091105 MAOCY 创建
//==========================================================
function MDescEdit(o){
   o = RClass.inherits(this, o, MValidator, MListView, MZoom);
   //..........................................................
   // @property
   o.editCase     = RClass.register(o, new TPtyStr('editCase'));
   o.editPattern  = RClass.register(o, new TPtyStr('editPattern'));
   o.editLength   = RClass.register(o, new TPtyInt('editLength'));
   o.editComplete = RClass.register(o, new TPtyBool('editComplete'));
   o.validLenmin  = RClass.register(o, new TPtyInt('validLenmin'));
   o.validLenmax  = RClass.register(o, new TPtyInt('validLenmax'));
   //..........................................................
   // @process
   o.oeValid      = MDescEdit_oeValid;
   return o;
}

//==========================================================
// <T>校验处理。</T>
//
// @method
// @param e:event:TEvent 事件对象
//==========================================================
function MDescEdit_oeValid(e){
   var o = this;
   var r = EEventStatus.Stop;
   // 判断是否需要检查
   if(o._visible && o._validable){
      var t = o.text();
      // 必须性检查
      if(o.validRequire && !RValidator.validRequire(o, t)){
         e.controls.push(o);
         return r;
      }
      // 长度检查
      if(o.editLength && !RValidator.validTextLength(o, t, o.editLength)){
         e.controls.push(o);
         return r;
      }
   }
   return r;
}
