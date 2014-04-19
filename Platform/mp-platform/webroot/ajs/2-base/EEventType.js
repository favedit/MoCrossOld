/**************************************************************
 * 事件处理前后类型枚举
 *
 * @enum
 * @author maochunyang
 * @version 1.0.1
 **************************************************************/
function EEventTypeFace(o){
   if(!o){o=this;}
   // Type
   o.None   = 0;
   o.Before = 1;
   o.After  = 2;
   return o;
}
var EEventType = new EEventTypeFace();
