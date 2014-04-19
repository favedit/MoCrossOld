//============================================================
// TSize
//============================================================
function TSize(w, h){
   var o = this;
   // Attribute
   o.width  = RInt.nvl(w);
   o.height = RInt.nvl(h);
   // Method
   o.assign = TSize_assign;
   o.set    = TSize_set;
   o.dump   = TSize_dump;
   return o;
}

//============================================================
//
//============================================================
function TSize_assign(o){
   this.width = o.width;
   this.height = o.height;
}

//============================================================
// width, height
//============================================================
function TSize_set(w, h){
   this.width = w;
   this.height = h;
}

//============================================================
//
//============================================================
function TSize_dump(){
   return RClass.dump(this) + ' [' + this.width + ',' + this.height + ']';
}
