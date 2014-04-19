//===========================================================
// 该类是一个定义number的格式类型的枚举类
//
// @enum
// @author maochunyang
// @version 1.0.1
//===========================================================
function ENumberTypeFace(){
   var o = this;
   ///I整数,PI正整数,NI负整数，F浮点数，PF正浮点数，NF负浮点数
   o.Integer              = 'I';
   o.PositiveInteger      = 'PI';
   o.NegativeInteger      = 'NI';
   o.Float                = 'F';
   o.PositiveFloat        = 'PF';
   o.NegativeFloat        = 'NF';
   return o;
}
var ENumberType = new ENumberTypeFace();

