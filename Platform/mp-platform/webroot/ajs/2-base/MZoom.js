//==========================================================
// <T>数据详细窗口的接口。</T>
//
// @manger
// @history 091012 MAOCY 创建
//==========================================================
function MZoom(o){
   o = RClass.inherits(this, o);
   //..........................................................
   // @property
   o.zoomRefer = RClass.register(o, new TPtyStr('zoomRefer'));
   o.zoomField = RClass.register(o, new TPtyStr('zoomField'));
   //..........................................................
   // @method
   o.canZoom   = MZoom_canZoom;
   o.doZoom    = MZoom_doZoom;
   return o;
}

//==========================================================
// <T>判断当前对象是否允许显示详细窗口。</T>
//
// @method
// @return Boolean
//    <L value='true'>允许</L>
//    <L value='false'>不允许</L>
//==========================================================
function MZoom_canZoom(){
   return !RString.isEmpty(this.zoomRefer);
}

//==========================================================
// <T>弹出关联的数据详细窗口。</T>
//
// @method
// @param v:value:String 数据
//==========================================================
function MZoom_doZoom(v){
   RFormSpace.doZoom(this, v);
}
