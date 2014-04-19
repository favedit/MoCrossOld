/**************************************************************
 * 事件处理状态枚举
 *
 * @enum
 * @author maochunyang
 * @version 1.0.1
 **************************************************************/
function EEventStatusFace(o){
   if(!o){o=this;}
   // Attribute
   o.None     = 0;
   o.Continue = 1;
   o.Stop     = 2;
   o.Cancel   = 3;
   return o;
}
var EEventStatus = new EEventStatusFace();
