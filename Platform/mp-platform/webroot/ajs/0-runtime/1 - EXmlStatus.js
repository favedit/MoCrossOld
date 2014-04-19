//===========================================================
// 该类是一个定义xml连接状态的枚举类
//
// @enum
// @author maochunyang
// @version 1.0.1
//===========================================================
function EXmlStatusFace(){
   var o = this;
   ///@attribute Integer 准备连接
   o.Begin   = 0; // Uninitialize
   ///@attribute Integer 初始化连接
   o.Build   = 1; // Initialize
   ///@attribute Integer 发送连接
   o.Send    = 2; // Send
   ///@attribute Integer 接收回馈消息
   o.Receive = 3; // Receive
   ///@attribute Integer 完成连接
   o.Finish  = 4; // Finish
   return o;
}
var EXmlStatus = new EXmlStatusFace();
