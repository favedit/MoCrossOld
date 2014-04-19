/**************************************************************
 * 控件的状态类型枚举
 *
 * @enum
 * @author maochunyang
 * @version 1.0.1
 **************************************************************/
function EEventFace(){
   var o = this;
   /// @attribute Integer 未设置
   o.None       = 0;
   /// @attribute Integer 类构造
   o.Construct  = 1;
   /// @attribute Integer 初始化
   o.Initialize = 2;
   /// @attribute Integer 可视化框架
   o.Build      = 3;
   /// @attribute Integer 刷新
   o.Refresh    = 4;
   /// @attribute Integer 改变大小
   o.Resize     = 5;
   /// @attribute Integer 更改可视化
   o.Visible    = 6;
   /// @attribute Integer 显示
   o.Show       = 7;
   /// @attribute Integer 隐藏
   o.Hidden     = 8;
   /// @attribute Integer 允许
   o.Enable     = 9;
   /// @attribute Integer 禁止
   o.Disable    = 10;
   /// @attribute Integer 释放
   o.Release    = 11;
   /// @attribute Integer 设计
   o.Design     = 12;
   /// @attribute Integer 命令
   o.Action     = 13;
   /// @attribute Integer 校验
   o.Valid      = 14;
   /// @attribute Integer 模式
   o.Mode       = 15;
   return o;
}
var EEvent = new EEventFace();
