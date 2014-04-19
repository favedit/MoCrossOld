//============================================================
// 二维坐标的工具类
//
// @tool
// @author maochunyang
// @version 1.0.1
//============================================================
function TPoint(x, y){
   var o = this;
   // Attribute
   o.x      = x == null? 0 : parseInt(x);
   o.y      = x == null? 0 : parseInt(y);
   // Method
   o.equals = TPoint_equals;
   o.assign = TPoint_assign;
   o.set    = TPoint_set;
   o.dump   = TPoint_dump;
   return o;
}

//============================================================
// 判断两个坐标是否相等
//
// @tool
// @param p:position:TPoint
// @return boolean  是否相等，返回布尔值
//============================================================
function TPoint_equals(p){
   return p ? (this.x == p.x && this.y == p.y) : false;
}

//============================================================
// 根据一个坐标来设置当前坐标的位置
//
// @method
// @param pos:position:TPoint 传入的坐标点
//============================================================
function TPoint_assign(pos){
   this.x = pos.x;
   this.y = pos.y;
}

//============================================================
// 设置坐标值
//
// @method
// @param x:xPostion:Integer x坐标值
// @param y:yPostion:Integer y坐标值
//============================================================
function TPoint_set(x, y){
   this.x = x;
   this.y = y;
}

//============================================================
// 判断两个坐标是否相等
//
// @tool
// @param p:position:TPoint
// @return boolean  是否相等，返回布尔值
//============================================================
function TPoint_dump(){
   return RClass.dump(this) + ' [' + this.x + ',' + this.y + ']';
}
