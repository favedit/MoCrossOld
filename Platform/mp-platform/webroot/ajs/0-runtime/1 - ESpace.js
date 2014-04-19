//==========================================================
// <T>范围空间的定义。</T>
//
// @enum
// @author maocy
// @version 1.0.1
//==========================================================
var ESpace = new function(){
   var o = this;
   /// @member 本地空间
   o.Local = 1;
   /// @member 页面内共享空间
   o.Top = 2;
   /// @member 全局空间
   o.Global = 4;
   return o;
}
