//============================================================
// 三维坐标的工具类
//
// @tool
// @author maochunyang
// @version 1.0.1
//============================================================
function TPoint3(x, y, z){
   var o = this;
   // Property
   o.x      = x;
   o.y      = y;
   o.z      = z;
   o.set    = TPoint3_set;
   o.resize = TPoint3_resize;
   o.dump   = TPoint3_dump;
   return o;
}

//============================================================
// 设置三维坐标，初始化
//
// @tool
// @param x:xPosition:Integer X坐标
// @param y:yPosition:Integer Y坐标
// @param z:zPosition:Integer Z坐标
//============================================================
function TPoint3_set(x, y, z){
   if(x != null){
      this.x = x;
   }
   if(y != null){
      this.y = y;
   }
   if(z != null){
      this.z = z;
   }
}

//============================================================
// 把坐标的三个坐标放大X Y Z
//
// @tool
// @param x:xPosition:Integer X坐标
// @param y:yPosition:Integer Y坐标
// @param z:zPosition:Integer Z坐标
//============================================================
function TPoint3_resize(x, y, z){
   if(x != null){
      this.x += x;
   }
   if(y != null){
      this.y += y;
   }
   if(z != null){
      this.z += z;
   }
}

//============================================================
// ???
//
// @tool
// @param x:xPosition:Integer X坐标
// @param y:yPosition:Integer Y坐标
// @param z:zPosition:Integer Z坐标
//============================================================
function TPoint3_dump(){
   return IClass.typeOf(this) + ' [' + this.x + ',' + this.y + ',' + this.z + ']';
}
