//===========================================================
// 该类是定义了字符格式的枚举类
//
// @enum
// @author maochunyang
// @version 1.0.1
//===========================================================
function ECaseFace(){
   var o = this;
   ///@Attribute String 字符大写
   o.Upper = 'U';
   ///@Attribute String 字符小写
   o.Lower = 'L';
   ///@Attribute String 字符按源格式写
   o.Word  = 'W';
   return o;
}
var ECase = new ECaseFace();
