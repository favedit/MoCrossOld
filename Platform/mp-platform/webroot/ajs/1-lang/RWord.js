//============================================================
// RWord
//============================================================
var RWord = new function(){
   var o = this;
   // Method
   o.ulName = RWord_ulName;
   // Construct
   RMemory.register('RWord', o);
   return o;
}

//===========================================================
//
//===========================================================
function RWord_ulName(s){
   var r = new TString();
   if(s){
      for(var i=0; i<s.length; i++){
         var c = s.charAt(i);
         if(c.toUpperCase() == c){
            if(i > 0){
               r.append('_');
            }
            r.append(c.toLowerCase());
         }else{
            r.append(c);
         }
      }
   }
   return r;
}

