//==========================================================
// <T>尺寸模式</T>
//
// @enum
// @history 090901 MAOCY 创建
//==========================================================
function ESizeFace(){
   var o = this;
   // @attribute 普通
   o.Normal     = 0
   // @attribute 横向自动
   o.Horizontal = 1
   // @attribute 纵向自动
   o.Vertical   = 2
   // @attribute 全部自动
   o.Both       = 3;
   return o;
}
var ESize = new ESizeFace();
