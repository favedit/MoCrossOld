//==========================================================
// <T>所有可继承对象的基类。</T>
// <P>支持类的判断、获取内部运行信息的功能。</P>
//
// @class
// @history 090729 MAOCY 创建
//==========================================================
function FObject(o){
   if(!o){o=this;}
   /// @attribute TClass 类对象
   o._class    = null;
   //..........................................................
   /// @method
   o.construct = FObject_construct;
   /// @method
   o.toString  = FObject_toString;
   /// @method
   o.dispose   = FObject_dispose;
   /// @method
   o.dump      = FObject_dump;
   return o;
}

//==========================================================
// <T>构建当前对象的实例。</T>
//
// @method
//==========================================================
function FObject_construct(){
}

//==========================================================
// <T>获取当前实例的信息。</T>
//
// @method
// @return String 含有内部信息的字符串
//==========================================================
function FObject_toString(){
   return RClass.dump(this);
}

//==========================================================
// <T>释放当前实例。</T>
//
// @method
//==========================================================
function FObject_dispose(){
   this._class = null;
}

//==========================================================
// <T>获取当前实例的信息。</T>
//
// @method
// @param s:dump:TString 字符串
// @param l:level:Integer 递归层次
// @return TString 含有内部信息的字符串
//==========================================================
function FObject_dump(s, l){
   s = RStringBuffer.nvl(s);
   s.append(RClass.dump(this));
   return s;
}
