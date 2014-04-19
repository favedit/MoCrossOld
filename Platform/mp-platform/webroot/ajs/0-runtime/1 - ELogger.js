//==========================================================
// 该类是一个定义Bool类型的枚举类
//
// @enum
// @author maochunyang
// @version 1.0.1
//==========================================================
function ELoggerFace(){
   var o = this;
   o.Debug = 'D';
   o.Info  = 'I';
   o.Warn  = 'W';
   o.Error = 'E';
   o.Fatal = 'F';
   return o;
}
var ELogger = new ELoggerFace();

