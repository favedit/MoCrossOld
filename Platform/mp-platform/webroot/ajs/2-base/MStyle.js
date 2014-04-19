/**************************************************************
 * 获得控件样式表接口
 *
 * @manger
 * @author maochunyang
 * @version 1.0.1
 **************************************************************/
function MStyle(o){
   o = RClass.inherits(this, o);
   // Method
   o.style         = MStyle_style;
   o.styleIcon     = MStyle_styleIcon;
   o.styleIconPath = MStyle_styleIconPath;
   return o;
}
// ------------------------------------------------------------
/**
 * 根据当前类型获得定义的样式<br>
 * 样式不存在的话，产生例外
 * 
 * @param n:name:String 名称
 * @param c:class:TClass 类
 */
function MStyle_style(n, c){
   return RClass.find(c ? c : this, true).style(n);
}
// ------------------------------------------------------------
/**
 * 根据当前类型获得定义的样式图标<br>
 * 
 */
function MStyle_styleIcon(n, c){
   //return '#ctl.' + RClass.name(c ? c : this, true) + '_' + n;
   return 'ctl.' + RClass.name(c ? c : this, true) + '_' + n;
}
// ------------------------------------------------------------
/**
 * 根据当前类型获得定义的样式图标<br>
 * 
 */
function MStyle_styleIconPath(n, c){
   //return RRes.iconPath('#ctl.' + RClass.name(c ? c : this, true) + '_' + n);
   return RRes.iconPath('ctl.' + RClass.name(c ? c : this, true) + '_' + n);
}
// ------------------------------------------------------------
