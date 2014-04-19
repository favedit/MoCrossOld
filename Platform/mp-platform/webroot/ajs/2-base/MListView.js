//==========================================================
// <T>支持选取数据窗口的接口。</T>
//
// @manger
// @history 091012 MAOCY 创建
//==========================================================
function MListView(o){
   o = RClass.inherits(this, o);
   //..........................................................
   // @property
   o.lovService     = RClass.register(o, new TPtyStr('lovService', EService.WebForm));
   o.lovRefer       = RClass.register(o, new TPtyStr('lovRefer'));
   o.lovFields      = RClass.register(o, new TPtyStr('lovFields'));
   o.lovWhere       = RClass.register(o, new TPtyStr('lovWhere'));
   o.lovOrder       = RClass.register(o, new TPtyStr('lovOrder'));
   //..........................................................
   // @attribute
   o.__listView     = null;
   //..........................................................
   // @event
   o.onListClick    = RClass.register(o, new HClick('onListClick'), MListView_onListClick);
   o.onListSelected = RMethod.empty;
   //..........................................................
   // @method
   o.canListView    = MListView_canListView;
   o.setLabelStyle  = MListView_setLabelStyle;
   o.doListView     = MListView_doListView;
   return o;
}

//==========================================================
// <T>响应显示选取窗口的事件。</T>
//
// @method
//==========================================================
function MListView_onListClick(e){
   var o = this;
   if(o.canListView()){
      o.doListView();
   }
}

//==========================================================
// <T>判断当前对象是否允许显示选取窗口。</T>
//
// @return Boolean
//    <L value='true'>允许</L>
//    <L value='false'>不允许</L>
//==========================================================
function MListView_canListView(){
   return !RString.isEmpty(this.lovRefer) && this._editable;
}

//==========================================================
// <T>设置控件的标签样式。</T>
//
// @method
//==========================================================
function MListView_setLabelStyle(){
   var o = this;
   if(!RString.isEmpty(o.lovRefer)){
      o.hLabel.style.cursor = 'hand';
      o.attachEvent('onListClick', o.hLabel);
      o.hLabel.className = 'RLine_Underline';
   }
}

//==========================================================
// <T>弹出关联的数据选取窗口。</T>
//
// @method
//==========================================================
function MListView_doListView(cvs){
   var o = this;
   // 获取关联的选取窗口
   var v = o.__listView;
   if(!v){
      v = o.__listView = top.RControl.create(top.FListWindow);
   }
   // 显示选取窗口
   v.linkConsole = RConsole;
   v.linkLovControl(o);
   v.show();
   v.fetch(cvs);
}
