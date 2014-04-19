//============================================================
// REnumFace
//============================================================
var REnum = new function(){
   var o = this;
   // Method
   o.encode    = REnum_encode;
   o.decode    = REnum_decode;
   o.tryEncode = REnum_tryEncode;
   o.tryDecode = REnum_tryDecode;
   o.contains  = REnum_contains;
   // Construct
   RMemory.register('REnum', o);
   return o;
}

//============================================================
//
//============================================================
function REnum_encode(e, v){
   var v = this.tryEncode(e, v);
   if(null == v){
      RMessage.fatal(this, 'encode', 'Invalid value (enum={0}, value={1})', RClass.dump(e), v); 
   }
   return v;
}

//============================================================
//
//============================================================
function REnum_decode(e, v){
   var v = this.tryDecode(e, v);
   if(null == v){
      RMessage.fatal(this, 'decode', 'Invalid value (enum={0}, value={1})', RClass.dump(e), v); 
   }
   return v;
}

//============================================================
//
//============================================================
function REnum_tryEncode(e, v, d){
   for(var n in e){
      if(n.toLowerCase() == v.toLowerCase()){
         return e[n];
      }
   }
   return d;
}

//============================================================
//
//============================================================
function REnum_tryDecode(e, v, d){
   for(var n in e){
      if(e[n] == v){
         return n;
      }
   }
   return d;
}

//============================================================
//
//============================================================
function REnum_contains(){
}

