//===========================================================
// <T>客户端游览器类型的枚举类。</T>
//
// @enum
// @author maochunyang
// @version 1.0.1
//===========================================================
function EBrowserFace(){
   var o = this;
   // Attribute
   ///@attribute Integer Microsoft IE
   o.IE = 0; // Microsoft IE
   ///@attribute Integer Nescape   NC
   o.NC = 1; // Nescape   NC
   return o;
}
//-----------------------------------------------------------
var EBrowser = new EBrowserFace();
