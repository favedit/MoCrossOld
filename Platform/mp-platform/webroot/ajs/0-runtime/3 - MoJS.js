//==========================================================
// 设置页面全局信息的操作类
//
// @reference
// @author maochunyang
// @version 1.0.1
//==========================================================
var MoJS = new function(){
   var o = this;
   // Attribute
   o._spaces    = new Object();
   o.Global     = new Object();
   o.Top        = new Object();
   o.Local      = new Object();
   // Event
   o.onRelease  = MoJS_onRelease;
   // Method
   o.register   = MoJS_register;
   o.initialize = MoJS_initialize;
   o.connect    = MoJS_connect;
   o.buildSpace = MoJS_buildSpace;
   o.find       = MoJS_find;
   o.findGlobal = MoJS_findGlobal;
   o.findTop    = MoJS_findTop;
   o.findLocal  = MoJS_findLocal;
   return o;
}

//==========================================================
// <T>释放引擎中所有的对象。</T>
//
// @method
// =========================================================
function MoJS_onRelease(){
   RConsole.release();
   REvent.release();
   CollectGarbage();
}

//==========================================================
// <T>向引擎工具中注册对象类型。</T>
//
// @method
// @param s:space:TSpace 存储对象描述
//==========================================================
function MoJS_register(s){
   var o = this;
   // 创建存储空间
   var p = o._spaces[s.space];
   if(!p){
      p = o._spaces[s.space] = new Object();
   }
   p[s.name] = s;
}

//==========================================================
// <T>初始化系统引擎。</T>
//
// @method
//==========================================================
function MoJS_initialize(){
   var o = this;
   // 初始化控制台
   RConsole.initialize();
   // 注册关闭事件
   //RWindow.lsnsUnload.register(o, o.onRelease);
   // Global
   //o.buildSpace(o.Global, o._spaces[ESpace.Global]);
   // Top
   //o.buildSpace(o.Top, o._spaces[ESpace.Global]);
   //o.buildSpace(o.Top, o._spaces[ESpace.Top]);
   // Local 
   //o.buildSpace(o.Local, o._spaces[ESpace.Global]);
   //o.buildSpace(o.Local, o._spaces[ESpace.Top]);
   //o.buildSpace(o.Local, o._spaces[ESpace.Local]);
}

//==========================================================
// <T>关联系统引擎。</T>
//
// @method
//==========================================================/
function MoJS_connect(){
   var o = this;
   // 初始化控制台
   RConsole.initialize();
   // 注册关闭事件
   //RWindow.lsnsUnload.register(o, o.onRelease);
   // Global
   //o.buildSpace(o.Global, top.MoJS._spaces[ESpace.Global]);
   // Top
   //o.buildSpace(o.Top, top.MoJS._spaces[ESpace.Top]);
   // Local 
   //o.buildSpace(o.Local, o._spaces[ESpace.Global]);
   //o.buildSpace(o.Local, o._spaces[ESpace.Top]);
   //o.buildSpace(o.Local, o._spaces[ESpace.Local]);
}

//==========================================================
// <T>建立空间内的对象结构。</T>
//
// @method
//==========================================================
function MoJS_buildSpace(t, p){
   var o = this;
   for(var n in p){
      if(RString.startsWith(n, 'R')){
         t[n.substring(1)] = p[n].instance;
      }
   }
}

//==========================================================
// <T>从指定范围内,根据对象名称中查找一个对象的实例。</T>
//
// @method
// @param n:name:String 对象名称
//==========================================================
function MoJS_find(s, n){
   var r = null;
   var s = this._spaces[s];
   if(s){
      r = s[n];
      if(r){
         return r.instance;
      }
   }
   return null;
}

//==========================================================
// <T>从全局范围内,根据对象名称中查找一个对象的实例。</T>
//
// @method
// @param n:name:String 对象名称
//==========================================================
function MoJS_findGlobal(n){
   return this.find(ESpace.Global, n);
}

//==========================================================
// <T>从最顶层页面范围内,根据对象名称中查找一个对象的实例。</T>
//
// @method
// @param n:name:String 对象名称
//==========================================================
function MoJS_findTop(n){
   return top.MoJS.find(ESpace.Top, n);
}

//==========================================================
// <T>从当前页面范围内,根据对象名称中查找一个对象的实例。</T>
//
// @method
// @param n:name:String 对象名称
//==========================================================
function MoJS_findLocal(n){
   return this.find(ESpace.Local, n);
}
