//===========================================================
// 该类是一个定义Bool类型的枚举类
//
// @enum
// @author maochunyang
// @version 1.0.1
 //===========================================================
function EBoolFace(){
   var o = this;
   ///@attribute boolean 值为真
   o.True   = 'Y';
   ///@attribute boolean 值为假
   o.False  = 'N';
   return o;
}
var EBool = EBoolean = new EBoolFace();
