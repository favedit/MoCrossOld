/**************************************************************
 * 标签的显示状态
 *
 * @enum
 * @author maochunyang
 * @version 1.0.1
 **************************************************************/
function ELabelFace(){
   var o = this;
   /// @attribute String 显示所有
   o.All    = 'A';
   /// @attribute String 显示所有
   o.Label  = 'L';
   /// @attribute String 显示所有
   o.Hidden = 'H';
   return o;
}
var ELabel = new ELabelFace();
