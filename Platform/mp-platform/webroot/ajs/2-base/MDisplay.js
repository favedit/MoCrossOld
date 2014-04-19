//==========================================================
// <T>可以显示工具条按钮的接口类。</T>
//
// @manger
// @history 090805 MAOCY 创建
//==========================================================
function MDisplay(o){
   o = RClass.inherits(this, o);
   //..........................................................
   // @property
   o.dispDisplay = RClass.register(o, new TPtyBoolSet('dispDisplay', 'dispMode', EMode.Display));
   o.dispSearch  = RClass.register(o, new TPtyBoolSet('dispSearch', 'dispMode', EMode.Search));
   o.dispInsert  = RClass.register(o, new TPtyBoolSet('dispInsert', 'dispMode', EMode.Insert));
   o.dispUpdate  = RClass.register(o, new TPtyBoolSet('dispUpdate', 'dispMode', EMode.Update));
   o.dispDelete  = RClass.register(o, new TPtyBoolSet('dispDelete', 'dispMode', EMode.Delete));
   o.dispZoom    = RClass.register(o, new TPtyBoolSet('dispZoom', 'dispMode', EMode.Zoom));
   // @property
   o.dispAlign   = RClass.register(o, new TPtyStr('dispAlign', EAlign.Left));
   //..........................................................
   // @attribute
   o._visible    = true;
   //..........................................................
   // @process
   o.oeMode      = MDisplay_oeMode;
   //..........................................................
   // @method
   o.canVisible  = MDisplay_canVisible;
   return o;
}

//==========================================================
// <T>根据工作模式改变当前控件的显示状态。</T>
//
// @method
// @param e:event:TEvent 事件对象
//==========================================================
function MDisplay_oeMode(e){
   var o = this;
   if(e.isBefore()){
      var v = true;
      if(!o.base.MDisplayAble){
         v = o.canVisible(e.mode);
      }
      o.setVisible(v);
   }
}

//==========================================================
// <T>根据模式获得控件的可见性。</T>
//
// @method
// @param m:mode:EMode 模式
// @param e:event:TEvent 事件对象
//==========================================================
function MDisplay_canVisible(m){
   var o = this;
   switch(RString.nvl(m, o._emode)){
      case EMode.Display:
         return o.dispList;
      case EMode.Search:
         return o.dispSearch;
      case EMode.Insert:
         return o.dispInsert;
      case EMode.Update:
         return o.dispUpdate;
      case EMode.Delete:
         return o.dispDelete;
      case EMode.Zoom:
         return o.dispZoom;
   }
}
