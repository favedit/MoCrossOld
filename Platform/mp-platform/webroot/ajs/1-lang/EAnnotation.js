//===========================================================
// <T>该类是一个定义Annotation类型的枚举类<T>
//
// @enum
// @author maochunyang
// @version 1.0.1
//===========================================================
function EAnnotationFace(){
   var o = this;
   /// @attribute EAnnotation 属性
   o.Property  = 'property';
   /// @attribute EAnnotation 事件
   o.Event     = 'event';
   /// @attribute EAnnotation 样式
   o.Style     = 'style';
   /// @attribute EAnnotation 样式图标
   o.StyleIcon = 'icon';
   return o;
}
var EAnnotation = new EAnnotationFace();
