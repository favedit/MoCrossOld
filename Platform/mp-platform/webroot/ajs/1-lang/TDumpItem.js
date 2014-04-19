//===========================================================
// 整型对象的操作类
//
// @reference
// @author maochunyang
// @version 1.0.1
//===========================================================
function TDumpItem(){
   var o = this;
   // Attribute
   o.hPanel       = null;
   o.hDocument    = null;
   o.hTable       = null;
   o.hText        = null;
   o.hRow         = null;
   o.link         = null;
   o.level        = 0;
   o.caption      = null;
   o.children     = new Array();
   o.items        = new Array();
   o.loaded       = false;
   o.innerDisplay = false;
   o.display      = false;
   // Method
   o.create       = TDumpItem_create;
   o.push         = TDumpItem_push;
   o.innerShow    = TDumpItem_innerShow;
   o.show         = TDumpItem_show;
   return o;
}

//===========================================================
//
//===========================================================
function TDumpItem_create(){
   var di = this.children[this.children.length] = new TDumpItem();
   return di;
}

//===========================================================
//
//===========================================================
function TDumpItem_push(obj){
   this.items[this.items.length] = obj;
   return obj;
}

//===========================================================
//
//===========================================================
function TDumpItem_innerShow(display){
   for(var n=0; n<this.items.length; n++){
      var tr = this.items[n];
      tr.style.display = display ? 'block' : 'none';
   }
   for(var n=0; n<this.children.length; n++){
      var di = this.children[n];
      di.hRow.style.display = display ? 'block' : 'none';
      if(display){
         di.show(di.innerDisplay);
      }else{
         di.innerDisplay = di.display;
         di.show(false);
      }
   }
}

//===========================================================
//
//===========================================================
function TDumpItem_show(display){
   this.display = display;
   var icon = display ? '-' : '+';
   this.hText.innerText = RString.repeat('   ', this.level-1) + icon + ' ' + this.caption;
   this.innerShow(display);
}

