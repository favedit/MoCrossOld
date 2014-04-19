/**************************************************************
 * ???
 *
 * @enum
 * @author maochunyang
 * @version 1.0.1
 **************************************************************/
function EPanelFace(){
   var o = this;
   // Attribute
   o.Display   = 0;
   o.Panel     = 1;
   o.Border    = 2;
   o.Edit      = 3;
   o.Focus     = 4;
   o.Design    = 5;
   o.Container = 6;
   o.Scroll    = 7;
   o.Shadow    = 8;
   o.Size      = 9;
   o.Move      = 10;
   o.Disable   = 11;
   return o;
}
var EPanel = new EPanelFace();
