//===========================================================
// 存放表格中一列的属性类
//
// @tool
// @author maochunyang
// @version 1.0.1
//===========================================================
function THtmlItem(){
   var o = this;
   // Property
   o.link  = null;
   o.links = new Object();
   // Method
   o.get   = THtmlItem_get;
   o.set   = THtmlItem_set;
   return o;
}

//===========================================================
// name
//===========================================================
function THtmlItem_get(n){
   return this.links[n];
}

//===========================================================
// name, value
//===========================================================
function THtmlItem_set(n, v){
   this.links[n] = v;
}
