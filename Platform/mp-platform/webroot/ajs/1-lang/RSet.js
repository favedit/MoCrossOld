//============================================================
// RSet
//============================================================
var RSet = new function(){
   var o = this;
   // Method
   o.contains    = RSet_contains;
   o.containsStr = RSet_containsStr;
   // Construct
   RMemory.register('RSet', o);
   return o;
}

//===========================================================
// source, value
//===========================================================
function RSet_contains(v, e){
   return e == (e & v);
}

//===========================================================
// source, value
//===========================================================
function RSet_containsStr(s, v){
   return RString.contains(s, v);
}

